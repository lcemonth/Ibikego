package com.forumresponse.model;
import java.sql.Date;

public class ForumresVO implements java.io.Serializable{
	private Integer forumres_no;
	private Integer forum_no;
	private Integer mem_no;
	private String  forumres_con;
	private Date forumres_cretime;
	private Integer forumres_del;
	
	public Integer getForumres_no(){
		return forumres_no;
	}
	public void  setForumres_no(Integer forumres_no){
		this.forumres_no=forumres_no;
	}
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
	public String getForumres_con(){
		return forumres_con;
	}
	public void setForumres_con(String forumres_con){
		this.forumres_con=forumres_con;
	}
	public Date getForumres_cretime(){
		return forumres_cretime;
	}
	public void setForumres_cretime(Date forumres_cretime){
		this.forumres_cretime=forumres_cretime;
	}
	
	public Integer getForumres_del(){
		return forumres_del;
	}
	
	public void setForumres_del(Integer forumres_del){
		this.forumres_del=forumres_del;
	}
	

}
