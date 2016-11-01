<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head><title>IBM TravelScore: Home</title></head>
	<body bgcolor='white'>
		<table border='1' cellpadding='5' cellspacing='0' width='400'>
		  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		    <td><h3>IBM TravelScore: Home</h3><font color=red>( MVC )</font></td>
		  </tr>
		</table>
		<p>This is the Home page for IBM TravelScore: Home</p>
		<h3>資料查詢:</h3>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		<ul>
		  <li><a href='<%=request.getContextPath()%>/travelScore/travelScore.do?action=listAll'>List</a> all TravelScore. </li> <br><br>
		  <li>
		    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travelScore/travelScore.do" >
		        <b>輸入旅遊點編號 (如111):</b>
		        <input type="text" name="tra_no">
		        <input type="submit" value="送出">
		        <input type="hidden" name="action" value="getOne_For_Display">
		    </FORM>
		  </li>

		  <jsp:useBean id="travelScoreSvc" scope="page" class="com.travelScore.model.TravelScoreService" />

		  <li>
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travelScore/travelScore.do" >
		       <b>選擇旅遊點編號:</b>
		       <select size="1" name="tra_no">
		         <c:forEach var="travelScoreVO" items="${travelScoreSvc.all}" > 
		          <option value="${travelScoreVO.tra_no}">${travelScoreVO.tra_no}
		         </c:forEach>   
		       </select>
		       <input type="submit" value="送出">
		       <input type="hidden" name="action" value="getOne_For_Display">
		    </FORM>
		  </li>
		  
		  <li>
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/travelScore/travelScore.do" >
		       <b>選擇分數:</b>
		       <select size="1" name="tra_no">
		         <c:forEach var="travelScoreVO" items="${travelScoreSvc.all}" > 
		          <option value="${travelScoreVO.tra_no}">${travelScoreVO.tra_score}
		         </c:forEach>   
		       </select>
		       <input type="submit" value="送出">
		       <input type="hidden" name="action" value="getOne_For_Display">
		     </FORM>
		  </li>
		</ul>
		<h3>日誌管理</h3>
		<ul>
		  <li><a href='<%=request.getContextPath()%>/travelScore/travelScore.do?action=add'>Add</a> a new TravelScore.</li>
		</ul>
	</body>
</html>