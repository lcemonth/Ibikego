package com.item.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.itemimage.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.item.model.*;
import com.itemdetails.model.*;
import com.tool.*;
@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class ItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//全部商品頁面
		if("searchall".equals(action)){
			String url = "back-end/item/listAllItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("searchallover".equals(action)){
			String url = "back-end/item/listAllItemover.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//新增商品頁面
		if("add".equals(action)){
			String url = "back-end/item/addItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//新增商品頁面一對多
				if("addall".equals(action)){
					String url = "back-end/item/addItemall.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
		//單一商品頁面
		if("index".equals(action)){
			String url = "back-end/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//商品全部圖片incude
		if ("listItemimages_ByItem_no_B".equals(action)) {
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer item_no = new Integer(req.getParameter("item_no"));
				Integer mem_no = new Integer(memberVO.getMem_no());
				/*************************** 2.開始查詢資料 ****************************************/
				ItemService itemSvc = new ItemService();
				Set<ItemImageVO> set = itemSvc.getItemImageByItemno(item_no);
				List<ItemVO> itemVO= itemSvc.getOneItemMem(mem_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listItemImages_ByItem_no", set);// 資料庫取出的set物件,存入request
				req.setAttribute("itemList", itemVO);
				RequestDispatcher successView = req.getRequestDispatcher("front-end/item/listOneItemMem.jsp");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		//查詢單一商品條件來自listAllItem.jsp的請求||AllItem.jsp請求
		if ("getOne_For_Display".equals(action) || "getOne_For_Front".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**************************/
				String str = req.getParameter("item_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("back-end/item/listAllItem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer item_no = null;
				try {
					item_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("back-end/item/listAllItem.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始查詢資料*****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(item_no);
				if (itemVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/item/listAllItem.jsp");
					failureView.forward(req, res);
					return;
				}
				Set<ItemImageVO> set = itemSvc.getItemImageByItemno(item_no);//查圖片編號in
				/***************************3.查詢完成,準備轉交(Send the Success view)**************/
				req.setAttribute("listItemImages_ByItem_no", set);// 資料庫取出的set物件,存入request
				req.setAttribute("itemVO", itemVO);
				String url = null;
				if("getOne_For_Display".equals(action))
				 url = "back-end/item/listOneItem.jsp";
				else if ("getOne_For_Front".equals(action))
				url = "front-end/item/listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/item/listAllItem.jsp");
				failureView.forward(req, res);
				}
			}
		//查詢個人商品
		if("MyItem".equals(action) ){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = new Integer(memberVO.getMem_no());
				/***************************2.開始查詢資料************************************/
				ItemService itemSvc = new ItemService();
				List<ItemVO> itemVO= itemSvc.getOneItemMem(mem_no);
				if(itemVO == null){
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("front-end/Login/Login.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*********/
				req.setAttribute("itemList", itemVO);			
				String url = "front-end/item/listOneItemMem.jsp";
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
		//查詢個人商品
				if("getOneMem".equals(action)){
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					try{
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer mem_no = new Integer(req.getParameter("mem_no"));
						/***************************2.開始查詢資料************************************/
						ItemService itemSvc = new ItemService();
						List<ItemVO> itemVO= itemSvc.getOneItemMem(mem_no);
						if(itemVO == null){
							errorMsgs.add("查無資料");
						}
						if(!errorMsgs.isEmpty()){
							RequestDispatcher failureView = req.getRequestDispatcher("front-end/Login/Login.jsp");
							failureView.forward(req, res);
							return;
						}
						/***************************3.查詢完成,準備轉交(Send the Success view)*********/
						req.setAttribute("itemList", itemVO);			
						String url = "back-end/item/listOneItemMem.jsp";
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
		//查詢商品[明細]來自listAllItem.jsp後端
		if ("Item_Details".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL = req.getParameter("requestURL"); //送出修改的來源網頁路徑
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**************************/
					String str = req.getParameter("item_no");
					Integer item_no = new Integer(str);
					/***************************2.開始查詢資料****************************************/
					ItemService itemSvc = new ItemService();
					ItemVO itemVO = itemSvc.getOneItem(item_no);
					Set<ItemImageVO> set = itemSvc.getItemImageByItemno(item_no);
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("listItemImages_ByItem_no", set);// 資料庫取出的set物件,存入request
					req.setAttribute("itemVO", itemVO);
					RequestDispatcher successView = req.getRequestDispatcher("back-end/item/Item_Details.jsp");
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					String url = requestURL;
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					}
				}
		
		//修改商品頁面來自listOneItemMem.jsp的請求,前端個人
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); //送出修改的來源網頁路徑
//			HttpSession session = req.getSession();//登入取得
//			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			try {
				/***************************1.接收請求參數****************************************/
//				Integer mem_no = new Integer(memberVO.getMem_no());//登入取得
				Integer item_no = new Integer(req.getParameter("item_no"));
				/***************************2.開始查詢資料****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(item_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				List<ItemVO> itemVO1= itemSvc.getOneItemMem(mem_no);//登入取得
//				req.setAttribute("itemList", itemVO1);//存登入編號
				req.setAttribute("itemVO", itemVO);// 資料庫取出的VO物件,存入req
				String url = "front-end/item/update_item_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//轉交 update_item_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/item/listAllItem.jsp");
				failureView.forward(req, res);
				}
			}
		
		// 來自update_item_input.jsp的請求
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); //送出修改的來源網頁路徑
			HttpSession session = req.getSession();//登入取得
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no1 = new Integer(memberVO.getMem_no());//登入取得
				Integer item_no = new Integer(req.getParameter("item_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String item_name = req.getParameter("item_name").trim();
				Integer item_price = null;
				try {
					item_price = new Integer(req.getParameter("item_price").trim());
				} catch (NumberFormatException e) {
					item_price = 0;
					errorMsgs.add("價格請填數字.");
				}
				String item_exp = req.getParameter("item_exp").trim();
				Integer item_is_added = new Integer(req.getParameter("item_is_added").trim());

				ItemVO itemVO = new ItemVO();
				itemVO.setItem_no(item_no);
				itemVO.setMem_no(mem_no);
				itemVO.setItem_name(item_name);
				itemVO.setItem_price(item_price);
				itemVO.setItem_exp(item_exp);
				itemVO.setItem_is_added(item_is_added);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的itemVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				ItemService empSvc = new ItemService();
				itemVO = empSvc.updateItem(item_no, mem_no, item_name, item_price, item_exp,item_is_added);
				/***************************3.修改完成,準備轉交(Send the Success view)**************/
				req.setAttribute("itemVO", itemVO); // 從資料庫update成功後,正確的itemVO物件,存入req
				ItemService itemSvc = new ItemService();
				List<ItemVO> itemVO1= itemSvc.getOneItemMem(mem_no1);//登入取得
				req.setAttribute("itemList", itemVO1);//存登入編號
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOne.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理***************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/item/update_item_input.jsp");
				failureView.forward(req, res);
			}
		}
		//select_page.jsp來自add.jsp的請求  
        if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();//登入取得
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理******************************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String item_name = req.getParameter("item_name").trim();
				Integer item_price = null;
				try {
					item_price = new Integer(req.getParameter("item_price").trim());
				} catch (NumberFormatException e) {
					item_price = 0;
					errorMsgs.add("價格請填數字.");
				}
				String item_exp = req.getParameter("item_exp").trim();
				Integer item_is_added = new Integer(req.getParameter("item_is_added").trim());

				ItemVO itemVO = new ItemVO();
				itemVO.setMem_no(mem_no);
				itemVO.setItem_name(item_name);
				itemVO.setItem_price(item_price);
				itemVO.setItem_exp(item_exp);
				itemVO.setItem_is_added(item_is_added);
				List<ItemImageVO> list =new ArrayList<>();
				
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if (part.getContentType() != null) {
						InputStream in = part.getInputStream();
						byte[] item_img = new byte[in.available()];
						in.read(item_img);
						
						in.close();

						ItemImageVO itemImageVO = new ItemImageVO();
						itemImageVO.setItem_img(item_img);

						list.add(itemImageVO);
					}
				}
								
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("front-end/item/AllItem.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ItemService itemSvc = new ItemService();
				itemSvc.insertWithItems(itemVO, list);
			
				/***************************3.新增完成,準備轉交(Send the Success view)************/
				String url = "front-end/item/AllItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交listAll.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("front-end/item/AllItem.jsp");
				failureView.forward(req, res);
			}
		}
      //add.jsp的請求一對多
        if ("insertall".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理******************************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String item_name = req.getParameter("item_name").trim();
				Integer item_price = null;
				try {
					item_price = new Integer(req.getParameter("item_price").trim());
				} catch (NumberFormatException e) {
					item_price = 0;
					errorMsgs.add("價格請填數字.");
				}
				String item_exp = req.getParameter("item_exp").trim();
				Integer item_is_added = new Integer(req.getParameter("item_is_added").trim());

				ItemVO itemVO = new ItemVO();
				itemVO.setMem_no(mem_no);
				itemVO.setItem_name(item_name);
				itemVO.setItem_price(item_price);
				itemVO.setItem_exp(item_exp);
				itemVO.setItem_is_added(item_is_added);
				
				List<ItemImageVO> list=new ArrayList<>();
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if (part.getContentType() != null) {
						InputStream in = part.getInputStream();
						byte[] item_img = new byte[in.available()];
						in.read(item_img);
						
						in.close();

						ItemImageVO itemImageVO = new ItemImageVO();
						itemImageVO.setItem_img(item_img);
						list.add(itemImageVO);				
					}
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/item/addItemall.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ItemService itemSvc = new ItemService();
				itemSvc.insertWithItems(itemVO, list);;
				/***************************3.新增完成,準備轉交(Send the Success view)************/
				String url = "back-end/item/listAllItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交listAll.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/item/addItemall.jsp");
				failureView.forward(req, res);
			}
		}
        //來自listAll.jsp刪除的請求
		if ("delete".equals(action) || "deleteOne".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			HttpSession session = req.getSession();//登入取得
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			try {
				/***************************1.接收請求參數***************************************/
				Integer mem_no = new Integer(memberVO.getMem_no());//登入取得
				Integer item_no = new Integer(req.getParameter("item_no"));
				/***************************2.開始刪除資料***************************************/
				ItemService itemSvc = new ItemService();
				itemSvc.deleteItem(item_no);			
				
				/***************************3.刪除完成,準備轉交(Send the Success view)************/
				
			String url = null;
			if("delete".equals(action))
				url = requestURL;
				else if("deleteOne".equals(action))
				url = "back-end/item/listAllItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料已在訂單中...");
				String url = requestURL;
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		//來自listAll.jsp刪除的請求
				if ("deleteOne2".equals(action)) { 
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					String requestURL = req.getParameter("requestURL");
					HttpSession session = req.getSession();//登入取得
					MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
					try {
						/***************************1.接收請求參數***************************************/
						Integer mem_no = new Integer(memberVO.getMem_no());//登入取得
						Integer item_no = new Integer(req.getParameter("item_no"));
						/***************************2.開始刪除資料***************************************/
						ItemService itemSvc = new ItemService();
						itemSvc.deleteItem(item_no);
						/***************************3.刪除完成,準備轉交(Send the Success view)************/
						String url = "front-end/item/listOneItemMem.jsp";
						List<ItemVO> itemVO= itemSvc.getOneItemMem(mem_no);//登入取得
						req.setAttribute("itemList", itemVO);//存登入編號
						RequestDispatcher successView = req.getRequestDispatcher(url);//刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);
						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("刪除資料已在訂單中...");
						String url = requestURL;
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
					}
				}
		// 來自listAll.jsp的複合查詢請求
		if ("listItems_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.將輸入資料轉為Map**********************************/ 
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				}
				/***************************2.開始複合查詢***************************************/
				ItemService itemSvc = new ItemService();
				List<ItemVO> list  = itemSvc.getAll(map);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listItems_ByCompositeQuery", list); //資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("back-end/item/listItems_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/item/listAllItem.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
