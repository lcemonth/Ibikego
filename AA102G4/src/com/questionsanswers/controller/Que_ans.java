package com.questionsanswers.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.questionsanswers.model.Que_ansService;
import com.questionsanswers.model.Que_ansVO;

import com.questionsanswers.model.*;


//import org.apache.commons.codec.binary;






	public class Que_ans extends HttpServlet {

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
				url = "front-end/forumres/Que_ans_select_page.jsp";
				 
				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
				successView.forward(req, res);	
			}
			
			if("query_back".equals(action)){
				url = "/back-end/questionsanswers/Que_ans_index.jsp";
				 
				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
				successView.forward(req, res);	
			}
			if("add_back".equals(action)){
				url = "/back-end/questionsanswers/Que_ans_add.jsp";
				 
				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
				successView.forward(req, res);	
			}
			
			if("insert_back".equals(action)){	//文章新增功能
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", 	errorMsgs);

					try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
																				
						String que_ans_q = req.getParameter("que_ans_q").trim();
						
						String que_ans_a = req.getParameter("que_ans_a").trim();
						
																					
						Que_ansVO que_ansVO = new Que_ansVO();
						
						
						que_ansVO.setQue_ans_q(que_ans_q);
						que_ansVO.setQue_ans_a(que_ans_a);
		
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("que_ansVO", que_ansVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("back-end/questionsanswers/Que_ans_add.jsp");
							failureView.forward(req, res);
							return;
						}
						
						/***************************2.開始新增資料***************************************/
						Que_ansService que_ansSvc = new Que_ansService();
						que_ansVO = que_ansSvc.addForumres(que_ans_q, que_ans_a);
						
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						url = "back-end/questionsanswers/Que_ans_index.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);				
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("back-end/questionsanswers/Que_ans_add.jsp");
						failureView.forward(req, res);
					}
			}
			
			if ("getOne_For_Select_back".equals(action)) {// 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer que_ans_no = new Integer(req.getParameter("que_ans_no"));
					

					/***************************2.開始查詢資料*****************************************/
					Que_ansService que_ansSvc = new Que_ansService();
					Que_ansVO que_ansVO = que_ansSvc.getOneQue_ans(que_ans_no);
					
					//					ForumresVO forumreslist =forumres.getOneForumres(forum_no);

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("que_ansVO", que_ansVO);
//					System.out.println(forumreslist.size());// 資料庫取出的forumVO物件,存入req
								
					
					url = "/back-end/questionsanswers/Que_ans_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要查詢的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/questionsanswers/Que_ans_index.jsp");

					failureView.forward(req, res);

					
				}
			}
			if ("getOne_For_Update_back".equals(action)) {// 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer que_ans_no = new Integer(req.getParameter("que_ans_no"));
					

					/***************************2.開始查詢資料*****************************************/
					Que_ansService que_ansSvc = new Que_ansService();
					Que_ansVO que_ansVO = que_ansSvc.getOneQue_ans(que_ans_no);
					

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("que_ansVO", que_ansVO);          // 資料庫取出的empVO物件,存入req
					url = "back-end/questionsanswers/Que_ans_update_que_ans_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/questionsanswers/Que_ans_index.jsp");

					failureView.forward(req, res);

					
				}
			}
			if ("getOne_For_Update_back".equals(action)) {// 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer que_ans_no = new Integer(req.getParameter("que_ans_no"));
					

					/***************************2.開始查詢資料*****************************************/
					Que_ansService que_ansSvc = new Que_ansService();
					Que_ansVO que_ansVO = que_ansSvc.getOneQue_ans(que_ans_no);
					

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("que_ansVO", que_ansVO);          // 資料庫取出的empVO物件,存入req
					url = "back-end/questionsanswers/Que_ans_update_que_ans_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/questionsanswers/Que_ans_index.jsp");

					failureView.forward(req, res);

					
				}
			}
			
						
			
			if ("update_back".equals(action)) {  // 來自Forum_update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					
					Integer que_ans_no = new Integer(req.getParameter("que_ans_no").trim());
					
									
					String que_ans_q = req.getParameter("que_ans_q").trim();
					
					String que_ans_a = req.getParameter("que_ans_a").trim();
								
										
					Que_ansVO que_ansVO = new Que_ansVO();
					
					que_ansVO.setQue_ans_no(que_ans_no);
					
					que_ansVO.setQue_ans_q(que_ans_q);
					
					que_ansVO.setQue_ans_a(que_ans_a);
					
				
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("que_ansVO", que_ansVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("back-end/questionsanswers/Que_ans_update_que_ans_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					Que_ansService que_ansSvc = new Que_ansService();
					que_ansVO = que_ansSvc.updateForumres(que_ans_q,que_ans_a,que_ans_no);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("que_ansVO", que_ansVO); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "back-end/questionsanswers/Que_ans_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/questionsanswers/Que_ans_update_que_ans_input.jsp");
					failureView.forward(req, res);
				}
			}
			
			
	
			if ("delete_back".equals(action)) { // 來自Forum_listAll.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer que_ans_no = new Integer(req.getParameter("que_ans_no"));
					
					/***************************2.開始刪除資料***************************************/
					Que_ansService que_ansSvc = new Que_ansService();
					que_ansSvc.deleteForumres(que_ans_no);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/							
					url = "back-end/questionsanswers/Que_ans_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/questionsanswers/Que_ans_index.jsp");
					failureView.forward(req, res);
				}
			}
								
			
//			if ("select".equals(action)) { /// 來自select_page.jsp的請求
//
//				List<String> errorMsgs = new LinkedList<String>();
//				// Store this set in the request scope, in case we need to
//				// send the ErrorPage view.
//				req.setAttribute("errorMsgs", errorMsgs);
//
//				try {
//					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//					
//					String str = req.getParameter("forum_no");
//					if (str == null || (str.trim()).length() == 0) {
//						errorMsgs.add("請輸入文章編號");
//					}
//					
//					
//					// Send the use back to the form, if there were errors
//					if (!errorMsgs.isEmpty()) {
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("front-end/forum/Forum_select_page.jsp");
//						failureView.forward(req, res);
//						return;//程式中斷
//					}
//					
//
//					
//					Integer que_ans_no = null;
//					try {
//						que_ans_no = new Integer(str);
//					} catch (Exception e) {
//						errorMsgs.add("文章編號格式不正確");
//					}
//					// Send the use back to the form, if there were errors
//					if (!errorMsgs.isEmpty()) {
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("front-end/forum/Forum_select_page.jsp");
//						failureView.forward(req, res);
//						return;//程式中斷
//					}
//					
//					
//
//					
//					/***************************2.開始查詢資料*****************************************/
//					Que_ansService que_ansSvc = new Que_ansService();
////					ForumresVO forumresVO = forumresSvc.getOneForumres(forum_no);
//					Que_ansVO que_aneVO = que_ansSvc.getOneQue_ans(que_ans_no);
//					if (que_aneVO == null) {
//						errorMsgs.add("查無資料");
//					}
//					// Send the use back to the form, if there were errors
//					if (!errorMsgs.isEmpty()) {
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("front-end/forum/Forum_select_page.jsp");
//						failureView.forward(req, res);
//						return;//程式中斷
//					}
//					
//
//					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//					req.setAttribute("que_aneVO", que_aneVO); // 資料庫取出的empVO物件,存入req
//					url = "front-end/forum/Forumres_listOne.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//					successView.forward(req, res);
//					
//
//					/***************************其他可能的錯誤處理*************************************/
//				} catch (Exception e) {
//					errorMsgs.add("無法取得資料:" + e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("front-end/forumres/Forumres_select_page.jsp");
//					failureView.forward(req, res);
//				}
//			}
			
						
			
			
	       
			
			
		}
	}

