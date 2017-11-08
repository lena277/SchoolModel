package com.example.demo.DTO;

import com.example.demo.entities.Employee;
import com.example.demo.entities.VerificationTokenEntity;
//import com.example.demo.security.VerificationToken;

public class DTOHelper {
    private DTOHelper(){}

    public static EmployeeDTO detach(Employee userEntity) {
    	EmployeeDTO user = new EmployeeDTO();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        return user;
    }
//
//    public static VerificationToken detach(VerificationTokenEntity verificationTokenEntity) {
//        VerificationToken verificationToken = new VerificationToken();
//        verificationToken.setToken(verificationTokenEntity.getToken());
//        verificationToken.setExpiryDate(verificationTokenEntity.getExpiryDate());
//        verificationToken.setVerified(verificationTokenEntity.isVerified());
//        verificationToken.setEmail(verificationTokenEntity.getUser().getEmail());
//        return verificationToken;
//    }
}