<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<script type="text/javascript"
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvdipJng50BY0PWtOXH1ly8uxDpakKSgo">
		</script>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
<!-- 		<div class="row"> -->
			<div class="col-md-12">
<!-- 				<div class="container"> -->
					<center>
						<div id="map-canvas" style="width:50%; height:620px;"></div>
						<div id="detailedRoute" style="width:50%; left:25%;">
							
						</div>
					</center>
<!-- 				</div> -->
			</div>
<!-- 		</div> -->
	</body>
</html>
<script type="text/javascript">
var directionsService = new google.maps.DirectionsService();
var directionsDisplay = new google.maps.DirectionsRenderer();
var waypts=[];	//中途點
var planningList=[];//要規劃的路線
var markers=[];		//多個標記變數
function init(){
	latlng=new google.maps.LatLng(24.967682, 121.191695);
	map=new google.maps.Map(document.getElementById('map-canvas'),{
		zoom:14,//地圖顯示區域大小
		center:latlng,//地圖中心點
		mapTypeId:google.maps.MapTypeId.ROADMAP//地圖形式
	});
	strokeTravel();
}
window.addEventListener('load',init,false);

function strokeTravel(){
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/stroke.do",
		data:{"action":"detailedItinerary","stroke_no":"${stroke_no}"},
		dataType:"json",
		success:function (maplist){
			planning(maplist);
		},
		error:function(){
			alert("查詢不到您所規劃好的行程，系統將自動轉入前一頁。");
			window.location.href="<%=request.getContextPath()%>/stroke.do?action=query";
		}
	})
}
function planning(maplist){	//規劃路線
//		alert(mapListOne.length)
	for(var i=0;i<maplist.length;i++){
		var title=maplist[i].tra_name;
		var position = new google.maps.LatLng(maplist[i].tra_lati,maplist[i].tra_longi);
 		var marker = new google.maps.Marker({   
	 	         position: position,   
// 	 	         icon: icontype,   
	 	         map: map,
	 	         Title:title,
 	 	         animation: google.maps.Animation.DROP	//1.null 2. google.maps.Animation.DROP  3. google.maps.Animation.BOUNCE
	   	});
 		marker.setMap(map);
 		markers.push(marker);
 		planningList.push(maplist[i]);

 		initialize(planningList);
 		calcRoute(planningList);	//行程規劃
	}
	/******************************************/
//		itineraryDays();	//將天數中的行程顯示出來
}
function initialize(googleMApJson) {
    //規畫路徑呈現選項
    var rendererOptions = {suppressMarkers: true};
    directionsDisplay.setMap(null)
//     directionsDisplay.setPanel(null);
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsDisplay.setMap(map);
    directionsDisplay.setPanel(document.getElementById("detailedRoute"));
//    directionsDisplay.setPanel(document.getElementById("googleText"));
}
function calcRoute(planningList){
	var lengths=(planningList.length-1);
	var start=new google.maps.LatLng(planningList[0].tra_lati,planningList[0].tra_longi);
	var end =new google.maps.LatLng(planningList[lengths].tra_lati,planningList[lengths].tra_longi);
	if(planningList.length>2){
		for (var i=1; i<(planningList.length-1); i++) {
	    	var locationOne=new google.maps.LatLng(planningList[i].tra_lati,planningList[i].tra_longi);
	    	waypts.push({
	        	location: locationOne,
	            stopover: true
	    	});
	    }
	}
	var request = {			//規畫路徑請求
		origin: start,		//起始地
		destination:end ,	//目的地
		waypoints: waypts,	//中途點
		optimizeWaypoints: true,	//重新排序所提供的中間路標
		avoidHighways:true,			//避開公速公路
		travelMode: google.maps.DirectionsTravelMode.DRIVING	//開車
	};
	directionsService.route(request,function(response,status){
		if (status == google.maps.DirectionsStatus.OK) {	//規畫路徑回傳結果
		  	directionsDisplay.setDirections(response);
		}
	});
}
</script>