package com.mindtree.training.employee.exceptionhandler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.training.employee.exception.NoEmployeeFound;
import com.mindtree.training.employee.exception.RequestInvalidDataException;
import com.mindtree.training.employee.exception.RequestInvalidException;
import com.mindtree.training.employee.exception.UserAuthenticationException;
import com.mindtree.training.employee.model.StatusResp;
import com.mindtree.training.employee.util.StatusUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RequestInvalidException.class)
	public ResponseEntity<StatusResp> invalidData(RequestInvalidException e) {
		StatusResp resp = new StatusResp();
		resp.setStatus(StatusUtil.FAILURE);
		resp.setMessage(e.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RequestInvalidDataException.class)
	public ResponseEntity<StatusResp> invalidData(RequestInvalidDataException e) {
		StatusResp resp = new StatusResp();
		resp.setStatus("FAILURE");

		if (e.getResult() != null) {
			BindingResult result = e.getResult();
			List<ObjectError> list = result.getAllErrors();
			if (!list.isEmpty())
				resp.setMessage(list.get(0).getDefaultMessage());
		}

		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserAuthenticationException.class)
	public ResponseEntity<StatusResp> authfail(UserAuthenticationException e) {
		StatusResp resp = new StatusResp();
		resp.setStatus(StatusUtil.FAILURE);
		resp.setMessage("Invalid Username and Password.");
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoEmployeeFound.class)
	public ResponseEntity<StatusResp> authfail(NoEmployeeFound e) {
		StatusResp resp = new StatusResp();
		resp.setStatus(StatusUtil.FAILURE);
		if (e.getEmpId() != null) {
			resp.setMessage("No Employee with id :" + e.getEmpId() + " found");

		} else {
			resp.setMessage("No Employee found");
		}
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}
}
