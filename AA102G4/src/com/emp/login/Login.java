package com.emp.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		 String action = request.getParameter("action").trim();
		 String url = "";

		
		if("login".equals(action)){
			String emp_acc = request.getParameter("emp_acc").trim();
			String emp_pw = request.getParameter("emp_pw").trim();

			EmpService Service = new EmpService();
			EmpVO empVO = Service.getOneEmpAcc(emp_acc);
			if (empVO == null) {
				String messageError = "查無此帳號";
				url = "back-end/Login/login.jsp";
				request.setAttribute("messageError", messageError);
			} else {
				if(empVO.getEmp_pw().equals(emp_pw)) { // 判斷帳密是否正確
					session.setAttribute("empVO", empVO);
//					try{	 //看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
//						String location = (String) session.getAttribute("location");
//						if (location != null) {
//							session.removeAttribute("location");
//							response.sendRedirect(location);
//							return;
//						}
//					}catch (Exception ignored) {}
					url = "/back-end/index.jsp";
				} else {	//密碼錯誤
					String messageError = "請確認帳密";
					url = "back-end/Login/login.jsp";
					request.setAttribute("messageError", messageError);
				}
			}
		}
		if("logout".equals(action)){
			session.invalidate();
			url = "back-end/Login/login.jsp";
		}
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}
}
