package com.item.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itemimage.model.ItemImageVO;




public class ItemService {
	
	private ItemDAO_interface dao;
	//DAO
	public ItemService() {
		dao = new ItemDAO();
	}
	//新增
	public ItemVO addItem(Integer mem_no, String item_name,
			Integer item_price, String item_exp, Integer item_is_added) {

		ItemVO itemVO = new ItemVO();
		
		itemVO.setMem_no(mem_no);
		itemVO.setItem_name(item_name);
		itemVO.setItem_price(item_price);
		itemVO.setItem_exp(item_exp);
		itemVO.setItem_is_added(item_is_added);
		dao.insert(itemVO);

		return itemVO;
	}
	//修改
	public ItemVO updateItem(Integer item_no,Integer mem_no, String item_name,
			Integer item_price, String item_exp, Integer item_is_added) {

		ItemVO itemVO = new ItemVO();

		itemVO.setItem_no(item_no);
		itemVO.setMem_no(mem_no);
		itemVO.setItem_name(item_name);
		itemVO.setItem_price(item_price);
		itemVO.setItem_exp(item_exp);
		itemVO.setItem_is_added(item_is_added);
		dao.update(itemVO);
		
		return itemVO;
	}
	//刪除
	public void deleteItem(Integer item_no) {
		dao.delete(item_no);
	}
	//查一筆
	public ItemVO getOneItem(Integer item_no) {
		return dao.findByPrimaryKey(item_no);
	}
	//查個人商品
	public List<ItemVO> getOneItemMem(Integer mem_no){
		return dao.findByMemKey(mem_no);
	}
	//查全部
	public List<ItemVO> getAll() {
		return dao.getAll();
	}
	//查圖片
	public Set<ItemImageVO> getItemImageByItemno(Integer item_no) {
		// TODO Auto-generated method stub
		return dao.getItemImageByItemno(item_no);
	}
	//複合式查詢
	public List<ItemVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	//新增商品時綁定自增主鍵同時新增多筆圖片
	public void insertWithItems(ItemVO itemVO, List<ItemImageVO> list)
	{
		 dao.insertWithItems(itemVO, list);
	}
	
}
