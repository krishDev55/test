package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.globleHandler.UserHander;
import com.example.demo.repository.UserDao;

@Service
public class UserService {
		@Autowired
		private UserDao userDao;
		
		public Users saveUser(Users user) {
			return userDao.save(user);
		}
		
		@Cacheable(value = "Users" , key = "#id")
		public Optional<Users> getUserById(long id) throws UserHander {
			Optional<Users> byId = userDao.findById(id);
			if(byId.isEmpty()) {
				throw  new UserHander( id + " : this id Not Present in database");
			}
			return userDao.findById(id);
		}
		
		@Cacheable(value = "Users" )
		public List<Users> getAllUsers() {
			return userDao.findAll();
		}
		
		@CachePut(value = "Users", key = "#user.id")
		public Users updateUser(Users user) {
			return userDao.saveAndFlush(user);
		}
	
		
		public void userRefer(Long mobile) {
			
		}
		
		@Cacheable(value = "Users" , key = "#mobile")
		public Users findUserByMobile(Long mobile) {
			
			List<Users> list= userDao.findByMobile(mobile);
			for(Users user: list) {
				if(user.getMobile()==mobile) {
					return user;
				}
			}
			return null;
		}
}
