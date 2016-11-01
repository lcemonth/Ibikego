<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.message.model.*"%>
<%
MessageVO messageVO = (MessageVO) request.getAttribute("messageVO"); //MessageServlet.java (Concroller), 存入req的messageVO物件 (包括幫忙取出的messageVO, 也包括輸入資料錯誤時的messageVO物件)
%>
<html>
<head>
<title>留言資料修改 - update_message_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料修改 - update_message_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/message/message.do?action=home"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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
		<td>留言編號:<font color=red><b>*</b></font></td>
		<td><%=messageVO.getMes_no()%></td>
		<td><input type="hidden" name="mes_no" size="45" value="<%=messageVO.getMes_no()%>" /></td>
	</tr>
	<tr>
		<td>日誌:<font color=red><b>*</b></font></td>
		<td><%=messageVO.getBlog_no()%>
		<td><input type="hidden" name="blog_no" size="45" value="<%=messageVO.getBlog_no()%>" /></td>
	</tr>
	<tr>
		<td>作者:<font color=red><b>*</b></font></td>
		<td><%=messageVO.getMem_no()%>
		<td><input type="hidden" name="mem_no" size="45" value="<%=messageVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>留言內容:</td>
		<td><input type="TEXT" name="mes_content" size="45" value="<%=messageVO.getMes_content()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>最後編輯:</td>
		<td><input readonly type="TEXT" name="mes_cre" size="45" value="<%=date_SQL%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
