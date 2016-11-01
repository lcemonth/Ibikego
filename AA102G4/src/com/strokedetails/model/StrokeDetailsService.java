package com.strokedetails.model;

import java.util.List;

public class StrokeDetailsService {
	private StrokeDetailsDAO_interface dao;
	public StrokeDetailsService(){
		dao=new StrokeDetailsDAO();
	}
	public int Days(Integer stroke_no){
		
		return dao.selectDays(stroke_no);
	}
	public List<StrokeDetailsVO> DaysItinerary(Integer stroke_no,Integer days){
		
		return dao.daysItinerary(stroke_no, days);
	}
	public List<StrokeDetailsVO> GetAllDetailedItinerary (Integer stroke_no){
		return dao.getAllDetailedItinerary(stroke_no);
	}
	//----------合---------
	public int getMaxDayByStrokeNo(Integer stroke_no) {
		return dao.getMaxDayByStrokeNo(stroke_no);
	}
	//----------合---------
}
