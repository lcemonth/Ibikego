<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	#navigation {
		width: 200px;
		font-family: Arial, Helvetica, sans-serif;
	}
	#navigation ul {
		list-style: none;
		margin: 0px;
		padding: 0px;
	}
	#navigation li {
		border-bottom: 1px solid #ED9F9F;
	}
	#navigation li:last-child {
		border-bottom: 0px solid #ED9F9F;
	}
	.mainTitle span, #navigation li a:link, #navigation li a:visited {
		display: block;
		padding: 5px 5px 5px 0.5em;
		border-left: 12px solid #711515;
		border-right: 1px solid #711515;
		background-color: #00A15C;
		color: #FFFFFF;
		text-decoration: none;
		border-top-right-radius: 3px;
		border-bottom-right-radius: 3px;
	}
	.mainTitle span:hover, #navigation li a:hover {
		background-color: #711515;
		color: #FFFFFF;
		cursor: pointer;
	}
	#navigation ul ul {
		margin-left: 12px;
	}
	#navigation ul ul li {
		border-bottom: 1px solid #711515;
		margin: 0;
	}
	#navigation ul ul a:link, #navigation ul ul a:visited {
		background-color: #87CEEB;
		color: #711515;
	}
	#navigation ul ul a:hover {
		background-color: #711515;
		color: #FFFFFF;
	}
</style>
<script type="text/javascript" src="jquery-1.7.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#menu > li > ul').hide().click(function(event) {
			event.stopPropagation();
		});
		$('#menu > li').toggle(function(){
			$(this).find('ul').slideDown();
		}, function(){
			$(this).find('ul').slideUp();
		});
	});
</script>
<div class="col-md-2">
	<div class="list-group">
		<div>
			<img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memberVO.mem_no}" width="100" height="100">
			<br><br>
			<div id="navigation">
				<ul id="menu">
					<li class="mainTitle"><span>個人資訊</span>
						<ul class="subMenu">
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=userInfo" class="list-group-item">基本資料</a></li>
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=userPwUpdate" class="list-group-item">密碼修改</a></li>
						</ul>
					</li>
					<li class="mainTitle"><span>我的旅遊點</span>
						<ul class="subMenu">
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=myTravelPoint">管理</a></li>
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=collectPoint">收藏</a></li>
						</ul>
					</li>
					<li class="mainTitle"><span>我的揪團</span>
						<ul class="subMenu">
							<li><a href="#">管理</a></li>
							<li><a href="#">邀請</a></li>
						</ul>
					</li>
					<li class="mainTitle"><span>我的行程</span>
						<ul class="subMenu">
							<li><a href="#">管理</a></li>
							<li><a href="#">收藏</a></li>
						</ul>
					</li>
					<li class="mainTitle"><span>我的日誌</span>
						<ul class="subMenu">
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=myBlog">管理</a></li>
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=collectBlog">收藏</a></li>
						</ul>
					</li>
					<li class="mainTitle"><span>我的討論區</span>
						<ul class="subMenu">
							<!-- 世麒 -->
							<li><a href="<%=request.getContextPath()%>/forum.do?action=forum_mem&mem_no=${memberVO.mem_no}">管理</a></li>
							
							<li><a href="<%=request.getContextPath()%>/forum.do?action=forum_satae&mem_no=${memberVO.mem_no}">收藏</a></li>
							<!--	世麒 -->
						</ul>
					</li>
					<li class="mainTitle"><span>我的商城</span>
						<ul class="subMenu">
							<li><a href="#">管理</a></li>
							<li><a href="#">收藏</a></li>
						</ul>
					</li>
					<li class="mainTitle"><span>關係</span>
						<ul class="subMenu">
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=insertFriend">會員搜尋</a></li>
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=myFriend">關注對象</a></li>
							<li><a href="<%=request.getContextPath()%>/front.do?navaction=blacklist">黑名單</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

			