package com.example.demo.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

//@Service
public class LoginDetailsPrincipal  implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
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
		return uloginDetails.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		String string = Long.toString(uloginDetails.getMobile());
		return string;
	}

}
