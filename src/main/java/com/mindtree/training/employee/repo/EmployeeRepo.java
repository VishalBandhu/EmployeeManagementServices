package com.mindtree.training.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.training.employee.entity.EmployeeEntity;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
	EmployeeEntity findEmployeeEntityByUsername(String username);
}
