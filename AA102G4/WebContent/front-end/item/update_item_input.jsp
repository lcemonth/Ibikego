
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%
ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); //itemServlet.java (Concroller), 存入req的itemVO物件 (包括幫忙取出的itemVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.6.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/back-end/js/selected.js"></script>
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
 	<h1>圖片修改管理<small>系統</small></h1>
	</div>

	<div class="container">
			<div class="row">
			<div class="col-sm-6">
			<h3>資料修改:</h3>
			<a href="<%=request.getContextPath()%>/item.do?action=searchall">List</a> all Items.
				<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/item.do?action=update">
					<div class="form-group">
						
						<label class="col-sm-4 control-label">商品編號:<b>*</b></label>
						<div class="col-sm-6">${itemVO.getItem_no()}</div>
					</div>
					<div class="form-group">
        				<label class="col-sm-4 control-label">會員編號:<b>*</b></label>
						<div class="col-sm-6">${itemVO.getMem_no()}</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">商品名稱:</label>
						<div class="col-sm-6"><input type="TEXT" name="item_name" class="form-control" value="${itemVO.getItem_name()}" /></div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">商品價格:</label>
						<div class="col-sm-6"><input type="TEXT" name="item_price" class="form-control" value="${itemVO.getItem_price()}" /></div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">商品說明:</label>
						<div class="col-sm-6"><textarea type="text" name="item_exp" class="form-control" rows="4" cols="47" style="resize: none">${itemVO.getItem_exp()}</textarea></div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">商品狀態:</label>
						<div class="col-sm-6"><input type="radio" name="item_is_added" value="0" />下架
											  <input type="radio" name="item_is_added" value="1" />上架
											  <input type="radio" name="item_is_added" value="2" />交易完成
											  <input type="hidden" id="add"  value="${itemVO.item_is_added}" />
											  </div>
					</div>
					<div class="col-sm-8"></div>
					<div class="col-sm-4">
						<input type="hidden" name="item_no" value="${itemVO.getItem_no()}">
						<input type="hidden" name="mem_no" value="${itemVO.getMem_no()}">
						<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
						<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
						<input type="submit" class="btn btn-primary" value="送出">
					</div>
				</form>	
			</div>
			</div>
	</div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>