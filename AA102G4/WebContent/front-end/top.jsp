<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div class="col-md-12">
				<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" >
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand active" href="<%=request.getContextPath()%>/front.do?navaction=bike">揪團騎BIKE</a>
					</div>
					<!-- 手機隱藏選單區 -->
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<!-- 左選單 -->
						<ul class="nav navbar-nav">
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=news">最新資訊</a></li><!--雙引號內請放連結-->
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=searchTravelPoint">旅遊點</a></li><!--雙引號內請放連結-->
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=act_browse">揪團</a></li><!--雙引號內請放連結-->
							<c:if test="${not empty memberVO}" >
								<li class="dropdown"><a href="<%=request.getContextPath()%>/stroke.do?action=query">行程規劃</a><!--雙引號內請放連結-->
									<ul class="dropdown-menu" data-animation="fadeInDown">
										<li><a href="<%=request.getContextPath()%>/stroke.do?action=query">我的行程</a></li>
										<li><a href="<%=request.getContextPath()%>/stroke.do?action=add">規劃行程</a></li>
									</ul>
								</li>							
							</c:if>
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=searchBlog">單車日誌</a></li><!--雙引號內請放連結-->
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=forum_front">討論區</a></li><!--雙引號內請放連結-->
							<li class="dropdown"><a href="<%=request.getContextPath()%>/front.do?navaction=item">購物專區<b class="caret"></b></a>
							<ul class="dropdown-menu" data-animation="fadeInDown">
								<li><a href="<%=request.getContextPath()%>/item.do?action=MyItem">我的商品</a></li>
								<li><a href="<%=request.getContextPath()%>/itemdetails.do?action=getOneBuyer_front">我的訂單</a></li>
								<li><a href="<%=request.getContextPath()%>/itemdetails.do?action=getOneSeller_front">已售出商品</a></li>
							</ul>
							</li><!--雙引號內請放連結-->
								<li><a href="<%=request.getContextPath()%>/front.do?navaction=QA">Q&A</a></li><!--雙引號內請放連結-->
						</ul>
						<!-- 右選單 -->
						<ul class="nav navbar-nav navbar-right"><!--navbar-right靠左-->
							<c:choose>
								<c:when test="${not empty memberVO}">
									<li><a href="#">${memberVO.mem_name},您好!</a></li>
									<li><a href="<%=request.getContextPath()%>/login/login.do?action=logout">登出</a></li>
									<li><a href="<%=request.getContextPath()%>/front.do?navaction=home">個人中心</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="<%=request.getContextPath()%>/front-end/Login/Login.jsp">登入</a></li>
									<li><a href="<%=request.getContextPath()%>/front-end/register/register.jsp">註冊</a></li>
								</c:otherwise>
							</c:choose>
							<li><a href="#"></a></li>
						</ul>
					</div>
				</nav>
				<div class="col-sm-12"></div>
			</div>
<script>
	function userLogout(){
		var reConfirm = confirm("確定要登出！");
		if(reConfirm){
			window.location = "<%=request.getContextPath()%>/login/login.do?action=logout";
		}else{
			return false;
		}
	}
</script>