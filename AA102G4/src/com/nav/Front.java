package com.nav;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Front
 */
@WebServlet("/Front")
public class Front extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String navaction= request.getParameter("navaction").trim();
		String url="";
		if("news".equals(navaction)){
			url="/front-end/New.jsp";
		}
		//建宇開始
		if("".equals(navaction)){
			url="";
		}
		//建宇結束
		
		//敏奇開始
		if("bike".equals(navaction)){
			url="/front-end/index.jsp";
		}
		if("item".equals(navaction)){
			url="/front-end/item/AllItem.jsp";
		}
		if("home".equals(navaction)){
			url="/front-end/home/homeIndex.jsp";
		}
		//敏奇結束
		
		//慶瑄開始
		if("home".equals(navaction)){
			url="/front-end/home/homeIndex.jsp";
		}
		if("login".equals(navaction)){
			url="/front-end/Login/Login.jsp";
		}
		if("userInfo".equals(navaction)){
			url="/front-end/home/listOneMember.jsp";
		}
		if("userPwUpdate".equals(navaction)){
			url="/front-end/home/user_pw_update.jsp";
		}
		if("pwConfirm".equals(navaction)){
			url="/front-end/home/homeIndex.jsp";
		}
		if("infoConfirm".equals(navaction)){
			url="/front-end/home/homeIndex.jsp";
		}
		if("insertFriend".equals(navaction)){
			url="/front-end/home/friend.jsp";
		}
		if("searchTravelPoint".equals(navaction)){
			url="/front-end/travelPoint/listAllTravelPoint.jsp";
		}
		if("searchBlog".equals(navaction)){
			url="/front-end/blog/listAllBlog.jsp";
		}
		if("userInsertTravelPoint".equals(navaction)){
			url="/front-end/travelPoint/addTravel.jsp";
		}
		if("followConfirm".equals(navaction)){
			url="/front-end/home/friend.jsp";
		}
		/*********************0827********************/
		if("myFriend".equals(navaction)){
			url="/front-end/home/myFriends.jsp";
		}
		if("blacklist".equals(navaction)){
			url="/front-end/home/blacklist.jsp";
		}
		if("myTravelPoint".equals(navaction)){
			url="/front-end/home/listMyTravelPoint.jsp";
		}
		if("collectPoint".equals(navaction)){
			url="/front-end/home/listCollectTravelPoint.jsp";
		}
		if("myBlog".equals(navaction)){
			url="/front-end/home/listMyBlog.jsp";
		}
		if("collectBlog".equals(navaction)){
			url="/front-end/home/listCollectBlog.jsp";
		}
		if("userInsertBlog".equals(navaction)){
			url="/front-end/blog/addBlog.jsp";
		}
		
		/*********************0827********************/
		//慶瑄結束
		
		//世麒開始
		if("forum_front".equals(navaction)){
			url = "/front-end/forum/Forum_index.jsp";
		}
		if("add_front".equals(navaction)){
			url = "/front-end/forum/Forum_add.jsp";
		}
		/**9/5新增**/
		if("QA".equals(navaction)){
			url = "/front-end/questionsanswers/Que_ans_index.jsp";
		}
		//世麒結束
		
		//善合開始
		if("act_browse".equals(navaction)){
			url="/front-end/activity/act_findexHot.jsp";
		}
		//善合結束
		RequestDispatcher successView = request.getRequestDispatcher(url); 
		successView.forward(request, response);
	}

}
