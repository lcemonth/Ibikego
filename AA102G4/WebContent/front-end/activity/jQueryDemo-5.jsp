<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
<title>jQueryDemoPage-�ƥ�j�w</title>
<style type="text/css">
<!--
.oddClass {
  background-color:red;
}
.evenClass {
  background-color:green;
}
.chgClass {
  background-color:yellow;
}
-->
</style>
<script type="text/javascript">
$(document).ready(
	 function test(){
	   //����ajax�϶�
	   $("#btnAjax").click(function (){
		   var url="ajaxResponse.jsp";
	       $("#div1").load(url,{"param1":document.getElementById('start').value,"param2":document.getElementById('end').value});
	   });
	   $("#start").bind("change",function(){
		   var url="ajaxResponse_sdate.jsp";
	       $("#div2").load(url,{"param1":document.getElementById('start').value});
	   });
	   
})
</script>
</head>
<body>
	<form method="get" name="myForm1">
		�_�l���:<input type="text" id="start"></input><div id="div2"></div>
		<br>
		�פ���:<input type="text" id="end"></input><div id="div3"></div>
		<br>
		<div id="div1"></div>
		<br>
		<input type="button" name="btnAjax" id="btnAjax" value="����ajax"><br>
		
		
	</form>
</body>
</html>