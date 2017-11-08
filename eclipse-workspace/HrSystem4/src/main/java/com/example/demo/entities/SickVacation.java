package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("sick")
public class SickVacation extends Vacation {

	public SickVacation() {
	}

	public void inform() {

	}

}