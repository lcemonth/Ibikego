<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>


<%
	List<EmpVO> list = (List<EmpVO>) request.getAttribute("list");
	if(list==null){
		EmpService empSvc = new EmpService();
		 list = empSvc.getAll();
		pageContext.setAttribute("list",list);
	}
%>

<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			.col-md-12{
				/*width: 100%;
				background-color: #fdf;*/
				text-align:center;
			}
			.col-md-2{
				/*width: 100%;
				background-color: #fcf;*/
				height: 100px;
				text-align:center;
			}
			.col-md-10{
				/*width: 100%;
				background-color: #fdf;*/

			}
			.table tr th{
				text-align:center;
			}
			.empadd{
				text-align: left;
			}
			.empsearch{
				width: 100%
				text-align:center;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<div class="col-md-12">
				<img src="https://api.fnkr.net/testimg/1024x100/00CED1/FFF/?text=Bike" >
			</div>
			<div class="col-md-12">
				<nav class="navbar navbar-default" role="navigation">
					<!-- logo區 -->
					<a class="navbar-brand" href="<%=request.getContextPath()%>/back-end/index.jsp">後管首頁</a>
					<!-- 左選單 -->
					<ul class="nav navbar-nav">
						<li><a href="#">員工管理</a></li>
						<li><a href="#">會員管理</a></li>
						<li><a href="#">最新消息管理</a></li>
						<li><a href="#">購物管理</a></li>
						<li><a href="#">討論區管理</a></li>
						<li><a href="#">檢舉管理</a></li>
						<li><a href="#">Q&A管理</a></li>
						<li><a href="#">旅遊點管理</a></li>
						<li><a href="#">評分管理</a></li>
					</ul>
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#"><c:out value="${empVO.emp_name}"></c:out>	您好<br/></a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/Login/login.jsp">登出</a></li>
						<li><a href="#">個人設定</a></li>
						<li><a href="#"></a></li>
					</ul>
				</nav>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<div class="list-group">
					<a href="#" class="list-group-item">員工帳號管理</a>
					<a href="#" class="list-group-item">權限管理</a>
				</div>
			</div>
			<div class="col-md-10">
				<div class="col-md-12">
					<div class="empsearch">
						<form class="navbar-form navbar-left" role="search" METHOD="post" ACTION="<%=request.getRequestURI()%>">
							<div class="form-group">
								<input type="text" name="emp_name" id="emp_name" class="form-control" placeholder="請輸入員工姓名">
							</div>
							<input type="hidden" name="action"value="search">
							<button type="submit" class="btn btn-default">搜尋</button>
						</form>
					</div>
				</div>
				<div class="col-md-12">
					<div class="empadd"><a href="<%=request.getContextPath()%>/emp_add.jsp">新增</a></div>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>員工編號</th>
								<th>姓名</th>
								<th>Mail</th>
								<th>手機</th>
								<th>到職日期</th>
								<th>離職日期</th>
								<th>設定</th>
							</tr>
						</thead>
						<tbody>
							<%@ include file="page1.file" %> 
							<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td>${empVO.emp_no}</td>
									<td><a href="<%=request.getContextPath()%>/emp.do?emp_no=${empVO.emp_no}&action=detail">${empVO.emp_name}</a></td>
									<td>${empVO.emp_email}</td>
									<td>${empVO.emp_phone}</td>
									<td>${empVO.emp_hire}</td>
									<td>
										<c:out value="${empVO.emp_over}" default="尚未離職"></c:out>
									</td>
									<td>
										<form METHOD="post" ACTION="<%=request.getContextPath()%>/emp.do">
											<input type="submit" value="修改">
										    <input type="hidden" name="emp_no" value="${empVO.emp_no}">
										    <input type="hidden" name="action"value="modify">
										</form>
										<form METHOD="post" ACTION="<%=request.getContextPath()%>/emp.do" onClick="return confirm('確定要刪除嗎?')">
										    <input type="submit" value="刪除">
										    <input type="hidden" name="emp_no" value="${empVO.emp_no}">
										    <input type="hidden" name="action"value="delete">
									    </form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
<%-- 					<%@ include file="page2.file" %> --%>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>