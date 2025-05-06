package com.example.demo.controller;


import org.json.JSONObject;
import com.razorpay.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.GenrateOTP;
import com.example.demo.common.Gmail;
import com.example.demo.entity.ULoginDetails;
import com.example.demo.entity.UserBankDetails;
import com.example.demo.entity.Users;
import com.example.demo.entity.vo.Products;
import com.example.demo.globleHandler.UserHander;
import com.example.demo.service.InviteCodeService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ULoginDetailsService;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/v1/app")
@CrossOrigin()
public class HomeController {
	 @Autowired private InviteCodeService inviteCodeService;

	BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
	
	@GetMapping("/")
	public String home(HttpServletRequest req) {
		return "This is Home Page ...!!! "+req.getSession().getId();
	}
	
	 @GetMapping("/generate")
	    public String generateInvite(@RequestParam String mobile) {
	        String code = inviteCodeService.generateInviteCode(mobile);
	   	 System.out.println("code is : "+code);
	        return "http://localhost:8081/v1/invite/register?invite=" + code;
	    }

		@Autowired ULoginDetailsService details;
		
		@Autowired private UserService userService;
		
		@Autowired private ProductService productService;
	
		public static  boolean invaite;
		
//		public static ULoginDetails loginDatailsById;
//		public static ULoginDetails currentUser =null;

		public static  String  OTP=null;
		
		@GetMapping("/test")
		public String test() {
			System.out.println("...........This the Testing Api page from ................. ");
			return "Testing API page.... ";
		}
		
	@PostMapping("/register")
	public ResponseEntity<Object>  register(@RequestBody ULoginDetails udetails
					,@RequestHeader String email	) throws UserHander {
			udetails.setPassword(encoder.encode(udetails.getPassword()));
		   ULoginDetails newRegister = details.newRegister(udetails,email);
		return  ResponseEntity.ok(newRegister);
	}
	
	@PostMapping("/registerRef")
	public ResponseEntity<Object>  register(@RequestBody ULoginDetails udetails
					,@RequestHeader String email,@RequestHeader String refCode) throws UserHander {
			udetails.setPassword(encoder.encode(udetails.getPassword()));
			Long key = inviteCodeService.getKey(refCode);
		ULoginDetails newRegister = details.newRegister(udetails,email);
		//referExtra Logic  
		ULoginDetails referUser = details.getLoginDatailsById(key).get();
		referUser.setBonus(referUser.getBonus()+30);
		List<Long> refer = referUser.getRefer();
		 if(refer==null) {
			 	refer=new ArrayList<>();
		 }
			refer.add(newRegister.getMobile());
		referUser.setRefer(refer);
		 details.updateLoginUser(referUser);
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
	public ResponseEntity<Map<String, Object>>  login(@RequestBody ULoginDetails udetails) throws UserHander {
		String username = Long.toString(udetails.getMobile());
				System.out.println("This si test");
				Map<String, Object> map = new HashMap<>();

					String token = details.varify(udetails);
			
					if (token.equals("fails")) {
						map.put("varify", false);
						return  new ResponseEntity<Map<String, Object>>(map, HttpStatusCode.valueOf(402));
					}
			
					ULoginDetails userDetails = details.getLoginDatailsById(udetails.getMobile()).get();
//					String mobile = Long.toString(udetails.getMobile());
					map.put("varify", true);
					map.put("token", token);
					map.put("user", userDetails);
					System.out.println("map is : "+map);
					return ResponseEntity.ok(map);
			
	}
	 
	@GetMapping("/logOut/{mobile}")
	public ResponseEntity<Object>  logOut(@PathVariable Long mobile) throws UserHander {
		Map<String, Object> map= new HashMap<>();
		System.out.println("inside log out method ");
			String mo1=Long.toString(mobile);
	
			map.put("msg", "user logout successfully");
			System.out.println("user logout successfully");
			return ResponseEntity.ok(map);
		
				
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
		Map<String, Object> map= new HashMap<>();
		UserBankDetails saveBDetails = details.findBandDetailsByMobile(mobile);
		System.out.println(saveBDetails);
		map.put("bnk", saveBDetails);
		map.put("varify", true);
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
	
	
}





