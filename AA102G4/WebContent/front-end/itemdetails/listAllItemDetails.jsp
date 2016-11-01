
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itemdetails.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ItemDetailsService itemdetailsSvc = new ItemDetailsService();
	List<ItemDetailsVO> list = itemdetailsSvc.getAll();
	pageContext.setAttribute("list",list);
%>
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
		<script type="text/javascript">
		</script>
	</head>
	<body>
	<div class="col-sm-12 page-header"><h1>訂單管理<small>查詢All</small></h1></div>
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
		<div class="container">
		<div class="row">
			
			<div class="col-sm-12">
			<a href="<%=request.getContextPath()%>/item.do?action=index">回首頁</a><br>
			
				<table class="table table-hover table-bordered table-condensed" >
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
		<th>修改</th>
		<th>查詢</th>
		<th>買方給評</th>
		<th>賣方給評</th>
		<th>訂單</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="itemDetailsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${itemDetailsVO.item_no}</td>
			<td>${memberSvc.getOneMember(itemDetailsVO.mem_no).mem_name}</td>
			<td>${(itemDetailsVO.item_detail_status == 0)? '交易中':'完畢'}</td>
			<td>${(itemDetailsVO.item_is_get == 0)? '未取貨':'已取貨'}</td>
			<td><a>${(itemDetailsVO.item_is_sellerscore == 0)? '未給評':'已給評'}</a></td>
			<td><a>${(itemDetailsVO.item_is_buyerscore == 0)? '未給評':'已給評'}</a></td>
			<td>${(itemDetailsVO.item_detail_del == 0)? '':'取消訂單'}</td>
			<td>${itemDetailsVO.item_seller_score}</td>
			<td>${itemDetailsVO.item_buyer_score}</td>
			<td>${itemDetailsVO.item_order_time}</td>
			<td>${itemDetailsVO.item_buyer_name}</td>
			<td>${itemDetailsVO.item_buyer_add}</td>
			<td>${itemDetailsVO.item_buyer_phone}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do?action=getOne_For_Update">
				     <input type="submit" value="修改" class="btn btn-warning">
				     <input type="hidden" name="item_no" value="${itemDetailsVO.item_no}">
				     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    	 <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     </FORM>
			</td>
			<td> <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do">
				<input type="hidden" name="action" value="listItemimages_ByItemDetails_no">
			    <input type="submit" value="明細" class="btn btn-primary"> 
			    <input type="hidden" name="item_no" value="${itemVO.item_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
			    </FORM>
			</td>
			<td> <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do?action=getSellerUpdate">
				<input type="hidden" name="action" value="listItemimages_ByItemDetails_no">
			    <input type="submit" value="買方給評" class="btn btn-primary"> 
			    <input type="hidden" name="item_no" value="${itemDetailsVO.item_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
			    </FORM>
			</td>
			<td> <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do?action=getBuyerUpdate">
			    <input type="submit" value="賣方給評" class="btn btn-primary"> 
			    <input type="hidden" name="item_no" value="${itemDetailsVO.item_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
			    </FORM>
			</td>
			<td> <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do?action=cancel">
			    <input type="submit" value="取消" class="btn btn-primary"> 
			    <input type="hidden" name="item_no" value="${itemDetailsVO.item_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
			    </FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>

			</div>
		</div>
	</div>
	<div class="container">
		<%@ include file="page2.file" %>
	</div>
		<!-- jQuery -->
		<script src="//code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
 		<script src="Hello World"></script>
 		
	</body>
</html>