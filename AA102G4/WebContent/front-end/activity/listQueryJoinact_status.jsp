<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.stroke.model.*"%>
<%	
	ActivityService actSvc = new ActivityService();
	StrokeService strokeSvc=new StrokeService();
	Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
	List<ActivityVO> list = actSvc.getMemJoinedActs(mem_no);
	MemberVO memVO= (MemberVO)session.getAttribute("memVO");
	List<StrokeVO> slist=strokeSvc.getStrokesByMem_no(mem_no);
	pageContext.setAttribute("list",list);
	pageContext.setAttribute("slist",slist);
%>
 <jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />

<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>發起揪團的招募狀況</title>
		
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
			img{
				width: 100px;
				height: 100px;
				border-radius:10px 10px 10px 10px;
			}
		</style>
	</head>
	<body>
		<jsp:useBean id="jactSvc" scope="page" class="com.joinactivity.model.JoinactivityService" />
			<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-xs-9 col-md-10 col-sm-10">		
				<div class="row">
 			<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
 				<div class="col-xs-9 col-md-10 col-sm-10">
			        <div class="container">
			
		<h2>			
		<%@ include file="page1.file" %> 		
		</h2>		
		<table class="table">
		<tr class="danger" align='center' valign='middle'>
		   
				<th width='8%'>編號</th>
				<th width='10%'>發起人</th>
				<th width='8%'>地區</th>
				<th width='10%'>行程名稱</th>
				<th width='10%'>揪團名稱</th>
				<th width='10%'>揪團地點</th>
				<th width='10%'>開始日期</th>
				<th width='10%'>結束日期</th>
				<th width='10%'>揪團主題照片</th>
				<th width='7%'>目前</th> 
				<th width='7%'>上限</th>
				
		
			
		</tr>	
 	<tbody>
		<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr class="info" align='center' valign='middle'>
				<td>${actVO.act_no}</td>
				<td>
					<c:if test="${actVO.mem_no==memberVO.mem_no}">
						${memberVO.mem_name}
					</c:if>	
				</td>
				<td> 
				     <c:forEach var="locVO" items="${locSvc.all}" > 
					     <c:if test="${actVO.loc_no==locVO.loc_no}">
			        	 	${locVO.loc_name}
			      		 </c:if>	
		      		 </c:forEach> 
	      		</td>
				<td> 
				     <c:forEach var="strokeVO" items="${slist}" >  
					     <c:if test="${actVO.stroke_no==strokeVO.stroke_no}">
			        	 	${strokeVO.stroke_name}
			      		 </c:if>	
		      		 </c:forEach> 
	      		</td>
				<td>${actVO.act_name}</td>
				<td>${actVO.act_loc}</td>
				<td>${actVO.act_start_date}</td>
				<td>${actVO.act_end_date}</td>
				<td><img src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
				<td>${jactSvc.getCntMemsByAct(actVO.act_no) }人</td>
				<td>${actVO.act_joinlimit}人</td>
				
				
				
				
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@ include file="page2.file" %>
						</div>
					</div>
				</div><%--end container's row --%>
			</div><%--end container --%>
		</div><%--end row left.jsp --%>
		
	</body>
</html>