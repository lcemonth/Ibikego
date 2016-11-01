package com.blog.model;

import java.util.List;

public interface BlogDAO_interface {
	public void insert(BlogVO memberVO);
    public void update(BlogVO memberVO);
    public void delete(Integer mem_no);
    public BlogVO findByPrimaryKey(Integer mem_no);
    public List<BlogVO> getAll();
    public void updateDel(Integer blog_no);
    /**************************0829***************************/
    public List<BlogVO> getMyBlogs(Integer mem_no);
    /**************************0829***************************/
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<BlogVO> getAll(Map<String, String[]> map); 
}