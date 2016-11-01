package com.travelImage.model;

import java.util.List;

public class TravelImageService{
	private TravelImageDAO_interface dao;
	public TravelImageService(){
		dao = new TravelImageDAO();
	}
	public TravelImageVO addTravelImage(Integer tra_no,byte[] tra_img){
		TravelImageVO travelImageVO = new TravelImageVO();
		travelImageVO.setTra_no(tra_no);
		travelImageVO.setTra_img(tra_img);
		dao.insert(travelImageVO);
		return travelImageVO;
	}
	public TravelImageVO updateTravelImage(TravelImageVO travelImageVO){
		dao.update(travelImageVO);
		return travelImageVO;
	}
	public void deleteTravelImage(Integer tra_img_no){
		dao.delete(tra_img_no);
	}
	public TravelImageVO getOneTravelImage(Integer tra_img_no){
		return dao.findByPrimaryKey(tra_img_no);
	}
	public List<TravelImageVO> getAll(){
		return dao.getAll();
	}
}