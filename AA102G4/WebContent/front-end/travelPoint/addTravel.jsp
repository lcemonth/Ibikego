<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.travel.model.*"%>
<%@ page import="com.travelImage.model.*"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	TravelVO travelVO = (TravelVO) session.getAttribute("travelVO");
	TravelImageVO travelImageVO = (TravelImageVO) session.getAttribute("travelImageVO");
	
	session.setAttribute("frontLocation", request.getRequestURI());
%>
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
		<script src="<%=request.getContextPath()%>/front-end/travelPoint/js/picture.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/index.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/Login/js/sweetalert.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCXAmhDtb0jrnLSGagfxffHdbDlM362nVw"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/sweetalert.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<style type="text/css">
			body{
		    	background-color: #0099CC;
			}
			.container{
				border: solid 1px white;
				margin: 0px auto;
				border-radius: 15px;
				background-color:#FFFFF0;
				box-shadow: 2px 10px 20px 5px #666666;
				width: 40%;
			}
			#desc{
				max-width:300px;
				max-height:300px;
			}
			td{
				padding: 10px;
			}
			.aa{
				position: relative;
				right: -41%;
			}
			#btnMagic{
				float:right;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-md-12">
				<c:if test="${not empty errorMsgs}">
		    		<font color='red'>
						<c:forEach var="message" items="${errorMsgs}">
							<script>swal("${message}", "請修正錯誤", "error")</script>
						</c:forEach>
		  			</font>	
				</c:if>
				<div class="container">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travel/travel.do" name="form1" enctype="multipart/form-data">
						<table style="margin: 3% auto;">
							<tr><th>旅遊點資料:</th></tr>
							<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
							<tr>
								<td>所屬地區:</td>
								<td>
									<select name="loc_no">
										<option value="1">北部</option>
									    <option value="2">中部</option>
									    <option value="3">南部</option>
									    <option value="4">東部</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>類別:</td>
								<td>
									<select name="tra_class_status">
										<option value="0">景點</option>
									    <option value="1">休息站</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>旅遊點名稱:</td>
								<td><input type="TEXT" name="tra_name" required="required" placeholder="Title"/></td>
							</tr>
							<tr>
								<td>描述:</td>
								<td>
									<textarea id="desc" rows="4" cols="50" required="required" name="tra_content" placeholder="description"></textarea>
								</td>
							</tr>
							<tr>
								<td>電話:</td>
								<td><input type="TEXT" name="tra_tel" placeholder="tel"/></td>
							</tr>
							<tr>
								<td>地址:</td>
								<td><input id="address" type="TEXT" name="tra_add" required="required" placeholder="address"/></td>
							</tr>
							<tr>
								<p><img id="image" width="100%"></p>
								<input type="file" id="myFile" name="tra_img" required="required"/>
							</tr>
						</table><br>
						<input type="hidden" name="tra_del" value="0"/>
						<input type="hidden" name="tra_lati" id="lat"/>
						<input type="hidden" name="tra_longi" id="lon"/>
						<input type="hidden" name="tra_cre" value="<%= date_SQL%>">
						<input type="hidden" name="mem_no" value="${memberVO.mem_no}"/>
						<input type="hidden" name="action" value="userInsert">
						<input type="submit" class="btn btn-primary aa" value="送出">
						<input type="button" class="btn btn-primary aa" value="取消" onclick="history.back()">
						<input type="button" value="神奇小按鈕" class="btn btn-blue" id="btnMagic">
					</FORM>
				</div>
			</div>
		</div>
	</body>
	<script>
		$(document).ready(function(){
			$("#btnMagic").click(function(){
			  	$("input[name=loc_no]").val("1");
			  	$("input[name=tra_class_status]").val("0");
			  	$("input[name=tra_name]").val("中央大學");
			  	$("textarea[name=tra_content]").val("中央大學環境優美，騎車好選擇^_^");
			  	$("input[name=tra_tel]").val("03-4229850");
			  	$("input[name=tra_add]").val("桃園市中壢區中大路300號");
				var geocoder = new google.maps.Geocoder();
				var addr = $("#address").val();
				geocoder.geocode({address:addr},
				function(result,status){
					if(status == google.maps.GeocoderStatus.OK){
			 			var lat = result[0].geometry.location.lat();
						var lon = result[0].geometry.location.lng();
//	 					var lat = JSON.stringify(result[0].geometry.location.lat());
						$('#lat').val(lat);
						$('#lon').val(lon);
					}else{		
						alert('查無資訊，請輸入正確地址或是自行搜尋位置!');
					}
				});
			});
			$("#address").change(function(){
				var geocoder = new google.maps.Geocoder();
				var addr = $("#address").val();
				geocoder.geocode({address:addr},
				function(result,status){
					if(status == google.maps.GeocoderStatus.OK){
			 			var lat = result[0].geometry.location.lat();
						var lon = result[0].geometry.location.lng();
//	 					var lat = JSON.stringify(result[0].geometry.location.lat());
						$('#lat').val(lat);
						$('#lon').val(lon);
					}else{		
						alert('查無資訊，請輸入正確地址或是自行搜尋位置!');
					}
				});
			});
		});
	</script>
</html>