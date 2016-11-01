package com.item.model;

public class ItemVO implements java.io.Serializable{

	private Integer item_no;//商品編號
	private Integer mem_no;//會員編號
	private String item_name;//商品名稱
	private Integer item_price;//商品價錢
	private String item_exp;//商品說明
	private Integer item_is_added;//商品狀態 (上架 下架)
	
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
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Integer getItem_price() {
		return item_price;
	}
	public void setItem_price(Integer item_price) {
		this.item_price = item_price;
	}
	public String getItem_exp() {
		return item_exp;
	}
	public void setItem_exp(String item_exp) {
		this.item_exp = item_exp;
	}
	public Integer getItem_is_added() {
		return item_is_added;
	}
	public void setItem_is_added(Integer item_is_added) {
		this.item_is_added = item_is_added;
	}
	
	
}