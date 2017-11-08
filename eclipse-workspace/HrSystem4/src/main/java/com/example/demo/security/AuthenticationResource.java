package com.example.demo.security;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;

@RestController
@RequestMapping("/api/session")
public class AuthenticationResource {
    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET,value="/user")
    public String session(Principal user) {
        String name = user == null ? null : user.getName();
        return user.getName();
    }

    @RequestMapping(method = RequestMethod.GET,value="/delete")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}