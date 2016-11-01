<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.questionsanswers.model.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
</head>
<body>
	<div class="row">
		<jsp:include page="/back-end/top.jsp"></jsp:include>
	</div>
	<div class="row">
		<jsp:include page="/back-end/left.jsp"></jsp:include>
		<div class="col-md-10">

			<%
				Que_ansService que_ansSvc = new Que_ansService();
				List<Que_ansVO> list = que_ansSvc.getAll();
				pageContext.setAttribute("list", list);
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

			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th>Q&A問題集</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="que_ansVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle'>

						<!--超連結取值 -->
						<td><a href="<%=request.getContextPath()%>/que_ans.do?action=getOne_For_Select_back&que_ans_no=${que_ansVO.que_ans_no}">${que_ansVO.que_ans_q}</a></td>

						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/que_ans.do?action=getOne_For_Update_back">
								<input type="submit" class="btn btn-primary" value="修改">
								<input type="hidden" name=que_ans_no value="${que_ansVO.que_ans_no}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/que_ans.do?action=delete_back">
								<input type="submit" class="btn btn-danger" value="刪除"> <input type="hidden" name=que_ans_no value="${que_ansVO.que_ans_no}"> 
								<input type="hidden" name="action" value="delete_back">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>


			<%@ include file="page2.file"%>

		</div>
	</div>



	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>