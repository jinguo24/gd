package com.simple.model;

import java.io.Serializable;
public class WxHomeWork implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String schoolId;
	private String title;
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
}
