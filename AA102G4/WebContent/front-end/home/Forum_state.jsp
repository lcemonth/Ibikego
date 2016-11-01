<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Controller), 存入req的memberVO物件
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/index.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/home.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<style type="text/css">
			body{
				background-color: #87CEEB;
			}
			.col-xs-2{
				position: relative;
				top: 50px;
			}
			.a1{
				background-color: #67cad2; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:176px;/*右邊*/
			}
			.a1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
			}
			.b1{
				background-color: #f86e8f; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:96px;/*右邊*/
			}
			.b1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
			}
			.c1{
				background-color: #ffc15e; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:16px;/*右邊*/
			}
			.c1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
			}
			.table-hover{
				width: 900px;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="container">
			<jsp:include page="homeTop.jsp"></jsp:include>
			<!-- 下面	 -->
			<div class="row">
				<jsp:include page="homeFunction.jsp"></jsp:include>
				<div class="row">
					<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
					<jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
					<table class="table table-hover">
	   					<thead>
							<tr>
								<th>標題</th>
								<th>日期</th>
								<th>取消收藏</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ReportcollectVO" items="${list}">
								<c:if test="${ReportcollectVO.forum_no!=0}">
									<tr align='center' valign='middle'>
										<td>
											<c:forEach var="forumVO" items="${forumSvc.all}">
												<c:if test="${ReportcollectVO.forum_no==forumVO.forum_no}">
													<div id="box">
														<a href="<%=request.getContextPath()%>/forum.do?action=getOne_For_Select_front&forum_no=${forumVO.forum_no}">${forumVO.forum_title}</a>
													</div>
												</c:if>
											</c:forEach>
										</td>
										<td>
											<c:forEach var="forumVO" items="${forumSvc.all}">
												<c:if test="${ReportcollectVO.forum_no==forumVO.forum_no}">
													<div id="box2">${forumVO.forum_cretime}</div>
												</c:if>
											</c:forEach>				
										</td>
										<td>	
											<c:forEach var="forumVO" items="${forumSvc.all}">
												<c:if test="${(ReportcollectVO.forum_no == forumVO.forum_no) && (ReportcollectVO.mem_no == memberVO.mem_no) && ReportcollectVO.rep_rel == 1}">
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?update_status_B">
														<input type="submit" class="btn btn-sm btn-primary" value="取消收藏">
														<input type="hidden" name="rc_no" value="${ReportcollectVO.rc_no}">
														<input type="hidden" name="mem_no" value="${ReportcollectVO.mem_no}">
														<input type="hidden" name="forum_no" value="${ReportcollectVO.forum_no}">
														<input type="hidden" name="rc_rep_handle" value="3">
														<input type="hidden" name="rc_col_status" value="1">
														<input type="hidden" name="rep_rel" value="1">
														<input type="hidden" name="action" value="update_status_B">
													</FORM>
												</c:if>
											</c:forEach>		
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>	
				</div>
			</div>
		</div>
	</body>
</html>