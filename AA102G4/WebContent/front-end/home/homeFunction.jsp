<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-2">
	<div class="panel-group" id="accordion2" role="tablist">
		<!-- 區塊1 -->
		<div class="panel panel-primary aaa">
			<div class="panel-heading" role="tab" id="tab1">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#aaa" aria-expanded="false" aria-controls="aaa">個人資訊</a>
				</h4>
			</div>
			<div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab1">
				<div class="panel-body">
					<div class="list-group">
						<a href="<%=request.getContextPath()%>/front.do?navaction=userInfo" class="list-group-item">
							<h5 class="list-group-item-heading">基本資料</h5>
						</a>
						<a href="<%=request.getContextPath()%>/front.do?navaction=userPwUpdate" class="list-group-item">
							<h5 class="list-group-item-heading">密碼修改</h5>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 區塊2 -->
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="tab2">
				<h4 class="panel-title">
					<a class="collapsed " role="button" data-toggle="collapse" data-parent="#accordion2" href="#bbb" aria-expanded="false" aria-controls="bbb">我的旅遊點</a>
				</h4>
			</div>
			<div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab2">
				<div class="panel-body">
					<div class="list-group">
						<a href="<%=request.getContextPath()%>/front.do?navaction=myTravelPoint" class="list-group-item">
							<h5 class="list-group-item-heading">管理</h5>
						</a>
						<a href="<%=request.getContextPath()%>/front.do?navaction=collectPoint" class="list-group-item">
							<h5 class="list-group-item-heading">收藏</h5>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 區塊3 -->
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="tab3">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ccc" aria-expanded="false" aria-controls="ccc">我的日誌</a>
				</h4>
			</div>
			<div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab3">
				<div class="panel-body">
					<div class="list-group">
						<a href="<%=request.getContextPath()%>/front.do?navaction=myBlog" class="list-group-item">
							<h5 class="list-group-item-heading">管理</h5>
						</a>
						<a href="<%=request.getContextPath()%>/front.do?navaction=collectBlog" class="list-group-item">
							<h5 class="list-group-item-heading">收藏</h5>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 區塊4 -->
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="tab4">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ddd" aria-expanded="false" aria-controls="ddd">我的揪團</a>
				</h4>
			</div>
			<div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab4">
				<div class="panel-body">
					<div class="list-group">
						<a href="<%=request.getContextPath()%>/back_end/back_ord/back_ord.jsp" class="list-group-item">
							<h5 class="list-group-item-heading">管理</h5>
						</a>
						<a href="<%=request.getContextPath()%>/back_end/back_product/prodIndex.jsp" class="list-group-item">
							<h5 class="list-group-item-heading">收藏</h5>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 區塊5 -->
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="tab5">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#eee" aria-expanded="false" aria-controls="eee">我的行程</a>
				</h4>
			</div>
			<div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab5">
				<div class="panel-body">
					<div class="list-group">
						<a href="<%=request.getContextPath()%>/back_end/back_point/editAllPoint.jsp" class="list-group-item">
							<h5 class="list-group-item-heading">管理</h5>
						</a>
						<a href="<%=request.getContextPath()%>/back_end/back_point/editAllPoint.jsp" class="list-group-item">
							<h5 class="list-group-item-heading">收藏</h5>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 區塊6 -->
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="tab7">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#fff" aria-expanded="false" aria-controls="fff">我的討論</a>
				</h4>
			</div>
			<div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab7">
				<div class="panel-body">
					<div class="list-group">
						<a href="<%=request.getContextPath()%>/forum.do?action=forum_mem&mem_no=${memberVO.mem_no}" class="list-group-item">
							<h5 class="list-group-item-heading">管理</h5>
						</a>
						<a href="<%=request.getContextPath()%>/forum.do?action=forum_state&mem_no=${memberVO.mem_no}" class="list-group-item">
							<h5 class="list-group-item-heading">收藏</h5>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 區塊7 -->
		<div class="panel panel-primary">
			<div class="panel-heading" role="tab" id="tab7">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ggg" aria-expanded="false" aria-controls="ggg">關係</a>
				</h4>
			</div>
			<div id="ggg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab7">
				<div class="panel-body">
					<div class="list-group">
						<a href="<%=request.getContextPath()%>/front.do?navaction=myFriend" class="list-group-item">
							<h5 class="list-group-item-heading">關注</h5>
						</a>
						<a href="<%=request.getContextPath()%>/front.do?navaction=blacklist" class="list-group-item">
							<h5 class="list-group-item-heading">黑名單</h5>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>	<!-- panel-group -->
</div>	<!-- col-xs-2 -->