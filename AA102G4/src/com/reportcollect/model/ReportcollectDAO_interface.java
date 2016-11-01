package com.reportcollect.model;

import java.util.*;

import com.reportcollect.model.ReportcollectVO;

public interface ReportcollectDAO_interface {
	public void insert(ReportcollectVO rcVO);
    public void update(ReportcollectVO rcVO);
    public void delete(Integer rc_no);
    public ReportcollectVO findByPrimaryKey(Integer rc_no);
    public ReportcollectVO checkTravelReport(Integer tra_no,Integer mem_no);
    public ReportcollectVO checkTravelCollect(Integer tra_no,Integer mem_no);
    public List<ReportcollectVO> getAll();
    public List<ReportcollectVO> getAllrep();
    public List<ReportcollectVO> getReportsBymem_no(Integer mem_no,Integer rep_rel);
    public List<ReportcollectVO> onePoint(Integer tra_no);
    public ReportcollectVO getOneRepActBymem_no(Integer mem_no,Integer act_no);
    public ReportcollectVO getOneRepForumBymem_no(Integer mem_no,Integer forum_no);
    public ReportcollectVO getOneRepBlogBymem_no(Integer mem_no,Integer blog_no);
    public ReportcollectVO getOneRepTravelBymem_no(Integer mem_no,Integer tra_no);
    public ReportcollectVO getOneRepStrokeBymem_no(Integer mem_no,Integer stroke_no);
    public ReportcollectVO getOneColForumBymem_no(Integer mem_no,Integer forum_no);
    public ReportcollectVO getOneColBlogBymem_no(Integer mem_no,Integer blog_no);
    public ReportcollectVO getOneColTravelBymem_no(Integer mem_no,Integer tra_no);
    
    /******討論區會員查詢**********/
    public List<ReportcollectVO> findBymem_no(Integer mem_no);
//    public void insert_forum(ReportcollectVO rcVO);
    public ReportcollectVO findByforum_no(Integer forum_no);
    /******查詢新增會員檢舉**********/
    public ReportcollectVO findByforum_mem(Integer forum_no,Integer mem_no);
    /******查詢有無收藏**********/
    public ReportcollectVO findBystate(Integer forum_no);
    /******查詢有無檢舉**********/
    public ReportcollectVO findByhandle(Integer forum_no);
//    public ReportcollectVO findBystate(Integer forum_no,Integer mem_no);
    /******收藏更新**********/
    public void update_state(ReportcollectVO rcVO);
    /******收藏更新**********/
    public void update_state_A(ReportcollectVO rcVO);
    /***收藏狀態新增*****/
    public void insert_forum(ReportcollectVO rcVO);
    
    /******討論區會員**********/
    public void deleteByActno(Integer act_no);
    //0830宣
    public ReportcollectVO checkBlogReport(Integer blog_no,Integer mem_no);
    public ReportcollectVO checkBlogCollect(Integer blog_no,Integer mem_no);
}
