package com.travelImage.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpVO;

public class TravelImageDAO implements TravelImageDAO_interface{
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
	        "INSERT INTO TRAVELIMAGE (tra_img_no,tra_no,tra_img) VALUES (travelimage_seq.NEXTVAL,?,?)";
	private static final String GET_ALL_STMT = 
	    	"SELECT tra_img_no,tra_no,tra_img FROM TRAVELIMAGE order by tra_no";
	private static final String GET_ONE_STMT = 
	    	"SELECT tra_img_no,tra_no,tra_img FROM TRAVELIMAGE where tra_img_no = ?";
	private static final String DELETE = 
	    	"DELETE FROM TRAVELIMAGE where tra_img_no = ?";
	private static final String UPDATE = 
	    	"UPDATE TRAVELIMAGE set tra_img=? where tra_img_no = ?";
    
    @Override
	public void insert(TravelImageVO travelImageVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,travelImageVO.getTra_no());
			pstmt.setBytes(2,travelImageVO.getTra_img());
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
	public void update(TravelImageVO travelImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setBytes(1,travelImageVO.getTra_img());
			pstmt.setInt(2,travelImageVO.getTra_img_no());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public void delete(Integer tra_img_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, tra_img_no);
			pstmt.executeUpdate();
			// Handle any driver errors
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
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}//delete

	@Override
	public TravelImageVO findByPrimaryKey(Integer tra_img_no){
		TravelImageVO travelImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,tra_img_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// travelImageVO 也稱為 Domain objects
				travelImageVO = new TravelImageVO();
				travelImageVO.setTra_img_no(rs.getInt("tra_img_no"));
				travelImageVO.setTra_no(rs.getInt("tra_no"));
				travelImageVO.setTra_img(rs.getBytes("tra_img"));
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
		return travelImageVO;
	}//findByPrimaryKey
	
	@Override
	public List<TravelImageVO> getAll(){
		List<TravelImageVO> list = new ArrayList<TravelImageVO>();
		TravelImageVO travelImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// travelImageVO 也稱為 Domain objects
				travelImageVO = new TravelImageVO();
				travelImageVO.setTra_img_no(rs.getInt("tra_img_no"));
				travelImageVO.setTra_no(rs.getInt("tra_no"));
				travelImageVO.setTra_img(rs.getBytes("tra_img"));
				list.add(travelImageVO); // Store the row in the list
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
	
	@Override
	public void insertFromTravel(TravelImageVO travelImageVO,Connection con){
		PreparedStatement pstmt = null;
		try {
     		pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, travelImageVO.getTra_no());
			pstmt.setBytes(2, travelImageVO.getTra_img());
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-travelImage");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
}