package com.itemimage.controller;

import java.io.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.itemimage.model.*;
import com.member.model.MemberVO;
import com.item.model.*;
@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class ItemImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listItemImages_ByItem_no.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數****************************************/
				Integer item_img_no = new Integer(req.getParameter("item_img_no"));
				
				/***************************2.開始查詢資料****************************************/
				ItemImageService imageSvc = new ItemImageService();
				ItemImageVO itemImageVO = imageSvc.getOneItemImage(item_img_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("itemImageVO", itemImageVO);         // 資料庫取出的empVO物件,存入req
				String url = "front-end/itemimage/update_itemimage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_itemimage_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = new Integer(memberVO.getMem_no());
				Integer item_img_no = new Integer(req.getParameter("item_img_no").trim());
				Integer item_no = new Integer(req.getParameter("item_no").trim());
				byte[] item_img =null;
				InputStream in = req.getPart("item_img").getInputStream();
				if(in.available()!=0){
					item_img = new byte[in.available()];
					in.read(item_img);
					in.close();
					}else{
					ItemImageService imageSvc = new ItemImageService();
					ItemImageVO itemVO = imageSvc.getOneItemImage(item_img_no);
					item_img = itemVO.getItem_img();
				}

				ItemImageVO itemImageVO = new ItemImageVO();
				itemImageVO.setItem_img_no(item_img_no);
				itemImageVO.setItem_no(item_no);
				itemImageVO.setItem_img(item_img);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemImageVO", itemImageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/itemimage/update_itemimage_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ItemImageService imageSvc = new ItemImageService();
				itemImageVO = imageSvc.updateItemImage(item_img_no, item_no, item_img);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				ItemService itemSvc = new ItemService();
				List<ItemVO> itemVO= itemSvc.getOneItemMem(mem_no);
				req.setAttribute("itemList", itemVO);
				if(requestURL.equals("/front-end/item/listItemImage_ByMem_no.jsq") || requestURL.equals("/back-end/item/listOneItemMem_no.jsp"))
					req.setAttribute("listItemImages_ByItem_no",itemSvc.getItemImageByItemno(item_no));
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			
		}
		if ("getOne_For_Insert".equals(action)) { // 來自listAllItem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
		
				/***************************1.接收請求參數****************************************/
			Integer item_no = new Integer(req.getParameter("item_no"));				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("item_no", item_no);         // 資料庫取出的VO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("front-end/itemimage/addItemImage.jsp");// 成功轉交 update__input.jsp
				successView.forward(req, res);
			} 

		if ("insert".equals(action)) { // 來自add.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			HttpSession session = req.getSession();//登入取得
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//登入取得
			/************************ 1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer mem_no = new Integer(memberVO.getMem_no());//登入取得
			Integer item_no = new Integer(req.getParameter("item_no").trim());
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				if (part.getContentType() != null) {
//					System.out.println(part.getName());
					InputStream in = part.getInputStream();
					byte[] item_img = new byte[in.available()];
					in.read(item_img);
					
					in.close();

					ItemImageVO itemImageVO = new ItemImageVO();
					itemImageVO.setItem_no(item_no);
					itemImageVO.setItem_img(item_img);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("itemImageVO", itemImageVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("front-end/itemimage/addItemImage.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
			/**************************** 2.開始新增資料***************************************/
					ItemImageService imageSvc = new ItemImageService();
					itemImageVO = imageSvc.addItemImage(item_no, item_img);
				}
			}
			/**************************** 3.新增完成,準備轉交(Send the Success view)***********/
			ItemService itemSvc = new ItemService();
			List<ItemVO> itemVO= itemSvc.getOneItemMem(mem_no);//登入取得
			req.setAttribute("itemList", itemVO);//存登入編號
			if (requestURL.equals("/front-end/item/listOneItemMem_no.jsp"))//前端會員各自的商品
				req.setAttribute("listItemImages_ByItem_no", itemSvc.getItemImageByItemno(item_no));
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		}
		
		
		if ("delete".equals(action)) { // 來自listItemImages_ByItem_no.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			try {
				/***************************1.接收請求參數***************************************/
				Integer mem_no = new Integer(memberVO.getMem_no());
				Integer item_img_no = new Integer(req.getParameter("item_img_no"));
				
				/***************************2.開始刪除資料***************************************/
				ItemImageService imageSvc = new ItemImageService();
				ItemImageVO itemImageVO = imageSvc.getOneItemImage(item_img_no);
				imageSvc.deleteItemImage(item_img_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				ItemService itemSvc = new ItemService();
				List<ItemVO> itemVO= itemSvc.getOneItemMem(mem_no);
				req.setAttribute("itemList", itemVO);
				if(requestURL.equals("/back-end/item/listItemImages_ByItem_no.jsp")||requestURL.equals("/front-end/item/listOneItemMem_no.jsp"))
					req.setAttribute("listItemImages_ByItem_no",itemSvc.getItemImageByItemno(itemImageVO.getItem_no()));
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
