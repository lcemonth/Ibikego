<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemimage.model.*"%>
<%
Integer  item_no = (Integer) request.getAttribute("item_no");
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
	</head>
	<style type="text/css">
	.img {
    max-width: 150px; 
    max-height: 150px;
    margin: 5px;
}
	</style>
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
				<form class="form-horizontal form1" METHOD="post" ACTION="<%=request.getContextPath()%>/itemimage.do?action=insert" name="form1" enctype="multipart/form-data">
				<table>
				<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
				<tr>
					<th>商品編號:</th>
					<td>
				       <%=item_no%>
			       </td>
				</tr>
				<tr>
					<label class="col-sm-4 control-label">圖片:</label>
						<div class="col-sm-6"><input type='file' class="upl" name="upl" multiple/><div class="preview"></div>
						</div>
				</tr>
				</table>
				<input type="hidden" name="item_no" value="<%=item_no%>">
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
				<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
				<input type="submit" value="送出新增" class="btn btn-primary">
				</form>	
			</div>
			</div>
	</div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script type="text/javascript">

   var Preview = new function (){
 
    var root = $(".form1");
 
    // 連續的圖片編碼
    var imgcode = '';
 
    // 選取發生改變
    this.change_file = function (){
        root.on("change", ".upl", function (){
            show(this);
        });
    }
 
    // 批次圖片，先清空後再插入
    var show = function (input){
        if (input.files && input.files[0]) {
            clean();
            each_img(input.files);
        }
    }
 
    // 批次讀取，最後再一次寫入
    var each_img = function (files){
 
        $.each(files, function (index, file){
            var src = URL.createObjectURL(file);
            create_imgcode(src);
        });
 
        // 放置預覽元素後重設
        root.find(".preview").html(imgcode);
        reset();
    }
 
 
    // 建立圖片
    var create_imgcode = function(src){
        imgcode += '<img class="img" src="' + src + '">';
    }
    
 
    // 清空預覽區域
    var clean = function (){
        root.find(".preview").empty();
    }

    // 還原 input[type=file]
    var reset = function (){
        imgcode = '';
    }
}
 
// 執行
Preview.change_file();
 
		</script>
	</body>
</html>