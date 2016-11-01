<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html >
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
	</head>
	<body>
		<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
 			<jsp:include page="/back-end/left.jsp"></jsp:include>
			<div class="col-md-10">
				<h4>資料查詢:</h4>
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
				<ul>
				  <li>
				    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do" >
				        <b>輸入日誌編號 (如111):</b>
				        <input type="text" name="blog_no">
				        <input type="submit" value="送出">
				        <input type="hidden" name="action" value="getOne_For_Display">
				    </FORM>
				  </li>
				
				  <jsp:useBean id="blogSvc" scope="page" class="com.blog.model.BlogService" />
				   
				  <li>
				     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do" >
				       <b>選擇日誌編號:</b>
				       <select size="1" name="blog_no">
				         <c:forEach var="blogVO" items="${blogSvc.all}" > 
				          <option value="${blogVO.blog_no}">${blogVO.blog_no}
				         </c:forEach>   
				       </select>
				       <input type="submit" value="送出">
				       <input type="hidden" name="action" value="getOne_For_Display">
				    </FORM>
				  </li>
				  
				  <li>
				     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do" >
				       <b>選擇日誌標題:</b>
				       <select size="1" name="blog_no">
					       <c:forEach var="blogVO" items="${blogSvc.all}" > 
					       	   <option value="${blogVO.blog_no}">${blogVO.blog_title}
					       </c:forEach>   
				       </select>
				       <input type="submit" value="送出">
				       <input type="hidden" name="action" value="getOne_For_Display">
				     </FORM>
				  </li>
				</ul>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>