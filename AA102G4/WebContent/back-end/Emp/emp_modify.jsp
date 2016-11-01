<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>員工管理--修改</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
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
				width:17%;
				text-align:left;
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
				<a href="" onclick="history.back()" class="btn btn-primary">上一頁</a>
				<div align="center" >
				<form METHOD="post" action="<%=request.getContextPath()%>/emp.do?action=update&emp_no=${modifyempVO.emp_no}" >
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td>帳號</td>
								<td><input type="text" name="emp_acc" id="emp_acc" value="${modifyempVO.emp_acc}"></td>
							</tr>
							<tr>
								<td>姓名</td>
								<td><input type="text" name="emp_name" id="emp_name" value="${modifyempVO.emp_name}"></td>
							</tr>
							<tr>
								<td>Mail</td>
								<td><input type="text" name="emp_email" id="emp_email" value="${modifyempVO.emp_email}"></td>
							</tr>
							<tr>
								<td>電話(家)</td>
								<td><input type="text" name="emp_tel" id="emp_tel" value="${modifyempVO.emp_tel}"></td>
							</tr>
							<tr>
								<td>電話(手)</td>
								<td><input type="text" name="emp_phone" id="emp_phone" value="${modifyempVO.emp_phone}"></td>
							</tr>
							<tr>
								<td>備註</td>
								<td><input type="text" name="emp_ps" id="emp_ps" value="${modifyempVO.emp_ps}"></td>
							</tr>
							<tr>
								<td>到職日期</td>
								<td><input type="text" name="emp_hire" id="emp_hire" value="${modifyempVO.emp_hire}" onclick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd'})" readonly></td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="emp_pw" id="emp_pw" value="${modifyempVO.emp_pw}">
					<input type="submit" value="送出" class="btn btn-info">
				</form>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>