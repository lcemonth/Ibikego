<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ActivityService actSvc = new ActivityService();
    List<ActivityVO> list = actSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"/>
<title>所有員工資料 - listAllAct.jsp</title>
</head>
<body bgcolor='white'>


<div class="navbar">
  <div class="button">
    <i class="fa fa-globe"></i>
    <span class="button__badge">2</span>
  </div>
  <div class="button">
    <i class="fa fa-comments"></i>
    <span class="button__badge">4</span>
  </div>
</div>
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
			<td>${actVO.act_exp}</td>
			<td><img src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
            <td><img src="<%=request.getContextPath()%>/activity/activityRoute.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
            <td><img src="<%=request.getContextPath()%>/activity/activityAlti.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
			<td>${actVO.act_km}</td>
			<td>${actVO.act_joinlimit}</td>
			<td>${actVO.act_is_public}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="act_no" value="${actVO.act_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="act_no" value="${actVO.act_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
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

</body>
</html>
