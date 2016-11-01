package com.itemimage.model;

import java.util.List;

import com.item.model.ItemDAO;
import com.item.model.ItemDAO_interface;

public class ItemImageService {
	
	private ItemImageDAO_interface dao;

	public ItemImageService() {
		dao = new ItemImageDAO();
	}
	
	public ItemImageVO addItemImage(Integer item_no, byte[] item_img) {

		ItemImageVO itemImageVO = new ItemImageVO();
		
		itemImageVO.setItem_no(item_no);
		itemImageVO.setItem_img(item_img);
		dao.insert(itemImageVO);

		return itemImageVO;
	}

	public ItemImageVO updateItemImage(Integer item_img_no,Integer item_no,byte[] item_img) {

		ItemImageVO itemImageVO = new ItemImageVO();

		itemImageVO.setItem_img_no(item_img_no);
		itemImageVO.setItem_no(item_no);
		itemImageVO.setItem_img(item_img);
		
		dao.update(itemImageVO);
		
		return dao.findByPrimaryKey(item_img_no);
	}

	public void deleteItemImage(Integer item_img_no) {
		dao.delete(item_img_no);
	}

	public ItemImageVO getOneItemImage(Integer item_img_no) {
		return dao.findByPrimaryKey(item_img_no);
	}

	public List<ItemImageVO> getAll() {
		return dao.getAll();
	}
	public List<ItemImageVO> getOne(Integer item_no) {
		return dao.getOne(item_no);
	}
}
