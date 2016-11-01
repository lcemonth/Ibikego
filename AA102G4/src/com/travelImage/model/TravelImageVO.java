package com.travelImage.model;

public class TravelImageVO implements java.io.Serializable{
	private Integer tra_img_no;
	private Integer tra_no;
	private byte[] tra_img;
	
	public Integer getTra_img_no() {
		return tra_img_no;
	}
	public void setTra_img_no(Integer tra_img_no) {
		this.tra_img_no = tra_img_no;
	}
	public Integer getTra_no() {
		return tra_no;
	}
	public void setTra_no(Integer tra_no) {
		this.tra_no = tra_no;
	}
	public byte[] getTra_img() {
		return tra_img;
	}
	public void setTra_img(byte[] tra_img) {
		this.tra_img = tra_img;
	}
}