<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.blog.model.*"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	BlogVO blogVO = (BlogVO) session.getAttribute("blogVO");
	session.setAttribute("frontLocation", request.getRequestURI());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/index.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/travelPoint/js/picture.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/Login/js/sweetalert.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/sweetalert.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/js/ckeditor/contents.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<style type="text/css">
			body{
		    	background-color: #0099CC;
			}
			.container{
				border: solid 1px black;
				margin: 0px auto;
				border-radius: 15px;
				background-color:#FFFFF0;
				box-shadow: 2px 10px 20px 5px #666666;
				width: 40%;
			}
			#desc{
				max-width:300px;
				max-height:300px;
			}
			td{
				padding: 10px;
			}
			.btn{
				position: relative;
				right: -41%;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-md-12">
				<c:if test="${not empty errorMsgs}">
					<font color='red'>
						<c:forEach var="message" items="${errorMsgs}">
							<script>swal("${message}", "請修正錯誤", "error")</script>
						</c:forEach>
					</font>
				</c:if>
				<div class="container">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do" name="form1" enctype="multipart/form-data">
						<table style="margin: 3% auto;">
							<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
							<tr>
								<td>標題:</td>
								<td><input type="TEXT" name="blog_title" size="28.5" placeholder="Title"/></td>
							</tr>
							<tr>
								<td>內容:</td>
								<td>
									<form id="content" method="post">
										<div id="clob_picture_holder" class="zone"></div>
										<textarea class="ckeditor" id="content" rows="7" cols="50" name="blog_content" placeholder="Content"></textarea>
									</form>
								</td>
							</tr>
							<tr>
								<p><img id="image" width="100%"></p>
								<input type="file" id="myFile" name="blog_photo" required="required" value="<%= (blogVO==null)? "" : blogVO.getBlog_photo()%>"/>
							</tr>
						</table><br>
						<input type="hidden" name="blog_del" value="0"/>
						<input type="hidden" name="blog_cre" value="<%= date_SQL%>">
						<input type="hidden" name="mem_no" value="${memberVO.mem_no}"/>
						<input type="hidden" name="action" value="userInsert">
						<input type="submit" class="btn btn-primary" value="送出">
						<input type="button" class="btn btn-primary" value="取消" onclick="history.back()">
					</FORM>
				</div>
			</div>
		</div>
	</body>
	<script>
		function preview(file){
			if(file.files && file.files[0]){
				var reader = new FileReader();
				reader.onload = function(evt){
					$("#clob_picture_holder").html('<img width="100px" height="60" border="1" src="' + evt.target.result + '" >');
					data = evt.target.result;
				}
				reader.readAsDataURL(file.files[0]);
			}
		}
		$(".various").click(function(){
			var ori = CKEDITOR.instances.content.getData();
			CKEDITOR.instances.content.setData(ori+ "<img width='30%'src='" + data + "'>");
		});
	</script>
</html>