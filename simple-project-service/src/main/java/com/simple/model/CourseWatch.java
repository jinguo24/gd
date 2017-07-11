package com.simple.model;

import java.io.Serializable;

public class CourseWatch implements Serializable{
	private static final long serialVersionUID = 1L;
	private int lineId;
	private String courseCode;
	private String tanentId;
	private int watchCount;
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	public int getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(int watchCount) {
		this.watchCount = watchCount;
	}
}
