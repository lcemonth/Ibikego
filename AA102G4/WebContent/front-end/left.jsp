<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	.btnact{
				border-radius:10px;
				background-color:#66B3FF;
			}
</style>
			<div class="col-xs-3 col-md-2 col-sm-2">
				<div class="list-group">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
						<c:if test="${!not empty memberVO}">
							<div>
								<img src="<%=request.getContextPath()%>/res/images/noName.jpg" width="100" height="100">
								<br><br>
								<a href="<%=request.getContextPath()%>/front-end/activity/act_f_listNewForGuest.jsp" class="list-group-item btnact">訪客瀏覽</a>
							</div>
				        </c:if>
						<c:if test="${not empty memberVO}">
						
						  <!-- 區塊1 -->
						  <img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memberVO.mem_no}" width="100" height="100">
							<br><br>
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab1" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#aaa" aria-expanded="true" aria-controls="aaa">
						         	 瀏覽揪團
						        </a>
						      </h4>
						    </div>
						    <div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab1">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
							       	<div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=fquery" class="list-group-item btnact">瀏覽最新揪團</a>
									</div>
									<div>	
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=queryMyJoin" class="list-group-item">查詢揪團</a>
									</div>	
									<div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=listInvited" class="list-group-item btnact">列出被邀請的團</a>
								   </div>
							   </div>
						      </div>
						    </div>
						  </div>
						
						
						
						  <!-- 區塊2 -->
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab2" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#bbb" aria-expanded="true" aria-controls="bbb">
						         	發起揪團
						        </a>
						      </h4>
						    </div>
						    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab2">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
									<a href="<%=request.getContextPath()%>/activity/activity.do?action=newAct" class="list-group-item btnact">發起揪團</a>
							   </div>
						      </div>
						    </div>
						  </div>
						  
						   <!-- 區塊3 -->
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab3" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#ccc" aria-expanded="true" aria-controls="ccc">
						         	管理揪團
						        </a>
						      </h4>
						    </div>
						    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab3">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
							       <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=updatefAct" class="list-group-item btnact">修改揪團</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=cancelfAct" class="list-group-item ">取消發起</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=manag_memfAct" class="list-group-item btnact">管理揪團成員</a>
								   </div>
							   </div>
						      </div>
						    </div>
						  </div>
						  
						  <!-- 區塊4 -->
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab4" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#ddd" aria-expanded="true" aria-controls="ddd">
						         	歷史紀錄查詢
						        </a>
						      </h4>
						    </div>
						    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab4">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
							       <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=queryLaunchactStatus" class="list-group-item btnact">發起揪團招募狀況</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=queryJoinactStatus" class="list-group-item ">參加揪團招募狀況</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=historyLaunchAct" class="list-group-item btnact">查詢歷史發起紀錄</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=historyJoinAct" class="list-group-item ">查詢歷史參加紀錄</a>
								   </div>
							   </div>
						      </div>
						    </div>
						  </div>
						  
						  <!-- 區塊5 -->
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="tab5" style="background-color:pink;">
						      <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#eee" aria-expanded="true" aria-controls="eee">
						         	收藏檢舉測試
						        </a>
						      </h4>
						    </div>
						    <div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab5">
						      <div class="panel-body" style="background-color:lightblue;border-radius:20px;">
						       <div class="list-group">
							       <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=colAndrepForum" class="list-group-item btnact">收藏檢舉討論區</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=colAndrepTravel" class="list-group-item ">收藏檢舉旅遊點</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=colAndrepBlog" class="list-group-item btnact">收藏檢舉日誌</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=colAndrepStroke" class="list-group-item ">收藏檢舉行程</a>
								   </div>
								   <div>
										<a href="<%=request.getContextPath()%>/activity/activity.do?action=testSocket" class="list-group-item ">測試</a>
								   </div>
								   
							   </div>
						      </div>
						    </div>
						  </div>
						  
						</c:if>
					 </div>
				</div>
			</div>