package com.questionsanswers.model;

public class Que_ansVO implements java.io.Serializable{
	private Integer que_ans_no;
	private String que_ans_q;
	private String que_ans_a;
	
	public Integer getQue_ans_no(){
		return que_ans_no;
	}
	public void setQue_ans_no(Integer que_ans_no){
		this.que_ans_no=que_ans_no;
	}
	public String getQue_ans_q(){
		return que_ans_q;
	}
	public void setQue_ans_q(String que_ans_q){
		this.que_ans_q=que_ans_q;
	}
	public String getQue_ans_a(){
		return que_ans_a;
	}
	public void setQue_ans_a(String que_ans_a){
		this.que_ans_a=que_ans_a;
	}

}
