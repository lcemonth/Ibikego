<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-12" style="background-color: #ffffff;height: 150px;">
	<div class="col-sm-2 row" style="height: 100%;">
		<a href="<%=request.getContextPath()%>/front.do?navaction=home">
			<img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memberVO.mem_no}" class="row" style="height: 100%;">
		</a>
	</div><!--class要加上row滿版才能靠左-->
	<div class="col-sm-5">
		<table>
			<tr><th style="font-size: 32px">${listOneUser.mem_name}</th><th></th></tr>
			<tr><td align='center'>暱稱:</td><td>${listOneUser.mem_nickname}</td></tr>
			<tr><td align='center'>信箱:</td><td>${listOneUser.mem_email}</td></tr>
			<tr><td align='center'>電話:</td><td>${listOneUser.mem_phone}</td></tr>
			<tr><td align='center'>地址:</td><td>${listOneUser.mem_add}</td></tr>
		</table>
	</div>
	<a href="<%=request.getContextPath()%>/front.do?navaction=userInsertBlog">
		<div class="row a1"><p>寫文章</p></div>
	</a>
	<a href="<%=request.getContextPath()%>/front.do?navaction=userInsertTravelPoint">
		<div class="row b1"><p>加旅遊點</p></div>
	</a>
	<a href="<%=request.getContextPath()%>/front.do?navaction=insertFriend">
		<div class="row c1"><p>加好友</p></div>
	</a>
</div>