<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.message.model.*"%>
<%
MessageVO messageVO = (MessageVO) request.getAttribute("messageVO"); //MessageServlet.java(Controller), 存入req的messageVO物件
%>
<html>
	<head>
		<title>留言資料 - listOneMessage.jsp</title>
	</head>
	<body bgcolor='white'>
		<table border='1' cellpadding='5' cellspacing='0' width='600'>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td>
					<h3>留言資料 - listOneMessage.jsp</h3>
					<a href="<%=request.getContextPath()%>/message/message.do?action=home"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</td>
			</tr>
		</table>
		<table border='1' bordercolor='#CCCCFF' width='600'>
			<tr>
				<th>留言編號</th>
				<th>日誌</th>
				<th>留言者</th>
				<th>內容</th>
				<th>留言時間</th>
			</tr>
			<tr align='center' valign='middle'>
				<td>${messageVO.mes_no}</td>
				<td>${messageVO.blog_no}</td>
				<td>${messageVO.mem_no}</td>
				<td>${messageVO.mes_content}</td>
				<td>${messageVO.mes_cre}</td>
			</tr>
		</table>
	</body>
</html>