<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.activity.model.*"%>
<%
ActivityVO actVO = (ActivityVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>揪團資料 - listOneAct.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>一筆揪團資料 - ListOneAct.jsp</h3>
		<a href="<%=request.getContextPath()%>/activity/activity.do?action=backHome"><img src="<%=request.getContextPath()%>/back-end/activity/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
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
	</tr>
</table>

</body>
</html>
