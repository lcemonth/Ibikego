package com.message.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.message.model.*;

public class MessageServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/**************************************導頁*****************************************/
		if("listAll".equals(action)){
			String url = "/back-end/message/listAllMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllMessage.jsp
			successView.forward(req,res);
		}
		if("home".equals(action)){
			String url = "/back-end/message/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 select_page.jsp
			successView.forward(req,res);
		}
		if("add".equals(action)){
			String url = "/back-end/message/addMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addMessage.jsp
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
				String str = req.getParameter("mes_no");
				if(str==null||(str.trim()).length()==0){
					errorMsgs.add("請輸入留言編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				Integer mes_no = null;
				try{
					mes_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				MessageService messageSvc = new MessageService();
				MessageVO messageVO = messageSvc.getOneMessage(mes_no);
				if(messageVO==null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageVO",messageVO); // 資料庫取出的messageVO物件,存入req
				String url ="/back-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMessage.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/select_page.jsp");
				failureView.forward(req,res);
			}
		}
		if("getOne_For_Update".equals(action)){ // 來自listAllMessage.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				Integer mes_no = new Integer(req.getParameter("mes_no"));
				/***************************2.開始查詢資料****************************************/
				MessageService messageSvc = new MessageService();
				MessageVO messageVO = messageSvc.getOneMessage(mes_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("messageVO",messageVO);         // 資料庫取出的messageVO物件,存入req
				String url ="/back-end/message/update_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_message_input.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/listAllMessage.jsp");
				failureView.forward(req,res);
			}
		}
		if("update".equals(action)){ // 來自update_message_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mes_no = new Integer(req.getParameter("mes_no").trim());
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String mes_content = req.getParameter("mes_content").trim();
				java.sql.Date mes_cre = null;
				try {
					mes_cre = java.sql.Date.valueOf(req.getParameter("mes_cre").trim());
				} catch (IllegalArgumentException e) {
					mes_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				MessageVO messageVO = new MessageVO();
				messageVO.setMes_no(mes_no);
				messageVO.setBlog_no(blog_no);
				messageVO.setMem_no(mem_no);
				messageVO.setMes_content(mes_content);
				messageVO.setMes_cre(mes_cre);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); // 含有輸入格式錯誤的messageVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/update_message_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MessageService messageSvc = new MessageService();
				messageVO = messageSvc.updateMessage(mes_no,blog_no,mem_no,mes_content,mes_cre);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageVO",messageVO); // 資料庫update成功後,正確的的messageVO物件,存入req
				String url = "/back-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMessage.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/update_message_input.jsp");
				failureView.forward(req,res);
			}
		}
        if("insert".equals(action)){ // 來自addMessage.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String mes_content = req.getParameter("mes_content").trim();
				java.sql.Date mes_cre = null;
				try {
					mes_cre = java.sql.Date.valueOf(req.getParameter("mes_cre").trim());
				} catch (IllegalArgumentException e) {
					mes_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				MessageVO messageVO = new MessageVO();
				messageVO.setBlog_no(blog_no);
				messageVO.setMem_no(mem_no);
				messageVO.setMes_content(mes_content);
				messageVO.setMes_cre(mes_cre);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("messageVO",messageVO); // 含有輸入格式錯誤的messageVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/addMessage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MessageService messageSvc = new MessageService();
				messageVO = messageSvc.addMessage(blog_no,mem_no,mes_content,mes_cre);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/message/listAllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMessage.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/addMessage.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("userInsert".equals(action)){ // 來自addMessage.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				
				String str = req.getParameter("mem_no");
				if(str==null||(str.trim()).length()==0){
					errorMsgs.add("請先登入或是註冊!");
				}
				Integer mem_no = null;
				try{
					mem_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				String mes_content = req.getParameter("mes_content").trim();
				if(mes_content.length()>=30) errorMsgs.add("留言不可超過30字!");
				if(mes_content.length()==0) errorMsgs.add("請輸入留言!");
				java.sql.Date mes_cre = null;
				try {
					mes_cre = java.sql.Date.valueOf(req.getParameter("mes_cre").trim());
				} catch (IllegalArgumentException e) {
					mes_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				MessageVO messageVO = new MessageVO();
				messageVO.setBlog_no(blog_no);
				messageVO.setMem_no(mem_no);
				messageVO.setMes_content(mes_content);
				messageVO.setMes_cre(mes_cre);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("messageVO",messageVO); // 含有輸入格式錯誤的messageVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listOneBlog.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MessageService messageSvc = new MessageService();
				messageVO = messageSvc.addMessage(blog_no,mem_no,mes_content,mes_cre);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMessage.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listOneBlog.jsp");
				failureView.forward(req,res);
			}
		}
        
		if("delete".equals(action)){ // 來自listAllMessage.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數***************************************/
				Integer mes_no = new Integer(req.getParameter("mes_no"));
				/***************************2.開始刪除資料***************************************/
				MessageService messageSvc = new MessageService();
				messageSvc.deleteMessage(mes_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/message/listAllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/listAllMessage.jsp");
				failureView.forward(req,res);
			}
		}
	}
}