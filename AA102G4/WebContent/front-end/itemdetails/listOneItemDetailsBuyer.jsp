<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.itemdetails.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService"/>
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
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/itemdetails/css/ItemDetails.css">
	</head>
	<body>
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	<!----------------------------------------------nav分割線---------------------------------------------->
	<div class="hd"></div>
	<div class="col-sm-12">
		<table class="table table-hover table-striped table-condensed">
			<tr>
				<th>商品編號</th>
				<th>賣家</th>
				<th>商品訂單</th>
				<th>取貨狀態</th>
				<th>賣方評分</th>
				<th>買方評分</th>
				<th>訂單取消</th>
				<th>賣家分數</th>
				<th>買家分數</th>
				<th>訂單日期</th>
				<th>訂單姓名</th>
				<th>訂單地址</th>
				<th>訂單電話</th>
				<th>修改</th>
				<th>買方給評</th>
				<th>訂單</th>
			</tr>
			<c:forEach var="itemDetailsVO" items="${itemDetailsList}">
			<tr align='center' valign='middle'> 
				<td id="itemno_${itemDetailsVO.item_no}">${itemDetailsVO.item_no}</td>
				<td>${itemSvc.getOneItem(itemDetailsVO.item_no).mem_no}</td>
				<td>${(itemDetailsVO.item_detail_status == 0)? '交易中':'完畢'}</td>
				<td>${(itemDetailsVO.item_is_get == 0)? '未取貨':'已取貨'}</td>
				<td>${(itemDetailsVO.item_is_sellerscore == 0)? '未給評':'已給評'}</td>
				<td>${(itemDetailsVO.item_is_buyerscore == 0)? '未給評':'已給評'}</td>
				<td>${(itemDetailsVO.item_detail_del == 0)? '':'取消訂單'}</td>
				<td id="item_seller_score_${itemDetailsVO.item_no}">${itemDetailsVO.item_seller_score}</td>
				<td id="item_buyer_score_${itemDetailsVO.item_no}">${itemDetailsVO.item_buyer_score}</td>
				<td id="item_order_time_${itemDetailsVO.item_no}">${itemDetailsVO.item_order_time}</td>
				<td id="item_buyer_name_${itemDetailsVO.item_no}">${itemDetailsVO.item_buyer_name}</td>
				<td id="item_buyer_add_${itemDetailsVO.item_no}">${itemDetailsVO.item_buyer_add}</td>
				<td id="item_buyer_phone_${itemDetailsVO.item_no}">${itemDetailsVO.item_buyer_phone}</td>
				<td>
					<input type="hidden" id="item_mem_${itemDetailsVO.item_no}" value="${itemDetailsVO.mem_no}" />
					<input type="hidden" id="item_detail_status_${itemDetailsVO.item_no}" value="${itemDetailsVO.item_detail_status}" />
					<input type="hidden" id="item_is_get_${itemDetailsVO.item_no}" value="${itemDetailsVO.item_is_get}" />
					<input type="hidden" id="item_is_sellerscore_${itemDetailsVO.item_no}" value="${itemDetailsVO.item_is_sellerscore}" />
					<input type="hidden" id="item_is_buyerscore_${itemDetailsVO.item_no}" value="${itemDetailsVO.item_is_buyerscore}" />
				    <button type="submit"  class="btn btn-warning" id="up_${itemDetailsVO.item_no}" data-toggle="modal" data-target="#update"
				    <c:if test="${(itemDetailsVO.item_is_buyerscore==1)||(itemDetailsVO.item_detail_del==1)}"> disabled </c:if>>修改</button>
				</td>
				<td>
				    <input type="submit" value="買方給評" class="btn btn-primary" data-toggle="modal" data-target="#upBuyer" onclick="upBuyer(this);" 
				    data-item_no="${itemDetailsVO.item_no}" data-mem_no="${itemDetailsVO.mem_no}" data-item_detail_status="${itemDetailsVO.item_detail_status}" 
				    data-item_is_sellerscore="${itemDetailsVO.item_is_sellerscore}" data-item_detail_del="${itemDetailsVO.item_detail_del}"
				    data-item_buyer_score="${itemDetailsVO.item_buyer_score}" data-item_buyer_name="${itemDetailsVO.item_buyer_name}" 
				    data-item_buyer_add="${itemDetailsVO.item_buyer_add}" data-item_buyer_phone="${itemDetailsVO.item_buyer_phone}"
				     <c:if test="${(itemDetailsVO.item_is_buyerscore==1)||(itemDetailsVO.item_detail_del==1)}"> disabled </c:if>>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemdetails.do?action=cancel" onClick="return confirm('確定要取消訂單嗎?')">
				    <input type="submit" value="取消" class="btn btn-primary"  <c:if test="${(itemDetailsVO.item_is_buyerscore==1)||(itemDetailsVO.item_detail_del==1)}"> disabled </c:if>> 
				    <input type="hidden" name="item_no" value="${itemDetailsVO.item_no}">
				    <input type="hidden" name="mem_no" value="${itemDetailsVO.mem_no}">
				    <input type="hidden" name="item_detail_del" value="1">
				    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				    </FORM>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>		
<div>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>
			<c:forEach var="message" items="${errorMsgs}">
				<script>
					swal("${message}", "請修正錯誤", "error")
				</script>
			</c:forEach>
		</font>	
	</c:if>
</div> 
	<!----------------------------------------------跳出視窗---------------------------------------------->
		<div class="modal fade" id="upBuyer" role="dialog"><!--跳出視窗-->
			<div class="modal-dialog">
				<div class="modal-content"><!--沒有會透明-->
					<div class="modal-header"><!--有一條線隔開-->
						<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
				    	<h3 class="modal-title">賣家評分</h3><!--modal-title跟右上X不會太開-->
				    </div>
					<form class="form-horizontal" METHOD="post" action="<%=request.getContextPath()%>/itemdetails.do?action=update_Seller">
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">評分狀態:</label>
							<div class="col-sm-8">
								<input type="radio" name="item_seller_score" value="60"/>差
								<input type="radio" name="item_seller_score" value="80"/>好
								<input type="radio" name="item_seller_score" value="100"/>棒
							</div>
						</div>
				    	<div class="modal-footer"><!--有一條線隔開-->
							<input type="hidden" name="item_no" id="item_no" value="${itemDetailsVO.item_no}" />
							<input type="hidden" name="mem_no" id="mem_no" value="${itemDetailsVO.mem_no}">
							<input type="hidden" name="item_detail_status" id="item_detail_status" value="${itemDetailsVO.item_detail_status}">
							<input type="hidden" name="item_is_get" value="1">
							<input type="hidden" name="item_is_sellerscore" id="item_is_sellerscore" value="${itemDetailsVO.item_is_sellerscore}">
							<input type="hidden" name="item_is_buyerscore" value="1">
							<input type="hidden" name="item_buyer_score" id="item_buyer_score" value="${itemDetailsVO.item_buyer_score}">
							<input type="hidden" name="item_buyer_name" id="item_buyer_name" value="${itemDetailsVO.item_buyer_name}"/>
							<input type="hidden" name="item_buyer_add" id="item_buyer_add" value="${itemDetailsVO.item_buyer_add}"/>
							<input type="hidden" name="item_buyer_phone" id="item_buyer_phone" value="${itemDetailsVO.item_buyer_phone}"/>
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				    		<input type="submit" class="update btn btn-primary" value="新增"></input>
				      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
				    	</div>
			        </form>
			    </div><!--modal-content-->
			</div>
		</div>
		<!----------------------------------------------跳出視窗---------------------------------------------->
		<div class="modal fade" id="update" role="dialog"><!--跳出視窗-->
			<div class="modal-dialog">
				<div class="modal-content"><!--沒有會透明-->
					<div class="modal-header"><!--有一條線隔開-->
						<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
				    	<h3 class="modal-title">新增商品資料</h3><!--modal-title跟右上X不會太開-->
				  	</div>
					<form class="form-horizontal" METHOD="post" action="<%=request.getContextPath()%>/itemdetails.do?action=update">
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">訂單姓名:</label>
							<div class="col-sm-8"><input type="text" class="form-control" name="item_buyer_name" id="up_item_buyer_name"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">訂單地址:</label>
							<div class="col-sm-8"><input type="text" class="form-control" name="item_buyer_add"  id="up_item_buyer_add"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">訂單電話:</label>
							<div class="col-sm-8"><input type="text" class="form-control" name="item_buyer_phone" id="up_item_buyer_phone"></div>
						</div>
				    	<div class="modal-footer"><!--有一條線隔開-->
							<input type="hidden" name="item_no" id="up_item_no">
							<input type="hidden" name="mem_no"  id="up_mem_no">
							<input type="hidden" name="item_detail_status" id="up_item_detail_status">
							<input type="hidden" name="item_is_get"  id="up_item_is_get">
							<input type="hidden" name="item_is_sellerscore" id="up_item_is_sellerscore">
							<input type="hidden" name="item_is_buyerscore"  id="up_item_is_buyerscore">
							<input type="hidden" name="item_seller_score"  id="up_item_seller_score">
							<input type="hidden" name="item_buyer_score" id="up_item_buyer_score">
							<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				    		<input type="submit" class="update btn btn-primary" value="新增"></input>
				      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
				    	</div>
			        </form>
			    </div><!--modal-content-->
			</div>
		</div>
		<!-- jQuery -->
		<script src="//code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
 		<script type="text/javascript">
 		function upBuyer(item_no){
 			
	 		var item_buyer_phone=$(item_no).attr("data-item_buyer_phone");
	 		var item_buyer_add=$(item_no).attr("data-item_buyer_add");
	 		var item_buyer_name=$(item_no).attr("data-item_buyer_name");
	 		var item_buyer_score=$(item_no).attr("data-item_buyer_score");
	 		var item_detail_del=$(item_no).attr("data-item_detail_del");
	 		var item_is_sellerscore=$(item_no).attr("data-item_is_sellerscore");
	 		var item_detail_status=$(item_no).attr("data-item_detail_status");
 			var mem_no=$(item_no).attr("data-mem_no");
 			var item_no=$(item_no).attr("data-item_no");
 			$("#mem_no").val(mem_no);
 			$("#item_no").val(item_no);
 			$("#item_detail_status").val(item_detail_status);
 			$("#item_is_sellerscore").val(item_is_sellerscore);
 			$("#item_detail_del").val(item_detail_del);
 			$("#item_buyer_score").val(item_buyer_score);
 			$("#item_buyer_name").val(item_buyer_name);
 			$("#item_buyer_add").val(item_buyer_add);
 			$("#item_buyer_phone").val(item_buyer_phone);
 		
 		}
$(function(){
		$("button[id^='up']").bind("click",function(){
			var idAry = $(this).attr("id").split("_");
			$("#up_item_no").val(idAry[1]);
			$("#up_mem_no").val($("#item_mem_"+idAry[1]).val());
			$("#up_item_detail_status").val($("#item_detail_status_"+idAry[1]).val());
			$("#up_item_is_get").val($("#item_is_get_"+idAry[1]).val());
			$("#up_item_is_sellerscore").val($("#item_is_sellerscore_"+idAry[1]).val());
			$("#up_item_is_buyerscore").val($("#item_is_buyerscore_"+idAry[1]).val());
			$("#up_item_seller_score").val($("#item_seller_score_"+idAry[1]).html());
			$("#up_item_buyer_score").val($("#item_buyer_score_"+idAry[1]).html());
			$("#up_item_buyer_name").val($("#item_buyer_name_"+idAry[1]).html());
			$("#up_item_buyer_add").val($("#item_buyer_add_"+idAry[1]).html());
			$("#up_item_buyer_phone").val($("#item_buyer_phone_"+idAry[1]).html());
/* 			alert($("#item_is_sellerscore_"+idAry[1]).val()) */
		})
	})
 		
 		</script>
	</body>
</html>