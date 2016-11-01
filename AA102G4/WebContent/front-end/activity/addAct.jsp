<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.stroke.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
ActivityVO actVO = (ActivityVO) request.getAttribute("actVO");
StrokeService strokeSvc=new StrokeService();
Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
List<StrokeVO> list=strokeSvc.getStrokesByMem_no(mem_no);
pageContext.setAttribute("list",list);
ServletContext servlet = getServletContext();
servlet.getAttribute("activityMAP");
%>
<jsp:useBean id="locSvc" scope="page"
	class="com.location.model.LocationService" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增揪團</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
<script src="<%=request.getContextPath()%>/res/js/upload_show.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvdipJng50BY0PWtOXH1ly8uxDpakKSgo"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var directionsService = new google.maps.DirectionsService();
var directionsDisplay = new google.maps.DirectionsRenderer();
var waypts=[];	//中途點
var planningList=[];//要規劃的路線
var markers=[];		//多個標記變數
var map;
	function ValidateFloat(e, pnumber)
	{
	    if (!/^\d+[.]?\d*$/.test(pnumber))
	    {
	        $(e).val(/^\d+[.]?\d*/.exec($(e).val()));
	    }
	    return false;
	}
	
	function ValidateNumber(e, pnumber)
	{
	    if (!/^\d+$/.test(pnumber))
	    {
	        $(e).val(/^\d+/.exec($(e).val()));
	    }
	    return false;
	}
	
	function vali(){
		   //測試ajax區塊
		    function aa (){
			   var url="<%=request.getContextPath()%>/front-end/activity/ajaxResponse.jsp";
			   $("#div1").load(url,{"param1":document.getElementById('start').value,"param2":document.getElementById('end').value,"mem_no":<%= mem_no%>});
			   var aa=$("#div1").text().trim();
			   var sd=$("#div2").text().trim();
			   var ed=$("#div3").text().trim();
			   var starttime =$("#start").val();
			   var endtime =$("#end").val();
			   if(aa=="此時段已有團，請選其他時間"||sd=="此時間衝突，請選其他時間"||ed=="此時間衝突，請選其他時間"){
				   $("#btn_save").attr('disabled',true);
				  
			   }else{
				   if($("#act_name").val()=="" || $("#act_name").val()==null){
					   $("#btn_save").attr('disabled',true);
					   alert("請輸入揪團名稱"); 
				   }else if($("#act_loc").val()=="" || $("#act_loc").val()==null){
					   $("#btn_save").attr('disabled',true);
					   alert("請輸入揪團地點"); 
				   }else if($("#start").val()=="" || $("#start").val()==null){
					   $("#btn_save").attr('disabled',true);
					   alert("請輸入開始時間"); 
				   }else if($("#end").val()=="" || $("#end").val()==null){
					   $("#btn_save").attr('disabled',true);
					   alert("請輸入結束時間");
				   }else if(Date.parse(starttime).valueOf() > Date.parse(endtime).valueOf()){  
					   alert("注意開始時間不能晚於結束時間！");
				   }else{
					   if($("#act_km").val()=="" || $("#act_km").val()==null){
						   $("#act_km").val(0.0);
					   }	   
				   $("#btn_save").attr('disabled',false);
				   $("#form1").submit();
				   }
			   }
			  
		   };
		   aa();
	}

//test map 
		function doMap1(){
			
	            var latlng = new google.maps.LatLng(25.070649,121.457101);
		 		map = new google.maps.Map(document.getElementById("map-canvas"),{
	 			zoom:11,
	 			center:latlng,
	 			mapTypeId:google.maps.MapTypeId.ROADMAP
 			});
		}
        
        function doMap(maplist){
				
        		var latlng = new google.maps.LatLng(25.070649,121.457101);
        		
        	
				latlng = new google.maps.LatLng(maplist[0].Lat,maplist[0].Lng);	
			
	 		var map = new google.maps.Map(document.getElementById("map-canvas"),{
	 			zoom:11,
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
	 	
			 marker.addListener("mouseover", function() {
		    map.setZoom(8);
		    map.setCenter(marker.getPosition());
		  });
 		
 	}
 	window.addEventListener('load',doMap1,false);
 //test map 
 
 //test stroke
 function strokeTravel(stroke_no){
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/stroke.do",
		data:{"action":"detailedItinerary","stroke_no":stroke_no},
		dataType:"json",
		success:function (maplist){
			planning(maplist);
		},
		error:function(){
		  	   alert("546")
		}
	})
}
function planning(maplist){	//規劃路線

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
//     directionsDisplay.setPanel(document.getElementById("detailedRoute"));
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

function date(){	//更換天數時 判斷天數內的行程
	
	for(var i=0;i<markers.length;i++){
		markers[i].setMap(null);	
	}
	directionsDisplay.setMap(null);
	markers=[];			//旅遊點清空
	planningList=[];	//行程規劃清空
	waypts=[];			//中途點清空
//		$('#box').html("");	//將訊息清除
}



//test stroke
 
$(document).ready(
	 function test(){
	   //神奇小按鈕
	   $("#misbtn").bind("click",function(){
		   $("#act_name").val("中大~龍潭直達抓寵遊");
		   $("#act_loc").val("中大熱血大草原");
		   $("#act_km").val(13.7);
		   $("#start").val("2016-9-9");
    	   $("#end").val("2016-9-9");
    	   //$(t).text('test').val();
    	   $("#myTextarea").val('中大資策會出發騎卡打車至龍潭大池抓寶可夢加欣賞湖景');
    	   $("#act_joinlimit").val(1);

       });	 
	   
	   //--ajax行程

       $("#stroke_no").bind("change",function(){
    	   date(); //清地圖
    	   var stroke_no= this.value;
  
    	   strokeTravel(stroke_no);
       });	   
	   
	   
	   //測試ajax區塊
//        $("#locsel").bind("change",function(){
//     	   var qq= this.value;
//     	   $.ajax({
// 				 type:"POST",
<%-- 				 url:"<%=request.getContextPath()%>/testMap.do", --%>
// 				 data:{"action":"testMap","loc_no":qq},
// 				 dataType:"json",
// 				 success:function (maplist){
// 					 doMap(maplist);
// 			     },error:function(){alert("error")}
// 	        })

//        });	   
	   $("#btn_save").bind("blur",function(){
		   var aa=$("#div1").text().trim();
		   var sd=$("#div2").text().trim();
		   var ed=$("#div3").text().trim();
		   if(aa=="此時段已有團，請選其他時間"||sd=="此時間衝突，請選其他時間"||ed=="此時間衝突，請選其他時間"
		    ||aa=="此時段已有團，請選其他時間，該時段已參加活動，請選其他時間"||aa=="該時段已參加活動，請選其他時間"){
			   $("#btn_save").attr('disabled',true);
			  
		   }else{
			   $("#btn_save").attr('disabled',false);
		   }
	   });
	   
	   
	   $("#btnAjax").click(function (){
		   var url="<%=request.getContextPath()%>/front-end/activity/ajaxResponse.jsp";
		   $("#div1").load(url,{"param1":document.getElementById('start').value,"param2":document.getElementById('end').value,"mem_no":<%= mem_no%>});
		   
	   });
	   
	   $("#btnAjax").bind("blur",function(){
		   var aa=$("#div1").text().trim();
		   var sd=$("#div2").text().trim();
		   var ed=$("#div3").text().trim();
		   if(aa=="此時段已有團，請選其他時間"||sd=="此時間衝突，請選其他時間"||ed=="此時間衝突，請選其他時間"
			||aa=="此時段已有團，請選其他時間，該時段已參加活動，請選其他時間"||aa=="該時段已參加活動，請選其他時間"){
			   $("#btn_save").attr('disabled',true);
		   }else{
			   $("#btn_save").attr('disabled',false);
		   }

	   });
	   
	   
	   $("#start").bind("focus",function(){
		   var url="<%=request.getContextPath()%>/front-end/activity/ajaxResponse_sdate.jsp";
	       $("#div2").load(url,{"param1":document.getElementById('start').value,"mem_no":<%= mem_no%>});
	       
	   });
	   
	   $("#start").bind("blur",function(){
		   var sd=$("#div2").text().trim();
	       if(sd=="此時間衝突，請選其他時間"){
			   $("#btn_save").attr('disabled',true);
		   }else{
			   $("#btn_save").attr('disabled',false);
		   }
	   });
	   
	   $("#end").bind("focus",function(){
		   var url="<%=request.getContextPath()%>/front-end/activity/ajaxResponse_edate.jsp";
	       $("#div3").load(url,{"param2":document.getElementById('end').value,"mem_no":<%= mem_no%>
	});

										});

		$("#end").bind("blur", function() {
			var ed = $("#div3").text().trim();
			if (ed == "此時間衝突，請選其他時間") {
				$("#btn_save").attr('disabled', true);
			} else {
				$("#btn_save").attr('disabled', false);
			}
		});
		
		

})
</script>
<style type="text/css">
    body{
		 background-color: #26a8ff;
	}
	textarea{/* Text Area 固定大小*/
				 max-width:450px;
				 max-height:100px;
				 width:450px;
				 height:100px;
			}
	.col-md-2{
				/*width: 100%;
				background-color: #fcf;*/
				height: 100px;
				text-align:center;
			}
 	.col-sm-12{ 
		height: 27px; 
 	}
 	.changedis{ 
		height: 10px; 
 	}
 	img{
				width: 100px;
				height: 100px;
				border-radius:10px 10px 10px 10px;
	}
	.misbtn{
			 float:right;
	}
</style>
</head>



<body>
	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
	
	<div class="col-sm-12 "></div> <!-- 與TOP的距離 -->
	
	<div class="row">
		<div class="col-xs-9 col-md-10 col-sm-10" >
			<div class="row"> 
				<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
				<div class="col-xs-9 col-md-10 col-sm-10">

				    <!-- 中間資料列-->
				   
					<div class="col-md-7 col-sm-7 ">
						
							<h2>
								發起揪團<small>填寫資料</small>
							</h2>
						
						<table border='1' cellpadding='5' cellspacing='0' width='100%'>
							<tr bgcolor='#EA7500' align='center' valign='middle' height='20'>
								<td><a
									href='<%=request.getContextPath()%>/activity/activity.do?action=backfindex'><img
										src="<%=request.getContextPath()%>/res/images/c.png"
										width="100" height="100" border="1">回首頁</a></td>
						</table>


						<FORM METHOD="post" id="form1" name="form1" ACTION="<%=request.getContextPath()%>/activity/activity.do" enctype="multipart/form-data">

							<table class="table table-bordered table-hover" width='100%'>

								<tr >
									<td bgcolor="lightblue">揪團地點:</td>
									<td bgcolor="#FFC78E"><input type="TEXT" id="act_loc" name="act_loc" size="45"
										value="<%=(actVO == null) ? "請輸入" : actVO.getAct_loc()%>" onfocus="if (value==defaultValue)value=''" onblur="if(!value)value=defaultValue"/></td> 
								</tr>
								<tr>
									<td bgcolor="lightblue">地區:</td>
									<%-- 		<td><input type="TEXT" name="loc_no" size="45"	value="<%=(actVO==null)? "1" : actVO.getLoc_no()%>" /></td> --%>
									<td bgcolor="#FFC78E"><select size="1"  name="loc_no" id="locsel">
											<c:forEach var="locVO" items="${locSvc.all}">
												<option value="${locVO.loc_no}">${locVO.loc_name}
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td bgcolor="lightblue">行程:</td>
									<%-- 		<td><input type="TEXT" name="stroke_no" size="45"	value="<%=(actVO==null)? "" : actVO.getStroke_no()%>" /></td> --%>
									<td bgcolor="#FFC78E"><select size="1" id="stroke_no" name="stroke_no">
											<option value="0">請選擇
											<c:forEach var="strokeVO" items="${list}">
											
												<option value="${strokeVO.stroke_no}">${strokeVO.stroke_name}
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td bgcolor="lightblue">揪團名稱:</td>
									<td bgcolor="#FFC78E"><input type="TEXT" id="act_name" name="act_name" size="45"
										value="<%=(actVO == null) ? "請輸入" : actVO.getAct_name()%>" onfocus="if (value==defaultValue)value=''" onblur="if(!value)value=defaultValue"/></td>
								</tr>
								<tr>
									<td bgcolor="lightblue">揪團資訊:</td>
									<td bgcolor="#FFC78E">
									<textarea rows="6" cols="500" id="myTextarea" name="act_exp" onfocus="if (value==defaultValue)value=''" onblur="if(!value)value=defaultValue"><%=(actVO == null) ? "請輸入" : actVO.getAct_exp()%></textarea>
									</td>
								</tr>
								<tr>
									<td bgcolor="lightblue">開始日期:</td>
									<td bgcolor="#FFC78E"><input type="TEXT" id="start" name="act_start_date"
										size="45"
										" onfocus="WdatePicker({minDate:'%y-%M-{%d+1}',dateFmt:'yyyy-MM-dd'})"
										readonly /> <span id="div2"></span></td>
								</tr>

								<tr>
									<td bgcolor="lightblue">結束日期:</td>
									<td bgcolor="#FFC78E"><input type="TEXT" id="end" name="act_end_date"
										size="45"
										onclick="WdatePicker({minDate:'%y-%M-{%d+1}',dateFmt:'yyyy-MM-dd'})"
										readonly /> <span id="div3"></span>
										<div>
											<input type="button" name="btnAjax" id="btnAjax" value="驗證時段"><span
												id="div1"></span>
										</div> <br></td>
								</tr>

								<tr>
									<td bgcolor="lightblue">揪團公開:</td>
									<td bgcolor="#FFC78E"><select size="1" name="act_is_public">
											<option value="0">${activityMAP["0"]}
											<option value="1">${activityMAP["1"]}
									</select></td>
								</tr>
								<tr>
									<td bgcolor="lightblue">揪團總公里數:</td>
									<td bgcolor="#FFC78E"><input type="TEXT" id="act_km" name="act_km" size="45" maxlength="6"
										value="<%=(actVO == null) ? "0.0" : actVO.getAct_km()%>" 
										style="ime-mode:disabled" onkeyup="return ValidateFloat($(this),value)"/>
									</td>
								</tr>
								<tr>
									<td bgcolor="lightblue">揪團人數上限:</td>
									<td bgcolor="#FFC78E"><input  type="TEXT" id="act_joinlimit" name="act_joinlimit" size="45" maxlength="3"
										value="<%=(actVO == null) ? "1" : actVO.getAct_joinlimit()%>" 
										style="ime-mode:disabled" onkeyup="return ValidateNumber($(this),value)"/>
									</td>
								</tr>
								<tr>
									<td bgcolor="lightblue">揪團主題圖:</td>
									<td bgcolor="#FFC78E">
										<div id="imgDiv2" style="display: none">
											<img id="image2" width="100" height="100" />
										</div> <input type="file" id="myFile1" name="act_photo" size="15">
									</td>
								</tr>
							</table>
							
								 <input type="hidden" name="action" value="addAct">
								 <input type="hidden" name="mem_no" value="<%=mem_no%>"> 
								 <input  type="button" id="btn_save" class="btn btn-lg btn-primary" value="送出新增" onclick="vali();">
						         <div class="misbtn">
						         <input type="button" id="misbtn" class="btn btn-success" value="小按鈕" >
						         </div>
						         <c:if test="${not empty errorMsgs}">
						        	 <div class="col-xs-1 col-sm-2" id="aaa" style="width:400px;border:3px #cccccc dashed;float:right;">
									<%-- 錯誤表列 --%>
												<c:forEach var="message" items="${errorMsgs}">
													 <p class="bg-danger">${message}</p>
												</c:forEach>
									</div>
								</c:if>		
								<c:if test="${not empty successMsgs}">
									<div class="col-xs-1 col-sm-2" id="aaa" style="width:300px;border:3px #cccccc dashed;">
												<c:forEach var="message" items="${successMsgs}">
													<div class="container">
													  <p class="bg-success">${message}</p>
													</div>
												</c:forEach>
									</div>			
								</c:if>
						</FORM>

									<!-- 外層  col-sm-2-->
						
					</div>
					
					<!-- 中間資料列-->
					
					<!-- GOOGLE MAP-->
					<div class="col-md-5 col-sm-5 ">
						<div class="changedis"></div>
						<div id="map-canvas" style="width: 860px; height: 860px"></div>
					</div>
				    <!-- GOOGLE MAP-->
				 
				</div>
			</div> <!-- 內層  col-sm-10 row-->
		</div>
	</div ><!-- 最外層  col-sm-10 row-->
</body>

</html>
