<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Controller), 存入req的memberVO物件 
%> 
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="res/js/html5shiv.js"></script>
		<script type="text/javascript" src="res/js/respond.min.js"></script>
		<script type="text/javascript" src="res/js/jquery.js"></script>
		<script type="text/javascript" src="res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="res/js/jquery.cycle.all.js"></script>
		<link rel="stylesheet" href="res/css/bootstrap.min.css">
		<link rel="stylesheet" href="res/css/index.css">
		<link rel="stylesheet" href="res/css/font-awesome.min.css" rel="stylesheet"/>
		<style type="text/css">
		/*這是空白*/
		body{
			background-color: #ededed;
		}
		.hd{
			height: 200px;
		}
		/*盒子連在一起*/
		.panel-group .panel{
		    border:0px none;
		    border-radius: 0px;
		    box-shadow: none;
		    border-bottom: 1px solid #676767;
		}
		/*標題的div*/
		.panel-heading{
		    padding: 0;
		}
		
		/*標題外的div*/
		.panel-title > a{
		    display: block;
		    padding: 11px 35px;
		    background: #fff;
		    font-weight: 600;
		    text-decoration:none;

		}
		/*變紅色的div*/
		.box{
			transition-duration: 0.4s;/*變色秒數*/
			width: 60px; 
			height: 60px;
			border-radius:10px 10px 10px 10px;/*圓角*/
			cursor:pointer;/*變手*/
			box-shadow: 0px 0px 2px 2px #99CCFF;
			font-size: 24px;
			text-align:center;/*置中*/
			line-height:60px;
			display: inline-block;
			margin:0px 30px 0px 0px;/*外邊距離*/


		}
		/*刪除button*/
		.dele{
			position:relative; top:0px; left:82%;/*絕對位置*/
		}
		.a1{
			background-color: #67cad2; 
			float:right; 
			height:60%; 
			width: 80px;
			transition: height 0.4s;
			border-radius:0px 0px 10px 10px;/*圓角*/
			position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:176px;/*右邊*/
		}
		.a1:hover{
			height:70%;
			box-shadow:   5px 5px 15px #888888;/*陰影*/

		}
		.b1{
			background-color: #f86e8f; 
			float:right; 
			height:60%; 
			width: 80px;
			transition: height 0.4s;
			border-radius:0px 0px 10px 10px;/*圓角*/
			position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:96px;/*右邊*/
		}
		.b1:hover{
			height:70%;
			box-shadow:   5px 5px 15px #888888;/*陰影*/

		}
		.c1{
			background-color: #ffc15e; 
			float:right; 
			height:60%; 
			width: 80px;
			transition: height 0.4s;
			border-radius:0px 0px 10px 10px;/*圓角*/
			position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:16px;/*右邊*/
		}
		.c1:hover{
			height:70%;
			box-shadow:   5px 5px 15px #888888;/*陰影*/

		}
		.f div{
			float:left;/*並排*/
			text-align:center; /*內部置中*/
			width: 80px;
			cursor:pointer;/*變手*/
			font-family:Microsoft JhengHei;
			font-size:20px;
			line-height: 50px;/*字體高(內部)*/
			transition-duration: 0.4s;/*變色秒數*/
		}
		.f1{
			height: 100%;
			position:absolute;/*絕對位置*/left: 0px;
		}
		.f1:hover{
			color:#67cad2;
		}
		.f2{
			height: 100%;
			position:absolute;/*絕對位置*/left: 80px;
		}
		.f2:hover{
			color:#c93f58;
		}
		.f3{
			height: 100%;
			position:absolute;/*絕對位置*/left: 160px;
		}
		.f3:hover{
			color:#7f5151;
		}
		.line{
		}
		</style>
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" >
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				<a class="navbar-brand active" href="TESTBIKE.HTML">揪團騎BIKE</a>
			</div>
			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<li><a href="">最新資訊</a></li><!--雙引號內請放連結-->
					<li><a href="">查詢旅遊點</a></li><!--雙引號內請放連結-->
					<li><a href="">揪團</a></li><!--雙引號內請放連結-->
					<li><a href="">行程規劃</a></li><!--雙引號內請放連結-->
					<li><a href="">我的日誌</a></li><!--雙引號內請放連結-->
					<li><a href="">討論區</a></li><!--雙引號內請放連結-->
					<li class="dropdown"><a href="http://www.yahoo.com.tw" >購物專區<b class="caret"></b></a>
							<ul class="dropdown-menu" data-animation="fadeInDown">
								<li><a href="http://www.yahoo.com.tw">中文</a></li>
								<li><a href="#">日語</a></li>
								<li><a href="#">English</a></li>
							</ul>
					</li><!--雙引號內請放連結-->
				</ul>
		
				<!-- 搜尋表單 -->
				<form class="navbar-form navbar-left">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="請輸入關鍵字">
				</div>
					<button type="submit" class="btn btn-primary" >搜尋</button>
				</form>
				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right"><!--navbar-right靠左-->
					<li><a href="#section7">個人中心</a></li>
					<li><a href="#">Amos 您好</a></li>
					<li><a href="#">登入</a></li>
					<li><a href="#"></a></li>
				</ul>
			</div>
		</nav>
		<div class="col-sm-12 hd"></div>
		<div class="container">
			<div class="row">
				<div class="col-sm-12" style="background-color: #ffffff;height: 150px;">
					<div class="col-sm-2 row" style="height: 100%;"><img src="images/to/1.png" class="row" style="height: 100%;"></div><!--class要加上row滿版才能靠左-->
					<div class="col-sm-5">
						<table>
							<tr><th style="font-size: 32px">我是誰</th><th></th></tr>
							<tr><td align='center'>暱稱:</td><td>123</td></tr>
							<tr><td align='center'>信箱:</td><td>qqqq123@gmail.com</td></tr>
							<tr><td align='center'>電話:</td><td>0987654321</td></tr>
							<tr><td align='center'>地址:</td><td>桃園市中壢區中大路300-1號</td></tr>
						</table>
					</div>
					<div class="col-sm-5"></div>
					<div class="row a1">A1</div>
					<div class="row b1">B2</div>
					<div class="row c1">C1</div>
				</div>
				<div class="col-sm-12" style="height:20px;"></div>
				<div class="col-sm-12 f">
					<div class="f1"><i class="glyphicon glyphicon-user"></i><b>朋友</b></div><div class="f2"><span class="glyphicon glyphicon-heart"></span><b>收藏</b></div><div class="f3"><span class="glyphicon glyphicon-user"></span><b>規劃</b></div>
				</div>
				<div class="col-sm-12" style="background-color:#efefef; height:5px;"><div class="row line" style="background-color:rgb(127, 81, 81);width:80px;height:5px;position:absolute;"></div></div>
				<div class="col-sm-12">			
			        <div class="panel-group row"  role="tablist" aria-multiselectable="true"><!--class要加上row滿版-->
			          <!--這可放迴圈開始-->
			         	<div class="panel panel-default">
			            	<div class="panel-heading" role="tab">
			                	<div class="panel-title"><a  role="button" data-toggle="collapse"  href="#a1">
			                	<div class="box">8/30</div>這裡是標題<input type="submit" value="刪除" class="btn btn-danger dele" data-toggle="modal" data-target="#additemdetails">
			                	</a></div>
			                </div>
			                <div id="a1" class="panel-collapse collapse"  >
			                    <div class="panel-body">
			                         <ul>
			                         	<li>第一天</li>
			                         	<li>第二天</li>
			                         </ul>
			                         <input type="submit" value="詳細資料" class="btn ">
			                    </div>
			                 </div>
			         	</div>
			         <!--這可放迴圈結束-->
			          <!--這可放迴圈開始-->
			         	<div class="panel panel-default">
			            	<div class="panel-heading" role="tab">
			                	<div class="panel-title"><a  role="button" data-toggle="collapse"  href="#a2">
			                	<div class="box">8/30</div>這裡是標題<input type="submit" value="刪除" class="btn btn-danger dele" data-toggle="modal" data-target="#additemdetails">
			                	</a></div>
			                </div>
			                <div id="a2" class="panel-collapse collapse"  >
			                    <div class="panel-body">
			                         <ul>
			                         	<li>第一天</li>
			                         	<li>第二天</li>
			                         </ul>
			                         <input type="submit" value="詳細資料" class="btn ">
			                    </div>
			                 </div>
			         	</div>
			         <!--這可放迴圈結束-->
			        </div>
			     </div>
			</div>
		</div>
		<!--請用js自己新增,或者拿掉我好累喔-->
		<div class="modal fade" id="additemdetails" role="dialog"><!--跳出視窗-->
			<div class="modal-dialog">
				<div class="modal-content"><!--沒有會透明-->
					<div class="modal-header"><!--有一條線隔開-->
						<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
				    	<h3 class="modal-title">是否刪除資訊</h3><!--modal-title跟右上X不會太開-->
				  	</div>
					<form class="form-horizontal" METHOD="post" action="<%=request.getContextPath()%>">
				    	<div class="modal-footer"><!--有一條線隔開-->
				    		<button type="submit" class="update btn btn-primary">確定</button>
				      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
				    	</div>
			        </form>
			    </div><!--modal-content-->
			</div>
		</div>
		<!--請用js自己新增,或者拿掉結束-->
		<script type="text/javascript">
		
			$(".box").hover(function(){
				$(this).css("background-color","#99CCFF");
			},function(){
				$(this).css("background-color","white");
			});

			$(document).ready(function(){
				$(".f1").mouseenter(function(){
			        $(".line").animate({left: "15px"});
			    });
			    $(".f2").mouseenter(function(){
			        $(".line").animate({left: "100px"});
			    });
			    $(".f3").mouseenter(function(){
			        $(".line").animate({left: "180px"});
			    });
			});
			
		</script>
		
</html>