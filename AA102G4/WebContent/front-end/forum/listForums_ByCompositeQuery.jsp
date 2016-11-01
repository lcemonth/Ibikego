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

<!-- ************************************************************************************************************************ -->
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<style type="text/css">
		
/* 		background-color:#01FF93; */
/* .green{width: 100%; height: 1300px; background-color: #01FF93; */
body {padding-top:50px;height:100px;color: #000; background-color:#FFFFFF; }
	.green{width: 100%; height: 100px; background-color: #FFFFFF;}
/* img { width: 100%; } */
	.thumbnail{background-color: #fff; width: 280px; height: 450px;}
	.i{background-color: #1D7F56;}
	.box1{width: 280px;height: 480px; background-color: #FFF;}
	.col-sm-8{
		width: 600px;
		height: 100px;
		background-color: #017F4A;
	}

div#box2 {  
   	
	width:120px;height:50px;
/*     background:#BBFFEE; */
    line-height:50px;
    
    
}

.table tr th {
	text-align: center;
}
		</style>
	</head>
<body bgcolor='white'>

	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
	
<!-- 		<div class="row"> -->
<%-- 			<jsp:include page="/front-end/left.jsp"></jsp:include> --%>
	
		

		<c:if test="${not empty errorMsgs}">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>


		<h3>揪團騎Bike討論區</h3>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?action=add_front">

		
		<c:if test="${not empty memberVO}">
			<form>
				<input type="button" id="opener" class="btn btn-primary" value="發文" onclick="location.href='<%=request.getContextPath()%>/front-end/forum/Forum_add.jsp'">
			</form>
		</c:if>

		<c:if test="${empty memberVO}">
			<form>
				<input type="button" id="opener" class="btn btn-primary" value="發文" onclick="location.href='<%=request.getContextPath()%>/front-end/Login/Login.jsp'">
			</form>
		</c:if>		 
      
     
     
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do" class="navbar-form navbar-left" role="search">
		<div class="form-group">
			<b>標題:</b> <input type="text" class="form-control" name="forum_title"
				placeholder="請輸入關鍵字">
		</div>
		<!-- 			<input type="te	xt" name="forum_title" value=""><br> -->
		<button type="submit" class="btn btn-primary">搜尋</button>
		<input type="hidden" name="action"
			value="listForums_ByCompositeQuery_Front">
		</form>
<table class="table table-bordered table-hover table-striped">
	<tr>
	<th>發文者</th>
	<th>標題</th>
	<th>日期</th>
	</tr>
		<c:forEach var="forumVO" items="${listForums_ByCompositeQuery}" >

					<tr align='center' valign='middle'>
						<div class="post-title_block">
							<td>
							<div id="box2">
							<c:forEach var="memberVO" items="${memSvc.all}">
								<c:if test="${forumVO.mem_no==memberVO.mem_no}">
											${memberVO.mem_name}
										</c:if>
							</c:forEach>
							</div>
							</td>
							
							<td>
							<div id="box">
								<a href="<%=request.getContextPath()%>/forum.do?action=getOne_For_Select_front&forum_no=${forumVO.forum_no}">${forumVO.forum_title}</a>
							</div>
							</td>
							<td>
							<div id="box2">
							${forumVO.forum_cretime}
							</div>
							</td>
						</tr>
					
			
		</c:forEach>
			
	</table>
	


					

</div>
	<!-- ******************內容***************************** -->						

</body>
</html>