package com.emp.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA102G4";
	String passwd ="0000";

	private static final String INSERT_STMT = 
		"INSERT INTO EMPLOYEE (emp_no,emp_acc,emp_pw,emp_name,emp_tel,emp_phone,emp_ps,emp_hire,emp_over,emp_alive) VALUES (EMPLOYEE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT emp_no,emp_acc,emp_pw,emp_name,emp_tel,emp_phone,emp_ps,to_char(emp_hire,'yyyy-mm-dd') emp_hire,to_char(emp_over,'yyyy-mm-dd') emp_over,emp_alive FROM EMPLOYEE order by emp_no";
	private static final String GET_ONE_STMT = 
		"SELECT emp_no,emp_acc,emp_pw,emp_name,emp_tel,emp_phone,emp_ps,to_char(emp_hire,'yyyy-mm-dd') emp_hire,to_char(emp_over,'yyyy-mm-dd') emp_over,emp_alive FROM EMPLOYEE where emp_no = ?";
	private static final String GET_ONE_EMP_ACC = 
			"SELECT emp_no,emp_acc,emp_pw,emp_name,emp_tel,emp_phone,emp_ps,to_char(emp_hire,'yyyy-mm-dd') emp_hire,to_char(emp_over,'yyyy-mm-dd') emp_over,emp_alive FROM EMPLOYEE where emp_acc = ?";
	private static final String DELETE = 
		"DELETE FROM EMPLOYEE where emp_no = ?";
	private static final String UPDATE = 
		"UPDATE EMPLOYEE set emp_no=?,emp_acc=?,emp_pw=?,emp_name=?,emp_tel=?,emp_phone=?,emp_ps=?,emp_hire=?,emp_over=?,emp_alive=? where emp_no = ?";

	@Override
	public int insert(EmpVO empVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, empVO.getEmp_acc());
				pstmt.setString(2, empVO.getEmp_pw());
				pstmt.setString(3, empVO.getEmp_name());
				pstmt.setString(4, empVO.getEmp_tel());
				pstmt.setString(5, empVO.getEmp_phone());
				pstmt.setString(6, empVO.getEmp_ps());
				pstmt.setDate(7, empVO.getEmp_hire());
				pstmt.setDate(8, empVO.getEmp_over());
				pstmt.setInt(9, empVO.getEmp_alive());
			updateCount = pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int update(EmpVO empVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, empVO.getEmp_no());
			pstmt.setString(2, empVO.getEmp_acc());
			pstmt.setString(3, empVO.getEmp_pw());
			pstmt.setString(4, empVO.getEmp_name());
			pstmt.setString(5, empVO.getEmp_tel());
			pstmt.setString(6, empVO.getEmp_phone());
			pstmt.setString(7, empVO.getEmp_ps());
			pstmt.setDate(8, empVO.getEmp_hire());
			pstmt.setDate(9, empVO.getEmp_over());
			pstmt.setInt(10, empVO.getEmp_alive());
			pstmt.setInt(11,empVO.getEmp_no());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int delete(Integer emp_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, emp_no);
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public EmpVO findByPrimaryKey(Integer emp_no) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, emp_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				
				empVO.setEmp_no(rs.getInt("emp_no"));
				empVO.setEmp_acc(rs.getString("emp_acc"));
				empVO.setEmp_pw(rs.getString("emp_pw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_ps(rs.getString("emp_ps"));
				empVO.setEmp_hire(rs.getDate("emp_hire"));
				empVO.setEmp_over(rs.getDate("emp_over"));
				empVO.setEmp_alive(rs.getInt("emp_alive"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empVO;
	}
	@Override
	public EmpVO getOneEmpAcc(String emp_acc) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_EMP_ACC);
			
			pstmt.setString(1, emp_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_no(rs.getInt("emp_no"));
				empVO.setEmp_acc(rs.getString("emp_acc"));
				empVO.setEmp_pw(rs.getString("emp_pw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_ps(rs.getString("emp_ps"));
				empVO.setEmp_hire(rs.getDate("emp_hire"));
				empVO.setEmp_over(rs.getDate("emp_over"));
				empVO.setEmp_alive(rs.getInt("emp_alive"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empVO;
	}	

	@Override
	public List<EmpVO> getEmpName(String emp_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
					empVO.setEmp_no(rs.getInt("emp_no"));
					empVO.setEmp_acc(rs.getString("emp_acc"));
					empVO.setEmp_pw(rs.getString("emp_pw"));
					empVO.setEmp_name(rs.getString("emp_name"));
					empVO.setEmp_phone(rs.getString("emp_phone"));
					empVO.setEmp_ps(rs.getString("emp_ps"));
					empVO.setEmp_hire(rs.getDate("emp_hire"));
					empVO.setEmp_over(rs.getDate("emp_over"));
					empVO.setEmp_alive(rs.getInt("emp_alive"));
				list.add(empVO); 
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
//	public static void main(String[] args) {
//
//		EmpJDBCDAO dao = new EmpJDBCDAO();
		 //新增
//		for (int i = 20; i <= 40; i++) {
//			 EmpVO empvo = new EmpVO();
//			 empvo.setEmp_acc("toppyz");
//			 empvo.setEmp_pw("0000");
//			 empvo.setEmp_name("小");
//			 empvo.setEmp_phone("09341730");
//			 empvo.setEmp_ps("熟悉msSql");
//			 empvo.setEmp_hire(java.sql.Date.valueOf("2015-05-16"));
//			 
//			 int updateCount_insert = dao.insert(empvo);
//			 System.out.println(updateCount_insert);
//		}


		
		//修改
//		EmpVO empvo =new EmpVO();
//		
//		empvo.setEmp_no(155);
//		empvo.setEmp_acc("wwww");
//		empvo.setEmp_pw("eeee");
//		empvo.setEmp_name("小智");
//		empvo.setEmp_tel("034886548");
//		empvo.setEmp_phone("09341730107");
//		empvo.setEmp_ps("不熟悉msSql");
//		empvo.setEmp_hire(java.sql.Date.valueOf("2016-07-16"));
//		empvo.setEmp_alive(1);
//		int updateCount=dao.update(empvo);
//		System.out.println(updateCount);
		
		//刪除
//		int updateCount=dao.delete(155);
//		System.out.println(updateCount);
		
		//單筆查詢
//		EmpVO empvo = dao.findByPrimaryKey(112);
//		System.out.print(empvo.getEmp_no() + ",");
//		System.out.print(empvo.getEmp_acc() + ",");
//		System.out.print(empvo.getEmp_pw() + ",");
//		System.out.print(empvo.getEmp_name() + ",");
//		System.out.print(empvo.getEmp_ps());
//		System.out.println("---------------------");
		
//		List<EmpVO> list =dao.getAll();
//		for (EmpVO empvo : list) {
//			System.out.println(empvo.getEmp_no());
//			System.out.println(empvo.getEmp_acc());
//			System.out.println(empvo.getEmp_pw());
//			System.out.println(empvo.getEmp_name());
//			System.out.println(empvo.getEmp_ps());
//			System.out.println(empvo.getEmp_tel());
//			System.out.println(empvo.getEmp_phone());
//			System.out.println(empvo.getEmp_hire());
//			System.out.println(empvo.getEmp_over());
//			System.out.println(empvo.getEmp_alive());
//		}
		
		
//	}
}