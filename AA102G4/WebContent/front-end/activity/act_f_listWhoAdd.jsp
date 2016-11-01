<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<%  
	List<ActivityVO> list = (List<ActivityVO>) request.getAttribute("list");
	Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
	if(list==null){
	    ActivityService actSvc = new ActivityService();
	     list = actSvc.getAddacts_by_memno(mem_no);
	    pageContext.setAttribute("list",list);
	}
	
%>
<!DOCTYPE html>
<html >
	<head>
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/activity/css/actbox.css">
		
		
		<script type="text/javascript">
			$(function(){
				//getOne_For_Update
				$("input[id^='bt']").bind("click",function(){
					
					var idStr = $(this).attr("id");
					var idAry = idStr.split("_");

					if(idAry[0]=="bt1"){
						$("#form1").attr("action","<%=request.getContextPath()%>/activity/activity.do"); 
						$("#form1_action").val("getOne_For_Update_f");
						$("#act_no").val(idAry[1]);
					}else if(idAry[0]=="bt2"){

						$("#form1").attr("action","<%=request.getContextPath()%>/activity/activity.do"); 
						$("#form1_action").val("updatePublic");
						$("#act_no").val(idAry[1]);
 						$("#actisp").val(idAry[2]);
						
					}else{
						$("#form1").attr("action","<%=request.getContextPath()%>/activity/activity.do");
						$("#form1_action").val("queryOneJoinAct");
						$("#act_no").val(idAry[1]);
					}
					$("#form1").submit();
				})
			})
		</script>
	</head>
	<body>
	    <div class="col-sm-12 "></div> <!-- 與TOP的距離 -->
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-xs-9 col-md-10 col-sm-10">		
				<div class="row">
 			<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
 				<div class="col-xs-9 col-md-10 col-sm-10">
 				<div class="container">
		   <h1>我發起的揪團</h1>
		   	<div class="bg"></div>
			<div class="content"></div>
			<div class="row">
				<c:forEach var="actVO" items="${list}" >
				
					<div class="col-xs-12 col-sm-3">
						<div class="thumbnail aaa box">
						    <div class="imgloc">
							<img src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}" width="100" height="100">
							<p id="pp">修改</p>
							</div>
							<div class="caption underimg">
								<h3 style=color:#0066CC><b>${actVO.act_name}</b></h3>
								<p class="txtlim">
									${(actVO.act_exp==null or actVO.act_exp=="" or actVO.act_exp=="null")? "無內容" : actVO.act_exp }
								</p>
									<input type="button" id="bt1_${actVO.act_no}" value="修改" class="btn btn-primary">
									<c:if test="${actVO.act_is_public==0}">
										<input type="button" id="bt2_${actVO.act_no}_1" value="設私有" class="btn btn-danger">
									</c:if>
									<c:if test="${actVO.act_is_public==1}">
										<input type="button" id="bt2_${actVO.act_no}_0" value="設公開" class="btn btn-info">
									</c:if>
									<input type="button" id="bt3_${actVO.act_no}" value="查詢" class="btn btn-success">	  						
							</div>
						</div>
					</div>
				
				</c:forEach>
					<FORM METHOD="post" id="form1" ACTION="<%=request.getContextPath()%>/activity/activity.do">
					     <input type="hidden" id="act_no" name="act_no" value="">
					     <input type="hidden" id="form1_action" name="action" value="">
					     <input type="hidden" id="actisp" name="actisp" value="">
				  	</FORM>
			</div>	
			</div><!-- container-->
		</div>
		</div>
		</div>
		</div>		
	
		
	</body>
</html>