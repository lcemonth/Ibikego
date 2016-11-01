<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.stroke.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
   ActivityVO actVO = (ActivityVO) request.getAttribute("actVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
   StrokeService strokeSvc=new StrokeService();
   Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
   List<StrokeVO> list=strokeSvc.getStrokesByMem_no(mem_no);
   MemberVO memVO= (MemberVO)session.getAttribute("memVO");
   pageContext.setAttribute("list",list);
   pageContext.setAttribute("actVO",actVO);
%>
 <jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
 <jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>揪團資料修改</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">

	<script src="<%=request.getContextPath()%>/res/js/sweetalert2.js"></script>
	<script src="<%=request.getContextPath()%>/res/js/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.min.css">	
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/My97DatePicker/WdatePicker.js"></script>
	<script src="<%=request.getContextPath()%>/res/js/upload_show.js"></script> 
	<script type="text/javascript">
		$(function(){
	// 		alert($("#imgDiv").size());
		})
		
		function ValidateFloat(e, pnumber)
		{
		    if (!/^\d+[.]?\d*$/.test(pnumber))
		    {
		        $(e).val(/^\d+[.]?\d*/.exec($(e).val()));
		    }
		    return false;
		}
		
		function ValidateNumber(e, pnumber)
		{
		    if (!/^\d+$/.test(pnumber))
		    {
		        $(e).val(/^\d+/.exec($(e).val()));
		    }
		    return false;
		}
		
		function vali(){
			   //測試ajax區塊
			    function aa (){
				  
					   if($("#act_name").val()=="" || $("#act_name").val()==null){
						   $("#btn_save").attr('disabled',true);
						   alert("請輸入揪團名稱"); 
					   }else if($("#act_loc").val()=="" || $("#act_loc").val()==null){
						   $("#btn_save").attr('disabled',true);
						   alert("請輸入揪團地點"); 
					   }else{
						   if($("#act_km").val()=="" || $("#act_km").val()==null){
							   $("#act_km").val(0.0);
						   }	   
						   $("#btn_save").attr('disabled',false);
						   $("#form1").submit();
					   }
			   };
			   aa();
		}
</script>
<style type="text/css">
     		body{
				 background-color: #26a8ff;
			}	
			 textarea{/* Text Area 固定大小*/
				 max-width:450px;
				 max-height:100px;
				 width:450px;
				 height:100px;
			}
			.col-md-2{
				/*width: 100%;
				background-color: #fcf;*/
				height: 100px;
				text-align:center;
			}
			img{
				width: 100px;
				height: 100px;
				border-radius:10px 10px 10px 10px;
			}
</style>
</head>
	<body>
	   
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="col-xs-9 col-md-10 col-sm-10">
		<div class="row">
 			<jsp:include page="/front-end/activity/left.jsp"></jsp:include> 
		<div class="col-xs-9 col-md-10 col-sm-10 ">
			<div class="container">
				<!-- 中間資料列-->
				<div class="col-md-9 col-sm-9 ">
					
						<h1>
							揪團<small>修改資料</small>
						</h1>
							<table border='1' cellpadding='5' cellspacing='0' width='825'>
								<tr bgcolor='#EA7500' align='center' valign='middle' height='20'>
									<td><a
										href='<%=request.getContextPath()%>/activity/activity.do?action=backfindex'><img
											src="<%=request.getContextPath()%>/res/images/c.png"
											width="100" height="100" border="1">回首頁</a></td>
							</table>

				<FORM METHOD="post" ACTION="activity.do" id="form1" name="form1" enctype="multipart/form-data">
				<table class="table table-bordered table-hover" border='1' cellpadding='5' cellspacing='0' width='1000'>
					<tr>
						<td bgcolor="lightblue">揪團編號:<font color=red><b>*</b></font></td>
						<td bgcolor="#FFC78E">${actVO.act_no}</td>
					</tr>
					<tr>
						<td bgcolor="lightblue">主揪編號:</td>
						<td bgcolor="#FFC78E">${actVO.mem_no}</td>
					</tr>
					<tr>
						<td bgcolor="lightblue">主揪姓名:</td>
						<td bgcolor="#FFC78E">
							<c:forEach var="memVO" items="${memSvc.all}" > 
					          	<c:if test="${actVO.mem_no==memVO.mem_no}">
					          		${memVO.mem_name}
					          	</c:if>	
					        </c:forEach> 
						</td>
					</tr>
					<tr>
						<td bgcolor="lightblue">揪團地點:</td>
						<td bgcolor="#FFC78E"><input type="TEXT" id="act_loc" name="act_loc" size="45"
							value="<%=actVO.getAct_loc()%>" 
							/></td>
					</tr>
					<tr>
						<td bgcolor="lightblue">地區:</td>
				<%-- 		<td><input type="TEXT" name="loc_no" size="45"	value="<%=actVO.getLoc_no()%>" /></td> --%>
						<td bgcolor="#FFC78E"> 
				        	 <select size="1" name="loc_no">
					         	<c:forEach var="locVO" items="${locSvc.all}" > 
					          		<option value="${locVO.loc_no}" ${(actVO.loc_no==locVO.loc_no)?'selected':'' }>${locVO.loc_name}
					         	</c:forEach>   
				      		 </select>	
				      	</td>
					</tr>
					<tr>
						<td bgcolor="lightblue">行程編號:</td>
				<%-- 		<td><input type="TEXT" name="stroke_no" size="45"	value="<%=actVO.getStroke_no()%>" /></td> --%>
						<td bgcolor="#FFC78E">	
							 <select size="1" name="stroke_no">
							    <option value="0">請選擇
					         	<c:forEach var="strokeVO" items="${list}" > 
					          		<option value="${strokeVO.stroke_no}" ${(actVO.stroke_no==strokeVO.stroke_no)?'selected':'' }>${strokeVO.stroke_name}
					         	</c:forEach>   
				      		 </select>	
				      	</td>	 
					</tr>
					<tr>
						<td bgcolor="lightblue">揪團名稱:</td>
						<td bgcolor="#FFC78E"><input type="TEXT" id="act_name" name="act_name" size="45"	
							value="<%=(actVO.getAct_name()== null) ? "" : actVO.getAct_name()%>"/></td>
					</tr>
					<tr>
						<td bgcolor="lightblue">揪團資訊:</td>
						<td bgcolor="#FFC78E">
						<textarea rows="6" cols="500"  id="myTextarea" name="act_exp" >${(actVO.act_exp==null or actVO.act_exp=="" or actVO.act_exp=="null")? "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;請輸入" : actVO.act_exp }</textarea>
						</td>
					</tr>
					<tr>
						<td bgcolor="lightblue">開始日期:</td>
						<td bgcolor="#FFC78E">
							<%=actVO.getAct_start_date()%>
						</td>
						
					</tr>
					
					<tr>
						<td bgcolor="lightblue">結束日期:</td>
						
						<td bgcolor="#FFC78E">
							<%=actVO.getAct_end_date()%>
						</td>
					</tr>
					
					<tr>
						<td bgcolor="lightblue">揪團公開:</td>
				<%-- 		<td><input type="TEXT" name="act_is_public" size="45"	value="<%=actVO.getAct_is_public()%>" /></td> --%>
						<td bgcolor="#FFC78E">
							<select size="1" name="act_is_public">
							    
								<option value="0" ${(actVO.act_is_public==0)?'selected':'' }>${activityMAP["0"]}
						        <option value="1" ${(actVO.act_is_public==1)?'selected':'' }>${activityMAP["1"]}
							</select>
						</td>
					</tr>
					<tr>
						<td bgcolor="lightblue">揪團總公里數:</td>
						<td bgcolor="#FFC78E"><input type="TEXT" id="act_km" name="act_km" size="45" maxlength="6"	
						    value="<%=(actVO.getAct_km() == null) ? "0.0" : actVO.getAct_km()%>" 
							style="ime-mode:disabled" onkeyup="return ValidateFloat($(this),value)"/>
						</td>
					</tr>
					<tr>
						<td bgcolor="lightblue">揪團人數上限:</td>
						<td bgcolor="#FFC78E"><input type="TEXT" id="act_joinlimit" name="act_joinlimit" size="45" maxlength="3"
							value="<%=(actVO.getAct_joinlimit() == null) ? "1" : actVO.getAct_joinlimit()%>" 
							style="ime-mode:disabled" onkeyup="return ValidateNumber($(this),value)"/>
						</td>
					</tr>
					<tr>
						<td bgcolor="lightblue">揪團主題圖:</td>
						<td bgcolor="#FFC78E">
							<div id="imgDiv">
								<img id="image1" src="activityPhoto.do?act_no=${actVO.act_no}"  width="100" height="100"/>
							</div>
							
							<div id="imgDiv2" style="display:none">
								<img id="image2"  width="100" height="100"/>
							</div>
							
							上傳<input type="file" id="myFile1" name="act_photo"  size="15">
							<!-- 預覽上傳的code在res/js/upload_show.js-->
						</td>
					</tr>
				</table>
				<br>
				<input type="hidden" name="action" value="fupdate">
				<input type="hidden" name="act_no" value="<%=actVO.getAct_no()%>">
				<input type="hidden" name="mem_no" value="<%=actVO.getMem_no()%>">
				<input type="hidden" name="act_start_date" value="<%=actVO.getAct_start_date()%>">
				<input type="hidden" name="act_end_date" value="<%=actVO.getAct_end_date()%>">
				<input type="button" id="btn_save" value="送出修改" class="btn btn-lg btn-primary" onclick="vali();">
				<c:if test="${not empty errorMsgs}">
		        	 <div class="col-xs-1 col-sm-2" id="aaa" style="width:400px;border:3px #cccccc dashed;float:right;">
					<%-- 錯誤表列 --%>
								<c:forEach var="message" items="${errorMsgs}">
									 <p class="bg-danger">${message}</p>
								</c:forEach>
					</div>
				</c:if>		
				<c:if test="${not empty successMsgs}">
					<div class="col-xs-1 col-sm-2" id="aaa" style="width:300px;border:3px #cccccc dashed;">
								<c:forEach var="message" items="${successMsgs}">
									<div class="container">
									  <p class="bg-success">${message}</p>
									</div>
								</c:forEach>
					</div>			
				</c:if>	
				</FORM>
			   </div>	<%-- col-sm-7 --%>
			   <div class="col-sm-3 "></div><%-- col-sm-5 --%>		
			 </div>
	   		</div>
		  </div>
		</div>
	</body>
</html>