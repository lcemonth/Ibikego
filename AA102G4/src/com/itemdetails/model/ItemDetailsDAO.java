package com.itemdetails.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.item.model.ItemVO;

public class ItemDetailsDAO implements ItemDetailsDAO_interface{
	
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
			"INSERT INTO itemdetails (item_no,mem_no,item_detail_status,item_is_get,item_is_sellerscore,item_is_buyerscore,"
									+ "item_detail_del,item_seller_score,item_buyer_score,item_order_time,item_buyer_name,"
									+ "item_buyer_add,item_buyer_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String UPDATE_ITEM_ADDED = 
//			"UPDATE item set item_added = ? where item_no = ? ";
		//查全部
		private static final String GET_ALL_STMT = 
				"SELECT * FROM itemdetails order by item_order_time desc";
		//查一筆
		private static final String GET_ONE_STMT = 
				"SELECT * FROM itemdetails where item_no = ?";
		//刪除
		private static final String DELETE = 
				"DELETE FROM itemdetails where item_no = ?";
		//修改
		private static final String UPDATE = 
				"UPDATE itemdetails set item_detail_status = ?, item_is_get = ?, item_is_sellerscore = ?, item_is_buyerscore = ?,"
					 + "item_seller_score = ?, item_buyer_score = ?, item_buyer_name = ?,item_buyer_add = ?, item_buyer_phone = ? "
					 + "where item_no = ? and mem_no = ? ";
		//取消訂單
		private static final String DETAILS_DEL = 
				"UPDATE itemdetails set item_detail_del = ? where item_no = ? and mem_no = ? ";	
		//查個人訂單
		private static final String GET_ONE_BUYER =
				"SELECT * FROM itemdetails where mem_no = ? order by item_order_time desc";
		//買家分數
		private static final String BUYER_SCORE =
				"SELECT mem_no ,avg(item_buyer_score) item_buyer_score FROM itemdetails group by mem_no having mem_no=?";
		private static final String SELLER_SCORE =
				"SELECT avg(i.item_seller_score) item_seller_score,e.mem_no mem_no from itemdetails "
					+ "i join item e on i.item_no=e.item_no group by e.mem_no having e.mem_no = ?";
	   
	//新增
	@Override
	public void insert(ItemDetailsVO itemDetailsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
//			"INSERT INTO itemdetails (item_no,mem_no,item_is_status,item_is_get,item_is_sellerscore,item_is_buyerscore,"
//					  + "item_detail_del,item_seller_score,item_buyer_score,item_order_time,item_buyer_name,"
//					  + "item_buyer_add,item_buyer_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt.setInt(1, itemDetailsVO.getItem_no());
			pstmt.setInt(2, itemDetailsVO.getMem_no());
			pstmt.setInt (3, itemDetailsVO.getItem_detail_status());
			pstmt.setInt (4, itemDetailsVO.getItem_is_get());
			pstmt.setInt (5, itemDetailsVO.getItem_is_sellerscore());
			pstmt.setInt (6, itemDetailsVO.getItem_is_buyerscore());
			pstmt.setInt (7, itemDetailsVO.getItem_detail_del());
			pstmt.setInt (8, itemDetailsVO.getItem_seller_score());
			pstmt.setInt (9, itemDetailsVO.getItem_buyer_score());
			pstmt.setDate (10, itemDetailsVO.getItem_order_time());
			pstmt.setString (11, itemDetailsVO.getItem_buyer_name());
			pstmt.setString (12, itemDetailsVO.getItem_buyer_add());
			pstmt.setString (13, itemDetailsVO.getItem_buyer_phone());
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
	//修改
	@Override
	public void update(ItemDetailsVO itemDetailsVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
//		"UPDATE itemdetails set getItem_detail_status=?, item_is_get=?, item_is_sellerscore=?, item_is_buyerscore=?,"
//		  + "item_seller_score=?, item_buyer_score=?, item_buyer_name=?,"
//		  + "item_buyer_add=?, item_buyer_phone=? where item_no = ? and mem_no= ? ";

		pstmt.setInt (1, itemDetailsVO.getItem_detail_status());
		pstmt.setInt (2, itemDetailsVO.getItem_is_get());
		pstmt.setInt (3, itemDetailsVO.getItem_is_sellerscore());
		pstmt.setInt (4, itemDetailsVO.getItem_is_buyerscore());
		pstmt.setInt (5, itemDetailsVO.getItem_seller_score());
		pstmt.setInt (6, itemDetailsVO.getItem_buyer_score());
		pstmt.setString (7, itemDetailsVO.getItem_buyer_name());
		pstmt.setString(8, itemDetailsVO.getItem_buyer_add());
		pstmt.setString (9, itemDetailsVO.getItem_buyer_phone());
		pstmt.setInt(10, itemDetailsVO.getItem_no());
		pstmt.setInt(11, itemDetailsVO.getMem_no());
		
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
			pstmt = con.prepareStatement(DELETE);
			//"DELETE FROM itemdetails where item_no = ?";
			pstmt.setInt(1, item_no);
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
	//查詢一筆
	@Override
	public ItemDetailsVO findByPrimaryKey(Integer item_no) {
			// TODO Auto-generated method stub
		ItemDetailsVO itemDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
//			"SELECT item_no,mem_no,item_is_status,item_is_get,item_is_sellerscore,item_is_buyerscore,"
//			 + "item_detail_del,item_seller_score,item_buyer_score,item_order_time,item_buyer_name,"
//			 + "item_buyer_add,item_buyer_phone FROM itemdetails where item_no = ?";
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemDetailsVO = new ItemDetailsVO();
				itemDetailsVO.setItem_no(rs.getInt("item_no"));
				itemDetailsVO.setMem_no(rs.getInt("mem_no"));
				itemDetailsVO.setItem_detail_status(rs.getInt("item_detail_status"));
				itemDetailsVO.setItem_is_get(rs.getInt("item_is_get"));
				itemDetailsVO.setItem_is_sellerscore(rs.getInt("item_is_sellerscore"));
				itemDetailsVO.setItem_is_buyerscore(rs.getInt("item_is_buyerscore"));
				itemDetailsVO.setItem_detail_del(rs.getInt("item_detail_del"));
				itemDetailsVO.setItem_seller_score(rs.getInt("item_seller_score"));
				itemDetailsVO.setItem_buyer_score(rs.getInt("item_buyer_score"));
				itemDetailsVO.setItem_order_time(rs.getDate("item_order_time"));
				itemDetailsVO.setItem_buyer_name(rs.getString("item_buyer_name"));
				itemDetailsVO.setItem_buyer_add(rs.getString("item_buyer_add"));
				itemDetailsVO.setItem_buyer_phone(rs.getString("item_buyer_phone"));
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
		return itemDetailsVO;
		}
	//查全部
	@Override
	public List<ItemDetailsVO> getAll() {
		// TODO Auto-generated method stub
		List<ItemDetailsVO> list = new ArrayList<ItemDetailsVO>();
		ItemDetailsVO itemDetailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//
				itemDetailsVO = new ItemDetailsVO();
				itemDetailsVO.setItem_no(rs.getInt("item_no"));
				itemDetailsVO.setMem_no(rs.getInt("mem_no"));
				itemDetailsVO.setItem_detail_status(rs.getInt("item_detail_status"));
				itemDetailsVO.setItem_is_get(rs.getInt("item_is_get"));
				itemDetailsVO.setItem_is_sellerscore(rs.getInt("item_is_sellerscore"));
				itemDetailsVO.setItem_is_buyerscore(rs.getInt("item_is_buyerscore"));
				itemDetailsVO.setItem_detail_del(rs.getInt("item_detail_del"));
				itemDetailsVO.setItem_seller_score(rs.getInt("item_seller_score"));
				itemDetailsVO.setItem_buyer_score(rs.getInt("item_buyer_score"));
				itemDetailsVO.setItem_order_time(rs.getDate("item_order_time"));
				itemDetailsVO.setItem_buyer_name(rs.getString("item_buyer_name"));
				itemDetailsVO.setItem_buyer_add(rs.getString("item_buyer_add"));
				itemDetailsVO.setItem_buyer_phone(rs.getString("item_buyer_phone"));
				list.add(itemDetailsVO); // Store the row in the list
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
	//查個人訂單
	@Override
	public List<ItemDetailsVO> findByForeignKey(Integer mem_no) {
		// TODO Auto-generated method stub
		List<ItemDetailsVO> list= new ArrayList<ItemDetailsVO>();
		ItemDetailsVO itemDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_BUYER);
//		"SELECT item_no,mem_no,item_is_status,item_is_get,item_is_sellerscore,item_is_buyerscore,"
//		 + "item_detail_del,item_seller_score,item_buyer_score,item_order_time,item_buyer_name,"
//		 + "item_buyer_add,item_buyer_phone FROM itemdetails where mem_no = ?";
		pstmt.setInt(1, mem_no);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			itemDetailsVO = new ItemDetailsVO();
			itemDetailsVO.setItem_no(rs.getInt("item_no"));
			itemDetailsVO.setMem_no(rs.getInt("mem_no"));
			itemDetailsVO.setItem_detail_status(rs.getInt("item_detail_status"));
			itemDetailsVO.setItem_is_get(rs.getInt("item_is_get"));
			itemDetailsVO.setItem_is_sellerscore(rs.getInt("item_is_sellerscore"));
			itemDetailsVO.setItem_is_buyerscore(rs.getInt("item_is_buyerscore"));
			itemDetailsVO.setItem_detail_del(rs.getInt("item_detail_del"));
			itemDetailsVO.setItem_seller_score(rs.getInt("item_seller_score"));
			itemDetailsVO.setItem_buyer_score(rs.getInt("item_buyer_score"));
			itemDetailsVO.setItem_order_time(rs.getDate("item_order_time"));
			itemDetailsVO.setItem_buyer_name(rs.getString("item_buyer_name"));
			itemDetailsVO.setItem_buyer_add(rs.getString("item_buyer_add"));
			itemDetailsVO.setItem_buyer_phone(rs.getString("item_buyer_phone"));
			list.add(itemDetailsVO);
			
			
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
	//取消訂單
	@Override
	public void cancel(ItemDetailsVO itemDetailsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DETAILS_DEL);
//		"UPDATE itemdetails set item_detail_del=? where item_no = ? and mem_no= ? ";

		pstmt.setInt (1, itemDetailsVO.getItem_detail_del());
		pstmt.setInt(2, itemDetailsVO.getItem_no());
		pstmt.setInt(3, itemDetailsVO.getMem_no());
		
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
	//買家分數
	@Override
	public ItemDetailsVO BuyerScore(Integer mem_no) {
		// TODO Auto-generated method stub
		ItemDetailsVO itemDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(BUYER_SCORE);
//			"SELECT mem_no ,avg(item_buyer_score) item_buyer_score FROM itemdetails group by mem_no having mem_no=?";
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//
				itemDetailsVO = new ItemDetailsVO();
				itemDetailsVO.setMem_no(rs.getInt("mem_no"));
				itemDetailsVO.setItem_buyer_score(rs.getInt("item_buyer_score"));
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
		return  itemDetailsVO;
	}
	//賣家分數
	@Override
	public ItemDetailsVO SellerScore(Integer mem_no) {
			// TODO Auto-generated method stub
			ItemDetailsVO itemDetailsVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(SELLER_SCORE);
//				"SELECT mem_no ,avg(item_buyer_score) item_buyer_score FROM itemdetails group by mem_no having mem_no=?";
				pstmt.setInt(1, mem_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					itemDetailsVO = new ItemDetailsVO();
					itemDetailsVO.setMem_no(rs.getInt("mem_no"));
					itemDetailsVO.setItem_seller_score(rs.getInt("item_seller_score"));
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
			return  itemDetailsVO;
		}

	}

