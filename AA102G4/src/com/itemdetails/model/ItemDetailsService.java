package com.itemdetails.model;

import java.sql.Date;
import java.util.List;



public class ItemDetailsService {

	private ItemDetailsDAO_interface dao;

	public ItemDetailsService() {
		dao = new ItemDetailsDAO();
	}
	//新增訂單
	public ItemDetailsVO addItemDetails(Integer item_no,Integer mem_no,Integer item_detail_status,Integer item_is_get,
										Integer item_is_sellerscore,Integer item_is_buyerscore,Integer item_detail_del,
										Integer item_seller_score,Integer item_buyer_score,Date item_order_time,
										String item_buyer_name,String item_buyer_add,String item_buyer_phone) {

		ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
		
		itemDetailsVO.setItem_no(item_no);
		itemDetailsVO.setMem_no(mem_no);
		itemDetailsVO.setItem_detail_status(item_detail_status);
		itemDetailsVO.setItem_is_get(item_is_get);
		itemDetailsVO.setItem_is_sellerscore(item_is_sellerscore);
		itemDetailsVO.setItem_is_buyerscore(item_is_buyerscore);
		itemDetailsVO.setItem_detail_del(item_detail_del);
		itemDetailsVO.setItem_seller_score(item_seller_score);
		itemDetailsVO.setItem_buyer_score(item_buyer_score);
		itemDetailsVO.setItem_order_time(item_order_time);
		itemDetailsVO.setItem_buyer_name(item_buyer_name);
		itemDetailsVO.setItem_buyer_add(item_buyer_add);
		itemDetailsVO.setItem_buyer_phone(item_buyer_phone);
		
		dao.insert(itemDetailsVO);

		return itemDetailsVO;
	}
	//修改
	public ItemDetailsVO updateItemDetails(Integer item_no,Integer mem_no,Integer item_detail_status,Integer item_is_get,
										   Integer item_is_sellerscore,Integer item_is_buyerscore,Integer item_seller_score,
										   Integer item_buyer_score,String item_buyer_name, String item_buyer_add,String item_buyer_phone) {

		ItemDetailsVO itemDetailsVO = new ItemDetailsVO();

		itemDetailsVO.setItem_no(item_no);
		itemDetailsVO.setMem_no(mem_no);
		itemDetailsVO.setItem_detail_status(item_detail_status);
		itemDetailsVO.setItem_is_get(item_is_get);
		itemDetailsVO.setItem_is_sellerscore(item_is_sellerscore);
		itemDetailsVO.setItem_is_buyerscore(item_is_buyerscore);
		itemDetailsVO.setItem_seller_score(item_seller_score);
		itemDetailsVO.setItem_buyer_score(item_buyer_score);
		itemDetailsVO.setItem_buyer_name(item_buyer_name);
		itemDetailsVO.setItem_buyer_add(item_buyer_add);
		itemDetailsVO.setItem_buyer_phone(item_buyer_phone);
		
		dao.update(itemDetailsVO);
		
		return itemDetailsVO;
	}
	//刪除
	public void deleteItemDetails(Integer item_no,Integer mem_no) {
		dao.delete(item_no);
	}
	//查詢
	public ItemDetailsVO getOneItemDetails(Integer item_no) {
		return dao.findByPrimaryKey(item_no);
	}
	//查詢全部
	public List<ItemDetailsVO> getAll() {
		return dao.getAll();
	}
	//個人訂單
	public List<ItemDetailsVO> getOneItemDetailsBuyer(Integer mem_no) {
		return dao.findByForeignKey(mem_no);
	}
	//買家分數
	public ItemDetailsVO BuyerScore(Integer mem_no) {
		return dao.BuyerScore(mem_no);
	}
	//賣家分數
	public ItemDetailsVO SellerScore(Integer mem_no) {
		return dao.SellerScore(mem_no);
	}
	//取消訂單
	public ItemDetailsVO cancel(Integer item_detail_del,Integer item_no,Integer mem_no){
		ItemDetailsVO itemDetailsVO = new ItemDetailsVO();

		itemDetailsVO.setItem_detail_del(item_detail_del);
		itemDetailsVO.setItem_no(item_no);
		itemDetailsVO.setMem_no(mem_no);
		dao.cancel(itemDetailsVO);
		return itemDetailsVO;
		
	}
	
}
