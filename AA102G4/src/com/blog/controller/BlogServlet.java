package com.blog.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.blog.model.*;
import com.reportcollect.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BlogServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		/**************************************導頁*****************************************/
		if("listAll".equals(action)){
			String url = "/back-end/blog/listAllBlog.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllBlog.jsp
			successView.forward(req,res);
		}
		if("home".equals(action)){
			String url = "/back-end/blog/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 select_page.jsp
			successView.forward(req,res);
		}
		if("add".equals(action)){
			String url = "/back-end/blog/addBlog.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addBlog.jsp
			successView.forward(req,res);
		}
		if("selectOne".equals(action)){
			String url = "/back-end/blog/selectOne.jsp";
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
				String str = req.getParameter("blog_no");
				if(str==null||(str.trim()).length()==0){
					errorMsgs.add("請輸入日誌編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				Integer blog_no = null;
				try{
					blog_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("日誌編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blog_no);
				if(blogVO==null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogVO",blogVO); // 資料庫取出的blogVO物件,存入req
				String url ="/back-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBlog.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/selectOne.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("getOne_For_UserDisplay".equals(action)){ // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("blog_no");
				if(str==null||(str.trim()).length()==0){
					errorMsgs.add("請輸入日誌編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				Integer blog_no = null;
				try{
					blog_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("日誌編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blog_no);
				if(blogVO==null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("blogVO",blogVO); // 資料庫取出的blogVO物件,存入req
				String url ="/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBlog.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listAllBlog.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("getOne_For_Update".equals(action)){ // 來自listAllBlog.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				Integer blog_no = new Integer(req.getParameter("blog_no"));
				/***************************2.開始查詢資料****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blog_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("blogVO",blogVO);         // 資料庫取出的blogVO物件,存入req
				String url ="/back-end/blog/update_blog_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_blog_input.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/listAllBlog.jsp");
				failureView.forward(req,res);
			}
		}
		
		/******************************************************************/
		if("getOne_For_UserUpdate".equals(action)){ // 來自listMyBlog.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				Integer blog_no = new Integer(req.getParameter("blog_no"));
				/***************************2.開始查詢資料****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blog_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("blogVO",blogVO);         // 資料庫取出的blogVO物件,存入req
				String url ="/front-end/home/userUpdateBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 userUpdateBlog.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listMyBlog.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("userUpdate".equals(action)){ // 來自userUpdateBlog.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String blog_title = req.getParameter("blog_title").trim();
				if(blog_title==null || (blog_title.trim()).length()==0) errorMsgs.add("請輸入標題!");
				String blog_content = req.getParameter("blog_content").trim();
				
				java.sql.Date blog_cre = null;
				try {
					blog_cre = java.sql.Date.valueOf(req.getParameter("blog_cre").trim());
				} catch (IllegalArgumentException e) {
					blog_cre = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Part part = req.getPart("blog_photo");
				InputStream in = part.getInputStream();
				byte[] blog_photo = new byte[in.available()];
				if(getFileNameFromPart(part) != null){
					blog_photo = new byte[in.available()];
					in.read(blog_photo);
				}else{
					BlogService blogSvc = new BlogService();
					BlogVO blogVO = blogSvc.getOneBlog(blog_no);
					blog_photo = blogVO.getBlog_photo();
					if(blog_photo !=null)
						in.read(blog_photo);
				}
				in.close();
				
				Integer blog_del = null;
				try{
					blog_del = new Integer(req.getParameter("blog_del").trim());
				}catch(NumberFormatException e){
					blog_del = 0;
				}

				BlogVO blogVO = new BlogVO();
				blogVO.setBlog_no(blog_no);
				blogVO.setMem_no(mem_no);
				blogVO.setBlog_title(blog_title);
				blogVO.setBlog_content(blog_content);
				blogVO.setBlog_cre(blog_cre);
				blogVO.setBlog_photo(blog_photo);
				blogVO.setBlog_del(blog_del);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的blogVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/userUpdateBlog.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BlogService blogSvc = new BlogService();
				blogVO = blogSvc.updateBlog(blog_no,mem_no,blog_title,blog_content,blog_cre,blog_photo,blog_del);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogVO",blogVO); // 資料庫update成功後,正確的的blogVO物件,存入req
				String url = "/front-end/home/listMyBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMyBlog.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/userUpdateBlog.jsp");
				failureView.forward(req,res);
			}
		}
		/******************************************************************/
		
		if("update".equals(action)){ // 來自update_blog_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String blog_title = req.getParameter("blog_title").trim();
				if(blog_title==null || (blog_title.trim()).length()==0) errorMsgs.add("請輸入標題!");
				String blog_content = req.getParameter("blog_content").trim();
				
				java.sql.Date blog_cre = null;
				try {
					blog_cre = java.sql.Date.valueOf(req.getParameter("blog_cre").trim());
				} catch (IllegalArgumentException e) {
					blog_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Part part = req.getPart("blog_photo");
				InputStream in = part.getInputStream();
				byte[] blog_photo = new byte[in.available()];
				if(getFileNameFromPart(part) != null){
					blog_photo = new byte[in.available()];
					in.read(blog_photo);
				}else{
					BlogService blogSvc = new BlogService();
					BlogVO blogVO = blogSvc.getOneBlog(blog_no);
					blog_photo = blogVO.getBlog_photo();
					if(blog_photo !=null)
						in.read(blog_photo);
				}
				in.close();
				
				Integer blog_del = null;
				try{
					blog_del = new Integer(req.getParameter("blog_del").trim());
				}catch(NumberFormatException e){
					blog_del = 0;
				}

				BlogVO blogVO = new BlogVO();
				blogVO.setBlog_no(blog_no);
				blogVO.setMem_no(mem_no);
				blogVO.setBlog_title(blog_title);
				blogVO.setBlog_content(blog_content);
				blogVO.setBlog_cre(blog_cre);
				blogVO.setBlog_photo(blog_photo);
				blogVO.setBlog_del(blog_del);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的blogVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/update_blog_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BlogService blogSvc = new BlogService();
				blogVO = blogSvc.updateBlog(blog_no,mem_no,blog_title,blog_content,blog_cre,blog_photo,blog_del);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogVO",blogVO); // 資料庫update成功後,正確的的blogVO物件,存入req
				String url = "/back-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneBlog.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/update_blog_input.jsp");
				failureView.forward(req,res);
			}
		}
		/***/
		 if("userInsert".equals(action)){ // 來自addBlog.jsp的請求  
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs",errorMsgs);
				try{
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					String blog_title = req.getParameter("blog_title").trim();
					if(blog_title==null || (blog_title.trim()).length()==0) errorMsgs.add("請輸入標題!");
					String blog_content = req.getParameter("blog_content").trim();
					
					java.sql.Date blog_cre = null;
					try {
						blog_cre = java.sql.Date.valueOf(req.getParameter("blog_cre").trim());
					} catch (IllegalArgumentException e) {
						blog_cre=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					Part part = req.getPart("blog_photo");
					InputStream in = part.getInputStream();
					byte[] blog_photo = new byte[in.available()];
					in.read(blog_photo);
					in.close();
					
					Integer blog_del = null;
					try{
						blog_del = new Integer(req.getParameter("blog_del").trim());
					}catch(NumberFormatException e){
						blog_del = 0;
					}

					BlogVO blogVO = new BlogVO();
					blogVO.setMem_no(mem_no);
					blogVO.setBlog_title(blog_title);
					blogVO.setBlog_content(blog_content);
					blogVO.setBlog_cre(blog_cre);
					blogVO.setBlog_photo(blog_photo);
					blogVO.setBlog_del(blog_del);
					// Send the use back to the form, if there were errors
					if(!errorMsgs.isEmpty()){
						req.setAttribute("blogVO",blogVO); // 含有輸入格式錯誤的blogVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/addBlog.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					BlogService blogSvc = new BlogService();
					blogVO = blogSvc.addBlog(mem_no,blog_title,blog_content,blog_cre,blog_photo,blog_del);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/front-end/blog/listAllBlog.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBlog.jsp
					successView.forward(req,res);				
					
					/***************************其他可能的錯誤處理**********************************/
				}catch(Exception e){
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/addBlog.jsp");
					failureView.forward(req,res);
				}
			}
		 /***/
        if("insert".equals(action)){ // 來自addBlog.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String blog_title = req.getParameter("blog_title").trim();
				if(blog_title==null || (blog_title.trim()).length()==0) errorMsgs.add("請輸入標題!");
				String blog_content = req.getParameter("blog_content").trim();
				
				java.sql.Date blog_cre = null;
				try {
					blog_cre = java.sql.Date.valueOf(req.getParameter("blog_cre").trim());
				} catch (IllegalArgumentException e) {
					blog_cre=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Part part = req.getPart("blog_photo");
				InputStream in = part.getInputStream();
				byte[] blog_photo = new byte[in.available()];
				in.read(blog_photo);
				in.close();
				
				Integer blog_del = null;
				try{
					blog_del = new Integer(req.getParameter("blog_del").trim());
				}catch(NumberFormatException e){
					blog_del = 0;
				}

				BlogVO blogVO = new BlogVO();
				blogVO.setMem_no(mem_no);
				blogVO.setBlog_title(blog_title);
				blogVO.setBlog_content(blog_content);
				blogVO.setBlog_cre(blog_cre);
				blogVO.setBlog_photo(blog_photo);
				blogVO.setBlog_del(blog_del);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("blogVO",blogVO); // 含有輸入格式錯誤的blogVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/addBlog.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BlogService blogSvc = new BlogService();
				blogVO = blogSvc.addBlog(mem_no,blog_title,blog_content,blog_cre,blog_photo,blog_del);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBlog.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/addBlog.jsp");
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
				Integer blog_no = new Integer(req.getParameter("blog_no"));
				/***************************2.開始刪除資料***************************************/
				BlogService blogSvc = new BlogService();
				blogSvc.deleteBlog(blog_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/blog/listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/blog/listAllBlog.jsp");
				failureView.forward(req,res);
			}
		}
		/***/
		/**OK**/
		if("userInsertCollect".equals(action)){ // 來自listOneTravelPoint.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			ReportcollectService reportcollectSvc = new ReportcollectService();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer act_no = null;
				Integer forum_no = null;
				Integer tra_no = null;
				Integer stroke_no = null;
				Integer rc_rep_handle = 3;
				Integer rc_col_status = 0;
				Integer rep_rel = 1;
				String rep_content = null;
				/**帶改**/
				ReportcollectVO check = new ReportcollectVO();
				check = reportcollectSvc.checkBlogCollect(blog_no, mem_no);
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listOneBlog.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				if(check == null) reportcollectVO = reportcollectSvc.addRep(mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				else reportcollectSvc.updateRep(rc_no,mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneTravelPoint.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listOneBlog.jsp");
				failureView.forward(req,res);
			}
		}
		/**OK**/
        if("userCancelCollect".equals(action)){ // 來自listOneTravelPoint.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rc_no = new Integer(req.getParameter("rc_no").trim());
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer act_no = null;
				Integer forum_no = null;
				Integer tra_no = null;
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listOneBlog.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ReportcollectService reportcollectSvc = new ReportcollectService();
				reportcollectVO = reportcollectSvc.updateRep(rc_no,mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportcollectVO",reportcollectVO); // 資料庫update成功後,正確的reportcollectVO物件,存入req
				String url = "/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneTravel.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listOneBlog.jsp");
				failureView.forward(req,res);
			}
		}
        
        /**OK**/
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
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer act_no = null;
				Integer forum_no = null;
				Integer tra_no = null;
				Integer stroke_no = null;
				Integer rc_rep_handle = 0;
				Integer rc_col_status = 2;
				Integer rep_rel = 0;
				String rep_content = req.getParameter("rep_content").trim();
				if(rep_content==null || (rep_content.trim()).length()==0) errorMsgs.add("請輸入檢舉原因!");
				
				reportcollectVO = reportcollectSvc.checkBlogReport(blog_no, mem_no);
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listOneBlog.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				reportcollectVO = reportcollectSvc.addRep(mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/blog/listOneBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneBlog.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/listOneBlog.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("cancelCollectFromHome".equals(action)){ // 來自listCollectBlog.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rc_no = new Integer(req.getParameter("rc_no").trim());
				Integer blog_no = new Integer(req.getParameter("blog_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer act_no = null;
				Integer forum_no = null;
				Integer tra_no = null;
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listCollectBlog.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ReportcollectService reportcollectSvc = new ReportcollectService();
				reportcollectVO = reportcollectSvc.updateRep(rc_no,mem_no,tra_no,act_no,forum_no,blog_no,stroke_no,rc_rep_handle,rc_col_status,rep_rel,rep_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportcollectVO",reportcollectVO); // 資料庫update成功後,正確的reportcollectVO物件,存入req
				String url = "/front-end/home/listCollectBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listCollectBlog.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listCollectBlog.jsp");
				failureView.forward(req,res);
			}
		}
        /***/
        /*****************************0902*******************************/
        if("deleteBlog".equals(action)){ // 來自listMyBlog.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer blog_no = null;
				try{
					blog_no = new Integer(req.getParameter("blog_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("查無資訊!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listMyBlog.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BlogService blogSvc = new BlogService();
				blogSvc.updateDel(blog_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/front-end/home/listMyBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listMyBlog.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listMyBlog.jsp");
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