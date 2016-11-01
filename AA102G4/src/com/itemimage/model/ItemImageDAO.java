package com.itemimage.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.item.model.ItemVO;

public class ItemImageDAO implements ItemImageDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//INSERT_STMT新增
	private static final String INSERT_STMT = 
			"INSERT INTO itemimage (item_img_no,item_no,item_img) VALUES (ITEMIMAGE_seq.NEXTVAL, ?, ?)";
	//GET_ALL_STMT查全部圖片
	private static final String GET_ALL_STMT = 
			"SELECT * FROM itemimage order by item_img_no";
	//GET_ONE_STMT查一張圖片
	private static final String GET_ONE_STMT = 
			"SELECT item_img_no,item_no,item_img FROM itemimage where item_img_no = ?";
	//DELETE刪除
	private static final String DELETE = 
			"DELETE FROM itemimage where item_img_no = ?";
	//UPDATE修改
	private static final String UPDATE = 
			"UPDATE itemimage set item_no=?, item_img=? where item_img_no = ?";
	//查一個商品的圖片
	private static final String GET_ONE = 
				"SELECT * FROM itemimage where item_no=? order by item_img_no ";
		

	@Override
	public void insert(ItemImageVO itemImageVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			//"INSERT INTO itemimage (item_img_no,item_no,item_img) VALUES (ITEMIMAGE_seq.NEXTVAL, ?, ?)";
			
			pstmt.setInt(1, itemImageVO.getItem_no());
			pstmt.setBytes(2,itemImageVO.getItem_img());
//			ByteArrayInputStream image= new ByteArrayInputStream(itemImageVO.getItem_img());
//			pstmt.setBinaryStream(3,image);	
//			pstmt.setBinaryStream(3,new ByteArrayInputStream(itemImageVO.getItem_img()));
			
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
	public void update(ItemImageVO itemImageVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			//"UPDATE itemimage set item_no=?, item_img=? where item_img_no = ?";
			pstmt.setInt(1, itemImageVO.getItem_no());
			pstmt.setBytes(2,itemImageVO.getItem_img());
			pstmt.setInt(3, itemImageVO.getItem_img_no());
			
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
	public void delete(Integer item_img_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			//"DELETE FROM itemimage where item_img_no = ?";
			pstmt.setInt(1, item_img_no);

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
	public ItemImageVO findByPrimaryKey(Integer item_img_no) {

		ItemImageVO itemImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//"SELECT item_img_no,item_no,item_img FROM itemimage where item_img_no = ?";
			pstmt.setInt(1, item_img_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemImageVO = new ItemImageVO();
				itemImageVO.setItem_img_no(rs.getInt("item_img_no"));
				itemImageVO.setItem_no(rs.getInt("item_no"));
				itemImageVO.setItem_img(rs.getBytes("item_img"));
				
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
		return itemImageVO;
		
	}

	@Override
	public List<ItemImageVO> getAll() {

		List<ItemImageVO> list = new ArrayList<ItemImageVO>();
		ItemImageVO itemImageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// "SELECT * FROM itemimage order by item_img_no";
				itemImageVO = new ItemImageVO();
				itemImageVO.setItem_img_no(rs.getInt("item_img_no"));
				itemImageVO.setItem_no(rs.getInt("item_no"));
				itemImageVO.setItem_img(rs.getBytes("item_img"));
				
				list.add(itemImageVO); // Store the row in the list
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
	public List<ItemImageVO> getOne(Integer item_no) {

		List<ItemImageVO> list = new ArrayList<ItemImageVO>();
		ItemImageVO itemImageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			
			pstmt.setInt(1, item_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// "SELECT * FROM itemimage order by item_img_no";
				itemImageVO = new ItemImageVO();
				itemImageVO.setItem_img_no(rs.getInt("item_img_no"));
				itemImageVO.setItem_no(rs.getInt("item_no"));
				itemImageVO.setItem_img(rs.getBytes("item_img"));
				
				list.add(itemImageVO); // Store the row in the list
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
	public void insert2(ItemImageVO itemImageVO, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;

		try {

//			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			//"INSERT INTO itemimage (item_img_no,item_no,item_img) VALUES (ITEMIMAGE_seq.NEXTVAL, ?, ?)";
			pstmt.setInt(1, itemImageVO.getItem_no());
			pstmt.setBytes(2, itemImageVO.getItem_img());
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
		}
		
	}
	
	
	
}
