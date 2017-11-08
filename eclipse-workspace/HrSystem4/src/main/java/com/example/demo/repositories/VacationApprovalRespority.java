package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entities.VacationApproval;

@Component
public interface VacationApprovalRespority extends CrudRepository<VacationApproval, Integer> {

	List<VacationApproval> findAll();


}
