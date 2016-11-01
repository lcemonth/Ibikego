<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.travel.model.*"%>
<%@ page import="com.travelImage.model.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	List<TravelVO> searchPoint = (List<TravelVO>)request.getAttribute("searchPoint");
	
	TravelImageService travelImageSvc = new TravelImageService();
	List<TravelImageVO> listTravelImage = travelImageSvc.getAll();
	pageContext.setAttribute("listTravelImage",listTravelImage);
	
	session.setAttribute("frontLocation", request.getRequestURI());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>旅遊點搜尋</title>
		<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js'></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			body{
	    		background-color: #0099CC;
			}
			.box{
				width: 240px;
				height: 480px;
				background-color: #E6E6E6;
				border-radius:10px 10px 2px 2px;
				cursor:pointer;/*變手*/
				overflow-y:hidden;         /*--設定超出的內容隱藏, IE, FireFox通用--*/
			    text-overflow:ellipsis;  /*--(IE專用)在內容超出時,在後方補上逗號--*/
			    /*white-space:nowrap;*/ /*-- 設定內容強制顯示一行,直到內容結束或<br>--*/
				float:left;
				margin:80px 20px 0px 40px;
			}
			img{
				width: 100%;
				height: 50%;
				border-radius:10px 10px 0px 0px;
			}
			.box:hover{
				box-shadow: 0px 0px 2px 4px #01B468;
			}
			.p1 {
			    margin-top: 15px;/*字體高(外部)*/
			    font-size: 14px;/*字體大小*/
			    letter-spacing: 2px;/*字體寬*/
			    line-height: 21px;/*字體高(內部)*/
			    color: #555;/*字體顏色*/
			    margin: 5px;
			    padding: 5px;
			}
			h3, p{
			    margin: 0;
			    padding: 0;
			    border: 0;
			    font: inherit;   /*h3大小*/
			    font-family: '蘋果儷中黑','微軟正黑體','Helvetica','Arial',sans-serif;
			}
			#pp{
				background-color: #1D7F56;
				width: 60px;
				color:#fff;
				position:relative; top:-25px; left:0%;
			}
			#insert{
				position:relative;
				top: 8px;
			}
		</style>
		<script type="text/javascript">
			$(function(){
			    var len = 75; // 超過80個字以"..."取代
			    $(".p1").each(function(i){
			        if($(this).text().length>len){
			            $(this).attr("title",$(this).text());
			            var text=$(this).text().substring(0,len-1)+"...";
			            $(this).text(text);
			        }
			    });
			});
		</script>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
		<div class="col-md-12"><br><br>
			<div class="col-md-12">
				<form method="post" action="<%=request.getContextPath()%>/travel/travel.do" class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<select name="tra_class_status">
							<option value="2">全部</option>
						    <option value="0">景點</option>
						    <option value="1">休息站</option>
						</select>
					</div>
					<button type="submit" class="btn btn-primary">搜尋</button>
					<input type="hidden" name="action" value="searchPoint">
				</form>
				<c:if test="${not empty memberVO}">
					<a href="<%=request.getContextPath()%>/front.do?navaction=userInsertTravelPoint">
						<button class="btn btn-primary" id="insert">新增旅遊點</button>
					</a>
				</c:if>
	      	</div>
	      	<jsp:useBean id="travelSvc" scope="page" class="com.travel.model.TravelService" />
		    <jsp:useBean id="reportcollectSvc" scope="page" class="com.reportcollect.model.ReportcollectService" />
	      	<c:if test="${empty searchPoint}">
				<c:forEach var="travelVO" items="${travelSvc.all}">
					<c:if test="${travelVO.tra_del!=1}">
						<a href="<%=request.getContextPath()%>/travel/travel.do?action=getOne_For_UserDisplay&tra_no=${travelVO.tra_no}">
							<div class="box">
								<c:forEach var="travelImageVO" items="${listTravelImage}">
				                    <c:if test="${travelVO.tra_no == travelImageVO.tra_no}">
					                    <img src="<%=request.getContextPath()%>/image/image.do?tra_img_no=${travelImageVO.tra_img_no}"/>
				                    </c:if>
				                </c:forEach>
								<p id="pp" style="text-align: center;">${(travelVO.tra_class_status == 0)?"景點":"休息站"}</p>
								<p>
									<c:if test="${travelVO.loc_no == 1}">北部</c:if>
									<c:if test="${travelVO.loc_no == 2}">中部</c:if>
									<c:if test="${travelVO.loc_no == 3}">南部</c:if>
									<c:if test="${travelVO.loc_no == 4}">東部</c:if>
								</p>	
								<h3 id="title">${travelVO.tra_name}</h3>
								<p class="p1">${travelVO.tra_content}</p>
							</div>
						</a>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${not empty searchPoint}">
				<c:forEach var="travelVO" items="${searchPoint}">
					<c:if test="${travelVO.tra_del!=1}">
						<a href="<%=request.getContextPath()%>/travel/travel.do?action=getOne_For_UserDisplay&tra_no=${travelVO.tra_no}">
							<div class="box">
								<c:forEach var="travelImageVO" items="${listTravelImage}">
				                    <c:if test="${travelVO.tra_no == travelImageVO.tra_no}">
					                    <img src="<%=request.getContextPath()%>/image/image.do?tra_img_no=${travelImageVO.tra_img_no}"/>
				                    </c:if>
				                </c:forEach>
								<p id="pp" style="text-align: center;">${(travelVO.tra_class_status == 0)?"景點":"休息站"}</p>
								<p>
									<c:if test="${travelVO.loc_no == 1}">北部</c:if>
									<c:if test="${travelVO.loc_no == 2}">中部</c:if>
									<c:if test="${travelVO.loc_no == 3}">南部</c:if>
									<c:if test="${travelVO.loc_no == 4}">東部</c:if>
								</p>	
								<h3 id="title">${travelVO.tra_name}</h3>
								<p class="p1">${travelVO.tra_content}</p>
							</div>
						</a>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>