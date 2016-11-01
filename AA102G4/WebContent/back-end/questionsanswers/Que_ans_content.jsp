<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.questionsanswers.model.*"%>
<%
	Que_ansVO que_ansVO = (Que_ansVO) request.getAttribute("que_ansVO"); //ForumServlet.java(Concroller), 存入req的empVO物件
%>


<%-- <jsp:useBean id="forumresSvc" scope="page" --%>
<%-- 	class="com.forumresponse.model.ForumresService" /> --%>
<%-- 					<% --%>
<!-- // 					    ForumresService forumresSvc = new ForumresService(); -->
<!-- // 					    List<ForumresVO> list = forumresSvc.getAll(); -->
<!-- // 					    pageContext.setAttribute("list",list); -->
<%-- 					%> --%>
<%-- <% --%>
<!-- 					    ForumresService forumresSvc = new ForumresService(); -->
<!-- 					    List<ForumresVO> list = forumresSvc.getAll(); -->
<!-- 					    pageContext.setAttribute("forumreslist",forumreslist); -->
<!-- %> -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>文章修改完畢</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
.col-md-12 {
	/*width: 100%;
				background-color: #fdf;*/
	text-align: center;
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

.table tr th {
	text-align: center;
}

.empadd {
	text-align: left;
}

.empsearch {
	width: 100% text-align:center;
}
</style>
<%-- 引用js及css --%>
<script src="<%=request.getContextPath()%>/res/js/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>/res/js/calendarcode.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/res/js/ckeditor/contents.css">


</head>
<body bgcolor='white'>

	<div class="row">
		<jsp:include page="/back-end/top.jsp"></jsp:include>
	</div>

	<div class="row">
		<jsp:include page="/back-end/left.jsp"></jsp:include>

		
		
						<h3><%=que_ansVO.getQue_ans_q()%></h3>

	<div class="container">
 		<div class="row">
 			<div class="panel panel-info">
			 	<div class="panel-heading ">
			 	    <h3 class="panel-title">
					<p>Q&A編號${que_ansVO.que_ans_no}</p>
					<table>
						<tr>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/que_ans.do?action=getOne_For_Update_back">
									<input type="submit" class="btn btn-primary" value="修改"> <input type="hidden"name="que_ans_no" value="${que_ansVO.que_ans_no}"> 
									<input type="hidden" name="action" value="getOne_For_Update_back"></FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/que_ans.do?action=delete_back">
									<input type="submit" class="btn btn-danger" value="刪除">
									<input type="hidden" name=que_ans_no value="${que_ansVO.que_ans_no}">
									<input type="hidden" name="action"value="delete_back"></FORM>
							</td>
						</tr>
					</table>
					</h3>
			 	</div>
				
				<div class="panel-body">
			 	  		${que_ansVO.que_ans_q}
			 	</div>
			 				 	
			</div>
			
		</div>
 	</div>
			 		
					
			
<%-- 			${forumreslist.get(0).forumres_cretime} --%>
	
	<div class="container">
		<div class="row">
			<div class="panel panel-info">
			 	<div class="panel-heading ">
			 	    <h3 class="panel-title">		 	    	
							<p>問題</p>
					</h3>		 	  
			 	</div>
			 	<div class="panel-body">
			 	    <p>${que_ansVO.que_ans_a}</p>
				</div>
			 	
			</div>
		</div>
	</div>
			
		</div>
		
</body>
</html>
