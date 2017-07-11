package com.simple.model;

import java.io.Serializable;

public class AllVersion implements Serializable{

	private static final long serialVersionUID = 1L;

	private int vType;
	private int vVersion;
	public int getvType() {
		return vType;
	}
	public void setvType(int vType) {
		this.vType = vType;
	}
	public int getvVersion() {
		return vVersion;
	}
	public void setvVersion(int vVersion) {
		this.vVersion = vVersion;
	}
}
