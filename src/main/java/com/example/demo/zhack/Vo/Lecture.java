package com.example.demo.zhack.Vo;

public class Lecture {

	private String _id;
	private String video;
	private String course;
	private String createdAt;
	private int  __v;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
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
	@Override
	public String toString() {
		return "Lecture [_id=" + _id + ", video=" + video + ", course=" + course + ", createdAt=" + createdAt + ", __v="
				+ __v + "]\n";
	}
	
	
}
