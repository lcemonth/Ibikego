package com.queryact.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QueryactDAO implements QueryactDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ACT_JOIN_DETAIL = 
			
	"SELECT a.act_no,a.mem_no,a.act_name,a.act_loc,a.act_start_date,a.act_end_date,a.act_photo,a.act_is_public,"
	+" a.act_joinlimit,m.mem_name,m.mem_phone,m.mem_email,m.mem_photo,s.stroke_no,s.stroke_name"
	+" FROM activity a, stroke s, strokedetails sd, travel t,member m"
	+" WHERE a.stroke_no = s.stroke_no"
	+" AND s.stroke_no = sd.stroke_no"
	+" AND a.mem_no=m.mem_no"
	+" AND act_no=?";

@Override
public List<QueryactVO> getActJoinDetail(Integer act_no) {
		List<QueryactVO> list=new ArrayList<QueryactVO>();
		QueryactVO queryActVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ACT_JOIN_DETAIL);
			pstmt.setInt(1, act_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				queryActVO=new QueryactVO();
				queryActVO.setAct_no(rs.getInt("act_no"));
				queryActVO.setMem_no(rs.getInt("mem_no"));
				queryActVO.setMem_name(rs.getString("mem_name"));
				queryActVO.setMem_phone(rs.getString("mem_phone"));
				queryActVO.setMem_email(rs.getString("mem_email"));
				queryActVO.setMem_photo(rs.getBytes("mem_photo"));
				queryActVO.setAct_name(rs.getString("act_name"));
				queryActVO.setAct_loc(rs.getString("act_loc"));
				queryActVO.setAct_start_date(rs.getDate("act_start_date"));
				queryActVO.setAct_end_date(rs.getDate("act_end_date"));
				queryActVO.setAct_photo(rs.getBytes("act_photo"));
				queryActVO.setAct_is_public(rs.getInt("act_is_public"));
				queryActVO.setAct_joinlimit(rs.getInt("act_joinlimit"));
				queryActVO.setStroke_no(rs.getInt("stroke_no"));
				queryActVO.setStroke_name(rs.getString("stroke_name"));
				queryActVO.setTra_no(rs.getInt("tra_no"));
				queryActVO.setStroke_whichday(rs.getInt("stroke_whichday"));
				list.add(queryActVO);
			}
		}
		catch (SQLException se) {
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
