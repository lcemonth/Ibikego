<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.queryact.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.stroke.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
ActivityVO actVO;
actVO= (ActivityVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
pageContext.setAttribute("actVO",actVO);
%>
<%-- List<QueryactVO> qlist = (List<QueryactVO>) request.getAttribute("qlist");
	 pageContext.setAttribute("qlist",qlist);	
--%>

<% 
int maxDay=(Integer)(request.getAttribute("maxDay"));
pageContext.setAttribute("maxDay",maxDay); 
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="strokeSvc" scope="page" class="com.stroke.model.StrokeService" />
<!DOCTYPE html>
<html >
	<head>
		<title>揪團資料</title>
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
	
		<script src="<%=request.getContextPath()%>/res/js/sweetalert2.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/sweetalert2.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.min.css">
		<style type="text/css">
		    body{
				 background-color: #26a8ff;
			}
		    .col-sm-12{ 
				height: 27px; 
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
		
			.table tr th{
				text-align:center;
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
			#aaa{
				position:relative;
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
				   
				  	  var xhr=new XMLHttpRequest();
				  	  xhr.onreadystatechange=function(){
				  	  	if(xhr.readyState==4){
				  	  		if(xhr.status==200){
				  	  			document.getElementById("showPanel").innerHTML=xhr.responseText;
				  	  		}else if(xhr.status==500){
				  	  			xhr.alert("五百拉");	
				  	  		}else
				  	  			xhr.alert(xhr.status);
				  	  	}
				  	  }
				  	  //設定好回呼函數 
				  	  var queryString="?stroke_no="+document.getElementById("stroke_no").value
				  	  +" &whichday="+document.getElementById("whichday").value;
				  	  var url="<%=request.getContextPath()%>/front-end/activity/select_page_Basic.jsp"+queryString;
				  	  //建立好Get連接與送出請求
				  	  xhr.open("get",url,true);
				  	  xhr.send(null);

				}
				
			})
		})
	</script>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		
		<div class="col-sm-12 "></div> <!-- 與TOP的距離 -->
		<br>
		<div class="row">
			<div class="col-xs-9 col-md-10 col-sm-10">		
				<div class="row">
 			<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
 				<div class="col-xs-9 col-md-10 col-sm-10">
					<div class="container">
					 	<table border='1' cellpadding='5' cellspacing='0' width='1140'>
								<tr bgcolor='#EA7500' align='center' valign='middle' height='20'>
								<td>
									<a href="<%=request.getContextPath()%>/activity/activity.do?action=fquery"><img src="<%=request.getContextPath()%>/res/images/c.png" width="100" height="32" border="0">回最新揪團</a>
								</td>	
						</table>
						
						<table border='1'  width='600' class="table table-bordered table-hover">
							<tr class="success">
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
							<tr align='center' valign='middle' class="info">
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
								 <c:if test="${jaVO.joinact_is_join!=1}">    
									 <FORM METHOD="post" id="form" ACTION="<%=request.getContextPath()%>/activity/activity.do">
										    <input type="hidden" id="act_no" name="act_no" value="${actVO.act_no}">
										    <input type="hidden" name="action"value="readyjoin">
										    <input type="hidden" name="maxDay"value=${maxDay}>
										    <input type="submit" value="參加" class="btn btn-primary"> 	
									 </FORM>  
								 </c:if>
								 <c:if test="${jaVO.joinact_is_join==1}">    
									 <FORM METHOD="post" id="form" ACTION="<%=request.getContextPath()%>/activity/activity.do">
										    <input type="button" value="已參加" class="btn btn-primary" disabled='true'> 	
									 </FORM>  
								 </c:if>
								 <br>
								 <c:if test="${not empty successMsgs}">
									<font color='red'>
									<ul>
										<c:forEach var="message" items="${successMsgs}">
											<li>${message}</li>
										</c:forEach>
									</ul>
									</font>
								</c:if>
								 <c:if test="${not empty errorMsgs}">
									<font color='red'>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li>${message}</li>
										</c:forEach>
									</ul>
									</font>
								</c:if>
								<%
								
									String str="";
									int success=0;
									if(request.getAttribute("success")==null){
										
									}else{
										success=(Integer)request.getAttribute("success");
									}
									
								%>
								
							<%-- 	<c:if test="<%=success == 1 %>"> --%>
								
							<%-- 	</c:if> --%>
								<%success =2; %>
						
					     
	 					
					</div><!-- container-->
				 </div><!--內層 col-xs-9 col-md-10 col-sm-10-->
				</div><!-- 內層外的row-->
			</div><!--外層 col-xs-9 col-md-10 col-sm-10-->
		</div><!-- 下方頁面最外層row-->
			
</body>
</html>