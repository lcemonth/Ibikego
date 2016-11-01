<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //MemberServlet.java(Controller), 存入req的memberVO物件 
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
			.btn{
				align: center;
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
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>
							<c:forEach var="message" items="${errorMsgs}">
								<script>swal("${message}", "請修正錯誤", "error")</script>
							</c:forEach>
						</font>
					</c:if>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do?action=user_update" name="form1" enctype="multipart/form-data">
						<table  id="info" align="center">
							<tr>
								<td>帳號:<font color=red><b>*</b></font></td>
								<td>${memberVO.mem_acc}</td>
								<td><input type="hidden" name="mem_acc" size="45" value="${memberVO.mem_acc}" /></td>
							</tr>
							<tr>
								<td>姓名:</td>
								<td><input type="TEXT" name="mem_name" size="45" value="${memberVO.mem_name}" /></td>
							</tr>
							<tr>
								<td>暱稱:</td>
								<td><input type="TEXT" name="mem_nickname" size="45" value="${memberVO.mem_nickname}" /></td>
							</tr>
							<tr>
								<td>地址:</td>
								<td><input type="TEXT" name="mem_add" size="45" value="${memberVO.mem_add}" /></td>
							</tr>
							<tr>
								<td>電話:</td>
								<td><input type="TEXT" name="mem_phone" size="45" value="${memberVO.mem_phone}" /></td>
							</tr>
							<tr>
								<td>信箱:<font color=red><b>*</b></font></td>
								<td>${memberVO.mem_email}</td>
								<td><input type="hidden" name="mem_email" size="45" value="${memberVO.mem_email}" /></td>
							</tr>
							<tr>
								<td>照片:</td>
								<p><img id="image" width="100"></p>
								<td><input type="file" id="myFile" name="mem_photo" value="${(memberVO==null)?'':memberVO.mem_photo}"/></td>
							</tr>
						</table><br>
							<input type="submit" class="btn btn-primary" value="修改" style="margin: 0 685px;">
						<div id="showImg" border="1"></div>
						<input type="hidden" name="mem_del" value="${memberVO.mem_del}"/>
						<input type="hidden" name="mem_reg" value="${memberVO.mem_reg}"/>
						<input type="hidden" name="mem_pw" value="${memberVO.mem_pw}"/>
						<input type="hidden" name="mem_no" value="${memberVO.mem_no}"/>
					</FORM>
				</div>
			</div>
		</div>
	</body>
</html>