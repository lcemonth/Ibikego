package com.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmpLoginFilter implements Filter {
	/*
	 * 請參閱吳神 Java Web Application 第229頁
	 * 與Security5_CUST_Session_Filter 範例
	 * 
	 * */
	
	private FilterConfig config;
	public void init(FilterConfig config) {	  
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();	// 【取得 session】
		Object empVO = session.getAttribute("empVO");	// 【從 session 判斷此user是否登入過】
		if (empVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back-end/Login/login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}