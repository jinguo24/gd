package com.simple.model;

import java.io.Serializable;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class GdSignWorkers implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String gsid;
	private String cardNo;
	private String cardImage="";
	private Date createTime;
	private String showCreateTime;
	private String name;
	private String sex="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGsid() {
		return gsid;
	}
	public void setGsid(String gsid) {
		this.gsid = gsid;
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
		if ( null != createTime) {
			this.showCreateTime = DateUtil.date2AllString(createTime);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShowCreateTime() {
		return showCreateTime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
