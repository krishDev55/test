package com.example.demo.globleHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobleHandler {
	
	@ExceptionHandler(value = NullPointerException.class)
	public void nullPointer( ) {
	
		
	}
	@ExceptionHandler(value = UserHander.class)
	public ResponseEntity<Map<String, Object>> nullPointer1(UserHander userHander) {
		
		
		Map<String, Object> map= new HashMap<>();
		 map.put("error", userHander.getMessage());
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
	}
	
	
	
	// to handle the  exception of already defined
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Map<String,Object>> 
						noResouceFoundExcpetion(NoResourceFoundException ex){
		
		Map<String, Object> map= new HashMap<>();
		 map.put("error", ex.getMessage());
		 map.put("resone", "url Not Found");
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
		
	}

	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<Map<String, Object>> 
				invalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {

		Map<String, Object> map = new HashMap<>();
		map.put("error", ex.getMessage());
		map.put("resone", "Id is Not Fount");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);

	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String, Object>> 
	noSuchElementException(NoSuchElementException ex) {

		Map<String, Object> map = new HashMap<>();
		map.put("error", ex.getMessage());
		map.put("resone", "No value Present");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);

	}
	
}
