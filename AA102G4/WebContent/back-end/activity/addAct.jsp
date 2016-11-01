<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%
ActivityVO actVO = (ActivityVO) request.getAttribute("actVO");
%>

<html>
<head>
<title>���u��Ʒs�W - addEmp.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	textarea{/* Text Area �T�w�j�p*/
			 max-width:250px;
			 max-height:100px;
			 width:250px;
			 height:100px;
			 margin: 5px;
			}
</style>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���θ�Ʒs�W - addAct.jsp</h3>
		</td>
		<td>
		   <a href='<%=request.getContextPath()%>/activity/activity.do?action=backHome'><img src="<%=request.getContextPath()%>/res/images/c.png" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>��ƭ��u:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>���Φa�I:</td>
		<td><input type="TEXT" name="act_loc" size="45"	value="<%=(actVO==null)? "�|������" : actVO.getAct_loc()%>" /></td>
	</tr>
	<tr>
		<td>�a�Ͻs��:</td>
		<td><input type="TEXT" name="loc_no" size="45"	value="<%=(actVO==null)? "1" : actVO.getLoc_no()%>" /></td>
	</tr>
	<tr>
		<td>��{�s��:</td>
		<td><input type="TEXT" name="stroke_no" size="45"	value="<%=(actVO==null)? "" : actVO.getStroke_no()%>" /></td>
	</tr>
	<tr>
		<td>���ΦW��:</td>
		<td><input type="TEXT" name="act_name" size="45"	value="<%=(actVO==null)? "XXX����" : actVO.getAct_name()%>" /></td>
	</tr>
	<tr>
		<td>���θ�T:</td>
		<td>
			<textarea rows="6" cols="1000" name="act_exp">
					
			</textarea>		
		</td>
	</tr>
	<tr>
		<td>�}�l���:</td>
		<td>
			<input type="TEXT" name="act_start_date" size="45" "	onclick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd'})" readonly /> 
		</td>
	</tr>
	
	<tr>
		<td>�������:</td>
		<td>
			<input type="TEXT" name="act_end_date"   size="45"	    onclick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd'})" readonly />
		</td>
	</tr>
	
	<tr>
		<td>���Τ��}:</td>
		<td><input type="TEXT" name="act_is_public" size="45"	value="<%=(actVO==null)? "0" : actVO.getAct_is_public()%>" /></td>
	</tr>
	<tr>
		<td>�����`������:</td>
		<td><input type="TEXT" name="act_km" size="45"	value="<%=(actVO==null)? "0.0": actVO.getAct_km()%>" /></td>
	</tr>
	<tr>
		<td>���ΤH�ƤW��:</td>
		<td><input type="TEXT" name="act_joinlimit" size="45"	value="<%=(actVO==null)? "1" : actVO.getAct_joinlimit()%>" /></td>
	</tr>
	<tr>
		<td>���ΥD�D��:</td>
		<td>
			�W�ǧ�s<input type="file" name="act_photo" size="15">
		</td>
	</tr>

	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>
