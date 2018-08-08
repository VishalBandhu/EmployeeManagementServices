package com.mindtree.training.employee.exception;

import org.springframework.validation.BindingResult;

public class RequestInvalidException extends RuntimeException {
	private static final long serialVersionUID = 8647451395048975226L;

	private BindingResult bindingResult;

	public RequestInvalidException(final String statusMessage) {
		super(statusMessage);
	}

}
