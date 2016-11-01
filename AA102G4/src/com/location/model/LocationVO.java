package com.location.model;

public class LocationVO implements java.io.Serializable{
	private Integer loc_no;
	private String loc_name;
	private Double loc_longi;
	private Double loc_lati;
	
	public Integer getLoc_no() {
		return loc_no;
	}
	public void setLoc_no(Integer loc_no) {
		this.loc_no = loc_no;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public Double getLoc_longi() {
		return loc_longi;
	}
	public void setLoc_longi(Double loc_longi) {
		this.loc_longi = loc_longi;
	}
	public Double getLoc_lati() {
		return loc_lati;
	}
	public void setLoc_lati(Double loc_lati) {
		this.loc_lati = loc_lati;
	}
	
	
}
