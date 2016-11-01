package com.reportcollect.model;

import java.io.ByteArrayInputStream;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tools.TurnByte;


public class ReportcollectDAO implements ReportcollectDAO_interface{
	
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO reportcollect (rc_no, mem_no, tra_no, act_no, forum_no, blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content)"+
	 " VALUES (reportcollect_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE reportcollect set mem_no=?, tra_no=?, act_no=?, forum_no=?, blog_no=?, stroke_no=? ,"+
	" rc_rep_handle= ?, rc_col_status= ?, rep_rel= ? , rep_content= ? where rc_no = ?";
	private static final String DELETE = 
			"DELETE FROM reportcollect where rc_no = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM reportcollect order by rc_no desc";
	private static final String ONEPOINT = 
			"SELECT * FROM reportcollect where tra_no = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM reportcollect where rc_no = ?";
	private static final String GET_REPORTS_BY_MEM = 
			"SELECT * FROM reportcollect where mem_no = ? and rep_rel= ?";
	private static final String GET_ALL_REP = 
			"SELECT * FROM reportcollect where rep_rel=0 order by rc_no desc";
	private static final String GET_ONE_REPACT_BY_MEM_NO = 
			"select * from reportcollect where mem_no=? and act_no=? and rep_rel=0";
	private static final String GET_ONE_REPFORUM_BY_MEM_NO = 
			"select * from reportcollect where mem_no=? and forum_no=? and rep_rel=0";
	private static final String GET_ONE_REPTRAVEL_BY_MEM_NO = 
			"select * from reportcollect where mem_no=? and tra_no=? and rep_rel=0";
	private static final String GET_ONE_REPSTROKE_BY_MEM_NO = 
			"select * from reportcollect where mem_no=? and stroke_no=? and rep_rel=0";
	private static final String GET_ONE_REPBLOG_BY_MEM_NO = 
			"select * from reportcollect where mem_no=? and blog_no=? and rep_rel=0";
	private static final String GET_ONE_COLFORUM_BY_MEM_NO = 
			"select * from reportcollect where mem_no=? and forum_no=? and rep_rel=1";
	private static final String GET_ONE_COLTRAVEL_BY_MEM_NO = 
			"select * from reportcollect where mem_no=? and tra_no=? and rep_rel=1";
	private static final String GET_ONE_COLBLOG_BY_MEM_NO = 
			"select * from reportcollect where mem_no=? and blog_no=? and rep_rel=1";
	private static final String checkTravelReport = 
			"SELECT * FROM reportcollect where tra_no = ? and mem_no = ? and rep_rel = 0";
	private static final String checkTravelCollect = 
			"SELECT * FROM reportcollect where tra_no = ? and mem_no = ? and rep_rel = 1";
	private static final String DELETE_BY_ACTNO = 
			"delete reportcollect where act_no=?";
	//--世麒 /***新增收藏or檢舉*****/
	/******討論區會員查詢**********/
	private static final String GET_ALL_FORUM = 
			"SELECT * FROM reportcollect where mem_no = ? and rc_col_status = 0 ";
	/******討論區單一查詢**********/
	private static final String GET_ONE_FORUM = 
			"SELECT * FROM reportcollect where forum_no = ?";
	/******討論區收藏修改**********/
	private static final String UPDATE_RC_STATUS_A  = 
			"UPDATE reportcollect set mem_no= ? , forum_no = ? , rc_rep_handle = ? , rc_col_status = ? , rep_rel = ?  where rc_no = ?";
	/******討論區收藏修改**********/
	private static final String UPDATE_RC_STATUS  = 
			"UPDATE reportcollect set mem_no= ? , forum_no = ? , rc_rep_handle = ? , rc_col_status = ? , rep_rel = ?  where rc_no = ?";
	/******討論區收藏新增**********/
	private static final String INSERT_RC_STATUS = 
			"INSERT INTO ReportCollect (rc_no,mem_no,forum_no,rc_rep_handle,rc_col_status,rep_rel) VALUES (reportcollect_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	/******討論區收藏查詢**********/
	private static final String GET_ONE_STATE = 
			"SELECT rc_no,mem_no,forum_no,rc_rep_handle,rc_col_status,rep_rel,rep_content FROM reportcollect where  forum_no = ? and rep_rel = 1";
	/******檢舉查詢收藏查詢**********/
	private static final String GET_ONE_HANDLE = 
			"SELECT mem_no,forum_no,rc_rep_handle,rc_col_status,rep_rel,rep_content FROM reportcollect where  forum_no = ? and rep_rel = 0";
	/******討論區再度確認查詢**********/
	private static final String GET_Two_CHECK = 
			"SELECT mem_no,forum_no,rc_rep_handle,rc_col_status,rep_rel,rep_content FROM reportcollect where mem_no =? and forum_no = ? and rep_rel = 0";
	//--世麒 /***新增收藏or檢舉*****/
	
	//---0830宣----
	private static final String checkBlogReport = 
			"SELECT * FROM reportcollect where blog_no = ? and mem_no = ? and rep_rel = 0";
	private static final String checkBlogCollect = 
			"SELECT * FROM reportcollect where blog_no = ? and mem_no = ? and rep_rel = 1";
	
	@Override
	public void insert(ReportcollectVO rcVO) {
		Connection con=null;
		
		PreparedStatement pstmt=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, rcVO.getMem_no());
			
			if(rcVO.getTra_no()==null){
				pstmt.setNull(2, java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(2, rcVO.getTra_no());
			}
			if(rcVO.getAct_no()==null){
				pstmt.setNull(3, java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(3, rcVO.getAct_no());
			}
			if(rcVO.getForum_no()==null){
				pstmt.setNull(4,java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(4, rcVO.getForum_no());
			}
			if(rcVO.getBlog_no()==null){
				pstmt.setNull(5, java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(5, rcVO.getBlog_no());
			}
			if(rcVO.getStroke_no()==null){
				pstmt.setNull(6, java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(6, rcVO.getStroke_no ());
			}
			
			pstmt.setInt(7, rcVO.getRc_rep_handle());
			pstmt.setInt(8, rcVO.getRc_col_status());
			pstmt.setInt(9, rcVO.getRep_rel());
			pstmt.setString(10, rcVO.getRep_content());
			pstmt.execute();
		
		}
		
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		}
		catch(Exception e){
			e.printStackTrace(); 
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
		
		
	}
	


	@Override
	public void update(ReportcollectVO rcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rcVO.getMem_no());
			
			if(rcVO.getTra_no()==null){
				pstmt.setNull(2, java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(2, rcVO.getTra_no());
			}
			if(rcVO.getAct_no()==null){
				pstmt.setNull(3, java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(3, rcVO.getAct_no());
			}
			if(rcVO.getForum_no()==null){
				pstmt.setNull(4,java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(4, rcVO.getForum_no());
			}
			if(rcVO.getBlog_no()==null){
				pstmt.setNull(5, java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(5, rcVO.getBlog_no());
			}
			if(rcVO.getStroke_no()==null){
				pstmt.setNull(6, java.sql.Types.INTEGER);
			}
			else{
				pstmt.setInt(6, rcVO.getStroke_no ());
			}
			
			pstmt.setInt(7, rcVO.getRc_rep_handle());
			pstmt.setInt(8, rcVO.getRc_col_status());
			pstmt.setInt(9, rcVO.getRep_rel());
			pstmt.setString(10, rcVO.getRep_content());
			pstmt.setInt(11, rcVO.getRc_no());
			
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
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

	}
		
	

	@Override
	public void delete(Integer rc_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rc_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
		
	}

	@Override
	public ReportcollectVO findByPrimaryKey(Integer rc_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
				
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
		return rcVO;
	}

	@Override
	public List<ReportcollectVO> getAll() {
		
		List<ReportcollectVO> list = new ArrayList<ReportcollectVO>();
		ReportcollectVO rcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
				
				list.add(rcVO); // Store the row in the list
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
	
	@Override	
	public List<ReportcollectVO> getReportsBymem_no(Integer mem_no,Integer rep_rel) {
		
		List<ReportcollectVO> list = new ArrayList<ReportcollectVO>();
		ReportcollectVO rcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_REPORTS_BY_MEM);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, rep_rel);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));

				list.add(rcVO); // Store the row in the list
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

	@Override
	public List<ReportcollectVO> getAllrep() {
		List<ReportcollectVO> list = new ArrayList<ReportcollectVO>();
		ReportcollectVO rcVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_REP);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
				
				list.add(rcVO); // Store the row in the list
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

	@Override
	public ReportcollectVO getOneRepActBymem_no(Integer mem_no,Integer act_no) {

		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_REPACT_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, act_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
	
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
		return rcVO;
	}



	@Override
	public ReportcollectVO getOneRepForumBymem_no(Integer mem_no, Integer forum_no) {
	
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_REPFORUM_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, forum_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
	
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
		return rcVO;
	}



	@Override
	public ReportcollectVO getOneRepBlogBymem_no(Integer mem_no, Integer blog_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_REPBLOG_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, blog_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
	
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
		return rcVO;
	}



	@Override
	public ReportcollectVO getOneRepTravelBymem_no(Integer mem_no, Integer tra_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_REPTRAVEL_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, tra_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
	
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
		return rcVO;
	}



	@Override
	public ReportcollectVO getOneRepStrokeBymem_no(Integer mem_no, Integer stroke_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_REPSTROKE_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, stroke_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
	
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
		return rcVO;
	}



	@Override
	public ReportcollectVO getOneColForumBymem_no(Integer mem_no, Integer forum_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_COLFORUM_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, forum_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
	
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
		return rcVO;
	}



	@Override
	public ReportcollectVO getOneColBlogBymem_no(Integer mem_no, Integer blog_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_COLBLOG_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, blog_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
	
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
		return rcVO;
	}



	@Override
	public ReportcollectVO getOneColTravelBymem_no(Integer mem_no, Integer tra_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_COLTRAVEL_BY_MEM_NO);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, tra_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
	
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
		return rcVO;
	}



	@Override
	public ReportcollectVO checkTravelReport(Integer tra_no,Integer mem_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(checkTravelReport);
			pstmt.setInt(1, tra_no);
			pstmt.setInt(2, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		return rcVO;
	}
	
	@Override
	public ReportcollectVO checkTravelCollect(Integer tra_no,Integer mem_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(checkTravelCollect);
			pstmt.setInt(1,tra_no);
			pstmt.setInt(2,mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		return rcVO;
	}



	@Override
	public List<ReportcollectVO> onePoint(Integer tra_no) {
		List<ReportcollectVO> list = new ArrayList<ReportcollectVO>();
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ONEPOINT);
			pstmt.setInt(1,tra_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// rcVO 也稱為 Domain objects
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				list.add(rcVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	
	//--世麒 檢舉文章查詢
	@Override
	public ReportcollectVO findByhandle(Integer forum_no){
		
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_HANDLE);

			pstmt.setInt(1, forum_no);

			rs = pstmt.executeQuery();
			while(rs.next()){
				rcVO = new ReportcollectVO();
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
				rcVO.setRep_content(rs.getString("rep_content"));
			}
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
		return rcVO;
	}
//--世麒 收藏文章查詢
@Override
public ReportcollectVO findBystate(Integer forum_no){
	
	ReportcollectVO rcVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STATE);

		pstmt.setInt(1, forum_no);

		rs = pstmt.executeQuery();
		while(rs.next()){
			rcVO = new ReportcollectVO();
			rcVO.setRc_no(rs.getInt("rc_no"));
			rcVO.setMem_no(rs.getInt("mem_no"));
			rcVO.setForum_no(rs.getInt("forum_no"));
			rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
			rcVO.setRc_col_status(rs.getInt("rc_col_status"));
			rcVO.setRep_rel(rs.getInt("rep_rel"));
			rcVO.setRep_content(rs.getString("rep_content"));
		}
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
	return rcVO;
}
//--世麒
//--世麒 會員新增檢舉查詢
@Override
public ReportcollectVO findByforum_mem(Integer forum_no,Integer mem_no){

ReportcollectVO rcVO = null;
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
try{
	con = ds.getConnection();
	pstmt = con.prepareStatement(GET_Two_CHECK);

	pstmt.setInt(1, forum_no);
	pstmt.setInt(2, mem_no);
	rs = pstmt.executeQuery();
	while(rs.next()){
		rcVO = new ReportcollectVO();
		rcVO.setMem_no(rs.getInt("mem_no"));
		rcVO.setForum_no(rs.getInt("forum_no"));
		rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
		rcVO.setRc_col_status(rs.getInt("rc_col_status"));
		rcVO.setRep_rel(rs.getInt("rep_rel"));
		rcVO.setRep_content(rs.getString("rep_content"));
	}
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
return rcVO;
}
//--世麒
//--世麒 /**收藏文章新增**/
@Override
public void insert_forum(ReportcollectVO rcVO){

Connection con = null;
PreparedStatement pstmt=null;

try{
	con = ds.getConnection();
	pstmt=con.prepareStatement(INSERT_RC_STATUS);
	pstmt.setInt(1, rcVO.getMem_no());
	pstmt.setInt(2, rcVO.getForum_no());
	pstmt.setInt(3, rcVO.getRc_rep_handle());
	pstmt.setInt(4, rcVO.getRc_col_status());
	pstmt.setInt(5, rcVO.getRep_rel());
	pstmt.execute();
}
catch(SQLException se){
	throw new RuntimeException("A database error occured."
	+se.getMessage());
}
catch(Exception e){
	e.printStackTrace();
}finally {
	if(pstmt !=null){
		try{
		pstmt.close();
		}catch(Exception e){
		e.printStackTrace(System.err);
		}
	}
	if(con !=null){
		try{
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
}
}
//--世麒 /**收藏文章新增**/
//--世麒 /*******收藏狀態改變*********/
@Override
public void update_state_A(ReportcollectVO rcVO){
Connection con = null;
PreparedStatement pstmt = null;
try{
	con = ds.getConnection();
	pstmt = con.prepareStatement(UPDATE_RC_STATUS_A);
	
	pstmt.setInt(1, rcVO.getMem_no());
	pstmt.setInt(2, rcVO.getForum_no());
	pstmt.setInt(3, rcVO.getRc_rep_handle());
	pstmt.setInt(4, rcVO.getRc_col_status());
	pstmt.setInt(5, rcVO.getRep_rel());
	pstmt.setInt(6, rcVO.getRc_no());
	pstmt.executeUpdate();
} catch (SQLException se){
	throw new RuntimeException("A database error occured."
			+se.getMessage());
} finally {
	if(pstmt != null){
		try{
			pstmt.close();
		}
		catch (SQLException se){
			se.printStackTrace(System.err);
		}
	} if(con != null){
		try {
			con.close();
		}
		catch (Exception e){
			e.printStackTrace(System.err);
		}
	}
}
}
//--世麒 /*******收藏狀態改變*********/
//--世麒  /*******收藏狀態改變*********/
@Override
public void update_state(ReportcollectVO rcVO){
Connection con = null;
PreparedStatement pstmt = null;
try{
	con = ds.getConnection();
	pstmt = con.prepareStatement(UPDATE_RC_STATUS);
	
	pstmt.setInt(1, rcVO.getMem_no());
	pstmt.setInt(2, rcVO.getForum_no());
	pstmt.setInt(3, rcVO.getRc_rep_handle());
	pstmt.setInt(4, rcVO.getRc_col_status());
	pstmt.setInt(5, rcVO.getRep_rel());
	pstmt.setInt(6, rcVO.getRc_no());
	pstmt.executeUpdate();
} catch (SQLException se){
	throw new RuntimeException("A database error occured."
			+se.getMessage());
} finally {
	if(pstmt != null){
		try{
			pstmt.close();
		}
		catch (SQLException se){
			se.printStackTrace(System.err);
		}
	} if(con != null){
		try {
			con.close();
		}
		catch (Exception e){
			e.printStackTrace(System.err);
		}
	}
}
}
//--世麒  /*******收藏狀態改變*********/
//--世麒 /******討論區會員查詢**********/
@Override
public List<ReportcollectVO> findBymem_no(Integer mem_no) {

List<ReportcollectVO> list = new ArrayList<ReportcollectVO>();

ReportcollectVO rcVO = null;
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
	con = ds.getConnection();
	pstmt = con.prepareStatement(GET_ALL_FORUM);

	pstmt.setInt(1, mem_no);

	rs = pstmt.executeQuery();
	while (rs.next()) {
		// empVo 也稱為 Domain objects
		
		rcVO=new ReportcollectVO();
		rcVO.setRc_no(rs.getInt("rc_no"));
		rcVO.setMem_no(rs.getInt("mem_no"));
		rcVO.setTra_no(rs.getInt("tra_no"));
		rcVO.setAct_no(rs.getInt("act_no"));
		rcVO.setForum_no(rs.getInt("forum_no"));
		rcVO.setBlog_no(rs.getInt("blog_no"));
		rcVO.setStroke_no(rs.getInt("stroke_no"));
		rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
		rcVO.setRc_col_status(rs.getInt("rc_col_status"));
		rcVO.setRep_rel(rs.getInt("rep_rel"));
		rcVO.setRep_content(rs.getString("rep_content"));

		list.add(rcVO);
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
	//--世麒 /******討論區會員查詢**********/
	
	//--世麒 /*******收藏狀態查詢文章*********/
	@Override
	public ReportcollectVO findByforum_no(Integer forum_no){
	ReportcollectVO rcVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_FORUM);
	
		pstmt.setInt(1, forum_no);
		rs = pstmt.executeQuery();
		while(rs.next()){
			rcVO = new ReportcollectVO();
			rcVO.setRc_no(rs.getInt("rc_no"));
			rcVO.setMem_no(rs.getInt("mem_no"));
			rcVO.setTra_no(rs.getInt("tra_no"));
			rcVO.setAct_no(rs.getInt("act_no"));
			rcVO.setForum_no(rs.getInt("forum_no"));
			rcVO.setBlog_no(rs.getInt("blog_no"));
			rcVO.setStroke_no(rs.getInt("stroke_no"));
			rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
			rcVO.setRc_col_status(rs.getInt("rc_col_status"));
			rcVO.setRep_rel(rs.getInt("rep_rel"));
			rcVO.setRep_content(rs.getString("rep_content"));
		}
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
	return rcVO;
	}
	//--世麒 /*******收藏狀態查詢文章*********/



	@Override
	public void deleteByActno(Integer act_no) {
		
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_BY_ACTNO);

				pstmt.setInt(1, act_no);

				pstmt.executeUpdate();

				// Handle any driver errors
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
			
		
	}
	@Override
	public ReportcollectVO checkBlogReport(Integer blog_no,Integer mem_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(checkBlogReport);
			pstmt.setInt(1, blog_no);
			pstmt.setInt(2, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		return rcVO;
	}
	
	@Override
	public ReportcollectVO checkBlogCollect(Integer blog_no,Integer mem_no) {
		ReportcollectVO rcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(checkBlogCollect);
			pstmt.setInt(1,blog_no);
			pstmt.setInt(2,mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rcVO = new ReportcollectVO();
				rcVO.setRc_no(rs.getInt("rc_no"));
				rcVO.setMem_no(rs.getInt("mem_no"));
				rcVO.setTra_no(rs.getInt("tra_no"));
				rcVO.setAct_no(rs.getInt("act_no"));
				rcVO.setForum_no(rs.getInt("forum_no"));
				rcVO.setBlog_no(rs.getInt("blog_no"));
				rcVO.setStroke_no(rs.getInt("stroke_no"));
				rcVO.setRc_rep_handle(rs.getInt("rc_rep_handle"));
				rcVO.setRc_col_status(rs.getInt("rc_col_status"));
				rcVO.setRep_rel(rs.getInt("rep_rel"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		return rcVO;
	}

}

