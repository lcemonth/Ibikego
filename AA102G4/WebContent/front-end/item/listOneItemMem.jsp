<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>



<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
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
		<script src="<%=request.getContextPath()%>/res/js/3.3.6/js/bootstrap.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<script src="<%=request.getContextPath()%>/front-end/item/js/selected.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/itemdetails/css/ItemDetails.css">
	</head>
	<body>
	<jsp:include page="/front-end/top.jsp"></jsp:include>
	<!----------------------------------------------nav分割線---------------------------------------------->
		<div class="hd"></div>
		<table class="table table-hover table-striped table-condensed">
			<tr>
				<th>商品編號</th>
				<th>會員編號</th>
				<th>商品名稱</th>
				<th>商品價格</th>
				<th>說明</th>
				<th>商品狀態</th>
				<th>修改</th>
				<th>刪除</th>
				<th>圖片</th>
				<th>新增圖片</th>
				<th>一圖</th>
			</tr>
			<c:forEach var="itemVO" items="${itemList}"  >
			
			<tr align='center' valign='middle' ${(itemVO.item_no==param.item_no) ? 'bgcolor=#eee':''}>
				<td>${itemVO.item_no}</td>
				<td>${memberSvc.getOneMember(itemVO.mem_no).mem_name}</td>
				<td>${itemVO.item_name}</td>
				<td>${itemVO.item_price}</td>
				<td>${itemVO.item_exp}</td>
				<td class="added" value="${itemVO.item_is_added}">${(itemVO.item_is_added == 0) ? '下架':(itemVO.item_is_added == 1) ? '上架':'交易完成'}</td>
				<td>
				     <input type="submit" value="修改" class="btn btn-warning" data-toggle="modal" data-target="#upitem" onclick="upItem(this);" 
				     data-item_no="${itemVO.item_no}" data-mem_no="${itemVO.mem_no}" data-item_name="${itemVO.item_name}" 
				     data-item_price="${itemVO.item_price}" data-item_exp="${itemVO.item_exp}"  data-item_is_added="${itemVO.item_is_added}" 
				     <c:if test="${itemVO.item_is_added==2}"> disabled </c:if>>     
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item.do?action=deleteOne2">
						<input type="submit" value="刪除" class="btn btn-danger" <c:if test="${itemVO.item_is_added==2}"> disabled </c:if>>
					    <input type="hidden" name="item_no" value="${itemVO.item_no}">
					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
					</FORM>
				</td>
				<td> 
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item.do">
						<input type="hidden" name="action" value="listItemimages_ByItem_no_B">
					    <input type="submit" value="送出查詢" class="btn btn-primary"> 
					    <input type="hidden" name="item_no" value="${itemVO.item_no}">
					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				    </FORM>
				</td>
				<td> 
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemimage.do?action=getOne_For_Insert">
						<input type="submit" value="新增" class="btn btn-info" <c:if test="${itemVO.item_is_added==2}"> disabled </c:if>> 
					    <input type="hidden" name="item_no" value="${itemVO.item_no}">
					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				    </FORM>
				</td>
				<td>
					<img src="<%=request.getContextPath()%>/getOnePostersServelt?item_no=${itemVO.item_no}" width="100px" height="100px" class="img-rounded">
				</td>
			</tr>
		 </c:forEach>
		</table>



		<!-- jQuery -->
		<script src="//code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		
		<!----------------------------------------------跳出視窗---------------------------------------------->
		<div class="modal fade" id="initem" role="dialog"><!--跳出視窗-->
			<div class="modal-dialog">
				<div class="modal-content"><!--沒有會透明-->
					<div class="modal-header"><!--有一條線隔開-->
						<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
				    	<h3 class="modal-title">商品資料修改</h3><!--modal-title跟右上X不會太開-->
				  	</div>
					<form class="form-horizontal" METHOD="post" action="<%=request.getContextPath()%>/item.do?action=update">
						<div class="form-group">
							<%if (request.getAttribute("listItemImages_ByItem_no")!=null){%>
					 		<jsp:include page="listItemImages_ByMem_no.jsp" />
					 		<%} %>
						</div>
						<div class="modal-footer"><!--有一條線隔開-->
					    	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				    		<input type="submit" class="update btn btn-primary" value="確定"></input>
				      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
				    	</div>
			        </form>
			    </div><!--modal-content-->
			</div>
		</div>
		<!----------------------------------------------跳出視窗---------------------------------------------->
		<%if (request.getAttribute("listItemImages_ByItem_no")!=null){%>
		<jsp:include page="listItemImages_ByMem_no.jsp" />
		<%} %>
 		<!----------------------------------------------跳出視窗---------------------------------------------->
		<div class="modal fade" id="upitem" role="dialog"><!--跳出視窗-->
			<div class="modal-dialog">
				<div class="modal-content"><!--沒有會透明-->
					<div class="modal-header"><!--有一條線隔開-->
						<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
				    	<h3 class="modal-title">商品資料修改</h3><!--modal-title跟右上X不會太開-->
				  	</div>
					<form class="form-horizontal" METHOD="post" action="<%=request.getContextPath()%>/item.do?action=update">
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">商品名稱:</label>
							<div class="col-sm-8"><input type="TEXT" name="item_name" id="item_name" class="form-control" /></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">商品價格:</label>
							<div class="col-sm-8"><input type="TEXT" name="item_price" id="item_price" class="form-control" value="${itemVO.getItem_price()}" /></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">商品說明:</label>
							<div class="col-sm-8"><textarea type="text" name="item_exp" id="item_exp" class="form-control" rows="4" cols="47" style="resize: none">${itemVO.getItem_exp()}</textarea></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">商品狀態:</label>
							<div class="col-sm-8"><input type="radio" name="item_is_added" value="0" />下架
												  <input type="radio" name="item_is_added" value="1" />上架
												  <input type="hidden" id="add"  value="${itemVO.item_is_added}" /></div>
						</div>
				    	<div class="modal-footer"><!--有一條線隔開-->
							<input type="hidden" name="item_no" id="item_no">
							<input type="hidden" name="mem_no" id="mem_no">
					    	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				    		<input type="submit" class="update btn btn-primary" value="確定"></input>
				      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
				    	</div>
			        </form>
			    </div><!--modal-content-->
			</div>
		</div>
		<!----------------------------------------------跳出視窗---------------------------------------------->

	</body>
</html>