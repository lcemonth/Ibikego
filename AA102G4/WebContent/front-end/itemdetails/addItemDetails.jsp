<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemdetails.model.*"%>
<%
ItemDetailsVO itemdetailsVO = (ItemDetailsVO) request.getAttribute("itemdetailsVO");

Integer item_no = (Integer) request.getAttribute("item_no");
Integer mem_no = (Integer) request.getAttribute("mem_no");
%>
<%    
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd HH/mm/ss");    
java.util.Date currentTime_1 = new java.util.Date();    
out.print(formatter.format(currentTime_1));    
%>  
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		</style>
	</head>
	<body>
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
	<div class="col-sm-12 page-header">
 	<h1>商品資料新增<small>系統</small></h1>
	</div>

	<div class="container">
			<div class="row">
			<div class="col-sm-6">
			<h3>資料修改:</h3>
			<a href="<%=request.getContextPath()%>/item.do?action=searchall">ALL</a>
				<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do?action=insert" name="form1">
				<table cellspacing="10">
				<jsp:useBean id="itemdetailsSvc" scope="page" class="com.itemdetails.model.ItemDetailsService" />
				<tr>
					<th>收貨姓名:</th>
					<td><input type="TEXT" name="item_buyer_name" class="form-control" value="<%=(itemdetailsVO==null)? "我是誰" : itemdetailsVO.getItem_buyer_name()%>" /></td>
				</tr>
				
				<tr>
					<th>收貨地址:</th>
					<td><input type="TEXT" name="item_buyer_add" class="form-control" value="<%=(itemdetailsVO==null)? "地址" : itemdetailsVO.getItem_buyer_add()%>" /></td>
				</tr>
				<tr>
					<th>收貨電話:</th>
					<td><input type="TEXT" name="item_buyer_phone"  class="form-control" value="<%=(itemdetailsVO==null)? 0111111111 : itemdetailsVO.getItem_buyer_phone()%>"/></td>
				</tr>
				</table>
				<input type="hidden" name="item_no" value="${item_no}">
				<input type="hidden" name="mem_no" value="${mem_no}">
				<input type="hidden" name="item_detail_status" value="0">
				<input type="hidden" name="item_is_get" value="0">
				<input type="hidden" name="item_is_sellerscore" value="0">
				<input type="hidden" name="item_is_buyerscore" value="0">
				<input type="hidden" name="item_detail_del" value="0">
				<input type="hidden" name="item_seller_score" value="0">
				<input type="hidden" name="item_buyer_score" value="0">
				<input type="submit" value="送出新增" class="btn btn-primary">
				</form>	
			</div>
			</div>
	</div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>