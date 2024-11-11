package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Users;
import com.example.demo.globleHandler.UserHander;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin()

@RequestMapping("/v1/user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Users>> getAllUsers() {
		
		return ResponseEntity.ok(userService.getAllUsers());
	}
	@PostMapping("/saveUser")
	public ResponseEntity<Users> saveUser(@RequestBody Users user) {
		
		return ResponseEntity.ok(userService.saveUser(user));
	}
	
	@GetMapping("/getuser/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable int id) throws UserHander {
		Optional<Users> userById = userService.getUserById(id);
		
		 return ResponseEntity.ok(userById);
	}
	
	@GetMapping("/getUserByMobile/{mobile}")
	public ResponseEntity<Object> getUserByMobileNo(@PathVariable Long mobile) throws UserHander {
	Users users = userService.findUserByMobile(mobile);
		
		 return ResponseEntity.ok(users);
	}
	
	
	
	@PutMapping("/updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody Users user){
		
		Users updateUser=userService.updateUser(user);
		return ResponseEntity.ok(updateUser);
		
	}
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<Object> updateUserById(@PathVariable int id, @RequestBody Users user){
		
		Users updateUser=null;
		try {
			updateUser = userService.getUserById(id).get();
			if(user.getFirstName()!=null) {updateUser.setFirstName(user.getFirstName());} ;
			if(user.getEmail()!=null) {updateUser.setEmail(user.getEmail());} ;  
			if(user.getLastName()!=null) {updateUser.setLastName(user.getLastName());} ;  
			if(user.getMobile()!=0) {updateUser.setMobile(user.getMobile());} ;  
			
		} catch (UserHander e) {
			e.setName("nullValue");
			
		}
		
		Users up=userService.updateUser(updateUser);
		return ResponseEntity.ok(up);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
