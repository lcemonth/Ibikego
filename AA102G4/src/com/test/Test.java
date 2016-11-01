package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import com.emp.model.*;
import com.google.gson.Gson;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
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
		String action= request.getParameter("action").trim();
		String url="";
		if("test".equals(action)){
			url="back-end/Test/test_index.jsp";
		}
		
		if("getEmpVO".equals(action)){
//			JSONArray array = new JSONArray();
//			JSONObject emp =new JSONObject();
			EmpService empSvc=new EmpService();
			List<EmpVO> list=empSvc.getAll();
			
			/* 		var latlng = new google.maps.LatLng(24.970826,121.1882077);
 					var latlng1 = new google.maps.LatLng(24.970826,121.2082077);
			 * 
			 * */
			
			 Map map = new HashMap();
				 map.put("Lat", 24.970826);
				 map.put("Lng", 121.1882077);
				 map.put("title", "這裡是哪裡?");
				 
			Map map2 = new HashMap(); 
				map2.put("Lat", 24.970826);
				map2.put("Lng", 121.2082077);
				map.put("title", "中央大學有超夢");
			
			List mapList=new ArrayList();
				mapList.add(map);
				mapList.add(map2);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(new Gson().toJson(mapList));
			out.flush();
			out.close();
			return;
		}
		
		RequestDispatcher successView = request.getRequestDispatcher(url); 
		successView.forward(request, response);
	}

}
