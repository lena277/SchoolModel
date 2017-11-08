package com.example.demo.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(Integer id) {
        super("Could not find Employee " + id);
    }
    
    public EmployeeNotFoundException(String msg) {
        super(msg);
    }
    
    
    public EmployeeNotFoundException() {
        super("Could not find Employee ");
    }
}