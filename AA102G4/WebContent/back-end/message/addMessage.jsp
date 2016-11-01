<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.message.model.*"%>
<%
MessageVO messageVO = (MessageVO) request.getAttribute("messageVO");
%>
<html>
	<head>
		<title>新增留言  - addMessage.jsp</title>
	</head>
	<link rel="stylesheet" type="text/css" href="js/calendar.css">
	<script language="JavaScript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
	<div id="popupcalendar" class="text"></div>
	<body bgcolor='white'>
		<table border='1' cellpadding='5' cellspacing='0' width='400'>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td>
					<h3>新增留言  - addMessage.jsp</h3>
				</td>
				<td>
		   			<a href="<%=request.getContextPath()%>/message/message.do?action=home"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="100" border="1">回首頁</a>
	    		</td>
			</tr>
		</table>
		<h3>會員資料:</h3>
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
	
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do" name="form1">
			<table border="0">
				<tr>
					<td>日誌:</td>
					<td><input type="TEXT" name="blog_no" size="45" value="<%= (messageVO==null)? "115":messageVO.getBlog_no()%>"/></td>
				</tr>
				<tr>
					<td>留言者:</td>
					<td><input type="TEXT" name="mem_no" size="45" value="<%= (messageVO==null)? "116":messageVO.getMem_no()%>"/></td>
				</tr>
				<tr>
					<td>內容:</td>
					<td><input type="TEXT" name="mes_content" value="<%= (messageVO==null)? "好想去玩!":messageVO.getMes_content()%>"/></td>
				</tr>
				<tr>
					<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
					<td>留言時間:</td>
					<td><input class="TEXT" size="45" readonly type="text" name="mes_cre" value="<%=date_SQL%>"></td>
				</tr>
			</table>
			<br>
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</FORM>
	</body>
</html>