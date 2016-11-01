package com.stroke.model;

import java.sql.Date;

public class StrokeVO implements java.io.Serializable{
	private Integer stroke_no;	//行程編號
	private Integer mem_no;		//會員編號
	private String stroke_name;	//行程名稱
	private Date 	buildDate;			//日期
	
	public Integer getStroke_no() {
		return stroke_no;
	}
	public void setStroke_no(Integer stroke_no) {
		this.stroke_no = stroke_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getStroke_name() {
		return stroke_name;
	}
	public void setStroke_name(String stroke_name) {
		this.stroke_name = stroke_name;
	}
	public Date getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

}
