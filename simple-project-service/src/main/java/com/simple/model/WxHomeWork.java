package com.simple.model;

import java.io.Serializable;
import java.util.Date;
public class WxHomeWork implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String schoolId;
	private String title;
	private Date publishTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
