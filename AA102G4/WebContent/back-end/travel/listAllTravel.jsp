<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.travel.model.*"%>
<%@ page import="com.travelImage.model.*"%>
<%@ page import="com.travelScore.model.*"%>
<%
	TravelService travelSvc = new TravelService();
    List<TravelVO> list = travelSvc.getAll();
    pageContext.setAttribute("list",list);
    
    TravelImageService travelImageSvc = new TravelImageService();
    List<TravelImageVO> listTravelImage = travelImageSvc.getAll();
    pageContext.setAttribute("listTravelImage",listTravelImage);
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
				width: 100%;
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
							<th>旅遊點編號</th>
							<th>發布者</th>
							<th>所屬地區</th>
							<th>類別</th>
							<th>旅遊點名稱</th>
							<th>描述</th>
							<th>電話</th>
							<th>地址</th>
							<th>最後編輯</th>
							<th>緯度</th>
							<th>經度</th>
							<th>圖片</th>
							<th>刪除</th>
						</tr>
					</thead>
					<%@ include file="page1.file" %> 
					<tbody>
						<c:forEach var="travelVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${travelVO.tra_no}</td>
								<td>
									<c:forEach var="memberVO" items="${memberSvc.all}">
					                    <c:if test="${travelVO.mem_no == memberVO.mem_no}">
					                    	${memberVO.mem_name}
					                    </c:if>
					                </c:forEach>
				                </td>
								<td>
									<c:if test="${travelVO.loc_no == 1}">北部</c:if>
									<c:if test="${travelVO.loc_no == 2}">中部</c:if>
									<c:if test="${travelVO.loc_no == 3}">南部</c:if>
									<c:if test="${travelVO.loc_no == 4}">東部</c:if>
								</td>
								<td>${(travelVO.tra_class_status==0)?"景點":"休息站"}</td>
								<td>${travelVO.tra_name}</td>
								<td id="content">${travelVO.tra_content}</td>
								<td>${travelVO.tra_tel}</td>
								<td>${travelVO.tra_add}</td>
								<td>${travelVO.tra_cre}</td>
								<td>${travelVO.tra_lati}</td>
								<td>${travelVO.tra_longi}</td>
								<td>
									<c:forEach var="travelImageVO" items="${listTravelImage}">
					                    <c:if test="${travelVO.tra_no == travelImageVO.tra_no}">
						                    <img class="img" src="<%=request.getContextPath()%>/image/image.do?tra_img_no=${travelImageVO.tra_img_no}"/>
					                    </c:if>
					                </c:forEach>
				                </td>
								<td>${(travelVO.tra_del==0)?"否":"是"}</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travel/travel.do">
									    <input type="submit" value="修改">
									    <input type="hidden" name="tra_no" value="${travelVO.tra_no}">
									    <input type="hidden" name="action"	value="getOne_For_Update">
									</FORM>
								</td>
<!-- 								<td> -->
<%-- 									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travel/travel.do"> --%>
<!-- 									    <input type="submit" value="刪除"> -->
<%-- 									    <input type="hidden" name="tra_no" value="${travelVO.tra_no}"> --%>
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