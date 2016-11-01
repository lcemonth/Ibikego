<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
		<script type="text/javascript"
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvdipJng50BY0PWtOXH1ly8uxDpakKSgo">
		</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			.col-md-12{
				/*width: 100%;
				background-color: #fdf;*/
				text-align:center;
			}
			.col-md-2{
				/*width: 100%;
				background-color: #fcf;*/
				height: 100px;
				text-align:center;
			}
			.col-md-10{
				/*width: 100%;
				background-color: #fdf;*/

			}
			.table tr th{
				text-align:center;
			}
			.empadd{
				text-align: left;
			}
			.empsearch{
				width: 100%
				text-align:center;
			}
		</style>
	</head>
	<body >
		<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
<%-- 			<jsp:include page="/back-end/left.jsp"></jsp:include> --%>
            
			<div class="col-sm-2">
				這是測試頁面
			</div>
			<div class="col-sm-10">
				<input type="button" id="clickAll" value="test"/>
				<div name="emp" id="emp"></div>
				<div id="map-canvas" style="width: 1000px; height: 630px"></div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>
<script type="text/javascript">
	$(document).ready(
		$("#clickAll").click(function(){
			 $.ajax({
				 type:"POST",
				 url:"<%=request.getContextPath()%>/test.do",
				 data:{"action":"getEmpVO"},
				 dataType:"json",
				 success:function (maplist){
					 doMap(maplist);
					 initialize();
// 					 alert(data[0].emp_name+" "+data[0].emp_acc+" "+data[0].emp_pw+" "+data[0].emp_tel);
// 					 tableadd(maplist);
			     }
	        })
		})
	)
// 	function tableadd(maplist){
// 		var str="<table class='table table-bordered table-hover'>";
// 		for(var i=0;i<maplist.length;i++){
// 			str+="<tr>";
// 			str+="<td>"+maplist[i].emp_name+"</td>";
// 			str+="<td>"+maplist[i].emp_acc+"</td>";
// 			str+="<td>"+maplist[i].emp_pw+"</td>";
// 			str+="<td>"+maplist[i].emp_tel+"</td>";
// 			str+="</tr>";
// 		}
// 		document.getElementById("emp").innerHTML=str;
// 	}
 	function doMap(maplist){
 		var latlng = new google.maps.LatLng(24.970826,121.1882077);
 		var latlng1 = new google.maps.LatLng(24.970826,121.2082077);
 		var map = new google.maps.Map(document.getElementById("map-canvas"),{
 			zoom:14,
 			center:latlng,
 			mapTypeId:google.maps.MapTypeId.ROADMAP
 		});
// 		alert(maplist[0].Lat);
 		for(var i=0;i<maplist.length;i++){	//maplist.length
 			var marker = new google.maps.Marker({
//  	 			position:latlng,	//標記位址
 	 			position:{ lat: maplist[i].Lat, lng: maplist[i].Lng},	//標記位址
 	 			map:map,
 	 			//draggable:true,	//座標可以移動
 	 			//icon:"test.jpg",
 	 			title:maplist[i].title
 	 		});
 		}


 		
 		var marker = new google.maps.Marker({
 			position:latlng,	//標記位址
 			map:map,
 			//icon:"test.jpg",
 			title:'這不是我家'
 		});
 		var marker = new google.maps.Marker({
 			position:latlng1,	//標記位址
 			map:map,
 			//icon:"test.jpg",
 			title:'這是我家'
 		});
		 marker.addListener("mouseover", function() {
	    map.setZoom(8);
	    map.setCenter(marker.getPosition());
	  });
 		
 	}
 	window.addEventListener('load',doMap,false);
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
</script>