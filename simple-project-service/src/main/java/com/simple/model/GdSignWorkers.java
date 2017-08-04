package com.simple.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class GdSignWorkers implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String gsid;
	private String tanentId;
	private String cardNo;
	private String groupName;
	private String leaderName;
	private String cardImage="";
	private Date createTime;
	private Date createDate;
	private String showCreateTime;
	private String name;
	private String sex="";
	private String nation="";//民族
	private String address="";//住址
	private String validtermOfStart="";//身份证有效开始日期
	private String validtermOfEnd="";//身份证有效截止日期
	private String department="";//签发机关
	private String itemJson;
	private Date judgeTime;
	private int zonghe;
	
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
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getValidtermOfStart() {
		return validtermOfStart;
	}
	public void setValidtermOfStart(String validtermOfStart) {
		this.validtermOfStart = validtermOfStart;
	}
	public String getValidtermOfEnd() {
		return validtermOfEnd;
	}
	public void setValidtermOfEnd(String validtermOfEnd) {
		this.validtermOfEnd = validtermOfEnd;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setShowCreateTime(String showCreateTime) {
		this.showCreateTime = showCreateTime;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getItemJson() {
		return itemJson;
	}
	public void setItemJson(String itemJson) {
		this.itemJson = itemJson;
	}
	public Date getJudgeTime() {
		return judgeTime;
	}
	public void setJudgeTime(Date judgeTime) {
		this.judgeTime = judgeTime;
	}
	public int getZonghe() {
		return zonghe;
	}
	public void setZonghe(int zonghe) {
		this.zonghe = zonghe;
	}
}
