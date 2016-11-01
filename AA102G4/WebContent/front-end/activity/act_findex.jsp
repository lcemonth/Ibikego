<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page"
	class="com.location.model.LocationService" />
<%
	List<ActivityVO> list = (List<ActivityVO>) request.getAttribute("list");
	if (list == null) {
		ActivityService actSvc = new ActivityService();
		list = actSvc.getAll();
	}
	pageContext.setAttribute("list", list);
	session.setAttribute("location", request.getRequestURI());
// 	String mem_acctest = "yyyy123";
// 	session.setAttribute("mem_acc", mem_acctest);
// 	String mem_acc = (String) session.getAttribute("mem_acc");
// 	MemberVO memVO = memSvc.getOneMemByAcc(mem_acc);
// 	session.setAttribute("memVO", memVO);
// 	Integer mem_no = memVO.getMem_no();
// 	session.setAttribute("mem_no", mem_no);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
<style type="text/css">
body{
	 background-color: #26a8ff;
}
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


</style>
</head>
<body>
	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
<div class="row">
		<div class="col-md-10">
			<div class="row">
				<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
				<div class="col-md-10">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>

								<th>揪團編號</th>
								<th>團主</th>
								<th>地區編號</th>
								<!-- 			<th>行程編號</th> -->
								<th>揪團名稱</th>
								<th>揪團地點</th>
								<th>開始日期</th>
								<th>結束日期</th>
								<!-- 			<th>揪團內容</th> -->
								<th>揪團主題照片</th>
								<!-- 			<th>揪團路線圖</th> -->
								<!-- 			<th>揪團經緯圖</th> -->
								<!-- 			<th>公里數</th> -->
								<th>人數</th>
								<th>揪團公開</th>

							</tr>
						</thead>
						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<tr align='center' valign='middle'>
									<%-- 				<td>${actVO.act_no}</td> --%>
									<td><a
										href="<%=request.getContextPath()%>/activity/activity.do?act_no=${actVO.act_no}&action=linkOneFromAct_index">${actVO.act_no}</a></td>
									<%-- 				<td>${actVO.mem_no}</td> --%>
									<td><c:forEach var="memVO" items="${memSvc.all}">
											<c:if test="${actVO.mem_no==memVO.mem_no}">
												<H3 color=orange>${memVO.mem_name}</H3> Tel: ${memVO.mem_phone}
	                    </c:if>
										</c:forEach></td>
									<%-- 				<td>${actVO.loc_no}</td> --%>
									<td><c:forEach var="locVO" items="${locSvc.all}">
											<c:if test="${actVO.loc_no==locVO.loc_no}">
		                    	【<font color=orange>${locVO.loc_name}</font>】
	                    </c:if>
										</c:forEach></td>
									<%-- 				<td>${actVO.stroke_no}</td> --%>
									<td>${actVO.act_name}</td>
									<td>${actVO.act_loc}</td>
									<td>${actVO.act_start_date}</td>
									<td>${actVO.act_end_date}</td>
									<%-- 				<td>${actVO.act_exp}</td> --%>
									<td><img
										src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}"
										width="100" height="100" /></td>
									<%-- 	            <td><img src="<%=request.getContextPath()%>/activity/activityRoute.do?act_no=${actVO.act_no}" width="100" height="100"/></td> --%>
									<%-- 	            <td><img src="<%=request.getContextPath()%>/activity/activityAlti.do?act_no=${actVO.act_no}" width="100" height="100"/></td> --%>
									<%-- 				<td>${actVO.act_km}</td> --%>
									<td>${actVO.act_joinlimit}</td>
									<td>${actVO.act_is_public}</td>


									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/activity/activity.do">
											<input type="hidden" name="act_no" value="${actVO.act_no}">
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="action" value="getOne_For_Update">
											<input type="submit" value="修改" class="btn btn-primary">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/activity/activity.do">

											<input type="hidden" name="act_no" value="${actVO.act_no}">
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="action" value="delete"> <input
												type="submit" value="刪除" class="btn btn-primary">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/joinActivity/joinActivity.do">
											<input type="hidden" name="act_no" value="${actVO.act_no}">
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="action"
												value="listMems_ByActno_fromActPage"> <input
												type="submit" value="送出查詢" class="btn btn-primary">
										</FORM>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="page2.file"%>

				</div>
			</div>
		</div>
	</div>
</body>
</html>