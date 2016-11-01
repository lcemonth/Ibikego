package com.itemdetails.model;

import java.util.*;

public interface ItemDetailsDAO_interface {
	
	public void insert(ItemDetailsVO itemDetailsVO);//新增
	public void update(ItemDetailsVO itemDetailsVO);//修改
	public void delete(Integer item_no);//刪除
	public void cancel(ItemDetailsVO itemDetailsVO);//取消訂單
	public ItemDetailsVO findByPrimaryKey(Integer item_no);
	public List<ItemDetailsVO> findByForeignKey(Integer mem_no);//求個人訂單
	public ItemDetailsVO BuyerScore(Integer mem_no);//求買家平均
	public ItemDetailsVO SellerScore(Integer mem_no);//求賣家平均
	public List<ItemDetailsVO> getAll();//查全部訂單

}
