package com.mindtree.training.employee.exception;

public class UserAuthenticationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8008440582085725466L;
	private String user;

	public UserAuthenticationException(final String user) {
		this.user = user;
	}

}
