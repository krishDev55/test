package com.example.demo.zhack.Vo;

import java.util.List;

public class Course {
	private int _id;
	private  String courseId;
	private  String name;
	private  String image;
	private  String description;
	private int __v;
	private  int price;
	
	private List<String> lectures;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int get__v() {
		return __v;
	}

	public void set__v(int __v) {
		this.__v = __v;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<String> getLectures() {
		return lectures;
	}

	public void setLectures(List<String> lectures) {
		this.lectures = lectures;
	}

	@Override
	public String toString() {
		return "Course [_id=" + _id + ", courseId=" + courseId + ", name=" + name + ", image=" + image
				+ ", description=" + description + ", __v=" + __v + ", price=" + price + ", lectures=" + lectures + "]";
	}
	
	
	
	
	
}
