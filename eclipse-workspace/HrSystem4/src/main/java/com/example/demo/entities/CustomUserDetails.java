package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails {
	//extends Employee implements UserDetails {
	
//	 public static final List<GrantedAuthority> DEFAULT_ROLES;
//
//	    static {
//	        DEFAULT_ROLES = new ArrayList<GrantedAuthority>(1);
//	        GrantedAuthority defaultRole = new SimpleGrantedAuthority("ROLE_USER");
//	        DEFAULT_ROLES.add(defaultRole);
//	    }
//
//	public CustomUserDetails(Employee em) {
//		super(em);
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//				.collect(Collectors.toList());
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return super.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return super.getEmail();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}

}
