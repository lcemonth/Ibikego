<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Forum: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Forum: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

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
  <li><a href='<%=request.getContextPath()%>/forum.do?action=query'>List</a> all Forums. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do" >
        <b>輸入文章編號 (如100001):</b>
        <input type="text" name="forum_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="select">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do" >
        <b>輸入會員編號 (如111):</b>
        <input type="text" name="mem_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="select_mem_no">
    </FORM>
  </li>

  <jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="mem_no">
         <c:forEach var="forumVO" items="${forumSvc.all}" > 
          <option value="${forumVO.mem_no}">${forumVO.mem_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="select_mem_no">
    </FORM>
  </li>
  
  
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/forum.do?action=add'>Add</a> a new Forum.</li>
</ul>

</body>

</html>
