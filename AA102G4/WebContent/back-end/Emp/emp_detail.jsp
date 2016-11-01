<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>員工管理--詳細</title>
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
			.table{
				width: 20%;
				text-align:center;
			}
			.table tr td{
				width: 30%;
				text-align:left;
			}
			.center{
				/*background-color: #fdf;*/
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
				<a href="<%=request.getContextPath()%>/emp.do?action=query">上一頁</a>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>員工帳號${test}</td>
							<td>${detailEmpVO.emp_acc}</td>
						</tr>
						<tr>
							<td>員工姓名</td>
							<td>${detailEmpVO.emp_name}</td>
						</tr>
						<tr>
							<td>電子信箱</td>
							<td>${detailEmpVO.emp_email}</td>
						</tr>
						<tr>
							<td>員工電話(家)</td>
							<td>${detailEmpVO.emp_tel}</td>
						</tr>
						<tr>
							<td>員工電話(手)</td>
							<td>${detailEmpVO.emp_phone}</td>
						</tr>
						<tr>
							<td>員工備註</td>
							<td><c:out value="${detailEmpVO.emp_phone}" default="無備註"></c:out></td>
						</tr>
						<tr>
							<td>到職日期</td>
							<td>${detailEmpVO.emp_hire}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>