<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.member.model.*"%>
<%
BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //BlogServlet.java (Concroller), 存入req的blogVO物件 (包括幫忙取出的blogVO, 也包括輸入資料錯誤時的blogVO物件)
%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<script src="<%=request.getContextPath()%>/back-end/travel/js/picture.js"></script>
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
			td{
				padding: 10px;
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
				<h4>資料修改:</h4>
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
				<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do" name="form1" enctype="multipart/form-data">
					<table>
						<tr>
							<td>日誌編號:<font color=red><b>*</b></font></td>
							<td><%=blogVO.getBlog_no()%></td>
						</tr>
						<tr>
							<td>作者:<font color=red><b>*</b></font></td>
							<td>
								<c:forEach var="memberVO" items="${memberSvc.all}">
				                    <c:if test="${blogVO.mem_no == memberVO.mem_no}">
				                    	${memberVO.mem_name}
				                    </c:if>
				                </c:forEach>
							<td>
						</tr>
						<tr>
							<td>日誌標題:</td>
							<td><input type="TEXT" name="blog_title" value="<%=blogVO.getBlog_title()%>"/></td>
						</tr>
						<tr>
							<td>日誌內容:</td>
							<td><textarea id="desc" rows="4" cols="70" name="blog_content" placeholder="description">${blogVO.blog_content}</textarea></td>
						</tr>
						<tr>
							<p><img id="image" width="600"></p>
							<input type="file" id="myFile" name="blog_photo" value="<%= (blogVO==null)?"":blogVO.getBlog_photo()%>"/>
						</tr>
						<tr>
							<td>刪除日誌:</td>
							<td>
								<select name="blog_del">
								    <option value="0">否</option>
								    <option value="1">是</option>
								</select>
							</td>
						</tr>
					</table><br>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="blog_no" value="<%=blogVO.getBlog_no()%>"/>
					<input type="hidden" name="mem_no" value="<%=blogVO.getMem_no()%>"/>
					<input type="hidden" name="blog_cre" value="<%=date_SQL%>"/>
					<input type="submit" value="送出修改">
				</FORM>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>