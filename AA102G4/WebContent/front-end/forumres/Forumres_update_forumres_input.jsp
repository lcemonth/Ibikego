<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forumresponse.model.*"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	ForumresVO forumresVO = (ForumresVO) request.getAttribute("forumresVO"); //ForumServlet.java (Concroller), 存入req的forumVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的forumresVO物件)
%>
<html>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<style type="text/css">
		 body {padding-top:50px;height:100px;color: #000; background-color:#30FFFF;}
		 .green{width: 100%; height: 100px; background-color: #30FFFF;}
/* 		 img { width: 100%; } */
		 .thumbnail{background-color: #fff; width: 280px; height: 450px;}
		 .i{background-color: #1D7F56;}
		 .box1{width: 280px;height: 480px; background-color: #FFF;}
		 .col-sm-8{
		 	width: 600px;
		 	height: 100px;
		 	background-color: #017F4A;
		 }
</style>
<%-- 引用js及css --%>



<title>回應修改</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/js/ckeditor/contents.css">
<script src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=request.getContextPath()%>/front-end/forumres/js/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/forumres/js/calendarcode.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/forumres/css/calendar.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
	</div>
<!-- 		<div class="row"> -->
<%-- 			<jsp:include page="/front-end/left.jsp"></jsp:include> --%>
		
	
	<div class="col-md-10">

		<h3>修改回應:</h3>
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

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/forumres.do?action=update_front"
			name="form1">
			<table border="0">
	
				<tr>
					<td>
					<%-- 作用區塊 java name值帶入CKeditor --%>
					<form id="content" method="post">
						<div id="clob_picture_holder" class="zone"></div>
						<input type="file" onchange="preview(this)" style="width: 200px" />
						<a id="v0" class="various" href="javascript:void(0)">
						<img width="30" src="<%=request.getContextPath()%>/front-end/forumres/images/image.png" title="插入圖片"></a>
						<textarea class="ckeditor" id="content" rows="6" cols="35"
						name="forumres_con"><%=(forumresVO == null) ? "" : forumresVO.getForumres_con()%></textarea>
					</form>
					</td>
				</tr>



			</table>
			<br> <input type="hidden" name="action" value="update">
			<input type="hidden" name="forumres_no" value="${forumresVO.forumres_no}">
			<input type="hidden" name="mem_no" value="${forumresVO.mem_no}">
			<input type="hidden" name="forum_no" value="${forumresVO.forum_no}" >
			<input type="hidden" name="forumres_cretime" value="${forumresVO.forumres_cretime}">
			<input type="hidden" name="forumres_del" value="${forumresVO.forumres_del}">
			<input type="submit" value="送出修改">
		</FORM>

	</div>
	<script>
		//履歷上傳-預覽用
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
