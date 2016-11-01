package com.blogScore.model;

public class BlogScoreVO implements java.io.Serializable{
	private Integer blog_no;
	private Integer mem_no;
	private Integer blog_score;
	private Integer blog_score_status;
	/****************************************/
	private Integer totalScore;
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	/****************************************/
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
	public Integer getBlog_score() {
		return blog_score;
	}
	public void setBlog_score(Integer blog_score) {
		this.blog_score = blog_score;
	}
	public Integer getBlog_score_status() {
		return blog_score_status;
	}
	public void setBlog_score_status(Integer blog_score_status) {
		this.blog_score_status = blog_score_status;
	}
}