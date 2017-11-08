package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class EmployeeDTO implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private String email;


    public EmployeeDTO() {
    }

    protected EmployeeDTO(EmployeeDTO user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public EmployeeDTO(String name, String password, String email, boolean isEnabled, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}