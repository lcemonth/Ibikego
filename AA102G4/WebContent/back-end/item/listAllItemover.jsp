
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<jsp:useBean id="imageSvc" scope="page" class="com.itemimage.model.ItemImageService"/>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/item/js/jquery-1.7.2.min.js"></script>
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
		<!-- <div class="container"> -->
		<div class="row">
			<div class="col-sm-12">
			<h1>商品紀錄<small>查詢All</small></h1>
<table class="table table-hover table-bordered table-condensed" >
	<tr align='center' >
		<th>商品編號</th>
		<th>賣家</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品狀態</th>
		<th>刪除</th>
		<th>圖片</th>
		<th>一圖</th>
		
	</tr>
	<c:forEach var="itemVO" items="${list}">
	<c:if test="${itemVO.item_is_added==2}">
		<tr align='center' valign='middle' ${(itemVO.item_no==param.item_no) ? 'bgcolor=#eee':''}>
			<td>${itemVO.item_no}</td>
			<td>${memberSvc.getOneMember(itemVO.mem_no).mem_name}</td>
			<td>${itemVO.item_name}</td>
			<td>${itemVO.item_price}</td>
			<td class="added" value="${itemVO.item_is_added}">${(itemVO.item_is_added == 0) ? '下架':(itemVO.item_is_added == 1) ? '上架':'交易完成'}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item.do?action=delete">
					<input type="submit" value="刪除" class="btn btn-danger" <c:if test="${(itemVO.item_is_added==2)}"> disabled </c:if>>
				    <input type="hidden" name="item_no" value="${itemVO.item_no}">
				    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				</FORM>
			</td>
			<td> <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item.do?action=Item_Details">
			    <input type="submit" value="明細" class="btn btn-primary"> 
			    <input type="hidden" name="item_no" value="${itemVO.item_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    </FORM>
			</td>
			<td>
				<img src="<%=request.getContextPath()%>/getOnePostersServelt?item_no=${itemVO.item_no}" width="100px" height="100px" class="img-rounded">
			</td>
		</tr>
		</c:if>
	 </c:forEach>
</table>
			</div>
		</div>
	<!-- </div> -->

	</div>
	
		<!-- jQuery -->
		<script src="https://code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
 		<%if (request.getAttribute("listItemImages_ByItem_no")!=null){%>
 		<jsp:include page="listItemImages_ByItem_no.jsp" />
 		<%} %>
	</body>
</html>