package com.example.demo.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name = "vacation")
@DiscriminatorColumn(name = "vacation_type")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use =JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "vacationType")
@JsonSubTypes({
    @Type(name = "type1", value = VacationType1.class),
    @Type(name = "type2", value = VacationType2.class)
})
@Proxy(lazy=false) 
public  abstract class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	
	   @ManyToOne(cascade = CascadeType.ALL)
	    @JsonIgnore

	    Employee employee;
	   
	@Column(name = "vacation_type", insertable = false, updatable = false)
	
	private String vacationType;
	public String getVacationType() {
		return vacationType;
	}
	
	private Integer longOfVacation;



	
	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public int getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getLongOfVacation() {
		return longOfVacation;
	}

	public void setLongOfVacation(Integer longOfVacation) {
		this.longOfVacation = longOfVacation;
	}

}