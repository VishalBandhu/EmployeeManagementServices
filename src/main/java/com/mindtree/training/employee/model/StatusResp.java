package com.mindtree.training.employee.model;

import io.swagger.annotations.ApiModelProperty;

public class StatusResp {
	@ApiModelProperty(notes="response status type",example="SUCCESS",dataType="string")
	private String status;
	@ApiModelProperty(notes="status message",example="Invalid Username and Password")
	private String message;
	@ApiModelProperty(name="employee data")
	private Employee[] data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Employee[] getData() {
		return data;
	}

	public void setData(Employee[] data) {
		this.data = data;
	}

}
