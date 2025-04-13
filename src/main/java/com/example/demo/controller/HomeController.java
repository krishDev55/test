package com.example.demo.controller;


import org.json.JSONObject;
import com.razorpay.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.GenrateOTP;
import com.example.demo.common.Gmail;
import com.example.demo.entity.ULoginDetails;
import com.example.demo.entity.UserBankDetails;
import com.example.demo.entity.Users;
import com.example.demo.entity.vo.Products;
import com.example.demo.globleHandler.UserHander;
import com.example.demo.service.ProductService;
import com.example.demo.service.ULoginDetailsService;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/v1/app")
@CrossOrigin()
public class HomeController {
	
	BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
	
	@GetMapping("/")
	public String home(HttpServletRequest req) {
		return "This is Home Page ...!!! "+req.getSession().getId();
	}
	

		@Autowired
		ULoginDetailsService details;
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private ProductService productService;
	
		public static  boolean invaite;
		
//		public static ULoginDetails loginDatailsById;
//		public static ULoginDetails currentUser =null;
		@Autowired
		static public HttpSession session;
		
		public static  String  OTP=null;
		
		@GetMapping("/test")
		public String test() {
			System.out.println("...........This the Testing Api page from ................. ");
			return "Testing API page.... ";
		}
		
	@PostMapping("/register")
	public ResponseEntity<Object>  register(@RequestBody ULoginDetails udetails) throws UserHander {
			udetails.setPassword(encoder.encode(udetails.getPassword()));
		ULoginDetails newRegister = details.newRegister(udetails);
		
		return  ResponseEntity.ok(newRegister);
				
	}
	@GetMapping("/invait/{mobile}/xyz")
	public ResponseEntity<Object> invaitUser(@PathVariable Long mobile){
		System.out.println(mobile);
		
//		invaite=true;
//		 loginDatailsById = details.getLoginDatailsById(mobile).get();					
		return  ResponseEntity.ok(" invait link Open");
	}
	
	
	@PostMapping("/login")
	
	public ResponseEntity<Object>  login(@RequestBody ULoginDetails udetails,HttpSession session1) throws UserHander {
		session = session1;
		String username = Long.toString(udetails.getMobile());

		if (ckeckSession(username)) {
			return ResponseEntity.ok("User is Allready Login");
		}
			else {
					String varify = details.varify(udetails);
			
					if (varify.equals("fails")) {
						return ResponseEntity.ok("Pleaase check the valid user Id ");
					}
			
					Map<String, Object> map = new HashMap<>();
					String mobile = Long.toString(udetails.getMobile());
					
					String id = session.getId();
					System.out.println("session id : " + id);
					session.setAttribute(mobile, udetails);
					map.put("token", varify);
					map.put("user", udetails);
					return ResponseEntity.ok(map);
			}				
	}
	
	@GetMapping("/logOut/{mobile}")
	public ResponseEntity<Object>  logOut(@PathVariable Long mobile) throws UserHander {
		Map<String, Object> map= new HashMap<>();
		System.out.println("inside log out method ");
			String mo1=Long.toString(mobile);
	
			if(ckeckSession(mo1)) {
			session.removeAttribute(mo1);
			map.put("msg", "user logout successfully");
			
			System.out.println("user logout successfully");
			return ResponseEntity.ok(map);
		}
			throw new UserHander("please logIn First");
		
				
	}

	@PostMapping("/addBankDetails")
	public ResponseEntity<Object>  saveDetails(@RequestBody UserBankDetails bankDetails)  {
		System.out.println(bankDetails);
		UserBankDetails saveBDetails = details.saveBankDetails(bankDetails);
		return ResponseEntity.ok(saveBDetails);
				
	}
	
	
	@GetMapping("/getbankDByMobile/{mobile}")
	public ResponseEntity<Object>  getBankDetailsByMobile(@PathVariable Long mobile) throws UserHander  {
		System.out.println(mobile);
		UserBankDetails saveBDetails = details.findBandDetailsByMobile(mobile);
		return ResponseEntity.ok(saveBDetails);
				
	}
	
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<Object> updateUserById(ULoginDetails user){
		
		ULoginDetails up =details.updateLoginUser(user);
	
		return ResponseEntity.ok(up);
		
	}
	
	@GetMapping("/getLoginUser/{mobile}")
	public ResponseEntity<Object> getLoginUserById(@PathVariable Long mobile){
		
		System.out.println("thsi number is :"+ mobile);
		Optional<ULoginDetails> up =details.getLoginDatailsById(mobile);
	
		return ResponseEntity.ok(up);
		
	}
		
	
	
	// user SendOtp varify 
	@GetMapping("/SendOtp/{email}")
	public ResponseEntity<Map<String, String>> sendOtp(@PathVariable String email) {
		Map<String, String> map= new HashMap<>();
		
		OTP = GenrateOTP.otp();
		
		Gmail.send(email, "user Text", OTP);
		System.out.println("OTP is : "+OTP);
		map.put("massage", "Sent Otp" + email + "Your Email Address");
		map.put("otp", OTP);
		
		return ResponseEntity.ok(map);
		
	}
	@GetMapping("/varifyOtp/{otp}")
	public ResponseEntity<Map<String,String>> otpVarifiy(@PathVariable String otp) throws UserHander {
		System.out.println("Varify Otp method callllllll :"+otp);
		Map<String, String> map= new HashMap<>();
		try {
			
			if(otp.equals(OTP)) {
			    OTP=null;
			    map.put("ok", "OTP varify seccessfully");
				return ResponseEntity.ok(map);
			}
			else { 
				map.put("error", "Wrong OTP ... Please Enter Valid OTP");
				return ResponseEntity.badRequest().body(map);
			}
			
		}
		catch (Exception e) {
			
			throw new  UserHander(" Null Pointer Exception....!!!!!");
		}	
	}

		
	@PutMapping("/updateBonus/{mobile}/{bonus}/{todayEarn}/{orderCount}")
	public  ULoginDetails updateLoginUserByBonus(@PathVariable Long mobile,
												@PathVariable double bonus,
												@PathVariable double todayEarn,
												@PathVariable int orderCount) {
		
		
		System.out.println("mobile is : "+mobile);
		System.out.println("bonus is : "+bonus);
		return details.updateLoginUserByBonus(mobile, bonus,todayEarn,orderCount);
	}
	
	
	@PutMapping("/RefreshIncome")
	public ResponseEntity<String> refreshIncome() {
			details.incomeExchange();
			return ResponseEntity.ok("All Data Are Refresh....!");
	}
	
	@GetMapping("/getProduct")
	public ResponseEntity<Map<String, Products>> getProduct(){
		
		String id = session.getId();
		System.out.println("session id : "+id);
		Products product = productService.getProduct();
		Map<String, Products> map= new HashMap<>();
		map.put("products", product);
		
	return	ResponseEntity.ok(map);
	}
	
	
	
	@PostMapping("/create_order")
	public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
	
		System.out.println("Data is : "+data);
		int amt=Integer.parseInt(data.get("amount").toString());
		
		var client=new RazorpayClient("rzp_test_HKhIE2kfoLbD8L", "9FnBhBsO6eDmvEa7KrHQO69h");
		
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",amt*100);
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "txn_112121");
		
		// create a order 
		Order order = client.orders.create(orderRequest);
		
		System.out.println("Order is create : "+order);
		
		JSONObject notes = new JSONObject();
		
		notes.put("notes_key_1","Tea, Earl Grey, Hot");
		orderRequest.put("notes",notes);
		
		return order.toString();
	}
	
	
	
	
	public boolean ckeckSession(String mobile) {
//		  ULoginDetails
		ULoginDetails attribute = (ULoginDetails)session.getAttribute(mobile);
		if(attribute!=null) {
			return true;
		}
		return false;
			
		}
}





