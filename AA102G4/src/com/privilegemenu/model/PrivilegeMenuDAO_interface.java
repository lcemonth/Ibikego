package com.privilegemenu.model;

import java.util.List;


public interface PrivilegeMenuDAO_interface {
    public int insert(PrivilegeMenuVO privilegeMenuVO);
    public int update(PrivilegeMenuVO privilegeMenuVO);
    public int delete(Integer pvl_no);
    public List<PrivilegeMenuVO> findByPrimaryKey(Integer pvl_no);
    public List<PrivilegeMenuVO> getAll();
}
