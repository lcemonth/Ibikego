<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Controller), 存入req的memberVO物件
%>
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
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
 			<jsp:include page="/back-end/left.jsp"></jsp:include>
			<div class="col-md-10">
				<table class="table table-hover">
   					<thead>
						<tr>
							<th>會員編號</th>
							<th>帳號</th>
							<th>密碼</th>
							<th>姓名</th>
							<th>暱稱</th>
							<th>地址</th>
							<th>連絡電話</th>
							<th>信箱</th>
							<th>大頭貼</th>
							<th>註冊平台</th>
							<th>帳號停權</th>
						</tr>
					</thead>
					<tbody>
						<tr align='center' valign='middle'>
							<td><%=memberVO.getMem_no()%></td>
							<td><%=memberVO.getMem_acc()%></td>
							<td><%=memberVO.getMem_pw()%></td>
							<td><%=memberVO.getMem_name()%></td>
							<td><%=memberVO.getMem_nickname()%></td>
							<td><%=memberVO.getMem_add()%></td>
							<td><%=memberVO.getMem_phone()%></td>
							<td><%=memberVO.getMem_email()%></td>
							<td><img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memberVO.mem_no}"/></td>
							<td>${(memberVO.mem_reg==0)?"網頁":"手機"}</td>
							<td>${(memberVO.mem_del==0)?"否":"是"}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>