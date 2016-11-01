<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>AA102G4 揪團首頁</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Emp: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>揪團首頁</p>

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
<!--   <li><a href='listAllAct.jsp'>List</a> 全部揪團列表 </li> <br><br> -->
       <li><a href="<%=request.getContextPath()%>/activity/activity.do?action=allList">List</a> 全部揪團列表 </li> <br><br>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" >
        <b>輸入揪團 (如13):</b>
        <input type="text" name="act_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

</ul>


<h3>員工管理</h3>

<ul>
  
  <li><a href="<%=request.getContextPath()%>/activity/activity.do?action=creAct">增加</a>揪團</li>
</ul>

</body>

</html>
