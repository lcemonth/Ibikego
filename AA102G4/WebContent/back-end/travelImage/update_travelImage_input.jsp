<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travelImage.model.*"%>
<%
TravelImageVO travelImageVO = (TravelImageVO) request.getAttribute("travelImageVO"); //TravelImageServlet.java(Controller), 存入req的travelImageVO物件 (包括幫忙取出的travelImageVO, 也包括輸入資料錯誤時的travelImageVO物件)
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
				<h3>資料修改:</h3>
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
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travelImage/travelImage.do" name="form1" enctype="multipart/form-data">
				<table border="0">
					<tr>
						<td>圖片編號:</td><font color=red><b>*</b></font></td>
						<td><%=travelImageVO.getTra_img_no()%></td>
						<td><input type="hidden" name="tra_img_no" size="45" value="<%=travelImageVO.getTra_img_no()%>" /></td>
					</tr>
					<tr>
						<td>旅遊點編號:<font color=red><b>*</b></font></td>
						<td><%=travelImageVO.getTra_no()%></td>
						<td><input type="hidden" name="tra_no" size="45" value="<%=travelImageVO.getTra_no()%>" /></td>
					</tr>
					<tr>
						<td>圖片:</td>
						<td><input type="file" name="tra_img" value="<%=travelImageVO.getTra_img()%>"/></td>
					</tr>
				</table>
				<br>
				<input type="hidden" name="action" value="update">
				<input type="submit" value="送出修改"></FORM>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>