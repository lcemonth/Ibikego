package com.travelScore.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.travelScore.model.*;

public class TravelScoreServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/**************************************導頁*****************************************/
		if("listAll".equals(action)){
			String url = "/back-end/travelScore/listAllTravelScore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllTravelScore.jsp
			successView.forward(req,res);
		}
		if("home".equals(action)){
			String url = "/back-end/travelScore/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 select_page.jsp
			successView.forward(req,res);
		}
		if("add".equals(action)){
			String url = "/back-end/travelScore/addTravelScore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addTravelScore.jsp
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelScore/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelScore/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				TravelScoreService travelScoreSvc = new TravelScoreService();
				TravelScoreVO travelScoreVO = travelScoreSvc.findOne(tra_no);
				if(travelScoreVO==null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelScore/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("travelScoreVO",travelScoreVO); // 資料庫取出的travelScoreVO物件,存入req
				String url ="/back-end/travelScore/listOneTravelScore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTravelScore.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelScore/select_page.jsp");
				failureView.forward(req,res);
			}
		}
//		if("getOne_For_Update".equals(action)){ // 來自listAllBlog.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs",errorMsgs);
//			try{
//				/***************************1.接收請求參數****************************************/
//				Integer blog_no = new Integer(req.getParameter("blog_no"));
//				/***************************2.開始查詢資料****************************************/
//				BlogService blogSvc = new BlogService();
//				BlogVO blogVO = blogSvc.getOneBlog(blog_no);
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("blogVO",blogVO);         // 資料庫取出的blogVO物件,存入req
//				String url ="/back-end/blog/update_blog_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_blog_input.jsp
//				successView.forward(req,res);
//				/***************************其他可能的錯誤處理**********************************/
//			}catch(Exception e){
//				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/listAllBlog.jsp");
//				failureView.forward(req,res);
//			}
//		}
//		if("update".equals(action)){ // 來自update_blog_input.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to send the ErrorPage view.
//			req.setAttribute("errorMsgs",errorMsgs);
//			
//			try{
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
//				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
//				String blog_title = req.getParameter("blog_title").trim();
//				if(blog_title==null || (blog_title.trim()).length()==0) errorMsgs.add("請輸入標題!");
//				String blog_content = req.getParameter("blog_content").trim();
//				java.sql.Date blog_cre = null;
//				try {
//					blog_cre = java.sql.Date.valueOf(req.getParameter("blog_cre").trim());
//				} catch (IllegalArgumentException e) {
//					blog_cre=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				Integer blog_del = new Integer(req.getParameter("blog_del").trim());
//
//				BlogVO blogVO = new BlogVO();
//				blogVO.setBlog_no(blog_no);
//				blogVO.setMem_no(mem_no);
//				blogVO.setBlog_title(blog_title);
//				blogVO.setBlog_content(blog_content);
//				blogVO.setBlog_cre(blog_cre);
//				blogVO.setBlog_del(blog_del);
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的blogVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/update_blog_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				BlogService blogSvc = new BlogService();
//				blogVO = blogSvc.updateBlog(blog_no,mem_no,blog_title,blog_content,blog_cre,blog_del);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("blogVO",blogVO); // 資料庫update成功後,正確的的blogVO物件,存入req
//				String url = "/back-end/blog/listOneBlog.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneBlog.jsp
//				successView.forward(req,res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			}catch(Exception e){
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/update_blog_input.jsp");
//				failureView.forward(req,res);
//			}
//		}
//        if("insert".equals(action)){ // 來自addTravelScore.jsp的請求  
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs",errorMsgs);
//			try{
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
//				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
//				
//				Integer tra_score = null;
//				try{
//					tra_score = new Integer(req.getParameter("tra_score").trim());
//				}catch(NumberFormatException e){
//					tra_score = 0;
//					errorMsgs.add("請輸入分數!");
//				}
//				
//				Integer tra_score_status = null;
//				try{
//					tra_score_status = new Integer(req.getParameter("tra_score_status").trim());
//				}catch(NumberFormatException e){
//					tra_score_status = 1;
//				}
//
//				TravelScoreVO travelScoreVO = new TravelScoreVO();
//				travelScoreVO.setTra_no(tra_no);
//				travelScoreVO.setMem_no(mem_no);
//				travelScoreVO.setTra_score(tra_score);
//				travelScoreVO.setTra_score_status(tra_score_status);
//				// Send the use back to the form, if there were errors
//				if(!errorMsgs.isEmpty()){
//					req.setAttribute("travelScoreVO",travelScoreVO); // 含有輸入格式錯誤的travelScoreVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelScore/addTravelScore.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				TravelScoreService travelScoreSvc = new TravelScoreService();
//				travelScoreVO = travelScoreSvc.addTravelScore(tra_no,mem_no,tra_score,tra_score_status);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/blogScore/listAllBlogScore.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBlogScore.jsp
//				successView.forward(req,res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			}catch(Exception e){
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/travelScore/addTravelScore.jsp");
//				failureView.forward(req,res);
//			}
//		}
        
        if("userInsert".equals(action)){ // 來自addTravelScore.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			List<String> warningMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			req.setAttribute("warningMsgs",warningMsgs);
			TravelScoreService travelScoreSvc = new TravelScoreService();
			TravelScoreVO travelScoreVO = new TravelScoreVO();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer tra_score = 1;
				Integer tra_score_status = 1;
				
				travelScoreVO = travelScoreSvc.getCheck(tra_no,mem_no);
				if(travelScoreVO != null) warningMsgs.add("您已經評分過囉!");
				
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTra_no(tra_no);
				travelScoreVO.setMem_no(mem_no);
				travelScoreVO.setTra_score(tra_score);
				travelScoreVO.setTra_score_status(tra_score_status);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty() || !warningMsgs.isEmpty()){
					req.setAttribute("travelScoreVO",travelScoreVO); // 含有輸入格式錯誤的travelScoreVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				travelScoreVO = travelScoreSvc.addTravelScore(tra_no,mem_no,tra_score,tra_score_status);
				
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
        
//		if("delete".equals(action)){ // 來自listAllBlogScore.jsp
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs",errorMsgs);
//			try{
//				/***************************1.接收請求參數***************************************/
//				Integer blog_no = new Integer(req.getParameter("blog_no"));
//				/***************************2.開始刪除資料***************************************/
//				BlogService blogSvc = new BlogService();
//				blogSvc.deleteBlog(blog_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/back-end/blog/listAllBlog.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req,res);
//				/***************************其他可能的錯誤處理**********************************/
//			}catch(Exception e){
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/listAllBlog.jsp");
//				failureView.forward(req,res);
//			}
//		}
	}
}