<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.travelScore.model.*"%>
<%
TravelScoreVO travelScoreVO = (TravelScoreVO) request.getAttribute("travelScoreVO");
%>
<html>
	<head>
		<title>新增評分  - addBlogScore.jsp</title>
	</head>
	<link rel="stylesheet" type="text/css" href="js/calendar.css">
	<script language="JavaScript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
	<div id="popupcalendar" class="text"></div>
	<body bgcolor='white'>
		<table border='1' cellpadding='5' cellspacing='0' width='400'>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td>
					<h3>新增評分  - addBlogScore.jsp</h3>
				</td>
				<td>
		   			<a href="<%=request.getContextPath()%>/travelScore/travelScore.do?action=home"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="100" border="1">回首頁</a>
	    		</td>
			</tr>
		</table>
		<h3>評分資料:</h3>
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
	
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travelScore/travelScore.do" name="form1">
			<table border="0">
				<tr>
					<td>旅遊點:</td>
					<td><input type="TEXT" name="tra_no" size="45" value="<%= (travelScoreVO==null)? "112":travelScoreVO.getTra_no()%>"/></td>
				</tr>
				<tr>
					<td>評分者:</td>
					<td><input type="TEXT" name="mem_no" size="45" value="<%= (travelScoreVO==null)? "117":travelScoreVO.getMem_no()%>"/></td>
				</tr>
				<tr>
					<td>分數:</td>
					<td><input type="TEXT" name="tra_score" value="<%= (travelScoreVO==null)? "10":travelScoreVO.getTra_score()%>"/></td>
				</tr>
				<tr>
					<td>評分狀態:</td>
					<td><input type="TEXT" name="tra_score_status" size="45" value="<%= (travelScoreVO==null)? "1":travelScoreVO.getTra_score_status()%>" /></td>
				</tr>
			</table>
			<br>
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</FORM>
	</body>
</html>