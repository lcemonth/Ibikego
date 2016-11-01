package com.message.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MessageDAO implements MessageDAO_interface{
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
	        "INSERT INTO message (mes_no,blog_no,mem_no,mes_content,mes_cre) VALUES (message_seq.NEXTVAL,?,?,?,?)";
	private static final String GET_ALL_STMT = 
	    	"SELECT mes_no,blog_no,mem_no,mes_content,mes_cre FROM message order by mes_no";
	private static final String GET_ONE_STMT = 
	    	"SELECT mes_no,blog_no,mem_no,mes_content,mes_cre FROM message where mes_no = ?";
	private static final String DELETE = 
	    	"DELETE FROM message where mes_no = ?";
	private static final String UPDATE = 
	    	"UPDATE message set mes_content=?,mes_cre=? where mes_no = ?";
	private static final String getAllByBlog = 
	    	"SELECT mes_no,blog_no,mem_no,mes_content,mes_cre FROM message order by mes_no";
    
    @Override
	public void insert(MessageVO messageVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,messageVO.getBlog_no());
			pstmt.setInt(2,messageVO.getMem_no());
			pstmt.setString(3,messageVO.getMes_content());
			pstmt.setDate(4,messageVO.getMes_cre());
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
	public void update(MessageVO messageVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,messageVO.getMes_content());
			pstmt.setDate(2,messageVO.getMes_cre());
			pstmt.setInt(3,messageVO.getMes_no());
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
	}//update

	@Override
	public void delete(Integer mes_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, mes_no);
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
	public MessageVO findByPrimaryKey(Integer mes_no){
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, mes_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// messageVO 也稱為 Domain objects
				messageVO = new MessageVO();
				messageVO.setMes_no(rs.getInt("mes_no"));
				messageVO.setBlog_no(rs.getInt("blog_no"));
				messageVO.setMem_no(rs.getInt("mem_no"));
				messageVO.setMes_content(rs.getString("mes_content"));
				messageVO.setMes_cre(rs.getDate("mes_cre"));
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
		return messageVO;
	}//findByPrimaryKey

	@Override
	public List<MessageVO> getAll(){
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// messageVO 也稱為 Domain objects
				messageVO = new MessageVO();
				messageVO.setMes_no(rs.getInt("mes_no"));
				messageVO.setBlog_no(rs.getInt("blog_no"));
				messageVO.setMem_no(rs.getInt("mem_no"));
				messageVO.setMes_content(rs.getString("mes_content"));
				messageVO.setMes_cre(rs.getDate("mes_cre"));
				list.add(messageVO); // Store the row in the list
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
	
	/************************************************************************/
	@Override
	public List<MessageVO> getAllByBlog(Integer blog_no){
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, blog_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// messageVO 也稱為 Domain objects
				messageVO = new MessageVO();
				messageVO.setMes_no(rs.getInt("mes_no"));
				messageVO.setBlog_no(rs.getInt("blog_no"));
				messageVO.setMem_no(rs.getInt("mem_no"));
				messageVO.setMes_content(rs.getString("mes_content"));
				messageVO.setMes_cre(rs.getDate("mes_cre"));
				list.add(messageVO); // Store the row in the list
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
}