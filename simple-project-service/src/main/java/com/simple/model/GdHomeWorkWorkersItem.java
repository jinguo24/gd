package com.simple.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GdHomeWorkWorkersItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String gdSignId;
	private String cardNo;
	private String name;
	private String itemJson;
	private int homeworkId;
	private String homeworkName;
	private Date signTime;
	private Date homeworkTime;
	private BigDecimal score;
	private Date createTime;
	private String tanentId;
	private String tanentName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGdSignId() {
		return gdSignId;
	}
	public void setGdSignId(String gdSignId) {
		this.gdSignId = gdSignId;
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
	public String getItemJson() {
		return itemJson;
	}
	public void setItemJson(String itemJson) {
		this.itemJson = itemJson;
	}
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public Date getHomeworkTime() {
		return homeworkTime;
	}
	public void setHomeworkTime(Date homeworkTime) {
		this.homeworkTime = homeworkTime;
	}
	public String getTanentName() {
		return tanentName;
	}
	public void setTanentName(String tanentName) {
		this.tanentName = tanentName;
	}
}
