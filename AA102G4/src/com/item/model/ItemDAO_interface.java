package com.item.model;

import java.util.*;

import com.itemdetails.model.ItemDetailsVO;
import com.itemimage.model.ItemImageVO;

public interface ItemDAO_interface {
	
	public void insert(ItemVO itemVO);//新增
	public void update(ItemVO itemVO);//修改
	public void delete(Integer item_no);//刪除
	public ItemVO findByPrimaryKey(Integer item_no);//查一筆資料
	public List<ItemVO> findByMemKey(Integer mem_no);//求個人商品
	public List<ItemVO> getAll();//查全部商品
	public Set<ItemImageVO> getItemImageByItemno(Integer item_no);//顯示單張圖
	
	public List<ItemVO> getAll(Map<String, String[]> map); //萬用複合式查詢
	public void insertWithItems(ItemVO itemVO , List<ItemImageVO> list);//一對多綁定自增主鍵

}
