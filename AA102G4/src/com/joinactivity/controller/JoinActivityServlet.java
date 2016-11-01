package com.joinactivity.controller;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.activity.model.*;
import com.joinactivity.model.*;


public class JoinActivityServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String linkUrl="";
		String fwUrl="";
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
		
		

		    // 來自select_page.jsp的請求                                  // 來自 activity/listAllAct.jsp的請求
		if ("listEmps_ByDeptno_A".equals(action) || "listMems_ByActno_fromActPage".equals(action)) {
	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer act_no = new Integer(req.getParameter("act_no"));
	
				/*************************** 2.開始查詢資料 ****************************************/
				JoinactivityService jaSvc = new JoinactivityService();
				Set<JoinactivityVO> set = jaSvc.getJoinMemsByActno(act_no);
	
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listJoinMems", set);    // 資料庫取出的list物件,存入request
				
				String url = null;
				if ("listEmps_ByDeptno_A".equals(action))
					url = "/dept/listEmps_ByDeptno.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listMems_ByActno_fromActPage".equals(action)){
					url = "/back-end/activity/act_index.jsp";              // 成功轉交 dept/listAllDept.jsp
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
	
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
	}
}
