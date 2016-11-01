<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.stroke.model.*"%>
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page"
	class="com.location.model.LocationService" />
<%
	List<ActivityVO> list = (List<ActivityVO>) request.getAttribute("list");
	Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
	StrokeService strokeSvc=new StrokeService();
	List<StrokeVO> slist=strokeSvc.getStrokesByMem_no(mem_no);
	pageContext.setAttribute("slist", slist);
	if (list == null) {
		ActivityService actSvc = new ActivityService();
		list = actSvc.getAddacts_by_memno(mem_no);
		pageContext.setAttribute("list", list);
	}

%>

<!DOCTYPE html>
<html>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>	
<script src="js/index.js"></script>
<style>
	select { width: 5.5em }
</style>
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
img{
				width: 100px;
				height: 100px;
				border-radius:10px 10px 10px 10px;
			}

</style>
</head>
<body>
	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
<div class="row">
		<div class="col-xs-9 col-md-10 col-sm-10">
			<div class="row">
				<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
				<div class="col-xs-9 col-md-10 col-sm-10">
					<div class="container">
					<div class="grid_10">
            <div class="box round first grid">
                <h2>歷史發起揪團查詢</h2>
                <div class="block">
<!-- 將要使用的網頁放在這個DIV(block)內************************************************************************************************ -->

  <ul>
  <li> 所有揪團活動<a href="<%=request.getContextPath()%>/activity/activity.do?action=listGroups_ByCompositeQuery&mem_no=<%=mem_no%>">
  <input type="button" class="btn btn-success" value="查詢">
  </a></li>
  </ul>
  <input type="hidden" name="mem_no" value="<%=mem_no%>">
  <input type="hidden" name="action" value="listGroups_ByCompositeQuery" >



<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" name="form1">
        <b>選擇揪團名稱:</b>
        <select size="1" name="act_no">
        			<option value="" >--不選--
	         	<c:forEach var="actVO" items="${list}" > 
	          		<option value="${actVO.act_no}">${actVO.act_name}
	         	</c:forEach>   
        </select>	
       <br>    
       <b>揪團地區:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
       <select size="1" name="loc_no">
       				<option value="" >--不選--	
	         	<c:forEach var="locVO" items="${locSvc.all}" > 
	          		<option value="${locVO.loc_no}" >${locVO.loc_name}
	         	</c:forEach>   
       </select>
       <br>
       
       <b>行程:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
       <select size="1" name="stroke_no">
       				<option value="" >--不選--
	         	<c:forEach var="strokeVO" items="${slist}" > 
	          		<option value="${strokeVO.stroke_no}"  >${strokeVO.stroke_name}
	         	</c:forEach>   
       </select>	
       <br>
 
        <b>輸入旅遊日期:</b>
        <br>
		<b>起始</b><input type="TEXT" id="start" name="act_start_date" size="16" " onfocus="WdatePicker({minDate:'%y-%M-{%d+1}',dateFmt:'yyyy-MM-dd'})" readonly />
		<br>
		<b>終止</b><input type="TEXT" id="end" name="act_end_date" size="16" " onfocus="WdatePicker({minDate:'%y-%M-{%d+1}',dateFmt:'yyyy-MM-dd'})" readonly />
		<br>

        <input type="submit" class="btn btn-success" value="送出">
        <input type="hidden" name="mem_no" value="<%=mem_no%>">
        <input type="hidden" name="action" value="listGroups_ByCompositeQuery">
     </FORM>
  </li>
</ul>


 <!-- ************************************************************************************************************************************ -->                   
                </div>
            </div>
        </div>
 <!-- ************************************************************************************************************************************ -->					
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>