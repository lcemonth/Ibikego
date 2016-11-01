<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	BlogService blogSvc = new BlogService();
    List<BlogVO> list = blogSvc.getAll();
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
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</font>
				</c:if>
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
					<%@ include file="page1.file" %>
					<tbody>
						<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr align='center' valign='middle'>
								<td>${blogVO.blog_no}</td>
								<td>
									<c:forEach var="memberVO" items="${memberSvc.all}">
					                    <c:if test="${blogVO.mem_no == memberVO.mem_no}">
					                    	${memberVO.mem_name}
					                    </c:if>
					                </c:forEach>
				                </td>
								<td>${blogVO.blog_title}</td>
								<td id="content">${blogVO.blog_content}</td>
								<td>${blogVO.blog_cre}</td>
								<td><img class="img" src="<%=request.getContextPath()%>/blogImage.do?blog_no=${blogVO.blog_no}"/></td>
								<td>${(blogVO.blog_del==0)?"否":"是"}</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do">
									    <input type="submit" value="修改">
									    <input type="hidden" name="blog_no" value="${blogVO.blog_no}">
									    <input type="hidden" name="action"	value="getOne_For_Update">
									</FORM>
								</td>
<!-- 								<td> -->
<%-- 									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do"> --%>
<!-- 									    <input type="submit" value="刪除"> -->
<%-- 									    <input type="hidden" name="blog_no" value="${blogVO.blog_no}"> --%>
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