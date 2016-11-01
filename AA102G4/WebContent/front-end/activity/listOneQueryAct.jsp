<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.queryact.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.stroke.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
ActivityVO actVO=null;
if(request.getAttribute("actVO")!=null){
	actVO= (ActivityVO) request.getAttribute("actVO");
}
else{
	ActivityService actSvc = new ActivityService();
    actVO = actSvc.getOneAct(Integer.valueOf(request.getParameter("act_no")));
}

Integer maxDay;
if(request.getAttribute("maxDay")!=null){
	maxDay=(Integer)request.getAttribute("maxDay");
}else{
	maxDay=Integer.valueOf(request.getParameter("maxDay"));
}
pageContext.setAttribute("actVO",actVO);
pageContext.setAttribute("maxDay",maxDay); 
session.setAttribute("location", request.getRequestURI()+"?act_no="+actVO.getAct_no()+"&maxDay="+maxDay);
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="strokeSvc" scope="page" class="com.stroke.model.StrokeService" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>揪團資料 - listOneQueryAct.jsp</title>
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
			
			<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCXAmhDtb0jrnLSGagfxffHdbDlM362nVw"></script>
			<style type="text/css">
			    body{
					 background-color: #26a8ff;
				}
				textarea{/* Text Area 固定大小*/
						 max-width:250px;
						 max-height:100px;
						 width:250px;
						 height:100px;
						 margin: 5px;
						}
						img{
				width: 100px;
				height: 100px;
				border-radius:10px 10px 10px 10px;
				}
				.col-md-2{
				/*width: 100%;
				background-color: #fcf;*/
				height: 100px;
				text-align:center;
				}
			</style>
	
		<script type="text/javascript">
				$(function(){
					//getOne_For_Update
					$("input[id^='day']").bind("click",function(){
						
						var idStr = $(this).attr("id");
						var idAry = idStr.split("_");
						sno=<%= actVO.getStroke_no()%> 
						
						if(idAry[0]=="day"){
	
							$("#stroke_no").val(sno);
							$("#whichday").val(idAry[1]);
							var url="<%=request.getContextPath()%>/front-end/activity/select_page_Basic.jsp";
							$.ajax({
						        url: url,
						        type: 'get',
						        data: { "stroke_no": $("#stroke_no").val(),"whichday":$("#whichday").val() },
						        error: function (xhr) {
						            result = false;
						        },
						        success: function (response) {
						        	$("#showPanel").html(response);
						        	
						        	$("div[id^='address']").bind("click",function(){
										 var addr=$(this).text().trim();
											
										 doFirst(addr);	 
									})
										
						        }
						    });
						//	$("#showPanel").load(url,{"stroke_no":$("#stroke_no").val(),"whichday":$("#whichday").val()});
						}
						
					})
					
					
				})
		</script>
		<script type="text/javascript">
		function doFirst(addr){
			var geocoder = new google.maps.Geocoder();
			var address = addr;
			geocoder.geocode({ address: address },
			function (result, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					var location = result[0].geometry.location;
					// location.Pa 緯度
					// location.Qa 經度
				} else {
					alert('詳細位置請自行查詢，感謝您！');
					//document.getElementById('map').display();
				}
				var map = new google.maps.Map(document.getElementById('map'),{ //放地圖的位置
					zoom:14,
					center:location,
					mapTypeId:google.maps.MapTypeId.ROADMAP,
				});
				var marker = new google.maps.Marker({
					position:location,
					map:map,
					title:address,
					animation: google.maps.Animation.BOUNCE
				});		
			});
		}
		//window.addEventListener('load',doFirst,false);
	</script>
	</head>
	
	<body>
		
		<div class="row">
				<jsp:include page="/front-end/top.jsp"></jsp:include>
			</div>
			<div class="col-xs-9 col-md-10 col-sm-10" >
			<div class="row">
	 			<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
	 			 <div class="col-xs-9 col-md-10 col-sm-10">
			<div class="container">
				
	<table  width='100%'>							
		<tr bgcolor='#EA7500' align='center' valign='middle' height='20'>
			<td>
			<a href='<%=request.getContextPath()%>/activity/activity.do?action=backfindex'><img
			   src="<%=request.getContextPath()%>/res/images/c.png"
			   width="100" height="100" border="1">回首頁</a>
			</td>
		</tr>
	</table>
	
	<table class="table" width='100%'>
		<tr class="success" align='center' valign='middle'>
			    <th width='6%'>編號</th>
				<th width='8%'>發起人</th>
				<th width='6%'>地區</th>
				<th width='10%'>行程名稱</th>
				<th width='10%'>揪團名稱</th>
				<th width='10%'>揪團地點</th>
				<th width='10%'>開始日期</th>
				<th width='10%'>結束日期</th>
				<th width='12%'>揪團內容</th>
				<th width='10%'>主題照片</th>
				<th width='6%'>人數</th>
				<th width='6%'>公開</th>
			
			  
		</tr>
		<tbody>
		<tr class="info" align='center' valign='middle'>
				<td>${actVO.act_no}</td>
				<td>
					<c:forEach var="memVO" items="${memSvc.all}" > 
					     <c:if test="${actVO.mem_no==memVO.mem_no}">
			        	 	${memVO.mem_name}
			      		 </c:if>	
		      		 </c:forEach> 
				</td>
				<td>
					<c:forEach var="locVO" items="${locSvc.all}" > 
					     <c:if test="${actVO.loc_no==locVO.loc_no}">
			        	 	${locVO.loc_name}
			      		 </c:if>	
		      		 </c:forEach> 
				</td>
				<td>
					<c:forEach var="strVO" items="${strokeSvc.all}" > 
					     <c:if test="${actVO.stroke_no==strVO.stroke_no}">
			        	 	${strVO.stroke_name}
			      		 </c:if>	
		      		 </c:forEach> 
				</td>
				<td>${actVO.act_name}</td>
				<td>${actVO.act_loc}</td>
				<td>${actVO.act_start_date}</td>
				<td>${actVO.act_end_date}</td>
				<td>
					<textarea readonly rows="6" cols="80">${(actVO.act_exp==null or actVO.act_exp=="" or actVO.act_exp=="null")? "無內容" : actVO.act_exp }</textarea>	
				</td>	
				<td><img src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}" width="100" height="100"/></td>
				<td>${actVO.act_joinlimit}</td>
				<td>
					<c:if test="${actVO.act_is_public==0}">
						${activityMAP["0"]}
					</c:if>
					<c:if test="${actVO.act_is_public==1}">
						${activityMAP["1"]}
					</c:if>
				</td>
				
				
		</tr>
		</tbody>
	</table>
	    
				<%  out.println("共:"+maxDay+"天行程");
					for(int i=1;i<=maxDay;i++){
				%>
				    <input type="button" id="day_<%=i%>" value="第<%=i%>天" class="btn btn-primary">
				<% 		
					}
				%>
			    <div id="showPanel">
				</div>
		
			 <FORM METHOD="get" id="form1"> 
					<input type="hidden" id="stroke_no" name="stroke_no" value="">
					<input type="hidden" id="whichday" name="whichday" value="">
			 </FORM>	
			 <br>
			 <div class="col-md-6" id="map" style="width:1170px;height:550px; top:20px; padding: 15px;"></div>
	         </div>
			</div>
		</div>
	</div>
		
	</body>
	
	
	
</html>
