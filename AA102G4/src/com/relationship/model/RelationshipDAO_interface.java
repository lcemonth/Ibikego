package com.relationship.model;

import java.util.*;

public interface RelationshipDAO_interface {
	public void insert(RelationshipVO relationshipVO);
    public void update(RelationshipVO relationshipVO);
    public void delete(Integer rel_mem_no);
    public RelationshipVO findByPrimaryKey(Integer mem_no_main);
    public List<RelationshipVO> getAll();
    //合
    public List<RelationshipVO> getMmemFollowRelmem(Integer mem_no_main);
    //合
    
    //瑄
    public RelationshipVO checkRelationship(Integer mem_no_main,Integer rel_mem_no);
    public Integer checkFollow(Integer mem_no_main,Integer rel_mem_no);
    public Integer checkBlacklist(Integer mem_no_main,Integer rel_mem_no);
    //瑄
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<MemberVO> getAll(Map<String, String[]> map); 
}