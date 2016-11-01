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
<!-- 		<script src="http://maps.google.com/maps/api/js?sensor=false"></script> -->
<!-- 		<script src="4_new.js"></script> -->
		<script type="text/javascript"
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvdipJng50BY0PWtOXH1ly8uxDpakKSgo">
		</script>
		<link rel="stylesheet" href="bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<script src="js/index.js"></script>
		<style type="text/css">
		/*地圖*/
		#position{
			height: 100%;
			width: 1600px;
		}
		/*全部*/
		.all{
			height: 720px;
			background-color: #6699CC;
			overflow:auto;	
		}
		/*景點*/
		.scene{
			height: 720px;
			background-color: #66CCFF;
			overflow:auto;
		}
		/*休息站*/
		.reat{
			height: 720px;
			background-color: #66CCFF;	
			overflow:auto;
		}
		/*地圖div*/
		.google{
			height: 900px;
/* 			background-color: #01FF93; */
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
	<body onLoad="inits();">
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="col-md-12 hd"></div>
		<div class="col-md-10 google">
			<div id="map-canvas" style="width:100%; height:768px;"></div>
			<div id="box" style="z-index:1;position:absolute;left:10px;top:10px;background-color:#FFBB00;">
<!-- 				<table width='500' height='500' border='0' > -->
<!-- 					<tr> -->
<!-- 						<td colspan="2" width="35%" height="35%"> -->
<%-- 							<img src="<%=request.getContextPath()%>/image/image.do?tra_img_no=112" width="100%" height="75%"> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td width="18%" height="30">景點名稱:</td> -->
<!-- 						<td width="82%" >永安漁港</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td colspan="2" >景點描述</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td colspan="2" >景點地址</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td colspan="2" ><input type="submit" onclick="informationClose();" value="關閉"></td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
			</div>
			<a href="#test11">123</a>
		</div>
		<div class="col-md-2">
			<div class="tab-content">
				<!--全部、休息站、景點-->
				<ul class="nav nav-tabs row">
					<input type="hidden" name="information" id="information"  value="2"></input>
					<li class="active"><a data-toggle="tab" name="informationSelect" id="informationSelect" href="#all"><b>全部</b></a></li>
					<li><a data-toggle="tab" name="informationSelect" id="informationSelect" href="#scene"><b>景點</b></a></li>
					<li><a data-toggle="tab" name="informationSelect" id="informationSelect" href="#reat"><b>休息站</b></a></li>
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
				<!--景點-->
				<div id="scene" class="scene tab-pane fade row">
					
				</div>
				<!--休息站-->
				<div id="reat" class="reat tab-pane fade row">
					
				</div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	var map;
	var markers=[];//多個標記變數
	var currentInfoWindow="";	//暫時存放當前infowindow視窗
	var mapListOne=[];
	var storage = sessionStorage; //session
	function init(){
		latlng=new google.maps.LatLng(24.967682, 121.191695);
		map=new google.maps.Map(document.getElementById('map-canvas'),{
			zoom:14,//地圖顯示區域大小
			center:latlng,//地圖中心點
			mapTypeId:google.maps.MapTypeId.ROADMAP//地圖形式
		});
		onBounds_changedInit();
	}
    window.addEventListener('load',init,false);
    
    function onBounds_changedInit(){
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/stroke.do",
			data:{"action":"getALLTravel","information":$("#information").val()},
			dataType:"json",
			success:function (maplist){
				mapListOne=maplist;
				doMap(mapListOne);
				allTravel(mapListOne);
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
 	 	          animation: google.maps.Animation.DROP	//1.null 2. google.maps.Animation.DROP  3. google.maps.Animation.BOUNCE
	   		});
 			markers.push(marker);
 		}
 		for(var i=0; i<=(markers.length-1); i++){
 			markers[i].setMap(map);
//  			google.maps.event.addListener(markers[i], 'click', function(event){
// // 					alert(this.getPosition());
//  			});
 			google.maps.event.addListener(markers[i], 'click', function(e){
 				travelInformation(this.getTitle());
// 				mapDisplay(this,mapListOne);
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
	function mapDisplay(marker,mapListOne){	//google提供的訊息視窗
	    if(currentInfoWindow !=""){    
	        currentInfoWindow.close();   
	        currentInfoWindow="";   
	    } 
	    var infowindow = new google.maps.InfoWindow({
			content:"<img src='te.jpg'>Hello World!"+marker.getTitle()
		});
		infowindow.open(map,marker);
		currentInfoWindow=infowindow;
	}
	//右邊顯示
	function allTravel(maplist){
		var str="";
		$('#all').html(str);
		$('#reat').html(str);
		$('#scene').html(str);
		for(var i=0;i<maplist.length;i++){
			str+="<div class='travel-box' >";
			str+="<img src='<%=request.getContextPath()%>/image/image.do?tra_img_no="+maplist[i].tra_no+"'>";
			str+="<p><b>"+maplist[i].tra_name+"</b></p>";
			str+="<div class='box-btn'>";
			str+="<div class='btn-group'>";
			str+="<button type='button' class='btn btn-link' onClick='add("+maplist[i].tra_no+")' >加入</button>";
// 			str+="<button type='button' class='btn btn-link'>移除</button>";
// 			str+="<button type='button' class='btn btn-link'>搜藏</button>";
// 			str+="<button type='button' class='btn btn-link'>檢舉</button>";
			str+="</div>";
			str+="</div>";
			str+="</div>";
		}
		if($("#information").val()==2){
			$('#all').append(str);	
		}else if($("#information").val()==1){
			$('#reat').append(str);
		}else{
			$('#scene').append(str);
		}
		$(".travel-box").hover(function() {
			$(this).find(".box-btn").show();
		}, function() {
			$(this).find(".box-btn").hide();
		});
		
		
		
// 		$(document).ready(function(){	//判斷 
// 			$(this).find("#add").click(function(){
// 				alert(1)
// 			})
// 		})
	}
	function travelInformation(titl){	//跳出該景點的資訊訊息
		for(var i=0;i<mapListOne.length;i++){
			if(mapListOne[i].tra_name==titl){
				var str="";
				$('#box').html(str);
				str+="<table width='500' height='500' border='0' ><tr><td colspan='2' width='35%' height='35%'>"+
				"<img src='<%=request.getContextPath()%>/image/image.do?tra_img_no="+mapListOne[i].tra_no+"' width='100%' height='75%'>"+
				"</td></tr>"+
				"<tr><td width='18%' height='30'>景點名稱:</td><td width='82%' >"+mapListOne[i].tra_name+"</td></tr>"+
				"<tr><td colspan='2' >"+mapListOne[i].tra_content+"</td></tr>"+
				"<tr><td colspan='2' >"+mapListOne[i].tra_add+"</td></tr>"+
				"<tr><td colspan='2' ><input type='submit' onclick='informationClose();' value='關閉'></tr></table>";
				$('#box').append(str);
			}
		}
	}
	function informationClose(){	//關閉視窗並將 標記停止跳動
		for(var i=0; i<markers.length; i++){ 
 			markers[i].setAnimation(null);
 		}
		$('#box').html("");
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
	/********************************************************/
	$(document).ready(function(){	//判斷 
		$(this).find("a").click(function(){
			if($(this).attr("href")=="#all"){
				$("#information").val(2);
			}else if($(this).attr("href")=="#reat"){
				$("#information").val(1);
			}else{
				$("#information").val(0);
			}
	        for (var i = 0; i < markers.length; i++) {   
	            markers[i].setMap(null);   
	        }   
	        markers = []; 
			onBounds_changedInit();
		})
	})
	function add(tra_no){
		alert(tra_no)
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/stroke.do",
			data:{"action":"newAttractions","informationAdd":tra_no},
			dataType:"text",
			success:function (data){
				alert(data)
			},
			error:function(){alert("錯誤")}
		})
	}
</script>