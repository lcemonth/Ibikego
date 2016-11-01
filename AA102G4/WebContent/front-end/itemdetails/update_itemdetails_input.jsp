<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemdetails.model.*"%>
<%
ItemDetailsVO itemDetailsVO = (ItemDetailsVO) request.getAttribute("itemDetailsVO");
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
			<a href="<%=request.getContextPath()%>/itemdetails.do?action=allitemdetails">All</a>
				<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do?action=update" name="form1">
				<div class="form-group">
					<label class="col-sm-4 control-label">收貨姓名:</label>
					<div class="col-sm-6"><input type="TEXT" name="item_buyer_name" class="form-control" value="${itemDetailsVO.item_buyer_name}"/></div>
				</div>	
				<div class="form-group">
					<label class="col-sm-4 control-label">收貨地址:</label>
					<div class="col-sm-6"><input type="TEXT" name="item_buyer_add" class="form-control" value="${itemDetailsVO.item_buyer_add}"/></div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">收貨電話:</label>
					<div class="col-sm-6"><input type="TEXT" name="item_buyer_phone" class="form-control" value="${itemDetailsVO.item_buyer_phone}"/></div>
				</div>
				<div class="col-sm-8"></div>
				<div class="col-sm-4">
				<input type="hidden" name="item_no"  value="${itemDetailsVO.item_no}" />
				<input type="hidden" name="mem_no" value="${itemDetailsVO.mem_no}">
				<input type="hidden" name="item_detail_status" value="${itemDetailsVO.item_detail_status}">
				<input type="hidden" name="item_is_get" value="${itemDetailsVO.item_is_get}">
				<input type="hidden" name="item_is_sellerscore" value="1">
				<input type="hidden" name="item_is_buyerscore" value="${itemDetailsVO.item_is_buyerscore}">
				<input type="hidden" name="item_detail_del" value="${itemDetailsVO.item_detail_del}">
				<input type="hidden" name="item_buyer_score" value="${itemDetailsVO.item_buyer_score}">
				<input type="hidden" name="item_seller_score" value="${itemDetailsVO.item_seller_score}">
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
				<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
				<input type="submit" value="送出新增" class="btn btn-primary">
				</div>
				</form>	
				</div>
			</div>
			</div>
	</div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>