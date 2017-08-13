package com.simple.model;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import freemarker.template.utility.StringUtil;

public class GdJudgeItems implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String tanentId="";
	private String tanentName="";
	private String itemNames="";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	public String getItemNames() {
		return itemNames;
	}
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
	public String getTanentName() {
		return tanentName;
	}
	public void setTanentName(String tanentName) {
		this.tanentName = tanentName;
	}
	public String[] getItemNameArray() {
		if (!StringUtils.isEmpty(this.itemNames)) {
			return this.itemNames.split(",");
		}
		return null;
	}
	public void initItems() {
		
	}
}
