
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ItemVO itemVO = (ItemVO) request.getAttribute("itemVO");

	ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAll();
	pageContext.setAttribute("list",list);
%>
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
		<script src="<%=request.getContextPath()%>/res/js/3.3.6/bootstrap.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/item/css/AllItem.css">
		
	</head>
	<body>
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	<!----------------------------------------------nav分割線---------------------------------------------->
	<div class="hd"></div>
	<div class="container">
	<div class="row">
	<div class="col-sm-12" style="background-color: #99CCFF;">
	<c:forEach var="itemVO" items="${list}">
	<c:if test="${itemVO.item_is_added!=0}">
	<form class="form-horizontal myForm" METHOD="post" ACTION="<%=request.getContextPath()%>/item.do?action=getOne_For_Front" >
	<input type="hidden" name="item_no" value="${itemVO.item_no}">
	<div class="itembox" onclick="formSubmit()">
			<img src="<%=request.getContextPath()%>/getOnePostersServelt?item_no=${itemVO.item_no}">
			<p class="name"><b>${itemVO.item_name}</b></p>
			<p class="price">${itemVO.item_price}</p>
			<p class="mem">${memberSvc.getOneMember(itemVO.mem_no).mem_name}</p>
			<p class="added" style="${(itemVO.item_is_added == 1) ? 'background-color:red':'background-color:yellow'}">${(itemVO.item_is_added == 0) ? '下架':(itemVO.item_is_added == 1) ? '上架':'完成'}</p>
		</div>
	</form>
	</c:if>
	</c:forEach>
	</div>
	</div><!--row-->
	</div>
	<c:if test="${not empty memberVO}">
		<input type="submit" class="addItem" value="新增" data-toggle="modal" data-target="#additem" style="font-size:36px">
	</c:if>
	<!----------------------------------------------跳出視窗---------------------------------------------->
		<div class="modal fade" id="additem" role="dialog"><!--跳出視窗-->
			<div class="modal-dialog">
				<div class="modal-content"><!--沒有會透明-->
					<div class="modal-header"><!--有一條線隔開-->
						<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
				    	<h3 class="modal-title">新增商品資料</h3><!--modal-title跟右上X不會太開-->
				  	</div>
					<form class="form-horizontal form1" METHOD="post" action="<%=request.getContextPath()%>/item.do?action=insert" enctype="multipart/form-data" onsubmit="return test();">
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">商品名稱:</label>
							<div class="col-sm-8"><input type="TEXT" name="item_name" id="item_name" class="form-control" value="<%= (itemVO==null)? "便宜賣單車" : itemVO.getItem_name()%>" /></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">商品價格:</label>
							<div class="col-sm-8"><input type="TEXT" name="item_price" id="item_price" class="form-control" value="<%= (itemVO==null)? 1000 : itemVO.getItem_price()%>" /></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">商品說明:</label>
							<div class="col-sm-8"><textarea type="text" name="item_exp" id="item_exp" rows="4" cols="47" class="form-control" style="resize: none"><%= (itemVO==null)? "抓寶、運動和把妹，必備單車。" : itemVO.getItem_exp()%></textarea></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3" style="text-align:center;">圖片</label>
							<div class="col-sm-8"><input type='file' name = "item_img" id = "item_img" class="upl" multiple/></div>
							<div class="col-sm-12"><div class="preview"></div></div>
						</div>
				    	<div class="modal-footer"><!--有一條線隔開-->
							<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
							<input type="hidden" name="item_is_added" value="1">

				    		<input type="submit" class="update btn btn-primary" value="新增"></input>
				      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
				    	</div>
			        </form>
			    </div><!--modal-content-->
			</div>
		</div>
		<!-- -------------------------------------------------------------------------------------------- -->
	</body>
</html>

<script type="text/javascript">
	function test(){
		if(document.getElementById("item_name").value==""){
			alert("請輸入商品名稱");
			return false;
		}
		if(document.getElementById("item_price").value==""){
			alert("請輸入商品價格");
			return false;
		}
		if(document.getElementById("item_exp").value==""){
			alert("請輸入商品說明");
			return false;
		}
		if(document.getElementById("item_img").value==""){
			alert("請輸入商品圖片");
			return false;
		}
		
		
	}
	$(function(){
		$(".myForm").click(function(){
			var form = this;
				form.submit();
		})
	})
	 var Preview = new function (){
		 
		    var root = $(".form1");
		 
		    // 連續的圖片編碼
		    var imgcode = '';
		 
		    // 選取發生改變
		    this.change_file = function (){
		        root.on("change", ".upl", function (){
		            show(this);
		        });
		    }
		 
		    // 批次圖片，先清空後再插入
		    var show = function (input){
		        if (input.files && input.files[0]) {
		            clean();
		            each_img(input.files);
		        }
		    }
		 
		    // 批次讀取，最後再一次寫入
		    var each_img = function (files){
		 
		        $.each(files, function (index, file){
		            var src = URL.createObjectURL(file);
		            create_imgcode(src);
		        });
		 
		        // 放置預覽元素後重設
		        root.find(".preview").html(imgcode);
		        reset();
		    }
		 
		 
		    // 建立圖片
		    var create_imgcode = function(src){
		        imgcode += '<img class="img" src="' + src + '" width="150px" height="150px">';
		    }
		    
		 
		    // 清空預覽區域
		    var clean = function (){
		        root.find(".preview").empty();
		    }

		    // 還原 input[type=file]
		    var reset = function (){
		        imgcode = '';
		    }
		}
		 
		// 執行
		Preview.change_file();
	
</script>