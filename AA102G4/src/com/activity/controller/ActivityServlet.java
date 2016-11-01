package com.activity.controller;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.activity.model.*;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.joinactivity.model.*;
import com.member.model.*;
import com.queryact.model.*;
import com.relationship.model.RelationshipService;
import com.relationship.model.RelationshipVO;
import com.reportcollect.model.ReportcollectService;
import com.reportcollect.model.ReportcollectVO;
import com.strokedetails.model.*;
import com.tool.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1000 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)
public class ActivityServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action").trim();
		String linkUrl="";
		String fwUrl="";

		//------------------------------------前端導頁區--------------------------------------
		//測試SOCKET
		if("testSocket".equals(action)){
			fwUrl="/front-end/activity/act_f_basic_index.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		//測試收藏檢舉----------
		
		
		if("colAndrepForum".equals(action)){
			fwUrl="/front-end/activity/act_f_listForum.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("colAndrepTravel".equals(action)){
			fwUrl="/front-end/activity/act_f_listTravel.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("colAndrepBlog".equals(action)){
			fwUrl="/front-end/activity/act_f_listBlog.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("colAndrepStroke".equals(action)){
			fwUrl="/front-end/activity/act_f_listStroke.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		//測試收藏檢舉----------
		
		//----訪客瀏覽
		if("guestQuery".equals(action)){
			fwUrl="/front-end/activity/act_f_listNewForGuest.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		//----查詢個人揪團
		if("queryMyJoin".equals(action)){
			fwUrl="/front-end/activity/listAllMemJoinAct.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		} 
		//----查詢受邀的揪團
		if("listInvited".equals(action)){
			fwUrl="/front-end/activity/listAllMemInvitedAct.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		} 
		//----查詢最新揪團
		if("fquery".equals(action)){
			fwUrl="/front-end/activity/act_f_listNew.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		//----導到歷史發起的複合查詢
		if("historyLaunchAct".equals(action)){
			fwUrl="/front-end/activity/act_f_ComplexQuery_Mmem.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		//----導到歷史參加的複合查詢
		if("historyJoinAct".equals(action)){
			fwUrl="/front-end/activity/act_f_ComplexQuery_Joinmem.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("queryLaunchactStatus".equals(action)){
			fwUrl="/front-end/activity/listQueryLaunchact.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("queryJoinactStatus".equals(action)){
			fwUrl="/front-end/activity/listQueryJoinact_status.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("updatefAct".equals(action)){
			fwUrl="/front-end/activity/act_f_listWhoAdd.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("cancelfAct".equals(action)){
			fwUrl="/front-end/activity/act_f_listWhoAdd_cancel.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("manag_memfAct".equals(action)){
			fwUrl="/front-end/activity/act_f_invite_act.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("newAct".equals(action)){
			fwUrl="/front-end/activity/addAct.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("backfindex".equals(action)){
			fwUrl = "/front-end/activity/act_findexHot.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}
		if("listWhoAdd".equals(action)){
			fwUrl = "/front-end/activity/act_f_listWhoAdd.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			return;
		}

	   //------------------------------------前端--------------------------------------
		
		//複合查詢參加的揪團
				if ("listJoinAct_ByCompositeQuery".equals(action)) { // 來自act_f_ComplexQuery_Joinmem.jsp的複合查詢請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						
						/***************************1.將輸入資料轉為Map**********************************/ 
						//採用Map<String,String[]> getParameterMap()的方法 
						//注意:an immutable java.util.Map 
						//Map<String, String[]> map = req.getParameterMap();
						HttpSession session = req.getSession();
						Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
						if (req.getParameter("whichPage") == null){
							HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
							HashMap<String, String[]> map2 = new HashMap<String, String[]>();
							map2 = (HashMap<String, String[]>)map1.clone();
							session.setAttribute("map",map2);
							map = (HashMap<String, String[]>)req.getParameterMap();
						} 
						
						
						/***************************2.開始複合查詢***************************************/
						ActivityService actSvc = new ActivityService();
						List<ActivityVO> list  = actSvc.getMemJoinedActs(map);
						
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.setAttribute("list", list); // 資料庫取出的list物件,存入request
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/activity/act_f_listALLcomQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
						successView.forward(req, res);
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/act_f_ComplexQuery_Mmem.jsp");
						failureView.forward(req, res);
					}
				}
		//複合查詢發起的揪團
		if ("listGroups_ByCompositeQuery".equals(action)) { // 來自act_f_ComplexQuery_Mmem.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				
				/***************************2.開始複合查詢***************************************/
				ActivityService actSvc = new ActivityService();
				List<ActivityVO> list  = actSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("list", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/activity/act_f_listALLcomQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/activity/act_f_ComplexQuery_Mmem.jsp");
				failureView.forward(req, res);
			}
		}	
		// 來自訪客的查詢
		if ("guestQueryOneAct".equals(action)) { 
			 
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer act_no = Integer.valueOf(req.getParameter("act_no"));

				/***************************2.開始查詢資料*****************************************/
				ActivityService actSvc = new ActivityService();
				ActivityVO actVO = actSvc.getOneAct(act_no);

				StrokeDetailsService sdSvc=new StrokeDetailsService();
				int maxDay=sdSvc.getMaxDayByStrokeNo(actVO.getStroke_no());

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				 
				req.setAttribute("actVO", actVO); 
				req.setAttribute("maxDay", maxDay);
				String url="";
			
				if ("guestQueryOneAct".equals(action)){
					 url = "/front-end/activity/listOneQueryAct.jsp";
				}

				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage()); 
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/activity/act_f_listNewForGuest.jsp");
				failureView.forward(req, res);
			}
		}
		//剔除已入團的單一會員
		 if("kickOneMem".equals(action)){
				List<String> Msgs = new LinkedList<String>();
				
				req.setAttribute("Msgs", Msgs);
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
					Integer act_no = new Integer(req.getParameter("act_no"));
					Integer kick_mem_no = new Integer(req.getParameter("kick_mem_no"));
					//rel_invite=1 邀請 0取消
					/***************************2.開始查詢資料*****************************************/
					
					JoinactivityService jactSvc=new JoinactivityService();
					
					JoinactivityVO jactVO=jactSvc.updateJoinAct(act_no, kick_mem_no, 4, 0, 0.0, 0.0);
					if(jactVO!=null){
						Msgs.add("剔除成功");
					}else{
						Msgs.add("剔除失敗");
					}
					List<JoinactivityVO> list=jactSvc.getCnJMemsByAct(act_no);

					if (!Msgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/listJoinedMemsInAct.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
				
					req.setAttribute("list", list);
					req.setAttribute("act_no", act_no);
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					
					String url="";
					if ("kickOneMem".equals(action)){
						 url = "/front-end/activity/listJoinedMemsInAct.jsp";
					}
             
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					Msgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/listJoinedMemsInAct.jsp");
					failureView.forward(req, res);
				}
			 
		 } 
		//邀請取消單一追蹤的會員
		 if("inviteOneMem".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
					Integer act_no = new Integer(req.getParameter("act_no"));
					Integer rel_mem_no = new Integer(req.getParameter("r_mem_no"));
					Integer mem_no_main = new Integer(req.getParameter("m_mem_no"));
					Integer rel_invite = new Integer(req.getParameter("rel_invite"));
					//rel_invite=1 邀請 0取消
					/***************************2.開始查詢資料*****************************************/
					RelationshipService rsSvc=new RelationshipService();
					JoinactivityService jactSvc=new JoinactivityService();
					JoinactivityVO jactVO=jactSvc.getIsINActByMem_no(act_no, rel_mem_no);
					
					if(rel_invite==1){
						if(jactVO!=null){
							jactSvc.updateJoinAct(act_no, rel_mem_no, 0, 0, 0.0, 0.0);
						}
						else{//該成員未參加時
							jactSvc.addJoinAct(act_no, rel_mem_no, 0, 0, 0.0, 0.0);
						}
					}
					else if(rel_invite==0){
							jactSvc.updateJoinAct(act_no, rel_mem_no,3, 0, 0.0, 0.0);
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/listAllM_followRms.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					List<RelationshipVO> list=rsSvc.getMFollowRms(mem_no_main);
					req.setAttribute("list", list);
					req.setAttribute("act_no", act_no);
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					
					String url="";
					if ("inviteOneMem".equals(action)){
						 url = "/front-end/activity/listAllM_followRms.jsp";
					}
                
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/listAllM_followRms.jsp");
					failureView.forward(req, res);
				}
			 
		 } 
		//剔除已入團的會員列表
		 if("kick".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
					Integer act_no = new Integer(req.getParameter("act_no"));
					Integer mem_no = new Integer(req.getParameter("mem_no"));
					req.setAttribute("act_no", act_no);
					/***************************2.開始查詢資料*****************************************/
					JoinactivityService jactSvc=new JoinactivityService();
					List<JoinactivityVO> list=jactSvc.getCnJMemsByAct(act_no);
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/act_f_invite_act.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					
					String url="";
					if ("kick".equals(action)){
						 url = "/front-end/activity/listJoinedMemsInAct.jsp";
					}
					req.setAttribute("list", list);
					req.setAttribute("mem_no", mem_no);
					req.setAttribute("act_no", act_no);
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/act_f_invite_act.jsp");
					failureView.forward(req, res);
				}
			 
		 } 
		 
		//邀請追蹤的會員列表
		 if("invite".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
					Integer act_no = new Integer(req.getParameter("act_no"));
					Integer mem_no_main = new Integer(req.getParameter("mem_no"));
					req.setAttribute("act_no", act_no);
					/***************************2.開始查詢資料*****************************************/
					RelationshipService rsSvc=new RelationshipService();
					List<RelationshipVO> list=rsSvc.getMFollowRms(mem_no_main);
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/act_f_invite_act.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					
					String url="";
					if ("invite".equals(action)){
						 url = "/front-end/activity/listAllM_followRms.jsp";
					}
					req.setAttribute("list", list);
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/act_f_invite_act.jsp");
					failureView.forward(req, res);
				}
			 
		 } 
		
		//修改揪團的公開狀態
		 if("updatePublic".equals(action)){
			 List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("act_no");
					Integer act_no = new Integer(str);
					HttpSession session=req.getSession();
					Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
					Integer actisp=new Integer(req.getParameter("actisp"));
					
					/***************************2.開始查詢資料*****************************************/
					ActivityService actSvc=new ActivityService();
					actSvc.updatePublicStatus(actisp, mem_no, act_no);

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/act_f_listWhoAdd.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					
					String url="";
					if ("updatePublic".equals(action)){
						 url = "/front-end/activity/act_f_listWhoAdd.jsp";
					}
                  
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/act_f_listWhoAdd.jsp");
					failureView.forward(req, res);
				}
			 
		 } 
		  
		  //joinAct、queryOneJoinAct 的action來自act_f_listNew.jsp
		  //  報名                                                      查詢 
		 if ("joinAct".equals(action)||"queryOneJoinAct".equals(action)) { // 來自最新揪團報名
 
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("act_no");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入揪團編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/act_f_listNew.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					Integer act_no = null;
					try {
						act_no = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("揪團編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/act_f_listNew.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					ActivityService actSvc = new ActivityService();
					ActivityVO actVO = actSvc.getOneAct(act_no);
					//QueryactService qactSvc=new QueryactService();
					//List<QueryactVO> qlist= qactSvc.getOneNewAct(act_no);
					
					//從session的會員帳號查出mem_no
					HttpSession session=req.getSession();
					MemberVO memVO=(MemberVO)session.getAttribute("memberVO");
					Integer mem_no=memVO.getMem_no();
					
					//從行程明細算出天數
					StrokeDetailsService sdSvc=new StrokeDetailsService();
					int maxDay=sdSvc.getMaxDayByStrokeNo(actVO.getStroke_no());
					//查出該登入會員有沒有在該報名的揪團明細的資料
					JoinactivityService jactSvc=new JoinactivityService();
					JoinactivityVO jaVO=jactSvc.getIsINActByMem_no(act_no, mem_no);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/act_f_listNew.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("actVO", actVO); 
					//req.setAttribute("qlist", qlist);
					req.setAttribute("maxDay", maxDay);
					req.setAttribute("jaVO", jaVO);
					String url="";
					if ("joinAct".equals(action)){
						 url = "/front-end/activity/listQueryJoinAct.jsp";
					}
					if ("queryOneJoinAct".equals(action)){
						 url = "/front-end/activity/listOneQueryAct.jsp";
					}
//					else if("linkOneFromAct_index".equals(action)){
//						 url = "/back-end/activity/listOneActDetail.jsp";
//						 JoinactivityService jaSvc = new JoinactivityService();
//						 Set<JoinactivityVO> set = jaSvc.getJoinMemsByActno(act_no);
//						 session.setAttribute("listJoinMemsOnLink", set); 
//						 
//					}
					
					
					
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/act_f_listNew.jsp");
					failureView.forward(req, res);
				}
			}
		 
		//listAllMemInvitedAct.jsp 不參加揪團 
		 if ("refuseAct".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				List<String> successMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("successMsgs",successMsgs);
				String requestURL = req.getParameter("requestURL");
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("act_no");
					Integer act_no = new Integer(str);
					
					/***************************2.開始查詢資料*****************************************/
					HttpSession session=req.getSession();
					MemberVO memVO=(MemberVO)session.getAttribute("memberVO");					
					Integer mem_no=memVO.getMem_no();
					JoinactivityService jactSvc=new JoinactivityService();
					jactSvc.updateJoinAct(act_no, mem_no, 2, 0, 0.0, 0.0);
					successMsgs.add("成功拒絕此團");						
					req.setAttribute("success", 1);
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
		 
		 //來自listQueryJoinAct.jsp OR listAllMemInvitedAct.jsp 參加揪團 
		 if ("readyjoin".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				List<String> successMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("successMsgs",successMsgs);
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("act_no");
					Integer act_no = new Integer(str);
					/***************************2.開始查詢資料*****************************************/
					HttpSession session=req.getSession();
					MemberVO memVO=(MemberVO)session.getAttribute("memberVO");
					StrokeDetailsService sdSvc=new StrokeDetailsService();
					ActivityService actSvc = new ActivityService();
					ActivityVO actVO = actSvc.getOneAct(act_no); //取得該參加的actVO
					int maxDay=sdSvc.getMaxDayByStrokeNo(actVO.getStroke_no()); //取的該揪團的最大天數
					req.setAttribute("actVO", actVO); 
					req.setAttribute("maxDay", maxDay);
					Integer mem_no=memVO.getMem_no();
					
					//判斷日期有無衝突-------------------------
					int cntact=0;
					int cntjact=0;
					System.out.println("測試DATE起始:"+actVO.getAct_start_date().toString());
					System.out.println("測試DATE終止:"+actVO.getAct_end_date().toString());
					//判斷參加的日期與自己發起的團時間有無衝突
					cntact =actSvc.getChkActDate(actVO.getAct_start_date().toString(),actVO.getAct_end_date().toString(),mem_no);
					//判斷參加的日期與自己正在參加的團時間有無衝突
					cntjact=actSvc.getChkJoinActDate(actVO.getAct_start_date().toString(),actVO.getAct_end_date().toString(),mem_no,act_no);
					//今天日期
					java.util.Date du = new java.util.Date();
					 long long1 = du.getTime();
	                java.sql.Date today = new java.sql.Date(long1);
	                
	                if(today.compareTo(actVO.getAct_start_date())>=0){	
	                	errorMsgs.add("已過報名時間!!");
	                }
	                
					if(cntact!=0){
						errorMsgs.add("欲參加時段與已發起的揪團時間衝突");
					}
					
					if(cntjact!=0){
						errorMsgs.add("欲參加時段與其他參加揪團時間衝突");
					}
					
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/listQueryJoinAct.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					//判斷日期有無衝突-------------------------時間有誤
					
					//判斷會員是否是主揪
					JoinactivityService jactSvc=new JoinactivityService();
					ActivityVO actVO1 = actSvc.getOneActByMem_no(memVO.getMem_no(),act_no);
					JoinactivityVO jaVO=jactSvc.getIsINActByMem_no(act_no, mem_no);
					if(jactSvc.getCntMemsByAct(act_no)<actVO.getAct_joinlimit()){ //當揪團人數小於目前參加人數時才可加入
						if(actVO1!=null){ //查詢該團編號跟會員是否出現在activity table 過
							errorMsgs.add("你已經是主揪不能再加入");						
						}else{            //查詢該團編號跟會員沒出現在activity table
							
							
							if(jaVO==null){  //查該會員是否有參加此揪團
								jactSvc.addJoinAct(act_no, mem_no, 1, 0, 0.0, 0.0);
								successMsgs.add("成功加入此團");
								jaVO=jactSvc.getIsINActByMem_no(act_no, mem_no);
								req.setAttribute("jaVO", jaVO);
							}
							else{
								Integer joinStatus=jaVO.getJoinact_is_join();
								if(joinStatus==4){
									errorMsgs.add("你已經被拒絕入團");
								}else if(joinStatus==0||joinStatus==2||joinStatus==3){
										jactSvc.updateJoinAct(act_no, mem_no, 1, 0, 0.0, 0.0);
										successMsgs.add("成功加入此團");
								}else if(joinStatus==1){
										errorMsgs.add("你已經參加囉");
								}
								jaVO=jactSvc.getIsINActByMem_no(act_no, mem_no);
								req.setAttribute("jaVO", jaVO);
							}
						}
					}else{
						errorMsgs.add("已達人數上限囉");
					}
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/listQueryJoinAct.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					
					String url="";
					if ("readyjoin".equals(action)){
						 url = "/front-end/activity/listQueryJoinAct.jsp";
					}
					session.setAttribute("success",1);
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/listQueryJoinAct.jsp");
					failureView.forward(req, res);
				}
			}
		 
		//取消已報名的揪團
		 if("cancelOneJoinAct".equals(action)){
			 List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("act_no");
					Integer act_no = new Integer(str);
					String str1 = req.getParameter("mem_no");
					Integer mem_no = new Integer(str1);
					
					/***************************2.開始查詢資料*****************************************/
					JoinactivityService jactSvc=new JoinactivityService();
					jactSvc.updateJoinAct(act_no, mem_no, 2, 0, 0.0, 0.0);

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/listAllMemJoinAct.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					
					String url="";
					if ("cancelOneJoinAct".equals(action)){
						 url = "/front-end/activity/listAllMemJoinAct.jsp";
					}
                    req.setAttribute("test",1);
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage()); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/listAllMemJoinAct.jsp");
					failureView.forward(req, res);
				}
			 
		 }
		
		//------------------------------------後端導頁區--------------------------------------
		 
		if("query".equals(action)){
			fwUrl="/back-end/activity/act_index.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
		}
		
		
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
		//回後端index
		if("backIndex".equals(action)){
			linkUrl = req.getContextPath()+"/back-end/activity/act_index.jsp";
			HttpSession session=req.getSession();
			session.removeAttribute("actVO");
			session.removeAttribute("listJoinMemsOnLink");
			res.sendRedirect(linkUrl);
		}
		//連結到新增
		if("creAct".equals(action)){
			
			fwUrl = "/back-end/activity/addAct.jsp";
			RequestDispatcher fwView = req.getRequestDispatcher(fwUrl);
			fwView.forward(req, res);
			
		}
		//------------------------------------後端--------------------------------------
		
		if ("getOne_For_Display".equals(action)||"linkOneFromAct_index".equals(action)||"linkOneFromComQ".equals(action)) { // 來自select_page.jsp的請求
			String url="/front-end/activity/act_f_listALLcomQuery.jsp";
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("act_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ("linkOneFromComQ".equals(action)){
						RequestDispatcher failureView = req
								.getRequestDispatcher(url);
						failureView.forward(req, res);
					}else{
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/activity/act_index.jsp");
						failureView.forward(req, res);	
					}
					return;//程式中斷
				}
				
				Integer act_no = null;
				try {
					act_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("揪團編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ("linkOneFromComQ".equals(action)){
						RequestDispatcher failureView = req
								.getRequestDispatcher(url);
						failureView.forward(req, res);
					}else{
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/activity/act_index.jsp");
						failureView.forward(req, res);	
					}
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActivityService actSvc = new ActivityService();
				ActivityVO actVO = actSvc.getOneAct(act_no);
				if (actVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ("linkOneFromComQ".equals(action)){
						RequestDispatcher failureView = req
								.getRequestDispatcher(url);
						failureView.forward(req, res);
					}else{
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/activity/act_index.jsp");
						failureView.forward(req, res);	
					}
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫取出的empVO物件,存入req
				
				if ("getOne_For_Display".equals(action)){
					 url = "/back-end/activity/listOneAct.jsp";
				}
				else if("linkOneFromComQ".equals(action)){ //前端查詢的連結
					 url = "/front-end/activity/listOneActDetail.jsp";
					 JoinactivityService jaSvc = new JoinactivityService();
					 Set<JoinactivityVO> set = jaSvc.getJoinMemsByActno(act_no);
					 HttpSession session=req.getSession();
					 session.setAttribute("listJoinMemsOnLink", set); 
				}else if("linkOneFromAct_index".equals(action)){
					 url = "/back-end/activity/listOneActDetail.jsp";
					 JoinactivityService jaSvc = new JoinactivityService();
					 Set<JoinactivityVO> set = jaSvc.getJoinMemsByActno(act_no);
					 HttpSession session=req.getSession();
					 session.setAttribute("listJoinMemsOnLink", set); 
				}
				
	
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				if ("linkOneFromComQ".equals(action)){
					url="/front-end/activity/act_f_listALLcomQuery.jsp";
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
				}else{
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/activity/act_index.jsp");
					failureView.forward(req, res);	
				}
			}
		}
		//------來自前後端單一修改
		if ("getOne_For_Update".equals(action)||"getOne_For_Update_f".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer act_no = new Integer(req.getParameter("act_no"));
				
				/***************************2.開始查詢資料****************************************/
				ActivityService actSvc = new ActivityService();
				ActivityVO actVO = actSvc.getOneAct(act_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actVO", actVO);         // 資料庫取出的empVO物件,存入req
				String url="";
				if("getOne_For_Update".equals(action)){
					url="/back-end/activity/update_act_input.jsp";
				}
				if("getOne_For_Update_f".equals(action)){
					url="/front-end/activity/update_act_input.jsp";
				}

				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				String errUrl="";
				if("getOne_For_Update".equals(action)){
					errUrl="/back-end/activity/act_index.jsp";           
				}
				if("getOne_For_Update_f".equals(action)){
					errUrl="/front-end/activity/act_f_listWhoAdd.jsp";
				}
				RequestDispatcher failureView = req
						.getRequestDispatcher(errUrl);
				failureView.forward(req, res);
			}
		}
		//--end getOne_For_Update 
		
		if ("update".equals(action)||"fupdate".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			ActivityService actSvc = new ActivityService();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer act_no = new Integer(req.getParameter("act_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer loc_no = new Integer(req.getParameter("loc_no").trim());
				String  s=req.getParameter("stroke_no").trim();
				Integer stroke_no;
				if(s.length()==0||s.equals("0")||s==null){
					stroke_no=null;
				}
				else{
					stroke_no = new Integer(req.getParameter("stroke_no").trim());
				}
				String act_name = req.getParameter("act_name").trim();
				if (act_name == null || act_name.length() == 0) {
					errorMsgs.add("請輸入揪團姓名");
				}
				String act_loc = req.getParameter("act_loc").trim();
				if (act_loc == null || act_loc.length() == 0) {
					errorMsgs.add("請輸入揪團地點");
				}
				byte[] act_act_route=null;
				byte[] act_alti=null;
				java.sql.Date act_start_date = null;
				try {
					String startStr=req.getParameter("act_start_date").trim();
					startStr=startStr.replace('/', '-');
					act_start_date = java.sql.Date.valueOf(startStr);
				} catch (IllegalArgumentException e) {
					act_start_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				
				java.sql.Date act_end_date = null;
				try {
					String endStr=req.getParameter("act_end_date").trim();
					endStr=endStr.replace('/', '-');
					act_end_date = java.sql.Date.valueOf(endStr);
				} catch (IllegalArgumentException e) {
					act_end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				
				String act_exp = req.getParameter("act_exp").trim();
				Double act_km=0.0;
				Integer act_joinlimit=0;
				String joinlimitregex="\\d{1,3}";
				String kmregex="\\d{1,3}[\\.]{0,1}\\d{0,2}";
				Integer act_is_public = new Integer(req.getParameter("act_is_public").trim());
				if (req.getParameter("act_km").trim() == null || req.getParameter("act_km").trim().length() == 0) {
					errorMsgs.add("請輸入公里數");
					
				}else{
					if( req.getParameter("act_km").trim().matches(kmregex)){
						   act_km = new Double(req.getParameter("act_km").trim());
					}else{
						errorMsgs.add("公里數格式:XXX.YY");	
					}	
				}
				
				if (req.getParameter("act_joinlimit").trim() == null ||req.getParameter("act_joinlimit").trim().length() == 0||
						req.getParameter("act_joinlimit").trim() =="0") {
					errorMsgs.add("請輸入揪團人數");
				}else{
					if(req.getParameter("act_joinlimit").trim().matches(joinlimitregex)){
						act_joinlimit = new Integer(req.getParameter("act_joinlimit").trim());
					}
					else{
					 errorMsgs.add("人數上限三位數");	
					}
				}
				
				ActivityVO actVO = new ActivityVO();
				actVO.setAct_no(act_no);
				actVO.setMem_no(mem_no);
				actVO.setLoc_no(loc_no);
				if(stroke_no!=null){
					actVO.setStroke_no(stroke_no);
				}
				actVO.setAct_name(act_name);
				actVO.setAct_loc(act_loc);
				actVO.setAct_start_date(act_start_date);
				actVO.setAct_end_date(act_end_date);
				actVO.setAct_exp(act_exp);
				actVO.setAct_is_public(act_is_public);
				actVO.setAct_km(act_km);
				actVO.setAct_joinlimit(act_joinlimit);
				
				//讀取上傳圖片
				Part part=req.getPart("act_photo");
				InputStream in =part.getInputStream();
				//byte[] act_photo=new byte[in.available()];
				//in.read(act_photo);
				//in.close();
				
				byte[] act_photo=null;
				
				if(getFileNameFromPart(part)!=null){
					act_photo=new byte[in.available()];
					in.read(act_photo);
				}else{
					ActivityVO actVo=actSvc.getOneAct(act_no);
					act_photo=actVo.getAct_photo();
					if(act_photo!=null){
						in.read(act_photo);
					}
				}
				
				in.close();
				
				actVO.setAct_photo(act_photo);
				actVO.setAct_act_route(act_act_route);
				actVO.setAct_alti(act_alti);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
					String failUrl="";
					if ("update".equals(action)){
						failUrl="/back-end/activity/update_act_input.jsp";
					}
					if ("fupdate".equals(action)){
						failUrl="/front-end/activity/update_act_input.jsp";
					}
					RequestDispatcher failureView = req
							.getRequestDispatcher(failUrl);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				
				if(stroke_no==null){
					actVO = actSvc.updateNoStrokeAct(act_no, mem_no, loc_no, act_name, act_loc, act_start_date, act_end_date, act_exp,
							act_photo, act_is_public, act_act_route, act_alti, act_km, act_joinlimit);
				}    
				else{
					actVO = actSvc.updateAct(act_no,mem_no, loc_no, stroke_no, act_name, act_loc,act_start_date, act_end_date,act_exp,
							act_photo,act_is_public,act_act_route,act_alti,act_km,act_joinlimit);
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url ="";
				if ("update".equals(action)){
					url="/back-end/activity/act_index.jsp";
				}
				if ("fupdate".equals(action)){
					url="/front-end/activity/act_f_listWhoAdd.jsp";
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String failUrl="";
				if ("update".equals(action)){
					failUrl="/back-end/activity/update_act_input.jsp";
				}
				if ("fupdate".equals(action)){
					failUrl="/front-end/activity/update_act_input.jsp";
				}
				RequestDispatcher failureView = req
						.getRequestDispatcher(failUrl);
				
				failureView.forward(req, res);
			}
		}
        //--end update 
		
		 if ("insert".equals(action)||"addAct".equals(action)) { // 來自addEmp.jsp的請求  
			    List<String> successMsgs = new LinkedList<String>();
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("successMsgs", successMsgs);
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					//Integer act_no = new Integer(req.getParameter("act_no").trim());
					//Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					Integer loc_no = new Integer(req.getParameter("loc_no").trim());
					String  s=req.getParameter("stroke_no").trim();
					Integer stroke_no;
					if(s.length()==0||s.equals("")||s==null||s.equals("0")){
						stroke_no=null;
					}
					else{
						stroke_no = new Integer(req.getParameter("stroke_no").trim());
					}
					Integer mem_no = new Integer(req.getParameter("mem_no").trim()); 
					String act_name = req.getParameter("act_name").trim();
					if (act_name == null || act_name.length() == 0) {
						errorMsgs.add("請輸入揪團姓名");
					}
					String act_loc = req.getParameter("act_loc").trim();
					if (act_loc == null || act_loc.length() == 0) {
						errorMsgs.add("請輸入揪團地點");
					}
					
					byte[] act_act_route=null;
					byte[] act_alti=null;
					
					java.sql.Date act_start_date = null;
					try {
						String startStr=req.getParameter("act_start_date").trim();
						startStr=startStr.replace('/', '-');
						System.out.println("startStr:"+startStr);
						act_start_date = java.sql.Date.valueOf(startStr);
					} catch (IllegalArgumentException e) {
						act_start_date=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入開始日期!");
					}
					
					java.sql.Date act_end_date = null;
					try {
						String endStr=req.getParameter("act_end_date").trim();
						endStr=endStr.replace('/', '-');
						System.out.println("endStr:"+endStr);
						act_end_date = java.sql.Date.valueOf(endStr);
					} catch (IllegalArgumentException e) {
						act_end_date=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入結束日期!");
					}
					
					String act_exp = req.getParameter("act_exp").trim();
					Double act_km=0.0;
					Integer act_joinlimit=0;
					String joinlimitregex="\\d{1,3}";
					String kmregex="\\d{1,3}[\\.]{0,1}\\d{0,2}";
					Integer act_is_public = new Integer(req.getParameter("act_is_public").trim());
					if (req.getParameter("act_km").trim() == null || req.getParameter("act_km").trim().length() == 0) {
						errorMsgs.add("請輸入公里數");
						
					}else{
						if( req.getParameter("act_km").trim().matches(kmregex)){
							   act_km = new Double(req.getParameter("act_km").trim());
						}else{
							errorMsgs.add("公里數格式:XXX.YY");	
						}	
					}
					
					if (req.getParameter("act_joinlimit").trim() == null ||req.getParameter("act_joinlimit").trim().length() == 0||
							req.getParameter("act_joinlimit").trim() =="0") {
						errorMsgs.add("請輸入揪團人數");
					}else{
						if(req.getParameter("act_joinlimit").trim().matches(joinlimitregex)){
							act_joinlimit = new Integer(req.getParameter("act_joinlimit").trim());
						}
						else{
						 errorMsgs.add("人數上限三位數");	
						}
					}
					 
					
					ActivityVO actVO = new ActivityVO();
					//actVO.setAct_no(act_no);
					actVO.setMem_no(mem_no);
					actVO.setLoc_no(loc_no);
					actVO.setStroke_no(stroke_no);
					actVO.setAct_name(act_name);
					actVO.setAct_loc(act_loc);
					actVO.setAct_start_date(act_start_date);
					actVO.setAct_end_date(act_end_date);
					//actVO.setAct_start_date(java.sql.Date.valueOf("2016-05-28"));
					//actVO.setAct_end_date(java.sql.Date.valueOf("2016-06-28"));
					actVO.setAct_exp(act_exp);
					actVO.setAct_is_public(act_is_public);
					actVO.setAct_km(act_km);
					actVO.setAct_joinlimit(act_joinlimit);
					
					//讀取上傳圖片
					Part part=req.getPart("act_photo");
					InputStream in =part.getInputStream();
					byte[] act_photo=new byte[in.available()];
					in.read(act_photo);
					in.close();
					
					actVO.setAct_photo(act_photo);
					actVO.setAct_act_route(act_act_route);
					actVO.setAct_alti(act_alti);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/activity/addAct.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					ActivityService actSvc = new ActivityService();
					if(stroke_no==null){
						actVO = actSvc.addActNoStroke(mem_no,loc_no, act_name, act_loc, act_start_date, act_end_date, act_exp, act_photo, act_is_public, act_act_route, act_alti, act_km, act_joinlimit);
					}    
					else{
						actVO = actSvc.addAct(mem_no,loc_no, stroke_no, act_name, act_loc, act_start_date, act_end_date, act_exp, act_photo, act_is_public, act_act_route, act_alti, act_km, act_joinlimit);
					}
					
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url="";
					if ("insert".equals(action)){
						url ="/back-end/activity/listAllAct.jsp";
					}
					if ("addAct".equals(action)) {
						url ="/front-end/activity/act_f_listNew.jsp";
					}
				//	successMsgs.add("新增成功!");
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);	
					
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/addAct.jsp");
					failureView.forward(req, res);
				}
			}
		    //end insert 
		 
		 if ("delete".equals(action)||"cancelOneWhoaddAct_f".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				List<String> successMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("successMsgs", successMsgs);
				try {
					/***************************1.接收請求參數***************************************/
					Integer act_no = new Integer(req.getParameter("act_no"));
					
					
					/***************************2.開始刪除資料***************************************/
					ActivityService actSvc = new ActivityService();
					JoinactivityService jactSvc=new JoinactivityService();
					ReportcollectService rcSvc=new ReportcollectService();
					if ("delete".equals(action)){
					rcSvc.deleteAct(act_no); 	
					jactSvc.deleteJoinAct(act_no);	
					actSvc.deleteAct(act_no);
						}
					if ("cancelOneWhoaddAct_f".equals(action)){
						List<JoinactivityVO> list=jactSvc.getJoinSureMemsByAct(act_no);
						MailService mail=new MailService();
						ActivityVO actVO=actSvc.getOneAct(act_no);
						String tilte=actVO.getAct_start_date()+"至"+actVO.getAct_end_date()+"的"+actVO.getAct_name()+"已取消!!!";
						String messageText="您好，於"+tilte+"造成行程規劃上不便，敬請見諒，請選擇其他揪團。";
						 for (int i = 0 ; i < list.size() ; i++){
							 JoinactivityVO jactVO = list.get(i);
						     System.out.println(jactVO.getMem_no());
						     MemberService memSvc=new MemberService();
						     MemberVO memVO=memSvc.getOneMember(jactVO.getMem_no());
						     mail.sendMail(memVO.getMem_email(),tilte,memVO.getMem_name()+messageText);
						 }
						rcSvc.deleteAct(act_no); 
						jactSvc.deleteJoinAct(act_no);	
						actSvc.deleteAct(act_no);
						
						}
					
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
					
					String url ="";
					if ("delete".equals(action)){
						url ="/back-end/activity/act_index.jsp";
						successMsgs.add("刪除資料成功!!!");
					}
					if ("cancelOneWhoaddAct_f".equals(action)){
						url ="/front-end/activity/act_f_listWhoAdd_cancel.jsp";
					}
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					String furl ="";
					if ("delete".equals(action)){
						furl ="/back-end/activity/act_index.jsp";
					}
					if ("cancelOneWhoaddAct_f".equals(action)){
						furl ="/front-end/activity/act_f_listWhoAdd_cancel.jsp";
					}
					RequestDispatcher failureView = req
							.getRequestDispatcher(furl);
					failureView.forward(req, res);
				}
			}
		 //end del
	 
	} 
	
	
	
	//end doPost
	 public String getFileNameFromPart(Part part){
		 String header=part.getHeader("content-disposition");
		 String filename=new File(header.substring(header.lastIndexOf("=")+2,header.length()-1)).getName();
		 if(filename.length()==0){
			 return null;
		 }
		 return filename;
	 }
	
}
