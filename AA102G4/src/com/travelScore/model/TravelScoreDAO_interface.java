package com.travelScore.model;

import java.util.List;

public interface TravelScoreDAO_interface {
	public void insert(TravelScoreVO travelScoreVO);
    public TravelScoreVO findByPrimaryKey(Integer tra_no);
    public TravelScoreVO getOneTravelScore(Integer tra_no);
    public TravelScoreVO getCheck(Integer tra_no,Integer mem_no);
    public List<TravelScoreVO> getOneTravel(Integer tra_no);
    public List<TravelScoreVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<TravelScoreVO> getAll(Map<String, String[]> map); 
}