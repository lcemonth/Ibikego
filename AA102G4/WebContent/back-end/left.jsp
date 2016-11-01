<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div class="col-xs-3 col-md-2 col-sm-2">
				<div class="list-group">
				
				<!-- 建宇開始 -->
					<c:if test="${navaction=='emp'}">
						<a href="<%=request.getContextPath()%>/emp.do?action=query" class="list-group-item">員工帳號管理</a>
					</c:if>
					<c:if test="${navaction=='memr'}">
						<a href="<%=request.getContextPath()%>/memr.do?action=query" class="list-group-item">會員帳號管理</a>
					</c:if>
				<!-- 建宇結束 -->
				
				<!-- 敏奇開始 -->	
					<c:if test="${navaction=='item'}">
						<a href="<%=request.getContextPath()%>/item.do?action=searchall" class="list-group-item">商品管理</a>
						<a href="<%=request.getContextPath()%>/itemdetails.do?action=allitemdetails" class="list-group-item">訂單管理</a>
						<a href="<%=request.getContextPath()%>/item.do?action=searchallover" class="list-group-item">完成紀錄</a>
					</c:if>
				<!-- 敏奇結束 -->
				
				<!-- 慶瑄開始 -->
					<c:if test="${navaction=='member'}">
						<a href="<%=request.getContextPath()%>/member/member.do?action=listAll" class="list-group-item">所有會員查詢</a>
						<a href="<%=request.getContextPath()%>/member/member.do?action=selectOne" class="list-group-item">單一會員查詢</a>
<%-- 						<a href="<%=request.getContextPath()%>/member/member.do?action=add" class="list-group-item">新增會員</a> --%>
					</c:if>
					
					<c:if test="${navaction=='qa'}">
						<a href="<%=request.getContextPath()%>/qa.do?action=query" class="list-group-item">QA</a>
					</c:if>
					
					<c:if test="${navaction=='travelPoint'}">
				        <a href="<%=request.getContextPath()%>/travel/travel.do?action=listAll" class="list-group-item">所有旅遊點查詢</a>
						<a href="<%=request.getContextPath()%>/travel/travel.do?action=selectOne" class="list-group-item">單一旅遊點查詢</a>
<%-- 						<a href="<%=request.getContextPath()%>/travel/travel.do?action=add" class="list-group-item">新增旅遊點</a> --%>
					</c:if>
					
					<c:if test="${navaction=='travelImage'}">
				        <a href="<%=request.getContextPath()%>/travelImage/travelImage.do?action=listAll" class="list-group-item">所有旅遊點圖片查詢</a>
						<a href="<%=request.getContextPath()%>/travelImage/travelImage.do?action=selectOne" class="list-group-item">單一旅遊點圖片查詢</a>
						<a href="<%=request.getContextPath()%>/travelImage/travelImage.do?action=add" class="list-group-item">新增旅遊點圖片</a>
					</c:if>
					<c:if test="${navaction=='blog'}">
				        <a href="<%=request.getContextPath()%>/blog/blog.do?action=listAll" class="list-group-item">所有日誌查詢</a>
						<a href="<%=request.getContextPath()%>/blog/blog.do?action=selectOne" class="list-group-item">單一日誌查詢</a>
<%-- 						<a href="<%=request.getContextPath()%>/blog/blog.do?action=add" class="list-group-item">新增日誌</a> --%>
					</c:if>
				<!-- 慶瑄結束 -->
				
				<!-- 世麒開始 -->
					<c:if test="${navaction=='forum'}">
						<a href="<%=request.getContextPath()%>/forum.do?action=query_back" class="list-group-item">討論區文章管理</a>
					</c:if>
					<c:if test="${navaction=='que_ans'}">
						<a href="<%=request.getContextPath()%>/que_ans.do?action=query_back" class="list-group-item">Q&A管理</a>
						<a href="<%=request.getContextPath()%>/que_ans.do?action=add_back" class="list-group-item">新增Q&A</a>
					</c:if>
				<!-- 世麒結束 -->
				
				<!-- 善合開始 -->
					<c:if test="${navaction=='actm'}">
						<a href="<%=request.getContextPath()%>/activity/activity.do?action=query" class="list-group-item">揪團查詢管理</a>
					</c:if> 
					<c:if test="${navaction=='repm'}">
						<a href="<%=request.getContextPath()%>/reportcollect/reportcollect.do?action=query" class="list-group-item">檢舉處理管理</a>
					</c:if>
				<!-- 善合結束 -->
					
					
				</div>
			</div>