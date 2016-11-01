package com.blog.model;

import java.sql.Date;

public class BlogVO implements java.io.Serializable{
	private Integer blog_no;
	private Integer mem_no;
	private String blog_title;
	private String blog_content;
	private Date blog_cre;
	private byte[] blog_photo;
	private Integer blog_del;
	
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
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_content() {
		return blog_content;
	}
	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}
	public Date getBlog_cre() {
		return blog_cre;
	}
	public void setBlog_cre(Date blog_cre) {
		this.blog_cre = blog_cre;
	}
	public byte[] getBlog_photo() {
		return blog_photo;
	}
	public void setBlog_photo(byte[] blog_photo) {
		this.blog_photo = blog_photo;
	}
	public Integer getBlog_del() {
		return blog_del;
	}
	public void setBlog_del(Integer blog_del) {
		this.blog_del = blog_del;
	}
}
