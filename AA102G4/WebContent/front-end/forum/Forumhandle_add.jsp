<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>

<%
	ForumVO forumVO = (ForumVO) request.getAttribute("forumVO");
%>
<%
	ReportcollectVO rcVO = (ReportcollectVO) request.getAttribute("rcVO");
%>
<%
	java.sql.Date SQL_date = new java.sql.Date(System.currentTimeMillis());
%>
<html>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
<head>
<title>檢舉原因</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
<style type="text/css">

/* 		background-color:#01FF93; */
/* .green{width: 100%; height: 1300px; background-color: #01FF93; */
body {
	padding-top: 50px;
	height: 100px;
	color: #000;
	background-color: #30FFFF;
}

.green {
	width: 100%;
	height: 100px;
	background-color: #30FFFF;
}
/* 		 img { width: 100%; } */
.thumbnail {
	background-color: #fff;
	width: 280px;
	height: 450px;
}

.i {
	background-color: #1D7F56;
}

.box1 {
	width: 280px;
	height: 480px;
	background-color: #FFF;
}

.col-sm-8 {
	width: 600px;
	height: 100px;
	background-color: #017F4A;
}

.col-md-2 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 100px;
	text-align: center;
}

.col-md-10 {
	/*width: 100%;
				background-color: #fdf;*/
	
}
</style>
<%-- 引用js及css --%>
<script
	src="<%=request.getContextPath()%>/front-end/forum/js/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/forum/js/calendarcode.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/res/js/ckeditor/contents.css">

<title>檢舉新增</title>
</head>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/forum/css/calendar.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor="white">

	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>

	<div class="row">
		<%-- 		<jsp:include page="/back-end/left.jsp"></jsp:include> --%>

		<div class="col-md-10">
			<table border='1' cellpadding='5' cellspacing='0' width='400'>
				<tr bgcolor='#33FFDD' align='center' valign='middle' height='20'>
					<td>
						<h3>檢舉文章</h3>
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

			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?action=insert_handle" name="form1">
				<table border="0">
					<tr>
						<td>會員編號:</td>
						<td>
							<input type="hidden" name="mem_no" name="mem_no" value="${memberVO.mem_no}">
								<c:if test="${memberVO.mem_no !=null}">
								<H3>${memberVO.mem_name}</H3>
							</c:if>
						</td>
					</tr>
					
					<tr>
						<td>檢舉標題:</td>				
						<td>
							<input type="hidden" name="forum_no" size="30" value="${rcVO.forum_no}" />
							<c:forEach var="forumVO" items="${forumSvc.all}">
								<c:if test="${rcVO.forum_no == forumVO.forum_no}">
									<H3>${forumVO.forum_title}</H3>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					
					<tr>
						<td>檢舉理由:</td>
						<td><input type="TEXT" name="rep_content" size="30" value="<%=(rcVO == null) ? "" : rcVO.getRep_content()%>" /></td>
					</tr>
		
					<input type="hidden" name="rc_rep_handle" value="<%=(rcVO == null) ? "0" : rcVO.getRc_rep_handle()%>">
					<input type="hidden" name="rc_col_status" value="<%=(rcVO == null) ? "2" : rcVO.getRc_col_status()%>">
					<input type="hidden" name="rep_rel" value="<%=(rcVO == null) ? "0" : rcVO.getRep_rel()%>">
				</table>
				<br> <input type="hidden" name="button" value="insert_handle">
				<input type="submit" value="送出新增">
			</FORM>
		</div>
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