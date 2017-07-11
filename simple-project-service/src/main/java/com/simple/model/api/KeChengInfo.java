package com.simple.model.api;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.simple.common.config.EnvPropertiesConfiger;

public class KeChengInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String kctp = "";
	private String name = "" ;
	private String kcjj = "";
	private String splb = "";
	private String kcnr = "";
	private String code = "";
	private String kjdz = "";
	private String teacherWenjuan = "";
	private String studentWenjuan = "";
	private String from = "森霖木";
	private String kcxlbh = "";
	private String kcxlmc = "";
	private String kcxltp = "";
	private String childKcxlbh = "";
	private String childKcxlmc = "";
	private String childKcxltp = "";
	private String dmtkjdz = "";
	private int flag;
	public String getKctp() {
		return kctp;
	}
	public void setKctp(String kctp) {
		if (!StringUtils.isEmpty(kctp)&&(!kctp.startsWith("http"))) {
			this.kctp = EnvPropertiesConfiger.getValue("fileDomain")+kctp.replace("\\", "/");
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKcjj() {
		return kcjj;
	}
	public void setKcjj(String kcjj) {
		this.kcjj = kcjj;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSplb() {
		return splb;
	}
	public void setSplb(String splb) {
		this.splb = splb;
	}
	public String getKcnr() {
		return kcnr;
	}
	public void setKcnr(String kcnr) {
		this.kcnr = kcnr;
	}
	public String getKjdz() {
		return kjdz;
	}
	public void setKjdz(String kjdz) {
		if (!StringUtils.isEmpty(kjdz)&&(!kjdz.startsWith("http"))) {
			this.kjdz = EnvPropertiesConfiger.getValue("fileDomain")+kjdz.replace("\\", "/");
		}
	}
	public String getTeacherWenjuan() {
		return teacherWenjuan;
	}
	public void setTeacherWenjuan(String teacherWenjuan) {
		this.teacherWenjuan = EnvPropertiesConfiger.getValue("wenjuanUrl") + teacherWenjuan;
	}
	public String getStudentWenjuan() {
		return studentWenjuan;
	}
	public void setStudentWenjuan(String studentWenjuan) {
		this.studentWenjuan = EnvPropertiesConfiger.getValue("wenjuanUrl")+studentWenjuan;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
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
	public String getChildKcxlbh() {
		return childKcxlbh;
	}
	public void setChildKcxlbh(String childKcxlbh) {
		this.childKcxlbh = childKcxlbh;
	}
	public String getChildKcxlmc() {
		return childKcxlmc;
	}
	public void setChildKcxlmc(String childKcxlmc) {
		this.childKcxlmc = childKcxlmc;
	}
	public String getKcxltp() {
		return kcxltp;
	}
	public void setKcxltp(String kcxltp) {
		this.kcxltp = kcxltp;
	}
	public String getChildKcxltp() {
		return childKcxltp;
	}
	public void setChildKcxltp(String childKcxltp) {
		this.childKcxltp = childKcxltp;
	}
	public String getDmtkjdz() {
		return dmtkjdz;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public void setDmtkjdz(String dmtkjdz) {
		if (!StringUtils.isEmpty(dmtkjdz)&&(!dmtkjdz.startsWith("http"))) {
			this.dmtkjdz = EnvPropertiesConfiger.getValue("fileDomain")+dmtkjdz;
		}
	}
}
