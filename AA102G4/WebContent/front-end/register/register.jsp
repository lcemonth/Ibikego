<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%-- <% session.setAttribute("location", request.getRequestURI());%> --%>
<%MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/js/address.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/front-end/register/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/register/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/register/js/picture.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/register/js/addressUpdate.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/front-end/register/js/sweetalert.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/register/css/sweetalert.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/register/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/register/css/style.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/register/jquery-ui-1.11.4.custom/jquery-ui.css">
	</head>
	<script>
		 $(function(){
		   $("#radio").buttonset();
		 });
		 
		 $(function(){
		   $("#datepicker").datepicker({
		     changeMonth: true,
		     changeYear: true
		   });
		 });
		
		 $(function(){
		   $("#radio1").click(function(){
		  $("#demo").css("color","red");
		  $("#demo1").css("color","black");
		  $("#demo").show();
		  $("#demo1").hide();
		});
		 });
		 
		 $(function(){
		   $("#radio2").click(function(){
		  $("#demo1").css("color","red");
		  $("#demo").css("color","black");
		  $("#demo1").show();
		  $("#demo").hide();
		});
		 });
		 
		 $(document).ready(function(){
		   $("#demo").hide();
		$("#demo1").hide();  	  
		$('#btnMagic').on('click',function(e){
		  $("input[name=mem_acc]").val("ffff123");
		  $("input[name=mem_pw]").val("123456");
		  $("input[name=mem_name]").val("小霞");
		  $("input[name=mem_nickname]").val("阿霞");
		  $("input[name=mem_add]").val("台灣市台灣路1號");
		  $("input[name=mem_phone]").val("0987654321");
		  $("input[name=mem_email]").val("a5933369@gmail.com");
		});
		 });
	</script>
	<style type="text/css">
		#img{
			position:relative;
			right: -130px;
		}
	</style>
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
				<img id="img" src="<%=request.getContextPath()%>/res/images/header_logo.png" width="300px">
				<c:if test="${not empty errorMsgs}">
					<font color='red'>
						<c:forEach var="message" items="${errorMsgs}">
							<script>swal("${message}", "請修正錯誤", "error")</script>
						</c:forEach>
					</font>
				</c:if>
				<form class="form-signin" role="form" METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1" enctype="multipart/form-data">
					<h3 class="form-signin-heading">註冊</h3>
					<div>
						<p><img id="image" width="150"></p>
						<input type="file" id="myFile" name="mem_photo" size="45" value="<%= (memberVO==null)? "" : memberVO.getMem_photo()%>"/>
					</div>
					<div class="input-group margin-bottom-sm">
						<input class="form-control" type="text" name="mem_acc" size="45" 
						value="<%= (memberVO==null)? "" : memberVO.getMem_acc()%>" placeholder="Account 必填" required autofocus>
					</div>
					<div class="input-group">
						<input class="form-control" type="password" name="mem_pw" size="45" 
						value="<%= (memberVO==null)? "" : memberVO.getMem_pw()%>" placeholder="Password 必填" required>
					</div>   
					 <div class="input-group margin-bottom-sm">
						<input class="form-control" type="text" name="mem_name" size="45" 
						value="<%= (memberVO==null)? "" : memberVO.getMem_name()%>" placeholder="Name 必填" required autofocus>
					</div>
					<div class="input-group margin-bottom-sm">
						<input class="form-control" type="text" name="mem_nickname" size="45" 
						value="<%= (memberVO==null)? "" : memberVO.getMem_nickname()%>" placeholder="Nickname">
					</div>
					<div class="input-group margin-bottom-sm">
						<input class="form-control" type="text" name="mem_add" size="45" 
						value="<%= (memberVO==null)? "" : memberVO.getMem_add()%>" placeholder="Address">
					</div>
					<div class="input-group margin-bottom-sm">
						<input class="form-control" type="text" name="mem_phone" size="45" 
						value="<%= (memberVO==null)? "" : memberVO.getMem_phone()%>" onkeyup="value=value.replace(/[^(\d)]/g,'')" placeholder="Phone">
					</div>
					<div class="input-group margin-bottom-sm">
						<input class="form-control" type="email" name="mem_email" size="45" 
						value="<%= (memberVO==null)? "" : memberVO.getMem_email()%>" placeholder="E-Mail 必填" required autofocus>
					</div>
					<div class="input-group margin-bottom-sm">
						<input class="form-control" type="hidden" name="mem_reg" size="45" 
						value="<%= (memberVO==null)? "0" : memberVO.getMem_reg()%>">
					</div>
					<div class="input-group margin-bottom-sm">
						<input class="form-control" type="hidden" name="mem_del" size="45" 
						value="<%= (memberVO==null)? "0" : memberVO.getMem_del()%>">
					</div>
					<input type="hidden" name="action" value="user_insert">
					<button class="btn btn-lg btn-primary btn-block" type="submit">註冊</button>
					<input type="button" value="神奇小按鈕" class="btn btn-blue" id="btnMagic">
				</form>
				<div class="footer">
					<span class="pull-right">&copy; 2016 <a href="<%=request.getContextPath()%>/front.do?navaction=bike" target="_blank" title="Company">Bike</a></span>
				</div>
			</div>
		</div> <!-- /container -->
	</body>
</html>