<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.questionsanswers.model.*"%>
<%
	Que_ansVO que_ansVO = (Que_ansVO) request.getAttribute("que_ansVO");
%>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Q&A修改完畢</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			.col-md-12{
				/*width: 100%;
				background-color: #fdf;*/
				text-align:center;
			}
			.col-md-2{
				/*width: 100%;
				background-color: #fcf;*/
				height: 100px;
				text-align:center;
			}
			.col-md-10{
				/*width: 100%;
				background-color: #fdf;*/

			}
			.table tr th{
				text-align:center;
			}
			.empadd{
				text-align: left;
			}
			.empsearch{
				width: 100%
				text-align:center;
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
<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>Q&A資料</h3>
		<a href="<%=request.getContextPath()%>/forum.do?action=query_back"><img src="<%=request.getContextPath()%>/res/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>Q&A編號</th>
		<th>Q&A問題</th>
		<th>Q&A回答</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=que_ansVO.getQue_ans_no()%></td>
		<td><%=que_ansVO.getQue_ans_q()%></td>
		<td><%=que_ansVO.getQue_ans_a()%></td>
	</tr>
</table>
	</div>
</div>
</body>
</html>
