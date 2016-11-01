<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogScore.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	BlogScoreService blogScoreSvc = new BlogScoreService();
    List<BlogScoreVO> list = blogScoreSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
	<head>
		<title>所有評分 - listAllBlogScore.jsp</title>
	</head>
	<body bgcolor='white'>
		<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
		<table border='1' cellpadding='5' cellspacing='0' width='800'>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td>
					<h3>所有評分 - listAllBlogScore.jsp</h3>
					<a href="<%=request.getContextPath()%>/blogScore/blogScore.do?action=home"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
				<th>日誌</th>
				<th>評分者</th>
				<th>分數</th>
				<th>評分狀態</th>
			</tr>
			<%@ include file="page1.file" %> 
			<c:forEach var="blogScoreVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr align='center' valign='middle'>
					<td>${blogScoreVO.blog_no}</td>
					<td>${blogScoreVO.mem_no}</td>
					<td>${blogScoreVO.blog_score}</td>
					<td>${blogScoreVO.blog_score_status}</td>
					<!--<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do">
						    <input type="submit" value="修改">
						    <input type="hidden" name="blog_no" value="${blogVO.blog_no}">
						    <input type="hidden" name="action"	value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do">
						    <input type="submit" value="刪除">
						    <input type="hidden" name="blog_no" value="${blogVO.blog_no}">
						    <input type="hidden" name="action"value="delete">
						</FORM>
					</td>-->
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file" %>
	</body>
</html>