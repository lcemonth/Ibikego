package com.blogRouteDetails.model;

import java.sql.Date;

public class BlogRouteDetailsVO implements java.io.Serializable{
	private Integer route_no;
	private Integer blog_no;
	public Integer getRoute_no() {
		return route_no;
	}
	public void setRoute_no(Integer route_no) {
		this.route_no = route_no;
	}
	public Integer getBlog_no() {
		return blog_no;
	}
	public void setBlog_no(Integer blog_no) {
		this.blog_no = blog_no;
	}
}
