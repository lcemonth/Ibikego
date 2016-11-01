<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="listItemImages_ByItem_no" scope="request" type="java.util.Set" />
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
<div class="container">
		<div class="row">
		<div class="col-sm-12">
		<table class="table table-hover table-bordered table-condensed" ><!-- table-striped影響顯示 -->
			<tr bgcolor="#dff0d8">
				<th>圖片編號</th>
				<th>商品編號</th>
				<th>圖片</th>
				<th>修改</th>
				<th>刪除</th>
				
			</tr>
			
			<c:forEach var="itemImageVO" items="${listItemImages_ByItem_no}" >
				<tr align='center' valign='middle' ${(ItemImageVO.item_img_no==param.item_img_no) ? 'bgcolor=#d9edf7':''}><!--將修改的那一筆加入對比色而已-->
					<td>${itemImageVO.item_img_no}</td>
					<td>${itemImageVO.item_no}</td>
					<td><img src="<%=request.getContextPath()%>/getPostersServelt?item_img_no=${itemImageVO.item_img_no}" width="100px" height="100px" class="img-rounded"></td>			
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemimage.do?action=getOne_For_Update">
					    <input type="submit" value="修改" class="btn btn-warning"
					    <c:if test="${itemSvc.getOneItem(itemImageVO.item_no).item_is_added==2}"> disabled</c:if>> 
					    <input type="hidden" name="item_img_no"value="${itemImageVO.item_img_no}">
					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
					    </FORM>
					</td>
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemimage.do">
					    <input type="submit" value="刪除" class="btn btn-danger"
					    <c:if test="${itemSvc.getOneItem(itemImageVO.item_no).item_is_added==2}"> disabled</c:if> >
					    <input type="hidden" name="item_img_no" value="${itemImageVO.item_img_no}">
					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
					    <input type="hidden" name="action" value="delete"></FORM>
					</td>
					
				</tr>
			</c:forEach>
		</table>

			</div>
		</div>
	</div>
