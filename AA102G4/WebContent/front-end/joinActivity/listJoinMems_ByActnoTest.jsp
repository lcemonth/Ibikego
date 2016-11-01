<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%-- �����m�߱ĥ� EL ���g�k���� --%>

 <jsp:useBean id="listJoinMems" scope="request" type="java.util.Set" /> 
 

<%-- <c:out value="${listJoinMems}"  default="listJoinMems�L��"></c:out> --%>

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<%--  <c:out value="${memSvc.all}"  default="memSvc�L��"></c:out>  --%>

<html>
<head>
<title>�������u - listEmps_ByDeptno.jsp</title>
</head>
<body bgcolor='white'>

<!-- <b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b> -->
<!-- <table border='1' cellpadding='5' cellspacing='0' width='800'> -->
<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!-- 		<td> -->
<!-- 		<h3>���Φ��� - listJoinMems_ByActno.jsp</h3> -->
<%-- 		<a href="<%=request.getContextPath()%>/activity/activity.do?action=allList"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">�^�C��</a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>���νs��</th>
		<th>�ѥ[�|���s��</th>
		<th>�O�_�ѥ[</th>
		<th>���ε�������</th>
		<th>�n��</th>
		<th>�g��</th>
		<th>�m�W</th>
		
	</tr>
	 
	<c:forEach var="jaVO" items="${listJoinMems}">
		<tr align='center' valign='middle' ${(jaVO.act_no==param.act_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td>${jaVO.act_no}</td>
			<td>${jaVO.mem_no}</td>
			<td>${jaVO.joinact_is_join}</td>
			<td>${jaVO.joinact_score}</td>
			<td>${jaVO.joinact_lati}</td>
			<td>${jaVO.joinact_longi}</td>			
			<td>
				<c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${jaVO.mem_no==memVO.mem_no}">
	                    	�i<font color=orange>${memVO.mem_name}</font> - ${memVO.mem_phone}�j
                    </c:if>
                </c:forEach>
			</td>

		</tr>
	</c:forEach>
	
</table>


<br>included�����������|:<br><b>
   <font color=red>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=red>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
