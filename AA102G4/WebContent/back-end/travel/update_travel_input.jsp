<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travel.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.travelImage.model.*"%>
<%
TravelVO travelVO = (TravelVO) request.getAttribute("travelVO"); //TravelServlet.java(Controller), 存入req的travelVO物件 (包括幫忙取出的travelVO, 也包括輸入資料錯誤時的travelVO物件)
TravelImageVO travelImageVO = (TravelImageVO) session.getAttribute("travelImageVO");
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
				<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travel/travel.do" name="form1">
				<table>
					<tr>
						<td>旅遊點編號:<font color=red><b>*</b></font></td>
						<td>${travelVO.tra_no}</td>
						<td><input type="hidden" name="tra_no" value="<%=travelVO.getTra_no()%>" /></td>
					</tr>
					<tr>
						<td>發布者:<font color=red><b>*</b></font></td>
						<td>
							<c:forEach var="memberVO" items="${memberSvc.all}">
			                    <c:if test="${travelVO.mem_no == memberVO.mem_no}">
			                    	${memberVO.mem_name}
			                    </c:if>
			                </c:forEach>
		                </td>
						<td><input type="hidden" name="mem_no" value="${travelVO.mem_no}" /></td>
					</tr>
					<tr>
						<td>所屬地區:</td>
						<td>
							<select name="loc_no" value="${travelVO.loc_no}">
							    <option value="1">北部</option>
							    <option value="2">中部</option>
							    <option value="3">南部</option>
							    <option value="4">東部</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>類別:</td>
						<td>
							<select name="tra_class_status" value="${travelVO.tra_class_status}">
							    <option value="0">景點</option>
							    <option value="1">休息站</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>旅遊點名稱:</td>
						<td><input type="TEXT" name="tra_name" value="${travelVO.tra_name}" /></td>
					</tr>
					<tr>
						<td>描述:</td>
						<td><textarea id="desc" rows="4" cols="70" name="tra_content" placeholder="description">${travelVO.tra_content}</textarea></td>
					</tr>
					<tr>
						<td>電話:</td>
						<td><input type="TEXT" name="tra_tel" value="<%=travelVO.getTra_tel()%>" /></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input type="TEXT" name="tra_add" value="<%=travelVO.getTra_add()%>" /></td>
					</tr>
					<tr>
						<p><img id="image" width="600"></p>
						<input type="file" id="myFile" name="tra_img" multiple="multiple" required="required"  value="<%= (travelImageVO==null)? "" : travelImageVO.getTra_img()%>"/>
					</tr>
					<tr>
						<td>刪除:</td>
						<td>
							<select name="tra_del" value="${travelVO.tra_del}">
							    <option value="0">否</option>
							    <option value="1">是</option>
							</select>
						</td>
					</tr>
				</table>
				<br>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="tra_cre" value="<%= date_SQL%>">
				<input type="hidden" name="tra_lati" value="0.0">
				<input type="hidden" name="tra_longi" value="0.0">
				<input type="submit" value="送出修改"></FORM>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>