package com.joinactivity.model;

import java.util.List;
import java.util.Set;

import com.activity.model.ActivityVO;


public class JoinactivityService {
	private JoinactivityDAO_interface dao;
	public JoinactivityService(){
		dao=new JoinactivityDAO();
	}
	
	public JoinactivityVO addJoinAct(Integer act_no,Integer mem_no,Integer joinact_is_join,Integer joinact_score,Double joinact_lati,Double joinact_longi){
		
		JoinactivityVO jactVO = new JoinactivityVO();
		jactVO.setAct_no(act_no);
		jactVO.setMem_no(mem_no);
		jactVO.setJoinact_is_join(joinact_is_join);
		jactVO.setJoinact_score(joinact_score);
		jactVO.setJoinact_lati(joinact_lati);
		jactVO.setJoinact_longi(joinact_longi);

		dao.insert(jactVO);
		
		return jactVO;
	}
	
	
	public JoinactivityVO updateJoinAct(Integer act_no,Integer mem_no,Integer joinact_is_join,Integer joinact_score,Double joinact_lati,Double joinact_longi){
			
			JoinactivityVO jactVO = new JoinactivityVO();
			jactVO.setAct_no(act_no);
			jactVO.setMem_no(mem_no);
			jactVO.setJoinact_is_join(joinact_is_join);
			jactVO.setJoinact_score(joinact_score);
			jactVO.setJoinact_lati(joinact_lati);
			jactVO.setJoinact_longi(joinact_longi);
			
			dao.update(jactVO);
			
			return jactVO;
		}
	public void deleteJoinAct(Integer act_no,Integer mem_no) {
		dao.delete(act_no,mem_no);
	}

	public List<JoinactivityVO> getActsByMem(Integer mem_no) {
		return dao.findActsByMem(mem_no);
	}
	
	public List<JoinactivityVO> getMemsByAct(Integer act_no) {
		return dao.findMemsByAct(act_no);
	}

	public List<JoinactivityVO> getAll() {
		return dao.getAll();
	}
	public void deleteJoinAct(Integer act_no) {
		dao.delete(act_no);
	}
	public List<JoinactivityVO> getJoinSureMemsByAct(Integer act_no){
		return dao.findJoinSureMemsByAct(act_no);
	}
	public Set<JoinactivityVO> getJoinMemsByActno(Integer act_no) {
		return dao.findJoinMemsByActno(act_no);
	}
	public JoinactivityVO getIsINActByMem_no(Integer act_no,Integer mem_no){
	return dao.findMemIsInAct(act_no,mem_no);
	}
	public List<JoinactivityVO> getCnJMemsByAct(Integer act_no) {
		return dao.findCnJMemsByAct(act_no);
	} 
	public int getCntMemsByAct(Integer act_no) {
		return dao.findCntMemsByAct(act_no);
	} 
	public int getCntNoSureByMem(Integer mem_no) {
		return dao.findCntNoSureByMem(mem_no);
	} 
		
}
