package com.simple.model;

import java.io.Serializable;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class GdCardMake implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String cardNo="";
	private int homeworkId;
	private String name="";
	private String sex="";
	private String sequenceNo="";
	private String cardImage="";
	private String type="";
	private Date makeTime;
	private String showMakeTime="";
	private int makeCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public Date getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
		if ( null != makeTime) {
			this.showMakeTime = DateUtil.date2AllString(makeTime);
		}
	}
	public int getMakeCount() {
		return makeCount;
	}
	public void setMakeCount(int makeCount) {
		this.makeCount = makeCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShowMakeTime() {
		return showMakeTime;
	}
	public String getCardImage() {
		return cardImage;
	}
	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
}
