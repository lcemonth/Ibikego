<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
Integer mem_no = memberVO.getMem_no();
MemberVO listOneUser =new MemberService().getOneMember(mem_no);
pageContext.setAttribute("listOneUser",listOneUser);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/index.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/home.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<style type="text/css">
			body{
				background-color: #87CEEB;
			}
			.col-xs-2{
				position: relative;
				top: 50px;
			}
			.a1{
				background-color: #67cad2; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:176px;/*右邊*/
			}
			.a1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
			}
			.b1{
				background-color: #f86e8f; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:96px;/*右邊*/
			}
			.b1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
			}
			.c1{
				background-color: #ffc15e; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:16px;/*右邊*/
			}
			.c1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
			}
			#change{
				position: relative;
				top: 50px;
			}
			#info td{
				padding: 10px;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="container">
			<jsp:include page="homeTop.jsp"></jsp:include>
			<!-- 下面	 -->
			<div class="row">
				<jsp:include page="homeFunction.jsp"></jsp:include>
				<div class="row">
					<table id="info" bordercolor='#CCCCFF' width='600' align="center">
						<tr>
							<th>帳號</th>
							<td>${listOneUser.mem_acc}</td>
						</tr>
						<tr>
							<th>姓名</th>
							<td>${listOneUser.mem_name}</td>
						</tr>
						<tr>
							<th>暱稱</th>
							<td>${listOneUser.mem_nickname}</td>
						</tr>
						<tr>
							<th>地址</th>
							<td>${listOneUser.mem_add}</td>
						</tr>
						<tr>
							<th>連絡電話</th>
							<td>${listOneUser.mem_phone}</td>
						</tr>
						<tr>
							<th>信箱</th>
							<td>${listOneUser.mem_email}</td>
						</tr>
						<tr>
							<th>大頭貼</th>
							<td><img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${listOneUser.mem_no}"/></td>
						</tr>
						<tr>
							<th>註冊平台</th>
							<td>${(listOneUser.mem_reg==0)?"網頁":"手機"}</td>
						</tr>						
					</table>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do?action=getOneUser_For_Update" align="center">
						     <input type="submit" class="btn btn-primary" value="修改" style="margin: 0 670px;">
						     <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
						</FORM>
				</div>
			</div>
		</div>
	</body>
</html>