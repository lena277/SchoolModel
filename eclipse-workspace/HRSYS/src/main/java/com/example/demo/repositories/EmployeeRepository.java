package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
     
	 List <Employee> findByName(String name);
//List<Employee> findByIsManger(boolean manger);
	 List <Employee> findAll();
	 
	 
}
