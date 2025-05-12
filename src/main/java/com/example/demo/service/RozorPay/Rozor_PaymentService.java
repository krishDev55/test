package com.example.demo.service.RozorPay;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class Rozor_PaymentService {
	@Value("${rozorPayUsername}") public    String  KEY;
	@Value("${rozorPayPassword}") private   String SECRET;
	
	@Autowired private RestTemplate restTemplate;
	@Autowired private HttpHeaders headers;
	private final String url="https://api.razorpay.com/v1/payments/";
	
	 
	@SuppressWarnings("unused")
	public  List<Map<String, Object>> getPayments() throws RazorpayException{
		 headers.setBasicAuth(KEY, SECRET);
		 HttpEntity<String> entity= new HttpEntity<>(headers);	
		 ResponseEntity<Map> ex = restTemplate.exchange(url,
											HttpMethod.GET,
										    entity, 
										    Map.class);
		
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = ((List<Map<String, Object>>) ex.getBody()
													.get("items"))
													.stream()
													.collect(Collectors.toList());
			return list;
	   }


	public List<Map<String, Object>> getPaymentsByEmail(String email) {
		 headers.setBasicAuth(KEY, SECRET);
		 HttpEntity<String> entity= new HttpEntity<>(headers);	
		 ResponseEntity<Map> ex = restTemplate.exchange(url,
											HttpMethod.GET,
										    entity, 
										    Map.class);
		
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = ((List<Map<String, Object>>) ex.getBody()
													.get("items"))
													.stream()
													.filter(e->((String)(e.get("email"))).equalsIgnoreCase(email))
													.collect(Collectors.toList());
			return list;
		
	}


	public List<Map<String, Object>> megetPaymentsByMobile(String mobile) {
		 headers.setBasicAuth(KEY, SECRET);
		 HttpEntity<String> entity= new HttpEntity<>(headers);	
		 ResponseEntity<Map> ex = restTemplate.exchange(url,
											HttpMethod.GET,
										    entity, 
										    Map.class);
		
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = ((List<Map<String, Object>>) ex.getBody()
													.get("items"))
													.stream()
													.filter(e->((String)(e.get("contact"))).equalsIgnoreCase(mobile))
													.collect(Collectors.toList());
			return list;
	}
	
}
