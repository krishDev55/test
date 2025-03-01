package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginDetailsPrincipal;
import com.example.demo.entity.ULoginDetails;
import com.example.demo.repository.ULoginDetailsDao;



@Service
public class MyUserDetailsService  implements UserDetailsService{

//	@Autowired
//	ULoginDetailsService loginDetails;
	@Autowired
	private ULoginDetailsDao uLoginDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		ULoginDetails loginDatailsById = loginDetails.getLoginDatailsById(Long.valueOf(username)).get();
		ULoginDetails uLoginDetails = uLoginDao.findById(Long.valueOf(username)).get();
		
		return new LoginDetailsPrincipal(uLoginDetails);
	}

}
