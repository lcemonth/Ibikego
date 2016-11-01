package com.forumresponse.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.forum.model.ForumVO;
import com.forumresponse.model.ForumresDAO_interface;

public class ForumresJDBCDAO implements ForumresDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "project4";
	String passwd = "project4";
	
	private static final String INSERT_CON =
			"INSERT INTO forumresponse (forumres_no,forum_no,mem_no,forumres_con,forumres_cretime,forumres_del) VALUES (ForumRes_seq.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_CON =
			"SELECT forumres_no,forum_no,mem_no,forumres_con,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime FROM forumresponse";
	private static final String GET_ONE_CON = 
			"SELECT forumres_no,forum_no,mem_no,forumres_con,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime FROM forumresponse where forum_no=?";
	
	private static final String GET_ONE_NO =
			"SELECT forumres_no,forum_no,mem_no,forumres_con,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime,forumres_del FROM forumresponse where forumres_no=?";

//	private static final String GET_Forums_ByForumresno_CON = "SELECT forumres_no,forumres_con,mem_no,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime,forum_no,forum_title,forum_content,to_char(forum_cretime,'yyyy-mm-dd') forum_cretime  FROM forumres where forum_no = ? order by forum_no";

	private static final String DELETE_FORUMRESPONSEs = "UPDATE forumresponse set forumres_del=1  where forumres_no = ?";
//	private static final String DELETE_FORUM = "DELETE FROM forum where forum_no = ?";	

	private static final String UPDATE = "UPDATE forumresponse set forum_no=?, mem_no=?,forumres_con=?,forumres_cretime = ?,forumres_del=? where forumres_no = ?";

	@Override
	public void insert(ForumresVO forumresVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_CON);



			pstmt.setInt(1, forumresVO.getForum_no());
			pstmt.setInt(2, forumresVO.getMem_no());
			pstmt.setString(3, forumresVO.getForumres_con());
			pstmt.setDate(4, forumresVO.getForumres_cretime());
			pstmt.setInt(5, forumresVO.getForumres_del());
			pstmt.executeUpdate();

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

	}
	
	
	@Override
	public void update(ForumresVO forumresVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);



			pstmt.setInt(1, forumresVO.getForum_no());
			pstmt.setInt(2, forumresVO.getMem_no());
			pstmt.setString(3, forumresVO.getForumres_con());
			pstmt.setDate(4, forumresVO.getForumres_cretime());
			pstmt.setInt(5, forumresVO.getForumres_del());
			pstmt.setInt(6, forumresVO.getForumres_no());

			pstmt.executeUpdate();

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

	}
	
			
	@Override
	public void delete(Integer forumres_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_FORUMRESPONSEs);

			pstmt.setInt(1, forumres_no);

			pstmt.executeUpdate();

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

	}

	
	@Override
	public List<ForumresVO> getAll() {
		List<ForumresVO> list = new ArrayList<ForumresVO>();
		ForumresVO forumresVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	
	@Override
	public ForumresVO findByforumres_no(Integer forumres_no){
		ForumresVO forumresVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_NO);

			pstmt.setInt(1, forumres_no);

			rs = pstmt.executeQuery();

			while (rs.next()){
				forumresVO = new ForumresVO();
				forumresVO.setForumres_no(rs.getInt("forumres_no"));
				//				System.out.println(forumres_no);
				//				System.out.println(forumresVO);
				forumresVO.setForum_no(rs.getInt("forum_no"));

				forumresVO.setMem_no(rs.getInt("mem_no"));

				forumresVO.setForumres_con(rs.getString("forumres_con"));

				forumresVO.setForumres_cretime(rs.getDate("forumres_cretime"));

				forumresVO.setForumres_del(rs.getInt("forumres_del"));


			}
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			//			 Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		finally {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
					
					
					public static void main(String[] args) {

						ForumresJDBCDAO dao = new ForumresJDBCDAO();

						// 新增
//						ForumresVO forumresVO1 = new ForumresVO();
//						
//						forumresVO1.setForumres_no(100048);
//						forumresVO1.setForum_no(100010);
//						forumresVO1.setMem_no(115);
//						forumresVO1.setForumres_con("關於適用台鐵新制的攜車袋");
//						forumresVO1.setForumres_cretime(java.sql.Date.valueOf("2016-07-02"));
//						forumresVO1.setForumres_del(0);
////						
//						dao.insert(forumresVO1);
						
//						// 修改
//						ForumresVO forumresVO2 = new ForumresVO();
//						
//						forumresVO2.setForum_no(100010);
//						forumresVO2.setMem_no(115);
//						forumresVO2.setForumres_con("修改後xxxxxxxxxxxxxxx");
//						forumresVO2.setForumres_cretime(java.sql.Date.valueOf("2016-05-01"));
//						forumresVO2.setForumres_del(0);
//						forumresVO2.setForumres_no(100034);
//						dao.update(forumresVO2);

//						// 刪除
						dao.delete(100035);
				//
////						// 查詢
//						List<ForumresVO> list = dao.findByforum_con(100001);
//						for (ForumresVO aForum : list) {
//						System.out.print(aForum.getForumres_no() + ",");
//						System.out.print(aForum.getForum_no() + ",");
//						System.out.print(aForum.getMem_no() + ",");
//						System.out.print(aForum.getForumres_con() + ",");
//						System.out.print(aForum.getForumres_cretime() + ",");
//						System.out.println("---------------------");
////						}
				//
//						ForumresVO forumresVO3 = dao.findByforumres_no(100034);
//						System.out.print(forumresVO3.getForumres_no() + ",");
//						System.out.print(forumresVO3.getForum_no() + ",");
//						System.out.print(forumresVO3.getMem_no() + ",");
//						System.out.print(forumresVO3.getForumres_con() + ",");
//						System.out.print(forumresVO3.getForumres_cretime() + ",");
//						System.out.print(forumresVO3.getForumres_del() + ",");
//						System.out.println("---------------------");
						
						// 查詢
						List<ForumresVO> list = dao.getAll();
						for (ForumresVO aForum : list) {
							System.out.print(aForum.getForumres_no() + ",");
							System.out.print(aForum.getForum_no() + ",");
							System.out.print(aForum.getMem_no() + ",");
							System.out.print(aForum.getForumres_con() + ",");
							System.out.print(aForum.getForumres_cretime() + ",");
							System.out.println();
						}
	
	}
  }

