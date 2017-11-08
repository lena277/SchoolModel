package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Role;

@Component
public interface RoleRepository extends CrudRepository<Role, Integer> {

	List<Role> findByName(String name);

	List<Role> findAll();

}
