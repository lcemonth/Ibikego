package com.stroke.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class StrokeService {
	private StrokeDAO_interface dao;
	public StrokeService(){
		dao=new StrokeDAO();
	}
	public StrokeVO addStroke(String stroke_name,Integer mem_no,Date buildDate,Map strokeList) {
		StrokeVO strokeVO=new StrokeVO();
			strokeVO.setStroke_name(stroke_name);
			strokeVO.setMem_no(mem_no);
			strokeVO.setBuildDate(buildDate);
		dao.insert(strokeVO,strokeList);
		return strokeVO;
	}
	public List<StrokeVO> getAll() {
		return dao.getAll();
	}
	public List<StrokeVO> getStrokesByMem_no(Integer mem_no) {
		return dao.findStrokesByMem_no(mem_no);
	}
	public StrokeVO getOneStroke(Integer stroke_no){
		 return dao.findByPrimaryKey(stroke_no);
	}
	public void deleteStroke(Integer stroke_no) {
		dao.delete(stroke_no);
	}
	public List<StrokeVO> getMemberStrokeAll(Integer mem_no){
		return dao.getMemberStrokeAll(mem_no);
	}
}
