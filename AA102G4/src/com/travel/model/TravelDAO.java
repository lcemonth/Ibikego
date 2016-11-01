package com.travel.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.travelImage.model.TravelImageDAO;
import com.travelImage.model.TravelImageVO;

public class TravelDAO implements TravelDAO_interface{
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
	        "INSERT INTO TRAVEL (tra_no,mem_no,loc_no,tra_class_status,tra_name,tra_content,tra_tel,tra_add,tra_cre,tra_lati,tra_longi,tra_del) VALUES (travel_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
	    	"SELECT tra_no,mem_no,loc_no,tra_class_status,tra_name,tra_content,tra_tel,tra_add,to_char(tra_cre,'yyyy-mm-dd') tra_cre,tra_lati,tra_longi,tra_del FROM TRAVEL order by tra_no desc";
	private static final String GET_ONE_STMT = 
	    	"SELECT tra_no,mem_no,loc_no,tra_class_status,tra_name,tra_content,tra_tel,tra_add,to_char(tra_cre,'yyyy-mm-dd') tra_cre,tra_lati,tra_longi,tra_del FROM TRAVEL where tra_no = ?";
	private static final String searchAttractions = 
	    	"SELECT * from travel where tra_class_status = 0";
	private static final String searchBreak = 
	    	"SELECT * from travel where tra_class_status = 1";
	private static final String DELETE = 
	    	"DELETE FROM TRAVEL where tra_no = ?";
	private static final String UPDATE = 
	    	"UPDATE TRAVEL set mem_no=?,loc_no=?,tra_class_status=?,tra_name=?,tra_content=?,tra_tel=?,tra_add=?,tra_cre=?,tra_lati=?,tra_longi=?,tra_del=? where tra_no = ?";
	/*******************************************************0827***********************************************************/
	private static final String getMyTravelPoints = 
	    	"SELECT * from travel where mem_no = ?";
	/*建宇新增-查詢旅遊點類型*/
	private static final String GET_CLASS_STATUS_ALL ="SELECT tra_no,mem_no,loc_no,tra_class_status,tra_name,tra_content,tra_tel,tra_add,to_char(tra_cre,'yyyy-mm-dd') tra_cre,tra_lati,tra_longi FROM  TRAVEL where tra_class_status=? order by tra_no";
    /*建宇結束*/
	//---合---
	private static final String UPDATEDEL=
			"UPDATE TRAVEL set tra_del=1 where tra_no = ?";
	//---合---
	
	@Override
	public void insert(TravelVO travelVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,travelVO.getMem_no());
			pstmt.setInt(2,travelVO.getLoc_no());
			pstmt.setInt(3,travelVO.getTra_class_status());
			pstmt.setString(4,travelVO.getTra_name());
			pstmt.setString(5,travelVO.getTra_content());
			pstmt.setString(6,travelVO.getTra_tel());
			pstmt.setString(7,travelVO.getTra_add());
			pstmt.setDate(8,travelVO.getTra_cre());
			pstmt.setDouble(9,travelVO.getTra_lati());
			pstmt.setDouble(10,travelVO.getTra_longi());
			pstmt.setInt(11,travelVO.getTra_del());
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void update(TravelVO travelVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,travelVO.getMem_no());
			pstmt.setInt(2,travelVO.getLoc_no());
			pstmt.setInt(3,travelVO.getTra_class_status());
			pstmt.setString(4,travelVO.getTra_name());
			pstmt.setString(5,travelVO.getTra_content());
			pstmt.setString(6,travelVO.getTra_tel());
			pstmt.setString(7,travelVO.getTra_add());
			pstmt.setDate(8,travelVO.getTra_cre());
			pstmt.setDouble(9,travelVO.getTra_lati());
			pstmt.setDouble(10,travelVO.getTra_longi());
			pstmt.setInt(11,travelVO.getTra_del());
			pstmt.setInt(12,travelVO.getTra_no());
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		}finally{
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
	public void delete(Integer tra_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, tra_no);
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
	public TravelVO findByPrimaryKey(Integer tra_no) {
		TravelVO travelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, tra_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// travelVO 也稱為 Domain objects
				travelVO = new TravelVO();
				travelVO.setTra_no(rs.getInt("tra_no"));
				travelVO.setMem_no(rs.getInt("mem_no"));
				travelVO.setLoc_no(rs.getInt("loc_no"));
				travelVO.setTra_class_status(rs.getInt("tra_class_status"));
				travelVO.setTra_name(rs.getString("tra_name"));
				travelVO.setTra_content(rs.getString("tra_content"));
				travelVO.setTra_tel(rs.getString("tra_tel"));
				travelVO.setTra_add(rs.getString("tra_add"));
				travelVO.setTra_cre(rs.getDate("tra_cre"));
				travelVO.setTra_lati(rs.getDouble("tra_lati"));
				travelVO.setTra_longi(rs.getDouble("tra_longi"));
				travelVO.setTra_del(rs.getInt("tra_del"));
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
		return travelVO;
	}
	
	@Override
	public List<TravelVO> searchAttractions() {
		List<TravelVO> list = new ArrayList<TravelVO>();
		TravelVO travelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(searchAttractions);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// travelVO 也稱為 Domain objects
				travelVO = new TravelVO();
				travelVO.setTra_no(rs.getInt("tra_no"));
				travelVO.setMem_no(rs.getInt("mem_no"));
				travelVO.setLoc_no(rs.getInt("loc_no"));
				travelVO.setTra_class_status(rs.getInt("tra_class_status"));
				travelVO.setTra_name(rs.getString("tra_name"));
				travelVO.setTra_content(rs.getString("tra_content"));
				travelVO.setTra_tel(rs.getString("tra_tel"));
				travelVO.setTra_add(rs.getString("tra_add"));
				travelVO.setTra_cre(rs.getDate("tra_cre"));
				travelVO.setTra_lati(rs.getDouble("tra_lati"));
				travelVO.setTra_longi(rs.getDouble("tra_longi"));
				travelVO.setTra_del(rs.getInt("tra_del"));
				list.add(travelVO); // Store the row in the list
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
	public List<TravelVO> searchBreak() {
		List<TravelVO> list = new ArrayList<TravelVO>();
		TravelVO travelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(searchBreak);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// travelVO 也稱為 Domain objects
				travelVO = new TravelVO();
				travelVO.setTra_no(rs.getInt("tra_no"));
				travelVO.setMem_no(rs.getInt("mem_no"));
				travelVO.setLoc_no(rs.getInt("loc_no"));
				travelVO.setTra_class_status(rs.getInt("tra_class_status"));
				travelVO.setTra_name(rs.getString("tra_name"));
				travelVO.setTra_content(rs.getString("tra_content"));
				travelVO.setTra_tel(rs.getString("tra_tel"));
				travelVO.setTra_add(rs.getString("tra_add"));
				travelVO.setTra_cre(rs.getDate("tra_cre"));
				travelVO.setTra_lati(rs.getDouble("tra_lati"));
				travelVO.setTra_longi(rs.getDouble("tra_longi"));
				travelVO.setTra_del(rs.getInt("tra_del"));
				list.add(travelVO); // Store the row in the list
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
	public List<TravelVO> getAll() {
		List<TravelVO> list = new ArrayList<TravelVO>();
		TravelVO travelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// travelVO 也稱為 Domain objects
				travelVO = new TravelVO();
				travelVO.setTra_no(rs.getInt("tra_no"));
				travelVO.setMem_no(rs.getInt("mem_no"));
				travelVO.setLoc_no(rs.getInt("loc_no"));
				travelVO.setTra_class_status(rs.getInt("tra_class_status"));
				travelVO.setTra_name(rs.getString("tra_name"));
				travelVO.setTra_content(rs.getString("tra_content"));
				travelVO.setTra_tel(rs.getString("tra_tel"));
				travelVO.setTra_add(rs.getString("tra_add"));
				travelVO.setTra_cre(rs.getDate("tra_cre"));
				travelVO.setTra_lati(rs.getDouble("tra_lati"));
				travelVO.setTra_longi(rs.getDouble("tra_longi"));
				travelVO.setTra_del(rs.getInt("tra_del"));
				list.add(travelVO); // Store the row in the list
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
/********************************0827******************************************/
	@Override
	public void insertWithImages(TravelVO travelVO,List<TravelImageVO> images){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
    		// 先新增旅遊點
			String cols[] = {"tra_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1,travelVO.getMem_no());
			pstmt.setInt(2,travelVO.getLoc_no());
			pstmt.setInt(3,travelVO.getTra_class_status());
			pstmt.setString(4,travelVO.getTra_name());
			pstmt.setString(5,travelVO.getTra_content());
			pstmt.setString(6,travelVO.getTra_tel());
			pstmt.setString(7,travelVO.getTra_add());
			pstmt.setDate(8,travelVO.getTra_cre());
			pstmt.setDouble(9,travelVO.getTra_lati());
			pstmt.setDouble(10,travelVO.getTra_longi());
			pstmt.setInt(11,travelVO.getTra_del());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_tra_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_tra_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_tra_no +"(剛新增成功的旅遊點編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增圖片
			TravelImageDAO dao = new TravelImageDAO();
			System.out.println("list.size()-A="+images.size());
			for (TravelImageVO aImage : images) {
				aImage.setTra_no(new Integer(next_tra_no)) ;
				dao.insertFromTravel(aImage,con);
			}
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+images.size());
			System.out.println("新增旅遊點編號"+next_tra_no+"時,共有圖片"+images.size()+"張同時被新增");
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-travel");
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
	public List<TravelVO> getMyTravelPoints(Integer mem_no) {
		List<TravelVO> list = new ArrayList<TravelVO>();
		TravelVO travelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getMyTravelPoints);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// travelVO 也稱為 Domain objects
				travelVO = new TravelVO();
				travelVO.setTra_no(rs.getInt("tra_no"));
				travelVO.setMem_no(rs.getInt("mem_no"));
				travelVO.setLoc_no(rs.getInt("loc_no"));
				travelVO.setTra_class_status(rs.getInt("tra_class_status"));
				travelVO.setTra_name(rs.getString("tra_name"));
				travelVO.setTra_content(rs.getString("tra_content"));
				travelVO.setTra_tel(rs.getString("tra_tel"));
				travelVO.setTra_add(rs.getString("tra_add"));
				travelVO.setTra_cre(rs.getDate("tra_cre"));
				travelVO.setTra_lati(rs.getDouble("tra_lati"));
				travelVO.setTra_longi(rs.getDouble("tra_longi"));
				travelVO.setTra_del(rs.getInt("tra_del"));
				list.add(travelVO); // Store the row in the list
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
/********************************0827******************************************/
	//--合
		@Override
		public void updateDel(Integer tra_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATEDEL);
				pstmt.setInt(1, tra_no);
				pstmt.executeUpdate();
				// Handle any driver errors
			}catch(SQLException se){
				throw new RuntimeException("A database error occured. "+ se.getMessage());
				// Clean up JDBC resources
			}finally{
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
		//--合
		 /*建宇新增-查詢旅遊點類型*/
		@Override
		public List<TravelVO> getAll(Integer tra_class_status) {
			List<TravelVO> list = new ArrayList<TravelVO>();
			TravelVO travelVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_CLASS_STATUS_ALL);
				pstmt.setInt(1, tra_class_status);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					// travelVO 也稱為 Domain objects
					travelVO = new TravelVO();
					travelVO.setTra_no(rs.getInt("tra_no"));
					travelVO.setMem_no(rs.getInt("mem_no"));
					travelVO.setLoc_no(rs.getInt("loc_no"));
					travelVO.setTra_class_status(rs.getInt("tra_class_status"));
					travelVO.setTra_name(rs.getString("tra_name"));
					travelVO.setTra_content(rs.getString("tra_content"));
					travelVO.setTra_tel(rs.getString("tra_tel"));
					travelVO.setTra_add(rs.getString("tra_add"));
					travelVO.setTra_cre(rs.getDate("tra_cre"));
					travelVO.setTra_lati(rs.getDouble("tra_lati"));
					travelVO.setTra_longi(rs.getDouble("tra_longi"));
					list.add(travelVO); // Store the row in the list
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
		/*建宇結束*/
}