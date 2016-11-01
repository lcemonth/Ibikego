<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.joinactivity.model.*"%>
<%@ page import="com.member.model.*"%>
<%	
	int cnt=0;
    int mem_no=0;
	if(session.getAttribute("memberVO")!=null){
		MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
		JoinactivityService jactSvc = new JoinactivityService();
	    cnt = jactSvc.getCntNoSureByMem(memberVO.getMem_no());
		mem_no=memberVO.getMem_no();
		pageContext.setAttribute("cnt", cnt);
	}

%>




<html>
<head>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/css/font-awesome.min.css">
<%--  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"/> --%>
<style type="text/css">

.navbar1 {
  background-color: lightblue;
  font-size: 22px;
  width:50px;
  padding: 5px 10px;
  float:right;
}

.xbutton {
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
.btnact{
		border-radius:10px;
		background-color:#66B3FF;
	} 
#ximg{
margin-left:50px;

}
.limg{
		width: 30px;
		height: 30px;
		border-radius:5px 5px 5px 5px;
		position:relative; top:0px; left:-20%;
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
	        	swal({
	        	  title: '提醒!',
	        	  text: '你有一則新消息',
	        	  imageUrl: '<%=request.getContextPath()%>/res/images/d1.png',
	        	  imageWidth: 400,
	        	  imageHeight: 200,
	        	  animation: false
	        	})
	        }
	   
		};

		webSocket.onclose = function(event) {
		};
	}

	function sendMessage(memno,actno,invate) {
		
		var jsonObj = {"mem" :memno,"act":actno,"rel_invite":invate};
		
        webSocket.send(JSON.stringify(jsonObj));
        window.location.reload();
		
    }
	

	function disconnect () {
		webSocket.close();
		
	}
	function updateStatus(newStatus) {
		
	}   
</script>	
	<script src="<%=request.getContextPath()%>/res/js/sweetalert2.js"></script>
	<script src="<%=request.getContextPath()%>/res/js/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.min.css">
</head>
<body onload="connect();" onunload="disconnect();">
			<div class="col-xs-3 col-md-2 col-sm-2">
			   <c:if test="${!not empty memberVO}">
							<div>
								<img src="<%=request.getContextPath()%>/res/images/noName.jpg" width="100" height="100">
								<br><br>
								<a href="<%=request.getContextPath()%>/front-end/activity/act_f_listNewForGuest.jsp" class="list-group-item btnact">訪客瀏覽</a>
							</div>
			   	</c:if>
			   	<c:if test="${not empty memberVO}">
			   		<div>
						   	<img id="ximg" src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memberVO.mem_no}" width="100" height="100">
						    <a href="<%=request.getContextPath()%>/activity/activity.do?action=listInvited">
							   	<div class="navbar1">
								  <div class="xbutton">
								   	 <i > <img class="limg" src="<%=request.getContextPath()%>/res/images/c.png"></i>
								   		  <span id="button_badge_<%=mem_no%>" class="button__badge"><%=cnt%></span>
								  </div>
								</div>
							</a>
					</div>
			   	</c:if>
			   <c:if test="${not empty memberVO}">
				<div class="list-group">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">

						  <!-- 區塊1 -->

						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab1" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#aaa" aria-expanded="true" aria-controls="aaa">
						         	 瀏覽揪團
						        </a>
						      </h4>
						    </div>
						    <div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab1">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
							       	<div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=fquery" class="list-group-item btnact">瀏覽最新揪團</a>
									</div>
									<div>	
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=queryMyJoin" class="list-group-item">我參加的揪團</a>
									</div>
									<div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=listInvited" class="list-group-item btnact">被邀請的揪團</a>
								   </div>	
							   </div>
						      </div>
						    </div>
						  </div>
						
						
						
						  <!-- 區塊2 -->
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab2" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#bbb" aria-expanded="true" aria-controls="bbb">
						         	發起揪團
						        </a>
						      </h4>
						    </div>
						    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab2">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
									<a href="<%=request.getContextPath()%>/activity/activity.do?action=newAct" class="list-group-item btnact">發起揪團</a>
							   </div>
						      </div>
						    </div>
						  </div>
						  
						   <!-- 區塊3 -->
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab3" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#ccc" aria-expanded="true" aria-controls="ccc">
						         	管理揪團
						        </a>
						      </h4>
						    </div>
						    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab3">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
							       <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=updatefAct" class="list-group-item btnact">修改揪團</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=cancelfAct" class="list-group-item ">取消發起</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=manag_memfAct" class="list-group-item btnact">管理揪團成員</a>
								   </div>
							   </div>
						      </div>
						    </div>
						  </div>
						  
						  <!-- 區塊4 -->
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab4" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#ddd" aria-expanded="true" aria-controls="ddd">
						         	歷史紀錄查詢
						        </a>
						      </h4>
						    </div>
						    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab4">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
							       <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=queryLaunchactStatus" class="list-group-item btnact">發起揪團招募狀況</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=queryJoinactStatus" class="list-group-item ">參加揪團招募狀況</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=historyLaunchAct" class="list-group-item btnact">查詢歷史發起紀錄</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=historyJoinAct" class="list-group-item ">查詢歷史參加紀錄</a>
								   </div>
							   </div>
						      </div>
						    </div>
						  </div>
						  
						  <!-- 區塊5 -->
<!-- 						  <div class="panel panel-default"> -->
<!-- 						    <div class="panel-heading" role="tab" id="tab5" style="background-color:pink;"> -->
<!-- 						      <h4 class="panel-title"> -->
<!-- 						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#eee" aria-expanded="true" aria-controls="eee"> -->
<!-- 						         	收藏檢舉測試 -->
<!-- 						        </a> -->
<!-- 						      </h4> -->
<!-- 						    </div> -->
<!-- 						    <div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab5"> -->
<!-- 						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;"> -->
<!-- 						       <div class="list-group"> -->
<!-- 							       <div> -->
<%-- 										<a href="<%=request.getContextPath()%>/activity/activity.do?action=colAndrepForum" class="list-group-item btnact">收藏檢舉討論區</a> --%>
<!-- 								   </div> -->
<!-- 								   <div> -->
<%-- 										<a href="<%=request.getContextPath()%>/activity/activity.do?action=colAndrepTravel" class="list-group-item ">收藏檢舉旅遊點</a> --%>
<!-- 								   </div> -->
<!-- 								   <div> -->
<%-- 										<a href="<%=request.getContextPath()%>/activity/activity.do?action=colAndrepBlog" class="list-group-item btnact">收藏檢舉日誌</a> --%>
<!-- 								   </div> -->
<!-- 								   <div> -->
<%-- 										<a href="<%=request.getContextPath()%>/activity/activity.do?action=colAndrepStroke" class="list-group-item ">收藏檢舉行程</a> --%>
<!-- 								   </div> -->
<!-- 								   <div> -->
<%-- 										<a href="<%=request.getContextPath()%>/activity/activity.do?action=testSocket" class="list-group-item ">測試</a> --%>
<!-- 								   </div> -->
<!-- 							   </div> -->
<!-- 						      </div> -->
<!-- 						    </div> -->
<!-- 						  </div> -->
						  
						
					 </div>
				</div>
			</c:if>
		</div>
			
</body>
</html>			