<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Stroke/css/button.css"/>
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
		<script src="<%=request.getContextPath()%>/front-end/Login/js/sweetalert.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/sweetalert.css">
		<script type="text/javascript"
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvdipJng50BY0PWtOXH1ly8uxDpakKSgo">
		</script>
		<title>行程規劃</title>
		
		<style type="text/css">
			/*地圖*/
			#position{
				height: 100%;
				width: 1600px;
			}
			/*全部*/
			.all{
				height: 820px;
				background-color: #6699CC;
				overflow:auto;	
			}
			/*全部*/
			.all{
				height: 820px;
				background-color: #6699CC;
				overflow:auto;	
			}
			/*地圖div*/
			.google{
				height: 900px;
				/*background-color: #01FF93; */
			}
			/*這裡是盒子*/
			.travel-box{
				background-color: #66CCFF;
				text-align:center;
				height: 30%;
				width: 320px;
				border-style:solid;
				border-color:transparent transparent black transparent;
			}
			/*景點圖片*/
			.travel-box img{
				width: 80%;
				height: 70%;
				position:relative; top:20px; left:0px;
			}
			/*為box裡的收藏檢舉加入*/
			.box-btn{
				width: 252px;
				height: 40px;
				position:relative; top:-55px; left:31px;
				background-color: black;
				display:none;
			}
			/*這是標題*/
			p{
				position:relative;
				top:35px;
				color: #01FF93;
			}
			/*這是空白*/
			.hd{
				height: 55px;
			}
			/*消息欄位*/
			#messageBar{
				height: 100px;
				background-color: #66CCFF;
			}
			#daysADD{
				width:45px;
				height:45px;
			}
			#daysTable{
				width:100%;
				height:100%;
			}
		</style>
	</head>
	<body onLoad="inits();">
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
<!-- 			<div class="col-md-6" name="messageBar" id="messageBar"> -->
<!-- <!-- 				<div class="col-md-12"> --> 
<!-- <!-- 					<div>預計騎乘時間:10小時50分鐘34秒</div> --> 
<!-- <!-- 					<div>預計公里數:236公里534公尺</div> --> 
<!-- <!-- 				</div> --> 
<!-- 			</div> -->
<!-- 			<div class="col-md-6"> -->
<!-- 				<div class="col-md-12"> -->
<!-- 					<div class="col-md-2"> -->
<!-- <!-- 						<input type="button" name="daysADD" id="daysADD" value="+"> --> 
<!-- 					</div> -->
<!-- 					<div class="col-md-10"> -->
<!-- 						<table border="1" name="daysTable" id="daysTable"> -->
<!-- 							<tr name="daysTd" id="daysTd"> -->
							
<!-- 							</tr> -->
<!-- 						</table> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="col-md-12">
				<div class="col-md-8 google">
					<div id="map-canvas" style="width:100%; height:880px;"></div>
					<div id="box" style="z-index:1;position:absolute;left:20px;top:10px;background-color:#FFBB00;">
					
					</div>
					
<%-- 					<img src="<%=request.getContextPath()%>/front-end/Stroke/images/myMessage.png" name="myMessage" id="myMessage"  border="0" style='z-index:1;position:absolute;right:40.8%;top:0%; color:#FFF;width:30%;height:20%;opacity:0.4;'> --%>
					
<!-- 					</img> -->
					<a href="<%=request.getContextPath()%>/stroke.do?action=add" class="myButton" style='z-index:1;position:absolute;top:85%;left:5%;'>上一步</a>
					<a href="" class="myButton" data-toggle="modal" data-target="#storage" style='z-index:1;position:absolute;top:85%;left:83%;'>儲存行程</a>
<!-- 					onclick="deletemove(this);" class="btn btn-danger dele" data-toggle="modal" data-target="#additemdetails" -->

					<div class="modal fade" id="storage" role="dialog"><!--跳出視窗-->
						<div class="modal-dialog">
							<div class="modal-content"><!--沒有會透明-->
								<div class="modal-header"><!--有一條線隔開-->
									<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
							    	<h3 class="modal-title">儲存行程</h3><!--modal-title跟右上X不會太開-->
							  	</div>
								<form class="form-horizontal" METHOD="post" action="<%=request.getContextPath()%>/stroke.do?action=insert">
									<div class="form-group">
										<label class="col-sm-3" style="text-align:center;">行程名稱:</label>
										<div class="col-sm-8"><input type="TEXT" name="stroke_name" id="stroke_name" class="form-control"  /></div>
									</div>
									<div class="form-group">
										<label class="col-sm-3" style="text-align:center;">規劃日期:</label>
										<div class="col-sm-8"><input type="TEXT" name="buildDate" id="buildDate" class="form-control" onclick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd'})" readonly /></div>
									</div>
							    	<div class="modal-footer"><!--有一條線隔開-->
										<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
										<input type="hidden" name="item_is_added" value="1">
							    		<input type="submit" class="update btn btn-primary" value="新增"></input>
							      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
							    	</div>
						        </form>
						    </div><!--modal-content-->
						</div>
					</div>
					
					
					
					
					
					
					
					
					
					
					
				</div>
				<div class="col-md-2">
					<div class="tab-content daysDiv">
						<ul class="nav nav-tabs row daysnac">
							<li class="active"><input type="button" name="daysADD" id="daysADD" value="+" onClick="daysADD();"></li>
							<li class="active"><a data-toggle="tab" data-travel-date="1" name="date" id="date" href="#days1" onclick='date(this);'><b>第1天</b></a></li>
						</ul>
						<div id="days1" class="all tab-pane fade in active row">
							
						</div>
						<input type="hidden" name="travelDate" id="travelDate" value="1"></input>
					</div>
				</div>
				<div class="col-md-2">
					<div class="tab-content">
						<!--全部、休息站、景點-->
						<ul class="nav nav-tabs row">
							<li class="active"><a data-toggle="tab" name="informationSelect" id="informationSelect" href="#all"><b>我的旅遊點</b></a></li>
						</ul>
						<!--全部-->
						<div id="all" class="all tab-pane fade in active row">
							<!--景點的盒子可加迴圈-->
		<!-- 					<div class="travel-box"> -->
		<!-- 						<img src="te.jpg"> -->
		<!-- 						<p><b>這是標題</b></p> -->
		<!-- 						<div class="box-btn"> -->
		<!-- 							<div class="btn-group"> -->
		<!-- 							  <button type="button" class="btn btn-link">加入</button> -->
		<!-- 							  <button type="button" class="btn btn-link">搜藏</button> -->
		<!-- 							  <button type="button" class="btn btn-link">檢舉</button> -->
		<!-- 							</div> -->
		<!-- 						</div> -->
		<!-- 					</div> -->
							<!--迴圈結束-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	var directionsService = new google.maps.DirectionsService();
	var directionsDisplay = new google.maps.DirectionsRenderer();		
	var map;
	var mapListOne=[];
	var markers=[];		//多個標記變數
	var planningList=[];//要規劃的路線
	var waypts=[];	//中途點
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
			data:{"action":"strokeTravel"},
			dataType:"json",
			success:function (maplist){
				mapListOne=maplist;
				allTravel(maplist,"#all");	//將mapListOne 放入右邊欄位
				itineraryDays();
			},
			error:function(){
				alert("查詢不到您所加入的旅遊點，系統將自動轉入行程規劃頁面。");
				window.location.href="<%=request.getContextPath()%>/stroke.do?action=add";
			}
		})
	}
	function allTravel(maplist,id){	//右邊規劃行程
		var str="";
		$(id).html(str);
		if(id=="#all"){
			for(var i=0;i<maplist.length;i++){
				str+="<div class='travel-box' >";
				str+="<img src='<%=request.getContextPath()%>/image/image.do?tra_img_no="+maplist[i].tra_no+"'>";
				str+="<p><b>"+maplist[i].tra_name+"</b></p>";
				str+="<div class='box-btn'>";
				str+="<div class='btn-group'>";
// 				str+="<button type='button' class='btn btn-link' onClick='planning("+maplist[i].tra_no+");itineraryDays();' >加入行程</button>";
				str+="<button type='button' class='btn btn-link' onClick='planning("+maplist[i].tra_no+");itineraryDisplay();' >加入行程</button>";
				str+="</div>";
				str+="</div>";
				str+="</div>";
			}
		}else{
			for(var i=0;i<maplist.length;i++){
// 				if((id=="#days1")||(i!=0)){
					str+="<div class='travel-box' >";
					str+="<img src='<%=request.getContextPath()%>/image/image.do?tra_img_no="+maplist[i].tra_no+"'>";
					str+="<p><b>"+maplist[i].tra_name+"</b></p>";
					str+="<div class='box-btn'>";
					str+="<div class='btn-group'>";
					str+="<button type='button' class='btn btn-link' onClick='removePlan("+maplist[i].tra_no+");itineraryDisplay();' >移除行程</button>";
					str+="</div>";
					str+="</div>";
					str+="</div>";
// 				}
			}
		}

		$(id).append(str);
		
// 		if(id=="#all"){
			$(".travel-box").hover(function() {
				$(this).find(".box-btn").show();
			}, function() {
				$(this).find(".box-btn").hide();
			});
// 		}else{
			
// 		}
	}
	function planning(tra_no){	//規劃路線
// 		alert(mapListOne.length)
		for(var i=0;i<mapListOne.length;i++){
			if(mapListOne[i].tra_no==tra_no){
				var title=mapListOne[i].tra_name;
				var position = new google.maps.LatLng(mapListOne[i].tra_lati,mapListOne[i].tra_longi);
	 			var marker = new google.maps.Marker({   
		 	          position: position,   
//	 	 	          icon: icontype,   
		 	          map: map,
		 	          Title:title,
	 	 	          animation: google.maps.Animation.DROP	//1.null 2. google.maps.Animation.DROP  3. google.maps.Animation.BOUNCE
		   		});
	 			marker.setMap(map);
	 			markers.push(marker);
	 			planningList.push(mapListOne[i]);
// 	 			alert("planning::"+mapListOne[i].tra_name)

	 			initialize(planningList);
	 			calcRoute(planningList);	//行程規劃
	 			travelDate(tra_no);	//行程點 放入天數欄位
			}
		}
		/******************************************/
// 		itineraryDays();	//將天數中的行程顯示出來
	}
	function travelDate(tra_no){	//行程點 放入天數欄位
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/stroke.do",
			data:{"action":"travelDate","travelDate":$("#travelDate").val(),"tra_no":tra_no},
			dataType:"json",
			success:function (daysItinerary){
				
			},
			error:function(){alert("錯誤")}
		})
	}
	function initialize(googleMApJson) {
	     //規畫路徑呈現選項
	     var rendererOptions = {suppressMarkers: true};
	     directionsDisplay.setMap(null)
// 	     directionsDisplay.setPanel(null);
	     directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
	     directionsDisplay.setMap(map);
// 	     directionsDisplay.setPanel(document.getElementById("directions_panel"));
//	     directionsDisplay.setPanel(document.getElementById("googleText"));
	}
	function calcRoute(planningList){
// 		for(var i=0;i<planningList.length;i++){
// 			alert("calcRoute::"+planningList[i].tra_name)
// 		}
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
// 			travelMode: google.maps.DirectionsTravelMode.WALKING //11號公車
// 			travelMode: google.maps.DirectionsTravelMode.TRANSIT //公共車 
		};
// 		alert(request.waypoints.length)
		
// 		for(var i=0;i<request.waypoints.length;i++){
// 			alert(request.waypoints[i])
// 		}
		
		directionsService.route(request,function(response,status){
			if (status == google.maps.DirectionsStatus.OK) {	//規畫路徑回傳結果
			  	directionsDisplay.setDirections(response);
				if(planningList.length>=2){
					var secs=0;
					var meters=0;
				  	for(var i=0;i<(planningList.length-1)&&(planningList.length)>=2;i++){
						var duration=directionsDisplay.getDirections().routes[0].legs[i].duration.value;
						var distance=directionsDisplay.getDirections().routes[0].legs[i].distance.value;
						secs+=duration;		//秒累加
						meters+=distance;	//公尺累加
// 						alert(duration)
				  	}
// 				  	<div class="col-md-12">
// 						<div>預計騎乘時間:10小時50分鐘34秒</div>
// 						<div>預計公里數:236公里534公尺</div>
// 					</div>
				  	var str="<div class='col-md-12'>"+
				  	"<div>預計騎乘時間:"+formatSecond((secs*2))+"</div>"+
				  	"<div>預計公里數:"+metricConversion(meters)+"</div>"+
				  	"<div><input type='submit' onclick='informationClose();' value='關閉'></div></div>";
// 					$('#messageBar').html(formatSecond((secs*2))+"     "+metricConversion(meters));
// 				  	$('#messageBar').append(str);
				  	$('#box').html(str);
				}
			}
		});
	}
	function formatSecond(secs) {   //秒轉小時
        var hr = Math.floor(secs / 3600);
        var min = Math.floor((secs - (hr * 3600)) / 60);
        var sec = parseInt( secs - (hr * 3600) - (min * 60));
        while (min.length < 2) { min = '0' + min; }
        while (sec.length < 2) { sec = '0' + min; }
        if (hr) hr += '小時';
        return hr + min + '分鐘' + sec+'秒';
    }
	function metricConversion(meters){	//公尺轉換公里
		var meter=meters;
		var Kilometer=(meter/1000);
		var str="";
		if(meter>=1000){
			str=Math.floor(Kilometer)+"公里"+Math.round(meter%1000)+"公尺";
		}else{
			str=meter+"公尺";
		}
		return str;
	}
	var days=2;	//新增天數
	$(document).ready(function(){
		$("#daysADD").click(function(){	
			var str="";
			str+="<li><a data-toggle='tab' data-travel-date='"+days+"' name='date' id='date' href='#days"+days+"' onclick='date(this);'><b>第"+days+"天</b></a></li>";
			$('.daysnac').append(str);
			$('.daysDiv').append("<div id='days"+days+"' class='all tab-pane fade in active row'>"+days+"</div>");
			days++;
		})
	})
	function date(date){	//更換天數時 判斷天數內的行程
		$("#travelDate").val($(date).attr("data-travel-date"));
		for(var i=0;i<markers.length;i++){
			markers[i].setMap(null);	
		}
		directionsDisplay.setMap(null);
		markers=[];			//旅遊點清空
		planningList=[];	//行程規劃清空
		waypts=[];			//中途點清空
		itineraryDays();	//將天數中的行程顯示出來
// 		$('#box').html("");	//將訊息清除
	}
	function itineraryDays(){	//將天數中的行程顯示出來
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/stroke.do",
			data:{"action":"itineraryDays","travelDate":$("#travelDate").val()},
			dataType:"json",
			success:function (daysItinerary){
// 				mapListOne=daysItinerary;
				allTravel(daysItinerary,("#days"+$("#travelDate").val()));
				for (var i = 0; i < daysItinerary.length; i++) {
// 					alert(daysItinerary[i].tra_no)
					planning(daysItinerary[i].tra_no);
				}
// 				planning(daysItinerary[i].tra_no)
			},
			error:function(){alert("錯誤1")}
		})
	}
	function itineraryDisplay(){	//單純將天數中的行程顯示出來
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/stroke.do",
			data:{"action":"itineraryDays","travelDate":$("#travelDate").val()},
			dataType:"json",
			success:function (daysItinerary){
// 				mapListOne=daysItinerary;
				allTravel(daysItinerary,("#days"+$("#travelDate").val()));
// 				for (var i = 0; i < daysItinerary.length; i++) {
// // 					alert(daysItinerary[i].tra_no)
// 					planning(daysItinerary[i].tra_no);
// 				}
// 				planning(daysItinerary[i].tra_no)
			},
			error:function(){alert("錯誤1")}
		})
	}
	
	
	
	
	/********************************************************/
	//宣告 box 欄位  
	var box;
	function inits(){  
	    //取得 id 為 "box" 物件，並且指定給 box 變數  
	    box = document.getElementById("box");
	    //將 onclick 註冊給 pickup    
	    box.onmousedown = pickup;
	}  
	 //撿起來  
	function pickup(){
	    //設定滑鼠移動時， box 跟著移動  
	    document.onmousemove = move;
	    //將onclick註冊給putdown所以再次按下滑鼠的時候，就是放下      
	    box.onmouseup = putdown; 
	}
	function move(e){
	    if(!e){  
	        e = window.event;  //IE使用
	    }
	    box.style.left = (e.clientX - 100) + "px";//減5只是讓滑鼠留在方塊裡面  
	    box.style.top = (e.clientY - 100) + "px";
	}
	//放下
	function putdown(){
	    document.onmousemove = null;  //把移動事件移除
	    box.onmouseup = pickup;//更改事件處理器 
	}
	function informationClose(){	//關閉視窗並將 標記停止跳動
		for(var i=0; i<markers.length; i++){ 
 			markers[i].setAnimation(null);
 		}
		$('#box').html("");
	}
	/********************************************************/
	
	function removePlan(tra_no){
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/stroke.do",
			data:{"action":"removePlan","travelDate":$("#travelDate").val(),"tra_no":tra_no},
			dataType:"json",
			success:function (list){
			},
			error:function(){alert("錯誤")}
		})
		for(var i=0;i<markers.length;i++){
			markers[i].setMap(null);	
		}
		directionsDisplay.setMap(null);
		markers=[];			//旅遊點清空
		planningList=[];	//行程規劃清空
		waypts=[];			//中途點清空
		itineraryDays();	//將天數中的行程顯示出來
// 		alert($("#travelDate").val())
// 		date(date);
	}
</script>