<!-- 開頭加入各jsp網頁的jsp等程式******************************************************************************************* -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumresponse.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="listForums_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<%
	ForumService forumSvc = new ForumService();
	List<ForumVO> list = forumSvc.getAll();
	pageContext.setAttribute("list", list);
%>



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
.col-md-12 {
	/*width: 100%;
				background-color: #fdf;*/
	text-align: center;
}

.col-md-2 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 100px;
	text-align: center;
}

.col-md-10 {
	/*width: 100%;
				background-color: #fdf;*/
	
}

.table tr th {
	text-align: center;
}

.empadd {
	text-align: left;
}

.empsearch {
	width: 100% text-align:center;
}
</style>
</head>
<body>
	<div class="row">
		<jsp:include page="/back-end/top.jsp"></jsp:include>
	</div>
	<div class="row">
		<jsp:include page="/back-end/left.jsp"></jsp:include>

		<div class="col-md-10">

			<c:if test="${not empty errorMsgs}">
				<font color='red'>請修正以下錯誤:
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</font>
			</c:if>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td>
					<h3>揪團騎Bike討論區</h3>


				</td>
			</tr>
			<li>
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do"
					class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<b>標題:</b> <input type="text" class="form-control"
							name="forum_title" placeholder="請輸入關鍵字">
					</div>
					<!-- 			<input type="te	xt" name="forum_title" value=""><br> -->
					<button type="submit" class="btn btn-primary">搜尋</button>
					<input type="hidden" name="action"
						value="listForums_ByCompositeQuery_Back">
				</form></li>
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th>討論區編號</th>
					<th>討論區會員編號</th>
					<th>討論區標題</th>
					<th>發文日期</th>
					<th>刪除</th>
				</tr>
				<c:forEach var="forumVO" items="${listForums_ByCompositeQuery}" >

					<tr align='center' valign='middle'>
						<div class="post-title_block">
							<td>${forumVO.forum_no}</td>
							<td><c:forEach var="memberVO" items="${memSvc.all}">
									<c:if test="${forumVO.mem_no==memberVO.mem_no}">
									${memberVO.mem_name}
								</c:if>
								</c:forEach></td>
							<!--超連結取值 -->
							<td><a
								href="<%=request.getContextPath()%>/forum.do?action=getOne_For_Select_back&forum_no=${forumVO.forum_no}">${forumVO.forum_title}</a></td>
							<td>${forumVO.forum_cretime}</td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/forum.do?action=delete_back">
									<input type="submit" value="刪除"> <input type="hidden"
										name=forum_no value="${forumVO.forum_no}"> <input
										type="hidden" name="action" value="delete_back">
								</FORM>
							</td>
					</tr>
				</c:forEach>

			</table>
		

		</div>
</body>
</html>