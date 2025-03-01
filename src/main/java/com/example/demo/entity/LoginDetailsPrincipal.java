package com.example.demo.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginDetailsPrincipal  implements UserDetails {
	
	ULoginDetails uloginDetails;
	
	

	public LoginDetailsPrincipal(ULoginDetails uloginDetails) {
		
		this.uloginDetails = uloginDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton( new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return uloginDetails.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return Long.toString(uloginDetails.getMobile());
	}

}
