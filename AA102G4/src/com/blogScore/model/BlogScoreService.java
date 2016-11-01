package com.blogScore.model;

import java.util.List;

import com.travelScore.model.TravelScoreVO;

public class BlogScoreService{
	private BlogScoreDAO_interface dao;
	public BlogScoreService(){
		dao = new BlogScoreDAO();
	}
	public BlogScoreVO addBlogScore(Integer blog_no,Integer mem_no,Integer blog_score,Integer blog_score_status){
		BlogScoreVO blogScoreVO = new BlogScoreVO();
		blogScoreVO.setBlog_no(blog_no);
		blogScoreVO.setMem_no(mem_no);
		blogScoreVO.setBlog_score(blog_score);
		blogScoreVO.setBlog_score_status(blog_score_status);
		dao.insert(blogScoreVO);
		return blogScoreVO;
	}
	public BlogScoreVO getOneBlog(Integer blog_no){
		return dao.findByPrimaryKey(blog_no);
	}
	public List<BlogScoreVO> getAll(){
		return dao.getAll();
	}
	/***/
	public BlogScoreVO getCheck(Integer blog_no,Integer mem_no){
		return dao.getCheck(blog_no,mem_no);
	}
	public BlogScoreVO getOneBlogScore(Integer blog_no){
		return dao.getOneBlogScore(blog_no);
	}
	/***/
}