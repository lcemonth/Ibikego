package com.queryact.model;

import java.util.List;
import java.util.Set;


public class QueryactService {
	private QueryactDAO_interface dao;
	public QueryactService(){
		dao=new QueryactDAO();
	}
	
	
	public List<QueryactVO> getOneNewAct(Integer act_no) {
		return dao.getActJoinDetail(act_no);
	}
	
}
