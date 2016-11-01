package com.forum.model;


import com.blogScore.model.BlogScoreVO;
import com.emp.model.EmpVO;
import com.forumresponse.model.ForumresVO;
import com.reportcollect.model.ReportcollectVO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.blog.model.BlogVO;
public class ForumService {

	private ForumDAO_interface dao;

	public ForumService() {
		dao = new ForumDAO();
	}

	public ForumVO addForum(Integer mem_no, String forum_title,String forum_content, java.sql.Date forum_cretime,
			Integer forum_del) {

		ForumVO forumVO = new ForumVO();
		
		forumVO.setMem_no(mem_no);
		forumVO.setForum_title(forum_title);
		forumVO.setForum_content(forum_content);
		forumVO.setForum_cretime(forum_cretime);
		forumVO.setForum_del(forum_del);
		dao.insert(forumVO);

		return forumVO;
	}
	
	public ReportcollectVO add_handle(Integer mem_no, Integer forum_no,Integer rc_rep_handle,Integer rc_col_status,Integer rep_rel,String rep_content) {

		ReportcollectVO rcVO = new ReportcollectVO();
		
		rcVO.setMem_no(mem_no);
		rcVO.setForum_no(forum_no);
		rcVO.setRc_rep_handle(rc_rep_handle);
		rcVO.setRc_col_status(rc_col_status);
		rcVO.setRep_rel(rep_rel);
		rcVO.setRep_content(rep_content);
		dao.insert_handle(rcVO);

		return rcVO;
	}
	
	
	public ForumVO updateForum( String forum_title,String forum_content,Integer forum_no,java.sql.Date forum_cretime
			) {

		ForumVO forumVO = new ForumVO();
		
		forumVO.setForum_title(forum_title);
		forumVO.setForum_content(forum_content);
		forumVO.setForum_no(forum_no);
		forumVO.setForum_cretime(forum_cretime);
		dao.update(forumVO);

		return forumVO;
	}
	
//	public Set<ForumresVO> getforumresByforum_no(Integer forum_no) {
//		return dao.getforumresByforum_no(forum_no);
//	}
	public List<BlogScoreVO> getAll_bsnew() {
		return dao. getAll_bsnew();
	}
	public List<ForumVO> getAll_new(){
		return dao.getAll_new();
	}
	
	public void delete_memForum(Integer froum_no) {
		dao.delete_mem(froum_no);
	}

	public ForumVO getOneForum(Integer forum_no) {
		return dao.findByPrimaryKey(forum_no);
	}
	
	public List<ForumVO> mem_getAll(){
		return dao.mem_getAll();
	}
	
	public List<ForumVO> getAll() {
		return dao.getAll();
	}
	
	public List<ForumVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	/**8/29新增**/
	public List<BlogVO> getAll_newblog() {
		return dao. getAll_newblog();
	}
	/**8/29新增**/
	/**8/31會員討論區查詢管理**/
	public List<ForumVO> getOneMem(Integer mem_no) {
		return dao.findByMem(mem_no);
	}
	//---------合---------
	public void deleteForum(Integer froum_no) {
		dao.delete(froum_no);
	}
	//---------合---------
	/**9/4新增**/
	public List<ForumVO> getForum_title(String forum_title) {	// 查詢 標題
		return dao.getForum_title(forum_title);
	}
	/**9/4新增**/
}
