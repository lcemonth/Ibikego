package com.privileged.model;

import java.util.List;


public interface PrivilegedDAO_interface {
    public int insert(PrivilegedVO privilegedVO);
    public int update(PrivilegedVO privilegedVO);
    public int delete(Integer pvl_no);
    public List<PrivilegedVO> findByPrimaryKey(Integer emp_no);
    public List<PrivilegedVO> getAll();
    
    public List getEmpPrivileged(Integer emp_no);
    public int insert(Integer emp_no,String[] pvl_no);
}
