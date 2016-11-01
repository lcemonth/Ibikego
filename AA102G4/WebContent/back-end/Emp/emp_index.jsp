<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>


<%

// 	List<EmpVO> list = (List<EmpVO>) request.getAttribute("list");
	List<EmpVO> list = (List<EmpVO>) request.getAttribute("list");
	if(list==null){
		EmpService empSvc = new EmpService();
		list = empSvc.getAll();	
	}
	
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>員工管理</title>
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
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<jsp:include page="/back-end/left.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="col-md-12">
					<div class="empsearch">
						<form class="navbar-form navbar-left" role="search" METHOD="post" ACTION="<%=request.getContextPath()%>/emp.do?action=select">
							<div class="form-group">
								<input type="text" name="emp_name" id="emp_name" class="form-control" placeholder="請輸入員工姓名" value="${emp_name}">
							</div>
							<input type="hidden" name="action" value="select">
							<button type="submit" class="btn btn-default">搜尋</button>
						</form>
					</div>
				</div>
				<div class="col-md-12">
					<div class="empadd">
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/emp.do?action=add" >
						    <input type="submit" value="新增" class="btn btn-info">
					    </form>
					</div>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>員工編號</th>
								<th>姓名</th>
								<th>Mail</th>
								<th>手機</th>
								<th>到職日期</th>
								<th>權限設定</th>
								<th colspan="2">設定</th>
							</tr>
						</thead>
						<tbody>
							<%@ include file="page1.file" %> 
							<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td>${empVO.emp_no}</td>
									<td><a href="<%=request.getContextPath()%>/emp.do?action=detail&emp_no=${empVO.emp_no}">${empVO.emp_name}</a></td>
									<td>${empVO.emp_email}</td>
									<td>${empVO.emp_phone}</td>
									<td>${empVO.emp_hire}</td>
									<td>
										<form METHOD="post" ACTION="<%=request.getContextPath()%>/privileged.do?action=query">
											<input type="submit" value="權限" class="btn btn-warning">
										    <input type="hidden" name="emp_no" value="${empVO.emp_no}">
										    <input type="hidden" name="emp_name" value="${empVO.emp_name}">
										</form>
									</td>
									<td>
										<form METHOD="post" ACTION="<%=request.getContextPath()%>/emp.do?action=modify">
											<input type="submit" value="修改"  class="btn btn-primary">
										    <input type="hidden" name="emp_no" value="${empVO.emp_no}">
										</form>

									</td>
									<td>
										<form METHOD="post" ACTION="<%=request.getContextPath()%>/emp.do?action=delete" onClick="return confirm('確定要刪除嗎?')">
										    <input type="submit" value="刪除" class="btn btn-danger">
										    <input type="hidden" name="emp_no" value="${empVO.emp_no}">
									    </form>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${!not empty list}" >
								<tr>
									<td colspan="7">查無此資料</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					
					<%@ include file="page2.file" %>
<%-- 					<jsp:include page="page2.jsp"></jsp:include> --%>
<%-- 					<c:if test="${emp_name!=null}">&emp_name=${emp_name}</c:if> --%>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>