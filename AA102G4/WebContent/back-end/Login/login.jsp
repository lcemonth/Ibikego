<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>員工登入</title>
			<script src="<%=request.getContextPath()%>/front-end/Login/js/sweetalert.min.js"></script>
			<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/sweetalert.css">
	</head>
<style>
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}


</style>
	<body>
		<div align="center" >
			<!--This is hidden/shown automatically after Durandal loads. -->
			<div class="container">
				<div class="overlay">
					<img src="<%=request.getContextPath()%>/res/images/header_logo.png" width="300px">
					
					<form METHOD="post"  class="form-signin" role="form" ACTION="<%=request.getContextPath()%>/login.do?action=login" >
						<h3 class="form-signin-heading">登入</h3>
						<div class="input-group margin-bottom-sm">
							<span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
							<input class="form-control" type="text" name="emp_acc" id="emp_acc" placeholder="Account" required autofocus>
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-unlock-alt fa-fw"></i></span>
							<input class="form-control" type="password" name="emp_pw" id="emp_pw" placeholder="Password" required>
						</div>
						<div>
							<c:if test="${not empty messageError}">
								<font color='red'>
									<c:forEach var="message" items="${messageError}">
										<script>swal("${message}", "", "error")</script>
									</c:forEach>
								</font>	
							</c:if>
						</div>   		
						<button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
						<input type="hidden" name="action" value="getACC_PW">
					</form>
				</div>
			</div>	<!-- /container -->
	   
		</div>
	</body>
</html>