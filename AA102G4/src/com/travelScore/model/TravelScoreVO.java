package com.travelScore.model;

public class TravelScoreVO implements java.io.Serializable{
	private Integer tra_no;
	private Integer mem_no;
	private Integer tra_score;
	private Integer tra_score_status;
	private Integer totalScore;
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getTra_no() {
		return tra_no;
	}
	public void setTra_no(Integer tra_no) {
		this.tra_no = tra_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getTra_score() {
		return tra_score;
	}
	public void setTra_score(Integer tra_score) {
		this.tra_score = tra_score;
	}
	public Integer getTra_score_status() {
		return tra_score_status;
	}
	public void setTra_score_status(Integer tra_score_status) {
		this.tra_score_status = tra_score_status;
	}
}