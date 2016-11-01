<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
	MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
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
					<%@ include file="page1.file" %>
					<tbody>
						<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr align='center' valign='middle'>
								<td>${memberVO.mem_no}</td>
								<td>${memberVO.mem_acc}</td>
								<td>${memberVO.mem_pw}</td>
								<td>${memberVO.mem_name}</td>
								<td>${memberVO.mem_nickname}</td>
								<td>${memberVO.mem_add}</td>
								<td>${memberVO.mem_phone}</td>
								<td>${memberVO.mem_email}</td>
								<td><img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memberVO.mem_no}" height=80 width=80/></td>
								<td>${(memberVO.mem_reg==0)?"網頁":"手機"}</td>
								<td>${(memberVO.mem_del==0)?"否":"是"}</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
									     <input type="submit" value="修改">
									     <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
									     <input type="hidden" name="action"	value="getOne_For_Update">
									</FORM>
								</td>
<!-- 								<td> -->
<%-- 									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do"> --%>
<!-- 									    <input type="submit" value="刪除"> -->
<%-- 									    <input type="hidden" name="mem_no" value="${memberVO.mem_no}"> --%>
<!-- 									    <input type="hidden" name="action"value="delete"> -->
<!-- 									</FORM> -->
<!-- 								</td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<%@ include file="page2.file" %>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>