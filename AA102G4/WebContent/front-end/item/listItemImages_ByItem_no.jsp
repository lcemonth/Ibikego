<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="listItemImages_ByItem_no" scope="request" type="java.util.Set" />
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
			<c:forEach var="itemImageVO" items="${listItemImages_ByItem_no}" >
				<li><img src="<%=request.getContextPath()%>/getPostersServelt?item_img_no=${itemImageVO.item_img_no}"></li>
			</c:forEach>
