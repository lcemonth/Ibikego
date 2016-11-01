<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.travel.model.*"%>
<%@ page import="com.travelImage.model.*"%>
<%@ page import="com.travelScore.model.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	TravelVO travelVO = (TravelVO) session.getAttribute("travelVO");
	
	TravelScoreService travelScoreSvc = new TravelScoreService();
	TravelScoreVO total = (TravelScoreVO) travelScoreSvc.getOneTravelScore(travelVO.getTra_no());
	pageContext.setAttribute("total",total);
	
	session.setAttribute("frontLocation", request.getRequestURI());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCXAmhDtb0jrnLSGagfxffHdbDlM362nVw"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/index.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/Login/js/sweetalert.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/sweetalert.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/travelPoint/css/listOneTravel.css">
		<style type="text/css">
			body{
	    		background-color: #0099CC;
			}
			.pre{
				white-space:pre-wrap; /*讓內容維持原排版*/
			}
		</style>
	</head>
	<body>
		<div>
			<c:if test="${not empty errorMsgs}">
	    		<font color='red'>
					<c:forEach var="message" items="${errorMsgs}">
						<script>swal("${message}", "請修正錯誤", "error")</script>
					</c:forEach>
	  			</font>	
			</c:if>
			<c:if test="${not empty warningMsgs}">
	    		<font color='red'>
					<c:forEach var="warning" items="${warningMsgs}">
						<script>swal("${warning}")</script>
					</c:forEach>
	  			</font>	
			</c:if>
		</div>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="container listone">
					<div class="outside">
						<p id="title1">${travelVO.tra_name}</p>
						<jsp:useBean id="travelImageSvc" scope="page" class="com.travelImage.model.TravelImageService" />
						<c:forEach var="travelImageVO" items="${travelImageSvc.all}">
		                    <c:if test="${travelVO.tra_no == travelImageVO.tra_no}">
			                    <img id="image" src="<%=request.getContextPath()%>/image/image.do?tra_img_no=${travelImageVO.tra_img_no}"/>
		                    </c:if>
		                </c:forEach>
		                <div class="content">
							<p class="pre">${travelVO.tra_content}</p>   
						</div>
					</div>
				</div>
			</div>
			<div class="outside2 col-md-5">
				<div>
					<c:if test="${not empty memberVO}">
						<%
							TravelScoreVO scored = (TravelScoreVO) travelScoreSvc.getCheck(travelVO.getTra_no(),memberVO.getMem_no());
							pageContext.setAttribute("scored",scored);
						%>
						<div>
		                    <button id="report" class="btn btn-danger btn-lg">檢舉</button>
		                    <input type="hidden" name="tra_no" value="${travelVO.tra_no}">
		                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
				    	</div>
				    	<c:choose>
		                    <c:when test="${empty scored}">
				                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travelScore/travelScore.do" >	
				                	<button type="submit" class="btn btn-success btn-lg">評分</button>
				                    <input type="hidden" name="action" value="userInsert">
				                    <input type="hidden" name="tra_no" value="${travelVO.tra_no}">
				                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
				                </FORM>
			                </c:when>
			                <c:otherwise>
			                	<button type="submit" class="btn btn-default btn-lg">已評</button>
		                    </c:otherwise>
		                </c:choose>
		                <%
			                ReportcollectService reportcollectSvc = new ReportcollectService();
			                ReportcollectVO collect = (ReportcollectVO) reportcollectSvc.checkTravelCollect(travelVO.getTra_no(),memberVO.getMem_no());
			                pageContext.setAttribute("collect",collect);
		                %>
				    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travel/travel.do" >
							<c:choose>
			                    <c:when test="${(travelVO.tra_no==collect.tra_no)&&(collect.mem_no==memberVO.mem_no)&&(collect.rc_col_status==0)}">
				                    <button type="submit" class="btn btn-default btn-lg">取消收藏</button>
				                    <input type="hidden" name="action" value="userCancelCollect">
				                    <input type="hidden" name="rc_no" value="${collect.rc_no}">
				                    <input type="hidden" name="tra_no" value="${travelVO.tra_no}">
				                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			                    </c:when>
				                <c:otherwise>
				                    <button type="submit" class="btn btn-warning btn-lg">收藏</button>
				                    <input type="hidden" name="action" value="userInsertCollect">
				                    <input type="hidden" name="tra_no" value="${travelVO.tra_no}">
				                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			                    </c:otherwise>
		                    </c:choose>
				    	</FORM>
			    	</c:if>
			    </div>	      
				<div class="content">
					<div class="info">詳細資訊</div>
					<div>【總分】:${total.totalScore}</div>						
					<div id="address">【地址】:${travelVO.tra_add}</div>
					<div>【電話】:${travelVO.tra_tel}</div>
					<div>
						<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
						【發布者】:
						<c:forEach var="memberVO" items="${memberSvc.all}">
		                    <c:if test="${travelVO.mem_no == memberVO.mem_no}">
		                    	${memberVO.mem_name}
		                    </c:if>
		                </c:forEach>
					</div>
					<div>【最後編輯】:${travelVO.tra_cre}</div>
				</div>
			</div>
			<div class="col-md-6" id="map" style="width:900px;height:650px; top:20px; padding: 15px;"></div>
		</div>
	</body>
	<script type="text/javascript">
		$("#report").click(function(){
			swal({
				title: "您確定要檢舉此旅遊點?",
				text: "檢舉內容",
				type: "input",
				showCancelButton: true,
				confirmButtonClass: "btn-danger",
				confirmButtonText: "檢舉此旅遊點",
				cancelButtonText: "不了，謝謝!",
				closeOnConfirm: false,
				inputPlaceholder: "請輸入檢舉內容"
			},
			function(inputValue){
				if(inputValue === false){
					return false;
				}
				if(inputValue === ""){     
					swal.showInputError("請填寫內容");     
					return false;
				}
				$.ajax({
					type: "POST",
					dataType: "text",
					url: "<%=request.getContextPath()%>/travel/travel.do",
					data:{
						action: "userInsertReport",
						tra_no: tra_no(this),
						mem_no: mem_no(this),
						rep_content: inputValue
					},
					success: function(data){
						if("userInsertReport"){
							swal({title:"", text: "\n你的檢舉已收到 ，會盡速處理",timer: 1000,type: "success",showConfirmButton: false});
							return;
						}
					},
					error: function(){
						alert("error");
					}
				});
			});
		});
		function tra_no(e){
			return $("input[name='tra_no']").val();
	    }
		function mem_no(e){
			return $("input[name='mem_no']").val();
	    }
	
		function doFirst(){
			var geocoder = new google.maps.Geocoder();
			var address = document.getElementById("address").innerText;
			geocoder.geocode({ address: address },
			function (result, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					var location = result[0].geometry.location;
					// location.Pa 緯度
					// location.Qa 經度
				} else {
					alert('查無資訊，請輸入正確地址或是自行搜尋位置!');
					document.getElementById('map').display();
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
		window.addEventListener('load',doFirst,false);
	</script>
</html>