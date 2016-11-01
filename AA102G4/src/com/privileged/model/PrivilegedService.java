package com.privileged.model;

import java.util.List;


public class PrivilegedService {
	private PrivilegedDAO_interface dao;
	public PrivilegedService(){
		dao = new PrivilegedDAO();
	}
	public List<PrivilegedVO> findByPrimaryKey(Integer emp_no){
		
		return dao.findByPrimaryKey(emp_no);
	}
	public List getEmpPrivileged(Integer emp_no){	//查出會員權限
		
		return dao.getEmpPrivileged(emp_no);
	}
	public int insert(Integer emp_no,String[] pvl_no){	//查出會員權限
		
		return dao.insert(emp_no,pvl_no);
	}
	public List<PrivilegedVO> getAll(){
		return dao.getAll();
	}
	
	
//	public EmpVO getOneEmpAcc(String emp_acc) {	//登入 員工帳號比對
//		return dao.getOneEmpAcc(emp_acc);
//	}
	
}
