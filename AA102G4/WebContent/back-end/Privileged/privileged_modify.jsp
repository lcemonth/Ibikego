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
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
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
<%-- 				<%=request.getParameter("emp_no")%> --%>
				<div class="container">
					<a href="" onclick="history.back()" class="btn btn-primary">上一頁</a>
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/privileged.do?action=update">
						<table width="504" border="0"  class="table table-bordered">
							<thead>
								<tr>
									<th colspan="11" class="tableA" scope="col">
											<%=request.getParameter("emp_name")%>::權限設定
	<%-- 									<%=request.getParameter("emp_name")%>的權限 --%>
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
											<input type="checkbox" id="pvl_no" name="pvl_no" value="${(110+i)}" <c:if test="${fn:contains(list, (110+i))}">checked</c:if> />
										</td>
									</c:forEach>
									<td>
										<input type="submit" value="修改" class="btn btn-info">
										<input type="hidden" name="emp_name" value="<%=request.getParameter("emp_name")%>">
										<input type="hidden" name="emp_no" value="<%=request.getParameter("emp_no")%>">
										<input type="hidden" name="emp_name" value="${empVO.emp_name}">
									</td>
								</tr>
							</tbody>
						</table>
					</form>
<!-- 					<input name="clickAll" id="clickAll" type="checkbox"> 全選 -->
					<input type="button" name="clickAll" id="clickAll" value="全部選取" class="btn btn-success">
					<input type="button" name="All" id="All" value="全部取消" class="btn btn-danger">
<!-- 					<input name="clickAll" id="clickAll" type="checkbox"> 全部取消 -->
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>
<script type="text/javascript">
		$(document).ready(function(){
			$("#clickAll").click(function(){
				$("input[name='pvl_no']").each(function() {
					$(this).prop("checked", true);
				});
			})
			$("#All").click(function(){
				$("input[name='pvl_no']").each(function() {
					$(this).prop("checked", false);
				});
			})
			
		})
</script>