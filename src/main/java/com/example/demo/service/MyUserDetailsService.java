package com.example.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginDetailsPrincipal;
import com.example.demo.entity.ULoginDetails;
import com.example.demo.entity.UserBankDetails;
import com.example.demo.repository.ULoginDetailsDao;



@Service
public class MyUserDetailsService  implements UserDetailsService 
							{
/**
	 * 
	 */
	
	//
//	@Autowired
//	ULoginDetailsService loginDetails;
//	
	@Autowired
	private ULoginDetailsDao uLoginDao;
//	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Long valueOf = Long.valueOf(username);
		ULoginDetails uLoginDetails = uLoginDao.findById(valueOf).get();
		
	if(uLoginDetails ==null) {
		System.out.println("User not Found");
		throw new UsernameNotFoundException("User Not Found...!!!!");
	}
		return new LoginDetailsPrincipal(uLoginDetails);
	}

	
}
