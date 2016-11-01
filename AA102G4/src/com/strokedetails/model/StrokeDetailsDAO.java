package com.strokedetails.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.activity.model.ActivityVO;
import com.stroke.model.StrokeVO;

public class StrokeDetailsDAO implements StrokeDetailsDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"Insert into STROKEDETAILS (stroke_det_no,stroke_no,tra_no,stroke_whichday) values (STROKEDETAILS_SEQ.NEXTVAL,?,?,?)";
	private static final String DELETE="DELETE FROM STROKEDETAILS where stroke_no = ?";
	private static final String SELECTDAYS="SELECT max(stroke_whichday) days FROM STROKEDETAILS where Stroke_no=?";
	private static final String DAYSITINERARY ="SELECT * FROM STROKEDETAILS where Stroke_no=? and stroke_whichday =?";
	private static final String  GETALLDETAILEDITINERARY ="SELECT * FROM STROKEDETAILS where Stroke_no=?";
	private static final String GET_ALL_STMT ="SELECT stroke_det_no,stroke_no,tra_no,stroke_whichday  FROM StrokeDetails order by stroke_det_no";
	private static final String GET_ONE_STMT = "SELECT stroke_det_no,stroke_no,tra_no,stroke_whichday  FROM StrokeDetails where stroke_det_no=?";
	private static final String UPDATE ="UPDATE StrokeDetails set stroke_det_no＝?,stroke_no=?,tra_no=?,stroke_whichday=? where stroke_det_no=?";
    private static final String GET_DAY_OF_TRAVELS_BY_SRROKENO ="SELECT stroke_det_no,stroke_no,tra_no,stroke_whichday  FROM StrokeDetails where stroke_no=? and stroke_whichday=?";
	private static final String GET_MAXDAY_BY_STROKENO="SELECT  MAX(stroke_whichday) FROM strokedetails where stroke_no=? ";
	@Override
	public int insert(StrokeDetailsVO strokeDetailsVO, Connection con) {
		int updateCount = 0;
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			con.setAutoCommit(false);
			
//			pstmt.setInt(1, empVO.getEmp_no());
			pstmt.setInt(1, strokeDetailsVO.getStroke_no());
			pstmt.setInt(2, strokeDetailsVO.getTra_no());
			pstmt.setInt(3, strokeDetailsVO.getStroke_whichday());
			updateCount = pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
//					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int update(StrokeDetailsVO strokeDetailsVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(UPDATE);
				pstmt.setInt(1, strokeDetailsVO.getStroke_det_no());
				pstmt.setInt(2, strokeDetailsVO.getStroke_no());
				pstmt.setInt(3, strokeDetailsVO.getTra_no());
				pstmt.setInt(4, strokeDetailsVO.getStroke_whichday());
				pstmt.setInt(5, strokeDetailsVO.getStroke_det_no());
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return updateCount;
	}

	@Override
	public int delete(Integer stroke_no,Connection con) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DELETE);
			con.setAutoCommit(false);
			
			pstmt.setInt(1, stroke_no);
			updateCount = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
//					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}
	@Override
	public StrokeDetailsVO findByPrimaryKey(Integer stroke_det_no) {

		StrokeDetailsVO strokeDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, stroke_det_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				strokeDetailsVO = new StrokeDetailsVO();
				strokeDetailsVO.setStroke_det_no(rs.getInt("stroke_det_no"));
				strokeDetailsVO.setStroke_no(rs.getInt("stroke_no"));
				strokeDetailsVO.setTra_no(rs.getInt("tra_no"));
				strokeDetailsVO.setStroke_whichday(rs.getInt("stroke_whichday"));
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return strokeDetailsVO;
	}

	@Override
	public List<StrokeDetailsVO> getAll() {
		List<StrokeDetailsVO> list = new ArrayList<StrokeDetailsVO>();
		StrokeDetailsVO strokeDetailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				strokeDetailsVO = new StrokeDetailsVO();
				strokeDetailsVO.setStroke_det_no(rs.getInt("stroke_det_no"));
				strokeDetailsVO.setStroke_no(rs.getInt("stroke_no"));
				strokeDetailsVO.setTra_no(rs.getInt("tra_no"));
				strokeDetailsVO.setStroke_whichday(rs.getInt("stroke_whichday"));
				list.add(strokeDetailsVO); 
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
	public int selectDays(Integer stroke_no) {
		Integer days= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECTDAYS);
			
			pstmt.setInt(1, stroke_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				days=rs.getInt("days");
			}
//			empVO.setEmp_no(rs.getInt("emp_no"));


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
		return days;
	}
	@Override
	public List<StrokeDetailsVO> daysItinerary(Integer stroke_no, Integer days) {
		StrokeDetailsVO strokeDetailsVO = null;
		List<StrokeDetailsVO> list = new ArrayList<StrokeDetailsVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DAYSITINERARY);
			
			pstmt.setInt(1, stroke_no);
			pstmt.setInt(2, days);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				strokeDetailsVO =new StrokeDetailsVO();
					strokeDetailsVO.setStroke_det_no(rs.getInt("Stroke_det_no"));
					strokeDetailsVO.setStroke_no(rs.getInt("stroke_no"));
					strokeDetailsVO.setTra_no(rs.getInt("tra_no"));
					strokeDetailsVO.setStroke_whichday(rs.getInt("stroke_whichday"));
				list.add(strokeDetailsVO);
			}
//			empVO.setEmp_no(rs.getInt("emp_no"));


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
	public List<StrokeDetailsVO> getAllDetailedItinerary(Integer stroke_no) {
		List<StrokeDetailsVO> list = new ArrayList<StrokeDetailsVO>();
		StrokeDetailsVO strokeDetailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALLDETAILEDITINERARY);
			
			pstmt.setInt(1, stroke_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				strokeDetailsVO = new StrokeDetailsVO();
//					empVO.setEmp_no(rs.getInt("emp_no"));
				strokeDetailsVO.setStroke_det_no(rs.getInt("Stroke_det_no"));
				strokeDetailsVO.setStroke_no(rs.getInt("Stroke_no"));
				strokeDetailsVO.setTra_no(rs.getInt("Tra_no"));
				strokeDetailsVO.setStroke_whichday(rs.getInt("Stroke_whichday"));

				list.add(strokeDetailsVO); 
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
	//---------------合-----------------
	public List<StrokeDetailsVO> getDayOfTravelsByStrokeNo(Integer stroke_no,Integer stroke_whichday){
		
		List<StrokeDetailsVO> list = new ArrayList<StrokeDetailsVO>();
		StrokeDetailsVO strokeDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DAY_OF_TRAVELS_BY_SRROKENO);
			pstmt.setInt(1, stroke_no);
			pstmt.setInt(2, stroke_whichday);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				strokeDetailsVO = new StrokeDetailsVO();
				strokeDetailsVO.setStroke_det_no(rs.getInt("stroke_det_no"));
				strokeDetailsVO.setStroke_no(rs.getInt("stroke_no"));
				strokeDetailsVO.setTra_no(rs.getInt("tra_no"));
				strokeDetailsVO.setStroke_whichday(rs.getInt("stroke_whichday"));
				list.add(strokeDetailsVO); 
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
	
	public int getMaxDayByStrokeNo(Integer stroke_no){
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxDay;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MAXDAY_BY_STROKENO);
			pstmt.setInt(1, stroke_no);
			rs = pstmt.executeQuery();
			rs.next();
			maxDay=rs.getInt("MAX(stroke_whichday)");
			

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
		return maxDay;
	}
	//---------------合-----------------
	
}
