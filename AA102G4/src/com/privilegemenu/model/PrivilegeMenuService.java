package com.privilegemenu.model;

import java.util.List;


public class PrivilegeMenuService {
	private PrivilegeMenuDAO_interface dao;
	public PrivilegeMenuService(){
		dao = new PrivilegeMenuDAO();
	}
	public List<PrivilegeMenuVO> getAll(){
		return dao.getAll();
	}
}
