package com.member.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import com.member.model.*;
import com.tool.*;
import pwd.RanPwd;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/**************************************導頁*****************************************/
		if("home".equals(action)){
			String url = "/back-end/member/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 select_page.jsp
			successView.forward(req,res);
		}
		if("listAll".equals(action)){
			String url = "/back-end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllMember.jsp
			successView.forward(req,res);
		}
		if("add".equals(action)){
			String url = "/back-end/member/addMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addMember.jsp
			successView.forward(req,res);
		}
		if("selectOne".equals(action)){
			String url = "/back-end/member/selectOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 selectOne.jsp
			successView.forward(req,res);
		}
		/**********************************************************************************/
		if("getOne_For_Display".equals(action)){ // 來自selectOne.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_no");
				if(str==null||(str.trim()).length()==0){
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				Integer mem_no = null;
				try{
					mem_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
				if(memberVO==null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/selectOne.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO",memberVO); // 資料庫取出的memberVO物件,存入req
				String url ="/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/selectOne.jsp");
				failureView.forward(req,res);
			}
		}
		
		if ("searchMember".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.開始複合查詢***************************************/
				MemberService memberSvc = new MemberService();
				List<MemberVO> list  = memberSvc.getAll(map);
				if(list == null) errorMsgs.add("查無資料!");
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/friend.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("searchMember", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/home/friend.jsp"); // 成功轉交friend.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/friend.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)){ // 來自listAllMember.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO",memberVO);         // 資料庫取出的memberVO物件,存入req
				String url ="/back-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("update".equals(action)){ // 來自update_member_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String mem_acc = req.getParameter("mem_acc").trim();
				if(mem_acc==null || (mem_acc.trim()).length()==0) errorMsgs.add("請輸入帳號!");
				
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw==null || (mem_pw.trim()).length()==0) errorMsgs.add("請輸入密碼!");
				
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name==null || (mem_name.trim()).length()==0) errorMsgs.add("請輸入姓名!");
				
				String mem_nickname = req.getParameter("mem_nickname").trim();
				String mem_add = req.getParameter("mem_add").trim();
				String mem_phone = req.getParameter("mem_phone").trim();
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email==null || (mem_email.trim()).length()==0) errorMsgs.add("請輸入信箱!");
				
				Part part = req.getPart("mem_photo");
				InputStream in = part.getInputStream();
				byte[] mem_photo = new byte[in.available()];
				if(getFileNameFromPart(part)!=null){
					mem_photo=new byte[in.available()];
					in.read(mem_photo);
				}else{
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMember(mem_no);
					mem_photo = memberVO.getMem_photo();
					if(mem_photo !=null)
						in.read(mem_photo);
				}
				in.close();
				
				Integer mem_reg = new Integer(req.getParameter("mem_reg").trim());
				Integer mem_del = null;
				try{
					mem_del = new Integer(req.getParameter("mem_del").trim());
				}catch(NumberFormatException e){
					mem_del = 0;
					errorMsgs.add("請輸入是否停權!");
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_acc(mem_acc);
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_nickname(mem_nickname);
				memberVO.setMem_add(mem_add);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_photo(mem_photo);
				memberVO.setMem_reg(mem_reg);
				memberVO.setMem_del(mem_del);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(mem_no,mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO",memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("getOneUser_For_Update".equals(action)){ // 來自listAllMember.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數****************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO",memberVO);         // 資料庫取出的memberVO物件,存入req
				String url ="/front-end/home/user_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 user_update.jsp
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/listOneMember.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("user_update".equals(action)){ // 來自update_member_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String mem_acc = req.getParameter("mem_acc").trim();
				if(mem_acc==null || (mem_acc.trim()).length()==0) errorMsgs.add("請輸入帳號!");
				
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw==null || (mem_pw.trim()).length()==0) errorMsgs.add("請輸入密碼!");
				
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name==null || (mem_name.trim()).length()==0) errorMsgs.add("請輸入姓名!");
				
				String mem_nickname = req.getParameter("mem_nickname").trim();
				String mem_add = req.getParameter("mem_add").trim();
				String mem_phone = req.getParameter("mem_phone").trim();
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email==null || (mem_email.trim()).length()==0) errorMsgs.add("請輸入信箱!");
				
				Part part = req.getPart("mem_photo");
				InputStream in = part.getInputStream();
				byte[] mem_photo = new byte[in.available()];
				if(getFileNameFromPart(part)!=null){
					mem_photo=new byte[in.available()];
					in.read(mem_photo);
				}else{
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMember(mem_no);
					mem_photo = memberVO.getMem_photo();
					if(mem_photo !=null)
						in.read(mem_photo);
				}
				in.close();
				
				Integer mem_reg = new Integer(req.getParameter("mem_reg").trim());
				Integer mem_del = null;
				try{
					mem_del = new Integer(req.getParameter("mem_del").trim());
				}catch(NumberFormatException e){
					mem_del = 0;
					errorMsgs.add("請輸入是否停權!");
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_acc(mem_acc);
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_nickname(mem_nickname);
				memberVO.setMem_add(mem_add);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_photo(mem_photo);
				memberVO.setMem_reg(mem_reg);
				memberVO.setMem_del(mem_del);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/user_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(mem_no,mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO",memberVO); // 資料庫update成功後,正確的memberVO物件,存入req
				String url = "/front-end/home/confirm_userInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/user_update.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("user_pw_update".equals(action)){ // 來自update_member_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name==null || (mem_name.trim()).length()==0) errorMsgs.add("請輸入姓名!");
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw==null || (mem_pw.trim()).length()==0) errorMsgs.add("請輸入原密碼!");
				String newPw = req.getParameter("newPw").trim();
				if(newPw==null || (newPw.trim()).length()==0) errorMsgs.add("請輸入新密碼!");
				if (newPw.length() < 6) errorMsgs.add("密碼需大於等於6個字");
				String confirmNewPw = req.getParameter("confirmNewPw").trim();
				if(confirmNewPw==null || (confirmNewPw.trim()).length()==0) errorMsgs.add("請輸入確認新密碼!");
				if(!newPw.equals(confirmNewPw)) errorMsgs.add("新密碼不相符!");
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
				if(!mem_pw.equals(memberVO.getMem_pw())) errorMsgs.add("原密碼錯誤!");
				memberVO.setMem_pw(newPw);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/user_pw_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				memberSvc = new MemberService();
				memberVO = memberSvc.updateMemberPw(mem_no,mem_name,newPw);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO",memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/front-end/home/confirm_pw.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req,res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/user_pw_update.jsp");
				failureView.forward(req,res);
			}
		}
		
        if("insert".equals(action)){ // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_acc = req.getParameter("mem_acc").trim();
				if(mem_acc==null || (mem_acc.trim()).length()==0) errorMsgs.add("請輸入帳號!");
				
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw==null || (mem_pw.trim()).length()==0) errorMsgs.add("請輸入密碼!");
				
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name==null || (mem_name.trim()).length()==0) errorMsgs.add("請輸入姓名!");
				
				String mem_nickname = req.getParameter("mem_nickname").trim();
				String mem_add = req.getParameter("mem_add").trim();
				String mem_phone = req.getParameter("mem_phone").trim();
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email==null || (mem_email.trim()).length()==0) errorMsgs.add("請輸入信箱!");
				
				Part part = req.getPart("mem_photo");
				InputStream in = part.getInputStream();
				byte[] mem_photo = new byte[in.available()];
				in.read(mem_photo);
				in.close();
				
				Integer mem_reg = new Integer(req.getParameter("mem_reg").trim());
				Integer mem_del = null;
				try{
					mem_del = new Integer(req.getParameter("mem_del").trim());
				}catch(NumberFormatException e){
					mem_del = 0;
					errorMsgs.add("請輸入是否停權!");
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setMem_acc(mem_acc);
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_nickname(mem_nickname);
				memberVO.setMem_add(mem_add);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_photo(mem_photo);
				memberVO.setMem_reg(0);
				memberVO.setMem_del(0);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("memberVO",memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("user_insert".equals(action)){ // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			MemberService memberSvc = new MemberService();
			List<MemberVO> list = memberSvc.getAll();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_acc = req.getParameter("mem_acc").trim();
				if(mem_acc==null || (mem_acc.trim()).length()==0) errorMsgs.add("請輸入帳號!");
				if(mem_acc.length() < 7) errorMsgs.add("帳號需大於等於7個字");
				for (MemberVO memberVO : list) {
					if(memberVO.getMem_acc().equals(mem_acc)) errorMsgs.add("帳號已重複");
				}
				
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw==null || (mem_pw.trim()).length()==0) errorMsgs.add("請輸入密碼!");
				if (mem_pw.length() < 6) errorMsgs.add("密碼需大於等於6個字");
				
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name==null || (mem_name.trim()).length()==0) errorMsgs.add("請輸入姓名!");
				
				String mem_nickname = req.getParameter("mem_nickname").trim();
				
				String mem_add = req.getParameter("mem_add").trim();
				
				String mem_phone = req.getParameter("mem_phone").trim();
				
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email==null || (mem_email.trim()).length()==0) errorMsgs.add("請輸入信箱!");
				for (MemberVO memberVO : list) {
					if(memberVO.getMem_email().equals(mem_email)) errorMsgs.add("信箱已重複");
				}
				
				Part part = req.getPart("mem_photo");
				InputStream in = part.getInputStream();
				byte[] mem_photo = new byte[in.available()];
				in.read(mem_photo);
				in.close();
				
				Integer mem_reg = new Integer(req.getParameter("mem_reg").trim());
				Integer mem_del = null;
				try{
					mem_del = new Integer(req.getParameter("mem_del").trim());
				}catch(NumberFormatException e){
					mem_del = 0;
					errorMsgs.add("請輸入是否停權!");
				}
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_acc(mem_acc);
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_nickname(mem_nickname);
				memberVO.setMem_add(mem_add);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_photo(mem_photo);
				memberVO.setMem_reg(0);
				memberVO.setMem_del(0);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("memberVO",memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/register/register.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				memberVO = memberSvc.addMember(mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/Login/Login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交Login.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/register/register.jsp");
				failureView.forward(req,res);
			}
		}
        
		if("delete".equals(action)){ // 來自listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				/***************************1.接收請求參數***************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				/***************************2.開始刪除資料***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req,res);
			}
		}
		
		if ("forgetPsw".equals(action)) { // 來自Login.jsp的請求
			// HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				String mem_acc = new String(req.getParameter("mem_acc").trim());
				String mem_email = new String(req.getParameter("mem_email").trim());
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMemberByAcc(mem_acc);
				if(memberVO == null){
					errorMsgs.add("無此帳號!");
				}else if(!memberVO.getMem_email().equals(mem_email)){  //判斷 信箱是否正確
					errorMsgs.add("請確認信箱是否正確!");
				}
				/*if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Login/forgetPsw.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}*/
				/***************************
				 * 2.開始查詢資料
				 ****************************************/
				memberVO = memberSvc.getAcc_Email(mem_acc, mem_email);
				try {
					if ((memberVO.getMem_acc()).equals(mem_acc) && (memberVO.getMem_email()).equals(mem_email)) {
						String randomPwd = new RanPwd().RanPwd(8);
						MemberService memberSvc2 = new MemberService();
						memberSvc2.updateMember(memberVO.getMem_no(), memberVO.getMem_acc(), randomPwd, memberVO.getMem_name(),
												memberVO.getMem_nickname(),memberVO.getMem_add(), memberVO.getMem_phone(), memberVO.getMem_email(),
												memberVO.getMem_photo(),memberVO.getMem_reg(), memberVO.getMem_del());
						String subject = "您的新密碼";
						String messageText = "Hello "+memberVO.getMem_name()+",此為您的新密碼: "+randomPwd+" ,登入成功後請盡快修改您的密碼!";
						MailService mailSvc = new MailService();
						try {
							mailSvc.sendMail(mem_email, subject, messageText);
						} catch (Exception e) {
							System.out.print("error");
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(errorMsgs.size());
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Login/Login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("memberVO", memberVO);// 資料庫取出的memberVO物件,存入req
				String url = "/front-end/Login/Login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交Login.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Login/Login.jsp");
				failureView.forward(req, res);
			}
		}
	}
	public String getFileNameFromPart(Part part) {
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