package com.member.login;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.*;


public class Login extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);	
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		String url = "";
		String action = req.getParameter("action").trim();
		if("login".equals(action)){
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String mem_acc= req.getParameter("mem_acc").trim();
			String mem_pw= req.getParameter("mem_pw").trim();
			/***************************2.開始查詢資料*****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMemberByAcc(mem_acc);
			if(memberVO == null){
				errorMsgs.add("無此帳號!");
			}else if(memberVO.getMem_del() == 1){  //判斷 帳號是否停權
				errorMsgs.add("帳號已停權,請重新註冊新帳號!");
			}else if(!memberVO.getMem_pw().equals(mem_pw)){  //判斷 帳密是否正確
				errorMsgs.add("請確認密碼是否正確!");
			}else{
				session.setAttribute("memberVO",memberVO);
				try{	 //看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					String location = (String) session.getAttribute("frontLocation");
					if (location != null) {
						session.removeAttribute("frontLocation");
						res.sendRedirect(location);
						return;
					}
				}catch (Exception ignored) {
					url = "/front-end/index.jsp";
				}
			}
			if(!errorMsgs.isEmpty()){
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Login/Login.jsp");
				failureView.forward(req,res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			url = "/front-end/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 index.jsp
			successView.forward(req,res);
		}
		if("logout".equals(action)){
			session.invalidate();
			url = "/front-end/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 index.jsp
			successView.forward(req,res);
		}
	}
}