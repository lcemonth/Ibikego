package com.forum.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;



//import org.apache.commons.codec.binary;

import com.forum.model.ForumService;
import com.forum.model.ForumVO;
import com.forumresponse.model.ForumresService;
import com.forumresponse.model.*;
import com.emp.model.EmpService;
import com.forum.model.*;
import com.member.model.*;
import com.reportcollect.model.*;
import com.reportcollect.model.ReportcollectVO;
import com.reportcollect.model.ReportcollectService;
import com.tool.jdbcUtil_CompositeQuery_forum;


	public class Forum extends HttpServlet {

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
			
			
		
			
//			if("search_back".equals(action)){
//				url = "/back-end/forum/Forum_select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
//				successView.forward(req, res);	
//			}
//			if("search_front".equals(action)){
//				url = "/front-end/forum/Forum_select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
//				successView.forward(req, res);	
//			}
			/******後端討論區首頁*******/
			if("query_back".equals(action)){ 
				url = "/back-end/forum/Forum_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
				successView.forward(req, res);	
			}
//			if("query_front".equals(action)){
//				url = "/front-end/forum/Forum_index.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
//				successView.forward(req, res);	
//			}
//			if("add_front".equals(action)){
//				MemberService memSvc = new MemberService();
//				Integer mem_no = new Integer(req.getParameter("mem_no"));
//				MemberVO memVO = memSvc.getOneMember(mem_no);
//				
//				if(mem_no != null)
//					url = "/front-end/forum/Forum_add.jsp";
//				else
//					url = "/front-end/Login/Login.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
//				successView.forward(req, res);	
//			}
			
			
			
			
			/************會員收藏查詢****************/
			if("forum_state".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
				/***************************1.接收請求參數****************************************/
						
					Integer mem_no = new Integer(req.getParameter("mem_no"));
				/***************************2.開始查詢資料*****************************************/
					ReportcollectService rcSvc = new ReportcollectService();
					List<ReportcollectVO> list = rcSvc.findBymem_no(mem_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("list", list);
					
					url = "/front-end/home/Forum_state.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
					successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要查詢的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/home/homeIndex.jsp");

					failureView.forward(req, res);

					
				}
			}
			
			/************文章回應查詢****************/
			if ("getOne_For_Select_front".equals(action)) {// 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer forum_no = new Integer(req.getParameter("forum_no"));
					

					/***************************2.開始查詢資料*****************************************/
					ForumService forumSvc = new ForumService();
					ForumVO forumVO = forumSvc.getOneForum(forum_no);
					
//					ForumresService forumresSvc=new ForumresService();
					
//					List<ForumresVO> forumreslist =forumresSvc.getOneForumres(forum_no);
//					ForumresVO forumreslist =forumres.getOneForumres(forum_no);

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumVO", forumVO);
//					System.out.println(forumreslist.size());// 資料庫取出的forumVO物件,存入req
//					req.setAttribute("forumreslist", forumreslist);          // 資料庫取出的forumresVO物件,存入req
					
					
					url = "front-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要查詢的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forum/Forum_index.jsp");

					failureView.forward(req, res);

					
				}
			}
			
			
//			
			
			/************前端新增檢舉****************/
			if("handle_front".equals(action)){	//文章新增功能
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", 	errorMsgs);

					try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						
						Integer mem_no = new Integer(req.getParameter("mem_no").trim());
						Integer forum_no = new Integer(req.getParameter("forum_no").trim());
						
						String forum_title = req.getParameter("forum_title").trim();
						String forum_content = req.getParameter("forum_content").trim();
						
						java.sql.Date forum_cretime = null;
						try {
							forum_cretime = java.sql.Date.valueOf(req.getParameter("forum_cretime").trim());
						} catch (IllegalArgumentException e) {
							forum_cretime=new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入日期!");
						}
						
						
						
																
						Integer forum_del = new Integer(req.getParameter("forum_del").trim());
						
						ForumVO forumVO = new ForumVO();
						
						forumVO.setMem_no(mem_no);
						forumVO.setForum_title(forum_title);
						forumVO.setForum_content(forum_content);
						forumVO.setForum_cretime(forum_cretime);
						forumVO.setForum_del(forum_del);
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("forumVO", forumVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("back-end/forum/Forum_add.jsp");
							failureView.forward(req, res);
							return;
						}
						
						/***************************2.開始新增資料***************************************/
						ForumService forumSvc = new ForumService();
						forumVO = forumSvc.addForum(mem_no, forum_title, forum_content, forum_cretime, forum_del);
						
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						url = "front-end/forum/Forum_index.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);				
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("front-end/forum/Forum_add.jsp");
						failureView.forward(req, res);
					}
			}
			/************前端新增文章****************/
			if("insert_front".equals(action)){	//文章新增功能
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", 	errorMsgs);

					try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						
						Integer mem_no = new Integer(req.getParameter("mem_no").trim());
						
						String forum_title = req.getParameter("forum_title").trim();
						String forum_content = req.getParameter("forum_content").trim();
						
						java.sql.Date forum_cretime = null;
						try {
							forum_cretime = java.sql.Date.valueOf(req.getParameter("forum_cretime").trim());
						} catch (IllegalArgumentException e) {
							forum_cretime=new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入日期!");
						}
																
						Integer forum_del = new Integer(req.getParameter("forum_del").trim());
						
						ForumVO forumVO = new ForumVO();
						
						forumVO.setMem_no(mem_no);
						forumVO.setForum_title(forum_title);
						forumVO.setForum_content(forum_content);
						forumVO.setForum_cretime(forum_cretime);
						forumVO.setForum_del(forum_del);
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("forumVO", forumVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("back-end/forum/Forum_add.jsp");
							failureView.forward(req, res);
							return;
						}
						
						/***************************2.開始新增資料***************************************/
						ForumService forumSvc = new ForumService();
						forumVO = forumSvc.addForum(mem_no, forum_title, forum_content, forum_cretime, forum_del);
						
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						url = "front-end/forum/Forum_index.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);				
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("front-end/forum/Forum_add.jsp");
						failureView.forward(req, res);
					}
			}
			
			/************後段頁面文章查詢****************/
			if ("getOne_For_Select_back".equals(action)) {// 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer forum_no = new Integer(req.getParameter("forum_no"));
					

					/***************************2.開始查詢資料*****************************************/
					ForumService forumSvc = new ForumService();
					ForumVO forumVO = forumSvc.getOneForum(forum_no);
					
					ForumresService forumresSvc=new ForumresService();
					
					List<ForumresVO> forumreslist =forumresSvc.getOneForumres(forum_no);
//					ForumresVO forumreslist =forumres.getOneForumres(forum_no);

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumVO", forumVO);
//					System.out.println(forumreslist.size());// 資料庫取出的forumVO物件,存入req
					req.setAttribute("forumreslist", forumreslist);          // 資料庫取出的forumresVO物件,存入req
					
					
					url = "/back-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要查詢的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/forum/Forum_index.jsp");

					failureView.forward(req, res);

					
				}
			}
			
			
			
			/******前端文章修改*******/
			if ("getOne_For_Update_front".equals(action)) {// 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer forum_no = new Integer(req.getParameter("forum_no"));
					

					/***************************2.開始查詢資料*****************************************/
					ForumService forumSvc = new ForumService();
					ForumVO forumVO = forumSvc.getOneForum(forum_no);
					

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumVO", forumVO);          // 資料庫取出的empVO物件,存入req
					url = "/front-end/forum/Forum_update_forum_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/forum/Forum_index.jsp");

					failureView.forward(req, res);

					
				}
			}
			/******後端文章修改*******/
			
			if ("getOne_For_Update_back".equals(action)) {// 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer forum_no = new Integer(req.getParameter("forum_no"));
					

					/***************************2.開始查詢資料*****************************************/
					ForumService forumSvc = new ForumService();
					ForumVO forumVO = forumSvc.getOneForum(forum_no);
					

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumVO", forumVO);          // 資料庫取出的empVO物件,存入req
					url = "/back-end/forum/Forum_update_forum_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/forum/Forum_index.jsp");

					failureView.forward(req, res);

					
				}
			}
			/******前端文章修改*******/
			if ("update_front".equals(action)) {  // 來自Forum_update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					System.out.println(forum_no);
					
					
					String forum_title = req.getParameter("forum_title").trim();
					System.out.println(forum_title);
					String forum_content = req.getParameter("forum_content").trim();
					
					java.sql.Date forum_cretime = null;
					try {
						forum_cretime = java.sql.Date.valueOf(req.getParameter("forum_cretime").trim());
					} catch (IllegalArgumentException e) {
						forum_cretime=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					ForumVO forumVO = new ForumVO();
					
					forumVO.setForum_no(forum_no);
					
					forumVO.setForum_title(forum_title);
					
					forumVO.setForum_content(forum_content);
					
					forumVO.setForum_cretime(forum_cretime);
				
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("forumVO", forumVO); // 含有輸入格式錯誤的forumVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/forum/Forum_update_forum_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					ForumService forumSvc = new ForumService();
					forumVO = forumSvc.updateForum(forum_title,forum_content,forum_no,forum_cretime);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumVO", forumVO); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "front-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/forum/Forum_update_forum_input.jsp");
					failureView.forward(req, res);
				}
			}
			/******後端文章修改*******/
			if ("update_back".equals(action)) {  // 來自Forum_update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					System.out.println(forum_no);
					
					
					String forum_title = req.getParameter("forum_title").trim();
					System.out.println(forum_title);
					String forum_content = req.getParameter("forum_content").trim();
					
					java.sql.Date forum_cretime = null;
					try {
						forum_cretime = java.sql.Date.valueOf(req.getParameter("forum_cretime").trim());
					} catch (IllegalArgumentException e) {
						forum_cretime=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					ForumVO forumVO = new ForumVO();
					
					forumVO.setForum_no(forum_no);
					
					forumVO.setForum_title(forum_title);
					
					forumVO.setForum_content(forum_content);
					
					forumVO.setForum_cretime(forum_cretime);
				
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("forumVO", forumVO); // 含有輸入格式錯誤的forumVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/forum/Forum_update_forum_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					ForumService forumSvc = new ForumService();
					forumVO = forumSvc.updateForum(forum_title,forum_content,forum_no,forum_cretime);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("forumVO", forumVO); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "back-end/forum/Forum_listOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/forum/Forum_update_forum_input.jsp");
					failureView.forward(req, res);
				}
			}
			/******前端文章刪除*******/
			if ("delete_front".equals(action)) { // 來自Forum_index.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer forum_no = new Integer(req.getParameter("forum_no"));
					
					/***************************2.開始刪除資料***************************************/
					ForumService forumSvc = new ForumService();
					forumSvc.delete_memForum(forum_no);
					forumSvc.mem_getAll();
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/							
					url = "front-end/forum/Forum_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forum/Forum_index.jsp");
					failureView.forward(req, res);
				}
			}
			
			/******前端文章刪除*******/
			if ("delete_back".equals(action)) { // 來自Forum_index.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer forum_no = new Integer(req.getParameter("forum_no"));
					
					/***************************2.開始刪除資料***************************************/
					ForumService forumSvc = new ForumService();
					forumSvc.delete_memForum(forum_no);
					forumSvc.mem_getAll();
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/							
					url = "back-end/forum/Forum_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/forum/Forum_index.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			
			/******後端複合查詢*******/
			if("listForums_ByCompositeQuery_Back".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.將輸入資料轉為Map**********************************/ 
					//採用Map<String,String[]> getParameterMap()的方法 
					//注意:an immutable java.util.Map 
					//Map<String, String[]> map = req.getParameterMap();
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					if (req.getParameter("whichPage") == null) {
						HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
						HashMap<String, String[]> map2 = new HashMap<String, String[]>();
						map2 = (HashMap<String, String[]>)map1.clone();
						session.setAttribute("map", map2);
						map = (HashMap<String, String[]>)req.getParameterMap();
					}
					
					/***************************2.開始複合查詢***************************************/
					
					ForumService forumSvc = new ForumService();
					List<ForumVO> list = forumSvc.getAll(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("listForums_ByCompositeQuery",list);
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/forum/listForums_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
					
				} catch(Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/forum/listForums_ByCompositeQuery.jsp");
					failureView.forward(req, res);
				}
			}
			/******前端複合查詢*******/
			if("listForums_ByCompositeQuery_Front".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.將輸入資料轉為Map**********************************/ 
					//採用Map<String,String[]> getParameterMap()的方法 
					//注意:an immutable java.util.Map 
					//Map<String, String[]> map = req.getParameterMap();
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					if (req.getParameter("whichPage") == null) {
						HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
						HashMap<String, String[]> map2 = new HashMap<String, String[]>();
						map2 = (HashMap<String, String[]>)map1.clone();
						session.setAttribute("map", map2);
						map = (HashMap<String, String[]>)req.getParameterMap();
					}
					
					/***************************2.開始複合查詢***************************************/
					
					ForumService forumSvc = new ForumService();
					List<ForumVO> list = forumSvc.getAll(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("listForums_ByCompositeQuery",list);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/forum/listForums_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
					
				} catch(Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/forum/listForums_ByCompositeQuery.jsp");
					failureView.forward(req, res);
				}
			}
			/******檢舉新增頁面*******/
			if("add_handle".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				List<String> warningMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("warningMsgs",warningMsgs);
				ReportcollectService rcSvc = new ReportcollectService();
				ReportcollectVO rcVO = new ReportcollectVO();
				try{
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					
					Integer rc_rep_handle = 0;
					Integer rc_col_status = 2;
					Integer rep_rel = 0;
					
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
//					rcVO = rcSvc.getOneForum_mem(forum_no,mem_no);
//					if(rcVO != null) warningMsgs.add("您已經評分過囉!");
					
					String rep_content = "";
					
					rcVO = new ReportcollectVO();
					rcVO.setMem_no(mem_no);
					rcVO.setForum_no(forum_no);
					rcVO.setRc_rep_handle(rc_rep_handle);
					rcVO.setRc_col_status(rc_col_status);
					rcVO.setRep_rel(rep_rel);
					rcVO.setRep_content(rep_content);
					if (!errorMsgs.isEmpty() || !warningMsgs.isEmpty()) {
						req.setAttribute("rcVO", rcVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("front-end/forum/Forum_content.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("rcVO", rcVO);
					url = "front-end/forum/Forumhandle_add.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forum/Forumhandle_add.jsp");
					failureView.forward(req, res);
				}
				
			}
			/******檢舉新增輸入*******/
 			if("insert_handle".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				
				try{
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					String rep_content = req.getParameter("rep_content").trim();
					Integer rc_rep_handle = 0;
					Integer rc_col_status = 2;
					Integer rep_rel = 0;
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					
					
					ReportcollectVO rcVO = new ReportcollectVO();
					
					rcVO.setMem_no(mem_no);
					rcVO.setForum_no(forum_no);
					rcVO.setRc_rep_handle(rc_rep_handle);
					rcVO.setRc_col_status(rc_col_status);
					rcVO.setRep_rel(rep_rel);
					rcVO.setRep_content(rep_content);
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("rcVO", rcVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("front-end/forum/Forumhandle_add.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始新增資料***************************************/
					ForumService rcSvc = new ForumService();
					rcVO = rcSvc.add_handle(mem_no, forum_no, rc_rep_handle, rc_col_status, rep_rel,rep_content);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					url = "front-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forum/Forumhandle_add.jsp");
					failureView.forward(req, res);
				}
				
			}
			
			/************收藏狀態改變****************/
			if("update_status".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				
				try{
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					Integer rc_rep_handle = 3;
					Integer rc_col_status = 1;
					Integer rep_rel = 1;
					Integer rc_no = new Integer(req.getParameter("rc_no").trim());
					
					ReportcollectVO rcVO = new ReportcollectVO();

					rcVO.setRc_no(rc_no);
					rcVO.setMem_no(mem_no);
					rcVO.setForum_no(forum_no);
					rcVO.setRc_rep_handle(rc_rep_handle);
					rcVO.setRc_col_status(rc_col_status);
					rcVO.setRep_rel(rep_rel);
					rcVO.setRc_no(rc_no);
					if(!errorMsgs.isEmpty()){
						req.setAttribute("rcVO",rcVO);
						RequestDispatcher failureView = req.getRequestDispatcher("front-end/forum/Forum_content.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始修改資料*****************************************/
					ReportcollectService rcSvc = new ReportcollectService();
					rcVO = rcSvc.update_state(mem_no,forum_no,rc_rep_handle,rc_col_status,rep_rel,rc_no);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("rcVO", rcVO); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "front-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
						/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/forum/Forum_content.jsp");
					failureView.forward(req, res);
				}
			}
			/***收藏狀態改變*****/
			if("update_status_A".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				
				try{
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					Integer rc_rep_handle = 3;
					Integer rc_col_status = 0;
					Integer rep_rel = 1;
					Integer rc_no = new Integer(req.getParameter("rc_no").trim());
					
					ReportcollectVO rcVO = new ReportcollectVO();

					rcVO.setRc_no(rc_no);
					rcVO.setMem_no(mem_no);
					rcVO.setForum_no(forum_no);
					rcVO.setRc_rep_handle(rc_rep_handle);
					rcVO.setRc_col_status(rc_col_status);
					rcVO.setRep_rel(rep_rel);
					rcVO.setRc_no(rc_no);
					if(!errorMsgs.isEmpty()){
						req.setAttribute("rcVO",rcVO);
						RequestDispatcher failureView = req.getRequestDispatcher("front-end/forum/Forum_content.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始修改資料*****************************************/
					ReportcollectService rcSvc = new ReportcollectService();
					rcVO = rcSvc.update_state_A(mem_no,forum_no,rc_rep_handle,rc_col_status,rep_rel,rc_no);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("rcVO", rcVO); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "front-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
						/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/forum/Forum_content.jsp");
					failureView.forward(req, res);
				}
			}
			/***會員收藏取消******/
			if("update_status_B".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				
				try{
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					Integer rc_rep_handle = 3;
					Integer rc_col_status = 1;
					Integer rep_rel = 1;
					Integer rc_no = new Integer(req.getParameter("rc_no").trim());
					
					ReportcollectVO rcVO = new ReportcollectVO();

					rcVO.setRc_no(rc_no);
					rcVO.setMem_no(mem_no);
					rcVO.setForum_no(forum_no);
					rcVO.setRc_rep_handle(rc_rep_handle);
					rcVO.setRc_col_status(rc_col_status);
					rcVO.setRep_rel(rep_rel);
					rcVO.setRc_no(rc_no);
					if(!errorMsgs.isEmpty()){
						req.setAttribute("rcVO",rcVO);
						RequestDispatcher failureView = req.getRequestDispatcher("front-end/forum/Forum_state.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始修改資料*****************************************/
					ReportcollectService rcSvc = new ReportcollectService();
					rcVO = rcSvc.update_state(mem_no,forum_no,rc_rep_handle,rc_col_status,rep_rel,rc_no);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("rcVO", rcVO); // 資料庫update成功後,正確的的empVO物件,存入req
					url = "front-end/forum/Forum_state.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
						/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/forum/Forum_state.jsp");
					failureView.forward(req, res);
				}
			}
//			/************收藏狀態改變****************/
			/************新增收藏****************/
			if("insert_status".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				
				try{
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					
					Integer forum_no = new Integer(req.getParameter("forum_no").trim());
					
					Integer rc_rep_handle = 3;
					Integer rc_col_status = 0;
					Integer rep_rel = 1;
					
					ReportcollectVO reportcollectVO = new ReportcollectVO();
					
					reportcollectVO.setMem_no(mem_no);
					reportcollectVO.setForum_no(forum_no);
					reportcollectVO.setRc_rep_handle(rc_rep_handle);
					reportcollectVO.setRc_col_status(rc_col_status);
					reportcollectVO.setRep_rel(rep_rel);
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("reportcollectVO", reportcollectVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("front-end/forum/Forum_content.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始新增資料***************************************/
					ReportcollectService rcSvc = new ReportcollectService();
					reportcollectVO = rcSvc.addForum(mem_no, forum_no, rc_rep_handle, rc_col_status, rep_rel);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					url = "front-end/forum/Forum_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forum/Forum_content.jsp");
					failureView.forward(req, res);
				}
				
			}
			/************8/31會員文章管理查詢****************/
			if("forum_mem".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
				/***************************1.接收請求參數****************************************/
						
					Integer mem_no = new Integer(req.getParameter("mem_no"));
				/***************************2.開始查詢資料*****************************************/
					ForumService forumSvc = new ForumService();
					List<ForumVO> list = forumSvc.getOneMem(mem_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("list", list);
					
					url = "/front-end/home/Forum_mem.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 重新導引頁面
					successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要查詢的資料:" + e.getMessage());
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/home/homeIndex.jsp");

					failureView.forward(req, res);

					
				}
			}
			
			/******8/31前端會員刪除*******/
			if ("delete_mem".equals(action)) { // 來自Forum_index.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer forum_no = new Integer(req.getParameter("forum_no"));
					
					/***************************2.開始刪除資料***************************************/
					ForumService forumSvc = new ForumService();
					forumSvc.delete_memForum(forum_no);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/							
					url = "front-end/forum/Forum_mem.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/forum/Forum_mem.jsp");
					failureView.forward(req, res);
				}
			}
			
			/**9/4新增**/
			if("select_title".equals(action)){
				try {
					String forum_title= req.getParameter("forum_title").trim();
					if( req.getMethod().equals("GET") ){
						forum_title= new String(forum_title.getBytes("ISO-8859-1"),"UTF-8");
					}
					req.setAttribute("forum_title", forum_title);
					req.setAttribute("list", new ForumService().getForum_title(forum_title));
					url="back-end/forum/Forum_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
				} catch (Exception e) {
//					req.setAttribute("emp_name", "");
					req.setAttribute("list", new ForumService().getForum_title(""));
					url="back-end/forum/Forum_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
				}
			}
			if("select_title_front".equals(action)){
				try {
					String forum_title= req.getParameter("forum_title").trim();
					if( req.getMethod().equals("GET") ){
						forum_title= new String(forum_title.getBytes("ISO-8859-1"),"UTF-8");
					}
					req.setAttribute("forum_title", forum_title);
					req.setAttribute("list", new ForumService().getForum_title(forum_title));
					url="front-end/forum/Forum_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
				} catch (Exception e) {
//					req.setAttribute("emp_name", "");
					req.setAttribute("list", new ForumService().getForum_title(""));
					url="front-end/forum/Forum_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
				}
			}
			/**9/4新增**/
		}
	}

