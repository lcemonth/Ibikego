<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.itemdetails.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
	<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
	</div>
	<jsp:include page="/back-end/left.jsp"></jsp:include>
	<div class="col-md-10">
 	<h1>查詢商品<small>One</small></h1>
 	<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do?action=getOneBuyer">
		<div class="col-sm-12">
		<div class="col-sm-1">
		<b valign="middle">選擇買家編號:</b>
		</div>
		<div class="col-sm-1">
			<select  name="mem_no" class="form-control col-sm-1">
				<c:forEach var="memberVO" items="${memberSvc.all}" ><option value="${memberVO.mem_no}">${memberVO.mem_no}</c:forEach>
			</select>
		</div>
		<input type="submit" class="btn btn-primary" value="送出">
		</div>
	</form>
	<!-- <div class="container"> -->
		<div class="row">	
		<div class="col-sm-12">
		<table class="table table-hover table-bordered table-striped table-condensed">
		<tr>
			<th>商品編號</th>
			<th>買家</th>
			<th>商品訂單</th>
			<th>取貨狀態</th>
			<th>賣方評分</th>
			<th>買方評分</th>
			<th>訂單取消</th>
			<th>賣家分數</th>
			<th>買家分數</th>
			<th>訂單日期</th>
			<th>訂單姓名</th>
			<th>訂單地址</th>
			<th>訂單電話</th>
		</tr>
		<c:forEach var="itemDetailsVO" items="${itemDetailsList}">
		<tr align='center' valign='middle'> 
			<td>${itemDetailsVO.item_no}</td>
			<td>${memberSvc.getOneMember(itemDetailsVO.mem_no).mem_name}</td>
			<td>${(itemDetailsVO.item_detail_status == 0)? '交易中':'完畢'}</td>
			<td>${(itemDetailsVO.item_is_get == 0)? '未取貨':'已取貨'}</td>
			<td>${(itemDetailsVO.item_is_sellerscore == 0)? '未給評':'已給評'}</td>
			<td>${(itemDetailsVO.item_is_buyerscore == 0)? '未給評':'已給評'}</td>
			<td>${(itemDetailsVO.item_detail_del == 0)? '':'取消訂單'}</td>
			<td>${itemDetailsVO.item_seller_score}</td>
			<td>${itemDetailsVO.item_buyer_score}</td>
			<td>${itemDetailsVO.item_order_time}</td>
			<td>${itemDetailsVO.item_buyer_name}</td>
			<td>${itemDetailsVO.item_buyer_add}</td>
			<td>${itemDetailsVO.item_buyer_phone}</td>
		</tr>
		</c:forEach>
		</table>
		</div>
		</div>
	<!-- </div> -->
	</div>	

		<!-- jQuery -->
		<script src="//code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
 		<script src="Hello World"></script>
	</body>
</html>