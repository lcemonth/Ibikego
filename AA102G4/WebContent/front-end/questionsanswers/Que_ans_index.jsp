<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.questionsanswers.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- <%	 --%>
<!-- // 	MemberService memSvc = new MemberService(); -->
<!-- // 	String mem_acctest="qqqq123"; -->
<!-- // 	session.setAttribute("mem_acc",mem_acctest); -->
<!-- // 	String mem_acc=(String)session.getAttribute("mem_acc"); -->
<!-- // 	MemberVO memVO=memSvc.getOneMemByAcc(mem_acc); -->
<!-- // 	Integer mem_no=memVO.getMem_no(); -->
<!-- // 	session.setAttribute("mem_no",mem_no); -->
<%-- %> --%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<style type="text/css">
		
/* 		background-color:#01FF93; */
/* .green{width: 100%; height: 1300px; background-color: #01FF93; */
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

div#box2 {  
   	
	width:120px;height:50px;
/*     background:#BBFFEE; */
    line-height:50px;
    
    
}

.table tr th {
	text-align: center;
}
		</style>
	</head>
<body bgcolor='white'>

	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
	
<!-- 		<div class="row"> -->
<%-- 			<jsp:include page="/front-end/left.jsp"></jsp:include> --%>
	<div class="col-sm-offset-4 col-sm-4" >
		<%
			Que_ansService que_ansSvc = new Que_ansService();
			List<Que_ansVO> list = que_ansSvc.getAll();
			pageContext.setAttribute("list",list);
		%>
		
		<c:if test="${not empty errorMsgs}">
			<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
			</font>
		</c:if>
		
		
		<h3>Q&A問題集</h3>
<%-- 		<a href="<%=request.getContextPath()%>/que_ans.do?action=query_front">回首頁</a> --%>
		
		<%@ include file="page1.file" %>

		<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="false">
			<c:forEach var="que_ansVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<!-- 區塊1 -->
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="tab2">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#${que_ansVO.que_ans_no}" aria-expanded="false" aria-controls="${que_ansVO.que_ans_no}">
								${que_ansVO.que_ans_q}
							</a>
						</h4>
					</div>
					<div id="${que_ansVO.que_ans_no}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab2">
						<div class="panel-body">${que_ansVO.que_ans_a}</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<%@ include file="page2.file" %>
	</div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>