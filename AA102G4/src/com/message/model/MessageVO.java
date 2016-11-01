package com.message.model;

import java.sql.Date;

public class MessageVO implements java.io.Serializable{
	private Integer mes_no;
	private Integer blog_no;
	private Integer mem_no;
	private String mes_content;
	private Date mes_cre;
	public Integer getMes_no() {
		return mes_no;
	}
	public void setMes_no(Integer mes_no) {
		this.mes_no = mes_no;
	}
	public Integer getBlog_no() {
		return blog_no;
	}
	public void setBlog_no(Integer blog_no) {
		this.blog_no = blog_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getMes_content() {
		return mes_content;
	}
	public void setMes_content(String mes_content) {
		this.mes_content = mes_content;
	}
	public Date getMes_cre() {
		return mes_cre;
	}
	public void setMes_cre(Date mes_cre) {
		this.mes_cre = mes_cre;
	}
}