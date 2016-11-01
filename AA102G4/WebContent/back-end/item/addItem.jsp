<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%
ItemVO itemVO = (ItemVO) request.getAttribute("itemVO");
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
		<script type="text/JavaScript">      
 function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
 
                reader.onload = function (e) {
                    $('#blah').attr('src', e.target.result);
                }
 
                reader.readAsDataURL(input.files[0]);
            }
        }

</script>
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
			<h3>資料新增:</h3>
			<a href="<%=request.getContextPath()%>/item.do?action=index">回首頁</a>
				<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/item.do?action=insert" name="form1" enctype="multipart/form-data">
				<table>
				<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
				<tr>
					<th>選擇會員編號:</th>
					<td>
				       <select size="1" name="mem_no">
				         <c:forEach var="memberVO" items="${memberSvc.all}" > 
				          <option value="${memberVO.mem_no}">${memberVO.mem_no}
				         </c:forEach>   
				       </select>
			       </td>
				</tr>
				<tr>
					<th>商品名稱:</th>
					<td><input type="TEXT" name="item_name" class="form-control" value="<%= (itemVO==null)? "自行車90" : itemVO.getItem_name()%>" /></td>
				</tr>
				
				<tr>
					<th>商品價格:</th>
					<td><input type="TEXT" name="item_price" class="form-control" value="<%= (itemVO==null)? 1000 : itemVO.getItem_price()%>" /></td>
				</tr>
				<tr>
					<th>商品說明:</th>
					<td><textarea type="text" name="item_exp"  rows="4" cols="47" class="form-control" style="resize: none"><%= (itemVO==null)? "請說明" : itemVO.getItem_exp()%></textarea></td>
				</tr>
				<tr>
					<th>商品狀態:</th>
					<td>
					<input type="radio" name="item_is_added" value="0" />0-下架
					<input type="radio" name="item_is_added" value="1" />1-上架
					</td>
				</tr>
				<tr>
					<th>圖片:</th>
						<td><input type='file' name = "item_img" onchange="readURL(this);" multiple/>
						<img id="blah" src="" width="150px" height="150px"/></td>
						
				</tr>
				</table>
				<input type="hidden" name="action" value="insert">
				<input type="submit" value="送出新增" class="btn btn-primary">
				</form>	
			</div>
			</div>
	</div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>