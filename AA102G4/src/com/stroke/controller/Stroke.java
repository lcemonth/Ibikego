package com.stroke.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;

import com.emp.model.*;
import com.google.gson.Gson;
import com.member.model.MemberVO;
import com.stroke.model.StrokeService;
import com.strokedetails.model.StrokeDetailsService;
import com.strokedetails.model.StrokeDetailsVO;
import com.travel.model.TravelService;
import com.travel.model.TravelVO;

/**
 * Servlet implementation class Test
 */
//@WebServlet("/Test")
public class Stroke extends HttpServlet {
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

		
		if("query".equals(action)){
			url="front-end/Stroke/stroke_index.jsp";
		}
		if("detail".equals(action)){
			Integer stroke_no = new Integer( request.getParameter("stroke_no").trim());
			request.setAttribute("stroke_no", stroke_no);
			url="front-end/Stroke/stroke_detail.jsp";
		}
		if("add".equals(action)){
			HttpSession session = request.getSession();
			session.removeAttribute("daysItinerary");	//清除所規劃好的景點
//			session.removeAttribute("informationList");	//清除所選擇的旅遊點
			url="front-end/Stroke/stroke_add.jsp";
		}
		if("planning".equals(action)){
			url="front-end/Stroke/stroke_planning.jsp";
		}
		if("getALLTravel".equals(action)){	//取得資料庫中所有點	或是 景點 或 休息點 
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			Integer information = new Integer( request.getParameter("information").trim());
			List<TravelVO> list = new ArrayList<TravelVO>();
			TravelService travelSvc=new TravelService();
			
			if(information==2){
				list=travelSvc.getAll();
			}else{
				list=travelSvc.getAll(information);
			}
			PrintWriter out = response.getWriter();
			out.write(new Gson().toJson(list));
			out.flush();
			out.close();
			return;
		}
		if("newAttractions".equals(action)){	//將旅遊點加入行程規劃中
			Integer informationAdd = new Integer( request.getParameter("informationAdd").trim());
			HttpSession session = request.getSession();
//			String location = (String) session.getAttribute("location");
			List informationList= (List) session.getAttribute("informationList");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			if(informationList==null){
				List list = new ArrayList();
				list.add(informationAdd);
				session.setAttribute("informationList", list);
				out.write("加入成功");
			}else{
				if(informationList.contains(informationAdd)){
					out.write("已有此景點");
				}else{
					informationList.add(informationAdd);
					session.setAttribute("informationList", informationList);
					out.write("加入成功");
				}
			}
			out.flush();
			out.close();
			return;
		}
		if("strokeTravel".equals(action)){	//將行程規劃的點 從session 撈出來
			HttpSession session = request.getSession();
			List informationList= (List) session.getAttribute("informationList");
			List<TravelVO> list = new ArrayList<TravelVO>();
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			TravelService travelSvc=new TravelService();
												
			for (int i = 0; i < informationList.size(); i++) {
				list.add(new TravelService().getOneTravel((Integer)informationList.get(i)));
			}
			out.write(new Gson().toJson(list));
			out.flush();
			out.close();
			return;
		}
		if("delectBackpack".equals(action)){	//移除背包內的旅遊點
			HttpSession session = request.getSession();
			List list= (List) session.getAttribute("informationList");
			Integer tra_no = new Integer( request.getParameter("tra_no").trim());
			
			list.remove(tra_no);
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			if(list.size()!=0){
				out.write(new Gson().toJson(list));
			}
			out.flush();
			out.close();
			return;
		}
		if("travelDate".equals(action)){	//將行程規劃 加入某個天數中
			HttpSession session = request.getSession();
			Map daysItinerary= (HashMap) session.getAttribute("daysItinerary");
			Integer travelDate = new Integer( request.getParameter("travelDate").trim());
			Integer tra_no = new Integer( request.getParameter("tra_no").trim());
			
			List list= new ArrayList();
			Map map = new HashMap();
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			if((daysItinerary!=null) && (daysItinerary.get(travelDate)!=null)){	//如果該筆天數内不為空 
				list =(ArrayList) daysItinerary.get(travelDate);
				if(!list.contains(tra_no)){
					list.add(tra_no);
				}
//				map.put(travelDate, list);
				daysItinerary.put(travelDate, list);
				session.setAttribute("daysItinerary", daysItinerary);
				out.write(new Gson().toJson(list));
				
			}else{	//如果該筆天數為空
				if(daysItinerary!=null){
					list.add(tra_no);
					daysItinerary.put(travelDate, list);
					session.setAttribute("daysItinerary", daysItinerary);
					out.write(new Gson().toJson(list));
				}else{
					list.add(tra_no);
					map.put(travelDate, list);
					session.setAttribute("daysItinerary", map);
					out.write(new Gson().toJson(list));
				}
			}
			out.flush();
			out.close();
			return;
		}
		if("itineraryDays".equals(action)){			//將規劃好的行程在特定的天數中顯示
			HttpSession session = request.getSession();
			Map daysItinerary= (HashMap) session.getAttribute("daysItinerary");
			Integer travelDate = new Integer( request.getParameter("travelDate").trim());
			
			List list=new ArrayList();
			List itineraryDaysList=new ArrayList();
			Map map = new HashMap();
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			if(daysItinerary!=null){
				list =(ArrayList) daysItinerary.get(travelDate);
				
				if(list!=null){
					for (int i = 0; i < list.size(); i++) {
						itineraryDaysList.add(new TravelService().getOneTravel((Integer)list.get(i)));
					}
					out.write(new Gson().toJson(itineraryDaysList));
				}
			}
//			out.write(new Gson().toJson(list));
			out.flush();
			out.close();
			return;
		}
		if("removePlan".equals(action)){	//將天數內的旅遊點移除
			HttpSession session = request.getSession();
			Map daysItinerary= (HashMap) session.getAttribute("daysItinerary");
			Integer travelDate =new Integer( request.getParameter("travelDate").trim());	//天數
			Integer tra_no = new Integer( request.getParameter("tra_no").trim());	//要移除的點
			
			List list= new ArrayList();
			Map map = new HashMap();
			
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			list =(ArrayList) daysItinerary.get(travelDate);
			list.remove(tra_no);
			
			out.write(new Gson().toJson(list));
			out.flush();
			out.close();
			return;
			
		}
		
		if("detailedItinerary".equals(action)){
			Integer stroke_no= new Integer(request.getParameter("stroke_no").trim());	//要查詢的行程清單
			List<StrokeDetailsVO> list= new ArrayList();
			List<TravelVO>	travelList=new ArrayList();
			list = new StrokeDetailsService().GetAllDetailedItinerary(stroke_no);
//			for(int i=0;i<list.size();i++){
//				list.get(0).getStroke_no();
//			}
//			Integer tra_no =new StrokeDetailsService().GetAllDetailedItinerary(stroke_no).get(0).getTra_no();
			
			for (int i = 0; i < list.size(); i++) {
				Integer tra_no=list.get(i).getTra_no();
				travelList.add(new TravelService().getOneTravel(tra_no));
			}
			
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			
			out.write(new Gson().toJson(travelList));
			out.flush();
			out.close();
			return;

		}
		
		
		
		if("insert".equals(action)){	//新增規劃行程
			HttpSession session = request.getSession();
			Map daysItinerary= (HashMap) session.getAttribute("daysItinerary");	//所有天數的行程
			MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");	//會員VO
			String stroke_name=request.getParameter("stroke_name").trim();	//行程名稱
			Date buildDate= java.sql.Date.valueOf(request.getParameter("buildDate").trim());
//			String stroke_name="test11";
//			Date buildDate=java.sql.Date.valueOf("2016-08-07");
			
			List list=new ArrayList();
			StrokeService strokeSvc =new StrokeService();
				strokeSvc.addStroke(stroke_name, memberVO.getMem_no(),buildDate,daysItinerary);

//			daysItinerary.get(key)
			session.removeAttribute("daysItinerary");	//清除天數內的行程
			session.removeAttribute("informationList");	//清除所選擇的旅遊點
			url="front-end/Stroke/stroke_index.jsp";
			
		}
		
		if("delete".equals(action)){	//刪除行程規劃
			Integer no= new Integer(request.getParameter("no").trim());	
			
			new StrokeService().deleteStroke(no);
			url="front-end/Stroke/stroke_index.jsp";
		}
		
		RequestDispatcher successView = request.getRequestDispatcher(url); 
		successView.forward(request, response);
	}

}
