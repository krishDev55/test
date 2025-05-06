package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.controller.HomeController;
import com.example.demo.entity.ULoginDetails;
import com.example.demo.entity.UserBankDetails;
import com.example.demo.entity.Users;
import com.example.demo.globleHandler.UserHander;
import com.example.demo.repository.ULoginDetailsDao;
import com.example.demo.repository.UserBankDetailsDao;



@Service
public class ULoginDetailsService {
	
	@Autowired
	private ULoginDetailsDao uLoginDao;
	
	@Autowired
	private UserBankDetailsDao bankDetailDao;
	
	@Autowired AuthenticationManager authManager;
	@Autowired private UserService userService;
//	
	@Autowired
	private JWTService jwtService;
	
		public ULoginDetails newRegister(ULoginDetails details,String email) throws UserHander {
			
			Optional<ULoginDetails> uLogin=null;
			ULoginDetails save=null;
			
					try{ 
						uLogin=	getLoginDatailsById(details.getMobile());
						System.out.println(uLogin);
					}
					catch (NoSuchElementException e) {
						throw new NoSuchElementException("No value noValue present");
					}
					
					
			if(uLogin.isEmpty()) {
				details.setBonus(50.00);
				if(HomeController.invaite) {
//					Double bonus = HomeController.loginDatailsById.getBonus();
//					HomeController.loginDatailsById.setBonus(bonus+30);
					save = uLoginDao.save(details);
					System.out.println("new user register");
					
//					HomeController.loginDatailsById.setRefer(
//										new ArrayList<Long>(
//														Arrays.asList(
//																details.getMobile()
//												)));

//					uLoginDao.save(HomeController.loginDatailsById);
					
//					HomeController.invaite=false;
				}
				else{
					save = uLoginDao.save(details);
					UserBankDetails bank = new UserBankDetails();
					bank.setMobile(details.getMobile());
							saveBankDetails(bank);
						Users user= new Users();
						user.setMobile(details.getMobile());
						user.setEmail(email);
						System.out.println("Email is : "+email);
							userService.saveUser(user);	
							
					}
			}
			else {
				throw new UserHander("this mobileNo Are present");
			}
			return save;
		}
		
		
		
		public Optional<ULoginDetails> getLoginDatailsById(Long mobile) {
		
			return uLoginDao.findById(mobile);
		}

		
		
		public ULoginDetails loginChack(ULoginDetails udetails) throws UserHander {
			 Optional<ULoginDetails> ulTest = getLoginDatailsById(udetails.getMobile());
				if(ulTest.isEmpty()) {
					throw new UserHander("this mobile Not present");
				}
				else if(ulTest.get().getPassword().equals(udetails.getPassword())) {
					
					return ulTest.get();
				}
				else {
					
					throw new UserHander("Wrong Password");
				}
			
			
		}

		
		public UserBankDetails saveBankDetails(UserBankDetails bankDetails) {
			
			UserBankDetails bankDetail = bankDetailDao.saveAndFlush(bankDetails);
			System.out.println("After Bank Details save");
			return bankDetail;
		}
		
		public List<UserBankDetails> findByMobile(Long mobile) {
			List<UserBankDetails> bankDetail = bankDetailDao.findByMobile(mobile);
			return bankDetail;
		}
		
		

		public ULoginDetails updateLoginUser(ULoginDetails user) {	
			return uLoginDao.save(user);
		}
		
		
		/*		This Method is Updata the Balence Of daily task...
		 * 
		 * */
		public ULoginDetails updateLoginUserByBonus(
										Long mobile,double bonus,
										double totalEarn,int orderCount) {	
			
			ULoginDetails uLoginDetails = getLoginDatailsById(mobile).get();
			
			uLoginDetails.setBonus(bonus);
			uLoginDetails.setTotayEarn(totalEarn);
			uLoginDetails.setOrderCount(orderCount);
			
			System.out.println("Login : "+uLoginDetails);
			return uLoginDao.save(uLoginDetails);
		}

		
		public UserBankDetails findBandDetailsByMobile(Long mobile) throws UserHander {
			List<UserBankDetails> byMobile = bankDetailDao.findByMobile(mobile);
			if(byMobile.size()==0) {
				throw new UserHander("this user not Add a bank Dtails");
			}
			return byMobile.getFirst();
		}
		
		
		
		
		
		public void incomeExchange() {
			List<ULoginDetails> all = uLoginDao.findAll();
			Iterator<ULoginDetails> itr = all.iterator();
			
			while(itr.hasNext()) {
				ULoginDetails next = itr.next();
				next.setYesterdayEarn(next.getTotayEarn());
				next.setTotayEarn(0);
				next.setOrderCount(0);
				
				 uLoginDao.save(next);
				
			}
			
			System.out.println("All Data Are Refress");
		}



		public String varify(ULoginDetails udetails) {
			Authentication authenticate = 
					authManager.authenticate(
								new UsernamePasswordAuthenticationToken(udetails.getMobile(),udetails.getPassword()));			
						;
	if(authenticate.isAuthenticated()) {
		String string = Long.toString(udetails.getMobile());
		
		return jwtService.generateToken(string);
	}		
	
	return "fails";
//			
//			
		}
		
		
		
		
		
}
