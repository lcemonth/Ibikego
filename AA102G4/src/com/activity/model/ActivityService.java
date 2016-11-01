package com.activity.model;

import java.util.List;
import java.util.Map;

public class ActivityService {
	private ActivityDAO_interface dao;
	public ActivityService(){
		dao=new ActivityDAO();
	}
	
	public ActivityVO addAct(Integer mem_no,Integer loc_no,Integer stroke_no,String act_name,String act_loc,java.sql.Date act_start_date,
		   java.sql.Date act_end_date,String act_exp,byte[] act_photo,Integer act_is_public,byte[] act_act_route,
		   byte[] act_alti,Double act_km,Integer act_joinlimit){
		
		ActivityVO actVO = new ActivityVO();
		    actVO.setMem_no(mem_no);
			actVO.setLoc_no(loc_no);
			actVO.setStroke_no(stroke_no);
			actVO.setAct_name(act_name);
			actVO.setAct_loc(act_loc);
			actVO.setAct_start_date(act_start_date);
			actVO.setAct_end_date(act_end_date);
			actVO.setAct_exp(act_exp);
			actVO.setAct_photo(act_photo);
			actVO.setAct_is_public(act_is_public);
			actVO.setAct_act_route(act_act_route);
			actVO.setAct_alti(act_alti);
			actVO.setAct_km(act_km);
			actVO.setAct_joinlimit(act_joinlimit);
		dao.insert(actVO);
		
		return actVO;
	}
	
	public ActivityVO addActNoStroke(Integer mem_no,Integer loc_no,String act_name,String act_loc,java.sql.Date act_start_date,
			   java.sql.Date act_end_date,String act_exp,byte[] act_photo,Integer act_is_public,byte[] act_act_route,
			   byte[] act_alti,Double act_km,Integer act_joinlimit){
			
			ActivityVO actVO = new ActivityVO();
			    actVO.setMem_no(mem_no);
				actVO.setLoc_no(loc_no);
				actVO.setAct_name(act_name);
				actVO.setAct_loc(act_loc);
				actVO.setAct_start_date(act_start_date);
				actVO.setAct_end_date(act_end_date);
				actVO.setAct_exp(act_exp);
				actVO.setAct_photo(act_photo);
				actVO.setAct_is_public(act_is_public);
				actVO.setAct_act_route(act_act_route);
				actVO.setAct_alti(act_alti);
				actVO.setAct_km(act_km);
				actVO.setAct_joinlimit(act_joinlimit);
			dao.insertNoStroke(actVO);
			
			return actVO;
		}
	
	public ActivityVO updateAct(Integer act_no,Integer mem_no,Integer loc_no,Integer stroke_no,String act_name,String act_loc,java.sql.Date act_start_date,
			   java.sql.Date act_end_date,String act_exp,byte[] act_photo,Integer act_is_public,byte[] act_act_route,
			   byte[] act_alti,Double act_km,Integer act_joinlimit){
			
			ActivityVO actVO = new ActivityVO();
			actVO.setMem_no(mem_no);
			actVO.setAct_no(act_no);
			actVO.setLoc_no(loc_no);
			actVO.setStroke_no(stroke_no);
			actVO.setAct_name(act_name);
			actVO.setAct_loc(act_loc);
			actVO.setAct_start_date(act_start_date);
			actVO.setAct_end_date(act_end_date);
			actVO.setAct_exp(act_exp);
			actVO.setAct_photo(act_photo);
			actVO.setAct_is_public(act_is_public);
			actVO.setAct_act_route(act_act_route);
			actVO.setAct_alti(act_alti);
			actVO.setAct_km(act_km);
			actVO.setAct_joinlimit(act_joinlimit);
			dao.update(actVO);
			
			return actVO;
		}
	public ActivityVO updateNoStrokeAct(Integer act_no,Integer mem_no,Integer loc_no,String act_name,String act_loc,java.sql.Date act_start_date,
			   java.sql.Date act_end_date,String act_exp,byte[] act_photo,Integer act_is_public,byte[] act_act_route,
			   byte[] act_alti,Double act_km,Integer act_joinlimit){
			
			ActivityVO actVO = new ActivityVO();
			actVO.setMem_no(mem_no);
			actVO.setAct_no(act_no);
			actVO.setLoc_no(loc_no);
			actVO.setAct_name(act_name);
			actVO.setAct_loc(act_loc);
			actVO.setAct_start_date(act_start_date);
			actVO.setAct_end_date(act_end_date);
			actVO.setAct_exp(act_exp);
			actVO.setAct_photo(act_photo);
			actVO.setAct_is_public(act_is_public);
			actVO.setAct_act_route(act_act_route);
			actVO.setAct_alti(act_alti);
			actVO.setAct_km(act_km);
			actVO.setAct_joinlimit(act_joinlimit);
			dao.updateNoStroke(actVO);
			
			return actVO;
		}
	public void deleteAct(Integer act_no) {
		dao.delete(act_no);
	}

	public ActivityVO getOneAct(Integer act_no) {
		return dao.findByPrimaryKey(act_no);
	}

	public List<ActivityVO> getAll() {
		return dao.getAll();
	}
	
	public List<ActivityVO> getAllact_recent() {
		return dao.getAllActRecent();
	}
	
	public ActivityVO getOneActByMem_no(Integer mem_no,Integer act_no) {
		return dao.findActByMem_no(mem_no, act_no);
	}
	public List<ActivityVO> getMemJoinedActs(Integer mem_no) {
		return dao.findMemJoinedActs(mem_no);
	}
	public List<ActivityVO> getMemInvitedActs(Integer mem_no) {
		return dao.findMemInvitedActs(mem_no);
	}
	public List<ActivityVO> getAddacts_by_memno(Integer mem_no) {
		return dao.findAddActsByMemno(mem_no);
	}
	public void updatePublicStatus(Integer act_is_public,Integer mem_no, Integer act_no) {
		dao.updatePublic(act_is_public,mem_no,act_no);
	}
	public int getChkActDate(String in_start,String in_end,Integer mem_no){
		return dao.chkActDate(in_start,in_end,mem_no);
	}
	public int getChkJoinActDate(String in_start,String in_end,Integer mem_no,Integer act_no){
		return dao.chkJoinActDate(in_start,in_end,mem_no,act_no);
	}
	public List<ActivityVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public List<ActivityVO> getMemJoinedActs(Map<String, String[]> map) {
		return dao.findMemJoinedActs(map);
	}
	public List<ActivityVO> getTopActs(Integer top){
		return dao.findTopActs(top);
	}
}
