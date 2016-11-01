<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.travelImage.model.*"%>
<%
	TravelImageService travelImageSvc = new TravelImageService();
    List<TravelImageVO> list = travelImageSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
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
				<c:if test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</font>
				</c:if>
				<table border='1' bordercolor='#CCCCFF' width='800'>
					<tr>
						<th>圖片編號</th>
						<th>旅遊點編號</th>
						<th>圖片</th>
					</tr>
					<%@ include file="page1.file" %> 
					<c:forEach var="travelImageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle'>
							<td>${travelImageVO.tra_img_no}</td>
							<td>${travelImageVO.tra_no}</td>
							<td><img src="<%=request.getContextPath()%>/image/image.do?tra_img_no=${travelImageVO.tra_img_no}" height=80 width=80/></td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travelImage/travelImage.do">
								    <input type="submit" value="修改">
								    <input type="hidden" name="tra_img_no" value="${travelImageVO.tra_img_no}">
								    <input type="hidden" name="action"	value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travelImage/travelImage.do">
								    <input type="submit" value="刪除">
								    <input type="hidden" name="tra_img_no" value="${travelImageVO.tra_img_no}">
								    <input type="hidden" name="action"value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file" %>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>