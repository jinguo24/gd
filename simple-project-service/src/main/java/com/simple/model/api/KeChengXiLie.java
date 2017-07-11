package com.simple.model.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.simple.common.config.EnvPropertiesConfiger;

public class KeChengXiLie implements Serializable{

	private static final long serialVersionUID = 1L;

	private String kcxlbh="";
	private String kcxlmc="";
	private String kctp="";
	private List<KeChengXiLie> zxllist;
	private List<KeCheng> kclist;
	public String getKcxlbh() {
		return kcxlbh;
	}
	public void setKcxlbh(String kcxlbh) {
		this.kcxlbh = kcxlbh;
	}
	public String getKcxlmc() {
		return kcxlmc;
	}
	public void setKcxlmc(String kcxlmc) {
		this.kcxlmc = kcxlmc;
	}
	public String getKctp() {
		return kctp;
	}
	public void setKctp(String kctp) {
		if (!StringUtils.isEmpty(kctp)&&(!kctp.startsWith("http"))) {
			this.kctp = EnvPropertiesConfiger.getValue("fileDomain")+kctp;
		}
	}
	public List<KeChengXiLie> getZxllist() {
		return zxllist;
	}
	public void setZxllist(List<KeChengXiLie> zxllist) {
		this.zxllist = zxllist;
	}
	public List<KeCheng> getKclist() {
		return kclist;
	}
	public void setKclist(List<KeCheng> kclist) {
		this.kclist = kclist;
	}
}
