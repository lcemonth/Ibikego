<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.questionsanswers.model.*"%>
<%
	Que_ansVO que_ansVO = (Que_ansVO) request.getAttribute("que_ansVO");
%>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
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
<script src="<%=request.getContextPath()%>/back-end/questionsanswers//js/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>/back-end/questionsanswers//js/calendarcode.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/res/js/ckeditor/contents.css">

<title>Q&A新增</title>
</head>
<script language="JavaScript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/questionsanswers/css/calendar.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor="white">

	<div class="row">
		<jsp:include page="/back-end/top.jsp"></jsp:include>
	</div>

	<div class="row">
		<jsp:include page="/back-end/left.jsp"></jsp:include>

		<div class="col-md-10">

			
						<h3>Q&A新增</h3>
	
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

			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/que_ans.do?action=insert_back" name="form1">
				<table border="0">

					<tr>
						<td>Q&A問題:<input type="TEXT" name="que_ans_q" size="30" value="<%=(que_ansVO == null) ? "" : que_ansVO.getQue_ans_q()%>" /></td>
					</tr>

					<tr>
						<td>
							<form id="content" method="post">
								<div id="clob_picture_holder" class="zone"></div>
								<input type="file" onchange="preview(this)" style="width: 200px" />
								<a id="v0" class="various" href="javascript:void(0)"><img
									width="30" src="<%=request.getContextPath()%>/back-end/questionsanswers/images/image.png"
									title="插入圖片"></a>
								<textarea class="ckeditor" id="content" rows="6" cols="35" name="que_ans_a"><%=(que_ansVO == null) ? "" : que_ansVO.getQue_ans_a()%></textarea>
							</form>
						</td>
					</tr>
				</table>
				<br> 
				<input type="hidden" name="button" value="insert_back">
				<input type="submit" value="送出新增">
			</FORM>
			
		</div>
	</div>
<script>

//履歷上傳-預覽用
	function preview(file) {
	
	    if (file.files && file.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (evt) {
	            $("#clob_picture_holder").html( '<img width="100px" height="60" border="1" src="' + evt.target.result + '" >');
	        	data = evt.target.result;
	        }
	        reader.readAsDataURL(file.files[0]);
	    }
	}
	$(".various").click(function(){
		var ori = CKEDITOR.instances.content.getData();
		CKEDITOR.instances.content.setData(ori+"<img width='30%'src='"+data+"'>");
	});
</script>
</body>
</html>
