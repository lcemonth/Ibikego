<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%-- 此頁練習採用 EL 的寫法取值 --%>

 <jsp:useBean id="listJoinMems" scope="request" type="java.util.Set" /> 
 

<%-- <c:out value="${listJoinMems}"  default="listJoinMems無值"></c:out> --%>

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<%--  <c:out value="${memSvc.all}"  default="memSvc無值"></c:out>  --%>

<html>
<head>
<title>部門員工 - listEmps_ByDeptno.jsp</title>
</head>
<body bgcolor='white'>

<!-- <b><font color=red>此頁練習採用 EL 的寫法取值:</font></b> -->
<!-- <table border='1' cellpadding='5' cellspacing='0' width='800'> -->
<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!-- 		<td> -->
<!-- 		<h3>揪團成員 - listJoinMems_ByActno.jsp</h3> -->
<%-- 		<a href="<%=request.getContextPath()%>/activity/activity.do?action=allList"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回列表</a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

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
		<th>揪團編號</th>
		<th>參加會員編號</th>
		<th>是否參加</th>
		<th>揪團評分分數</th>
		<th>緯度</th>
		<th>經度</th>
		<th>姓名</th>
		
	</tr>
	 
	<c:forEach var="jaVO" items="${listJoinMems}">
		<tr align='center' valign='middle' ${(jaVO.act_no==param.act_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${jaVO.act_no}</td>
			<td>${jaVO.mem_no}</td>
			<td>${jaVO.joinact_is_join}</td>
			<td>${jaVO.joinact_score}</td>
			<td>${jaVO.joinact_lati}</td>
			<td>${jaVO.joinact_longi}</td>			
			<td>
				<c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${jaVO.mem_no==memVO.mem_no}">
	                    	【<font color=orange>${memVO.mem_name}</font> - ${memVO.mem_phone}】
                    </c:if>
                </c:forEach>
			</td>

		</tr>
	</c:forEach>
	
</table>


<br>included本網頁的路徑:<br><b>
   <font color=red>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=red>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
