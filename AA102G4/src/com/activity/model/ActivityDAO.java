package com.activity.model;

import java.io.ByteArrayInputStream;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpVO;

import com.tool.CompositeQuery_Act;
import tools.TurnByte;


public class ActivityDAO implements ActivityDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO activity (act_no,mem_no,loc_no,stroke_no,act_name,"+
	 "act_loc,act_start_date,act_end_date,act_exp,act_photo,act_is_public,act_act_route,act_alti,act_km,act_joinlimit)"+
	 " VALUES (activity_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_STMT_NO_STROKE = "INSERT INTO activity (act_no,mem_no,loc_no,act_name,"+
			 "act_loc,act_start_date,act_end_date,act_exp,act_photo,act_is_public,act_act_route,act_alti,act_km,act_joinlimit)"+
			 " VALUES (activity_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE activity set loc_no=?,act_name=?, act_loc=?, act_start_date=?, act_end_date=?, act_exp=? ,"+
	" act_photo= ?, act_is_public= ?, act_act_route= ?, act_alti= ?, act_km= ?, act_joinlimit= ?, stroke_no=? where mem_no = ? and act_no=? ";
	private static final String UPDATE_NO_STROKE = 
			"UPDATE activity set loc_no=?, act_name=?, act_loc=?, act_start_date=?, act_end_date=?, act_exp=? ,"+
	" act_photo= ?, act_is_public= ?, act_act_route= ?, act_alti= ?, act_km= ?, act_joinlimit= ?, stroke_no=? where mem_no = ? and act_no=? ";
	private static final String DELETE = 
			"DELETE FROM activity where act_no = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM activity order by act_no";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM activity where act_no = ?";
	private static final String GET_ALLACT_NEW = 
			"SELECT * FROM activity where act_start_date > (SELECT SYSDATE FROM dual) and act_is_public=0 order by act_no desc ";
	private static final String LOOKUP_MEM_IS_MACT = 
			"SELECT * FROM activity where mem_no=? and act_no = ?";
	private static final String FIND_MEM_JOINED_ACTS = 
			"select * from joinactivity ja,activity act where act.act_no = ja.act_no and ja.joinact_is_join=1 and ja.mem_no = ? order by act.act_no desc";
	private static final String CHK_DATE = "SELECT SF_CHK_DATE(?, ?, ?) AS RSL FROM DUAL";
    private static final String CHK_ACT_DATE=		
		    " select count(1) as cnt from activity where ("+
			  " to_date(?,'yyyy-mm-dd') "+
		    " between act_start_date and  act_end_date or "+
		    " to_date(?,'yyyy-mm-dd') "+
		    " between act_start_date and  act_end_date or "+
		    " act_start_date between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd') or "+
		    " act_end_date between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')"+
		    " ) and mem_no= ?";
    private static final String CHK_JOINACT_DATE=
    		" select count(1) as cnt from (select a.act_start_date,a.act_end_date from joinactivity ja,activity a "+
            " where ja.act_no=a.act_no and ja.mem_no=? and ja.act_no <> ? ) where ("+
   	  	 	" to_date(?,'yyyy-mm-dd')"+
            " between act_start_date and  act_end_date or "+
            " to_date(?,'yyyy-mm-dd')"+
            " between act_start_date and  act_end_date or"+
            " act_start_date between to_date(?,'yyyy-mm-dd')and to_date(?,'yyyy-mm-dd') or"+
            " act_end_date between to_date(?,'yyyy-mm-dd')and to_date(?,'yyyy-mm-dd')"+
            " ) ";
    
    private static final String FIND_ADDACT_BY_MEM="select * from activity where mem_no=? order by act_no desc";
    private static final String UPDATE_PUBLIC_STATUS="UPDATE activity set act_is_public= ? where mem_no = ? and act_no=?";
    private static final String FIND_MEM_INVITED_ACTS = 
			"select * from joinactivity ja,activity act where act.act_no = ja.act_no and ja.joinact_is_join=0 and ja.mem_no = ? order by act.act_no desc";
    private static final String FIND_TOP_ACTS = 
			"SELECT A.*, (ACT_JOINLIMIT - NVL(B.CNT, 0)) TOT, CNT, "+
    		"ROUND(DECODE(CNT, null, 0, (B.CNT / ACT_JOINLIMIT * 100)), 2) HOT_PRSN "+
            "FROM ACTIVITY A, (SELECT ACT_NO, COUNT(1) CNT FROM JOINACTIVITY WHERE joinact_is_join=1 GROUP BY ACT_NO) B "+
            "WHERE A.ACT_NO = B.ACT_NO (+) "+
            "AND A.act_start_date > (SELECT SYSDATE FROM dual) "+
            "AND A.act_is_public=0 "+ 
            "AND ROWNUM < ? "+
            "order by 18 DESC";
    
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con=null;
		
		PreparedStatement pstmt=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, activityVO.getMem_no());
			pstmt.setInt(2, activityVO.getLoc_no());
			pstmt.setInt(3, activityVO.getStroke_no());
			pstmt.setString(4, activityVO.getAct_name());
			pstmt.setString(5, activityVO.getAct_loc());
			pstmt.setDate(6, activityVO.getAct_start_date());
			pstmt.setDate(7, activityVO.getAct_end_date());
			pstmt.setString(8, activityVO.getAct_exp());
			pstmt.setBytes(9,activityVO.getAct_photo() );
			pstmt.setInt(10, activityVO.getAct_is_public());
			pstmt.setBytes(11, activityVO.getAct_act_route());
			pstmt.setBytes(12,activityVO.getAct_alti() );
			pstmt.setDouble(13, activityVO.getAct_km());
			pstmt.setInt(14, activityVO.getAct_joinlimit());
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
	
	public void insertNoStroke(ActivityVO activityVO) {
		Connection con=null;
		
		PreparedStatement pstmt=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT_NO_STROKE);
			pstmt.setInt(1, activityVO.getMem_no());
			pstmt.setInt(2, activityVO.getLoc_no());
			pstmt.setString(3, activityVO.getAct_name());
			pstmt.setString(4, activityVO.getAct_loc());
			pstmt.setDate(5, activityVO.getAct_start_date());
			pstmt.setDate(6, activityVO.getAct_end_date());
			pstmt.setString(7, activityVO.getAct_exp());
		//	ByteArrayInputStream in0= new ByteArrayInputStream(activityVO.getAct_photo());
		//	pstmt.setBinaryStream(8,in0,in0.available());
			pstmt.setBytes(8,activityVO.getAct_photo() );
			pstmt.setInt(9, activityVO.getAct_is_public());
			pstmt.setBytes(10,activityVO.getAct_act_route() );
			pstmt.setBytes(11,activityVO.getAct_alti());
			pstmt.setDouble(12, activityVO.getAct_km());
			pstmt.setInt(13, activityVO.getAct_joinlimit());
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
	public void update(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, activityVO.getLoc_no());
			pstmt.setString(2, activityVO.getAct_name());
			pstmt.setString(3, activityVO.getAct_loc());
			pstmt.setDate(4, activityVO.getAct_start_date());
			pstmt.setDate(5, activityVO.getAct_end_date());
			pstmt.setString(6, activityVO.getAct_exp());
			pstmt.setBytes(7,activityVO.getAct_photo() );
			pstmt.setInt(8, activityVO.getAct_is_public());
			pstmt.setBytes(9, activityVO.getAct_act_route());
			pstmt.setBytes(10,activityVO.getAct_alti() );
			pstmt.setDouble(11, activityVO.getAct_km());
			pstmt.setInt(12, activityVO.getAct_joinlimit());
			pstmt.setInt(13, activityVO.getStroke_no());
			pstmt.setInt(14, activityVO.getMem_no());
			pstmt.setInt(15, activityVO.getAct_no());
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
	public void updateNoStroke(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_NO_STROKE);

			pstmt.setInt(1, activityVO.getLoc_no());
			pstmt.setString(2, activityVO.getAct_name());
			pstmt.setString(3, activityVO.getAct_loc());
			pstmt.setDate(4, activityVO.getAct_start_date());
			pstmt.setDate(5, activityVO.getAct_end_date());
			pstmt.setString(6, activityVO.getAct_exp());
			pstmt.setBytes(7,activityVO.getAct_photo() );
			pstmt.setInt(8, activityVO.getAct_is_public());
			pstmt.setBytes(9, activityVO.getAct_act_route());
			pstmt.setBytes(10,activityVO.getAct_alti() );
			pstmt.setDouble(11, activityVO.getAct_km());
			pstmt.setInt(12, activityVO.getAct_joinlimit());
			pstmt.setNull(13, java.sql.Types.INTEGER); 
			pstmt.setInt(14, activityVO.getMem_no());
			pstmt.setInt(15, activityVO.getAct_no());
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
	public void delete(Integer act_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, act_no);

			pstmt.executeUpdate();

			
		}catch (SQLException se) {
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
	public ActivityVO findByPrimaryKey(Integer act_no) {
		ActivityVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, act_no);

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
				actVO.setAct_photo(rs.getBytes("act_photo"));
				actVO.setAct_is_public(rs.getInt("act_is_public"));
				actVO.setAct_act_route(rs.getBytes("act_act_route"));
				actVO.setAct_alti(rs.getBytes("act_alti"));
				actVO.setAct_km(rs.getDouble("act_km"));
				actVO.setAct_joinlimit(rs.getInt("act_joinlimit"));
				
				
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
		return actVO;
	}

	@Override
	public List<ActivityVO> getAll() {
		
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 也稱為 Domain objects
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
	
public List<ActivityVO> getAllActRecent() {
		
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLACT_NEW);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 也稱為 Domain objects
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

	public ActivityVO findActByMem_no(Integer mem_no,Integer act_no) {
		ActivityVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOOKUP_MEM_IS_MACT);
	
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, act_no);
	
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
				actVO.setAct_photo(rs.getBytes("act_photo"));
				actVO.setAct_is_public(rs.getInt("act_is_public"));
				actVO.setAct_act_route(rs.getBytes("act_act_route"));
				actVO.setAct_alti(rs.getBytes("act_alti"));
				actVO.setAct_km(rs.getDouble("act_km"));
				actVO.setAct_joinlimit(rs.getInt("act_joinlimit"));
				
				
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
		return actVO;
	}
    //列出會員已參加的揪團
	@Override
	public List<ActivityVO> findMemJoinedActs(Integer mem_no) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_MEM_JOINED_ACTS);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 也稱為 Domain objects
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
	public List<ActivityVO> findMemInvitedActs(Integer mem_no) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_MEM_INVITED_ACTS);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 也稱為 Domain objects
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
	public int chkActDate(String in_start,String in_end,Integer mem_no) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt=0;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHK_ACT_DATE);
			pstmt.setString(1, in_start);
			pstmt.setString(2, in_end);
			pstmt.setString(3, in_start);
			pstmt.setString(4, in_end);
			pstmt.setString(5, in_start);
			pstmt.setString(6, in_end);
			pstmt.setInt(7, mem_no);
			rs = pstmt.executeQuery();
		    rs.next();
		    cnt=rs.getInt("cnt");
				
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
		return cnt;
	}
	@Override
	public int chkJoinActDate(String in_start,String in_end,Integer mem_no,Integer act_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt=0;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHK_JOINACT_DATE);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, act_no);
			pstmt.setString(3, in_start);
			pstmt.setString(4, in_end);
			pstmt.setString(5, in_start);
			pstmt.setString(6, in_end);
			pstmt.setString(7, in_start);
			pstmt.setString(8, in_end);
			rs = pstmt.executeQuery();
		    rs.next();
		    cnt=rs.getInt("cnt");
				
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
		return cnt;
	}

	@Override
	public List<ActivityVO> findAddActsByMemno(Integer mem_no) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ADDACT_BY_MEM);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 也稱為 Domain objects
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
	public void updatePublic(Integer act_is_public,Integer mem_no, Integer act_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PUBLIC_STATUS);
			pstmt.setInt(1,act_is_public);
			pstmt.setInt(2,mem_no);
			pstmt.setInt(3,act_no);
			
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
	
	public List<ActivityVO> getAll(Map<String, String[]> map) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s_date="";
		String e_date="";
			
			
			//----拆出map的param
			Set<String> keys = map.keySet();
			
			
			for (String key : keys) {
				String value = map.get(key)[0];
				//System.out.println(key);
				if (key.trim().equals("act_start_date")) {
					s_date=value.trim();
				}
				if (key.trim().equals("act_end_date")) {
					e_date=value.trim();
				}
			}
			
			if(s_date.equals("")&&!e_date.equals("")){
				s_date=e_date;
			}
			if(!s_date.equals("")&&e_date.equals("")){
				e_date=s_date;
			}
			
			try {
			con = ds.getConnection();	
			if(!s_date.equals("")&&!e_date.equals("")){

				String finalSQL = 
						" select * from activity where ("+
								  " to_date('"+s_date+"','yyyy-mm-dd')"+
						          " between act_start_date and  act_end_date or "+
						          " to_date('"+e_date+"','yyyy-mm-dd')"+
						          " between act_start_date and  act_end_date or"+
						          " act_start_date between to_date('"+s_date+"','yyyy-mm-dd')and to_date('"+e_date+"','yyyy-mm-dd') or"+
						          " act_end_date between to_date('"+s_date+"','yyyy-mm-dd')and to_date('"+e_date+"','yyyy-mm-dd')) "		       
			            + CompositeQuery_Act.get_AndCondition(map)
			            + "order by act_no";
				
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			}else{
				String finalSQL = 
						" select * from activity "		       
			            + CompositeQuery_Act.get_WhereCondition(map)
			            + "order by act_no";
				
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			}
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
				list.add(actVO); // Store the row in the List
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

	@Override
	public List<ActivityVO> findMemJoinedActs(Map<String, String[]> map) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s_date="";
		String e_date="";
		String mem_no="";	
			
			//----拆出map的param
			Set<String> keys = map.keySet();

			for (String key : keys) {
				String value = map.get(key)[0];
				//System.out.println(key);
				if (key.trim().equals("act_start_date")) {
					s_date=value.trim();
				}
				if (key.trim().equals("act_end_date")) {
					e_date=value.trim();
				}
				if (key.trim().equals("mem_no")) {
					mem_no=value.trim();
				}
			}
			
			if(s_date.equals("")&&!e_date.equals("")){
				s_date=e_date;
			}
			if(!s_date.equals("")&&e_date.equals("")){
				e_date=s_date;
			}
			
			try {
			con = ds.getConnection();	
			if(!s_date.equals("")&&!e_date.equals("")){

				String finalSQL = 
						" select * from (select a.* from joinactivity ja,activity  a where ja.act_no=a.act_no "+
								  " and ja.mem_no="+mem_no+") where ("+
								  " to_date('"+s_date+"','yyyy-mm-dd')"+
						          " between act_start_date and  act_end_date or "+
						          " to_date('"+e_date+"','yyyy-mm-dd')"+
						          " between act_start_date and  act_end_date or"+
						          " act_start_date between to_date('"+s_date+"','yyyy-mm-dd')and to_date('"+e_date+"','yyyy-mm-dd') or"+
						          " act_end_date between to_date('"+s_date+"','yyyy-mm-dd')and to_date('"+e_date+"','yyyy-mm-dd')) "		       
			            + CompositeQuery_Act.get_AndConditionByJoinAct(map)
			            + "order by act_no";
				
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			}else{
				String finalSQL = 
						" select * from (select a.* from joinactivity ja,activity  a where ja.act_no=a.act_no "+
								  " and ja.mem_no="+mem_no+") "		       
			            + CompositeQuery_Act.get_WhereConditionByJoinAct(map)
			            + "order by act_no";
				
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			}
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
				list.add(actVO); // Store the row in the List
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

	@Override
	public List<ActivityVO> findTopActs(Integer top) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_TOP_ACTS);
			pstmt.setInt(1, top);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 也稱為 Domain objects
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
