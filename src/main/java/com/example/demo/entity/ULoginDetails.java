package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ULoginDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	
	private Long mobile;
	private String password;
	private Double bonus;

	
	private List<Long> refer;
	
	private double totayEarn;
	private double yesterdayEarn;
	private double orderCount;
	
	
	
	
	
	
	
	public double getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(double orderCount) {
		this.orderCount = orderCount;
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
	public List<Long> getRefer() {
		return refer;
	}
	public void setRefer(List<Long> refer) {
		this.refer = refer;
	}
	
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "ULoginDetails [mobile=" + mobile + ", password=" + password + ", bonus=" + bonus + ", refer=" + refer
				+ ", totayEarn=" + totayEarn + ", yesterdayEarn=" + yesterdayEarn + "]";
	}
	
	
	
	

}
