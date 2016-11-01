<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="rcSvc" scope="page" class="com.reportcollect.model.ReportcollectService" />
<%  
	List<ActivityVO> list = (List<ActivityVO>) request.getAttribute("list");
    Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
	if(list==null){
	    ActivityService actSvc = new ActivityService();
	    list = actSvc.getMemInvitedActs(mem_no);
	    pageContext.setAttribute("list",list);
	}
	 pageContext.setAttribute("mem_no",mem_no);
	 
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
	
		<script src="<%=request.getContextPath()%>/res/js/sweetalert2.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/sweetalert2.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/activity/css/actbox.css">
		<script type="text/javascript">
			$(function(){
				//getOne_For_Update
				
				$("input[id^='bt']").bind("click",function(){
					
					var idStr = $(this).attr("id");
					var idAry = idStr.split("_");
					$("#act_no").val(idAry[1]);
					if(idAry[0]=="bt1"){
						$("#form1").attr("action","<%=request.getContextPath()%>/activity/activity.do"); 
						$("#form1_action").val("joinAct");
						$("#act_no").val(idAry[1]);
						$("#form1").submit();
					}else if(idAry[0]=="bt2"){
					   	$("#form1").attr("action","<%=request.getContextPath()%>/activity/activity.do"); 
				    	$("#form1_action").val("refuseAct");
				    	$("#act_no").val(idAry[1]);	
						$("#form1").submit();		
					}else{
						$("#form1").attr("action","<%=request.getContextPath()%>/activity/activity.do");
						$("#form1_action").val("queryOneJoinAct");
						$("#act_no").val(idAry[1]);
						$("#form1").submit();
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
			<div class="col-xs-12 col-sm-10">
			<div class="row">
 				<jsp:include page="/front-end/activity/left.jsp"></jsp:include> 
				<div class="col-xs-9 col-sm-10">
					<div class="container">
					 	
					   <h1>被邀請的揪團</h1>
					   	<div class="bg"></div>
						<div class="content"></div>
						<div class="row">
							<c:forEach var="actVO" items="${list}" >
								<div class="col-xs-12 col-sm-3">
									<div class="thumbnail aaa box">
									        <div class="imgloc">
											<img src="<%=request.getContextPath()%>/activity/activityPhoto.do?act_no=${actVO.act_no}" width="100" height="100">
											<p id="pp">被邀請</p>
											</div>
											<div class="caption underimg">
												<h3 style=color:#0066CC><b>${actVO.act_name}</b></h3>
												<p class="txtlim ">
													${(actVO.act_exp==null or actVO.act_exp=="" or actVO.act_exp=="null")? "無內容" : actVO.act_exp }
												</p>
													<input type="button" id="bt1_${actVO.act_no}" value="接受" class="btn btn-primary">
													<input type="button" id="bt2_${actVO.act_no}" value="不接受" class="btn bg-danger">
													<input type="button" id="bt3_${actVO.act_no}" value="查詢" class="btn bg-success">	  						
											</div>
									</div>
								</div>
							</c:forEach>
								
								<FORM METHOD="post" id="form1" ACTION="<%=request.getContextPath()%>/activity/activity.do">
								     <input type="hidden" id="act_no" name="act_no" value="">
								     <input type="hidden" id="form1_action" name="action" value="">
								     <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
								     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
							  	</FORM>
						</div>	
	 					
					</div><!-- container-->
					
				
				</div><!-- 內層  col-sm-10-->
			</div><!-- 內層  col-sm-10 row-->
			</div><!-- 外層  col-sm-10-->
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
		</div><!-- 最外層  col-sm-10 row-->
</body>

<style>
#aaa{
position:relative;
}

</style>
</html>