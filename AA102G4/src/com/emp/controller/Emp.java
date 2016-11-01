package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

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
 * Servlet implementation class Emp
 */
@WebServlet("/Emp")
public class Emp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("UTF-8");
		String url="";
		String action= request.getParameter("action").trim();
		if("query".equals(action)){
			url="back-end/Emp/emp_index.jsp";
		}
		if("add".equals(action)){
			url="back-end/Emp/emp_add.jsp";			
		}
		if("modify".equals(action)){
			Integer emp_no= new Integer(request.getParameter("emp_no"));
			EmpVO modifyempVO=new EmpService().getOneEmp(emp_no);
			request.setAttribute("modifyempVO", modifyempVO);
			url="back-end/Emp/emp_modify.jsp";		
		}
		if("detail".equals(action)){
			Integer emp_no= new Integer(request.getParameter("emp_no"));
			EmpVO detailEmpVO=new EmpService().getOneEmp(emp_no);
			request.setAttribute("detailEmpVO", detailEmpVO);
			url="back-end/Emp/emp_detail.jsp";			
		}

		
		
		if("insert".equals(action)){
			String emp_acc= request.getParameter("emp_acc").trim();
			String emp_name= request.getParameter("emp_name").trim();
			String emp_email= request.getParameter("emp_email").trim();
			String emp_tel= request.getParameter("emp_tel").trim();
			String emp_phone= request.getParameter("emp_phone").trim();
			String emp_ps= request.getParameter("emp_ps").trim();
			Date emp_hire= java.sql.Date.valueOf(request.getParameter("emp_hire").trim());
			EmpService empServ =new EmpService();
				empServ.addEmp(emp_acc, emp_name,emp_email,emp_tel, emp_phone, emp_ps, emp_hire);
			url="back-end/Emp/emp_index.jsp";
	
		}
		if("update".equals(action)){
			Integer emp_no = new Integer( request.getParameter("emp_no").trim());
			String emp_acc= request.getParameter("emp_acc").trim();
			String emp_pw= request.getParameter("emp_pw").trim();
			String emp_name= request.getParameter("emp_name").trim();
			String emp_email= request.getParameter("emp_email").trim();
			String emp_tel= request.getParameter("emp_tel").trim();
			String emp_phone= request.getParameter("emp_phone").trim();
			String emp_ps= request.getParameter("emp_ps").trim();
			Date emp_hire= java.sql.Date.valueOf(request.getParameter("emp_hire").trim());
			EmpService empServ =new EmpService();
				empServ.updateEmp(emp_no, emp_acc,emp_pw, emp_name, emp_email, emp_tel, emp_phone, emp_ps, emp_hire);
			url="back-end/Emp/emp_index.jsp";			
		}
		if("delete".equals(action)){
			Integer emp_no = new Integer( request.getParameter("emp_no").trim());
			EmpService empServ =new EmpService();
				empServ.deleteEmp(emp_no);
			url="back-end/Emp/emp_index.jsp";		
		}
		if("select".equals(action)){
			try {
				String emp_name= request.getParameter("emp_name").trim();
				if( request.getMethod().equals("GET") ){
					emp_name= new String(emp_name.getBytes("ISO-8859-1"),"UTF-8");
				}
				request.setAttribute("emp_name", emp_name);
				request.setAttribute("list", new EmpService().getEmpName(emp_name));
				url="back-end/Emp/emp_index.jsp";
			} catch (Exception e) {
//				request.setAttribute("emp_name", "");
				request.setAttribute("list", new EmpService().getEmpName(""));
				url="back-end/Emp/emp_index.jsp";
			}
		}
		
		if("ajax".equals(action)){
			String emp_acc= request.getParameter("emp_acc").trim();
			EmpService empServ=new EmpService();
			EmpVO empVO=empServ.getOneEmpAcc(emp_acc);
			StringBuffer sb = new StringBuffer();
			if(empVO!=null){
				//����董���
				sb.append(false);
				PrintWriter pw = response.getWriter();
				pw.write(sb.toString());
			}else{
				//���撣唾��
				sb.append(true);
				PrintWriter pw = response.getWriter();
				pw.write(sb.toString());
			}
			return;
		}

		
		
		
		
		
		
		RequestDispatcher successView = request.getRequestDispatcher(url); 
		successView.forward(request, response);
	}

}
