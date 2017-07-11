package com.simple.model.api;

public class KaiKeHeJi extends KaiKe{

	private static final long serialVersionUID = 1L;
	private String skbh;
	private String sksj;
	private String kcbh;
	private String teacher;
	private String teacherName;
	
	public String getSkbh() {
		return skbh;
	}
	public void setSkbh(String skbh) {
		this.skbh = skbh;
	}
	public String getKcbh() {
		return kcbh;
	}
	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}
	public String getSksj() {
		return sksj;
	}
	public void setSksj(String sksj) {
		this.sksj = sksj;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
