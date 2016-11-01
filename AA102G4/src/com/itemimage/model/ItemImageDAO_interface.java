package com.itemimage.model;

import java.sql.Connection;
import java.util.List;

public interface ItemImageDAO_interface {

	public void insert(ItemImageVO itemImageVO);//新增
	public void update(ItemImageVO itemImageVO);//修改
	public void delete(Integer item_img_no);//刪除
	public ItemImageVO findByPrimaryKey(Integer item_img_no);
	public List<ItemImageVO> getAll();//
	public List<ItemImageVO> getOne(Integer item_no);
	public void insert2(ItemImageVO itemImageVO,Connection con);//多方
	
}
