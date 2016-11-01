package com.questionsanswers.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.forum.model.ForumVO;

import java.util.*;
import java.sql.*;




public class Que_ansJDBCDAO implements Que_ansDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "project4";
	String passwd = "project4";
	
	private static final String INSERT_CON =
			"INSERT INTO questionsanswers (que_ans_no,que_ans_q,que_ans_a) VALUES (questionsanswers_seq.NEXTVAL, ?, ?)";
	private static final String GET_ALL_CON =
			"SELECT que_ans_no,que_ans_q,que_ans_a FROM questionsanswers";
	private static final String GET_ONE_CON = 
			"SELECT que_ans_q,que_ans_a,que_ans_no FROM questionsanswers where que_ans_no=?";
	
	private static final String DELETE = "DELETE FROM questionsanswers  where que_ans_no = ?";
	

	private static final String UPDATE = "UPDATE questionsanswers set que_ans_q=?,que_ans_a=? where que_ans_no = ?";

	@Override
	public void insert(Que_ansVO que_ansVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_CON);

			
			
			pstmt.setString(1, que_ansVO.getQue_ans_q());
			pstmt.setString(2, que_ansVO.getQue_ans_a());
		

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
	public void update(Que_ansVO que_ansVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, que_ansVO.getQue_ans_q());
			pstmt.setString(2, que_ansVO.getQue_ans_a());
			pstmt.setInt(3, que_ansVO.getQue_ans_no());
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
	public void delete(Integer que_ans_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, que_ans_no);

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
	public List<Que_ansVO> getAll() {
		List<Que_ansVO> list = new ArrayList<Que_ansVO>();
		Que_ansVO que_ansVO = null;

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
				que_ansVO = new Que_ansVO();
				que_ansVO.setQue_ans_no(rs.getInt("que_ans_no"));
				que_ansVO.setQue_ans_q(rs.getString("que_ans_q"));
				que_ansVO.setQue_ans_a(rs.getString("que_ans_a"));


				list.add(que_ansVO); // Store the row in the list
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
	public Que_ansVO findByque_ans_con(Integer que_ans_no) {

		Que_ansVO que_ansVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return que_ansVO;
	}
					
					
					public static void main(String[] args) {

						Que_ansJDBCDAO dao = new Que_ansJDBCDAO();

						// 新增
//						Que_ansVO que_ansVO1 = new Que_ansVO();
//						
//						que_ansVO1.setQue_ans_q("120");
//						que_ansVO1.setQue_ans_a("關於適用台鐵新制的攜車袋");
//			
////						
//						dao.insert(que_ansVO1);

//						// 修改
//						Que_ansVO que_ansVO2 = new Que_ansVO();
//						que_ansVO2.setQue_ans_no(3);
//
//						que_ansVO2.setQue_ans_q("修改後xxxxxxxxxxxxxxx"
//								+ "asdasdsadsadsdsdsa");
//						que_ansVO2.setQue_ans_a("修改後xxxxxxxxxxxxxxx"
//								+ "asdasdsadsadsdsdsa");
//						dao.update(que_ansVO2);

//						// 刪除
//						dao.delete(3);
				//
						// 查詢
						Que_ansVO que_ansVO3 = dao.findByque_ans_con(2);
						System.out.print(que_ansVO3.getQue_ans_no() + ",");
						System.out.print(que_ansVO3.getQue_ans_q() + ",");
						System.out.print(que_ansVO3.getQue_ans_a() + ",");
						System.out.println("---------------------");
				
						
						
						// 查詢
//						List<Que_ansVO> list = dao.getAll();
//						for (Que_ansVO aForum : list) {
//							System.out.print(aForum.getQue_ans_no() + ",");
//							System.out.print(aForum.getQue_ans_q() + ",");
//							System.out.print(aForum.getQue_ans_a() + ",");
//							System.out.println();
//						}
	
	}
}
