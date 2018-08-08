package com.mindtree.training.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.training.employee.entity.EmployeeEntity;
import com.mindtree.training.employee.model.Employee;
import com.mindtree.training.employee.repo.EmployeeRepo;
import com.mindtree.training.employee.service.EmployeeManagementService;

@Service
@CacheConfig
public class EmployeeManagementServiceImpl implements EmployeeManagementService {
	@Autowired
	private EmployeeRepo employeeRepo;

	@Transactional
	public boolean addEmployee(Employee emp) {
		boolean flag = false;
		EmployeeEntity entity = null;
		if (emp != null) {
			entity = convertModelIntoEntity(emp);
			employeeRepo.save(entity);
			flag = true;
		}
		return flag;
	}

	@Cacheable(value = "employee.all")
	public List<Employee> getAllEmployee() {
		List<Employee> empList = null;
		List<EmployeeEntity> emps = employeeRepo.findAll();
		if (!emps.isEmpty()) {
			empList = new ArrayList<>();
			for (EmployeeEntity entity : emps) {
				empList.add(convertEntityIntoModel(entity));
			}
		}
		return empList;
	}

	@Cacheable(value = "employee.byId")
	public Employee getEmployeeById(Long empId) {
		Optional<EmployeeEntity> emp = employeeRepo.findById(empId);
		if (emp.isPresent()) {
			return convertEntityIntoModel(emp.get());
		}
		return null;
	}

	@Transactional
	@CacheEvict(value = "employee.byId")
	public boolean deleteEmployeeById(Long empId) {
		try {
			employeeRepo.deleteById(empId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean checkEmployeeLogin(String username, String password) {

		if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
			EmployeeEntity entity = employeeRepo.findEmployeeEntityByUsername(username);
			if (entity != null && entity.getPassword() != null) {
				if (password.equals(entity.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}

	private EmployeeEntity convertModelIntoEntity(Employee employee) {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setUsername(employee.getUsername());
		entity.setPassword(employee.getPassword());
		entity.setFullName(employee.getFullName());
		entity.setGender(employee.getGender());
		entity.setEmailId(employee.getEmailId());
		entity.setDateOfBirth(employee.getDateOfBirth());
		entity.setSecurityQuestion(employee.getSecurityQuestions());
		entity.setSecurityAnswer(employee.getSecurityAnswer());
		return entity;
	}

	private Employee convertEntityIntoModel(EmployeeEntity employee) {
		Employee model = new Employee();
		model.setEmpId(employee.getEmpId());
		model.setUsername(employee.getUsername());
		model.setPassword(employee.getPassword());
		model.setFullName(employee.getFullName());
		model.setGender(employee.getGender());
		model.setEmailId(employee.getEmailId());
		model.setDateOfBirth(employee.getDateOfBirth());
		model.setSecurityQuestions(employee.getSecurityQuestion());
		model.setSecurityAnswer(employee.getSecurityAnswer());
		return model;
	}

}
