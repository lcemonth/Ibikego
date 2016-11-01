package com.questionsanswers.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.questionsanswers.model.Que_ansVO;


public class Que_ansDAO implements Que_ansDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static  {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		}catch(NamingException e){
			e.printStackTrace();
		}
}
	
	private static final String INSERT_CON =
			"INSERT INTO questionsanswers (que_ans_no,que_ans_q,que_ans_a) VALUES (questionsanswers_seq.NEXTVAL, ?, ?)";
	private static final String GET_ALL_CON =
			"SELECT que_ans_no,que_ans_q,que_ans_a FROM questionsanswers order by que_ans_no desc";
	private static final String GET_ONE_CON = 
			"SELECT que_ans_q,que_ans_a,que_ans_no FROM questionsanswers where que_ans_no=?";
	
//	private static final String GET_Forums_ByForumresno_CON = "SELECT forumres_no,forumres_con,mem_no,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime,forum_no,forum_title,forum_content,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime  FROM forumres where forum_no = ? order by forum_no";

	private static final String DELETE = "DELETE FROM questionsanswers  where que_ans_no = ?";
//	private static final String DELETE_FORUM = "DELETE FROM forum where forum_no = ?";	

	private static final String UPDATE = "UPDATE questionsanswers set que_ans_q=?,que_ans_a=? where que_ans_no = ?";

	
	@Override
	public void insert(Que_ansVO que_ansVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_CON);

	
			pstmt.setString(1, que_ansVO.getQue_ans_q());
			pstmt.setString(2, que_ansVO.getQue_ans_a());
			
			pstmt.executeUpdate();

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

	}
	
	
	@Override
	public void update(Que_ansVO que_ansVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, que_ansVO.getQue_ans_q());
			
			pstmt.setString(2, que_ansVO.getQue_ans_a());
			
			pstmt.setInt(3, que_ansVO.getQue_ans_no());
			

			
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
	public void delete(Integer que_ans_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, que_ans_no);

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
	public List<Que_ansVO> getAll() {
		List<Que_ansVO> list = new ArrayList<Que_ansVO>();
		Que_ansVO que_ansVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CON);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				que_ansVO = new Que_ansVO();
				que_ansVO.setQue_ans_no(rs.getInt("que_ans_no"));
				que_ansVO.setQue_ans_q(rs.getString("que_ans_q"));
				que_ansVO.setQue_ans_a(rs.getString("que_ans_a"));

				list.add(que_ansVO); // Store the row in the list
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
	public Que_ansVO findByque_ans_con(Integer que_ans_no) {

		Que_ansVO que_ansVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CON);

			pstmt.setInt(1, que_ans_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				que_ansVO = new Que_ansVO();
				
				que_ansVO.setQue_ans_q(rs.getString("que_ans_q"));
				que_ansVO.setQue_ans_a(rs.getString("que_ans_a"));
				que_ansVO.setQue_ans_no(rs.getInt("que_ans_no"));
				
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
		return que_ansVO;
		
		
	}
}

