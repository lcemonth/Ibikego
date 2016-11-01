<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>員工管理--新增</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			.col-md-12{
				/*width: 100%;
				background-color: #fdf;*/
				text-align:center;
			}
			.col-md-2{
				/*width: 100%;
				background-color: #fcf;*/
				height: 100px;
				text-align:center;
			}
			.col-md-10{
				/*width: 100%;
				background-color: #fdf;*/
				text-align:center;
			}
			.table{
				width:17%;
				text-align:left;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<jsp:include page="/back-end/left.jsp"></jsp:include>
			<div class="col-md-10">
				<div align="center" >
				<form METHOD="post" action="<%=request.getContextPath()%>/emp.do?action=insert" onsubmit="return verification();">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td>帳號</td>
								<td><input type="text" name="emp_acc" id="emp_acc" onchange="RepeatAcc(this.value)"></td>
							</tr>
							<tr>
								<td>姓名</td>
								<td><input type="text" name="emp_name" id="emp_name"></td>
							</tr>
							<tr>
								<td>Mail</td>
								<td><input type="text" name="emp_email" id="emp_email"></td>
							</tr>
							<tr>
								<td>電話(家)</td>
								<td><input type="text" name="emp_tel" id="emp_tel"></td>
							</tr>
							<tr>
								<td>電話(手)</td>
								<td><input type="text" name="emp_phone" id="emp_phone"></td>
							</tr>
							<tr>
								<td>備註</td>
								<td><input type="text" name="emp_ps" id="emp_ps"></td>
							</tr>
							<tr>
								<td>到職日期</td>
								<td><input type="text" name="emp_hire" id="emp_hire" onclick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd'})" readonly></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="送出" class="btn btn-info">
					<input type="button" value="神奇小按鈕"  onclick="test();"/>
				</form>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>
<script type="text/javascript">
	function test(){
		$("#emp_acc").val("test8");
		$("#emp_name").val("小田捲");
		$("#emp_email").val("toppyzmonth@gmail.com");
		$("#emp_tel").val("034880995");
		$("#emp_phone").val("0905777886");
		$("#emp_ps").val("工讀生");
		$("#emp_hire").val("2016-09-08");
	}

	function RepeatAcc(emp_acc){
		var url = "<%=request.getContextPath()%>/emp.do?action=ajax&emp_acc="+emp_acc;
		var req;
		if (window.XMLHttpRequest){
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject){
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		req.open("POST", url, false);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
		req.send(null);
		if (req.readyState == 4){
			if (req.status == 200){
				var response  = req.responseText;
				if(response == "false"){
					document.getElementById("emp_acc").value="";
					alert('帳號重覆，請重新輸入。');
					return false;
				}
			}              
		}	
		return true;
	}
	function verification(){
		if(document.getElementById("emp_acc").value==""){
			alert("請輸入帳號");
			return false;
		}
		if(document.getElementById("emp_name").value==""){
			alert("請輸入姓名");
			return false;
		}
		if(document.getElementById("emp_email").value==""){
			alert("請輸入Mail");
			return false;
		}
		if(document.getElementById("emp_tel").value==""){
			alert("請輸入電話(家)");
			return false;
		}
		if(document.getElementById("emp_phone").value==""){
			alert("請輸入電話(手)");
			return false;
		}
		if(document.getElementById("emp_hire").value==""){
			alert("請輸入到職日期");
			return false;
		}
	}
	

	
	
</script>