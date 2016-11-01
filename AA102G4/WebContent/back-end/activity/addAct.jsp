<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%
ActivityVO actVO = (ActivityVO) request.getAttribute("actVO");
%>

<html>
<head>
<title>員工資料新增 - addEmp.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	textarea{/* Text Area 固定大小*/
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
		<h3>揪團資料新增 - addAct.jsp</h3>
		</td>
		<td>
		   <a href='<%=request.getContextPath()%>/activity/activity.do?action=backHome'><img src="<%=request.getContextPath()%>/res/images/c.png" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>揪團地點:</td>
		<td><input type="TEXT" name="act_loc" size="45"	value="<%=(actVO==null)? "四號公園" : actVO.getAct_loc()%>" /></td>
	</tr>
	<tr>
		<td>地區編號:</td>
		<td><input type="TEXT" name="loc_no" size="45"	value="<%=(actVO==null)? "1" : actVO.getLoc_no()%>" /></td>
	</tr>
	<tr>
		<td>行程編號:</td>
		<td><input type="TEXT" name="stroke_no" size="45"	value="<%=(actVO==null)? "" : actVO.getStroke_no()%>" /></td>
	</tr>
	<tr>
		<td>揪團名稱:</td>
		<td><input type="TEXT" name="act_name" size="45"	value="<%=(actVO==null)? "XXX揪團" : actVO.getAct_name()%>" /></td>
	</tr>
	<tr>
		<td>揪團資訊:</td>
		<td>
			<textarea rows="6" cols="1000" name="act_exp">
					
			</textarea>		
		</td>
	</tr>
	<tr>
		<td>開始日期:</td>
		<td>
			<input type="TEXT" name="act_start_date" size="45" "	onclick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd'})" readonly /> 
		</td>
	</tr>
	
	<tr>
		<td>結束日期:</td>
		<td>
			<input type="TEXT" name="act_end_date"   size="45"	    onclick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd'})" readonly />
		</td>
	</tr>
	
	<tr>
		<td>揪團公開:</td>
		<td><input type="TEXT" name="act_is_public" size="45"	value="<%=(actVO==null)? "0" : actVO.getAct_is_public()%>" /></td>
	</tr>
	<tr>
		<td>揪團總公里數:</td>
		<td><input type="TEXT" name="act_km" size="45"	value="<%=(actVO==null)? "0.0": actVO.getAct_km()%>" /></td>
	</tr>
	<tr>
		<td>揪團人數上限:</td>
		<td><input type="TEXT" name="act_joinlimit" size="45"	value="<%=(actVO==null)? "1" : actVO.getAct_joinlimit()%>" /></td>
	</tr>
	<tr>
		<td>揪團主題圖:</td>
		<td>
			上傳更新<input type="file" name="act_photo" size="15">
		</td>
	</tr>

	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
