package com.item.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.itemdetails.model.ItemDetailsVO;
import com.itemimage.model.ItemImageDAO;
import com.itemimage.model.ItemImageVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Item;





public class ItemDAO implements ItemDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		//新增
		private static final String INSERT_STMT = 
			"INSERT INTO item (item_no,mem_no,item_name,item_price,item_exp,item_is_added) VALUES (ITEM_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		//查全部
		private static final String GET_ALL_STMT = 
			"SELECT * FROM item order by item_no desc";
		//查一筆
		private static final String GET_ONE_STMT = 
			"SELECT item_no,mem_no,item_name,item_price,item_exp,item_is_added FROM item where item_no = ?";
		//查商品圖片
		private static final String GET_ItemImages_ByItemno = 
				"SELECT item_img_no,item_no,item_img FROM itemimage where item_no = ? order by item_img_no";
		//求個人商品
		private static final String GET_ONE_MEM =
				"SELECT * FROM item where mem_no=? order by item_no desc";
		//刪除
		private static final String DELETE = 
			"DELETE FROM item where item_no = ?";
		//刪圖片
		private static final String DELETE_ITEMIMAGE = 
				"DELETE FROM itemimage where item_no = ? ";	
		//修改
		private static final String UPDATE = 
			"UPDATE item set mem_no=?, item_name=?, item_price=?, item_exp=?, item_is_added=? where item_no = ?";

	//INSERT_STMT新增
	@Override
	public void insert(ItemVO itemVO) {
		// TODO Auto-generated method stub
		Connection con = null;//連線
		PreparedStatement pstmt = null;//SQL語法
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			//"INSERT INTO item (item_no,mem_no,item_name,item_price,item_exp,item_is_added) VALUES (ITEM_seq.NEXTVAL, ?, ?, ?, ?, ?)";
			pstmt.setInt(1, itemVO.getMem_no());
			pstmt.setString(2, itemVO.getItem_name());
			pstmt.setInt (3, itemVO.getItem_price());
			pstmt.setString (4, itemVO.getItem_exp());
			pstmt.setInt (5, itemVO.getItem_is_added());
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

	//UPDATE修改
	@Override
	public void update(ItemVO itemVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			//"UPDATE item set mem_no=?, item_name=?, item_price=?, item_exp=?, item_is_added=? where item_no = ?";
			pstmt.setInt(1, itemVO.getMem_no());
			pstmt.setString(2, itemVO.getItem_name());
			pstmt.setInt(3, itemVO.getItem_price());
			pstmt.setString(4, itemVO.getItem_exp());
			pstmt.setInt (5, itemVO.getItem_is_added());
			pstmt.setInt(6, itemVO.getItem_no());
			
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

	//刪除
	@Override
	public void delete(Integer item_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			//刪除時先刪圖片
			pstmt = con.prepareStatement(DELETE_ITEMIMAGE);
			pstmt.setInt(1, item_no);
			pstmt.executeUpdate();
			//在刪商品
			pstmt = con.prepareStatement(DELETE);
			//"DELETE FROM item where item_no = ?";
			pstmt.setInt(1, item_no);
			pstmt.executeUpdate();
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

	//GET_ONE_STMT查一筆
	@Override
	public ItemVO findByPrimaryKey(Integer item_no) {
		
		ItemVO itemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//"SELECT item_no,mem_no,item_name,item_price,item_exp,item_added FROM item where item_no = ?";
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setItem_no(rs.getInt("item_no"));
				itemVO.setMem_no(rs.getInt("mem_no"));
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_price(rs.getInt("item_price"));
				itemVO.setItem_exp(rs.getString("item_exp"));
				itemVO.setItem_is_added(rs.getInt("item_is_added"));
			}
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
		return itemVO;
	}

	//查全部
	@Override
	public List<ItemVO> getAll() {

		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setItem_no(rs.getInt("item_no"));
				itemVO.setMem_no(rs.getInt("mem_no"));
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_price(rs.getInt("item_price"));
				itemVO.setItem_exp(rs.getString("item_exp"));
				itemVO.setItem_is_added(rs.getInt("item_is_added"));
				list.add(itemVO); // Store the row in the list
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

	//查一筆商品全部的圖片
	@Override
	public Set<ItemImageVO> getItemImageByItemno(Integer item_no){
		Set<ItemImageVO> set = new LinkedHashSet<ItemImageVO>();
		ItemImageVO itemimageVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ItemImages_ByItemno);
			//"SELECT item_img_no,item_no,item_img FROM itemimage where item_no = ? order by item_img_no";
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				itemimageVO = new ItemImageVO();
				itemimageVO.setItem_img_no(rs.getInt("item_img_no"));
				itemimageVO.setItem_no(rs.getInt("item_no"));
				itemimageVO.setItem_img(rs.getBytes("item_img"));
				
				set.add(itemimageVO); // Store the row in the vector
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
	
	//複合式查詢
	@Override
	public List<ItemVO> getAll(Map<String, String[]> map) {
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from item "
		          + jdbcUtil_CompositeQuery_Item.get_WhereCondition(map)
		          + "order by item_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setItem_no(rs.getInt("item_no"));
				itemVO.setMem_no(rs.getInt("mem_no"));
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_price(rs.getInt("item_price"));
				itemVO.setItem_exp(rs.getString("item_exp"));
				itemVO.setItem_is_added(rs.getInt("item_is_added"));
				list.add(itemVO); // Store the row in the List
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
		return list;
	}

	@Override
	public void insertWithItems(ItemVO itemVO, List<ItemImageVO> list) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增商品
			String cols[] = {"item_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
			//"INSERT INTO item (item_no,mem_no,item_name,item_price,item_exp,item_is_added) VALUES (ITEM_seq.NEXTVAL, ?, ?, ?, ?, ?)";
			pstmt.setInt(1, itemVO.getMem_no());
			pstmt.setString(2, itemVO.getItem_name());
			pstmt.setInt(3, itemVO.getItem_price());
			pstmt.setString(4, itemVO.getItem_exp());
			pstmt.setInt(5, itemVO.getItem_is_added());
		
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			Integer itemno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				itemno = rs.getInt(1);
				System.out.println("自增主鍵值= " + itemno +"(剛新增成功的商品編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增圖片
			ItemImageDAO itemImageDAO = new ItemImageDAO();
			System.out.println("list.size()-A="+list.size());
			for (ItemImageVO itemImageVO : list) {
				
				itemImageVO.setItem_no(itemno);
				itemImageDAO.insert2(itemImageVO,con);
				
			}
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	//求個人商品
	@Override
	public List<ItemVO> findByMemKey(Integer mem_no) {
		// TODO Auto-generated method stub
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	try{
		con = ds. getConnection();
		pstmt = con.prepareStatement(GET_ONE_MEM);
//		"SELECT item_no,mem_no,item_name,item_price,item_exp,item_is_added where mem_no=?"
		pstmt.setInt(1, mem_no);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			itemVO = new ItemVO();
			itemVO.setItem_no(rs.getInt("item_no"));
			itemVO.setMem_no(rs.getInt("mem_no"));
			itemVO.setItem_name(rs.getString("item_name"));
			itemVO.setItem_price(rs.getInt("item_price"));
			itemVO.setItem_exp(rs.getString("item_exp"));
			itemVO.setItem_is_added(rs.getInt("item_is_added"));
			list.add(itemVO);
		}
	}catch(SQLException se){
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
