package com.strokedetails.model;

import java.sql.Connection;
import java.util.List;

public interface StrokeDetailsDAO_interface {
    public int insert(StrokeDetailsVO strokeDetailsVO,Connection con);
    public int update(StrokeDetailsVO strokeDetailsVO);
    public int delete(Integer stroke_det_no,Connection con);
    public StrokeDetailsVO findByPrimaryKey(Integer stroke_det_no);
    public List<StrokeDetailsVO> getAll();
    public List<StrokeDetailsVO> getAllDetailedItinerary(Integer stroke_no);
    public int selectDays(Integer stroke_no);	//查詢總共幾天
    public List<StrokeDetailsVO> daysItinerary(Integer stroke_no,Integer days);	//天數行程
    //--合
    public List<StrokeDetailsVO> getDayOfTravelsByStrokeNo(Integer stroke_no,Integer stroke_whichday);
    public int getMaxDayByStrokeNo(Integer stroke_no);
    //--合
}
