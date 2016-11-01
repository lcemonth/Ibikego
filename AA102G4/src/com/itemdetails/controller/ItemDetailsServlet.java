package com.itemdetails.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpVO;
import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.itemdetails.model.ItemDetailsService;
import com.itemdetails.model.ItemDetailsVO;
import com.member.model.MemberVO;

/**
 * Servlet implementation class ItemDetailsServlet
 */
@WebServlet("/ItemDetailsServlet")
public class ItemDetailsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		doPost(req,res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//查詢全部訂單
		if("allitemdetails".equals(action)){
			String url = "back-end/itemdetails/listAllItemDetails.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		//查詢個人訂單後端
		if("getOneBuyer".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				/***************************2.開始查詢資料************************************/
				ItemDetailsService itemdetailsSvc = new ItemDetailsService();
				List<ItemDetailsVO> itemDetailsVO= itemdetailsSvc.getOneItemDetailsBuyer(mem_no);
				if(itemDetailsVO == null){
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("back-end/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*********/
				req.setAttribute("itemDetailsList", itemDetailsVO);				  
				String url = "back-end/itemdetails/listOneItemDetailsBuyer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e){
				errorMsgs.add("無訂單資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/select_page.jsp");
				failureView.forward(req,res);
			}
		}
		//查詢個人訂單前端
				if("getOneBuyer_front".equals(action)){
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					HttpSession session = req.getSession();//登入取得
					MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
					try{
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer mem_no = new Integer(memberVO.getMem_no());
						/***************************2.開始查詢資料************************************/
						ItemDetailsService itemdetailsSvc = new ItemDetailsService();
						List<ItemDetailsVO> itemDetailsVO= itemdetailsSvc.getOneItemDetailsBuyer(mem_no);
						if(itemDetailsVO == null){
							errorMsgs.add("查無資料");
						}
						if(!errorMsgs.isEmpty()){
							RequestDispatcher failureView = req.getRequestDispatcher("front-end/Login/Login.jsp");
							failureView.forward(req, res);
							return;
						}
						/***************************3.查詢完成,準備轉交(Send the Success view)*********/
						req.setAttribute("itemDetailsList", itemDetailsVO);				  
						String url = "front-end/itemdetails/listOneItemDetailsBuyer.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						/***************************其他可能的錯誤處理**********************************/
					}catch (Exception e){
						errorMsgs.add("請登入會員" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("front-end/Login/Login.jsp");
						failureView.forward(req,res);
					}
				}
				//查詢個人出售商品訂單前端
				if("getOneSeller_front".equals(action)){
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					HttpSession session = req.getSession();//登入取得
					MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
					try{
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer mem_no = new Integer(memberVO.getMem_no());
						/***************************2.開始查詢資料************************************/
						ItemDetailsService itemdetailsSvc = new ItemDetailsService();
						List<ItemDetailsVO> list = itemdetailsSvc.getAll();
					
						if(memberVO == null){
							errorMsgs.add("查無資料");
						}
						if(!errorMsgs.isEmpty()){
							RequestDispatcher failureView = req.getRequestDispatcher("front-end/Login/Login.jsp");
							failureView.forward(req, res);
							return;
						}
						/***************************3.查詢完成,準備轉交(Send the Success view)*********/
						req.setAttribute("list",list);				  
						String url = "front-end/itemdetails/listOneItemDetailsSeller.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						/***************************其他可能的錯誤處理**********************************/
					}catch (Exception e){
						errorMsgs.add("請登入會員" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("front-end/Login/Login.jsp");
						failureView.forward(req,res);
					}
				}
	
		//來自listAllItem.jsp的新增訂單
		if ("getOne_For_Insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
				/***************************1.接收請求參數****************************************/
		   
			Integer item_no = new Integer(req.getParameter("item_no"));
			Integer mem_no = new Integer(req.getParameter("mem_no"));	
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("item_no", item_no);//值存入req
				req.setAttribute("mem_no", mem_no); 
				String url = "front-end/itemdetails/addItemDetails.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 addItemDetails.jsp
				successView.forward(req, res);
			}
		//新增訂單
		if("insert".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();//登入取得
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer item_no = new Integer(req.getParameter("item_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer item_detail_status = new Integer(req.getParameter("item_detail_status").trim());
				Integer item_is_get = new Integer(req.getParameter("item_is_get").trim());
				Integer item_is_sellerscore = new Integer(req.getParameter("item_is_sellerscore").trim());
				Integer item_is_buyerscore = new Integer(req.getParameter("item_is_buyerscore").trim());
				Integer item_detail_del = new Integer(req.getParameter("item_detail_del").trim());
				Integer item_seller_score = new Integer(req.getParameter("item_seller_score").trim());
				Integer item_buyer_score = new Integer(req.getParameter("item_buyer_score").trim());
				Date	item_order_time = new java.sql.Date(System.currentTimeMillis());
				String item_buyer_name = req.getParameter("item_buyer_name").trim();
				String item_buyer_add = req.getParameter("item_buyer_add").trim();
				String item_buyer_phone = req.getParameter("item_buyer_phone").trim();
				
				ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
				
				itemDetailsVO.setItem_no(item_no);
				itemDetailsVO.setMem_no(mem_no);
				itemDetailsVO.setItem_detail_status(item_detail_status);
				itemDetailsVO.setItem_is_get(item_is_get);
				itemDetailsVO.setItem_is_sellerscore(item_is_sellerscore);
				itemDetailsVO.setItem_is_buyerscore(item_is_buyerscore);
				itemDetailsVO.setItem_detail_del(item_detail_del);
				itemDetailsVO.setItem_seller_score(item_seller_score);
				itemDetailsVO.setItem_buyer_score(item_buyer_score);
				itemDetailsVO.setItem_order_time(item_order_time);
				itemDetailsVO.setItem_buyer_name(item_buyer_name);
				itemDetailsVO.setItem_buyer_add(item_buyer_add);
				itemDetailsVO.setItem_buyer_phone(item_buyer_phone);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemDetailsVO", itemDetailsVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/itemdetails/addItemDetails.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
			/***************************2.開始新增資料***************************************/
			ItemDetailsService itemdetailsSvc = new ItemDetailsService();
			itemDetailsVO = itemdetailsSvc.addItemDetails(item_no,mem_no,item_detail_status,item_is_get,
							item_is_sellerscore,item_is_buyerscore,item_detail_del,item_seller_score,
							item_buyer_score,item_order_time,item_buyer_name,item_buyer_add,item_buyer_phone);
			//修改商品資料狀態為2='交易完成'
			ItemService itemSvc= new ItemService();
			ItemVO itemVO = itemSvc.getOneItem(item_no);
			itemSvc.updateItem(item_no, itemVO.getMem_no(), itemVO.getItem_name(),itemVO.getItem_price(), itemVO.getItem_exp(), 2);
			
			List<ItemDetailsVO> itemDetailsList = itemdetailsSvc.getOneItemDetailsBuyer(mem_no);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "front-end/item/AllItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add("請登入會員");
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/Login/Login.jsp");
				failureView.forward(req, res);
			}
		}
		//來自買家或賣家的給評分數需求
		if("getBuyerUpdate".equals(action) || "getSellerUpdate".equals(action) || "getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try{
				/***************************1.接收請求參數****************************************/
				Integer item_no = new Integer(req.getParameter("item_no"));
				/***************************2.開始查詢資料****************************************/
				ItemDetailsService itemdetailsSvc = new ItemDetailsService();
				ItemDetailsVO itemDetailsVO = itemdetailsSvc.getOneItemDetails(item_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("itemDetailsVO",itemDetailsVO);
				if("getBuyerUpdate".equals(action)){
				String url = "back-end/itemdetails/updateBuyer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交到updateBuyer.jsp
				successView.forward(req, res);
				}else if("getSellerUpdate".equals(action)){
				String url = "back-end/itemdetails/updateSeller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateSeller.jsp
				successView.forward(req, res);
				}else{
				req.setAttribute("itemDetailsVO", itemDetailsVO);
				String url = "back-end/itemdetails/update_itemdetails_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_itemdetails_input.jsp
				successView.forward(req, res);	
				}
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/item/listAllItemDetails.jsp");
				failureView.forward(req, res);
			}
		}
		//買家和賣家評分
		if("update_Buyer".equals(action) || "update_Seller".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			HttpSession session = req.getSession();//登入取得
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer item_no = new Integer(req.getParameter("item_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer item_detail_status = new Integer(req.getParameter("item_detail_status").trim());
				Integer item_is_get = new Integer(req.getParameter("item_is_get").trim());
				Integer item_is_sellerscore = new Integer(req.getParameter("item_is_sellerscore").trim());
				Integer item_is_buyerscore = new Integer(req.getParameter("item_is_buyerscore").trim());
				if(item_is_buyerscore==1 && item_is_sellerscore == 1){//買家和賣家已給評交易狀態為完成
					item_detail_status =1;
				};
				Integer item_seller_score = new Integer(req.getParameter("item_seller_score").trim());
				Integer item_buyer_score = new Integer(req.getParameter("item_buyer_score").trim());
				String item_buyer_name = req.getParameter("item_buyer_name").trim();
				String item_buyer_add = req.getParameter("item_buyer_add").trim();
				String item_buyer_phone = req.getParameter("item_buyer_phone").trim();
				
				ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
				itemDetailsVO.setItem_no(item_no);
				itemDetailsVO.setMem_no(mem_no);
				itemDetailsVO.setItem_detail_status(item_detail_status);
				itemDetailsVO.setItem_is_get(item_is_get);
				itemDetailsVO.setItem_is_sellerscore(item_is_sellerscore);
				itemDetailsVO.setItem_is_buyerscore(item_is_buyerscore);
				itemDetailsVO.setItem_seller_score(item_seller_score);
				itemDetailsVO.setItem_buyer_score(item_buyer_score);
				itemDetailsVO.setItem_buyer_name(item_buyer_name);
				itemDetailsVO.setItem_buyer_add(item_buyer_add);
				itemDetailsVO.setItem_buyer_phone(item_buyer_phone);
				/***************************2.開始修改資料*************************************/
				ItemDetailsService itemdetailsSvc = new ItemDetailsService();
				itemDetailsVO = itemdetailsSvc.updateItemDetails(item_no, mem_no, item_detail_status, item_is_get, item_is_sellerscore, item_is_buyerscore, item_seller_score, item_buyer_score, item_buyer_name, item_buyer_add, item_buyer_phone);
				List<ItemDetailsVO> itemDetailsVO1= itemdetailsSvc.getOneItemDetailsBuyer(mem_no);
				List<ItemDetailsVO> itemDetailsVO2= itemdetailsSvc.getAll();
				/***************************3.修改完成,準備轉交(Send the Success view)**********/
				req.setAttribute("itemDetailsList", itemDetailsVO1);//存登入編號
				req.setAttribute("list", itemDetailsVO2);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回原來網頁
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/itemdetails/listAllItemDetails.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		//修改訂單姓名電話地址
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			HttpSession session = req.getSession();//登入取得
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer item_no = new Integer(req.getParameter("item_no").trim());
				Integer mem_no = new Integer(memberVO.getMem_no());
				Integer item_detail_status = new Integer(req.getParameter("item_detail_status").trim());
				Integer item_is_get = new Integer(req.getParameter("item_is_get").trim());
				Integer item_is_sellerscore = new Integer(req.getParameter("item_is_sellerscore").trim());
				Integer item_is_buyerscore = new Integer(req.getParameter("item_is_buyerscore").trim());
				Integer item_seller_score = new Integer(req.getParameter("item_seller_score").trim());
				Integer item_buyer_score = new Integer(req.getParameter("item_buyer_score").trim());
				String item_buyer_name = req.getParameter("item_buyer_name").trim();
				String item_buyer_add = req.getParameter("item_buyer_add").trim();
				String item_buyer_phone = req.getParameter("item_buyer_phone").trim();
				ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
				itemDetailsVO.setItem_no(item_no);
				itemDetailsVO.setMem_no(mem_no);
				itemDetailsVO.setItem_detail_status(item_detail_status);
				itemDetailsVO.setItem_is_get(item_is_get);
				itemDetailsVO.setItem_is_sellerscore(item_is_sellerscore);
				itemDetailsVO.setItem_is_buyerscore(item_is_buyerscore);
				itemDetailsVO.setItem_seller_score(item_seller_score);
				itemDetailsVO.setItem_buyer_score(item_buyer_score);
				itemDetailsVO.setItem_buyer_name(item_buyer_name);
				itemDetailsVO.setItem_buyer_add(item_buyer_add);
				itemDetailsVO.setItem_buyer_phone(item_buyer_phone);
				/***************************2.開始修改資料*************************************/
				ItemDetailsService itemdetailsSvc = new ItemDetailsService();
				itemDetailsVO = itemdetailsSvc.updateItemDetails(item_no, mem_no, item_detail_status, item_is_get, item_is_sellerscore, item_is_buyerscore, item_seller_score, item_buyer_score, item_buyer_name, item_buyer_add, item_buyer_phone);
				List<ItemDetailsVO> itemDetailsVO1= itemdetailsSvc.getOneItemDetailsBuyer(mem_no);
				/***************************3.修改完成,準備轉交(Send the Success view)**********/
				req.setAttribute("itemDetailsList", itemDetailsVO1);//存登入編號
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOne.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/itemdetails/listOneItemDetailsBuyer.jsp");
				failureView.forward(req, res);
			}
		}
		//買家分數
		if("BuyerScore".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				
				
				/***************************2.開始查詢資料************************************/
				ItemDetailsService itemdetailsSvc = new ItemDetailsService();
				ItemDetailsVO itemDetailsVO= itemdetailsSvc.BuyerScore(mem_no);
				if(itemDetailsVO == null){
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*********/
				req.setAttribute("itemDetailsVO", itemDetailsVO);				  
				String url = "back-end/itemdetails/BuyerScore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*********************************/
			}catch (Exception e){
				errorMsgs.add("無分數資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/select_page.jsp");
				failureView.forward(req,res);
				
			}
			
		}
		//賣家分數
				if("SellerScore".equals(action)){
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					try{
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer mem_no = new Integer(req.getParameter("mem_no"));
						
						
						/***************************2.開始查詢資料************************************/
						ItemDetailsService itemdetailsSvc = new ItemDetailsService();
						ItemDetailsVO itemDetailsVO= itemdetailsSvc.SellerScore(mem_no);
						if(itemDetailsVO == null){
							errorMsgs.add("查無資料");
						}
						if(!errorMsgs.isEmpty()){
							RequestDispatcher failureView = req
									.getRequestDispatcher("back-end/select_page.jsp");
							failureView.forward(req, res);
							return;
						}
						/***************************3.查詢完成,準備轉交(Send the Success view)*********/
						req.setAttribute("itemDetailsVO", itemDetailsVO);				  
						String url = "back-end/itemdetails/SellerScore.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						/***************************其他可能的錯誤處理*********************************/
					}catch (Exception e){
						errorMsgs.add("無分數資料" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("back-end/select_page.jsp");
						failureView.forward(req,res);
						
					}
					
				}
		//取消訂單
		if ("cancel".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			HttpSession session = req.getSession();//登入取得
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer item_no = new Integer(req.getParameter("item_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer item_detail_del = new Integer(req.getParameter("item_detail_del").trim());
				ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
				itemDetailsVO.setItem_no(item_no);
				itemDetailsVO.setMem_no(mem_no);
				itemDetailsVO.setItem_detail_status(item_detail_del);
				/***************************2.開始修改資料*************************************/
				ItemDetailsService itemdetailsSvc = new ItemDetailsService();
				itemDetailsVO = itemdetailsSvc.cancel(item_detail_del, item_no, mem_no);
				
				ItemService itemSvc= new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(item_no);
				itemSvc.updateItem(item_no, itemVO.getMem_no(), itemVO.getItem_name(),itemVO.getItem_price(), itemVO.getItem_exp(), 1);
				List<ItemDetailsVO> itemDetailsVO1= itemdetailsSvc.getOneItemDetailsBuyer(mem_no);
				List<ItemDetailsVO> itemDetailsVO2= itemdetailsSvc.getAll();
				/***************************3.修改完成,準備轉交(Send the Success view)**********/
				req.setAttribute("itemDetailsList", itemDetailsVO1); // 資料庫update成功後,正確的的VO物件,存入req
				req.setAttribute("list", itemDetailsVO2);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOne.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/itemdetails/listAllItemDetails.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
	}
}
