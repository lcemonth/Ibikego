package com.itemdetails.model;

import java.sql.Date;

public class ItemDetailsVO implements java.io.Serializable{

	private Integer item_no;//商品編號
	private Integer mem_no;//會員編號
	private Integer item_detail_status;//商品訂單狀態(交易中或完畢) 
	private Integer item_is_get;//取貨狀態 (預設Boolean False)
	private Integer item_is_sellerscore;//賣方評分狀態 (預設Boolean False)
	private Integer item_is_buyerscore;//買方評分狀態 (預設Boolean False)
	private Integer item_detail_del;//訂單取消
	private Integer item_seller_score;//賣家分數
	private Integer item_buyer_score;//買家分數
	private Date item_order_time;
	private String item_buyer_name;
	private String item_buyer_add;
	private String item_buyer_phone;
	public Integer getItem_no() {
		return item_no;
	}
	public void setItem_no(Integer item_no) {
		this.item_no = item_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getItem_detail_status() {
		return item_detail_status;
	}
	public void setItem_detail_status(Integer item_detail_status) {
		this.item_detail_status = item_detail_status;
	}
	public Integer getItem_is_get() {
		return item_is_get;
	}
	public void setItem_is_get(Integer item_is_get) {
		this.item_is_get = item_is_get;
	}
	public Integer getItem_is_sellerscore() {
		return item_is_sellerscore;
	}
	public void setItem_is_sellerscore(Integer item_is_sellerscore) {
		this.item_is_sellerscore = item_is_sellerscore;
	}
	public Integer getItem_is_buyerscore() {
		return item_is_buyerscore;
	}
	public void setItem_is_buyerscore(Integer item_is_buyerscore) {
		this.item_is_buyerscore = item_is_buyerscore;
	}
	public Integer getItem_detail_del() {
		return item_detail_del;
	}
	public void setItem_detail_del(Integer item_detail_del) {
		this.item_detail_del = item_detail_del;
	}
	public Integer getItem_seller_score() {
		return item_seller_score;
	}
	public void setItem_seller_score(Integer item_seller_score) {
		this.item_seller_score = item_seller_score;
	}
	public Integer getItem_buyer_score() {
		return item_buyer_score;
	}
	public void setItem_buyer_score(Integer item_buyer_score) {
		this.item_buyer_score = item_buyer_score;
	}
	public Date getItem_order_time() {
		return item_order_time;
	}
	public void setItem_order_time(Date item_order_time) {
		this.item_order_time = item_order_time;
	}
	public String getItem_buyer_name() {
		return item_buyer_name;
	}
	public void setItem_buyer_name(String item_buyer_name) {
		this.item_buyer_name = item_buyer_name;
	}
	public String getItem_buyer_add() {
		return item_buyer_add;
	}
	public void setItem_buyer_add(String item_buyer_add) {
		this.item_buyer_add = item_buyer_add;
	}
	public String getItem_buyer_phone() {
		return item_buyer_phone;
	}
	public void setItem_buyer_phone(String item_buyer_phone) {
		this.item_buyer_phone = item_buyer_phone;
	}
	
	
	
}