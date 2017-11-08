package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Vacation;

@Component("vacationRepository")
public interface VacationRepository extends CrudRepository<Vacation, Integer> {
	List<Vacation> findAll();

	Vacation findOne(Integer id);

	List<Vacation> findByVacationType(String type);

	List<Vacation> findByStatus(boolean isValidate);
}
