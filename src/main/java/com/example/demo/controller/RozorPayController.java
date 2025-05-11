package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.RozorPay.Rozor_PaymentService;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Transfer;

@RestController
@RequestMapping("/v1/rozorPay")
public class RozorPayController {
	
	@Autowired private  Rozor_PaymentService paymentService;
//	private final static String  KEY="rzp_test_HKhIE2kfoLbD8L";
//	private final static String 	SECRET="9FnBhBsO6eDmvEa7KrHQO69h";
	
//	private final static String  KEY="rzp_test_FkK1wk6t9QLKsw";
//	private final static String 	SECRET="6Q5ovzKDb2RVYo6IhjHkZanK";
			
	
	

	@GetMapping("/getPayments")
	   public List<Map<String, Object>> getPaymentByUserId() throws RazorpayException{
	   return paymentService.getPayments();
	   }
	
	@GetMapping("/getPaymentsByEmail/{email}")
	public List<Map<String, Object>> getPaymentsByEmail(@PathVariable String email) 
											throws RazorpayException{
		return paymentService.getPaymentsByEmail(email);
	}
	
	
	
	@GetMapping("/getPaymentsByMobile/{mobile}")
	   public List<Map<String, Object>> getPaymentsByMobile(@PathVariable String mobile)
			   										throws RazorpayException{
		return paymentService.megetPaymentsByMobile(mobile);
	   }

	 
	 
	 
	 

}
