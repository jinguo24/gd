package com.simple.model;

import java.io.Serializable;

public class GdHomeWorkItems implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String tanentId;
	private String tanentName;
	private int homeworkId;
	private String homeworkName;
	private String itemNames;
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
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
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
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
}
