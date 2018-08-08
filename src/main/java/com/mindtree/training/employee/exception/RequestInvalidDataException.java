package com.mindtree.training.employee.exception;

import org.springframework.validation.BindingResult;

public class RequestInvalidDataException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3430150901967997696L;
	private BindingResult result;

	public RequestInvalidDataException(final BindingResult result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public BindingResult getResult() {
		return result;
	}

}
