package com.example.demo.entity.vo;

public class Products {
		private int id;
		private String imgfileName;
		private String imgDescription;
		private double imgPrice;
		private double earnPrice;
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getImgfileName() {
			return imgfileName;
		}
		public void setImgfileName(String imgfileName) {
			this.imgfileName = imgfileName;
		}
		public String getImgDescription() {
			return imgDescription;
		}
		public void setImgDescription(String imgDescription) {
			this.imgDescription = imgDescription;
		}
		public double getImgPrice() {
			return imgPrice;
		}
		public void setImgPrice(double imgPrice) {
			this.imgPrice = imgPrice;
		}
		public double getEarnPrice() {
			return earnPrice;
		}
		public void setEarnPrice(double earnPrice) {
			this.earnPrice = earnPrice;
		}
		
		
		
		@Override
		public String toString() {
			return "Products [id=" + id + ", imgfileName=" + imgfileName + ", imgDescription=" + imgDescription
					+ ", imgPrice=" + imgPrice + ", earnPrice=" + earnPrice + "]";
		}
		
		
		public Products() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public Products(int id, String imgfileName, String imgDescription, double imgPrice, double earnPrice) {
			super();
			this.id = id;
			this.imgfileName = imgfileName;
			this.imgDescription = imgDescription;
			this.imgPrice = imgPrice;
			this.earnPrice = earnPrice;
		}
		
		
		
		
		
	
}
