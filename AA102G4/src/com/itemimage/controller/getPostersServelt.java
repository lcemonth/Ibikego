package com.itemimage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itemimage.model.ItemImageService;
import com.itemimage.model.ItemImageVO;

/**
 * Servlet implementation class PostersServelt
 */
@WebServlet("/getPostersServelt")
public class getPostersServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		req.setCharacterEncoding("UTF-8");
	
		ServletOutputStream out = res.getOutputStream();
		byte [] b= null;
		try
		{
			ItemImageService imageSvc = new ItemImageService();
			Integer item_img_no = new Integer(req.getParameter("item_img_no"));
			ItemImageVO ItemImageVO = imageSvc.getOneItemImage(item_img_no);
			b= ItemImageVO.getItem_img();
			out.write(b);
		
			
			
		}catch (Exception e)
		{
			System.out.println(e);
		}
	}

	

}
