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
		pageContext.setAttribute("list", list);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>揪團管理</title>

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
		<div class="col-xs-9 col-md-10 col-sm-10">
			<div class="row">
				<jsp:include page="/back-end/left.jsp"></jsp:include>
				<div class="col-xs-9 col-md-10 col-sm-10">
					<div class="container">
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="success">

									<th>揪團編號</th>
									<th>團主</th>
									<!-- 			<th>地區編號</th> -->
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
									<!-- 			<th>人數</th> -->
									<!-- 			<th>揪團公開</th> -->
									<th colspan="3">功能</th>
								</tr>
							</thead>
							<tbody>
								<%@ include file="page1.file"%>
								<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<tr align='center' valign='middle' class="info">
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
										<%-- 									<td><c:forEach var="locVO" items="${locSvc.all}"> --%>
										<%-- 											<c:if test="${actVO.loc_no==locVO.loc_no}"> --%>
										<%-- 					                    	【<font color=orange>${locVO.loc_name}</font>】 --%>
										<%-- 				                   			</c:if> --%>
										<%-- 										</c:forEach></td> --%>
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
										<%-- 									<td>${actVO.act_joinlimit}</td> --%>
										<%-- 									<td>${actVO.act_is_public}</td> --%>
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
<!-- 											<FORM METHOD="post" -->
<%-- 												ACTION="<%=request.getContextPath()%>/joinActivity/joinActivity.do"> --%>
<%-- 												<input type="hidden" name="act_no" value="${actVO.act_no}"> --%>
<%-- 												<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 												<input type="hidden" name="action" -->
<!-- 													value="listMems_ByActno_fromActPage"> <input -->
<!-- 													type="submit" value="送出查詢" class="btn btn-primary"> -->
<!-- 											</FORM> -->
									       <button type="button" class="btn btn-info "  data-toggle="modal" data-target="#myModal">查詢</button>
										 <!-- Modal -->
										  <div class="modal fade" id="myModal" role="dialog">
										    <div class="modal-dialog">
										    
										      <!-- Modal content-->
											      <div class="modal-content">
											        <div class="modal-header">
											          <button type="button" class="close" data-dismiss="modal">&times;</button>
											          <h4 class="modal-title ">${actVO.act_name}</h4>
											        </div>
											        <div class="modal-body">
											      
											          <table class="table">
													   
													        <tr class="success">
														        <th  width="20%">揪團編號</th>
														        <td>${actVO.act_no}</td>
													        </tr>  
													      
													        <c:forEach var="memVO" items="${memSvc.all}">
															<c:if test="${actVO.mem_no==memVO.mem_no}">
														    <tr class="info">
													        	<th >團主</th><td><H3 color=orange>${memVO.mem_name}</H3></td>
													        </tr>
													       	<tr class="info">   
													       		<th >電話</th>
													       		<td>${memVO.mem_phone}</td>
													        </tr>
													        </c:if>
															</c:forEach>
													        <tr class="info">   
													       		<th >地區</th> 
													       		<td>
													       		<c:forEach var="locVO" items="${locSvc.all}">
																<c:if test="${actVO.loc_no==locVO.loc_no}">
				                    						         【<font color=orange>${locVO.loc_name}</font>】
				                    							</c:if>
																</c:forEach>
				                    							</td>
													        </tr>
													        <tr class="info">   
													        <th>揪團地點</th>
													        <td>${actVO.act_loc}</td>
													        </tr>
													        <tr class="info">   
													        <th>開始日期</th>
													        <td>${actVO.act_start_date}</td>
													        </tr>
													        <tr class="info">   
													        <th>結束日期</th>
													        <td>${actVO.act_end_date}</td>
													        </tr>
													        <tr class="info">   
													        <th>內容</th>
													        <td>${(actVO.act_exp=="null" or actVO.act_exp=="")? "無內容" : actVO.act_exp }</td>
													        </tr>
													        <tr class="info">   
													        <th>主題照片</th>
													        <td><img src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}" width="100" height="100" /></td>
													        </tr>
													        <tr class="info">   
													        <th>人數上限</th>
													        <td>${actVO.act_joinlimit}</td>
													        </tr>
													        <tr class="info">   
													        <th>揪團公開</th>
													        <td>${(actVO.act_is_public=="0")? activityMAP["0"] : activityMAP["1"]  }</td>
													        </tr> 
													  </table>
													  
											        </div>
											        <div class="modal-footer">
											          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											        </div>
											      </div>
											      
											    </div>
											  </div>
											<!-- Modal -->
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
							
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<div class="col-xs-1 col-sm-2" id="aaa" style="width:300px;border:3px #cccccc dashed;">		
											<c:forEach var="message" items="${errorMsgs}">
												 <p class="bg-danger">${message}</p>
											</c:forEach>
								</div><!-- 外層  col-sm-2-->		
							</c:if>
							<c:if test="${not empty successMsgs}">
								<div class="col-xs-1 col-sm-2" id="aaa" style="width:300px;border:3px #cccccc dashed;">		
										<c:forEach var="message" items="${successMsgs}">
											<div class="container">
											  <p class="bg-success">${message}</p>
											</div>
										</c:forEach>
								</div><!-- 外層  col-sm-2-->		
							</c:if>
							
		
	</div>
	
</body>
</html>