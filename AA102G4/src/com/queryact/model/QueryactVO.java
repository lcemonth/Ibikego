package com.queryact.model;
import java.sql.*;

public class QueryactVO implements java.io.Serializable{
	
	private Integer act_no;
	private Integer mem_no;
	private String mem_name;
	private String mem_phone;
	private String mem_email;
	private byte[] mem_photo;
	private String act_name;
	private String act_loc;
	private Date act_start_date; 
	private Date act_end_date;
	private String act_exp;
	private byte[] act_photo;
	private Integer act_is_public;
	private Integer act_joinlimit;
	private Integer stroke_no;
	private String stroke_name;
	private Integer tra_no;
	private Integer stroke_whichday;
	public Integer getAct_no() {
		return act_no;
	}
	public void setAct_no(Integer act_no) {
		this.act_no = act_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public byte[] getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getAct_loc() {
		return act_loc;
	}
	public void setAct_loc(String act_loc) {
		this.act_loc = act_loc;
	}
	public Date getAct_start_date() {
		return act_start_date;
	}
	public void setAct_start_date(Date act_start_date) {
		this.act_start_date = act_start_date;
	}
	public Date getAct_end_date() {
		return act_end_date;
	}
	public void setAct_end_date(Date act_end_date) {
		this.act_end_date = act_end_date;
	}
	public String getAct_exp() {
		return act_exp;
	}
	public void setAct_exp(String act_exp) {
		this.act_exp = act_exp;
	}
	public byte[] getAct_photo() {
		return act_photo;
	}
	public void setAct_photo(byte[] act_photo) {
		this.act_photo = act_photo;
	}
	public Integer getAct_is_public() {
		return act_is_public;
	}
	public void setAct_is_public(Integer act_is_public) {
		this.act_is_public = act_is_public;
	}
	public Integer getAct_joinlimit() {
		return act_joinlimit;
	}
	public void setAct_joinlimit(Integer act_joinlimit) {
		this.act_joinlimit = act_joinlimit;
	}
	public Integer getStroke_no() {
		return stroke_no;
	}
	public void setStroke_no(Integer stroke_no) {
		this.stroke_no = stroke_no;
	}
	public String getStroke_name() {
		return stroke_name;
	}
	public void setStroke_name(String stroke_name) {
		this.stroke_name = stroke_name;
	}
	public Integer getTra_no() {
		return tra_no;
	}
	public void setTra_no(Integer tra_no) {
		this.tra_no = tra_no;
	}
	public Integer getStroke_whichday() {
		return stroke_whichday;
	}
	public void setStroke_whichday(Integer stroke_whichday) {
		this.stroke_whichday = stroke_whichday;
	}
	
}
