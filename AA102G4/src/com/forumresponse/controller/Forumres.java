package com.forumresponse.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.forum.model.ForumService;
import com.forum.model.ForumVO;




//import org.apache.commons.codec.binary;

import com.forumresponse.model.ForumresService;
import com.forumresponse.model.ForumresVO;
import com.forumresponse.model.*;




	public class Forumres extends HttpServlet {

		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			
			//test 1
			//導頁
			String url="";
			String action= req.getParameter("action").trim();
			
			
			
			if("search".equals(action)){
				url = "front-end/forumres/Forumres_select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
				successView.forward(req, res);	

			}
			if("query".equals(action)){
				url = "/front-end/forumres/Forumres_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
				successView.forward(req, res);	
			}
			
			/******會員新增回應頁面*******/
			if("add_front".equals(action)){
						
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer forum_no = new Integer(req.getParameter("forum_no"));
					

					/***************************2.開始查詢資料*****************************************/
					ForumService forumSvc = new ForumService();
//					ForumresVO forumresVO = forumresSvc.getOneForumres(forumres_no);
					ForumVO forumVO = forumSvc.getOneForum(forum_no);

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumVO", forumVO);          // 資料庫取出的empVO物件,存入req
								
					url = "/front-end/forumres/Forumres_add.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/forumres/Forumres_index.jsp");

					failureView.forward(req, res);

					
				}
			}
				
			
			/******會員回應*******/
			if("insert_front".equals(action)){	//文章新增功能
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", 	errorMsgs);

					try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						
						Integer mem_no = new Integer(req.getParameter("mem_no").trim());
						
						Integer forum_no = new Integer(req.getParameter("forum_no").trim());
						
						String forumres_con = req.getParameter("forumres_con").trim();
						
						Integer forumres_del = new Integer(req.getParameter("forumres_del").trim());
						
						java.sql.Date forumres_cretime = null;
						try {
							forumres_cretime = java.sql.Date.valueOf(req.getParameter("forumres_cretime").trim());
						} catch (IllegalArgumentException e) {
							forumres_cretime=new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入日期!");
						}
															
						ForumresVO forumresVO = new ForumresVO();
						
						forumresVO.setMem_no(mem_no);
						forumresVO.setForum_no(forum_no);
						forumresVO.setForumres_con(forumres_con);
						forumresVO.setForumres_cretime(forumres_cretime);
						forumresVO.setForumres_del(forumres_del);
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("forumresVO", forumresVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("front-end/forumres/Forumres_add.jsp");
							failureView.forward(req, res);
							return;
						}
						
						/***************************2.開始新增資料***************************************/
						ForumresService forumresSvc = new ForumresService();
						forumresVO = forumresSvc.addForumres(mem_no,forum_no, forumres_con, forumres_cretime,forumres_del);
						List<ForumresVO> forumreslist = forumresSvc.getOneForumres(forum_no);
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						req.setAttribute("forumreslist", forumreslist);          // 資料庫取出的empVO物件,存入req
						url = "front-end/forum/Forum_content.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);				
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("front-end/forumres/Forumres_add.jsp");
						failureView.forward(req, res);
					}
			}
			/******會員回應修改*******/
			if ("getOne_For_Update_front".equals(action)) {// 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer forumres_no = new Integer(req.getParameter("forumres_no"));
					
					/***************************2.開始查詢資料*****************************************/
					ForumresService forumresSvc = new ForumresService();
//					ForumresVO forumresVO = forumresSvc.getOneForumres(forumres_no);
					ForumresVO forumresVO = forumresSvc.getOneForumresno(forumres_no);

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumresVO", forumresVO);          // 資料庫取出的empVO物件,存入req
					url = "front-end/forumres/Forumres_update_forumres_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forum/Forum_conternt.jsp");

					failureView.forward(req, res);

					
				}
			}
			
			/******會員回應修改*******/
			if ("update_front".equals(action)) {  // 來自Forum_update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					
					String forumres_con = req.getParameter("forumres_con").trim();
					
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					
					Integer forumres_no = new Integer(req.getParameter("forumres_no").trim());
					
					Integer forumres_del = new Integer(req.getParameter("forumres_del").trim());
					
					java.sql.Date forumres_cretime = null;
					try {
						forumres_cretime = java.sql.Date.valueOf(req.getParameter("forumres_cretime").trim());
					} catch (IllegalArgumentException e) {
						forumres_cretime=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					ForumresVO forumresVO = new ForumresVO();
					
					
					forumresVO.setForum_no(forum_no);
					forumresVO.setMem_no(mem_no);
					forumresVO.setForumres_con(forumres_con);
					forumresVO.setForumres_cretime(forumres_cretime);
					forumresVO.setForumres_del(forumres_del);
					forumresVO.setForumres_no(forumres_no);
					
				
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("forumresVO", forumresVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("front-end/forumres/Forumres_update_forum_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					ForumresService forumresSvc = new ForumresService();
					forumresVO = forumresSvc.updateForumres(forum_no,mem_no,forumres_con,forumres_cretime,forumres_del,forumres_no);
					List<ForumresVO> forumreslist = forumresSvc.getOneForumres(forum_no);
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumreslist", forumreslist); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "front-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forumres/Forumres_update_forum_input.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			/******會員刪除回應*******/
			if ("delete_back".equals(action)) { // 來自Forum_listAll.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer forumres_no = new Integer(req.getParameter("forumres_no").trim());
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					/***************************2.開始刪除資料***************************************/
					ForumresService forumresSvc = new ForumresService();
					
					forumresSvc.deleteForumres(forumres_no);
					
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/
					req.setAttribute("forum_no", forum_no); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "back-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/forum/Forum_content.jsp");
					failureView.forward(req, res);
				}
			}
			
			/******會員刪除回應*******/
			if ("delete_front".equals(action)) { // 來自Forum_listAll.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer forumres_no = new Integer(req.getParameter("forumres_no").trim());
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					/***************************2.開始刪除資料***************************************/
					ForumresService forumresSvc = new ForumresService();
					
					forumresSvc.deleteForumres(forumres_no);
					forumresSvc.getOneForumresno(forumres_no);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/
					req.setAttribute("forum_no", forum_no); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "front-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forum/Forum_content.jsp");
					failureView.forward(req, res);
				}
			}
			
			
		}
	}

