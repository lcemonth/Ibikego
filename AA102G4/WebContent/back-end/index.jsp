<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.privileged.model.*"%>
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
			.left{
			width: 10%;
			margin:0 2%;
			background-color: #FFFACD;
			}
		</style>
	</head>
	<body>
		<jsp:useBean id="privilegedSvc" scope="page" class="com.privileged.model.PrivilegedService" />
		<jsp:useBean id="privilegeMenuSvc" scope="page" class="com.privilegemenu.model.PrivilegeMenuService" />
		<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="left">
				<table class="table table-hover" style="border: solid 1px black;">
 					<thead>
 					</thead>
			    	<tbody>
			        	<tr><td>員工基本資料</td></tr>
			            <tr><td>員工編號:  ${empVO.emp_no}</td></tr>
			            <tr><td>員工名稱:  ${empVO.emp_name}</td></tr>
			            <tr><td>擁有權限:</td></tr>
			            <tr>
			            	<td>
			            		<c:forEach var="privilegedVO" items="${privilegedSvc.all}">
			            			<c:forEach var="privilegeMenuVO" items="${privilegeMenuSvc.all}">
				            			<c:if test="${privilegedVO.emp_no==empVO.emp_no}">
					            			<c:if test="${privilegedVO.pvl_no==privilegeMenuVO.pvl_no}">
					            				${privilegeMenuVO.pvl_detail}
					            			</c:if>
				            			</c:if>
			            			</c:forEach>
			            		</c:forEach>
			            	</td>
			            </tr>
					    <tr><td>E-mail:<br>${empVO.emp_email}</td></tr>
				        <tr><td>連絡電話:<br>${empVO.emp_tel}</td></tr>
				    	<tr><td>手機:<br>${empVO.emp_phone}</td></tr>
				    	<tr><td>到職日期:${empVO.emp_hire}</td></tr>
					</tbody>
				</table>   
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>