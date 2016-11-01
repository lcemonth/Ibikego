package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public void update_pw(MemberVO memberVO);
    public void delete(Integer mem_no);
    public MemberVO findByPrimaryKey(Integer mem_no);
    public MemberVO findByAcc(String mem_acc);
    public MemberVO findByACC_MAIL(String mem_acc,String mem_email);
    public List<MemberVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<MemberVO> getAll(Map<String, String[]> map);
    //--合
    public MemberVO findOneMemByAcc(String mem_acc);
    //--合
}