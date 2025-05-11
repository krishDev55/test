package com.example.demo.entity.vo;

import java.util.List;

public class UserAndLoginDetailsCombine {
	
		private long mobile;
	
	private String firstName;
	private String lastName;
	private String email;
	
	private String password;
	private Double bonus;
	private List<Long> refer;
	private double totayEarn;
	private double yesterdayEarn;
	private double orderCount;
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public List<Long> getRefer() {
		return refer;
	}
	public void setRefer(List<Long> refer) {
		this.refer = refer;
	}
	public double getTotayEarn() {
		return totayEarn;
	}
	public void setTotayEarn(double totayEarn) {
		this.totayEarn = totayEarn;
	}
	public double getYesterdayEarn() {
		return yesterdayEarn;
	}
	public void setYesterdayEarn(double yesterdayEarn) {
		this.yesterdayEarn = yesterdayEarn;
	}
	public double getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(double orderCount) {
		this.orderCount = orderCount;
	}
	@Override
	public String toString() {
		return "UserAndLoginDetailsCombine [mobile=" + mobile + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", bonus=" + bonus + ", refer=" + refer
				+ ", totayEarn=" + totayEarn + ", yesterdayEarn=" + yesterdayEarn + ", orderCount=" + orderCount + "]";
	}
	
	
	
	
	
	
}
