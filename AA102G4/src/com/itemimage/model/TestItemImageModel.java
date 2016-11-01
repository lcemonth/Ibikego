package com.itemimage.model;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestFilmsModel
 */
@WebServlet("/TestItemImageModel")
public class TestItemImageModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/html; charset=UTF-8");      
				PrintWriter  out = response.getWriter();
				//�d�� ONE AND ALL
				ItemImageService imageSvc = new ItemImageService();
				ItemImageVO itemimage = imageSvc.getOneItemImage(9001);
				out.println(itemimage.getItem_img_no());
				out.println(itemimage.getItem_no());
				out.println(itemimage.getItem_img());
//								
				
//				List<ItemImageVO> list = imageSvc.getAll();
//				for (ItemImageVO  itemimages: list)
//				{
//					out.print(itemimages.getItem_img_no()+"\n");
//					
//				}
			
				//insert
//				imageSvc.addItemImage(10029,null);
				
//				//update
//				imageSvc.updateItemImage(9012, );
//				//delete
//				imageSvc.deleteItemImage(9013);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
