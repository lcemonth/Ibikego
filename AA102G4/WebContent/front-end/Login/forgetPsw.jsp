<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		%>
		<link href="<%=request.getContextPath()%>/Front/forIndex/layoutit/css/bootstrap.min.css" rel="stylesheet">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>忘記密碼</title>
	</head>
	<body>
		<div class="container">
			<div class="jumbotron" style="background-color:lightblue;"></div>
			<form role="form" action="<%=request.getContextPath()%>/member/member.do" method=post>
				<div class="row">
					<div class="col-md-4"></div>
		  			<div class="col-md-4">
						<div class="form-group">
							<label for="id">帳號:</label>
							<div>
								<input type="text" id="id" placeholder="輸入帳號" name="mem_acc"  class="form-control" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4"></div>
		  			<div class="col-md-4">
						<div class="form-group">
							<label for="email">信箱:</label>
							<div>
								<input type="email" id="mail" placeholder="輸入信箱" name="mem_email"  class="form-control" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4"></div>
		  			<div class="col-md-4">
						<div>
							<div>
								<button type="submit" class="btn btn-primary">確認</button>
								<a class="btn btn-warning" href="<%=request.getContextPath()%>/Login/Login.jsp" role="button">取消</a>
								<input type="hidden" name="action" value="forgetPsw">
							</div>
						</div>
						123131
					</div>
				</div>
			</form>
		</div>
	</body>
</html>