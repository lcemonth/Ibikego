package com.joinactivity.model;

import java.util.List;
import java.util.Set;

public interface JoinactivityDAO_interface {
	public void insert(JoinactivityVO joinactivityVO);
    public void update(JoinactivityVO joinactivityVO);
    public void delete(Integer act_no,Integer mem_no);
    public List<JoinactivityVO> findActsByMem(Integer mem_no);
    public List<JoinactivityVO> findMemsByAct(Integer act_no);
    public List<JoinactivityVO> getAll();
    public Set<JoinactivityVO> findJoinMemsByActno(Integer act_no);
    public JoinactivityVO findMemIsInAct(Integer act_no,Integer mem_no);
    public void delete(Integer act_no);
    public List<JoinactivityVO> findCnJMemsByAct(Integer act_no);
    public int findCntMemsByAct(Integer act_no);
    public List<JoinactivityVO> findJoinSureMemsByAct(Integer act_no);
    public int findCntNoSureByMem(Integer mem_no);
}
