package com.example.demo.common;

import java.util.Random;

public class GenrateOTP {
	
	

	public static String  otp() {
		Random random= new Random();
		
		int nextInt2 = random.nextInt(99999, 999999);
		
		
	
		return String.valueOf(nextInt2);
	}
}
