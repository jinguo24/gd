package com.simple.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WxMemberHomeWork implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int homeworkId;
	private String homeworkName="";
	private String schoolId="";
	private String tanentName="";
	private String studentNo="";
	private String studentName="";
	private String signId="";
	private BigDecimal totalScore= new BigDecimal(0);
	private BigDecimal score= new BigDecimal(0);
	private Date createTime;
	private String content="";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTanentName() {
		return tanentName;
	}
	public void setTanentName(String tanentName) {
		this.tanentName = tanentName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public BigDecimal getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
	}
}
