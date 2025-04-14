package com.example.demo.zhack.Vo;

import java.util.List;

public class User {
	
		private String _id;
		private String name;
		private String email;
		private String password;
		private String profileImage;
		private String role;
		private List<String> purchasedCourses;
		private String referralLink;
		private String referrer;
		private String contact;
		private String createdAt;
		private int __v;
		
		private Earnings earnings;

		public String get_id() {
			return _id;
		}

		public void set_id(String _id) {
			this._id = _id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public String getProfileImage() {
			return profileImage;
		}

		public void setProfileImage(String profileImage) {
			this.profileImage = profileImage;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public List<String> getPurchasedCourses() {
			return purchasedCourses;
		}

		public void setPurchasedCourses(List<String> purchasedCourses) {
			this.purchasedCourses = purchasedCourses;
		}

		public String getReferralLink() {
			return referralLink;
		}

		public void setReferralLink(String referralLink) {
			this.referralLink = referralLink;
		}

		public String getReferrer() {
			return referrer;
		}

		public void setReferrer(String referrer) {
			this.referrer = referrer;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}

		public int get__v() {
			return __v;
		}

		public void set__v(int __v) {
			this.__v = __v;
		}

		public Earnings getEarnings() {
			return earnings;
		}

		public void setEarnings(Earnings earnings) {
			this.earnings = earnings;
		}

		@Override
		public String toString() {
			return "User [_id=" + _id + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", profileImage=" + profileImage + ", role=" + role + ", purchasedCourses=" + purchasedCourses
					+ ", referralLink=" + referralLink + ", referrer=" + referrer + ", contact=" + contact
					+ ", createdAt=" + createdAt + ", __v=" + __v + ", earnings=" + earnings + "]";
		}
		
		
		
		
}




class Earnings{
	private double total;
	private double today;
	private double week;
	private double month;
	private String lastUpdated;
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getToday() {
		return today;
	}
	public void setToday(double today) {
		this.today = today;
	}
	public double getWeek() {
		return week;
	}
	public void setWeek(double week) {
		this.week = week;
	}
	public double getMonth() {
		return month;
	}
	public void setMonth(double month) {
		this.month = month;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	@Override
	public String toString() {
		return "Earnings [total=" + total + ", today=" + today + ", week=" + week + ", month=" + month
				+ ", lastUpdated=" + lastUpdated + "]";
	}
	
	
	
}
