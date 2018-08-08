package com.mindtree.training.employee.exception;

public class NoEmployeeFound extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6821156318528566944L;
	private String empId;

	public NoEmployeeFound() {
	}

	public NoEmployeeFound(final String empId) {
		this.empId = empId;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

}
