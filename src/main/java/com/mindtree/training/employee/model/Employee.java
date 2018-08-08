package com.mindtree.training.employee.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class Employee {
	private Long empId;
	@NotEmpty(message = "Username must not be empty")
	@NotNull(message = "Username must not be null")
	@ApiModelProperty(notes = "Username, must be unique",required=true,dataType="string")
	private String username;
	@NotEmpty(message = "Password must not be empty")
	@NotNull(message = "Password must not be null")
	@ApiModelProperty(notes = "password",required=true,dataType="string")
	private String password;
	@NotEmpty(message = "Full Name must not be empty")
	@NotNull(message = "Full Name must not be null")
	@ApiModelProperty(notes = "user full name",required=true,dataType="string")
	private String fullName;
	@NotEmpty(message = "Email must not be empty")
	@NotNull(message = "Email must not be null")
	@ApiModelProperty(notes = "email address",required=true,dataType="email")
	private String emailId;
	@NotEmpty(message = "Date of Birth must not be empty")
	@NotNull(message = "Full Name must not be null")
	// @Pattern(regexp = "[0-3][0-9][-][0-1][0-9][-][7-9][0-9]", message = "invalid
	// date of Birth")
	@ApiModelProperty(notes = "date of birth in dd-mm-yy format",required=true,dataType="date")
	private String dateOfBirth;
	@NotEmpty(message = "Gender must not be empty")
	@NotNull(message = "Gender must not be empty")
	@ApiModelProperty(notes = "employee gender",required=true,dataType="Character")
	private String gender;
	@NotEmpty(message = "Security Question must not be empty")
	@NotNull(message = "Security Question must not be null")
	@ApiModelProperty(notes = "Security Question for account recovery",required=true,dataType="string")
	private String securityQuestions;
	@NotEmpty(message = "Security Answer must not be empty")
	@NotNull(message = "Security Answer must not be null")
	@ApiModelProperty(notes = "Security Answer for account recovery",required=true,dataType="string")
	private String securityAnswer;

	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(String securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

}
