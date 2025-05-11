package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ULoginDetails;
import com.example.demo.entity.Users;
import com.example.demo.entity.vo.UserAndLoginDetailsCombine;
import com.example.demo.globleHandler.UserHander;
import com.example.demo.repository.ULoginDetailsDao;
import com.example.demo.repository.UserDao;

@Service
public class UserService {
		@Autowired private UserDao userDao;
		@Autowired private ULoginDetailsDao uloginDetalsDao;
		
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
		
		@CacheEvict(value = "Users", key = "#user.mobile")
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

		
		
		
	public UserAndLoginDetailsCombine getCombineUserOrLoginUserData(Long mobile) {
			Users user = findUserByMobile(mobile);
			Optional<ULoginDetails> loginDatailsById = uloginDetalsDao.findById(mobile);
			if(loginDatailsById!=null) {
				ULoginDetails uLoginDetails = loginDatailsById.get();
				UserAndLoginDetailsCombine ud= new UserAndLoginDetailsCombine();
				ud.setMobile(user.getMobile());
				ud.setFirstName(user.getFirstName());
				ud.setLastName(user.getLastName());
				ud.setEmail(user.getEmail());
				ud.setTotayEarn(uLoginDetails.getTotayEarn());
				ud.setYesterdayEarn(uLoginDetails.getYesterdayEarn());
				ud.setBonus(uLoginDetails.getBonus());
				ud.setRefer(uLoginDetails.getRefer());
				return ud;
			}
			return null;
		}
}
