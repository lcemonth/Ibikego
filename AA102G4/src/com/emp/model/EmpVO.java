package com.emp.model;
import java.sql.Date;
public class EmpVO implements java.io.Serializable{
	private Integer emp_no;		//員工編號
	private String emp_acc;		//帳號
	private String emp_pw;		//密碼
	private String emp_name;	//姓名
	private String emp_email;	//電子郵件
	private String emp_tel;		//聯絡電話
	private String emp_phone;	//聯絡手機
	private String emp_ps;		//備註
	private Date emp_hire;		//到職日期
	private Date emp_over;		//離職日期
	private Integer emp_alive=0;	//帳號存活
	
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_acc() {
		return emp_acc;
	}
	public void setEmp_acc(String emp_acc) {
		this.emp_acc = emp_acc;
	}
	public String getEmp_pw() {
		return emp_pw;
	}
	public void setEmp_pw(String emp_pw) {
		this.emp_pw = emp_pw;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_tel() {
		return emp_tel;
	}
	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_ps() {
		return emp_ps;
	}
	public void setEmp_ps(String emp_ps) {
		this.emp_ps = emp_ps;
	}
	public Date getEmp_hire() {
		return emp_hire;
	}
	public void setEmp_hire(java.sql.Date emp_hire) {
		this.emp_hire = emp_hire;
	}
	public Date getEmp_over() {
		return emp_over;
	}
	public void setEmp_over(java.sql.Date emp_over) {
		this.emp_over = emp_over;
	}
	public Integer getEmp_alive() {
		return emp_alive;
	}
	public void setEmp_alive(Integer emp_alive) {
		this.emp_alive = emp_alive;
	}
}
