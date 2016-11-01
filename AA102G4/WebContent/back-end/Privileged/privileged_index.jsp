<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%>
<%@ page import="com.privileged.model.*"%>
<%
	Integer	emp_no=new Integer(request.getParameter("emp_no"));
	PrivilegedService pvlSvc = new PrivilegedService();
	List list=pvlSvc.getEmpPrivileged(emp_no);
	pageContext.setAttribute("list",list);
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
			.table thead tr th{
				text-align:center;
			}
			.table thead tr td{
				text-align:center;
			}
			
			.table tbody tr td{
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
				<div class="container">
					<a href="<%=request.getContextPath()%>/emp.do?action=query" class="btn btn-primary">上一頁</a>
					<table width="504" border="0"  class="table table-bordered">
						<thead>
							<tr>
								<th colspan="11" class="tableA" scope="col">
									<%=request.getParameter("emp_name")%>的權限
								</th>
							</tr>
							<tr>
								<td width="2%"  scope="col">
									員<br/>工<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									會<br/>員<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									旅<br/>遊<br/>點<br/>照<br/>片<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									購<br/>物<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									討<br/>論<br/>區<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									檢<br/>舉<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									Q<br/>&<br/>A<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									旅<br/>遊<br/>點<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									日<br/>誌<br/>管<br/>理
								</td>
								<td width="2%"  scope="col">
									揪<br/>團<br/>管<br/>理
								</td>
								<td width="2%"  scope="col" >&nbsp;</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach var="i" begin="1" end="10">
									<td>
										<c:if test="${fn:contains(list, (110+i))}">V</c:if>
									</td>
								</c:forEach>
								<td>
									<form METHOD="post" ACTION="<%=request.getContextPath()%>/privileged.do?action=modify">
										<input type="submit" value="修改" class="btn btn-primary">
										<input type="hidden" name="emp_no" value="<%=request.getParameter("emp_no")%>">
										<input type="hidden" name="emp_name" value="<%=request.getParameter("emp_name")%>">
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>