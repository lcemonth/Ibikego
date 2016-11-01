package com.blog.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BlogDAO implements BlogDAO_interface{
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
	        "INSERT INTO BLOG (blog_no,mem_no,blog_title,blog_content,blog_cre,blog_photo,blog_del) VALUES (BLOG_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
	    	"SELECT blog_no,mem_no,blog_title,blog_content,to_char(blog_cre,'yyyy-mm-dd') blog_cre,blog_photo,blog_del FROM BLOG order by blog_no desc";
	private static final String GET_ONE_STMT = 
	    	"SELECT blog_no,mem_no,blog_title,blog_content,to_char(blog_cre,'yyyy-mm-dd') blog_cre,blog_photo,blog_del FROM BLOG where blog_no = ?";
	private static final String DELETE = 
	    	"DELETE FROM BLOG where blog_no = ?";
	private static final String UPDATE = 
	    	"UPDATE BLOG set blog_title=?,blog_content=?,blog_cre=?,blog_photo=?,blog_del=? where blog_no = ?";
	private static final String UPDATEDEL = 
	    	"UPDATE BLOG set blog_del=1 where blog_no = ?";
	/*******************************************************0829***********************************************************/
	private static final String getMyBlogs = 
	    	"SELECT * from blog where mem_no = ?";
	@Override
	public void insert(BlogVO blogVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,blogVO.getMem_no());
			pstmt.setString(2,blogVO.getBlog_title());
			pstmt.setString(3,blogVO.getBlog_content());
			pstmt.setDate(4,blogVO.getBlog_cre());
			pstmt.setBytes(5,blogVO.getBlog_photo());
			pstmt.setInt(6,blogVO.getBlog_del());
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
	public void update(BlogVO blogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,blogVO.getBlog_title());
			pstmt.setString(2,blogVO.getBlog_content());
			pstmt.setDate(3,blogVO.getBlog_cre());
			pstmt.setBytes(4,blogVO.getBlog_photo());
			pstmt.setInt(5,blogVO.getBlog_del());
			pstmt.setInt(6,blogVO.getBlog_no());
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
	public void delete(Integer blog_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, blog_no);
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
	public void updateDel(Integer blog_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEDEL);
			pstmt.setInt(1, blog_no);
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
	public BlogVO findByPrimaryKey(Integer blog_no) {

		BlogVO blogVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, blog_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blogVO = new BlogVO();
				blogVO.setBlog_no(rs.getInt("blog_no"));
				blogVO.setMem_no(rs.getInt("mem_no"));
				blogVO.setBlog_title(rs.getString("blog_title"));
				blogVO.setBlog_content(rs.getString("blog_content"));
				blogVO.setBlog_cre(rs.getDate("blog_cre"));
				blogVO.setBlog_photo(rs.getBytes("blog_photo"));
				blogVO.setBlog_del(rs.getInt("blog_del"));
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
		return blogVO;
	}

	@Override
	public List<BlogVO> getAll() {
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				blogVO = new BlogVO();
				blogVO.setBlog_no(rs.getInt("blog_no"));
				blogVO.setMem_no(rs.getInt("mem_no"));
				blogVO.setBlog_title(rs.getString("blog_title"));
				blogVO.setBlog_content(rs.getString("blog_content"));
				blogVO.setBlog_cre(rs.getDate("blog_cre"));
				blogVO.setBlog_photo(rs.getBytes("blog_photo"));
				blogVO.setBlog_del(rs.getInt("blog_del"));
				list.add(blogVO); // Store the row in the list
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
	public List<BlogVO> getMyBlogs(Integer mem_no) {
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getMyBlogs);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// blogVO 也稱為 Domain objects
				blogVO = new BlogVO();
				blogVO.setBlog_no(rs.getInt("blog_no"));
				blogVO.setMem_no(rs.getInt("mem_no"));
				blogVO.setBlog_title(rs.getString("blog_title"));
				blogVO.setBlog_content(rs.getString("blog_content"));
				blogVO.setBlog_cre(rs.getDate("blog_cre"));
				blogVO.setBlog_photo(rs.getBytes("blog_photo"));
				blogVO.setBlog_del(rs.getInt("blog_del"));
				list.add(blogVO); // Store the row in the list
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
}