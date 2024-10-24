package com.example.demo.globleHandler;

public class UserHander extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;

//			
			
	public UserHander(String name) {
		super(name);
		
	}
	
	  public String getName() {
		  return name; 
	  }
	 

	public void setName(String name) {
		this.name = name;
	}
	
	  
}
