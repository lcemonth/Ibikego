package com.travelScore.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TravelScoreDAO implements TravelScoreDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
    private static DataSource ds = null;
    static{
        try{
        	Context ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
        }catch(NamingException e){
        	e.printStackTrace();
        }
    }
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
	private static final String GET_ONE_TRAVELSCORE_STMT = 
	    	"SELECT COUNT ('tra_score') totalscore FROM travelscore where tra_no = ?";
    
    @Override
	public void insert(TravelScoreVO travelScoreVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,travelScoreVO.getTra_no());
			pstmt.setInt(2,travelScoreVO.getMem_no());
			pstmt.setInt(3,travelScoreVO.getTra_score());
			pstmt.setInt(4,travelScoreVO.getTra_score_status());
			pstmt.executeUpdate();
			// Handle any SQL errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
			// Clean up JDBC resources
		}finally{
		    if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}//insert

	@Override
	public TravelScoreVO findByPrimaryKey(Integer tra_no){
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,tra_no);
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
	}//findByPrimaryKey
	
	@Override
	public TravelScoreVO getCheck(Integer tra_no,Integer mem_no){
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
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
	public TravelScoreVO getOneTravelScore(Integer tra_no){
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TRAVELSCORE_STMT);
			pstmt.setInt(1,tra_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// travelScoreVO 也稱為 Domain objects
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTotalScore(rs.getInt("totalscore"));
			}
			// Handle any driver errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
			// Clean up JDBC resources
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
	public List<TravelScoreVO> getOneTravel(Integer tra_no) {
		List<TravelScoreVO> list = new ArrayList<TravelScoreVO>();
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
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
	public List<TravelScoreVO> getAll(){
		List<TravelScoreVO> list = new ArrayList<TravelScoreVO>();
		TravelScoreVO travelScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// travelScoreVO 也稱為 Domain objects
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTra_no(rs.getInt("tra_no"));
				travelScoreVO.setMem_no(rs.getInt("mem_no"));
				travelScoreVO.setTra_score(rs.getInt("tra_score"));
				travelScoreVO.setTra_score_status(rs.getInt("tra_score_status"));
				list.add(travelScoreVO); // Store the row in the list
			}
			// Handle any driver errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}//getAll
}