
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemimage.model.*"%>
<%
ItemImageVO itemImageVO = (ItemImageVO) request.getAttribute("itemImageVO"); 
%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
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
 	<h1>圖片修改管理<small>系統</small></h1>
	</div>

	<div class="container">
			<div class="row">
			<div class="col-sm-6">
			<h3>資料修改:</h3>
			<a href="<%=request.getContextPath()%>/item.do?action=searchall">List</a> all Items.
				<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/itemimage.do?action=update" enctype="multipart/form-data">
					<div class="form-group">
						
						<label class="col-sm-4 control-label">圖片編號:<b>*</b></label>
						<div class="col-sm-6"><%=itemImageVO.getItem_img_no()%></div>
						<br><br>

        				<label class="col-sm-4 control-label">商品編號:<b>*</b></label>
						<div class="col-sm-6"><%=itemImageVO.getItem_no()%></div>
						<br><br>

						<label class="col-sm-4 control-label">圖片:</label>
						<div class="col-sm-6"><input type='file' name = "item_img" onchange="readURL(this);"/>
						<img id="blah" src="<%=request.getContextPath()%>/getPostersServelt?item_img_no=${itemImageVO.item_img_no}" width="150px" height="150px"/>
						</div>
						<br><br>

					</div>
					<div class="col-sm-8"></div>
					<div class="col-sm-4">
						<input type="hidden" name="item_img_no" value="<%=itemImageVO.getItem_img_no()%>">
						<input type="hidden" name="item_no" value="<%=itemImageVO.getItem_no()%>">
						
						<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
						<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
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