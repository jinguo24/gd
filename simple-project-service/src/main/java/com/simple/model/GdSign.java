package com.simple.model;

import java.io.Serializable;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class GdSign implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private Date createTime;
	private String showCreateTime;
	private String tanentId;
	private String groupName;
	private String leaderName;
	private int status;
	private Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getShowCreateTime() {
		return showCreateTime;
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
}
