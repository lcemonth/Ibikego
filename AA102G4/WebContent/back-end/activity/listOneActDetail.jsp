<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page"
	class="com.location.model.LocationService" />
<%
ActivityVO actVO;
actVO= (ActivityVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
if(actVO!=null){
	session.setAttribute("actVO", actVO);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>揪團資料</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/font-awesome.min.css" rel="stylesheet"/>
<style type="text/css">
.col-md-12 {
	text-align: center;
}

.col-md-2 {
	height: 100px;
	text-align: center;
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
		<div class="col-xs-9 col-md-10 col-sm-10">
			<div class="row">
				<jsp:include page="/back-end/left.jsp"></jsp:include>
				<div class="col-xs-9 col-md-10 col-sm-10">
					<div class="container">
						<div class="container">
					 	<table border='1' cellpadding='5' cellspacing='0' width='1140'>
							<tr bgcolor='#EA7500' align='center' valign='middle' height='20'>
									<td><a href="<%=request.getContextPath()%>/activity/activity.do?action=backIndex"><img src="<%=request.getContextPath()%>/res/images/c.png" width="100" height="100" border="0">回首頁</a></td>
						</table>
					  	<table class="table table-bordered table-hover">
							<tr class="success">
								<th>揪團編號</th>
								<th>團主</th>
								<th>揪團名稱</th>
								<th>揪團地點</th>
								<th>開始日期</th>
								<th>結束日期</th>
								<th>揪團主題照片</th>
							</tr>
							<tr align='center' valign='middle' class="info">
								    <td>${actVO.act_no}</td>
									<td>
										<c:forEach var="memVO" items="${memSvc.all}">
											<c:if test="${actVO.mem_no==memVO.mem_no}">
												<H3 color=orange>${memVO.mem_name}</H3> 電話: ${memVO.mem_phone}
	                    					</c:if>
										</c:forEach>
									</td>
									<td>${actVO.act_name}</td>
									<td>${actVO.act_loc}</td>
									<td>${actVO.act_start_date}</td>
									<td>${actVO.act_end_date}</td>
									<td><img src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
			                        
							</tr>
						</table>
						
						<%if (session.getAttribute("listJoinMemsOnLink")!=null){%>
						      <jsp:include page="/back-end/joinActivity/listJoinMems_ByActno.jsp"/>
						<%} %>

						
	 					
					</div><!-- container-->
				 </div><!--內層 col-xs-9 col-md-10 col-sm-10-->
				</div><!-- 內層外的row-->
			</div><!--外層 col-xs-9 col-md-10 col-sm-10-->
		</div><!-- 下方頁面最外層row-->
	</div>	
			<div class="col-xs-1 col-sm-2" id="aaa" >
				<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
								<div class="container">
									<c:forEach var="message" items="${errorMsgs}">
										 <p class="bg-danger">${message}</p>
									</c:forEach>
								</div>
						</c:if>
						<c:if test="${not empty successMsgs}">
									<c:forEach var="message" items="${successMsgs}">
										<div class="container">
										  <p class="bg-success">${message}</p>
										</div>
									</c:forEach>
						</c:if>
			</div><!-- 外層  col-sm-2-->
					  				
						
						
	
</body>
</html>