package com.location.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import com.activity.model.ActivityVO;


public class LocationDAO implements LocationDAO_interface{
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
	
	private static final String INSERT_STMT = 
		"INSERT INTO location (loc_no, loc_name) VALUES (location_seq.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT loc_no, loc_name FROM location order by loc_no";
	private static final String GET_ONE_STMT = 
		"SELECT loc_no, loc_name FROM location where loc_no = ?";
	private static final String DELETE = 
		"DELETE FROM location where loc_no = ?";
	private static final String UPDATE = 
		"UPDATE location set loc_name = ? where loc_no = ?";
	private static final String GET_Activity_ByLoc_no_STMT = "SELECT * FROM activity where loc_no =? order by act_no";	
	private static final String GET_LNGNLAT_BYLOC_NO= 
			"SELECT * FROM location where loc_no = ?";
  @Override
  public void insert(LocationVO LocationVO) {
    Connection con = null;
    PreparedStatement pstmt = null;
    	
  	try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, LocationVO.getLoc_no());
			pstmt.setString(2, LocationVO.getLoc_name());
	
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (Exception e) {
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
  public void update(LocationVO LocationVO){
    Connection con = null;
    PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, LocationVO.getLoc_name());
			pstmt.setInt(2, LocationVO.getLoc_no());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (Exception e) {
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
  public void delete(Integer loc_no){
   	Connection con = null;
   	PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, loc_no);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (Exception e) {
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
  public LocationVO findByPrimaryKey(Integer loc_no){
   	LocationVO LocationVO = null;
	Connection con = null;
   	PreparedStatement pstmt = null;
   	ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, loc_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LocationVO = new LocationVO();
				LocationVO.setLoc_no(rs.getInt("loc_no"));
				LocationVO.setLoc_name(rs.getString("loc_name"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (Exception e) {
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
		return LocationVO;
  }
    
  @Override
  public List<LocationVO> getAll(){
   	List<LocationVO> list = new ArrayList<LocationVO>();
   	LocationVO LocationVO = null;
	Connection con = null;
   	PreparedStatement pstmt = null;
   	ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LocationVO = new LocationVO();
				LocationVO.setLoc_no(rs.getInt("loc_no"));
				LocationVO.setLoc_name(rs.getString("loc_name"));
				list.add(LocationVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (Exception e) {
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
public List<ActivityVO> getActivityByLoc_no(Integer loc_no) {

	List<ActivityVO> list = new ArrayList<ActivityVO>();
	ActivityVO actVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_Activity_ByLoc_no_STMT);
		pstmt.setInt(1, loc_no);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			actVO = new ActivityVO();
			actVO.setAct_no(rs.getInt("act_no"));
			actVO.setMem_no(rs.getInt("mem_no"));
			actVO.setLoc_no(rs.getInt("loc_no"));
			actVO.setStroke_no(rs.getInt("stroke_no"));
			actVO.setAct_name(rs.getString("act_name"));
			actVO.setAct_loc(rs.getString("act_loc"));
			actVO.setAct_start_date(rs.getDate("act_start_date"));
			actVO.setAct_end_date(rs.getDate("act_end_date"));
			actVO.setAct_exp(rs.getString("act_exp"));
			//actVO.setAct_photo(rs.getBytes("act_photo"));
			actVO.setAct_is_public(rs.getInt("act_is_public"));
			//actVO.setAct_act_route(rs.getBytes("act_act_route"));
			//actVO.setAct_alti(rs.getBytes("act_alti"));
			actVO.setAct_km(rs.getDouble("act_km"));
			actVO.setAct_joinlimit(rs.getInt("act_joinlimit"));
			
			
			list.add(actVO); // Store the row in the list
		}

		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
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

public LocationVO findLngNLatByLoc_no(Integer loc_no){
   	LocationVO LocationVO = null;
	Connection con = null;
   	PreparedStatement pstmt = null;
   	ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LNGNLAT_BYLOC_NO);
			
			pstmt.setInt(1, loc_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LocationVO = new LocationVO();
				LocationVO.setLoc_no(rs.getInt("loc_no"));
				LocationVO.setLoc_name(rs.getString("loc_name"));
				LocationVO.setLoc_longi(rs.getDouble("loc_longi")); //經
				LocationVO.setLoc_lati(rs.getDouble("loc_lati"));  //緯
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (Exception e) {
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
		return LocationVO;
  }
}
