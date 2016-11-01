package com.joinactivity.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class JoinactivityDAO implements JoinactivityDAO_interface{
	
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
	
	private static final String INSERT_STMT = "INSERT INTO joinactivity (act_no,mem_no,joinact_is_join,joinact_score,joinact_lati,joinact_longi)"+
	 " VALUES ( ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE joinactivity set joinact_is_join=?, joinact_score=?, joinact_lati=?, joinact_longi=? where act_no = ? and mem_no=? ";
	private static final String DELETE = 
			"DELETE FROM joinactivity where act_no = ? and mem_no=?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM joinactivity order by act_no";
	private static final String GET_ACTS_BY_MEM = 
			"select * from joinactivity where mem_no= ?";
	private static final String GET_MEMS_BY_ACT = 
			"select * from joinactivity where act_no= ? order by mem_no" ;
	private static final String GET_JOINMEMS_BY_ACT = 
			"select * from joinactivity where act_no= ? and joinact_is_join=1 order by mem_no" ;
	private static final String LOOKUP_MEM_IS_IN_ACT = 
			"SELECT * FROM joinactivity where act_no = ? and mem_no= ?";
	private static final String DELETE_ONEJOINACT = 
			"DELETE FROM joinactivity where act_no = ?";
	private static final String GET_CNJ_MEMS_BY_ACT = 
			"select * from joinactivity where act_no= ? and (joinact_is_join=1 or joinact_is_join=0) order by mem_no" ;
	private static final String GET_CNTMEMS_BY_ACT = 
			"select count(1) as cnt  from joinactivity where act_no= ? and joinact_is_join=1" ;
	private static final String GET_CNTNOSURE_BY_MEMNO = 
			"select count(1) as cnt from joinactivity where mem_no=? and joinact_is_join=0" ;
	@Override
	public void insert(JoinactivityVO joinactivityVO) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, joinactivityVO.getAct_no());
			pstmt.setInt(2, joinactivityVO.getMem_no());
			pstmt.setInt(3, joinactivityVO.getJoinact_is_join());
			pstmt.setInt(4, joinactivityVO.getJoinact_score());
			pstmt.setDouble(5, joinactivityVO.getJoinact_lati());
			pstmt.setDouble(6, joinactivityVO.getJoinact_longi());
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
	public void update(JoinactivityVO joinactivityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, joinactivityVO.getJoinact_is_join());
			pstmt.setInt(2, joinactivityVO.getJoinact_score());
			pstmt.setDouble(3, joinactivityVO.getJoinact_lati());
			pstmt.setDouble(4, joinactivityVO.getJoinact_longi());
			pstmt.setInt(5, joinactivityVO.getAct_no());
			pstmt.setInt(6, joinactivityVO.getMem_no());	
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
	public void delete(Integer act_no,Integer mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, act_no);
			pstmt.setInt(2, mem_no);	
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
	public List<JoinactivityVO> findActsByMem(Integer mem_no) {
		JoinactivityVO joinactivityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JoinactivityVO> list = new ArrayList<JoinactivityVO>();
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACTS_BY_MEM);

			pstmt.setInt(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// privilegedVO 也稱為 Domain objects
				joinactivityVO = new JoinactivityVO();
				joinactivityVO.setAct_no(rs.getInt("act_no"));
				joinactivityVO.setMem_no(rs.getInt("mem_no"));
				joinactivityVO.setJoinact_is_join(rs.getInt("joinact_is_join"));
				joinactivityVO.setJoinact_score(rs.getInt("joinact_score"));
				joinactivityVO.setJoinact_lati(rs.getDouble("joinact_lati"));
				joinactivityVO.setJoinact_longi(rs.getDouble("joinact_longi"));
				list.add(joinactivityVO); // Store the row in the list
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
	public List<JoinactivityVO> findMemsByAct(Integer act_no) {
		JoinactivityVO joinactivityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JoinactivityVO> list = new ArrayList<JoinactivityVO>();
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMS_BY_ACT);

			pstmt.setInt(1, act_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// privilegedVO 也稱為 Domain objects
				joinactivityVO = new JoinactivityVO();
				joinactivityVO.setAct_no(rs.getInt("act_no"));
				joinactivityVO.setMem_no(rs.getInt("mem_no"));
				joinactivityVO.setJoinact_is_join(rs.getInt("joinact_is_join"));
				joinactivityVO.setJoinact_score(rs.getInt("joinact_score"));
				joinactivityVO.setJoinact_lati(rs.getDouble("joinact_lati"));
				joinactivityVO.setJoinact_longi(rs.getDouble("joinact_longi"));
				list.add(joinactivityVO); // Store the row in the list
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
	public List<JoinactivityVO> findJoinSureMemsByAct(Integer act_no) {
		JoinactivityVO joinactivityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JoinactivityVO> list = new ArrayList<JoinactivityVO>();
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_JOINMEMS_BY_ACT);

			pstmt.setInt(1, act_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// privilegedVO 也稱為 Domain objects
				joinactivityVO = new JoinactivityVO();
				joinactivityVO.setAct_no(rs.getInt("act_no"));
				joinactivityVO.setMem_no(rs.getInt("mem_no"));
				joinactivityVO.setJoinact_is_join(rs.getInt("joinact_is_join"));
				joinactivityVO.setJoinact_score(rs.getInt("joinact_score"));
				joinactivityVO.setJoinact_lati(rs.getDouble("joinact_lati"));
				joinactivityVO.setJoinact_longi(rs.getDouble("joinact_longi"));
				list.add(joinactivityVO); // Store the row in the list
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
	public List<JoinactivityVO> getAll() {
		
		List<JoinactivityVO> list = new ArrayList<JoinactivityVO>();
		JoinactivityVO joinactivityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 也稱為 Domain objects
				joinactivityVO = new JoinactivityVO();
				joinactivityVO.setAct_no(rs.getInt("act_no"));
				joinactivityVO.setMem_no(rs.getInt("mem_no"));
				joinactivityVO.setJoinact_is_join(rs.getInt("joinact_is_join"));
				joinactivityVO.setJoinact_score(rs.getInt("joinact_score"));
				joinactivityVO.setJoinact_lati(rs.getDouble("joinact_lati"));
				joinactivityVO.setJoinact_longi(rs.getDouble("joinact_longi"));

				list.add(joinactivityVO); // Store the row in the list
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
	
	
	public Set<JoinactivityVO> findJoinMemsByActno(Integer act_no) {
		Set<JoinactivityVO> set = new LinkedHashSet<JoinactivityVO>();
		JoinactivityVO jaVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMS_BY_ACT);
			pstmt.setInt(1, act_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				jaVO = new JoinactivityVO();
				jaVO.setAct_no(rs.getInt("act_no"));
				jaVO.setMem_no(rs.getInt("mem_no"));
				jaVO.setJoinact_is_join(rs.getInt("joinact_is_join"));
				jaVO.setJoinact_score(rs.getInt("joinact_score"));
				jaVO.setJoinact_lati(rs.getDouble("joinact_lati"));
				jaVO.setJoinact_longi(rs.getDouble("joinact_longi"));
				set.add(jaVO); // Store the row in the vector
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
		return set;
	}
	
	public JoinactivityVO findMemIsInAct(Integer act_no,Integer mem_no){
		JoinactivityVO jaVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOOKUP_MEM_IS_IN_ACT);
			pstmt.setInt(1, act_no);
			pstmt.setInt(2, mem_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				jaVO = new JoinactivityVO();
				jaVO.setAct_no(rs.getInt("act_no"));
				jaVO.setMem_no(rs.getInt("mem_no"));
				jaVO.setJoinact_is_join(rs.getInt("joinact_is_join"));
				jaVO.setJoinact_score(rs.getInt("joinact_score"));
				jaVO.setJoinact_lati(rs.getDouble("joinact_lati"));
				jaVO.setJoinact_longi(rs.getDouble("joinact_longi"));
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
		return jaVO;
	}

	@Override
	public void delete(Integer act_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ONEJOINACT);
			pstmt.setInt(1, act_no);
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
	public List<JoinactivityVO> findCnJMemsByAct(Integer act_no) {
		JoinactivityVO joinactivityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JoinactivityVO> list = new ArrayList<JoinactivityVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CNJ_MEMS_BY_ACT);
			pstmt.setInt(1, act_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// privilegedVO 也稱為 Domain objects
				joinactivityVO = new JoinactivityVO();
				joinactivityVO.setAct_no(rs.getInt("act_no"));
				joinactivityVO.setMem_no(rs.getInt("mem_no"));
				joinactivityVO.setJoinact_is_join(rs.getInt("joinact_is_join"));
				joinactivityVO.setJoinact_score(rs.getInt("joinact_score"));
				joinactivityVO.setJoinact_lati(rs.getDouble("joinact_lati"));
				joinactivityVO.setJoinact_longi(rs.getDouble("joinact_longi"));
				list.add(joinactivityVO); // Store the row in the list
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
	public int findCntMemsByAct(Integer act_no) {
		int cnt=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CNTMEMS_BY_ACT);
			pstmt.setInt(1, act_no);
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
	public int findCntNoSureByMem(Integer mem_no) {
		int cnt=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CNTNOSURE_BY_MEMNO);
			pstmt.setInt(1, mem_no);
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
	
}
