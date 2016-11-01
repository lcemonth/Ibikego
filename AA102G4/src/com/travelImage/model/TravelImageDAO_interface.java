package com.travelImage.model;

import java.util.*;

public interface TravelImageDAO_interface {
	public void insert(TravelImageVO travelImageVO);
	public void update(TravelImageVO travelImageVO);
    public void delete(Integer tra_img_no);
    public TravelImageVO findByPrimaryKey(Integer tra_img_no);
    public List<TravelImageVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<TravelImageVO> getAll(Map<String, String[]> map);
    public void insertFromTravel(TravelImageVO travelImageVO,java.sql.Connection con);
}