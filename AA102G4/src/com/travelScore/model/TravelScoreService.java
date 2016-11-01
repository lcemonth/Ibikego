package com.travelScore.model;

import java.util.List;

public class TravelScoreService{
	private TravelScoreDAO_interface dao;
	public TravelScoreService(){
		dao = new TravelScoreDAO();
	}
	public TravelScoreVO addTravelScore(Integer tra_no,Integer mem_no,Integer tra_score,Integer tra_score_status){
		TravelScoreVO travelScoreVO = new TravelScoreVO();
		travelScoreVO.setTra_no(tra_no);
		travelScoreVO.setMem_no(mem_no);
		travelScoreVO.setTra_score(tra_score);
		travelScoreVO.setTra_score_status(tra_score_status);
		dao.insert(travelScoreVO);
		return travelScoreVO;
	}
	public TravelScoreVO findOne(Integer tra_no){
		return dao.findByPrimaryKey(tra_no);
	}
	public TravelScoreVO getCheck(Integer tra_no,Integer mem_no){
		return dao.getCheck(tra_no,mem_no);
	}
	public TravelScoreVO getOneTravelScore(Integer tra_no){
		return dao.getOneTravelScore(tra_no);
	}
	public List<TravelScoreVO> getOneTravel(Integer tra_no){
		return dao.getOneTravel(tra_no);
	}
	public List<TravelScoreVO> getAll(){
		return dao.getAll();
	}
}