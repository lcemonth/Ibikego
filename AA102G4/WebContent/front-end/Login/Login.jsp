<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
	<head>
		<meta charset="utf-8">
		<script src="<%=request.getContextPath()%>/front-end/Login/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/Login/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/Login/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/Login/js/sweetalert.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/front-end/Login/css/font-awesome.min.css" rel="stylesheet">
		<link href='<%=request.getContextPath()%>/front-end/Login/css/gfont.css' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/style.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/jquery-ui-1.11.4.custom/jquery-ui.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/sweetalert.css">
	</head>
	<style>
		#forget {
		    float: left;
		}
	</style>
	<script>
		$(function(){
			var dialog,form,
			forgetPsw = $("#forgetPsw"),
			allFields = $([]).add(forgetPsw),
			tips = $(".validateTips");
			function addUser(){
				var valid = true;
			}
			dialog = $("#dialog-form").dialog({
				autoOpen: false,
				height: 350,
				width: 650,
				modal: true,
				buttons: {},
				close: function(){
					// form[ 0 ].reset();
					allFields.removeClass("ui-state-error");
				}
			});
			$("#create-user").button().on("click",function(){
				dialog.dialog("open");
			});
		});
	</script>
	<body>
		<!--This is hidden/shown automatically after Durandal loads. -->
		<div class="container" style="display: none;">
			<div class="overlay loading">
				<i class="fa fa-spinner fa-5x fa-spin"></i><br/>
				<h1>Loading...</h1>
			</div>
		</div>
		<div class="container">
			<div class="overlay">
				<img src="<%=request.getContextPath()%>/res/images/header_logo.png" width="300px">
				<form method="post" class="form-signin" role="form" action="<%=request.getContextPath()%>/login/login.do?action=login">
					<h3 class="form-signin-heading">登入</h3>
					<div class="input-group margin-bottom-sm">
						<span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
						<input class="form-control" type="text" name="mem_acc" placeholder="Account" required autofocus>
					</div>
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-unlock-alt fa-fw"></i></span>
						<input class="form-control" type="password" name="mem_pw" placeholder="Password" required>
					</div>
					<div>
						<c:if test="${not empty errorMsgs}">
							<font color='red'>
								<c:forEach var="message" items="${errorMsgs}">
									<script>swal("${message}", "請修正錯誤", "error")</script>
								</c:forEach>
							</font>	
						</c:if>
					</div>   		
					<button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
					<input type="hidden" name="action" value="getACC_PW">
				</form>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<div>
					<table>
						<tr>
							<td>
								<a href="<%=request.getContextPath()%>/front-end/register/register.jsp">還沒註冊嗎?</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="create-user">忘記密碼?</button></td>
						</tr>	
					</table>
				</div>	
				<div class="footer">
					<span class="pull-right">
						&copy; 2016<a href="<%=request.getContextPath()%>/front.do?navaction=bike" target="_blank" title="Company">Bike</a>
					</span>
				</div>
			</div>
		</div>	<!-- /container -->
		
	    <div id="dialog-form" title="忘記密碼">
	    	<form role="form" action="<%=request.getContextPath()%>/member/member.do" method=post>
				<div class="row">
					<div class="col-md-4"></div>
		  			<div class="col-md-4">
						<div class="form-group">
							<label for="id">帳號:</label>
							<div><input type="text" id="id" placeholder="輸入帳號" name="mem_acc" class="form-control" /></div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4"></div>
		  			<div class="col-md-4">
						<div class="form-group">
							<label for="email">信箱:</label>
							<div><input type="email" id="mail" placeholder="輸入信箱" name="mem_email"  class="form-control" /></div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4"></div>
		  			<div class="col-md-4">
						<div>
							<div>
								<button type="submit" class="btn btn-primary">確認</button>
								<a class="btn btn-warning" href="<%=request.getContextPath()%>/front-end/Login/Login.jsp" role="button">取消</a>
								<input type="hidden" name="action" value="forgetPsw">
							</div>
						</div><br/>
						<b style="color:red";>請到註冊信箱內取得系統更新密碼後再登入!</b>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>