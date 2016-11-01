<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head><title>IBM BlogScore: Home</title></head>
	<body bgcolor='white'>
		<table border='1' cellpadding='5' cellspacing='0' width='400'>
		  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		    <td><h3>IBM BlogScore: Home</h3><font color=red>( MVC )</font></td>
		  </tr>
		</table>
		<p>This is the Home page for IBM BlogScore: Home</p>
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
		  <li><a href='<%=request.getContextPath()%>/blogScore/blogScore.do?action=listAll'>List</a> all BlogScore. </li> <br><br>
		  <li>
		    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogScore/blogScore.do" >
		        <b>輸入日誌編號 (如111):</b>
		        <input type="text" name="blog_no">
		        <input type="submit" value="送出">
		        <input type="hidden" name="action" value="getOne_For_Display">
		    </FORM>
		  </li>
		
		  <jsp:useBean id="blogScoreSvc" scope="page" class="com.blogScore.model.BlogScoreService" />
		   
		  <li>
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogScore/blogScore.do" >
		       <b>選擇日誌編號:</b>
		       <select size="1" name="blog_no">
		         <c:forEach var="blogScoreVO" items="${blogScoreSvc.all}" > 
		          <option value="${blogScoreVO.blog_no}">${blogScoreVO.blog_no}
		         </c:forEach>   
		       </select>
		       <input type="submit" value="送出">
		       <input type="hidden" name="action" value="getOne_For_Display">
		    </FORM>
		  </li>
		  
		  <li>
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogScore/blogScore.do" >
		       <b>選擇分數:</b>
		       <select size="1" name="blog_no">
		         <c:forEach var="blogScoreVO" items="${blogScoreSvc.all}" > 
		          <option value="${blogScoreVO.blog_no}">${blogScoreVO.blog_score}
		         </c:forEach>   
		       </select>
		       <input type="submit" value="送出">
		       <input type="hidden" name="action" value="getOne_For_Display">
		     </FORM>
		  </li>
		</ul>
		<h3>日誌管理</h3>
		<ul>
		  <li><a href='<%=request.getContextPath()%>/blogScore/blogScore.do?action=add'>Add</a> a new BlogScore.</li>
		</ul>
	</body>
</html>