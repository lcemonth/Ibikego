<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="">
	<head>
	
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="http://malsup.github.com/jquery.cycle.all.js"></script>
		<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<script src="4_new.js"></script>

		<link rel="stylesheet" href="bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<script src="js/index.js"></script>
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
			麵包削我不會
			<div class="col-sm-12" style="background-color: #ffffff;height: 150px;">
				這裡有需要方東西嗎?
			</div>
			<div class="col-sm-12">
			<br>
		         <div class="panel-group"  role="tablist" aria-multiselectable="true">
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
		                         	<li>
		                         		第一天:永安漁感→永安漁感→永安漁感→永安漁感→永安漁感
		                         	</li>
		                         	<li>第二天:</li>
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
	<body>
</html>
	<!--請用js自己新增,或者拿掉結束-->
	<script type="text/javascript">
		$(".box").hover(function(){
			$(this).css("background-color","#99CCFF");
		},function(){
			$(this).css("background-color","white");
		});
	</script>