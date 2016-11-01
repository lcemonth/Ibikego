package com.reportcollect.controller;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.activity.model.*;
import com.blog.model.*;
import com.member.model.*;
import com.reportcollect.model.*;
import com.stroke.model.StrokeService;
import com.stroke.model.StrokeVO;
import com.forum.model.*;
import com.joinactivity.model.JoinactivityService;
import com.joinactivity.model.JoinactivityVO;
import com.strokedetails.model.*;
import com.tool.MailService;
import com.travel.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1000 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)
public class ReportcollectServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action").trim();
		String linkUrl="";
		String fwUrl="";
		
		//------------------------------------後端導頁區--------------------------------------
		//back/left.jsp的連結 
		if("query".equals(action)){
			fwUrl="/back-end/reportcollect/rep_index.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
		}
		//回後端index
		if("backIndex".equals(action)){
			fwUrl="/back-end/reportcollect/rep_index.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
		}

		//連結回列表
		if("allList".equals(action)){
			linkUrl = req.getContextPath()+"/back-end/activity/listAllAct.jsp";
			res.sendRedirect(linkUrl);
		}
		//連結回首頁
		if("backHome".equals(action)){
			linkUrl = req.getContextPath()+"/back-end/activity/select_page.jsp";
			res.sendRedirect(linkUrl);
		}
		
		//連結到新增
		if("creAct".equals(action)){
			fwUrl = "/back-end/activity/addAct.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			
		}
		//------------------------------------前端--------------------------------------
		
				//--------------各式檢舉--------------
		
				if ("reportAct".equals(action)) { // 來自最新揪團檢舉
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer act_no = Integer.valueOf(req.getParameter("act_no"));
						String rep_cnt=req.getParameter("rep_cnt").trim();
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneRepActBymem_no(mem_no,act_no);
						if(rcVO==null){ //初次檢舉
							rcSvc.addRep(mem_no, null, act_no, null, null, null, 0, 2, 0,rep_cnt);	
							successMsgs.add("檢舉成功");
						}else{
							errorMsgs.add("已經檢舉過囉");
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/activity/act_f_listNew.jsp");
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					
						String url="";
						if ("reportAct".equals(action)){
							 url = "/front-end/activity/act_f_listNew.jsp";
						}

						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/act_f_listNew.jsp");
						failureView.forward(req, res);
					}
				}
				
				if ("reportForum".equals(action)) { // 來自討論區檢舉
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer forum_no = Integer.valueOf(req.getParameter("forum_no"));
						String rep_cnt=req.getParameter("rep_cnt").trim();
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneRepActBymem_no(mem_no,forum_no);
						if(rcVO==null){ //初次檢舉
							rcSvc.addRep(mem_no, null, null, forum_no, null, null, 0, 2, 0,rep_cnt);	
							successMsgs.add("檢舉成功");
						}else{
							errorMsgs.add("已經檢舉過囉");
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				
				if ("reportTravel".equals(action)) { // 來自旅遊點檢舉
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer tra_no = Integer.valueOf(req.getParameter("tra_no"));
						String rep_cnt=req.getParameter("rep_cnt").trim();
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneRepTravelBymem_no(mem_no,tra_no);
						if(rcVO==null){ //初次檢舉
							rcSvc.addRep(mem_no, tra_no, null, null, null, null, 0, 2, 0,rep_cnt);	
							successMsgs.add("檢舉成功");
						}else{
							errorMsgs.add("已經檢舉過囉");
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				
				if ("reportBlog".equals(action)) { // 來自日誌檢舉
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer blog_no = Integer.valueOf(req.getParameter("blog_no"));
						String rep_cnt=req.getParameter("rep_cnt").trim();
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneRepBlogBymem_no(mem_no,blog_no);
						if(rcVO==null){ //初次檢舉
							rcSvc.addRep(mem_no, null, null, null, blog_no, null, 0, 2, 0,rep_cnt);	
							successMsgs.add("檢舉成功");
						}else{
							errorMsgs.add("已經檢舉過囉");
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				
				if ("reportStroke".equals(action)) { // 來自行程檢舉
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer stroke_no = Integer.valueOf(req.getParameter("stroke_no"));
						String rep_cnt=req.getParameter("rep_cnt").trim();
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneRepStrokeBymem_no(mem_no,stroke_no);
						if(rcVO==null){ //初次檢舉
							rcSvc.addRep(mem_no, null, null, null, null, stroke_no, 0, 2, 0,rep_cnt);	
							successMsgs.add("檢舉成功");
						}else{
							errorMsgs.add("已經檢舉過囉");
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
			
				//--------------各式檢舉--------------
				
				//--------------各式收藏--------------
				
				if ("collectOneBlog".equals(action)) { // 來自日誌收藏
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer blog_no = Integer.valueOf(req.getParameter("blog_no"));
						
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneColBlogBymem_no(mem_no,blog_no);
						if(rcVO==null){ //初次收藏
							rcSvc.addRep(mem_no, null, null, null, blog_no, null,3, 0, 1,null);	
							successMsgs.add("收藏成功");
						}else{
							if(rcVO.getRc_col_status()==0){
								errorMsgs.add("已經收藏過囉");
							}else{
								rcSvc.updateRep(rcVO.getRc_no(), mem_no, null, null, null, blog_no, null, 3, 0, 1, null);
							}	
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				
				if ("cancelColOneBlog".equals(action)) { // 來自日誌取消收藏
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer blog_no = Integer.valueOf(req.getParameter("blog_no"));
						
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneColForumBymem_no(mem_no,blog_no);
					
						if(rcVO.getRc_col_status()==0){
							rcSvc.updateRep(rcVO.getRc_no(), mem_no, null, null, null, blog_no, null, 3, 1, 1, null);
						}

						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				
				if ("collectOneForum".equals(action)) { // 來自討論區收藏
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer forum_no = Integer.valueOf(req.getParameter("forum_no"));
						
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneColForumBymem_no(mem_no,forum_no);
						if(rcVO==null){ //初次收藏
							rcSvc.addRep(mem_no, null, null, forum_no, null, null, 3, 0, 1,null);	
							successMsgs.add("收藏成功");
						}else{
							if(rcVO.getRc_col_status()==0){
								errorMsgs.add("已經收藏過囉");
							}else{
								rcSvc.updateRep(rcVO.getRc_no(), mem_no, null, null, forum_no, null, null, 3, 0, 1, null);
							}	
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				
				if ("cancelColOneForum".equals(action)) { // 來自討論區取消收藏
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer forum_no = Integer.valueOf(req.getParameter("forum_no"));
						
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneColForumBymem_no(mem_no,forum_no);
					
						if(rcVO.getRc_col_status()==0){
							rcSvc.updateRep(rcVO.getRc_no(), mem_no, null, null, forum_no, null, null, 3, 1, 1, null);
						}

						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				  
				
				if ("collectOneTravel".equals(action)) { // 來自旅遊點收藏
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer tra_no = Integer.valueOf(req.getParameter("tra_no"));
						
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneColTravelBymem_no(mem_no,tra_no);
						if(rcVO==null){ //初次收藏
							rcSvc.addRep(mem_no, tra_no, null, null, null, null, 3, 0, 1,null);	
							successMsgs.add("收藏成功");
						}else{
							if(rcVO.getRc_col_status()==0){
								errorMsgs.add("已經收藏過囉");
							}else{
								rcSvc.updateRep(rcVO.getRc_no(), mem_no, tra_no, null, null, null, null, 3, 0, 1, null);
							}	
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				
				if ("cancelColOneTravel".equals(action)) { // 來自旅遊點取消收藏
					 
					List<String> errorMsgs = new LinkedList<String>();
					List<String> successMsgs = new LinkedList<String>();
					String requestURL = req.getParameter("requestURL");
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("successMsgs", successMsgs);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						Integer tra_no = Integer.valueOf(req.getParameter("tra_no"));
						
						/***************************2.開始查詢資料*****************************************/
						ReportcollectService rcSvc = new ReportcollectService();
						HttpSession session=req.getSession();
						Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
						ReportcollectVO rcVO = rcSvc.getOneColForumBymem_no(mem_no,tra_no);
					
						if(rcVO.getRc_col_status()==0){
							rcSvc.updateRep(rcVO.getRc_no(), mem_no, tra_no, null, null, null, null, 3, 1, 1, null);
						}

						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage()); 
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
				//--------------各式收藏--------------
		
		//------------------------------------後端--------------------------------------
				
		// 進入審核揪團詳細頁面
		if ("getOne_For_Check".equals(action)) { // 來自rep_index.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			String url="";
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				/***************************2.開始查詢資料*****************************************/
				
				ReportcollectService rcSvc = new ReportcollectService();
				ReportcollectVO rcVO = rcSvc.getOneRep(rc_no);
				if(rcVO.getAct_no()!=0){
					ActivityService actSvc = new ActivityService();
					ActivityVO actVO =actSvc.getOneAct(rcVO.getAct_no());
					req.setAttribute("actVO", actVO);
					url = "/back-end/reportcollect/checkOneAct.jsp";
				}
				if(rcVO.getBlog_no()!=0){
					BlogService blogSvc = new BlogService();
					BlogVO blogVO =blogSvc.getOneBlog(rcVO.getBlog_no());
					req.setAttribute("blogVO", blogVO);
					url = "/back-end/reportcollect/checkOneBlog.jsp";
				}
				if(rcVO.getTra_no()!=0){
					TravelService traSvc = new TravelService();
					TravelVO traVO =traSvc.getOneTravel(rcVO.getTra_no());
					req.setAttribute("traVO", traVO);
					url = "/back-end/reportcollect/checkOneTra.jsp";
				}
				if(rcVO.getForum_no()!=0){
					ForumService forumSvc = new ForumService();
					ForumVO forumVO =forumSvc.getOneForum(rcVO.getForum_no());
					req.setAttribute("forumVO", forumVO);
					url = "/back-end/reportcollect/checkOneForum.jsp";
				}
				if(rcVO.getStroke_no()!=0){
					StrokeService strokeSvc = new StrokeService();
					StrokeVO strokeVO =strokeSvc.getOneStroke(rcVO.getStroke_no());
					req.setAttribute("strokeVO", strokeVO);
					url = "/back-end/reportcollect/checkOneStroke.jsp";
				}
				req.setAttribute("rcVO", rcVO);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		//檢舉通過審核
		if ("passrep".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			String requestURL = req.getParameter("requestURL");
			String url="/back-end/reportcollect/rep_index.jsp";
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				/***************************2.開始查詢資料*****************************************/
				
				ReportcollectService rcSvc = new ReportcollectService();
				ReportcollectVO rcVO = rcSvc.getOneRep(rc_no);
				
				//審核旅遊點
				if(rcVO.getTra_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),rcVO.getTra_no(),null, null, null, null, 1, 2, 0, rcVO.getRep_content());
					}
				//審核揪團
				if(rcVO.getAct_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,rcVO.getAct_no(), null, null, null, 1, 2, 0, rcVO.getRep_content());
				}
				//審核討論區
				if(rcVO.getForum_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null, rcVO.getForum_no(), null, null, 1, 2, 0, rcVO.getRep_content());
					}
				//審核日誌
				if(rcVO.getBlog_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null,null, rcVO.getBlog_no(),null, 1, 2, 0, rcVO.getRep_content());
					}
				//審核行程
				if(rcVO.getStroke_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null,null,null,rcVO.getStroke_no(), 1, 2, 0, rcVO.getRep_content());
					}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);
		   }
		}
		
		//檢舉不通過審核
		if ("nopassrep".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			String url="/back-end/reportcollect/rep_index.jsp";
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				/***************************2.開始查詢資料*****************************************/
				
				ReportcollectService rcSvc = new ReportcollectService();
				ReportcollectVO rcVO = rcSvc.getOneRep(rc_no);
				
				//審核旅遊點
				if(rcVO.getTra_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),rcVO.getTra_no(),null, null, null, null, 2, 2, 0, rcVO.getRep_content());
					}
				//審核揪團
				if(rcVO.getAct_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,rcVO.getAct_no(), null, null, null, 2, 2, 0, rcVO.getRep_content());
				}
				//審核討論區
				if(rcVO.getForum_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null, rcVO.getForum_no(), null, null, 2, 2, 0, rcVO.getRep_content());
					}
				//審核日誌
				if(rcVO.getBlog_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null,null, rcVO.getBlog_no(),null, 2, 2, 0, rcVO.getRep_content());
					}
				//審核行程
				if(rcVO.getStroke_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null,null,null,rcVO.getStroke_no(), 2, 2, 0, rcVO.getRep_content());
					}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/reportcollect/rep_index.jsp");
				failureView.forward(req, res);
		   }
		}
		
		//下架檢舉的資料
		if ("getOne_For_Shelves".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				/***************************2.開始查詢資料*****************************************/
				
				ReportcollectService rcSvc = new ReportcollectService();
				ActivityService actSvc = new ActivityService();
				JoinactivityService jactSvc=new JoinactivityService();
				ForumService forumSvc=new ForumService();
				MemberService memSvc=new MemberService();
				BlogService blogSvc=new BlogService();
				TravelService traSvc=new TravelService();
				MailService mail=new MailService();
				ReportcollectVO rcVO = rcSvc.getOneRep(rc_no);
				
				//下架旅遊點
				if(rcVO.getTra_no()!=0){
					TravelVO tralVO=traSvc.getOneTravel(rcVO.getTra_no());
					MemberVO memVO=memSvc.getOneMember(tralVO.getMem_no());
					String tilte="您發佈的旅遊點:"+tralVO.getTra_name()+"已被檢舉!!!";
					String messageText=tilte+"經審核內容違反版規已刪除該旅遊點，敬請見諒。";
					mail.sendMail(memVO.getMem_email(),tilte,memVO.getMem_name()+messageText);
					traSvc.updateDel(rcVO.getTra_no());
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),rcVO.getTra_no(),null, null, null, null, 2, 2, 2, rcVO.getRep_content());
					req.setAttribute("test",1);
					}
				//下架揪團
				if(rcVO.getAct_no()!=0){
					List<JoinactivityVO> list=jactSvc.getJoinSureMemsByAct(rcVO.getAct_no());
					ActivityVO actVO=actSvc.getOneAct(rcVO.getAct_no());
					String tilte=actVO.getAct_start_date()+"至"+actVO.getAct_end_date()+"的"+actVO.getAct_name()+"已被檢舉，該團已下架!!!";
					String messageText="您好，於"+tilte+"造成行程規劃上不便，敬請見諒，請選擇其他揪團。";
					 for (int i = 0 ; i < list.size() ; i++){
						 JoinactivityVO jactVO = list.get(i);
					     System.out.println(jactVO.getMem_no()); 
					     MemberVO memVO=memSvc.getOneMember(jactVO.getMem_no());
					     mail.sendMail(memVO.getMem_email(),tilte,memVO.getMem_name()+messageText);
					 }
					 try {
						jactSvc.deleteJoinAct(rcVO.getAct_no());
						rcSvc.deleteRep(rc_no);
						actSvc.deleteAct(rcVO.getAct_no());
						rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,rcVO.getAct_no(), null, null, null, 2, 2, 0, rcVO.getRep_content());
						req.setAttribute("test",1);
					 }catch(Exception se){				 
						System.out.println("刪除失敗"+se.getMessage());
					 }
					 
				}
				//下架討論區
				if(rcVO.getForum_no()!=0){
					ForumVO forumVO=forumSvc.getOneForum(rcVO.getForum_no());
					MemberVO memVO=memSvc.getOneMember(forumVO.getMem_no());
					String tilte=forumVO.getForum_title()+"文章已被檢舉!!!";
					String messageText="您發表的"+tilte+"經審核內容違反版規已刪除該文章，敬請見諒。";
					mail.sendMail(memVO.getMem_email(),tilte,memVO.getMem_name()+messageText);
					forumSvc.delete_memForum(rcVO.getForum_no());
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null, rcVO.getForum_no(), null, null, 2, 2, 2, rcVO.getRep_content());
					req.setAttribute("test",1);
					}
				//下架日誌
				if(rcVO.getBlog_no()!=0){
					BlogVO BlogVO=blogSvc.getOneBlog(rcVO.getBlog_no());
					MemberVO memVO=memSvc.getOneMember(BlogVO.getMem_no());
					String tilte=BlogVO.getBlog_title()+"日誌已被檢舉!!!";
					String messageText="您發表的"+tilte+"經審核內容違反版規已刪除該日誌，敬請見諒。";
					mail.sendMail(memVO.getMem_email(),tilte,memVO.getMem_name()+messageText);
					blogSvc.updateDel(rcVO.getBlog_no());
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null,null, rcVO.getBlog_no(),null, 2, 2, 2, rcVO.getRep_content());
					req.setAttribute("test",1);
					}
				//下架行程功能已取消
				if(rcVO.getStroke_no()!=0){
					
					}
	
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
		   }
		}
		
		//檢舉刪除
		if ("getOne_For_Del".equals(action)) { 
	
			List<String> errorMsgs = new LinkedList<String>();
			String url="/back-end/reportcollect/rep_index.jsp";
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		
				Integer rc_no = new Integer(req.getParameter("rc_no"));
				
				/***************************2.開始查詢資料*****************************************/
				
				ReportcollectService rcSvc = new ReportcollectService();
				ReportcollectVO rcVO = rcSvc.getOneRep(rc_no);
				
				//審核旅遊點
				if(rcVO.getTra_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),rcVO.getTra_no(),null, null, null, null, 2, 2, 2, rcVO.getRep_content());
					}
				//審核揪團
				if(rcVO.getAct_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,rcVO.getAct_no(), null, null, null, 2, 2, 2, rcVO.getRep_content());
				}
				//審核討論區
				if(rcVO.getForum_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null, rcVO.getForum_no(), null, null, 2, 2, 2, rcVO.getRep_content());
					}
				//審核日誌
				if(rcVO.getBlog_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null,null, rcVO.getBlog_no(),null, 2, 2, 2, rcVO.getRep_content());
					}
				//審核行程
				if(rcVO.getStroke_no()!=0){
					rcSvc.updateRep(rc_no, rcVO.getMem_no(),null,null,null,null,rcVO.getStroke_no(), 2, 2, 2, rcVO.getRep_content());
					}
	
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/reportcollect/rep_index.jsp");
				failureView.forward(req, res);
		   }
		}
		
		/*****世麒新增討論區收藏重複改變狀態*******/
		if ("cancelColOneForum_change".equals(action)) { // 來自討論區取消收藏
			 
			List<String> errorMsgs = new LinkedList<String>();
			List<String> successMsgs = new LinkedList<String>();
			String requestURL = req.getParameter("requestURL");
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("successMsgs", successMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer forum_no = Integer.valueOf(req.getParameter("forum_no"));
				
				/***************************2.開始查詢資料*****************************************/
				ReportcollectService rcSvc = new ReportcollectService();
				HttpSession session=req.getSession();
				Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
				ReportcollectVO rcVO = rcSvc.getOneColForumBymem_no(mem_no,forum_no);
			
				if(rcVO.getRc_col_status()==0){
					rcSvc.updateRep(rcVO.getRc_no(), mem_no, null, null, forum_no, null, null, 3, 0, 1,null);
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage()); 
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	} 
	
}
