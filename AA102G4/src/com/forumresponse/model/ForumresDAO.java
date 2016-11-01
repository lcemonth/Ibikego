package com.forumresponse.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.forum.model.ForumVO;
import com.forumresponse.model.ForumresVO;

public class ForumresDAO implements ForumresDAO_interface{
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
			"INSERT INTO forumresponse (forumres_no,forum_no,mem_no,forumres_con,forumres_cretime,forumres_del) VALUES (ForumRes_seq.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_CON =
			"SELECT forumres_no,forum_no,mem_no,forumres_con,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime FROM forumresponse where forumres_del=0 order by forumres_cretime desc";
	private static final String GET_ONE_NO =
			"SELECT forumres_no,forum_no,mem_no,forumres_con,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime,forumres_del FROM forumresponse where forumres_no=? order by forumres_cretime";
//	private static final String GET_Forums_ByForumresno_CON = "SELECT forumres_no,forumres_con,mem_no,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime,forum_no,forum_title,forum_content,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime  FROM forumres where forum_no = ? order by forum_no";

	private static final String DELETE_FORUMRESPONSEs = "UPDATE forumresponse set forumres_del=1  where forumres_no = ?";
//	private static final String DELETE_FORUM = "DELETE FROM forum where forum_no = ?";	

	private static final String UPDATE = "UPDATE forumresponse set forum_no=?, mem_no=?,forumres_con=?,forumres_cretime = ?,forumres_del=? where forumres_no = ?";
	/**9/5回文修改**/
	private static final String GET_ONE_CON = 
			"SELECT forumres_no,forum_no,mem_no,forumres_con,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime,forumres_del FROM forumresponse where forum_no=? and forumres_del=0 order by forumres_cretime,forumres_no ";
	/**9/5回文修改**/

	@Override
	public void insert(ForumresVO forumresVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_CON);

			pstmt.setInt(1, forumresVO.getForum_no());
			pstmt.setInt(2, forumresVO.getMem_no());
			pstmt.setString(3, forumresVO.getForumres_con());
			pstmt.setDate(4, forumresVO.getForumres_cretime());
			pstmt.setInt(5, forumresVO.getForumres_del());
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
	public void update(ForumresVO forumresVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, forumresVO.getForum_no());
			pstmt.setInt(2, forumresVO.getMem_no());
			pstmt.setString(3, forumresVO.getForumres_con());
			pstmt.setDate(4, forumresVO.getForumres_cretime());
			pstmt.setInt(5, forumresVO.getForumres_del());
			pstmt.setInt(6, forumresVO.getForumres_no());
			

			
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
	public void delete(Integer forumres_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FORUMRESPONSEs);

			pstmt.setInt(1, forumres_no);

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
	public List<ForumresVO> getAll() {
		List<ForumresVO> list = new ArrayList<ForumresVO>();
		ForumresVO forumresVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CON);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				forumresVO = new ForumresVO();
				forumresVO.setForumres_no(rs.getInt("forumres_no"));
				forumresVO.setForum_no(rs.getInt("forum_no"));
				forumresVO.setMem_no(rs.getInt("mem_no"));
				forumresVO.setForumres_con(rs.getString("forumres_con"));
				forumresVO.setForumres_cretime(rs.getDate("forumres_cretime"));

				list.add(forumresVO); // Store the row in the list
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
	public ForumresVO findByforumres_no(Integer forumres_no){
		ForumresVO forumresVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_NO);
			
			pstmt.setInt(1, forumres_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				forumresVO = new ForumresVO();
				forumresVO.setForumres_no(rs.getInt("forumres_no"));
				forumresVO.setForum_no(rs.getInt("forum_no"));
				forumresVO.setMem_no(rs.getInt("mem_no"));
				forumresVO.setForumres_con(rs.getString("forumres_con"));
				forumresVO.setForumres_cretime(rs.getDate("forumres_cretime"));
				forumresVO.setForumres_del(rs.getInt("forumres_del"));
							
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
		return forumresVO;
	}
	@Override
	public List<ForumresVO> findByforum_con(Integer forum_no) {
		List<ForumresVO> list = new ArrayList<ForumresVO>();
		
		ForumresVO forumresVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CON);

			pstmt.setInt(1, forum_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				forumresVO = new ForumresVO();
				forumresVO.setForumres_no(rs.getInt("forumres_no"));
				forumresVO.setForum_no(rs.getInt("forum_no"));
				forumresVO.setMem_no(rs.getInt("mem_no"));
				forumresVO.setForumres_con(rs.getString("forumres_con"));
				forumresVO.setForumres_cretime(rs.getDate("forumres_cretime"));
				list.add(forumresVO);
				
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

