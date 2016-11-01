package com.blogScore.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.travelScore.model.TravelScoreVO;

public class BlogScoreDAO implements BlogScoreDAO_interface{
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
	        "INSERT INTO blogscore (blog_no,mem_no,blog_score,blog_score_status) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = 
	    	"SELECT blog_no,mem_no,blog_score,blog_score_status FROM blogscore order by blog_no";
	private static final String GET_ONE_STMT = 
	    	"SELECT blog_no,mem_no,blog_score,blog_score_status FROM blogscore where blog_no = ?";
	/***/
	private static final String CHECK = 
	    	"SELECT * FROM blogscore where blog_no = ? and mem_no = ?";
	private static final String GET_ONE_BLOGSCORE_STMT = 
	    	"SELECT COUNT ('blog_score') totalscore FROM blogscore where blog_no = ?";
	/***/
	@Override
	public BlogScoreVO getCheck(Integer blog_no,Integer mem_no){
		BlogScoreVO blogScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK);
			pstmt.setInt(1,blog_no);
			pstmt.setInt(2,mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// travelScoreVO 也稱為 Domain objects
				blogScoreVO = new BlogScoreVO();
				blogScoreVO.setBlog_no(rs.getInt("blog_no"));
				blogScoreVO.setMem_no(rs.getInt("mem_no"));
				blogScoreVO.setBlog_score(rs.getInt("blog_score"));
				blogScoreVO.setBlog_score_status(rs.getInt("blog_score_status"));
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
		return blogScoreVO;
	}//getCheck
	/***/
	
    @Override
	public void insert(BlogScoreVO blogScoreVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,blogScoreVO.getBlog_no());
			pstmt.setInt(2,blogScoreVO.getMem_no());
			pstmt.setInt(3,blogScoreVO.getBlog_score());
			pstmt.setInt(4,blogScoreVO.getBlog_score_status());
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
	public BlogScoreVO findByPrimaryKey(Integer blog_no){
		BlogScoreVO blogScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,blog_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// blogScoreVO 也稱為 Domain objects
				blogScoreVO = new BlogScoreVO();
				blogScoreVO.setBlog_no(rs.getInt("blog_no"));
				blogScoreVO.setMem_no(rs.getInt("mem_no"));
				blogScoreVO.setBlog_score(rs.getInt("blog_score"));
				blogScoreVO.setBlog_score_status(rs.getInt("blog_score_status"));
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
		return blogScoreVO;
	}//findByPrimaryKey

	@Override
	public List<BlogScoreVO> getAll(){
		List<BlogScoreVO> list = new ArrayList<BlogScoreVO>();
		BlogScoreVO blogScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// blogScoreVO 也稱為 Domain objects
				blogScoreVO = new BlogScoreVO();
				blogScoreVO.setBlog_no(rs.getInt("blog_no"));
				blogScoreVO.setMem_no(rs.getInt("mem_no"));
				blogScoreVO.setBlog_score(rs.getInt("blog_score"));
				blogScoreVO.setBlog_score_status(rs.getInt("blog_score_status"));
				list.add(blogScoreVO); // Store the row in the list
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
	
	/********************************************************************/
	@Override
	public BlogScoreVO getOneBlogScore(Integer blog_no){
		BlogScoreVO blogScoreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BLOGSCORE_STMT);
			pstmt.setInt(1,blog_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// blogScoreVO 也稱為 Domain objects
				blogScoreVO = new BlogScoreVO();
				blogScoreVO.setTotalScore(rs.getInt("totalscore"));
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
		return blogScoreVO;
	}//getOneTravelScore
}