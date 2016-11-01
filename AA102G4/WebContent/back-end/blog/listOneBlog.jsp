<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.member.model.*"%>
<%
BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //BlogServlet.java(Controller), 存入req的blogVO物件
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
			.img{
				width: 300px;
				height: 300px;
			}
			.content{
				word-spacing: break-word;
			}
		</style>
	</head>
	<body>
		<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
		<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
 			<jsp:include page="/back-end/left.jsp"></jsp:include>
			<div class="col-md-10">
				<table class="table table-hover">
   					<thead>
						<tr>
							<th>日誌編號</th>
							<th>作者</th>
							<th>標題</th>
							<th>內容</th>
							<th>最後編輯</th>
							<th>照片</th>
							<th>刪除日誌</th>
						</tr>
					</thead>
					<tbody>
						<tr align='center' valign='middle'>
							<td><%=blogVO.getBlog_no()%></td>
							<td>
								<c:forEach var="memberVO" items="${memberSvc.all}">
				                    <c:if test="${blogVO.mem_no == memberVO.mem_no}">
				                    	${memberVO.mem_name}
				                    </c:if>
				                </c:forEach>
			                </td>
							<td><%=blogVO.getBlog_title()%></td>
							<td id="content">${blogVO.blog_content}</td>
							<td><%=blogVO.getBlog_cre()%></td>
							<td><img class="img" src="<%=request.getContextPath()%>/blogImage.do?blog_no=${blogVO.blog_no}"/></td>
							<td><%=(blogVO.getBlog_del()==0)?"否":"是"%></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>