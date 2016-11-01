package com.blogScore.model;

import java.util.List;

public interface BlogScoreDAO_interface {
	public void insert(BlogScoreVO blogScoreVO);
    public BlogScoreVO findByPrimaryKey(Integer blog_no);
    public List<BlogScoreVO> getAll();
    /***/
    public BlogScoreVO getCheck(Integer blog_no,Integer mem_no);
    public BlogScoreVO getOneBlogScore(Integer blog_no);
    /***/
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<BlogScoreVO> getAll(Map<String, String[]> map); 
}