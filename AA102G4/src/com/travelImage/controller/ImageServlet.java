package com.travelImage.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.travelImage.model.*;

public class ImageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		byte[] b = null;
		try {
			TravelImageService travelImageSvc = new TravelImageService();
			String tra_img_no = req.getParameter("tra_img_no");
			TravelImageVO travelImageVO = travelImageSvc.getOneTravelImage(Integer.valueOf(tra_img_no));
			b = travelImageVO.getTra_img();
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