package com.travelImage.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import com.travelImage.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TravelImageServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/**************************************導頁*****************************************/
		if("home".equals(action)){
			String url = "/back-end/travelImage/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 select_page.jsp
			successView.forward(req,res);
		}
		if("listAll".equals(action)){
			String url = "/back-end/travelImage/listAllTravelImage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllTravelImage.jsp
			successView.forward(req,res);
		}
		if("add".equals(action)){
			String url = "/back-end/travelImage/addTravelImage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addTravelImage.jsp
			successView.forward(req,res);
		}
		if("selectOne".equals(action)){
			String url = "/back-end/travelImage/selectOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 selectOne.jsp
			successView.forward(req,res);
		}
		/**********************************************************************************/
		if("getOne_For_Display".equals(action)){ // 來自selectOne.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("tra_img_no");
				if(str==null||(str.trim()).length()==0){
					errorMsgs.add("請輸入圖片編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				Integer tra_img_no = null;
				try{
					tra_img_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("圖片編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				TravelImageService travelImageSvc = new TravelImageService();
				TravelImageVO travelImageVO = travelImageSvc.getOneTravelImage(tra_img_no);
				if(travelImageVO==null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("travelImageVO",travelImageVO); // 資料庫取出的travelImageVO物件,存入req
				String url ="/back-end/travelImage/listOneTravelImage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTravelImage.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/selectOne.jsp");
				failureView.forward(req,res);
			}
		}
		if("getOne_For_Update".equals(action)){ // 來自listAllTravelImage.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				Integer tra_img_no = new Integer(req.getParameter("tra_img_no"));
				/***************************2.開始查詢資料****************************************/
				TravelImageService travelImageSvc = new TravelImageService();
				TravelImageVO travelImageVO = travelImageSvc.getOneTravelImage(tra_img_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("travelImageVO",travelImageVO);         // 資料庫取出的travelImageVO物件,存入req
				String url ="/back-end/travelImage/update_travelImage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_travelImage_input.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/listAllTravelImage.jsp");
				failureView.forward(req,res);
			}
		}
		if("update".equals(action)){ // 來自update_travelImage_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer tra_img_no = new Integer(req.getParameter("tra_img_no").trim());
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				
				Part part = req.getPart("tra_img");
				InputStream in = part.getInputStream();
				byte[] tra_img = new byte[in.available()];
				if(getFileNameFromPart(part)!=null){
					tra_img=new byte[in.available()];
					in.read(tra_img);
				}else{
					TravelImageService travelImageSvc = new TravelImageService();
					TravelImageVO travelImageVO = travelImageSvc.getOneTravelImage(tra_img_no);
					tra_img = travelImageVO.getTra_img();
					if(tra_img !=null)
						in.read(tra_img);
				}
				in.close();

				TravelImageVO travelImageVO = new TravelImageVO();
				travelImageVO.setTra_img_no(tra_img_no);
				travelImageVO.setTra_no(tra_no);
				travelImageVO.setTra_img(tra_img);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("travelImageVO", travelImageVO); // 含有輸入格式錯誤的travelImageVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/update_travelImage_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TravelImageService travelImageSvc = new TravelImageService();
				travelImageVO = travelImageSvc.updateTravelImage(travelImageVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("travelImageVO",travelImageVO); // 資料庫update成功後,正確的的travelImageVO物件,存入req
				String url = "/back-end/travelImage/listOneTravelImage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTravelImage.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/update_travelImage_input.jsp");
				failureView.forward(req,res);
			}
		}
        if("insert".equals(action)){ // 來自addTravelImage.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/	
				Integer tra_no = null;
				try{
					tra_no = new Integer(req.getParameter("tra_no").trim());
				}catch(NumberFormatException e){
					tra_no = 0;
					errorMsgs.add("請輸入旅遊點編號!");
				}
				
				Part part = req.getPart("tra_img");
				InputStream in = part.getInputStream();
				byte[] tra_img = new byte[in.available()];
				in.read(tra_img);
				in.close();

				TravelImageVO travelImageVO = new TravelImageVO();
				travelImageVO.setTra_no(tra_no);
				travelImageVO.setTra_img(tra_img);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("travelImageVO",travelImageVO); // 含有輸入格式錯誤的travelImageVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/addTravelImage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TravelImageService travelImageSvc = new TravelImageService();
				travelImageVO = travelImageSvc.addTravelImage(tra_no,tra_img);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/travelImage/listAllTravelImage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTravelImage.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/addTravelImage.jsp");
				failureView.forward(req,res);
			}
		}
		if("delete".equals(action)){ // 來自listAllTravelImage.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數***************************************/
				Integer tra_img_no = new Integer(req.getParameter("tra_img_no"));
				/***************************2.開始刪除資料***************************************/
				TravelImageService travelImageSvc = new TravelImageService();
				travelImageSvc.deleteTravelImage(tra_img_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/travelImage/listAllTravelImage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelImage/listAllTravelImage.jsp");
				failureView.forward(req,res);
			}
		}
	}
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
}