package com.simple.model.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.simple.model.SysUser;

public class AllClass implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<NianJi> njlist = new ArrayList<NianJi>();
	private List<SysUser> teachers = new ArrayList<SysUser>();
	private int version;

	public List<NianJi> getNjlist() {
		return njlist;
	}

	public void setNjlist(List<NianJi> njlist) {
		this.njlist = njlist;
	}

	public List<SysUser> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<SysUser> teachers) {
		this.teachers = teachers;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
