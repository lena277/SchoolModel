package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Role;
import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;

@Controller
@RestController
@RequestMapping(value = "/roles")
public class RoleController {

	@Autowired
	private RoleRepository repository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Role> findAll() {

		return repository.findAll();
	}

	@ResponseBody
	public ResponseEntity<Role> create(@RequestBody Role role) {

		repository.save(role);
		return new ResponseEntity<Role>(role, HttpStatus.CREATED);

	}

}
