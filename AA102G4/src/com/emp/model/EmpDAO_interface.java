package com.emp.model;

import java.util.List;

public interface EmpDAO_interface {
          public int insert(EmpVO empVO);
          public int update(EmpVO empVO);
          public int delete(Integer emp_no);
          public EmpVO findByPrimaryKey(Integer emp_no);
          public EmpVO getOneEmpAcc(String emp_acc);	//員工登入 取得帳號比對
          public List<EmpVO> getEmpName(String emp_name);
          public List<EmpVO> getAll();
}
