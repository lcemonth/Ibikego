<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>AA102G4 ���έ���</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Emp: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>���έ���</p>

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
<!--   <li><a href='listAllAct.jsp'>List</a> �������ΦC�� </li> <br><br> -->
       <li><a href="<%=request.getContextPath()%>/activity/activity.do?action=allList">List</a> �������ΦC�� </li> <br><br>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" >
        <b>��J���� (�p13):</b>
        <input type="text" name="act_no">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

</ul>


<h3>���u�޲z</h3>

<ul>
  
  <li><a href="<%=request.getContextPath()%>/activity/activity.do?action=creAct">�W�[</a>����</li>
</ul>

</body>

</html>
