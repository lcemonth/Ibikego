package com.relationship.model;

import java.util.List;

public class RelationshipService{
	private RelationshipDAO_interface dao;
	public RelationshipService(){
		dao = new RelationshipDAO();
	}
	public RelationshipVO addRelationship(RelationshipVO relationshipVO){
		dao.insert(relationshipVO);
		return relationshipVO;
	}
	public RelationshipVO updateRelationship(RelationshipVO relationshipVO){
		dao.update(relationshipVO);
		return relationshipVO;
	}
	public void deleteRelationship(Integer rel_mem_no){
		dao.delete(rel_mem_no);
	}
	public RelationshipVO getOneRelationship(Integer rel_mem_no){
		return dao.findByPrimaryKey(rel_mem_no);
	}
	public List<RelationshipVO> getAll(){
		return dao.getAll();
	}
	//--合
	public List<RelationshipVO> getMFollowRms(Integer mem_no_main){
		return dao.getMmemFollowRelmem(mem_no_main);
	}
	//--合
	
	//--瑄
	public RelationshipVO checkRelationship(Integer mem_no_main,Integer rel_mem_no){
		return dao.checkRelationship(mem_no_main,rel_mem_no);
	}
	public Integer checkFollow(Integer mem_no_main,Integer rel_mem_no){
		return dao.checkFollow(mem_no_main,rel_mem_no);
	}
	public Integer checkBlacklist(Integer mem_no_main,Integer rel_mem_no){
		return dao.checkBlacklist(mem_no_main,rel_mem_no);
	}
	//--瑄
}