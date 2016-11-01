package com.activity.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.activity.model.*;
public class ActivityAlti extends HttpServlet{

		
		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}
		 
		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			
			req.setCharacterEncoding("UTF-8");
			res.setContentType("image/gif");
			ServletOutputStream out=res.getOutputStream();
			byte[] b=null;
			try{
				ActivityService actSvc=new ActivityService();
				String act_no=req.getParameter("act_no");
				ActivityVO actVO=actSvc.getOneAct(Integer.valueOf(act_no));
				b=actVO.getAct_alti();
			
					out.write(b);
					
			}catch(Exception e){
				InputStream in =getServletContext().getResourceAsStream("/front-end/activity/images/a1.gif");
				 b=new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
				
			}
			
		}	
			
}
