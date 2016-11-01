<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //MemberServlet.java(Controller), 存入req的memberVO物件
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list",list);
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
					<jsp:useBean id="relationshipSvc" scope="page" class="com.relationship.model.RelationshipService" />
					<div>
					    <c:if test="${not empty errorMsgs}">
							<font color='red'>請修正以下錯誤:
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ul>
							</font>
						</c:if>
					</div>
					<table class="table table-hover">
	   					<thead>
							<tr>
								<th>大頭貼</th>
								<th>姓名(暱稱)</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="blacklist" items="${list}">
								<c:if test="${(blacklist.mem_no!=memberVO.mem_no)&&(blacklist.mem_del!=1)}">
									<c:if test="${relationshipSvc.checkBlacklist(memberVO.mem_no,blacklist.mem_no)==1}">
										<tr align='center' valign='middle'>
											<td><img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${blacklist.mem_no}" height=80/></td>
											<td>${blacklist.mem_name}</td>
											<td>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/relationship.do">
												     <input type="submit" class="btn btn-primary" value="取消封鎖">
												     <input type="hidden" name="mem_no_main" value="${memberVO.mem_no}">
												     <input type="hidden" name="rel_mem_no" value="${blacklist.mem_no}">
												     <input type="hidden" name="action"	value="noBlacklist">
												</FORM>
											</td>
										</tr>
									</c:if>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>