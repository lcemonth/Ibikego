package com.itemdetails.model;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestFilmsModel
 */
@WebServlet("/TestItemDetailsModel")
public class TestItemDetailsModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/html; charset=UTF-8");      
				PrintWriter  out = response.getWriter();
				//�d�� ONE AND ALL
				ItemDetailsService itemdetailsSvc = new ItemDetailsService();
				out.print(itemdetailsSvc.BuyerScore(112).getItem_seller_score());
				
//				ItemDetailsVO itemdetail = itemdetailsSvc.getOneItemDetails(10029,117);
//				out.println(itemdetail.getItem_no());out.println(itemdetail.getMem_no());out.println(itemdetail.getItem_detail_status());
//				out.println(itemdetail.getItem_is_get());out.println(itemdetail.getItem_is_sellerscore());out.println(itemdetail.getItem_is_buyerscore());
//				out.println(itemdetail.getItem_detail_del());out.println(itemdetail.getItem_seller_score());out.println(itemdetail.getItem_buyer_score());
//				out.println(itemdetail.getItem_order_time());out.println(itemdetail.getItem_buyer_name());out.println(itemdetail.getItem_buyer_add());
//				out.println(itemdetail.getItem_buyer_phone());
//				
				
//				ItemDetailsVO itemdetails = itemdetailsSvc.getOneItemDetails(10029,117);
//				out.println(itemdetails.getMem_no());
				
				
//				List<ItemDetailsVO> list = itemdetailsSvc.getAll();
//				for (ItemDetailsVO  itemdetails:list)
//				{
//					out.print(itemdetails.getItem_buyer_name()+"\n");
//					
//				}
			
				//insert
//				itemdetailsSvc.addItemDetails(10027,115,1,1,1,0,0,0,0,new java.sql.Date(System.currentTimeMillis()),"��Z","��饫���c�Ϥ��j��300-5��","0955555555");
				
//				//update
//				itemdetailsSvc.updateItemDetails(10027,115,0,0,1,1,1,1,1,new Date(System.currentTimeMillis()),"aa","��饫���c�Ϥ��j��300-5��","0955555555");
//				//delete
//				itemdetailsSvc.deleteItemDetails(10019,116);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
