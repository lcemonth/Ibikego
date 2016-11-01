package com.privileged.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.privileged.model.PrivilegedService;

/**
 * Servlet implementation class privileged
 */
@WebServlet("/Privileged")
public class Privileged extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="";
		String action= request.getParameter("action").trim();

		if("query".equals(action)){
			url="back-end/Privileged/privileged_index.jsp";
		}
		if("modify".equals(action)){
			url="back-end/Privileged/privileged_modify.jsp";			
		}
		if("update".equals(action)){
			Integer emp_no= new Integer(request.getParameter("emp_no").trim());
			String[] pvl_no=request.getParameterValues("pvl_no");
			PrivilegedService privilegedSvc =new PrivilegedService();
			privilegedSvc.insert(emp_no, pvl_no);
			url="back-end/Privileged/privileged_index.jsp";
		}
		RequestDispatcher successView = request.getRequestDispatcher(url); 
		successView.forward(request, response);
	}

}
