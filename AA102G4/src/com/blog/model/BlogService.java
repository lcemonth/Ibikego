package com.blog.model;

import java.util.List;

public class BlogService{
	private BlogDAO_interface dao;
	public BlogService(){
		dao = new BlogDAO();
	}
	public BlogVO addBlog(Integer mem_no,String blog_title,String blog_content,java.sql.Date blog_cre,byte[] blog_photo,Integer blog_del){
		BlogVO blogVO = new BlogVO();
		blogVO.setMem_no(mem_no);
		blogVO.setBlog_title(blog_title);
		blogVO.setBlog_content(blog_content);
		blogVO.setBlog_cre(blog_cre);
		blogVO.setBlog_photo(blog_photo);
		blogVO.setBlog_del(blog_del);
		dao.insert(blogVO);
		return blogVO;
	}
	public BlogVO updateBlog(Integer blog_no,Integer mem_no,String blog_title,String blog_content,java.sql.Date blog_cre,byte[] blog_photo,Integer blog_del){
		BlogVO blogVO = new BlogVO();
		blogVO.setBlog_no(blog_no);
		blogVO.setMem_no(mem_no);
		blogVO.setBlog_title(blog_title);
		blogVO.setBlog_content(blog_content);
		blogVO.setBlog_cre(blog_cre);
		blogVO.setBlog_photo(blog_photo);
		blogVO.setBlog_del(blog_del);
		dao.update(blogVO);
		return blogVO;
	}
	public void deleteBlog(Integer blog_no){
		dao.delete(blog_no);
	}
	public BlogVO getOneBlog(Integer blog_no){
		return dao.findByPrimaryKey(blog_no);
	}
	public List<BlogVO> getAll() {
		return dao.getAll();
	}
	public void updateDel(Integer blog_no){
		dao.updateDel(blog_no);
	}
	/******************************0829********************************/
	public List<BlogVO> getMyBlogs(Integer mem_no) {
		return dao.getMyBlogs(mem_no);
	}
}