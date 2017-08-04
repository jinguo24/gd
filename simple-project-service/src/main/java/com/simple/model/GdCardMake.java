package com.simple.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class GdCardMake implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String tanentId="";
	private String cardNo="";
	private String name="";
	private String sex="";
	private String sequenceNo="";
	private String cardImage="";
	private Date makeTime;
	private String showMakeTime="";
	private int makeCount;
	private BigDecimal totalScore = new BigDecimal(0);
	private BigDecimal score = new BigDecimal(0);
	private String homeWorkTime="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	public String getShowMakeTime() {
		return showMakeTime;
	}
	public String getCardImage() {
		return cardImage;
	}
	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
	public BigDecimal getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public String getHomeWorkTime() {
		return homeWorkTime;
	}
	public void setHomeWorkTime(String homeWorkTime) {
		this.homeWorkTime = homeWorkTime;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
}
