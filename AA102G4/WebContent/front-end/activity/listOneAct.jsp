<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.activity.model.*"%>
<%
ActivityVO actVO = (ActivityVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<html>
<head>
<title>���θ�� - listOneAct.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�@�����θ�� - ListOneAct.jsp</h3>
		<a href="<%=request.getContextPath()%>/activity/activity.do?action=backHome"><img src="<%=request.getContextPath()%>/back-end/activity/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>���νs��</th>
		<th>�|���s��</th>
		<th>�a�Ͻs��</th>
		<th>��{�s��</th>
		<th>���ΦW��</th>
		<th>���Φa�I</th>
		<th>�}�l���</th>
		<th>�������</th>
		<th>���Τ��e</th>
		<th>���ΥD�D�Ӥ�</th>
		<th>���θ��u��</th>
		<th>���θg�n��</th>
		<th>������</th>
		<th>�H��</th>
		<th>���Τ��}</th>
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
			<td>${(actVO.act_exp==null or actVO.act_exp=="" or actVO.act_exp=="null")? "�L���e" : actVO.act_exp }</td>
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
