package com.nav;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Nav
 */
@WebServlet("/Nav")
public class Nav extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String navaction= request.getParameter("navaction").trim();
		HttpSession session = request.getSession();
		String url="";
		
		//建宇開始
		if("emp".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/Emp/emp_index.jsp";
		}
		//建宇結束
		
		//敏奇開始
		if("item".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/item/listAllItem.jsp";
		}
		//敏奇結束
		
		//慶瑄開始
		if("member".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/member/listAllMember.jsp";
		}
		if("qa".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/QA/QA_index.jsp";
		}
		if("travelPoint".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/travel/listAllTravel.jsp";
		}
		if("travelImage".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/travelImage/travelImage_index.jsp";
		}
		if("blog".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/blog/listAllBlog.jsp";
		}
		//慶瑄結束
		
		//世麒開始
		if("forum".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/forum/Forum_index.jsp";
		}
		if("que_ans".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/questionsanswers/Que_ans_index.jsp";
		}
		//世麒結束
		
		//善合開始
		if("actm".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/activity/act_index.jsp";
		}
		if("repm".equals(navaction)){
			session.setAttribute("navaction", navaction);
			url="/back-end/reportcollect/rep_index.jsp";
		}
		//善合結束
		
		
		RequestDispatcher successView = request.getRequestDispatcher(url); 
		successView.forward(request, response);
	}
}
