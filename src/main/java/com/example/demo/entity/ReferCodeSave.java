package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReferCodeSave {
	
	@Id private Long mobile;
		private String referCode;
		
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}
		public String getReferCode() {
			return referCode;
		}
		public void setReferCode(String referCode) {
			this.referCode = referCode;
		}
		
		public ReferCodeSave(Long mobile, String referCode) {
			super();
			this.mobile = mobile;
			this.referCode = referCode;
		}
		
		public ReferCodeSave() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
	
}
