<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<script src="<%=request.getContextPath()%>/back-end/travel/js/picture.js"></script>
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
			td{
				padding: 10px;
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
				<h4>會員資料:</h4>
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
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1" enctype="multipart/form-data">
					<table border="0">
						<tr>
							<td>會員帳號:</td>
							<td><input type="TEXT" name="mem_acc" value="<%= (memberVO==null)? "ffff123":memberVO.getMem_acc()%>"/></td>
						</tr>
						<tr>
							<td>會員密碼:</td>
							<td><input type="password" name="mem_pw" value="<%= (memberVO==null)? "456123":memberVO.getMem_pw()%>"/></td>
						</tr>
						<tr>
							<td>會員姓名:</td>
							<td><input type="TEXT" name="mem_name" value="<%= (memberVO==null)? "我是會員":memberVO.getMem_name()%>"/></td>
						</tr>
						<tr>
							<td>會員暱稱:</td>
							<td><input type="TEXT" name="mem_nickname" value="<%= (memberVO==null)? "哈哈":memberVO.getMem_nickname()%>"/></td>
						</tr>
						<tr>
							<td>會員住址:</td>
							<td><input type="TEXT" name="mem_add" size="30" value="<%= (memberVO==null)? "不知道":memberVO.getMem_add()%>"/></td>
						</tr>
						<tr>
							<td>會員手機:</td>
							<td><input type="TEXT" name="mem_phone" value="<%= (memberVO==null)? "0987654321":memberVO.getMem_phone()%>"/></td>
						</tr>
						<tr>
							<td>會員信箱:</td>
							<td><input type="TEXT" name="mem_email" value="<%= (memberVO==null)? "ffff123@gmail.com":memberVO.getMem_email()%>"/></td>
						</tr>
						<tr>
							<td>會員照片:</td>
							<p><img id="image" width="100"></p>
							<td><input type="file" id="myFile" name="mem_photo" value="<%= (memberVO==null)? "":memberVO.getMem_photo()%>"/></td>
						</tr>
						<tr>
							<td>帳號停權:</td>
							<td>
								<select name="mem_del">
								    <option value="0">否</option>
								    <option value="1">是</option>
								</select>
							</td>
						</tr>
					</table>
					<br>
					<input type="hidden" name="action" value="insert">
					<input type="hidden" name="mem_reg" value="0"/>
					<input type="submit" value="送出新增">
				</FORM>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>