package com.travel.model;

import java.util.List;

import com.travelImage.model.TravelImageVO;

public class TravelService{
	private TravelDAO_interface dao;
	public TravelService(){
		dao = new TravelDAO();
	}
	public TravelVO addTravel(TravelVO travelVO){
		dao.insert(travelVO);
		return travelVO;
	}
	public TravelVO addTravelWithImages(TravelVO travelVO,List<TravelImageVO> images){
		dao.insertWithImages(travelVO,images);
		return travelVO;
	}
	public TravelVO updateTravel(TravelVO travelVO){
		dao.update(travelVO);
		return travelVO;
	}
	public TravelVO addTravel(Integer mem_no,Integer loc_no,Integer tra_class_status,String tra_name,String tra_content,String tra_tel,String tra_add,java.sql.Date tra_cre,Double tra_lati,Double tra_longi){
		TravelVO travelVO = new TravelVO();
		travelVO.setMem_no(mem_no);
		travelVO.setLoc_no(loc_no);
		travelVO.setTra_class_status(tra_class_status);
		travelVO.setTra_name(tra_name);
		travelVO.setTra_content(tra_content);
		travelVO.setTra_tel(tra_tel);
		travelVO.setTra_add(tra_add);
		travelVO.setTra_cre(tra_cre);
		travelVO.setTra_lati(tra_lati);
		travelVO.setTra_longi(tra_longi);
		dao.insert(travelVO);
		return travelVO;
	}
	public TravelVO updateTravel(Integer tra_no,Integer mem_no,Integer loc_no,Integer tra_class_status,String tra_name,String tra_content,String tra_tel,String tra_add,java.sql.Date tra_cre,Double tra_lati,Double tra_longi){
		TravelVO travelVO = new TravelVO();
		travelVO.setTra_no(tra_no);
		travelVO.setMem_no(mem_no);
		travelVO.setLoc_no(loc_no);
		travelVO.setTra_class_status(tra_class_status);
		travelVO.setTra_name(tra_name);
		travelVO.setTra_content(tra_content);
		travelVO.setTra_tel(tra_tel);
		travelVO.setTra_add(tra_add);
		travelVO.setTra_cre(tra_cre);
		travelVO.setTra_lati(tra_lati);
		travelVO.setTra_longi(tra_longi);
		dao.update(travelVO);
		return travelVO;
	}
	public void deleteTravel(Integer tra_no){
		dao.delete(tra_no);
	}
	public TravelVO getOneTravel(Integer tra_no){
		return dao.findByPrimaryKey(tra_no);
	}
	public List<TravelVO> searchAttractions() {
		return dao.searchAttractions();
	}
	public List<TravelVO> searchBreak() {
		return dao.searchBreak();
	}
	public List<TravelVO> getAll() {
		return dao.getAll();
	}
	/******************************0827********************************/
	public List<TravelVO> getMyTravelPoints(Integer mem_no) {
		return dao.getMyTravelPoints(mem_no);
	}
	/******************************0827********************************/
	//--合
	public void updateDel(Integer tra_no){
		dao.updateDel(tra_no);
	}
	//--合
	/*建宇新增-查詢旅遊點類別*/
	public List<TravelVO> getAll(Integer information) {
		return dao.getAll(information);
	}
	/*建宇結束*/
}