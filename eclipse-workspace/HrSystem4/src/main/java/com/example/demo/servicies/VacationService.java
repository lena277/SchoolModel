package com.example.demo.servicies;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Vacation;
import com.example.demo.entities.SickVacation;
import com.example.demo.entities.PersonalVacation;
import com.example.demo.repositories.VacationRepository;

@Service
public class VacationService {

	@Autowired
	private VacationRepository vacationRepository;

	public Vacation create(Vacation type) {

		return vacationRepository.save(type);

	}

	public List<Vacation> findAll() {
		return vacationRepository.findAll();

	}

	public void deleteById(Integer id) {
		vacationRepository.delete(id);

	}

	public Vacation findById(Integer id) {
		return vacationRepository.findOne(id);
	}

	public boolean isExis(Vacation vacation) {
		return vacationRepository.exists(vacation.getId());
	}

	public void deleteAll() {
		vacationRepository.deleteAll();
	}

	public Vacation updateById(Vacation vacation) {

		return vacationRepository.save(vacation);

	}

	public List<Vacation> findByType(String type) {
		return vacationRepository.findByVacationType(type);
	}

	public List<Vacation> getByStatus(boolean isValidate) {
		return vacationRepository.findByStatus(isValidate);
	}
}