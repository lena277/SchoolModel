package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Employee;

@Component
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
     
	 List <Employee> findByName(String name);
	 List <Employee> findAll();
	 Employee findByEmail(String email);
	Employee findByPrincipalId(String principalId);
	Employee findByEmailIgnoreCase(String email);
	 
	 
}
