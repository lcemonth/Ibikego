<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.itemdetails.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<jsp:useBean id="imageSvc" scope="page" class="com.itemimage.model.ItemImageService"/>
<jsp:useBean id="itemdetailsSvc" scope="page" class="com.itemdetails.model.ItemDetailsService"/>
<%
ItemDetailsVO itemdetailsVO = (ItemDetailsVO) request.getAttribute("itemdetailsVO"); 
%> 
<!DOCTYPE html>

<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/item/js/jquery-1.7.2.min.js"></script>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<!-- jQuery -->
		<script src="https://code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/item/css/listOneItemMem.css">
		<script type="text/javascript">
		$(function(){
			if($(".item_is_added").attr("data-value") == 2){
				$(".in").attr("disabled", true);
			}
		});
		</script>
	</head>
	<body>
		
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		
	<!----------------------------------------------nav分割線---------------------------------------------->
		<div class="col-sm-12 hd"></div>
		<div class="container">
		<div class="row">
		<div class="col-sm-9 col-sm-offset-1" style="padding:0px; background-color: #ffffff; border-radius:15px 15px 15px 15px; box-shadow: -1px 2px 13px 0px #888888;/*陰影*/">
		<div class="col-sm-12"><div class="item_name"><p>${itemVO.item_name}</p></div></div>
		<!--圖片div開始-->
		<div class="col-sm-4">
			<img src="<%=request.getContextPath()%>/getOnePostersServelt?item_no=${itemVO.item_no}" class="img-thumbnail" width="304" height="236">
			<div style="margin: 10px 5px;"><img src="<%=request.getContextPath()%>/front-end/item/images/cat.jpg" width="40"height="40"><img src="<%=request.getContextPath()%>/front-end/item/images/pelican.jpg" width="35"height="35"></div>
			<div class="item_no" style="font-size:14px;/*字體大小*/">商品編號:${itemVO.item_no}</div>
		</div>
		<!--圖片div結束-->
		<!--價格div開始-->
		<div class="col-sm-8">
			<div class="item_price">
				<table>
					<tr valign="middle">
						<td>價格:</td>
						<td><b style="color:red;font-size:30px;">$</b></td>
						<td><b style="color:red;font-size:60px;">${itemVO.item_price}</b></td>
						<td style="width:360px;" align="right">
							<c:if test="${not empty memberVO}">
						  	  <input type="submit" value="購買" class="in btn btn-lg btn-success" data-toggle="modal" data-target="#additemdetails">
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--價格div結束-->
		<!--付款div開始-->
		<div class="col-sm-5" style="margin: 20px 0px;/*外部距離*/">
			<table>
				<tr>
					<td>付款方式:</td>
					<td>貨到付款</td>
				</tr>
				<tr>
					<td>運送方式:</td>
					<td>宅配/快遞0元</td>
				</tr>
				
			</table>
		</div>
		<!--付款div結束-->
		<!--賣家資訊div開始-->
		<div class="col-sm-3" style="margin: 20px 0px;/*外部距離*/">
			<table style="font-size:14px;/*字體大小*/">
				<tr style="background-color:#7bbdff; font-weight: bold;/*粗體*/">
					<td colspan="2">賣家資訊</td>
				</tr>
				<tr>
					<td colspan="2" style="color:#CCCCCC;">${memberSvc.getOneMember(itemVO.mem_no).mem_name}</td>
				</tr>
				<tr>
					<td width="100">評價分數:</td>
					<td width="70"  title="查看賣家評分">${itemdetailsSvc.SellerScore(itemVO.mem_no).item_seller_score}</td>

				</tr>
			</table>
		</div>
		<!--賣家資訊div結束-->
		<!--說明div開始-->
		<div class="col-sm-5">說明:${itemVO.item_exp}
		</div>
		<!--說明div結束-->
		<div class="col-sm-9">
		<div>
            <span style="background-color: #7bbdff;">圖片檔案</span>
            <div class="hr" style="width: 100%"></div>
         </div>
         <!--多圖div開始-->
		<ul class="imgs">
			<%if (request.getAttribute("listItemImages_ByItem_no")!=null){%>
 				<jsp:include page="listItemImages_ByItem_no.jsp" />
 			<%} %>
		</ul>
		<div type="hidden" class="item_is_added" data-value="${itemVO.item_is_added}"></div>
		<!--多圖div結束-->
		</div>
		</div><!--col-sm-9-->
		</div><!--row-->
		</div><!--container-->
		<div class="box hidden-xs"></div>
		<!----------------------------------------------跳出視窗---------------------------------------------->
		<div class="modal fade" id="additemdetails" role="dialog"><!--跳出視窗-->
			<div class="modal-dialog">
				<div class="modal-content"><!--沒有會透明-->
					<div class="modal-header"><!--有一條線隔開-->
						<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
				    	<h3 class="modal-title">請輸入送貨資訊</h3><!--modal-title跟右上X不會太開-->
				    	<% 
				    	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");    
				    	java.util.Date currentTime_1 = new java.util.Date();    
				    	out.print(formatter.format(currentTime_1));   
				    	%>
				  	</div>
					<form class="form-horizontal" METHOD="post" action="<%=request.getContextPath()%>/itemdetails.do?action=insert" onsubmit="return test();">
						<div class="form-group">
							<label class="col-sm-2" style="text-align:center;">姓名</label>
							<div class="col-sm-10"><input type="TEXT" name="item_buyer_name" id="item_buyer_name" class="form-control" value="<%=(itemdetailsVO==null)? "小霞" : itemdetailsVO.getItem_buyer_name()%>" /></div>
						</div>
						<div class="form-group">
							<label class="col-sm-2" style="text-align:center;">地址</label>
							<div class="col-sm-10"><input type="TEXT" name="item_buyer_add" id="item_buyer_add" class="form-control" value="<%=(itemdetailsVO==null)? "台灣市台灣路1號" : itemdetailsVO.getItem_buyer_add()%>" /></div>
						</div>
						<div class="form-group">
							<label class="col-sm-2" style="text-align:center;">電話</label>
							<div class="col-sm-10"><input type="TEXT" name="item_buyer_phone" id="item_buyer_phone" class="form-control" value="<%=(itemdetailsVO==null)? "0987654321" : itemdetailsVO.getItem_buyer_phone()%>"/></div>
						</div>
				    	<div class="modal-footer"><!--有一條線隔開-->
				    		<input type="hidden" name="item_no" value="${itemVO.item_no}">
							<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
							<input type="hidden" name="item_detail_status" value="0">
							<input type="hidden" name="item_is_get" value="0">
							<input type="hidden" name="item_is_sellerscore" value="0">
							<input type="hidden" name="item_is_buyerscore" value="0">
							<input type="hidden" name="item_detail_del" value="0">
							<input type="hidden" name="item_seller_score" value="0">
							<input type="hidden" name="item_buyer_score" value="0">
							
				    		<input type="submit" class="update btn btn-primary" value="購買"></input>
				      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
				    	</div>
			        </form>
			    </div><!--modal-content-->
			</div>
		</div>
		
	</body>
</html>
<script type="text/javascript">
	function test(){
		if(document.getElementById("item_buyer_name").value==""){
			alert("姓名");
			return false;
		}
		if(document.getElementById("item_buyer_add").value==""){
			alert("地址");
			return false;
		}
		if(document.getElementById("item_buyer_phone").value==""){
			alert("電話");
			return false;
		}
				
		
	}
</script>