package com.travel.model;

import java.util.*;

import com.travelImage.model.TravelImageVO;

public interface TravelDAO_interface {
	public void insert(TravelVO travelVO);
	public void insertWithImages(TravelVO travelVO,List<TravelImageVO> list);
    public void update(TravelVO travelVO);
    public void delete(Integer tra_no);
    public TravelVO findByPrimaryKey(Integer tra_no);
    public List<TravelVO> searchAttractions();
    public List<TravelVO> searchBreak();
    public List<TravelVO> getAll();
    /**************************0827*****************************/
    public List<TravelVO> getMyTravelPoints(Integer mem_no);
    //--合
    public void updateDel(Integer tra_no);
    //--合
    /*建宇新增-查詢旅遊點類型*/
    public List<TravelVO> getAll(Integer tra_class_status);
    /*建宇結束*/
}