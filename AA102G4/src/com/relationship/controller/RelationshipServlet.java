package com.relationship.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.member.model.*;
import com.relationship.model.*;

public class RelationshipServlet extends HttpServlet{
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
//		if("update".equals(action)){ // 來自update_member_input.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to send the ErrorPage view.
//			req.setAttribute("errorMsgs",errorMsgs);
//			
//			try{
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
//				String mem_acc = req.getParameter("mem_acc").trim();
//				if(mem_acc==null || (mem_acc.trim()).length()==0) errorMsgs.add("請輸入帳號!");
//				
//				String mem_pw = req.getParameter("mem_pw").trim();
//				if(mem_pw==null || (mem_pw.trim()).length()==0) errorMsgs.add("請輸入密碼!");
//				
//				String mem_name = req.getParameter("mem_name").trim();
//				if(mem_name==null || (mem_name.trim()).length()==0) errorMsgs.add("請輸入姓名!");
//				
//				String mem_nickname = req.getParameter("mem_nickname").trim();
//				String mem_add = req.getParameter("mem_add").trim();
//				String mem_phone = req.getParameter("mem_phone").trim();
//				String mem_email = req.getParameter("mem_email").trim();
//				if(mem_email==null || (mem_email.trim()).length()==0) errorMsgs.add("請輸入信箱!");
//				
//				Part part = req.getPart("mem_photo");
//				InputStream in = part.getInputStream();
//				byte[] mem_photo = new byte[in.available()];
//				if(getFileNameFromPart(part)!=null){
//					mem_photo=new byte[in.available()];
//					in.read(mem_photo);
//				}else{
//					MemberService memberSvc = new MemberService();
//					MemberVO memberVO = memberSvc.getOneMember(mem_no);
//					mem_photo = memberVO.getMem_photo();
//					if(mem_photo !=null)
//						in.read(mem_photo);
//				}
//				in.close();
//				
//				Integer mem_reg = new Integer(req.getParameter("mem_reg").trim());
//				Integer mem_del = null;
//				try{
//					mem_del = new Integer(req.getParameter("mem_del").trim());
//				}catch(NumberFormatException e){
//					mem_del = 0;
//					errorMsgs.add("請輸入是否停權!");
//				}
//
//				MemberVO memberVO = new MemberVO();
//				memberVO.setMem_no(mem_no);
//				memberVO.setMem_acc(mem_acc);
//				memberVO.setMem_pw(mem_pw);
//				memberVO.setMem_name(mem_name);
//				memberVO.setMem_nickname(mem_nickname);
//				memberVO.setMem_add(mem_add);
//				memberVO.setMem_phone(mem_phone);
//				memberVO.setMem_email(mem_email);
//				memberVO.setMem_photo(mem_photo);
//				memberVO.setMem_reg(mem_reg);
//				memberVO.setMem_del(mem_del);
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				MemberService memberSvc = new MemberService();
//				memberVO = memberSvc.updateMember(mem_no,mem_acc,mem_pw,mem_name,mem_nickname,mem_add,mem_phone,mem_email,mem_photo,mem_reg,mem_del);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("memberVO",memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
//				String url = "/back-end/member/listOneMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
//				successView.forward(req,res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			}catch(Exception e){
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
//				failureView.forward(req,res);
//			}
//		}
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
		
		/*****************************************0824新增****************************************/
        if("follow".equals(action)){ // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			RelationshipService relationshipSvc = new RelationshipService();
			RelationshipVO relationshipVO = new RelationshipVO();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mem_no_main = null;
				try{
					mem_no_main = new Integer(req.getParameter("mem_no_main").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請問你是誰?");
				}
				
				Integer rel_mem_no = null;
				try{
					rel_mem_no = new Integer(req.getParameter("rel_mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請問你要加誰為好友?");
				}
				
				Integer rel_status = 0;
				Integer rel_follow = 0;
				
				RelationshipVO check =  new RelationshipVO();
				check = relationshipSvc.checkRelationship(mem_no_main,rel_mem_no);
				
				relationshipVO.setMem_no_main(mem_no_main);
				relationshipVO.setRel_mem_no(rel_mem_no);
				relationshipVO.setRel_status(rel_status);
				relationshipVO.setRel_follow(rel_follow);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("relationshipVO",relationshipVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/friend.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				if(check!=null){
					if(check.getRel_follow() != 0){
						relationshipSvc.updateRelationship(relationshipVO);
					}
				}else{
					relationshipSvc.addRelationship(relationshipVO);
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/home/confirm_follow.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交confirm_follow.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/friend.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("noFollow".equals(action)){ // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			RelationshipService relationshipSvc = new RelationshipService();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mem_no_main = null;
				try{
					mem_no_main = new Integer(req.getParameter("mem_no_main").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請問你是誰?");
				}
				
				Integer rel_mem_no = null;
				try{
					rel_mem_no = new Integer(req.getParameter("rel_mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請問你不想追蹤誰?");
				}
				
				Integer rel_status = 0;
				Integer rel_follow = 1;
				
				RelationshipVO relationshipVO = new RelationshipVO();
				relationshipVO.setMem_no_main(mem_no_main);
				relationshipVO.setRel_mem_no(rel_mem_no);
				relationshipVO.setRel_status(rel_status);
				relationshipVO.setRel_follow(rel_follow);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("relationshipVO",relationshipVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/friend.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				relationshipVO = relationshipSvc.updateRelationship(relationshipVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/home/friend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交friend.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/friend.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("blacklist".equals(action)){ // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			RelationshipService relationshipSvc = new RelationshipService();
			RelationshipVO relationshipVO = new RelationshipVO();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mem_no_main = null;
				try{
					mem_no_main = new Integer(req.getParameter("mem_no_main").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請問你是誰?");
				}
				
				Integer rel_mem_no = null;
				try{
					rel_mem_no = new Integer(req.getParameter("rel_mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請問你要加誰進黑名單?");
				}
				
				Integer rel_status = 1;
				Integer rel_follow = 1;
				
				RelationshipVO check =  new RelationshipVO();
				check = relationshipSvc.checkRelationship(mem_no_main,rel_mem_no);
				
				relationshipVO.setMem_no_main(mem_no_main);
				relationshipVO.setRel_mem_no(rel_mem_no);
				relationshipVO.setRel_status(rel_status);
				relationshipVO.setRel_follow(rel_follow);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("relationshipVO",relationshipVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/friend.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				if(check!=null){
					if(check.getRel_status() != 1){
						relationshipSvc.updateRelationship(relationshipVO);
					}
				}else{
					relationshipSvc.addRelationship(relationshipVO);
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/home/friend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交confirm_follow.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/friend.jsp");
				failureView.forward(req,res);
			}
		}
        
        if("noBlacklist".equals(action)){ // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			RelationshipService relationshipSvc = new RelationshipService();
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer mem_no_main = null;
				try{
					mem_no_main = new Integer(req.getParameter("mem_no_main").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請問你是誰?");
				}
				
				Integer rel_mem_no = null;
				try{
					rel_mem_no = new Integer(req.getParameter("rel_mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請問你想把誰取消黑名單?");
				}
				
				Integer rel_status = 0;
				Integer rel_follow = 1;
				
				RelationshipVO relationshipVO = new RelationshipVO();
				relationshipVO.setMem_no_main(mem_no_main);
				relationshipVO.setRel_mem_no(rel_mem_no);
				relationshipVO.setRel_status(rel_status);
				relationshipVO.setRel_follow(rel_follow);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("relationshipVO",relationshipVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/blacklist.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				relationshipVO = relationshipSvc.updateRelationship(relationshipVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/home/blacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交friend.jsp
				successView.forward(req,res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/home/blacklist.jsp");
				failureView.forward(req,res);
			}
		}
        /*****************************************0824新增****************************************/
	}
}