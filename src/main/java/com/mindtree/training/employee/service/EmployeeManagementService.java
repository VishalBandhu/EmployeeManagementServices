package com.mindtree.training.employee.service;

import java.util.List;

import com.mindtree.training.employee.model.Employee;

public interface EmployeeManagementService {
	public boolean addEmployee(Employee emp);

	public List<Employee> getAllEmployee();

	public Employee getEmployeeById(Long empId);

	public boolean deleteEmployeeById(Long empId);

	public boolean checkEmployeeLogin(String username, String password);
}
