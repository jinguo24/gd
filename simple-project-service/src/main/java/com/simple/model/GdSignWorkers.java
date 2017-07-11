package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class GdSignWorkers implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String gdSignId;
	private String cardNo;
	private String cardImage;
	private Date createTime;
	private String name;
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
	public String getCardImage() {
		return cardImage;
	}
	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
