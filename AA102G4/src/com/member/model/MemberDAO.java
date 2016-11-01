package com.member.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Member;

public class MemberDAO implements MemberDAO_interface{
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
        "INSERT INTO MEMBER (mem_no,mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del) VALUES (member_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_ALL_STMT = 
    	"SELECT mem_no,mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del FROM MEMBER order by mem_no";
    private static final String GET_ONE_STMT = 
    	"SELECT mem_no,mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del FROM MEMBER where mem_no = ?";
    private static final String GET_ONE_STMT_ACC = 
    	"SELECT mem_no,mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del FROM MEMBER where mem_acc = ?";
    private static final String GET_BY_NAME = 
    	"SELECT * FROM MEMBER where mem_name like ?";
    private static final String DELETE = 
    	"DELETE FROM MEMBER where mem_no = ?";
    private static final String UPDATE = 
    	"UPDATE MEMBER set mem_pw=?,mem_name=?,mem_nickname=?,mem_add=?,mem_phone=?,mem_email=?,mem_photo=?,mem_del=? where mem_no = ?";
    private static final String UPDATE_PW = 
    	"UPDATE MEMBER set mem_pw=?,mem_name=? where mem_no = ?";
    private static final String GET_ACC_MAIL = "SELECT * FROM member where mem_acc= ? and mem_email= ?";
    //--合
    private static final String GET_ONE_MEM_BY_ACC = 
    	"SELECT mem_no,mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del FROM MEMBER where mem_acc = ?";
    //--合
    
    @Override
	public void insert(MemberVO memberVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,memberVO.getMem_acc());
			pstmt.setString(2,memberVO.getMem_pw());
			pstmt.setString(3,memberVO.getMem_name());
			pstmt.setString(4,memberVO.getMem_nickname());
			pstmt.setString(5,memberVO.getMem_add());
			pstmt.setString(6,memberVO.getMem_phone());
			pstmt.setString(7,memberVO.getMem_email());
			pstmt.setBytes(8,memberVO.getMem_photo());
			pstmt.setInt(9,memberVO.getMem_reg());
			pstmt.setInt(10,memberVO.getMem_del());
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
	public void update(MemberVO memberVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,memberVO.getMem_pw());
			pstmt.setString(2,memberVO.getMem_name());
			pstmt.setString(3,memberVO.getMem_nickname());
			pstmt.setString(4,memberVO.getMem_add());
			pstmt.setString(5,memberVO.getMem_phone());
			pstmt.setString(6,memberVO.getMem_email());
			pstmt.setBytes(7,memberVO.getMem_photo());
			pstmt.setInt(8,memberVO.getMem_del());
			pstmt.setInt(9,memberVO.getMem_no());
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
	public void update_pw(MemberVO memberVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PW);
			pstmt.setString(1,memberVO.getMem_pw());
			pstmt.setString(2,memberVO.getMem_name());
			pstmt.setInt(3,memberVO.getMem_no());
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
	public void delete(Integer mem_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, mem_no);
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
	public MemberVO findByPrimaryKey(Integer mem_no){
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,mem_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_acc(rs.getString("mem_acc"));
				memberVO.setMem_pw(rs.getString("mem_pw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_add(rs.getString("mem_add"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_reg(rs.getInt("mem_reg"));
				memberVO.setMem_del(rs.getInt("mem_del"));
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
		return memberVO;
	}//findByPrimaryKey
	
	@Override
	public MemberVO findByAcc(String mem_acc){
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ACC);
			pstmt.setString(1,mem_acc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_acc(rs.getString("mem_acc"));
				memberVO.setMem_pw(rs.getString("mem_pw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_add(rs.getString("mem_add"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_reg(rs.getInt("mem_reg"));
				memberVO.setMem_del(rs.getInt("mem_del"));
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
		return memberVO;
	}//findByAcc
	
	@Override
	public List<MemberVO> getAll(Map<String, String[]> map) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String finalSQL = "select * from member "
		          + jdbcUtil_CompositeQuery_Member.get_WhereCondition(map)
		          + "order by mem_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_acc(rs.getString("mem_acc"));
				memberVO.setMem_pw(rs.getString("mem_pw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_add(rs.getString("mem_add"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_reg(rs.getInt("mem_reg"));
				memberVO.setMem_del(rs.getInt("mem_del"));
				list.add(memberVO); // Store the row in the List
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public List<MemberVO> getAll(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_acc(rs.getString("mem_acc"));
				memberVO.setMem_pw(rs.getString("mem_pw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_add(rs.getString("mem_add"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_reg(rs.getInt("mem_reg"));
				memberVO.setMem_del(rs.getInt("mem_del"));
				list.add(memberVO); // Store the row in the list
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
	
	@Override
	public MemberVO findByACC_MAIL(String mem_acc, String mem_email) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACC_MAIL);
			pstmt.setString(1, mem_acc);
			pstmt.setString(2, mem_email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_acc(rs.getString("mem_acc"));
				memberVO.setMem_pw(rs.getString("mem_pw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_add(rs.getString("mem_add"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_reg(rs.getInt("mem_reg"));
				memberVO.setMem_del(rs.getInt("mem_del"));
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
		return memberVO;
	}
	
	//--合
		public MemberVO findOneMemByAcc(String mem_acc){
			MemberVO memberVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_MEM_BY_ACC);
				pstmt.setString(1,mem_acc);
				rs = pstmt.executeQuery();
				while(rs.next()){
					// memberVO 也稱為 Domain objects
					memberVO = new MemberVO();
					memberVO.setMem_no(rs.getInt("mem_no"));
					memberVO.setMem_acc(rs.getString("mem_acc"));
					memberVO.setMem_pw(rs.getString("mem_pw"));
					memberVO.setMem_name(rs.getString("mem_name"));
					memberVO.setMem_nickname(rs.getString("mem_nickname"));
					memberVO.setMem_add(rs.getString("mem_add"));
					memberVO.setMem_phone(rs.getString("mem_phone"));
					memberVO.setMem_email(rs.getString("mem_email"));
					memberVO.setMem_photo(rs.getBytes("mem_photo"));
					memberVO.setMem_reg(rs.getInt("mem_reg"));
					memberVO.setMem_del(rs.getInt("mem_del"));
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
			return memberVO;
		}
		//--合
}