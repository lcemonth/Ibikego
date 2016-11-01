package com.member.model;

public class MemberVO implements java.io.Serializable{
	private Integer mem_no;
	private String mem_acc;
	private String mem_pw;
	private String mem_name;
	private String mem_nickname;
	private String mem_add;
	private String mem_phone;
	private String mem_email;
	private byte[] mem_photo;
	private Integer mem_reg;
	private Integer mem_del;

	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_acc() {
		return mem_acc;
	}
	public void setMem_acc(String mem_acc) {
		this.mem_acc = mem_acc;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public String getMem_add() {
		return mem_add;
	}
	public void setMem_add(String mem_add) {
		this.mem_add = mem_add;
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
	public Integer getMem_reg() {
		return mem_reg;
	}
	public void setMem_reg(Integer mem_reg) {
		this.mem_reg = mem_reg;
	}
	public Integer getMem_del() {
		return mem_del;
	}
	public void setMem_del(Integer mem_del) {
		this.mem_del = mem_del;
	}
}