package com.example.demo.exceptions;
public class NotFoundVacationApproval extends  RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundVacationApproval(Integer id) {
        super("Could not find VacationApproval " + id);
    }
    
    public NotFoundVacationApproval() {
        super("Could not find VacationApproval ");
    }
}