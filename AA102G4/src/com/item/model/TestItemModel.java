package com.item.model;



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
@WebServlet("/TestItemModel")
public class TestItemModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/html; charset=UTF-8");      
				PrintWriter  out = response.getWriter();
				//琩高 ONE AND ALL
//				ItemService itemsSvc = new ItemService();
//				ItemVO item = itemsSvc.getOneItem(10018);
//				out.println(item.getItem_no());
//				out.println(item.getMem_no());
//				out.println(item.getItem_name());
//				out.println(item.getItem_price());
//				out.println(item.getItem_exp());
//				out.println(item.getItem_is_added());
				
				
//				ItemVO items = itemsSvc.getOneItem(10019);
//				out.println(items.getItem_name());
				
				
//				List<ItemVO> list = itemsSvc.getAll();
//				for (ItemVO  items: list)
//				{
//					out.print(items.getItem_no()+"\n");
//					
//				}
			
				//insert
//				itemsSvc.addItem(115,"︽ó10",2690,"絤策ず肕,琌﹚Τ璍靖,璶ㄓ近璍,狦ㄏノ繷璍,盢璍靖程翅",1);
				
//				//update
//				itemsSvc.updateItem(10019,111,"︽ó0",2690,"絤策ず肕,琌﹚Τ璍靖,璶ㄓ近璍,狦ㄏノ繷璍,盢璍靖程翅",0);
//				//delete
//				itemsSvc.deleteItem(10030);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
