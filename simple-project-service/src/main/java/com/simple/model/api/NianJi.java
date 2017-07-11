package com.simple.model.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NianJi implements Serializable{

	private static final long serialVersionUID = 1L;
	private String njmc ="" ;
	private String njbh ="";
	private List<BanJi> bjlist = new ArrayList<BanJi>();
	public String getNjmc() {
		return njmc;
	}
	public void setNjmc(String njmc) {
		this.njmc = njmc;
	}
	public String getNjbh() {
		return njbh;
	}
	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}
	public List<BanJi> getBjlist() {
		return bjlist;
	}
	public void setBjlist(List<BanJi> bjlist) {
		this.bjlist = bjlist;
	}
}
