<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ActivityService actSvc = new ActivityService();
    List<ActivityVO> list = actSvc.getAllact_recent();
    pageContext.setAttribute("list",list);
    
%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">

</head>
<body>
	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
<div class="row">
		<div class="col-md-10">
			<div class="row">
				<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
				<div class="col-md-10">
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有揪團資料 - listAllAct.jsp</h3>
		<a href='<%=request.getContextPath()%>/activity/activity.do?action=backHome'><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>地區編號</th>
		<th>行程編號</th>
		<th>揪團名稱</th>
		<th>揪團地點</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>揪團內容</th>
		<th>揪團主題照片</th>
		<th>揪團路線圖</th>
		<th>揪團經緯圖</th>
		<th>公里數</th>
		<th>人數</th>
		<th>揪團公開</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${actVO.act_no}</td>
			<td>${actVO.mem_no}</td>
			<td>${actVO.loc_no}</td>
			<td>${actVO.stroke_no}</td>
			<td>${actVO.act_name}</td>
			<td>${actVO.act_loc}</td>
			<td>${actVO.act_start_date}</td>
			<td>${actVO.act_end_date}</td>
			<td>${(actVO.act_exp==null or actVO.act_exp=="" or actVO.act_exp=="null")? "無內容" : actVO.act_exp }</td>
			<td><img src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
            <td><img src="<%=request.getContextPath()%>/activity/activityRoute.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
            <td><img src="<%=request.getContextPath()%>/activity/activityAlti.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
			<td>${actVO.act_km}</td>
			<td>${actVO.act_joinlimit}</td>
			<td>${actVO.act_is_public}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/joinActivity/joinActivity.do">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="act_no" value="${actVO.act_no}">
			    <input type="hidden" name="action" value="listMems_ByActno_fromActPage">
			</td></FORM>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<%if (request.getAttribute("listJoinMems")!=null){%>
      <jsp:include page="/back-end/joinActivity/listJoinMems_ByActno.jsp"/>
<%} %>
 </div>
			</div>
		</div>
	</div>
</body>
</html>
