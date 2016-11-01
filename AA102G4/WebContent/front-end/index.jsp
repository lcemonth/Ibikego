<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //MemberServlet.java(Controller), 存入req的memberVO物件
session.setAttribute("location", request.getRequestURI());
%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<title>揪團騎BIKE</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/font-awesome.min.css" rel="stylesheet"/>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<style type="text/css">
		 .green{width: 100%; height: 1300px; background-color: #01FF93;}
		 img { width: 100%; }
		 .thumbnail{background-color: #fff; width: 280px; height: 450px;}
		 .i{background-color: #1D7F56;}
		 .box1{width: 280px;height: 480px; background-color: #FFF;}
		 .col-sm-8{
		 	width: 600px;
		 	height: 100px;
		 	background-color: #017F4A;
		 }
		 </style>
	</head>
	<body>
		<div id="section0">
		<div class="container">
		    <div class="carousel-caption">
		        <div><img src="<%=request.getContextPath()%>/res/images/header_logo.png"></div>
		    </div>
		</div>
		<div class="slideshow">
			<img src="<%=request.getContextPath()%>/res/images/01.jpg"/>
			<img style="display:none;" src="<%=request.getContextPath()%>/res/images/02.jpg"/>
			<img style="display:none;" src="<%=request.getContextPath()%>/res/images/03.png"/>
		</div>
		</div>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="http://malsup.github.com/jquery.cycle.all.js"></script>
		<script type="text/javascript"> $(function() { $('.slideshow').cycle({fx:'fade'}); }); </script>
	</body>
</html>