<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.joinactivity.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page"
	class="com.location.model.LocationService" />
<%
		MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
		JoinactivityService jactSvc = new JoinactivityService();
		int cnt = jactSvc.getCntNoSureByMem(memberVO.getMem_no());
		int mem_no=memberVO.getMem_no();
		pageContext.setAttribute("cnt", cnt);
		

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

<script src="js/index.js"></script>
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

.navbar1 {
  background-color: lightblue;
  font-size: 22px;
  width:50px;
  padding: 5px 10px;
}

.button {
  color: white;
  display: inline-block; 
  position: relative; 
  padding: 2px 5px;
}

.button__badge {
  background-color: #fa3e3e;
  border-radius: 2px;
  color: white;
 
  padding: 1px 3px;
  font-size: 10px;
  
  position: absolute; 
  top: 0;
  right: 0;
}

</style>
<script>
    var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var webSocket;
	var count=0;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
		};

		webSocket.onmessage = function(event) {
			
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.cnt;
	        var message2 = jsonObj.mem;
	        var idStr = $("span[id^='button_badge']").attr("id");
	       
	        var idAry = idStr.split("_");
	      
	        if(idAry[2]==message2){
	        	
	        $("#button_badge_<%=mem_no%>").text(message);
	        }
	   
		};

		webSocket.onclose = function(event) {
		};
	}

	function disconnect () {
		webSocket.close();
		
	}
	function updateStatus(newStatus) {
		
	}   
</script>
</head>
<body onload="connect();" onunload="disconnect();">
	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
	<div class="row">
		<div class="col-sm-10">
			<div class="row">
				<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
				<div class="col-sm-10">
					    <c:if test="${(memberVO!=null)}">
						<div class="navbar1">
						  <div class="button">
						    <i class="fa fa-globe"></i>
						    <span id="button_badge_<%=mem_no%>" class="button__badge"><%=cnt%></span>
						  </div>
						  
						</div>
						
						
						 <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();">
						 </c:if>
						 		
				</div>
			</div>
		</div>
	</div>
	
	
	
</body>
</html>