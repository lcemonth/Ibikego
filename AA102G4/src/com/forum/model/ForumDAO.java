package com.forum.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.blogScore.model.BlogScoreVO;
import com.emp.model.EmpVO;
import com.forumresponse.model.ForumresVO;
import com.reportcollect.model.ReportcollectVO;
/**8/29新增**/
import com.blog.model.BlogVO;
/**8/29新增**/
public class ForumDAO implements ForumDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
		"INSERT INTO forum (forum_no,mem_no,forum_title,forum_content,forum_cretime,forum_del) VALUES (forum_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT forum_no,mem_no,forum_title,forum_content,to_char(forum_cretime,'yyyy-mm-dd') forum_cretime,forum_del FROM forum order by forum_no desc  ";
	private static final String GET_ONE_STMT = 
		"SELECT forum_no,mem_no,forum_title,forum_content,to_char(forum_cretime,'yyyy-mm-dd') forum_cretime,forum_del FROM forum where forum_no = ?";
	
	private static final String GET_Forumres_ByForum_no_STMT = "SELECT forumres_no,forum_no,mem_no,forumres_con,to_char(forumres_cretime,'yyyy-mm-dd') forumres_cretime FROM forumresponse where forum_no = ? order by forumres_no";																															
	
	private static final String DELETE_Forumres = "DELETE FROM forumresponse where forum_no = ?";
	private static final String DELETE_Forum = "DELETE FROM forum where forum_no = ?";	
	
	private static final String DELETE_Mem = "UPDATE forum set forum_del=1 where forum_no = ?";
	private static final String UPDATE = "UPDATE forum set forum_title=?, forum_content=? ,forum_cretime=? where forum_no = ?";
	private static final String GET_ALL_FORUM_NEW = 
			"SELECT * FROM (select * from forum order by forum_cretime desc)  where forum_del=0 and rownum < 6";
	private static final String GET_ALL_BlogScore_NEW = 
			"SELECT * FROM (select * from blogScore order by blog_score desc)  where  rownum < 4";
	/****檢舉新增******/
	private static final String INSERT_HANDLE = 
			"INSERT INTO ReportCollect (rc_no,mem_no,forum_no,rc_rep_handle,rc_col_status,rep_rel,rep_content) VALUES (reportcollect_seq.NEXTVAL, ?, ?, ?, ?, ?,?)";
	/**8.29新增最新單車日誌**/
	private static final String GET_ALL_NEWBlogScore = 
			"SELECT * FROM (select * from blog order by blog_cre desc)  where  rownum < 4";
	/**8.29新增最新單車日誌**/
	/**8.31新增會員管理頁面最新討論區**/
	private static final String GET_ONE_MEM = 
			"SELECT forum_no,mem_no,forum_title,forum_content,to_char(forum_cretime,'yyyy-mm-dd') forum_cretime,forum_del FROM forum where mem_no = ? and forum_del = 0 order by forum_cretime";
	/**8.31新增會員管理頁面最新討論區**/
	
	//---------合---------
	private static final String GET_ALL_FORUM_RC_STATUS = 
			"Insert into Forum SELECT rc_no,mem_no,forum_no FROM ReportCollect where forum_no = ? and status = ? and rep_rel = 1";
	
	private static final String GET_ALL_FORUM_RC_NO_STATUS = 
			"SELECT rc_no,mem_no,forum_no FROM ReportCollect where forum_no = ? and status = 1 and rep_rel = 1";
	
	private static final String GET_ALL_FORUM_RC_HANDLE = 
			"SELECT forum_no,mem_no,forum_title,forum_content,to_char(forum_cretime,'yyyy-mm-dd') forum_cretime,forum_del FROM ReportCollect where forum_no = ? and handle = 0 and rep_rel = 0";
	//---------合---------
	/**9.3更新討論區首頁順序**/
	private static final String GET_ALL_STMT_Mem =
			"SELECT forum_no,mem_no,forum_title,forum_content,to_char(forum_cretime,'yyyy-mm-dd') forum_cretime,forum_del FROM forum where forum_del=0 order by forum_cretime desc, forum_no desc  ";
	/**9.3更新討論區首頁順序**/
	
	/**9/4新增**/
	private static final String FORUMTITLE="SELECT forum_no,mem_no,forum_title,forum_content,to_char(forum_cretime,'yyyy-mm-dd') forum_cretime,forum_del FROM FORUM where forum_title LIKE ? and forum_del=0 order by forum_cretime desc, forum_no desc";
	/**9/4新增**/
	
	@Override
	public void insert(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, forumVO.getMem_no());
			pstmt.setString(2, forumVO.getForum_title());
			pstmt.setString(3, forumVO.getForum_content());
			pstmt.setDate(4, forumVO.getForum_cretime());
			pstmt.setInt(5, forumVO.getForum_del());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(ForumVO forumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, forumVO.getForum_title());
			
			pstmt.setString(2, forumVO.getForum_content());
			pstmt.setDate(3, forumVO.getForum_cretime());
			pstmt.setInt(4, forumVO.getForum_no());
			
			
			
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
	public void delete(Integer forum_no) {
		int updateCount_Forumres = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先刪除員回應
			pstmt = con.prepareStatement(DELETE_Forumres);
			pstmt.setInt(1, forum_no);
			updateCount_Forumres = pstmt.executeUpdate();
			
			// 再刪除文章
			pstmt = con.prepareStatement(DELETE_Forum);
			pstmt.setInt(1, forum_no);
			pstmt.executeUpdate();
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除文章編號" + forum_no + "時,共有回應" + updateCount_Forumres
			+ "篇同時被刪除");
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
	public void delete_mem(Integer forum_no){
		
		Connection con = null;
		PreparedStatement pstmt= null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_Mem);
			
			pstmt.setInt(1, forum_no);
			
			pstmt.executeUpdate();
			
		} catch(SQLException se){
			throw new RuntimeException("A database error occured."
			+se.getMessage());
		}finally {
			if(pstmt !=null){
				try {
					pstmt.close();
				}catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con !=null){
				try{
				con.close();
				} catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public ForumVO findByPrimaryKey(Integer forum_no) {

		ForumVO forumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forum_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				forumVO = new ForumVO();
				forumVO.setForum_no(rs.getInt("forum_no"));
				forumVO.setMem_no(rs.getInt("mem_no"));
				forumVO.setForum_title(rs.getString("forum_title"));
				forumVO.setForum_content(rs.getString("forum_content"));
				forumVO.setForum_cretime(rs.getDate("forum_cretime"));
				forumVO.setForum_del(rs.getInt("forum_del"));
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
		return forumVO;
	}
	
	
	
	
	
	@Override
	public List<ForumVO> mem_getAll(){
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_Mem);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				forumVO = new ForumVO();
				forumVO.setForum_no(rs.getInt("forum_no"));
				forumVO.setMem_no(rs.getInt("mem_no"));
				forumVO.setForum_title(rs.getString("forum_title"));
				forumVO.setForum_content(rs.getString("forum_content"));
				forumVO.setForum_cretime(rs.getDate("forum_cretime"));
				forumVO.setForum_del(rs.getInt("forum_del"));
				list.add(forumVO);
			}
		} catch(SQLException se){
			throw new RuntimeException("A database error occured."
					+se.getMessage());
		} finally{
			if (rs !=null){
				try{
					rs.close();
				} catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt !=null){
				try{
					pstmt.close();
				} catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con !=null){
				try{
					con.close();
				} catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	@Override
	public List<ForumVO> getAll() {
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				forumVO = new ForumVO();
				forumVO.setForum_no(rs.getInt("forum_no"));
				forumVO.setMem_no(rs.getInt("mem_no"));
				forumVO.setForum_title(rs.getString("forum_title"));
				forumVO.setForum_content(rs.getString("forum_content"));
				forumVO.setForum_cretime(rs.getDate("forum_cretime"));
				forumVO.setForum_del(rs.getInt("forum_del"));
				list.add(forumVO); // Store the row in the list
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

	
	/**9/2**/
	@Override
	public List<ForumVO> getAll(Map<String, String[]> map){
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try	{
			con = ds.getConnection();
			String finalSQL = "select * from forum"
					+jdbcUtil_CompositeQuery_forum.get_WhereCondition(map)
					+ " order by forum_cretime desc";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				forumVO = new ForumVO();
				forumVO.setForum_no(rs.getInt("forum_no"));
				forumVO.setMem_no(rs.getInt("mem_no"));
				forumVO.setForum_title(rs.getString("forum_title"));
				forumVO.setForum_content(rs.getString("forum_content"));
				forumVO.setForum_cretime(rs.getDate("forum_cretime"));
				forumVO.setForum_del(rs.getInt("forum_del"));
				list.add(forumVO);
			}
		} catch(SQLException se){
			throw new RuntimeException("A dadabase error occured."
					+ se.getMessage());
		}finally {
			if(rs !=null){
				try{
					rs.close();
				} catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (con != null){
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	/**9/2**/
	
	@Override
	public void insert_handle(ReportcollectVO rcVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_HANDLE);

			
			pstmt.setInt(1, rcVO.getMem_no());
			pstmt.setInt(2, rcVO.getForum_no());
			pstmt.setInt(3, rcVO.getRc_rep_handle());
			pstmt.setInt(4, rcVO.getRc_col_status());
			pstmt.setInt(5, rcVO.getRep_rel());
			pstmt.setString(6, rcVO.getRep_content());
			pstmt.executeUpdate();

			// Handle any SQL errors
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
	
	/***最高評分日誌****/
	@Override
	public List<BlogScoreVO> getAll_bsnew(){
		List<BlogScoreVO> list = new ArrayList<BlogScoreVO>();
		BlogScoreVO bsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BlogScore_NEW);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				bsVO = new BlogScoreVO();
				bsVO.setBlog_no(rs.getInt("blog_no"));
				bsVO.setMem_no(rs.getInt("mem_no"));
				bsVO.setBlog_score(rs.getInt("blog_score"));
				bsVO.setBlog_score_status(rs.getInt("blog_score_status"));

				list.add(bsVO); // Store the row in the list
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
	
	/***最新文章****/
	@Override
	public List<ForumVO> getAll_new() {
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FORUM_NEW);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				forumVO = new ForumVO();
				forumVO.setForum_no(rs.getInt("forum_no"));
				forumVO.setMem_no(rs.getInt("mem_no"));
				forumVO.setForum_title(rs.getString("forum_title"));
				forumVO.setForum_content(rs.getString("forum_content"));
				forumVO.setForum_cretime(rs.getDate("forum_cretime"));
				forumVO.setForum_del(rs.getInt("forum_del"));
				list.add(forumVO); // Store the row in the list
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
	/***8/29最新評分日誌****/
	@Override
	public List<BlogVO> getAll_newblog(){
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_NEWBlogScore);
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
/**8/31會員查詢**/
	
	@Override
	public List<ForumVO> findByMem(Integer mem_no) {
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEM);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				forumVO = new ForumVO();
				forumVO.setForum_no(rs.getInt("forum_no"));
				forumVO.setMem_no(rs.getInt("mem_no"));
				forumVO.setForum_title(rs.getString("forum_title"));
				forumVO.setForum_content(rs.getString("forum_content"));
				forumVO.setForum_cretime(rs.getDate("forum_cretime"));
				forumVO.setForum_del(rs.getInt("forum_del"));
				list.add(forumVO); // Store the row in the list
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
	
	/**9/4新增**/
	@Override
	public List<ForumVO> getForum_title(String forum_title) {
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FORUMTITLE);
			
			pstmt.setString(1, "%"+forum_title+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				forumVO = new ForumVO();
					forumVO.setForum_no(rs.getInt("forum_no"));
					forumVO.setMem_no(rs.getInt("mem_no"));
					forumVO.setForum_title(rs.getString("forum_title"));
					forumVO.setForum_content(rs.getString("forum_content"));
					forumVO.setForum_cretime(rs.getDate("forum_cretime"));
					forumVO.setForum_del(rs.getInt("forum_del"));
				list.add(forumVO); 
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
	/**9/4新增**/
}