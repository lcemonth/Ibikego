package com.location.model;

import java.util.List;

import com.location.model.LocationVO;
import com.activity.model.ActivityVO;


public class LocationService {
	
	private LocationDAO_interface dao;

	public LocationService() {
		dao = new LocationDAO();
	}
	
	public LocationVO addLocation(Integer loc_no, String loc_name) {

		LocationVO LocationVO = new LocationVO();

		LocationVO.setLoc_no(loc_no);
		LocationVO.setLoc_name(loc_name);
		dao.insert(LocationVO);

		return LocationVO;
	}
	
	public LocationVO updateLocation(Integer loc_no, String loc_name) {

		LocationVO LocationVO = new LocationVO();

		LocationVO.setLoc_no(loc_no);
		LocationVO.setLoc_name(loc_name);
		dao.update(LocationVO);

		return LocationVO;
	}
	
	public void deleteLocation(Integer loc_no) {
		dao.delete(loc_no);
	}

	public LocationVO getOneLocation(Integer loc_no) {
		return dao.findByPrimaryKey(loc_no);
	}

	public List<LocationVO> getAll() {
		return dao.getAll();
	}
	
	public List<ActivityVO> getActivityByLoc_no(Integer loc_no){
  		return dao.getActivityByLoc_no(loc_no);
  	}
	public LocationVO getLngNLatByLoc_no(Integer loc_no){
		return dao.findLngNLatByLoc_no(loc_no);
  	}
	
}
