package com.simple.model;

import java.io.Serializable;

public class CourseRanking implements Serializable{

	private static final long serialVersionUID = 1L;
	private String courseCode;
	private String courseName;
	private int watchCount;
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public int getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(int watchCount) {
		this.watchCount = watchCount;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
