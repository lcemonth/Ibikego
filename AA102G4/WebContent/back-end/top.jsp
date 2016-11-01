<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.privileged.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
	EmpVO empVO = (EmpVO) session.getAttribute("empVO");
	Integer emp_no = empVO.getEmp_no();

	PrivilegedService pvlSvc = new PrivilegedService();
	List list = pvlSvc.getEmpPrivileged(emp_no);	
// 	System.out.println(list.contains(111));
	pageContext.setAttribute("list",list);
%>

			<div class="col-md-12">
				<img src="https://api.fnkr.net/testimg/1024x100/00CED1/FFF/?text=Bike" >
			</div>
			<div class="col-md-12">
				<nav class="navbar navbar-default" role="navigation">
<!-- 					logo區 -->
					<a class="navbar-brand" href="<%=request.getContextPath()%>/back-end/index.jsp">後管首頁</a>
<!-- 					左選單 -->
					<ul class="nav navbar-nav">
						<c:if test="${fn:contains(list, 111)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=emp">員工管理</a></li>
						</c:if>
						<c:if test="${fn:contains(list, 112)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=member">會員管理</a></li>
						</c:if>
						<c:if test="${fn:contains(list, 113)}">
							
						</c:if>
						<c:if test="${fn:contains(list, 114)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=item">購物管理</a></li>
						</c:if>
						<c:if test="${fn:contains(list, 115)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=forum">討論區管理</a></li>
						</c:if>
						<c:if test="${fn:contains(list, 116)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=repm">檢舉管理</a></li>
						</c:if>
						<c:if test="${fn:contains(list, 117)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=que_ans">Q&A管理</a></li>
						</c:if>
						<c:if test="${fn:contains(list, 118)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=travelPoint">旅遊點管理</a></li>
						</c:if>
						<c:if test="${fn:contains(list, 119)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=blog">單車日誌管理</a></li>
						</c:if>
						<c:if test="${fn:contains(list, 120)}">
							<li><a href="<%=request.getContextPath()%>/nav.do?navaction=actm">揪團管理</a></li>
						</c:if>
					</ul>
<!-- 					右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#"><c:out value="${empVO.emp_name}"></c:out>	您好<br/></a></li>
						<li><a href="<%=request.getContextPath()%>/login.do?action=logout">登出</a></li>
						<li><a href="#">個人設定</a></li>
						<li><a href="#"></a></li>
					</ul>
				</nav>
			</div>