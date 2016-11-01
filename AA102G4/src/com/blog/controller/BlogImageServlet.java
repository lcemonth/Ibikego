package com.blog.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.blog.model.*;

public class BlogImageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		byte[] b = null;
		try {
			BlogService blogSvc = new BlogService();
			String blog_no = req.getParameter("blog_no");
			BlogVO blogVO = blogSvc.getOneBlog(Integer.valueOf(blog_no));
			b = blogVO.getBlog_photo();
			out.write(b);

		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream(req.getContextPath()+"/res/images/tomcat.gif");
			b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
}