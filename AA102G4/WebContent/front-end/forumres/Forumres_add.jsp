<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.forumresponse.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	ForumresVO forumresVO = (ForumresVO) request.getAttribute("forumresVO");
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



<title>文章回應</title>
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
	
		<div class="row">
<%-- 			<jsp:include page="/front-end/left.jsp"></jsp:include> --%>

		<div class="col-md-10">

			<table border='1' cellpadding='5' cellspacing='0' width='400'>
				<tr bgcolor='#33FFDD' align='center' valign='middle' height='20'>
					<td>
						<h3>回應</h3>
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

			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumres.do?action=insert_front" name="form1">
				<table border="0">
					<tr>
						<td><input type="hidden" name="mem_no" name="mem_no" value="${memberVO.mem_no}">
								<c:if test="${memberVO.mem_no !=null}">
									<h3>會員:${memberVO.mem_name}</h3>
								</c:if>
						</td>
					</tr>
					<%
						java.sql.Date SQL_date = new java.sql.Date(System.currentTimeMillis());
					%>
					<tr>
					<td><h3>圖片預覽:</h3></td>	
						<td>
							<input type="hidden" name="forumres_cretime" id="forumres_cretime" size="30" 
							value="<%=(forumresVO == null) ? SQL_date : forumresVO.getForumres_cretime()%>">
						</td>		
					</tr>
					<tr>
						<td>內容:</td>
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
				<input type="hidden" name=forum_no value="${forumVO.forum_no}">
				<input type="hidden" name=forumres_del value="<%=(forumresVO == null) ? "0" : forumresVO.getForumres_del()%>">
				<br> <input type="hidden" name="button" value="insert_front">
				<input type="submit" value="送出新增">
			</FORM>

		</div>
	</div>

<script>
//履歷上傳-預覽用
function preview(file) {

	if (file.files && file.files[0])
	{	var reader = new FileReader();
			reader.onload = function(evt)
			{
				$("#clob_picture_holder").html('<img width="100px" height="60" border="1" src="' + evt.target.result + '" >');
				data = evt.target.result;
			}
				reader.readAsDataURL(file.files[0]);
	}
}
$(".various").click(function()
{	
	var ori = CKEDITOR.instances.content.getData();
	CKEDITOR.instances.content.setData(ori + "<img width='30%'src='" + data + "'>");
});
</script>
</body>
</html>
