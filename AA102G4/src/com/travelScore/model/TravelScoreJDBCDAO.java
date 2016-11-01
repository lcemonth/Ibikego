package com.travelScore.model;

import java.util.*;
import java.sql.*;

public class TravelScoreJDBCDAO implements TravelScoreDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "project4";
	String passwd = "project4";

	private static final String INSERT_STMT = 
	        "INSERT INTO travelscore (tra_no,mem_no,tra_score,tra_score_status) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = 
	    	"SELECT tra_no,mem_no,tra_score,tra_score_status FROM travelscore order by tra_no";
	private static final String GET_ONE_STMT = 
	    	"SELECT tra_no,mem_no,tra_score,tra_score_status FROM travelscore where tra_no = ?";
	private static final String GET_ONETRAVEL_STMT = 
	    	"SELECT * FROM travelscore where tra_no = ?";
	private static final String CHECK = 
	    	"SELECT * FROM travelscore where tra_no = ? and mem_no = ?";
	private static final String GET_ONETRAVELSCORE_STMT = 
	    	"SELECT COUNT ('tra_score') totalscore FROM travelscore where tra_no = ?";

	@Override
	public void insert(TravelScoreVO travelScoreVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,travelScoreVO.getTra_no());
			pstmt.setInt(2,travelScoreVO.getMem_no());
			pstmt.setInt(3,travelScoreVO.getTra_score());
			pstmt.setInt(4,travelScoreVO.getTra_score_status());
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public TravelScoreVO findByPrimaryKey(Integer tra_no) {
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, tra_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// travelScoreVO 也稱為 Domain objects
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTra_no(rs.getInt("tra_no"));
				travelScoreVO.setMem_no(rs.getInt("mem_no"));
				travelScoreVO.setTra_score(rs.getInt("tra_score"));
				travelScoreVO.setTra_score_status(rs.getInt("tra_score_status"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
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
		return travelScoreVO;
	}
	
	@Override
	public TravelScoreVO getCheck(Integer tra_no,Integer mem_no){
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHECK);
			pstmt.setInt(1,tra_no);
			pstmt.setInt(2,mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// travelScoreVO 也稱為 Domain objects
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTra_no(rs.getInt("tra_no"));
				travelScoreVO.setMem_no(rs.getInt("mem_no"));
				travelScoreVO.setTra_score(rs.getInt("tra_score"));
				travelScoreVO.setTra_score_status(rs.getInt("tra_score_status"));
			}
			// Handle any driver errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return travelScoreVO;
	}//getCheck
	
	@Override
	public List<TravelScoreVO> getOneTravel(Integer tra_no) {
		List<TravelScoreVO> list = new ArrayList<TravelScoreVO>();
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONETRAVEL_STMT);
			pstmt.setInt(1, tra_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// travelScoreVO 也稱為 Domain objects
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTra_no(rs.getInt("tra_no"));
				travelScoreVO.setMem_no(rs.getInt("mem_no"));
				travelScoreVO.setTra_score(rs.getInt("tra_score"));
				travelScoreVO.setTra_score_status(rs.getInt("tra_score_status"));
				list.add(travelScoreVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public TravelScoreVO getOneTravelScore(Integer tra_no){
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONETRAVELSCORE_STMT);
			pstmt.setInt(1,tra_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// travelScoreVO 也稱為 Domain objects
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTotalScore(rs.getInt("tra_score"));
			}
			// Handle any driver errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return travelScoreVO;
	}//getOneTravelScore

	@Override
	public List<TravelScoreVO> getAll() {
		List<TravelScoreVO> list = new ArrayList<TravelScoreVO>();
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// travelScoreVO 也稱為 Domain objects
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTra_no(rs.getInt("tra_no"));
				travelScoreVO.setMem_no(rs.getInt("mem_no"));
				travelScoreVO.setTra_score(rs.getInt("tra_score"));
				travelScoreVO.setTra_score_status(rs.getInt("tra_score_status"));
				list.add(travelScoreVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {
		TravelScoreJDBCDAO dao = new TravelScoreJDBCDAO();
		// 新增
//		TravelScoreVO travelScoreVO = new TravelScoreVO();
//		travelScoreVO.setTra_no(112);
//		travelScoreVO.setMem_no(117);
//		travelScoreVO.setTra_score(10);
//		travelScoreVO.setTra_score_status(1);
//		dao.insert(travelScoreVO);
		
		// 查詢
//		TravelScoreVO travelScoreVO2 = dao.findByPrimaryKey(112);
//		System.out.print("旅遊點編號: "+travelScoreVO2.getTra_no() + ",");
//		System.out.print("評分者: "+travelScoreVO2.getMem_no() + ",");
//		System.out.print("分數: "+travelScoreVO2.getTra_score() + ",");
//		if(travelScoreVO2.getTra_score_status()==0) System.out.print("是否評分: 否.");
//		else System.out.println("是否評分: 是.");
//		System.out.println("---------------------");
		
		// 查詢單筆總分
//		TravelScoreVO travelScoreVO3 = dao.getOneScore(112);
//		System.out.println("總分: "+travelScoreVO3.getTotalScore() + ",");
//		System.out.println("---------------------");

//		// 查詢
//		List<TravelScoreVO> list = dao.getAll();
//		for (TravelScoreVO allTravelScore : list) {
//			System.out.print("日誌編號: "+allTravelScore.getTra_no() + ",");
//			System.out.print("評分者: "+allTravelScore.getMem_no() + ",");
//			System.out.print("分數: "+allTravelScore.getTra_score() + ",");
//			if(allTravelScore.getTra_score_status()==0) System.out.print("是否評分: 否.");
//			else System.out.println("是否評分: 是.");
//			System.out.println();
//		}
	}
}