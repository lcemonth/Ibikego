package com.forum.model;
import java.sql.Date;

public class ForumVO implements java.io.Serializable{
	private Integer forum_no;
	private Integer mem_no;
	private String forum_title;
	private String forum_content;
	private Date forum_cretime;
	private Integer forum_del;
	
	public Integer getForum_no(){
		return forum_no;
	}
	public void setForum_no(Integer forum_no){
		this.forum_no=forum_no;
	}
	
	public Integer getMem_no(){
		return mem_no;
	}
	public void setMem_no(Integer mem_no){
		this.mem_no=mem_no;
	}
	
	public String getForum_title(){
		return forum_title;
	}
	public void setForum_title(String forum_title){
		this.forum_title=forum_title;
	}
	
	public String getForum_content(){
		return forum_content;
	}
	public void setForum_content(String forum_content){
		this.forum_content=forum_content;
	}
	
	public Date getForum_cretime(){
		return forum_cretime;
	}
	public void setForum_cretime(Date forum_cretime){
		this.forum_cretime=forum_cretime;
	}
	
	public Integer getForum_del(){
		return forum_del;
	}
	public void setForum_del(Integer forum_del){
		this.forum_del=forum_del;
	}
}