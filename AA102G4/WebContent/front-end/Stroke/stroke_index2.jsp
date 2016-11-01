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
		<style type="text/css">
			/*地圖*/
			#position{
				height: 100%;
				width: 1600px;
			}
			/*全部*/
			.all{
				height: 850px;
				background-color: #6699CC;
				overflow:auto;	
			}
			/*休息站*/
			.reat{
				height: 850px;
				background-color: #66CCFF;	
			}
			/*地圖div*/
			.google{
				height: 900px;
				background-color: #01FF93;
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
				position:relative; top:35px;
				color: #01FF93;
			}
			/*這是空白*/
			.hd{
				height: 55px;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-md-10">
				<div id="googleOne"></div>
				<div id="map-canvas" style="width:100%; height:768px; "></div>
			</div>
			<div class="col-md-2">
			<div class="tab-content">
				<!--全部、休息站、景點-->
				<ul class="nav nav-tabs row">
					<li class="active"><a data-toggle="tab" href="#all"><b>全部</b></a></li>
					<li><a data-toggle="tab" href="#reat"><b>休息站</b></a></li>
					<li><a data-toggle="tab" href="#scene"><b>景點</b></a></li>
				</ul>
				<!--全部-->
				<div id="all" class="all tab-pane fade in active row">
					<!--景點的盒子可加迴圈-->
					<div class="travel-box">
						<img src="images/a11.jpg">
						<p><b>這是標題</b></p>
						<div class="box-btn">
							<div class="btn-group">
							  <button type="button" class="btn btn-link">加入</button>
							  <button type="button" class="btn btn-link">搜藏</button>
							  <button type="button" class="btn btn-link">檢舉</button>
							</div>
						</div>
					</div>
					<!--迴圈結束-->
				</div>
				<!--休息站-->
				<div id="reat" class="reat tab-pane fade row">
					
				</div>
				<!--景點-->
				<div id="scene" class="scene tab-pane fade row">
					
				</div>


			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	var map;
	var markers=[];//多個標記變數

	function init(){
		latlng=new google.maps.LatLng(24.967682, 121.191695);
		map=new google.maps.Map(document.getElementById('map-canvas'),{
			zoom:14,//地圖顯示區域大小
			center:latlng,//地圖中心點
			mapTypeId:google.maps.MapTypeId.ROADMAP//地圖形式
		});
		
			//建立改變地圖視窗監聽器  
// 		listenerDragend=google.maps.event.addListener(map, 'dragend', onBounds_changed);//地圖移動停止
// 		listenerZoom_changed=google.maps.event.addListener(map, 'zoom_changed', onBounds_changed);//改變比例大小 
		onBounds_changedInit();		 
			 
	}
    window.addEventListener('load',init,false);
    
    function onBounds_changedInit(){
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/stroke.do",
			data:{"action":"getALLTravel"},
			dataType:"json",
			success:function (maplist){
				doMap(maplist);
			},
			error:function(){alert("錯誤")}
		})
    }
	
 	function doMap(maplist){
 		for(var i=0;i<maplist.length;i++){
 			var title=maplist[i].tra_name;
 			var position = new google.maps.LatLng(maplist[i].tra_lati,maplist[i].tra_longi);
 			var marker = new google.maps.Marker({   
	 	          position: position,   
// 	 	          icon: icontype,   
	 	          map: map,
	 	          Title:title,
	 	         place:"5566",
 	 	          animation: google.maps.Animation.DROP	//1.null 2. google.maps.Animation.DROP  3. google.maps.Animation.BOUNCE
	   		});
 			markers.push(marker);
 		}
 		
 		for(var i=0; i<markers.length; i++){ 
 			markers[i].setMap(map);
//  			google.maps.event.addListener(markers[i], 'click', function(event){
				

// // 					alert(this.getPosition());
//  			});
			
 			google.maps.event.addListener(markers[i], 'click', function(){
 				alert(this.getPosition());
//  				mapDisplay(this.getTitle(map),googleMApJson);
//  			alert(maplist);
//  			mapDisplay(maplist);
 			
 			});//1.選取後顯示資訊視窗
 			google.maps.event.addListener(markers[i], 'click', function(){toggleBounce(this);});//2.選取後跳動地標
 			
 		} 
 	}
 	
	//marker跳動方法
	function toggleBounce(marker) {
		for(var i=0; i<markers.length; i++){   
 			markers[i].setAnimation(null);
 		}
		if(marker.getAnimation()!=null){
			marker.setAnimation(null);
		}else{
			marker.setAnimation(google.maps.Animation.BOUNCE);
		}
	}


// 	function mapDisplay(i,maplist){
// 		alert(maplist.tra_name)
// 		 x=i.indexOf(":");
// 		 i=parseInt(i.substr(0,x)-1);	
		
// 		alert(i)
// 		document.getElementById("googleOne").innerHTML=
// 			'<img  id="buttonClose"  src=""  title="關閉">'+
// 		    '<table  id="googleOneTable"><tr><th colspan="2"></th></tr>'+
// 			'<tr><td class="googleOneTd">類型:</td><td></td></tr>'+
// 			'<tr><td class="googleOneTd">寵物:</td><td>2</td></tr>'+
// 			'<tr><td class="googleOneTd">電話:</td><td>3</td></tr>'+
// 			'<tr><td class="googleOneTd">地址:</td><td>4</td></tr>'+
// 			'<tr><td class="googleOneTd">官網:</td><td>5</td></tr>'+
// 			'<tr><td class="googleOneTd">介紹:</td><td>6</td></tr></table>';
// 	}



			$(function() {
				$(".travel-box").hover(function() {
					$(".box-btn").show();
				}, function() {
					$(".box-btn").hide();
				});
			});



</script>