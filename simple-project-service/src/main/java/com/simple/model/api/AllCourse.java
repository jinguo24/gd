package com.simple.model.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllCourse implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<KeChengXiLie> xllist = new ArrayList<KeChengXiLie>();
	private int version;

	public List<KeChengXiLie> getXllist() {
		return xllist;
	}
	public void setXllist(List<KeChengXiLie> xllist) {
		this.xllist = xllist;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
