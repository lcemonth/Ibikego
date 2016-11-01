package com.travel.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import com.travel.model.*;
import com.travelImage.model.*;
import com.reportcollect.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TravelServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		/**************************************導頁*****************************************/
		if("home".equals(action)){
			String url = "/back-end/travel/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 select_page.jsp
			successView.forward(req,res);
		}
		if("listAll".equals(action)){
			String url = "/back-end/travel/listAllTravel.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllTravel.jsp
			successView.forward(req,res);
		}
		if("add".equals(action)){
			String url = "/back-end/travel/addTravel.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addTravel.jsp
			successView.forward(req,res);
		}
		if("selectOne".equals(action)){
			String url = "/back-end/travel/selectOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 selectOne.jsp
			successView.forward(req,res);
		}
		/**********************************************************************************/
		if("getOne_For_Display".equals(action)){ // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("tra_no");
				if(str==null||(str.trim()).length()==0){
					errorMsgs.add("請輸入旅遊點編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				Integer tra_no = null;
				try{
					tra_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("旅遊點編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				TravelService travelSvc = new TravelService();
				TravelVO travelVO = travelSvc.getOneTravel(tra_no);
				if(travelVO==null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("travelVO",travelVO); // 資料庫取出的travelVO物件,存入req
				String url ="/back-end/travel/listOneTravel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTravel.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/selectOne.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("getOne_For_UserDisplay".equals(action)){ // 來自listAllTravelPoint.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("tra_no");
				if(str==null||(str.trim()).length()==0){
					errorMsgs.add("請輸入旅遊點編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				Integer tra_no = null;
				try{
					tra_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("旅遊點編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				TravelService travelSvc = new TravelService();
				TravelVO travelVO = travelSvc.getOneTravel(tra_no);
				if(travelVO==null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("travelVO",travelVO); // 資料庫取出的travelVO物件,存入session
				String url ="/front-end/travelPoint/listOneTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTravelPoint.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("getOne_For_Update".equals(action)){ // 來自listAllTravel.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				Integer tra_no = new Integer(req.getParameter("tra_no"));
				/***************************2.開始查詢資料****************************************/
				TravelService travelSvc = new TravelService();
				TravelVO travelVO = travelSvc.getOneTravel(tra_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("travelVO",travelVO);         // 資料庫取出的travelVO物件,存入req
				String url ="/back-end/travel/update_travel_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_travel_input.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/listAllTravel.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("getOne_For_UserUpdate".equals(action)){ // 來自listMyTravelPoint.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				Integer tra_no = new Integer(req.getParameter("tra_no"));
				/***************************2.開始查詢資料****************************************/
				TravelService travelSvc = new TravelService();
				TravelVO travelVO = travelSvc.getOneTravel(tra_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("travelVO",travelVO);         // 資料庫取出的travelVO物件,存入req
				String url ="/front-end/home/userUpdateTravel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_travel_input.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listMyTravelPoint.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("update".equals(action)){ // 來自update_travel_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer loc_no = null;
				try{
					loc_no = new Integer(req.getParameter("loc_no").trim());
				}catch(NumberFormatException e){
					loc_no = 0;
					errorMsgs.add("請輸入所屬地區!");
				}
				
				Integer tra_class_status = null;
				try{
					tra_class_status = new Integer(req.getParameter("tra_class_status").trim());
				}catch(NumberFormatException e){
					tra_class_status = 0;
					errorMsgs.add("請輸入類別!");
				}
				
				String tra_name = req.getParameter("tra_name").trim();
				if(tra_name==null || (tra_name.trim()).length()==0) errorMsgs.add("請輸入旅遊點名稱!");
				String tra_content = req.getParameter("tra_content").trim();
				if(tra_content.length()>=100) errorMsgs.add("內容不可超過100字!");
				String tra_tel = req.getParameter("tra_tel").trim();
				if(tra_tel==null || (tra_tel.trim()).length()==0) errorMsgs.add("請輸入電話!");
				String tra_add = req.getParameter("tra_add").trim();
				if(tra_add==null || (tra_add.trim()).length()==0) errorMsgs.add("請輸入地址!");
				java.sql.Date tra_cre = null;
				try {
					tra_cre = java.sql.Date.valueOf(req.getParameter("tra_cre").trim());
				} catch (IllegalArgumentException e) {
					tra_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double tra_lati = null;
				try{
					tra_lati = new Double(req.getParameter("tra_lati").trim());
				}catch(NumberFormatException e){
					tra_lati = 0.0;
				}
				
				Double tra_longi = null;
				try{
					tra_longi = new Double(req.getParameter("tra_longi").trim());
				}catch(NumberFormatException e){
					tra_longi = 0.0;
				}
				
				Integer tra_del = null;
				try{
					tra_del = new Integer(req.getParameter("tra_del").trim());
				}catch(NumberFormatException e){
					tra_del = 0;
					errorMsgs.add("請輸入是否刪除!");
				}

				TravelVO travelVO = new TravelVO();
				travelVO.setTra_no(tra_no);
				travelVO.setMem_no(mem_no);
				travelVO.setLoc_no(loc_no);
				travelVO.setTra_class_status(tra_class_status);
				travelVO.setTra_name(tra_name);
				travelVO.setTra_content(tra_content);
				travelVO.setTra_tel(tra_tel);
				travelVO.setTra_add(tra_add);
				travelVO.setTra_cre(tra_cre);
				travelVO.setTra_lati(tra_lati);
				travelVO.setTra_longi(tra_longi);
				travelVO.setTra_del(tra_del);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("travelVO", travelVO); // 含有輸入格式錯誤的travelVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/update_travel_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TravelService travelSvc = new TravelService();
				travelSvc.updateTravel(travelVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("travelVO",travelVO); // 資料庫update成功後,正確的travelVO物件,存入req
				String url = "/back-end/travel/listOneTravel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTravel.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/update_travel_input.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("userUpdate".equals(action)){ // 來自update_travel_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer loc_no = null;
				try{
					loc_no = new Integer(req.getParameter("loc_no").trim());
				}catch(NumberFormatException e){
					loc_no = 0;
					errorMsgs.add("請輸入所屬地區!");
				}
				
				Integer tra_class_status = null;
				try{
					tra_class_status = new Integer(req.getParameter("tra_class_status").trim());
				}catch(NumberFormatException e){
					tra_class_status = 0;
					errorMsgs.add("請輸入類別!");
				}
				
				String tra_name = req.getParameter("tra_name").trim();
				if(tra_name==null || (tra_name.trim()).length()==0) errorMsgs.add("請輸入旅遊點名稱!");
				
				String tra_content = req.getParameter("tra_content").trim();
				if(tra_content.length()>=100) errorMsgs.add("內容不可超過100字!");
				
				String tra_tel = req.getParameter("tra_tel").trim();
				if(tra_tel==null || (tra_tel.trim()).length()==0) errorMsgs.add("請輸入電話!");
				
				String tra_add = req.getParameter("tra_add").trim();
				if(tra_add==null || (tra_add.trim()).length()==0) errorMsgs.add("請輸入地址!");
				java.sql.Date tra_cre = null;
				try {
					tra_cre = java.sql.Date.valueOf(req.getParameter("tra_cre").trim());
				} catch (IllegalArgumentException e) {
					tra_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double tra_lati = null;
				try{
					tra_lati = new Double(req.getParameter("tra_lati").trim());
				}catch(NumberFormatException e){
					tra_lati = 0.0;
				}
				
				Double tra_longi = null;
				try{
					tra_longi = new Double(req.getParameter("tra_longi").trim());
				}catch(NumberFormatException e){
					tra_longi = 0.0;
				}
				
				Integer tra_del = null;
				try{
					tra_del = new Integer(req.getParameter("tra_del").trim());
				}catch(NumberFormatException e){
					tra_del = 0;
					errorMsgs.add("請輸入是否刪除!");
				}
				
				Part part = req.getPart("tra_img");
				InputStream in = part.getInputStream();
				byte[] tra_img = new byte[in.available()];
				if(getFileNameFromPart(part)!=null){
					tra_img=new byte[in.available()];
					in.read(tra_img);
				}else{
					TravelImageService travelImageSvc = new TravelImageService();
					TravelImageVO travelImageVO = travelImageSvc.getOneTravelImage(tra_no);
					tra_img = travelImageVO.getTra_img();
					if(tra_img !=null)
						in.read(tra_img);
				}
				in.close();

				TravelVO travelVO = new TravelVO();
				travelVO.setTra_no(tra_no);
				travelVO.setMem_no(mem_no);
				travelVO.setLoc_no(loc_no);
				travelVO.setTra_class_status(tra_class_status);
				travelVO.setTra_name(tra_name);
				travelVO.setTra_content(tra_content);
				travelVO.setTra_tel(tra_tel);
				travelVO.setTra_add(tra_add);
				travelVO.setTra_cre(tra_cre);
				travelVO.setTra_lati(tra_lati);
				travelVO.setTra_longi(tra_longi);
				travelVO.setTra_del(tra_del);
				
				TravelImageVO travelImageVO = new TravelImageVO();
				travelImageVO.setTra_img_no(tra_no);
				travelImageVO.setTra_no(tra_no);
				travelImageVO.setTra_img(tra_img);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("travelVO", travelVO); // 含有輸入格式錯誤的travelVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/userUpdateTravel.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TravelService travelSvc = new TravelService();
				travelSvc.updateTravel(travelVO);
				TravelImageService travelImageSvc = new TravelImageService();
				travelImageSvc.updateTravelImage(travelImageVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("travelVO",travelVO); // 資料庫update成功後,正確的travelVO物件,存入req
				String url = "/front-end/home/listMyTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMyTravelPoint.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/userUpdateTravel.jsp");
				failureView.forward(req,res);
			}
		}
		
        if("insert".equals(action)){ // 來自addTravel.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			List<TravelImageVO> images = new LinkedList<TravelImageVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer loc_no = null;
				try{
					loc_no = new Integer(req.getParameter("loc_no").trim());
				}catch(NumberFormatException e){
					loc_no = 0;
					errorMsgs.add("請輸入所屬地區!");
				}
				
				Integer tra_class_status = null;
				try{
					tra_class_status = new Integer(req.getParameter("tra_class_status").trim());
				}catch(NumberFormatException e){
					tra_class_status = 0;
					errorMsgs.add("請輸入類別!");
				}
				
				String tra_name = req.getParameter("tra_name").trim();
				if(tra_name==null || (tra_name.trim()).length()==0) errorMsgs.add("請輸入旅遊點名稱!");
				String tra_content = req.getParameter("tra_content").trim();
				if(tra_content.length()>=100) errorMsgs.add("內容不可超過100字!");
				String tra_tel = req.getParameter("tra_tel").trim();
				if(tra_tel==null || (tra_tel.trim()).length()==0) errorMsgs.add("請輸入電話!");
				String tra_add = req.getParameter("tra_add").trim();
				if(tra_add==null || (tra_add.trim()).length()==0) errorMsgs.add("請輸入地址!");
				java.sql.Date tra_cre = null;
				try {
					tra_cre = java.sql.Date.valueOf(req.getParameter("tra_cre").trim());
				} catch (IllegalArgumentException e) {
					tra_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double tra_lati = null;
				try{
					tra_lati = new Double(req.getParameter("tra_lati").trim());
				}catch(NumberFormatException e){
					tra_lati = 0.0;
				}
				
				Double tra_longi = null;
				try{
					tra_longi = new Double(req.getParameter("tra_longi").trim());
				}catch(NumberFormatException e){
					tra_longi = 0.0;
				}
				
				Integer tra_del = null;
				try{
					tra_del = new Integer(req.getParameter("tra_del").trim());
				}catch(NumberFormatException e){
					tra_del = 0;
					errorMsgs.add("請輸入是否刪除!");
				}
				
				Part part = req.getPart("tra_img");
				InputStream in = part.getInputStream();
				byte[] tra_img = new byte[in.available()];
				in.read(tra_img);
				in.close();

				TravelVO travelVO = new TravelVO();
				travelVO.setMem_no(mem_no);
				travelVO.setLoc_no(loc_no);
				travelVO.setTra_class_status(tra_class_status);
				travelVO.setTra_name(tra_name);
				travelVO.setTra_content(tra_content);
				travelVO.setTra_tel(tra_tel);
				travelVO.setTra_add(tra_add);
				travelVO.setTra_cre(tra_cre);
				travelVO.setTra_lati(tra_lati);
				travelVO.setTra_longi(tra_longi);
				travelVO.setTra_del(tra_del);
				
				TravelImageVO travelImageVO = new TravelImageVO();
				travelImageVO.setTra_img(tra_img);
				images.add(travelImageVO);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("travelVO",travelVO); // 含有輸入格式錯誤的travelVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/addTravel.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TravelService travelSvc = new TravelService();
				travelSvc.addTravelWithImages(travelVO,images);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/travel/listAllTravel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTravel.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/addTravel.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("userInsert".equals(action)){ // 來自addTravel.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			List<TravelImageVO> images = new LinkedList<TravelImageVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer loc_no = null;
				try{
					loc_no = new Integer(req.getParameter("loc_no").trim());
				}catch(NumberFormatException e){
					loc_no = 0;
					errorMsgs.add("請輸入所屬地區!");
				}
				
				Integer tra_class_status = null;
				try{
					tra_class_status = new Integer(req.getParameter("tra_class_status").trim());
				}catch(NumberFormatException e){
					tra_class_status = 0;
					errorMsgs.add("請輸入類別!");
				}
				
				String tra_name = req.getParameter("tra_name").trim();
				if(tra_name==null || (tra_name.trim()).length()==0) errorMsgs.add("請輸入旅遊點名稱!");
				String tra_content = req.getParameter("tra_content").trim();
				if(tra_content.length()>=100) errorMsgs.add("內容不可超過100字!");
				String tra_tel = req.getParameter("tra_tel").trim();
				if(tra_tel==null || (tra_tel.trim()).length()==0) errorMsgs.add("請輸入電話!");
				String tra_add = req.getParameter("tra_add").trim();
				if(tra_add==null || (tra_add.trim()).length()==0) errorMsgs.add("請輸入地址!");
				java.sql.Date tra_cre = null;
				try {
					tra_cre = java.sql.Date.valueOf(req.getParameter("tra_cre").trim());
				} catch (IllegalArgumentException e) {
					tra_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double tra_lati = null;
				try{
					tra_lati = new Double(req.getParameter("tra_lati").trim());
				}catch(NumberFormatException e){
					tra_lati = 0.0;
				}
				
				Double tra_longi = null;
				try{
					tra_longi = new Double(req.getParameter("tra_longi").trim());
				}catch(NumberFormatException e){
					tra_longi = 0.0;
				}
				
				Integer tra_del = null;
				try{
					tra_del = new Integer(req.getParameter("tra_del").trim());
				}catch(NumberFormatException e){
					tra_del = 0;
					errorMsgs.add("請輸入是否刪除!");
				}
				
				Part part = req.getPart("tra_img");
				InputStream in = part.getInputStream();
				byte[] tra_img = new byte[in.available()];
				in.read(tra_img);
				in.close();

				TravelVO travelVO = new TravelVO();
				travelVO.setMem_no(mem_no);
				travelVO.setLoc_no(loc_no);
				travelVO.setTra_class_status(tra_class_status);
				travelVO.setTra_name(tra_name);
				travelVO.setTra_content(tra_content);
				travelVO.setTra_tel(tra_tel);
				travelVO.setTra_add(tra_add);
				travelVO.setTra_cre(tra_cre);
				travelVO.setTra_lati(tra_lati);
				travelVO.setTra_longi(tra_longi);
				travelVO.setTra_del(tra_del);
				
				TravelImageVO travelImageVO = new TravelImageVO();
				travelImageVO.setTra_img(tra_img);
				images.add(travelImageVO);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("travelVO",travelVO); // 含有輸入格式錯誤的travelVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/addTravel.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TravelService travelSvc = new TravelService();
				travelSvc.addTravelWithImages(travelVO,images);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/travelPoint/listAllTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTravelPoint.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/addTravel.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("searchPoint".equals(action)){ // 來自listAllTravelPoint.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			List<TravelVO> searchPoint = new LinkedList<TravelVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			TravelService travelSvc = new TravelService();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer tra_class_status = null;
				try{
					tra_class_status = new Integer(req.getParameter("tra_class_status").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請選擇旅遊點類別!");
				}
				
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/addTravel.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料***************************************/
				if(tra_class_status == 0){
					searchPoint = travelSvc.searchAttractions();
					req.setAttribute("searchPoint", searchPoint);
				}else if(tra_class_status == 1){
					searchPoint = travelSvc.searchBreak();
					req.setAttribute("searchPoint", searchPoint);
				}else{
					req.removeAttribute("searchPoint");
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/travelPoint/listAllTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTravelPoint.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/addTravel.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("userInsertCollect".equals(action)){ // 來自listOneTravelPoint.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			ReportcollectService reportcollectSvc = new ReportcollectService();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer act_no = null;
				Integer forum_no = null;
				Integer blog_no = null;
				Integer stroke_no = null;
				Integer rc_rep_handle = 3;
				Integer rc_col_status = 0;
				Integer rep_rel = 1;
				String rep_content = null;
				
				ReportcollectVO check = new ReportcollectVO();
				check = reportcollectSvc.checkTravelCollect(tra_no, mem_no);
				Integer rc_no = null;
				if(check != null) rc_no = (Integer)check.getRc_no();

				ReportcollectVO reportcollectVO = new ReportcollectVO();
				reportcollectVO.setMem_no(mem_no);
				reportcollectVO.setTra_no(tra_no);
				reportcollectVO.setAct_no(act_no);
				reportcollectVO.setForum_no(forum_no);
				reportcollectVO.setBlog_no(blog_no);
				reportcollectVO.setStroke_no(stroke_no);
				reportcollectVO.setRc_rep_handle(rc_rep_handle);
				reportcollectVO.setRc_col_status(rc_col_status);
				reportcollectVO.setRep_rel(rep_rel);
				reportcollectVO.setRep_content(rep_content);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("reportcollectVO",reportcollectVO); // 含有輸入格式錯誤的reportcollectVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				if(check == null) reportcollectSvc.addRep(mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				else reportcollectSvc.updateRep(rc_no,mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/travelPoint/listOneTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneTravelPoint.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("userCancelCollect".equals(action)){ // 來自listOneTravelPoint.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rc_no = new Integer(req.getParameter("rc_no").trim());
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer act_no = null;
				Integer forum_no = null;
				Integer blog_no = null;
				Integer stroke_no = null;
				Integer rc_rep_handle = 3;
				Integer rc_col_status = 1;
				Integer rep_rel = 1;
				String rep_content = null;

				ReportcollectVO reportcollectVO = new ReportcollectVO();
				reportcollectVO.setRc_no(rc_no);
				reportcollectVO.setMem_no(mem_no);
				reportcollectVO.setTra_no(tra_no);
				reportcollectVO.setAct_no(act_no);
				reportcollectVO.setForum_no(forum_no);
				reportcollectVO.setBlog_no(blog_no);
				reportcollectVO.setStroke_no(stroke_no);
				reportcollectVO.setRc_rep_handle(rc_rep_handle);
				reportcollectVO.setRc_col_status(rc_col_status);
				reportcollectVO.setRep_rel(rep_rel);
				reportcollectVO.setRep_content(rep_content);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportcollectVO", reportcollectVO); // 含有輸入格式錯誤的reportcollectVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ReportcollectService reportcollectSvc = new ReportcollectService();
				reportcollectVO = reportcollectSvc.updateRep(rc_no,mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportcollectVO",reportcollectVO); // 資料庫update成功後,正確的reportcollectVO物件,存入req
				String url = "/front-end/travelPoint/listOneTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTravel.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("cancelCollectFromHome".equals(action)){ // 來自listCollectTravelPoint.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rc_no = new Integer(req.getParameter("rc_no").trim());
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer act_no = null;
				Integer forum_no = null;
				Integer blog_no = null;
				Integer stroke_no = null;
				Integer rc_rep_handle = 3;
				Integer rc_col_status = 1;
				Integer rep_rel = 1;
				String rep_content = null;

				ReportcollectVO reportcollectVO = new ReportcollectVO();
				reportcollectVO.setRc_no(rc_no);
				reportcollectVO.setMem_no(mem_no);
				reportcollectVO.setTra_no(tra_no);
				reportcollectVO.setAct_no(act_no);
				reportcollectVO.setForum_no(forum_no);
				reportcollectVO.setBlog_no(blog_no);
				reportcollectVO.setStroke_no(stroke_no);
				reportcollectVO.setRc_rep_handle(rc_rep_handle);
				reportcollectVO.setRc_col_status(rc_col_status);
				reportcollectVO.setRep_rel(rep_rel);
				reportcollectVO.setRep_content(rep_content);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportcollectVO", reportcollectVO); // 含有輸入格式錯誤的reportcollectVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listCollectTravelPoint.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ReportcollectService reportcollectSvc = new ReportcollectService();
				reportcollectVO = reportcollectSvc.updateRep(rc_no,mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportcollectVO",reportcollectVO); // 資料庫update成功後,正確的reportcollectVO物件,存入req
				String url = "/front-end/home/listCollectTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTravel.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listCollectTravelPoint.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("userInsertReport".equals(action)){ // 來自listOneTravelPoint.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			List<String> warningMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			req.setAttribute("warningMsgs",warningMsgs);
			ReportcollectService reportcollectSvc = new ReportcollectService();
			ReportcollectVO reportcollectVO = new ReportcollectVO();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer act_no = null;
				Integer forum_no = null;
				Integer blog_no = null;
				Integer stroke_no = null;
				Integer rc_rep_handle = 0;
				Integer rc_col_status = 2;
				Integer rep_rel = 0;
				String rep_content = req.getParameter("rep_content").trim();
				if(rep_content==null || (rep_content.trim()).length()==0) errorMsgs.add("請輸入檢舉原因!");
				
				reportcollectVO = reportcollectSvc.checkTravelReport(tra_no, mem_no);
				if(reportcollectVO != null) warningMsgs.add("您已經檢舉過囉，正在處理檢舉項目!");

				reportcollectVO = new ReportcollectVO();
				reportcollectVO.setMem_no(mem_no);
				reportcollectVO.setTra_no(tra_no);
				reportcollectVO.setAct_no(act_no);
				reportcollectVO.setForum_no(forum_no);
				reportcollectVO.setBlog_no(blog_no);
				reportcollectVO.setStroke_no(stroke_no);
				reportcollectVO.setRc_rep_handle(rc_rep_handle);
				reportcollectVO.setRc_col_status(rc_col_status);
				reportcollectVO.setRep_rel(rep_rel);
				reportcollectVO.setRep_content(rep_content);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty() || !warningMsgs.isEmpty()){
					req.setAttribute("reportcollectVO",reportcollectVO); // 含有輸入格式錯誤的reportcollectVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				reportcollectVO = reportcollectSvc.addRep(mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/travelPoint/listOneTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneTravelPoint.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
				failureView.forward(req,res);
			}
		}
        
		if("delete".equals(action)){ // 來自listAllBlog.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數***************************************/
				Integer tra_no = new Integer(req.getParameter("tra_no"));
				/***************************2.開始刪除資料***************************************/
				TravelService travelSvc = new TravelService();
				travelSvc.deleteTravel(tra_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/travel/listAllTravel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travel/listAllTravel.jsp");
				failureView.forward(req,res);
			}
		}
		/*****************************0902*******************************/
        if("deleteTravel".equals(action)){ // 來自listMyTravelPoint.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer tra_no = null;
				try{
					tra_no = new Integer(req.getParameter("tra_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("查無資訊!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listMyTravelPoint.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TravelService travelSvc = new TravelService();
				travelSvc.updateDel(tra_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/front-end/home/listMyTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMyTravelPoint.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listMyTravelPoint.jsp");
				failureView.forward(req,res);
			}
		}
        /*****************************0902*******************************/
	}
	/***************************************0827*****************************************/
	private Object getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		  //System.out.println("header=" + header); // 測試用
		  String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		  //System.out.println("filename=" + filename); // 測試用
		  if (filename.length() == 0) {
		   return null;
		  }
		  return filename;
	}
	/***************************************0827*****************************************/
}