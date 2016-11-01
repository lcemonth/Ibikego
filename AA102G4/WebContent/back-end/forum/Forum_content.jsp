<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.forumresponse.model.*"%>
<%@ page import="java.util.*"%>


<%
	Integer forum_no = new Integer(request.getParameter("forum_no"));

	ForumService forumSvc = new ForumService();
	ForumVO forumVO = forumSvc.getOneForum(forum_no);
	request.setAttribute("forumVO", forumVO);

	ForumresService forumresSvc = new ForumresService();
	List<ForumresVO> forumreslist = forumresSvc.getOneForumres(forum_no);
	request.setAttribute("forumreslist", forumreslist);
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
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

<link rel="stylesheet" href="<%=request.getContextPath()%>/res/js/ckeditor/contents.css">

</head>
<body bgcolor='white'>

	<div class="row">
		<jsp:include page="/back-end/top.jsp"></jsp:include>
	</div>

	<div class="row">
		<jsp:include page="/back-end/left.jsp"></jsp:include>

		<div class="col-md-10">	
		
	 		<div class="row">
	 			<div class="panel panel-info">
			<h3>${forumVO.forum_title}</h3>
				</div>
			</div>
		
 		<div class="row">
 			<div class="panel panel-default">
			 	<div class="panel-heading ">
			 	    <h3 class="panel-title">
					<p>發文會員</p>
					<p><c:forEach var="memberVO" items="${memSvc.all}">
							<c:if test="${forumVO.mem_no==memberVO.mem_no}">
							${memberVO.mem_name}
							</c:if>
						</c:forEach>
					</p>
					<p>日期:${forumVO.forum_cretime}</p>
					</h3>
			 	</div>
				
				<div class="panel-body">
			 	  		${forumVO.forum_title}
			 	</div>
			 	<div class="panel-body">
			 			${forumVO.forum_content}
			 	</div>
			</div>
			<div>
				<table>
					<tr>
<!-- 						<td> -->
<%-- 			 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?action=getOne_For_Update_back"> --%>
<%-- 								<input type="submit" value="修改"> <input type="hidden"name="forum_no" value="${forumVO.forum_no}">  --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Update_back"> -->
<!-- 							</FORM> -->
<!-- 						</td> -->
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?action=delete_back">
								<input type="submit" class="btn btn-danger" value="刪除">
								<input type="hidden" name=forum_no value="${forumVO.forum_no}">
								<input type="hidden" name="action"value="delete_back">
							</FORM>
						</td>
					</tr>
				</table>
 			</div>
		</div>
			 		
					
			
<%-- 			${forumreslist.get(0).forumres_cretime} --%>
			<c:forEach var="forumresVO" items="${forumreslist}">
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading ">
							<h3 class="panel-title">
								<p>回應會員</p>
								<p>
									<c:forEach var="memberVO" items="${memSvc.all}">
										<c:if test="${forumresVO.mem_no==memberVO.mem_no}">
										${memberVO.mem_name}
										</c:if>
									</c:forEach>
								</p>
								<p>日期:${forumresVO.forumres_cretime}</p>
							</h3>
						</div>
						<div class="panel-body">${forumresVO.forumres_con}</div>
						<div class="col-sm-3">
							<table>
								<tr>
<!-- 									<td> -->
<!-- 										<FORM METHOD="post" -->
<%-- 											ACTION="<%=request.getContextPath()%>/forumres.do?action=getOne_For_Update_back"> --%>
<!-- 											<input type="submit" value="修改"> <input type="hidden" -->
<%-- 												name="forum_no" value="${forumVO.forum_no}"> <input --%>
<!-- 												type="hidden" name="action" value="getOne_For_Update_back"> -->
<!-- 										</FORM> -->
<!-- 									</td> -->
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumres.do?action=delete_back">
											<input type="submit" class="btn btn-danger" value="刪除">
											<input type="hidden" name=forum_no value="${forumVO.forum_no}">
											<input type="hidden" name=forumres_no value="${forumresVO.forumres_no}">
											<%--value="${forumresVO.forumres_no}" --%>
										</FORM>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
