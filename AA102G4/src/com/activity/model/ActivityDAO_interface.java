package com.activity.model;

import java.util.List;
import java.util.Map;

import com.emp.model.EmpVO;



public interface ActivityDAO_interface {
	public void insert(ActivityVO activityVO);
    public void update(ActivityVO activityVO);
    public void updateNoStroke(ActivityVO activityVO);
    public void delete(Integer act_no);
    public void insertNoStroke(ActivityVO activityVO);
    public ActivityVO findByPrimaryKey(Integer act_no);
    public List<ActivityVO> getAll();
    public List<ActivityVO> getAllActRecent();
    public ActivityVO findActByMem_no(Integer mem_no,Integer act_no);
    public List<ActivityVO> findMemJoinedActs(Integer mem_no);
    public List<ActivityVO> findAddActsByMemno(Integer mem_no);
    public void updatePublic(Integer act_is_public,Integer mem_no,Integer act_no);
    public int chkActDate(String in_start,String in_end,Integer mem_no);
    public int chkJoinActDate(String in_start,String in_end,Integer mem_no,Integer act_no);
    public List<ActivityVO> getAll(Map<String, String[]> map); 
    public List<ActivityVO> findMemJoinedActs(Map<String, String[]> map);
    public List<ActivityVO> findMemInvitedActs(Integer mem_no);
    public List<ActivityVO> findTopActs(Integer top);
}
