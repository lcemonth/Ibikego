package com.privilegemenu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.privileged.model.PrivilegedVO;

public class PrivilegeMenuDAO implements PrivilegeMenuDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ALL_STMT ="select pvl_no,pvl_detail from PrivilegeMenu order by pvl_no";
	@Override
	public int insert(PrivilegeMenuVO privilegeMenuVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(PrivilegeMenuVO privilegeMenuVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(Integer pvl_no) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<PrivilegeMenuVO> findByPrimaryKey(Integer pvl_no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PrivilegeMenuVO> getAll() {
		List<PrivilegeMenuVO> list = new ArrayList<PrivilegeMenuVO>();
		PrivilegeMenuVO privilegeMenuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				privilegeMenuVO = new PrivilegeMenuVO();
				privilegeMenuVO.setPvl_no(rs.getInt("pvl_no"));
				privilegeMenuVO.setPvl_detail(rs.getString("Pvl_detail"));
				list.add(privilegeMenuVO); 
			}
			// Handle any driver errors
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
	
	
}
