<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.travelScore.model.*"%>
<%
TravelScoreVO travelScoreVO = (TravelScoreVO) request.getAttribute("travelScoreVO"); //TravelScoreScoreServlet.java(Controller), 存入req的travelScoreVO物件
%>
<html>
	<head>
		<title>評分資料 - listOneTravelScore.jsp</title>
	</head>
	<body bgcolor='white'>
		<table border='1' cellpadding='5' cellspacing='0' width='600'>
			<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
				<td>
					<h3>評分資料 - listOneTravelScore.jsp</h3>
					<a href="<%=request.getContextPath()%>/travelScore/travelScore.do?action=home"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</td>
			</tr>
		</table>
		<table border='1' bordercolor='#CCCCFF' width='600'>
			<tr>
				<th>旅遊點</th>
				<th>評分者</th>
				<th>分數</th>
				<th>評分狀態</th>
			</tr>
			<tr align='center' valign='middle'>
				<td>${travelScoreVO.tra_no}</td>
				<td>${travelScoreVO.mem_no}</td>
				<td>${travelScoreVO.tra_score}</td>
				<td>${travelScoreVO.tra_score_status}</td>
			</tr>
		</table>
	</body>
</html>