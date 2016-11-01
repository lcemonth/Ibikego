package com.relationship.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RelationshipDAO implements RelationshipDAO_interface{
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
	        "INSERT INTO RELATIONSHIP (mem_no_main,rel_mem_no,rel_status,rel_follow) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = 
	    	"SELECT mem_no_main,rel_mem_no,rel_status,rel_follow FROM RELATIONSHIP order by mem_no_main";
	private static final String GET_ONE_STMT = 
	    	"SELECT mem_no_main,rel_mem_no,rel_status,rel_follow FROM RELATIONSHIP where rel_mem_no = ?";
	private static final String DELETE = 
	    	"DELETE FROM RELATIONSHIP where rel_mem_no = ?";
	private static final String UPDATE = 
	    	"UPDATE RELATIONSHIP set rel_status=?,rel_follow=? where mem_no_main = ? and rel_mem_no = ?";
	//--合
	private static final String GET_MMEM_FOLLOW_RELMEM="SELECT mem_no_main,rel_mem_no,rel_status,rel_follow FROM RELATIONSHIP "+
		    " where mem_no_main = ? and rel_status=0 and rel_follow=0";
	//--合
	
	//--瑄
	private static final String checkRelationship = 
	    	"SELECT * FROM RELATIONSHIP where mem_no_main = ? and rel_mem_no = ?";
	private static final String checkFollow = 
	    	"SELECT rel_follow FROM RELATIONSHIP where mem_no_main = ? and rel_mem_no = ? and rel_follow = 0";
	private static final String checkBlacklist = 
	    	"SELECT rel_status FROM RELATIONSHIP where mem_no_main = ? and rel_mem_no = ? and rel_status = 1";
	//--瑄
    
    @Override
	public void insert(RelationshipVO relationshipVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,relationshipVO.getMem_no_main());
			pstmt.setInt(2,relationshipVO.getRel_mem_no());
			pstmt.setInt(3,relationshipVO.getRel_status());
			pstmt.setInt(4,relationshipVO.getRel_follow());
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
	public void update(RelationshipVO relationshipVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,relationshipVO.getRel_status());
			pstmt.setInt(2,relationshipVO.getRel_follow());
			pstmt.setInt(3,relationshipVO.getMem_no_main());
			pstmt.setInt(4,relationshipVO.getRel_mem_no());
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
	public void delete(Integer rel_mem_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, rel_mem_no);
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
	public RelationshipVO findByPrimaryKey(Integer rel_mem_no){
		RelationshipVO relationshipVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,rel_mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// relationshipVO 也稱為 Domain objects
				relationshipVO = new RelationshipVO();
				relationshipVO.setMem_no_main(rs.getInt("mem_no_main"));
				relationshipVO.setRel_mem_no(rs.getInt("rel_mem_no"));
				relationshipVO.setRel_status(rs.getInt("rel_status"));
				relationshipVO.setRel_follow(rs.getInt("rel_follow"));
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
		return relationshipVO;
	}//findByPrimaryKey

	@Override
	public List<RelationshipVO> getAll(){
		List<RelationshipVO> list = new ArrayList<RelationshipVO>();
		RelationshipVO relationshipVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// relationshipVO 也稱為 Domain objects
				relationshipVO = new RelationshipVO();
				relationshipVO.setMem_no_main(rs.getInt("mem_no_main"));
				relationshipVO.setRel_mem_no(rs.getInt("rel_mem_no"));
				relationshipVO.setRel_status(rs.getInt("rel_status"));
				relationshipVO.setRel_follow(rs.getInt("rel_follow"));
				list.add(relationshipVO); // Store the row in the list
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
	
	//--合
	@Override
	public List<RelationshipVO> getMmemFollowRelmem(Integer mem_no_main) {
		List<RelationshipVO> list = new ArrayList<RelationshipVO>();
		RelationshipVO relationshipVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MMEM_FOLLOW_RELMEM);
			pstmt.setInt(1,mem_no_main);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// relationshipVO 也稱為 Domain objects
				relationshipVO = new RelationshipVO();
				relationshipVO.setMem_no_main(rs.getInt("mem_no_main"));
				relationshipVO.setRel_mem_no(rs.getInt("rel_mem_no"));
				relationshipVO.setRel_status(rs.getInt("rel_status"));
				relationshipVO.setRel_follow(rs.getInt("rel_follow"));
				list.add(relationshipVO); // Store the row in the list
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
	}//getMmemFollowRelmem
	//--合

	//--瑄
	@Override
	public RelationshipVO checkRelationship(Integer mem_no_main,Integer rel_mem_no){
		RelationshipVO relationshipVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(checkRelationship);
			pstmt.setInt(1,mem_no_main);
			pstmt.setInt(2,rel_mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// relationshipVO 也稱為 Domain objects
				relationshipVO = new RelationshipVO();
				relationshipVO.setMem_no_main(rs.getInt("mem_no_main"));
				relationshipVO.setRel_mem_no(rs.getInt("rel_mem_no"));
				relationshipVO.setRel_status(rs.getInt("rel_status"));
				relationshipVO.setRel_follow(rs.getInt("rel_follow"));
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
		return relationshipVO;
	}//checkRelationship
	
	@Override
	public Integer checkFollow(Integer mem_no_main,Integer rel_mem_no){
		Integer rel_follow = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(checkFollow);
			pstmt.setInt(1,mem_no_main);
			pstmt.setInt(2,rel_mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				rel_follow = (Integer) rs.getInt("rel_follow");
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
		return rel_follow;
	}//checkRelationship
	
	@Override
	public Integer checkBlacklist(Integer mem_no_main,Integer rel_mem_no){
		Integer Blacklist = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(checkBlacklist);
			pstmt.setInt(1,mem_no_main);
			pstmt.setInt(2,rel_mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Blacklist = (Integer) rs.getInt("rel_status");
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
		return Blacklist;
	}//checkRelationship
	//--瑄
}