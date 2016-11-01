<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.blogScore.model.*"%>
<%
BlogScoreVO blogScoreVO = (BlogScoreVO) request.getAttribute("blogScoreVO"); //BlogScoreServlet.java(Controller), 存入req的blogScoreVO物件
%>
<html>
	<head>
		<title>評分資料 - listOneBlogScore.jsp</title>
	</head>
	<body bgcolor='white'>
		<table border='1' cellpadding='5' cellspacing='0' width='600'>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td>
					<h3>評分資料 - listOneBlogScore.jsp</h3>
					<a href="<%=request.getContextPath()%>/blogScore/blogScore.do?action=home"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</td>
			</tr>
		</table>
		<table border='1' bordercolor='#CCCCFF' width='600'>
			<tr>
				<th>日誌</th>
				<th>評分者</th>
				<th>分數</th>
				<th>評分狀態</th>
			</tr>
			<tr align='center' valign='middle'>
				<td>${blogScoreVO.blog_no}</td>
				<td>${blogScoreVO.mem_no}</td>
				<td>${blogScoreVO.blog_score}</td>
				<td>${blogScoreVO.blog_score_status}</td>
			</tr>
		</table>
	</body>
</html>