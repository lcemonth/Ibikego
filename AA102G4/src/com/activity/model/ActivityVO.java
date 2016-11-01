package com.activity.model;
import java.sql.*;

public class ActivityVO implements java.io.Serializable{

	private Integer act_no;
	private Integer mem_no;
	private Integer loc_no;
	private Integer stroke_no;
	private String act_name;
	private String act_loc;
	private Date act_start_date; 
	private Date act_end_date;
	private String act_exp;
	private byte[] act_photo;
	private Integer act_is_public;
	private byte[] act_act_route;
	private byte[] act_alti;
	private Double act_km;
	private Integer act_joinlimit;
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
	public Integer getLoc_no() {
		return loc_no;
	}
	public void setLoc_no(Integer loc_no) {
		this.loc_no = loc_no;
	}
	public Integer getStroke_no() {
		return stroke_no;
	}
	public void setStroke_no(Integer stroke_no) {
		this.stroke_no = stroke_no;
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
	public byte[] getAct_act_route() {
		
		return act_act_route;
		
	}
	public void setAct_act_route(byte[] act_act_route) {
		this.act_act_route = act_act_route;
	}
	public byte[] getAct_alti() {
		
			return act_alti;
				
		
	}
	public void setAct_alti(byte[] act_alti) {
		this.act_alti = act_alti;
	}
	public Double getAct_km() {
		return act_km;
	}
	public void setAct_km(Double act_km) {
		this.act_km = act_km;
	}
	public Integer getAct_joinlimit() {
		return act_joinlimit;
	}
	public void setAct_joinlimit(Integer act_joinlimit) {
		this.act_joinlimit = act_joinlimit;
	}
	
	
}
