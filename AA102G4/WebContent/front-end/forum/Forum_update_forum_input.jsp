<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forum.model.*"%>
<%
	ForumVO forumVO = (ForumVO) request.getAttribute("forumVO"); //ForumServlet.java (Concroller), 存入req的forumVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的forumVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>文章修改</title>
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
			
			 body {padding-top:50px;height:100px;color: #fff; background-color:#01FF93;}
			 .green{width: 100%; height: 100px; background-color: #01FF93;}
/* 			 img { width: 100%; } */
			 .thumbnail{background-color: #fff; width: 280px; height: 450px;}
			 .i{background-color: #1D7F56;}
			 .box1{width: 280px;height: 480px; background-color: #FFF;}
			 .col-sm-8{
			 	width: 600px;
			 	height: 100px;
			 	background-color: #017F4A;
			 }
</style>

</head>


<%-- 引用js及css --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">

<script src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>/front-end/forum/js/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/forum/js/calendarcode.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/forum/css/calendar.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/res/js/ckeditor/contents.css">
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>

	<!-- 		<div class="row"> -->
	<%-- 			<jsp:include page="/front-end/left.jsp"></jsp:include> --%>
	<div class="col-md-10">
	
		<table border='1' cellpadding='5' cellspacing='0' width='400'>
			<tr bgcolor='#33FFDD' align='center' valign='middle' height='20'>
				<td>
					<h3>文章修改</h3> 
				</td>
			</tr>
		</table>
		
		
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

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?action=update_front" name="form1">
			<table border="0">
		
				<tr>
					<td><c:if test="${forumVO.mem_no==memberVO.mem_no}"><h3>會員:${memberVO.mem_name}</h3></c:if></td>
				</tr>

				<tr>
					<td><h3>文章標題:<input type="TEXT" name="forum_title" size="45" value="<%=forumVO.getForum_title()%>" /></h3></td>
				</tr>
				<tr>
					<td><h3>文章日期: ${forumVO.forum_cretime}</h3></td>
				</tr>
				<tr>
					<td><h3>文章內容:</h3><%-- 作用區塊 java name值帶入CKeditor --%>
						<form id="content" method="post">
							<div id="clob_picture_holder" class="zone"></div>
							<input type="file" onchange="preview(this)" style="width: 200px" />
							<a id="v0" class="various" href="javascript:void(0)">
							<img width="30" src="<%=request.getContextPath()%>/front-end/forum/images/image.png" title="插入圖片"></a>
							<textarea class="ckeditor" id="content" rows="6" cols="35" name="forum_content"><%=(forumVO == null) ? "" : forumVO.getForum_content()%></textarea>
						</form>
					</td>
				</tr>
			</table>
			
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="forum_no" value="<%=forumVO.getForum_no()%>">
			<input type="hidden" name="forum_cretime" value="<%=forumVO.getForum_cretime()%>">
			<input type="submit" value="送出修改">
		</FORM>
	</div>
	<script>
		//預覽用
		function preview(file) {

			if (file.files && file.files[0]) {
				var reader = new FileReader();
				reader.onload = function(evt) {
					$("#clob_picture_holder")
							.html(
									'<img width="100px" height="60" border="1" src="' + evt.target.result + '" >');
					data = evt.target.result;
				}
				reader.readAsDataURL(file.files[0]);
			}
		}
		$(".various").click(
				function() {
					var ori = CKEDITOR.instances.content.getData();
					CKEDITOR.instances.content.setData(ori
							+ "<img width='30%'src='" + data + "'>");
				});
	</script>
</body>
</html>
