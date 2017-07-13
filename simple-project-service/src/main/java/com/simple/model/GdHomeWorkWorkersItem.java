package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class GdHomeWorkWorkersItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String gdSignId;
	private String cardNo;
	private String name;
	private String itemJson;
	private int homeworkId;
	private Date homeworkTime;
	private Date createTime;
	private String tanentId;
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
	public Date getHomeworkTime() {
		return homeworkTime;
	}
	public void setHomeworkTime(Date homeworkTime) {
		this.homeworkTime = homeworkTime;
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
}
