package com.reportcollect.model;

import java.util.List;

import com.activity.model.ActivityVO;


public class ReportcollectService {
	
	private ReportcollectDAO_interface dao;

	public ReportcollectService() {
		dao = new ReportcollectDAO();
	}

	public ReportcollectVO addRep(Integer mem_no,Integer tra_no,Integer act_no,Integer forum_no,
			Integer blog_no,Integer stroke_no,Integer rc_rep_handle,Integer rc_col_status,Integer rep_rel,String rep_content) {

		ReportcollectVO rcVO = new ReportcollectVO();

		rcVO.setMem_no(mem_no);
		rcVO.setTra_no(tra_no);
		rcVO.setAct_no(act_no);
		rcVO.setForum_no(forum_no);
		rcVO.setBlog_no(blog_no);
		rcVO.setStroke_no(stroke_no);
		rcVO.setRc_rep_handle(rc_rep_handle);
		rcVO.setRc_col_status(rc_col_status);
		rcVO.setRep_rel(rep_rel);
		rcVO.setRep_content(rep_content);
		dao.insert(rcVO);

		return rcVO;
	}
	
	public ReportcollectVO updateRep(Integer rc_no,Integer mem_no,Integer tra_no,Integer act_no,Integer forum_no,
			Integer blog_no,Integer stroke_no,Integer rc_rep_handle,Integer rc_col_status,Integer rep_rel,String rep_content) {

		ReportcollectVO rcVO = new ReportcollectVO();
		rcVO.setRc_no(rc_no);
		rcVO.setMem_no(mem_no);
		rcVO.setTra_no(tra_no);
		rcVO.setAct_no(act_no);
		rcVO.setForum_no(forum_no);
		rcVO.setBlog_no(blog_no);
		rcVO.setStroke_no(stroke_no);
		rcVO.setRc_rep_handle(rc_rep_handle);
		rcVO.setRc_col_status(rc_col_status);
		rcVO.setRep_rel(rep_rel);
		rcVO.setRep_content(rep_content);
		dao.update(rcVO);

		return rcVO;
	}
	
	public void deleteRep(Integer rc_no) {
		dao.delete(rc_no);
	}

	public ReportcollectVO getOneRep(Integer rc_no) {
		return dao.findByPrimaryKey(rc_no);
	}
	public ReportcollectVO checkTravelReport(Integer tra_no,Integer mem_no) {
		return dao.checkTravelReport(tra_no,mem_no);
	}
	
	public ReportcollectVO checkTravelCollect(Integer tra_no,Integer mem_no){
		return dao.checkTravelCollect(tra_no,mem_no);
	}
	
	public List<ReportcollectVO> onePoint(Integer tra_no){
		return dao.onePoint(tra_no);
	}

	public List<ReportcollectVO> getAll() {
		return dao.getAll();
	}
	public List<ReportcollectVO> getAllrep() {
		return dao.getAllrep();
	}
	public List<ReportcollectVO> getRcsBymem(Integer mem_no,Integer rep_rel){
  		return dao.getReportsBymem_no(mem_no,rep_rel);
  	}
	//找有無檢舉過某一筆揪團
	public ReportcollectVO getOneRepActBymem_no(Integer mem_no,Integer act_no){
  		return dao.getOneRepActBymem_no(mem_no,act_no);
  	}
	//找有無檢舉過某一筆討論區
	public ReportcollectVO getOneRepForumBymem_no(Integer mem_no,Integer forum_no){
  		return dao.getOneRepForumBymem_no(mem_no,forum_no);
  	}
	//找有無檢舉過某一筆日誌
    public ReportcollectVO getOneRepBlogBymem_no(Integer mem_no,Integer blog_no){
    	return dao.getOneRepBlogBymem_no(mem_no,blog_no);
    }
    //找有無檢舉過某一筆旅遊點
    public ReportcollectVO getOneRepTravelBymem_no(Integer mem_no,Integer tra_no){
    	return dao.getOneRepTravelBymem_no(mem_no,tra_no);
    }
    //找有無檢舉過某一筆行程
    public ReportcollectVO getOneRepStrokeBymem_no(Integer mem_no,Integer stroke_no){
    	return dao.getOneRepStrokeBymem_no(mem_no,stroke_no);
    }
    //找有無收藏過某一筆討論區
    public ReportcollectVO getOneColForumBymem_no(Integer mem_no,Integer forum_no){
    	return dao.getOneColForumBymem_no(mem_no,forum_no);
    }
    //找有無收藏過某一筆日誌
    public ReportcollectVO getOneColBlogBymem_no(Integer mem_no,Integer blog_no){
    	return dao.getOneColBlogBymem_no(mem_no,blog_no);
    }
    //找有無收藏過某一筆旅遊點
    public ReportcollectVO getOneColTravelBymem_no(Integer mem_no,Integer tra_no){
    	return dao.getOneColTravelBymem_no(mem_no,tra_no);
    }
    /******討論區會員查詢**********/
	public List<ReportcollectVO> findBymem_no(Integer mem_no) {
		return dao.findBymem_no(mem_no);
	}
	/*****查詢文章******/
	public ReportcollectVO getOneForum(Integer forum_no) {
		return dao.findByforum_no(forum_no);
	}
	/*******查詢討論區會員查詢******/
	public ReportcollectVO getOneForum_mem(Integer forum_no,Integer mem_no) {
		return dao.findByforum_mem(forum_no,mem_no);
	}
	/*******查詢討論區收藏******/
	public ReportcollectVO getOneForum_state(Integer forum_no) {
		return dao.findBystate(forum_no);
	}
	/*******查詢討論區檢舉******/
	public ReportcollectVO getOneForum_handle(Integer forum_no) {
		return dao.findByhandle(forum_no);
	}
	/*******討論區新增收藏******/
	public ReportcollectVO addForum(Integer mem_no,Integer forum_no,Integer rc_rep_handle,Integer rc_col_status,Integer rep_rel){
		ReportcollectVO rcVO =new ReportcollectVO();
		
		rcVO.setMem_no(mem_no);
		rcVO.setForum_no(forum_no);
		rcVO.setRc_rep_handle(rc_rep_handle);
		rcVO.setRc_col_status(rc_col_status);
		rcVO.setRep_rel(rep_rel);
		
		dao.insert_forum(rcVO);
		return rcVO;
	}
	
	/*****檢舉新增********/
	/*****收藏狀態改變******/
	public ReportcollectVO update_state(Integer mem_no,Integer forum_no,Integer rc_rep_handle,Integer rc_col_status,Integer rep_rel,Integer rc_no){
		ReportcollectVO rcVO = new ReportcollectVO();
		rcVO.setMem_no(mem_no);
		rcVO.setForum_no(forum_no);
		rcVO.setRc_rep_handle(rc_rep_handle);
		rcVO.setRc_col_status(rc_col_status);
		rcVO.setRep_rel(rep_rel);
		rcVO.setRc_no(rc_no);
		dao.update_state(rcVO);
		return(rcVO);
	}
	/*****收藏狀態改變二******/
	public ReportcollectVO update_state_A(Integer mem_no,Integer forum_no,Integer rc_rep_handle,Integer rc_col_status,Integer rep_rel,Integer rc_no){
		ReportcollectVO rcVO = new ReportcollectVO();
		rcVO.setMem_no(mem_no);
		rcVO.setForum_no(forum_no);
		rcVO.setRc_rep_handle(rc_rep_handle);
		rcVO.setRc_col_status(rc_col_status);
		rcVO.setRep_rel(rep_rel);
		rcVO.setRc_no(rc_no);
		dao.update_state_A(rcVO);
		return(rcVO);
	}
	public void deleteAct(Integer act_no) {
		dao.deleteByActno(act_no);
	}
	//--0830宣
	public ReportcollectVO checkBlogReport(Integer blog_no,Integer mem_no) {
		return dao.checkBlogReport(blog_no,mem_no);
	}
	
	public ReportcollectVO checkBlogCollect(Integer blog_no,Integer mem_no){
		return dao.checkBlogCollect(blog_no,mem_no);
	}
}
