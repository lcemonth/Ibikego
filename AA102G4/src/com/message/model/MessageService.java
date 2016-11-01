package com.message.model;

import java.util.List;

public class MessageService{
	private MessageDAO_interface dao;
	public MessageService(){
		dao = new MessageDAO();
	}
	public MessageVO addMessage(Integer blog_no,Integer mem_no,String mes_content,java.sql.Date mes_cre){
		MessageVO messageVO = new MessageVO();
		messageVO.setBlog_no(blog_no);
		messageVO.setMem_no(mem_no);
		messageVO.setMes_content(mes_content);
		messageVO.setMes_cre(mes_cre);
		dao.insert(messageVO);
		return messageVO;
	}
	public MessageVO updateMessage(Integer mes_no,Integer blog_no,Integer mem_no,String mes_content,java.sql.Date mes_cre){
		MessageVO messageVO = new MessageVO();
		messageVO.setMes_no(mes_no);
		messageVO.setBlog_no(blog_no);
		messageVO.setMem_no(mem_no);
		messageVO.setMes_content(mes_content);
		messageVO.setMes_cre(mes_cre);
		dao.update(messageVO);
		return messageVO;
	}
	public void deleteMessage(Integer mes_no){
		dao.delete(mes_no);
	}
	public MessageVO getOneMessage(Integer mes_no){
		return dao.findByPrimaryKey(mes_no);
	}
	public List<MessageVO> getAll(){
		return dao.getAll();
	}
	public List<MessageVO> getAllByBlog(Integer blog_no){
		return dao.getAllByBlog(blog_no);
	}
}