package com.mindtree.training.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.training.employee.exception.NoEmployeeFound;
import com.mindtree.training.employee.exception.RequestInvalidDataException;
import com.mindtree.training.employee.exception.RequestInvalidException;
import com.mindtree.training.employee.exception.UserAuthenticationException;
import com.mindtree.training.employee.model.Employee;
import com.mindtree.training.employee.model.StatusResp;
import com.mindtree.training.employee.model.UserLogin;
import com.mindtree.training.employee.service.EmployeeManagementService;
import com.mindtree.training.employee.util.StatusUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/EmpMgt")
@Api(basePath = "/EmpMgt", value = "employee Service api Will allow user to create and manage Employee")
@CacheConfig
public class EmployeeManagementController {

	@Autowired
	private EmployeeManagementService service;

	@PutMapping(path = "/addEmp", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "create a new employee", response = StatusResp.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "employee created successfully"),
			@ApiResponse(code = 400, message = "Employee has not been created successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public StatusResp createEmployee(@Valid @RequestBody Employee employee, BindingResult result) {

		if (result.hasErrors()) {
			throw new RequestInvalidDataException(result);
		}
		service.addEmployee(employee);
		StatusResp resp = new StatusResp();
		resp.setStatus(StatusUtil.SUCCESS);
		resp.setMessage("Employee data inserted successfully.");

		return resp;
	}

	@PutMapping(path = "/deleteEmp/{empId}", produces = "application/json")
	@ApiOperation(value = "delete employee by their id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Employee has been Deleted successfully"),
			@ApiResponse(code = 400, message = "Unable to Delete Employee"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public StatusResp deleteEmployee(@PathVariable("empId") String empId) {

		if (empId == null || empId.isEmpty()) {
			throw new RequestInvalidException("Invalid Employee Id");
		}

		boolean flag = service.deleteEmployeeById(new Long(empId));
		if (!flag) {
			throw new RequestInvalidException("Unable to Delete Employee");
		}

		StatusResp resp = new StatusResp();
		resp.setStatus(StatusUtil.SUCCESS);
		resp.setMessage("Employee data deleted successfully.");
		return resp;
	}

	@ApiOperation(value = "get employee details by their ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Employee details has been found by their id"),
			@ApiResponse(code = 400, message = "Employee details may not exist or id is incorrect"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(path = "/getByEmpId/{empId}", produces = "application/json")
	public StatusResp getEmployeeById(@PathVariable("empId") String empId) {
		Employee employee = null;
		if (empId != null && !empId.isEmpty()) {
			employee = service.getEmployeeById(new Long(empId));
			if (employee == null) {
				throw new RequestInvalidException("Invalid Employee Id");
			}
		}
		StatusResp resp = new StatusResp();
		resp.setStatus(StatusUtil.SUCCESS);
		resp.setData(new Employee[] { employee });
		return resp;
	}

	@ApiOperation(value = "Getting All the Employee Details")
	@ApiResponses({

			@ApiResponse(code = 200, message = "Employee Details are available"),
			@ApiResponse(code = 400, message = "No Employee data has found or some error occur while fetching"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(path = "/getAllEmpDetails")
	public StatusResp getAllEmployeeDetails() {

		List<Employee> list = service.getAllEmployee();
		if (list == null || list.isEmpty()) {
			throw new NoEmployeeFound();
		}
		StatusResp resp = new StatusResp();
		resp.setStatus(StatusUtil.SUCCESS);
		Employee[] earray = new Employee[list.size()];
		for (int i = 0; i < list.size(); i++) {
			earray[i] = list.get(i);
		}
		resp.setData(earray);
		return resp;
	}

	@ApiOperation(value = "authenticate employee with username and password")
	@ApiResponses({ @ApiResponse(code = 200, message = "Employee authenticated successfully"),
			@ApiResponse(code = 400, message = "Employee authentication fail / Invalid Username and password"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(path = "/checkLogin", consumes = "application/json", produces = "application/json")
	public StatusResp checkLogin(@Valid @RequestBody UserLogin user, BindingResult result) {
		boolean valid = false;

		if (result.hasErrors()) {
			throw new RequestInvalidException("Invalid Username and Password");
		}
		if (user != null) {
			valid = service.checkEmployeeLogin(user.getUser(), user.getPwd());
			if (!valid) {
				throw new UserAuthenticationException(user.getUser());
			}
		}
		StatusResp resp = new StatusResp();
		resp.setStatus(StatusUtil.SUCCESS);
		resp.setMessage("Employee has authenticated successfully.");
		return resp;
	}

}
