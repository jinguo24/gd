package com.simple.model.api;

import java.io.Serializable;
import java.util.List;

import com.simple.model.GdSignWorkers;

public class GdSingReport implements Serializable{

	private static final long serialVersionUID = 1L;

	private String tanentName="";
	private String homeworkDescri="";
	private String judgeDate="";
	private int signCounts;
	private int homeworkCounts;
	private int homeworkPass;
	private int judgePass;
	private int bujigeCounts;
	private int jigeCounts;
	private int lianghaoCounts;
	private int youxiuCounts;
	private int allpersons;
	private List<GdSignWorkers> workers;
	
	public String getTanentName() {
		return tanentName;
	}
	public void setTanentName(String tanentName) {
		this.tanentName = tanentName;
	}
	public String getHomeworkDescri() {
		return homeworkDescri;
	}
	public void setHomeworkDescri(String homeworkDescri) {
		this.homeworkDescri = homeworkDescri;
	}
	public int getSignCounts() {
		return signCounts;
	}
	public void setSignCounts(int signCounts) {
		this.signCounts = signCounts;
	}
	public int getHomeworkCounts() {
		return homeworkCounts;
	}
	public void setHomeworkCounts(int homeworkCounts) {
		this.homeworkCounts = homeworkCounts;
	}
	public int getHomeworkPass() {
		return homeworkPass;
	}
	public void setHomeworkPass(int homeworkPass) {
		this.homeworkPass = homeworkPass;
	}
	public int getJudgePass() {
		return judgePass;
	}
	public void setJudgePass(int judgePass) {
		this.judgePass = judgePass;
	}
	public int getBujigeCounts() {
		return bujigeCounts;
	}
	public void setBujigeCounts(int bujigeCounts) {
		this.bujigeCounts = bujigeCounts;
	}
	public int getJigeCounts() {
		return jigeCounts;
	}
	public void setJigeCounts(int jigeCounts) {
		this.jigeCounts = jigeCounts;
	}
	public int getLianghaoCounts() {
		return lianghaoCounts;
	}
	public void setLianghaoCounts(int lianghaoCounts) {
		this.lianghaoCounts = lianghaoCounts;
	}
	public int getYouxiuCounts() {
		return youxiuCounts;
	}
	public void setYouxiuCounts(int youxiuCounts) {
		this.youxiuCounts = youxiuCounts;
	}
	public String getJudgeDate() {
		return judgeDate;
	}
	public void setJudgeDate(String judgeDate) {
		this.judgeDate = judgeDate;
	}
	public int getAllpersons() {
		return allpersons;
	}
	public void setAllpersons(int allpersons) {
		this.allpersons = allpersons;
	}
	public List<GdSignWorkers> getWorkers() {
		return workers;
	}
	public void setWorkers(List<GdSignWorkers> workers) {
		this.workers = workers;
	}
}
