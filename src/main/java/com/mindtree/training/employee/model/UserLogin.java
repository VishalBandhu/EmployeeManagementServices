package com.mindtree.training.employee.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLogin {
	@JsonProperty("userName")
	@NotNull
	@NotEmpty
	private String user;
	@JsonProperty("password")
	@NotNull
	@NotEmpty
	private String pwd;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
