<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.message.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	MessageService messageSvc = new MessageService();
    List<MessageVO> list = messageSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
	<head>
		<title>所有留言 - listAllMessage.jsp</title>
	</head>
	<body bgcolor='white'>
		<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
		<table border='1' cellpadding='5' cellspacing='0' width='800'>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td>
					<h3>所有留言 - listAllMessage.jsp</h3>
					<a href="<%=request.getContextPath()%>/message/message.do?action=home"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
				<th>留言編號</th>
				<th>日誌</th>
				<th>留言者</th>
				<th>內容</th>
				<th>留言時間</th>
			</tr>
			<%@ include file="page1.file" %> 
			<c:forEach var="messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr align='center' valign='middle'>
					<td>${messageVO.mes_no}</td>
					<td>${messageVO.blog_no}</td>
					<td>${messageVO.mem_no}</td>
					<td>${messageVO.mes_content}</td>
					<td>${messageVO.mes_cre}</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do">
						    <input type="submit" value="修改">
						    <input type="hidden" name="mes_no" value="${messageVO.mes_no}">
						    <input type="hidden" name="action"	value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do">
						    <input type="submit" value="刪除">
						    <input type="hidden" name="mes_no" value="${messageVO.mes_no}">
						    <input type="hidden" name="action"value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file" %>
	</body>
</html>