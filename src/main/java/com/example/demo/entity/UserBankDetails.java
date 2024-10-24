package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserBankDetails {
	@Id
	private String accountNumber;
    private String accountHolderName;
    private String bankName;
    private String bankBranch;
    private String ifscCode;
    private String accountType;
//    @OneToOne
//    private ULoginDetails ulLoginDetails;
    
    private Long mobile;
    
    
    
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "UserBankDetails [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName
				+ ", bankName=" + bankName + ", bankBranch=" + bankBranch + ", ifscCode=" + ifscCode + ", accountType="
				+ accountType + ", mobile=" + mobile + "]";
	}
	
	
    
	
    
	
	
}
