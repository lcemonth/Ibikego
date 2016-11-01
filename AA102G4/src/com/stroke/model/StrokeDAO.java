package com.stroke.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpDAO_interface;
import com.emp.model.EmpVO;
import com.strokedetails.model.StrokeDetailsDAO;
import com.strokedetails.model.StrokeDetailsVO;

public class StrokeDAO implements StrokeDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO stroke (stroke_no,mem_no,stroke_name,builddate) VALUES (stroke_seq.NEXTVAL,?,?,?)";
	private static final String GET_ALL_STMT ="SELECT stroke_no,mem_no,stroke_name  FROM Stroke order by stroke_no";
	private static final String GET_ONE_STMT = "SELECT stroke_no,mem_no,stroke_name  FROM Stroke where stroke_no = ?";
	private static final String DELETE = "DELETE FROM Stroke where stroke_no = ?";
	private static final String UPDATE ="UPDATE Stroke set stroke_no=?,mem_no=?,stroke_name=? where stroke_no=?";
	private static final String GET_MEM_ALL_STROKE = "SELECT stroke_no,mem_no,stroke_name  FROM Stroke where mem_no = ?";
	private static final String GETMEMBERSTROKEALL="SELECT stroke_no ,mem_no,stroke_name,builddate  FROM stroke where mem_no = ?  ORDER BY stroke_no DESC";
	@Override
	public int insert(StrokeVO strokeVO,Map strokeList) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			String cols[] = { "stroke_no" }; //或 int cols[] = {1};
			
			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			con.setAutoCommit(false);
			
			
			pstmt.setInt(1, strokeVO.getMem_no());
			pstmt.setString(2, strokeVO.getStroke_name());
			pstmt.setDate(3,strokeVO.getBuildDate());
			updateCount = pstmt.executeUpdate();
			
			
			List list=new ArrayList();
			ResultSet rsKeys = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rsKeys.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rsKeys.next()) {
				for(int i=0;i<strokeList.size();i++){
					StrokeDetailsDAO strokeDetails= new StrokeDetailsDAO();
					StrokeDetailsVO strokeDetailsVO =new StrokeDetailsVO();
					list =(ArrayList) strokeList.get((i+1));
					for (int j = 0; j < list.size(); j++) {
						Integer key = rsKeys.getInt(columnCount);
						strokeDetailsVO.setStroke_no(key);	//行程編號
						strokeDetailsVO.setStroke_whichday((i+1));	//行程天數(第幾天行程)
						strokeDetailsVO.setTra_no((Integer)list.get(j));	//旅遊點編號

						strokeDetails.insert(strokeDetailsVO,con);
					}
				}
			}else{
				System.out.println("NO KEYS WERE GENERATED.");
			}
			
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
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}
	@Override
	public int update(StrokeVO strokeVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, strokeVO.getStroke_no());
			pstmt.setInt(2, strokeVO.getMem_no());
			pstmt.setString(3, strokeVO.getStroke_name());
			pstmt.setInt(4, strokeVO.getStroke_no());


			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}
	@Override
	public int delete(Integer stroke_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			new StrokeDetailsDAO().delete(stroke_no,con);
			StrokeDetailsDAO strokeDetails= new StrokeDetailsDAO();
			
			
			pstmt.setInt(1, stroke_no);
			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}
	@Override
	public StrokeVO findByPrimaryKey(Integer stroke_no) {

		StrokeVO strokeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, stroke_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				strokeVO = new StrokeVO();
					strokeVO.setStroke_no(rs.getInt("stroke_no"));
					strokeVO.setMem_no(rs.getInt("mem_no"));
					strokeVO.setStroke_name(rs.getString("stroke_name"));
					strokeVO.setBuildDate(rs.getDate("builddate"));
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
		return strokeVO;
	}
	@Override
	public List<StrokeVO> getAll() {
		List<StrokeVO> list = new ArrayList<StrokeVO>();
		StrokeVO strokeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				strokeVO = new StrokeVO();
					strokeVO.setStroke_no(rs.getInt("stroke_no"));
					strokeVO.setMem_no(rs.getInt("mem_no"));
					strokeVO.setStroke_name(rs.getString("stroke_name"));
				list.add(strokeVO); 
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
    
	public List<StrokeVO> findStrokesByMem_no(Integer mem_no) {
		List<StrokeVO> list = new ArrayList<StrokeVO>();
		StrokeVO strokeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_ALL_STROKE);
			
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
					strokeVO = new StrokeVO();
					strokeVO.setStroke_no(rs.getInt("stroke_no"));
					strokeVO.setMem_no(rs.getInt("mem_no"));
					strokeVO.setStroke_name(rs.getString("stroke_name"));
					list.add(strokeVO); 
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
	public List<StrokeVO> getMemberStrokeAll(Integer mem_no) {
		List<StrokeVO> list = new ArrayList<StrokeVO>();
		StrokeVO strokeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETMEMBERSTROKEALL);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				strokeVO = new StrokeVO();
//					empVO.setEmp_no(rs.getInt("emp_no"));
				strokeVO.setStroke_no(rs.getInt("stroke_no"));
				strokeVO.setMem_no(rs.getInt("mem_no"));
				strokeVO.setStroke_name(rs.getString("stroke_name"));
				strokeVO.setBuildDate(rs.getDate("builddate"));
				list.add(strokeVO);
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
