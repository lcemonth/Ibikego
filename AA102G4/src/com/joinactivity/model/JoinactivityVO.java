package com.joinactivity.model;

public class JoinactivityVO implements java.io.Serializable{
	private Integer act_no;
	private Integer mem_no;
	private Integer joinact_is_join;
	private Integer joinact_score;
	private Double joinact_lati;
	private Double joinact_longi;
	public Integer getAct_no() {
		return act_no;
	}
	public void setAct_no(Integer act_no) {
		this.act_no = act_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getJoinact_is_join() {
		return joinact_is_join;
	}
	public void setJoinact_is_join(Integer joinact_is_join) {
		this.joinact_is_join = joinact_is_join;
	}
	public Integer getJoinact_score() {
		return joinact_score;
	}
	public void setJoinact_score(Integer joinact_score) {
		this.joinact_score = joinact_score;
	}
	public Double getJoinact_lati() {
		return joinact_lati;
	}
	public void setJoinact_lati(Double joinact_lati) {
		this.joinact_lati = joinact_lati;
	}
	public Double getJoinact_longi() {
		return joinact_longi;
	}
	public void setJoinact_longi(Double joinact_longi) {
		this.joinact_longi = joinact_longi;
	}
	
}
