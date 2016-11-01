package com.itemimage.model;

public class ItemImageVO implements java.io.Serializable{

	private Integer item_img_no;//商品圖片編號
	private Integer item_no;//商品編號
	private byte[] item_img;//圖片
	
	public Integer getItem_img_no() {
		return item_img_no;
	}
	public void setItem_img_no(Integer item_img_no) {
		this.item_img_no = item_img_no;
	}
	public Integer getItem_no() {
		return item_no;
	}
	public void setItem_no(Integer item_no) {
		this.item_no = item_no;
	}
	public byte[] getItem_img() {
		return item_img;
	}
	public void setItem_img(byte[] item_img) {
		this.item_img = item_img;
	}
	
}