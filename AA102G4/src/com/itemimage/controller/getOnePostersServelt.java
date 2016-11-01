package com.itemimage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

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
@WebServlet("/getOnePostersServelt")
public class getOnePostersServelt extends HttpServlet {
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
			Integer item_no = new Integer(req.getParameter("item_no"));
			List<ItemImageVO> ListItemImage = imageSvc.getOne(item_no);
			out.write(ListItemImage.get(0).getItem_img());
		}catch (Exception e)
		{
			System.out.println(e);
		}
	}

	

}
