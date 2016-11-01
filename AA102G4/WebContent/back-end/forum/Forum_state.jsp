<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Controller), 存入req的memberVO物件
%>

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
<!DOCTYPE html>
<html lang="">
<head>
<title>會員收藏</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=request.getContextPath()%>/front-end/forum/js/html5shiv.js"></script>
<script src="<%=request.getContextPath()%>/front-end/forum/js/respond.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/forum/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/forum/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/forum/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/forum/js/jquery.cycle.all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
<script src="<%=request.getContextPath()%>/res/js/index.js"></script>
<style type="text/css">

body {padding-top:50px;height:100px;color: #000; background-color:#FFFFFF; }
	.green{width: 100%; height: 100px; background-color: #FFFFFF;}
/* img { width: 100%; } */
	.thumbnail{background-color: #fff; width: 280px; height: 450px;}
	.i{background-color: #1D7F56;}
	.box1{width: 280px;height: 480px; background-color: #FFF;}
	.col-sm-8{
		width: 600px;
		height: 100px;
		background-color: #017F4A;
	}
.col-md-1 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 140px;
	text-align: center;
}

.col-md-2 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 120px;
	text-align: center;
}
.col-md-3 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 40px;
	text-align: center;
}
.col-md-5 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 140px;
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
</head>
<body>
	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
	
	
		<jsp:include page="/front-end/home/homeLeft.jsp"></jsp:include>
	<div class="col-md-6">
		<%
			Integer	mem_no = new Integer(request.getParameter("mem_no"));
			ReportcollectService rcSvc = new ReportcollectService();
			List<ReportcollectVO> list = rcSvc.findBymem_no(mem_no);
			pageContext.setAttribute("list", list);
		%>
<%-- 		<% --%>
<!-- 			ForumService forumSvc = new ForumService(); -->
<!-- 			List<ForumVO> forumlist = forumSvc.mem_getAll(); -->
<!-- 			pageContext.setAttribute("forumlist", forumlist); -->
<!-- 		%> -->
		<h3>揪團騎Bike討論區會員收藏</h3>

		<table class="table table-bordered table-hover table-striped">
			<tr>
				<th>收藏者</th>
				<th>標題</th>
				<th>日期</th>
				<th>取消收藏</th>
			</tr>
			
			<c:forEach var="ReportcollectVO" items="${list}" >

				<div>
					<tr align='center' valign='middle'>
						
							<td>
								<div id="box2">
									<c:forEach var="memberVO" items="${memSvc.all}">
										<c:if test="${ReportcollectVO.mem_no==memberVO.mem_no}">
											${memberVO.mem_name}
										</c:if>
									</c:forEach>
								</div>
							</td>
							<c:forEach var="forumVO" items="${forumSvc.all}">
								<c:if test="${ReportcollectVO.forum_no==forumVO.forum_no}">
								<td>
									<div id="box">
										<a href="<%=request.getContextPath()%>/forum.do?action=getOne_For_Select_front&forum_no=${forumVO.forum_no}">${forumVO.forum_title}</a>
									</div>
								</td>
								<td>
									<div id="box2">${forumVO.forum_cretime}</div>
								</td>
								</c:if>
								 </c:forEach>
							
								<td>
									<c:forEach var="forumVO" items="${forumSvc.all}">
									<c:if test="${(ReportcollectVO.forum_no == forumVO.forum_no) && (ReportcollectVO.mem_no == memberVO.mem_no) && ReportcollectVO.rep_rel == 1}">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?update_status_B">
												<input type="submit" class="btn btn-sm btn-primary" value="取消收藏">
												<input type="hidden" name="rc_no" value="${ReportcollectVO.rc_no}">
												<input type="hidden" name="mem_no" value="${ReportcollectVO.mem_no}">
												<input type="hidden" name="forum_no" value="${ReportcollectVO.forum_no}">
												<input type="hidden" name="rc_rep_handle" value="3">
												<input type="hidden" name="rc_col_status" value="1">
												<input type="hidden" name="rep_rel" value="1">
												<input type="hidden" name="action" value="update_status_B">
											</FORM>			
									 </c:if>
									 </c:forEach>
								 </td>		
					</tr>
				</div>
			</c:forEach>	
		</table>	
	</div>	
	
</body>
</html>