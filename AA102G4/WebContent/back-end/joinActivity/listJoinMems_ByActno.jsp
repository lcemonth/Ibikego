<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <jsp:useBean id="listJoinMemsOnLink" scope="session" type="java.util.Set" /> 
  
<%-- <c:out value="${listJoinMemsOnLink}"  default="listJoinMems無值"></c:out> --%>

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<%--  <c:out value="${memSvc.all}"  default="memSvc無值"></c:out>  --%>
<!DOCTYPE html>
<html >
	<head>
		<title>揪團資料</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/font-awesome.min.css" rel="stylesheet"/>
		<script src="<%=request.getContextPath()%>/res/js/sweetalert2.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/sweetalert2.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.min.css">
		<style type="text/css">
		    .col-sm-12{ 
				height: 27px; 
		 	}
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
		
			.table tr th{
				text-align:center;
			}
			textarea{/* Text Area 固定大小*/
			 max-width:250px;
			 max-height:100px;
			 width:250px;
			 height:100px;
			 margin: 5px;
			}
			#aaa{
				position:relative;
			}
		</style>
		
	</head>
	<body>
		<div class="row">
			
		</div>
		
		<div class="col-sm-12 "></div> <!-- 與TOP的距離 -->
		<br>
		<div class="row">
			
				<div class="row">
 		
 				<div class="col-xs-9 col-md-10 col-sm-10">
					<div class="container">
					 	<table class="table table-bordered table-hover">
							<tr class="success">
								<th>揪團編號</th>
								<th>參加會員</th>
								<th>照片</th>
								<th>是否參加</th>
<!-- 								<th>緯度</th> -->
<!-- 								<th>經度</th> -->
								<th>電話</th>
								
							</tr>
							 <%@ include file="/back-end/joinActivity/page1.file" %>
							<c:forEach var="jaVO" items="${listJoinMemsOnLink}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr class="info" align='center' valign='middle' ${(jaVO.act_no==param.act_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
									<td>${jaVO.act_no}</td>
									
										<c:forEach var="memVO" items="${memSvc.all}">
						                    <c:if test="${jaVO.mem_no==memVO.mem_no}">
							                   <td>
							                    ${memVO.mem_name}
							                   </td>
							                    <td><img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memVO.mem_no}" height=80 width=80/></td>
						                    </c:if>
						              	</c:forEach>
									
									
									<td>
										<c:if test="${jaVO.joinact_is_join==0}">
											${joinActivityMAP["0"]}
										</c:if>
										<c:if test="${jaVO.joinact_is_join==1}">
											${joinActivityMAP["1"]}
										</c:if>
										<c:if test="${jaVO.joinact_is_join==2}">
											${joinActivityMAP["2"]}
										</c:if>
										<c:if test="${jaVO.joinact_is_join==3}">
											${joinActivityMAP["3"]}
										</c:if>
										<c:if test="${jaVO.joinact_is_join==4}">
											${joinActivityMAP["4"]}
										</c:if>
									</td>
									
<%-- 									<td>${jaVO.joinact_lati}</td> --%>
<%-- 									<td>${jaVO.joinact_longi}</td>			 --%>
									<td>
										<c:forEach var="memVO" items="${memSvc.all}">
						                    <c:if test="${jaVO.mem_no==memVO.mem_no}">
							                    	<font >${memVO.mem_phone}</font>
						                    </c:if>
						                </c:forEach>
									</td>
						
								</tr>
							</c:forEach>
						
							
						</table>
						<%-- <% Integer act_no=Integer.valueOf(request.getParameter("act_no"));  --%>
						   
						<%--    %> --%>
						<%@ include file="/back-end/joinActivity/page2.file" %>
					  
	 					
					</div><!-- container-->
				 </div><!--內層 col-xs-9 col-md-10 col-sm-10-->
				</div><!-- 內層外的row-->
			</div><!--外層 col-xs-9 col-md-10 col-sm-10-->
		
			<div class="col-xs-1 col-sm-2" id="aaa" >
				<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
								<div class="container">
									<c:forEach var="message" items="${errorMsgs}">
										 <p class="bg-danger">${message}</p>
									</c:forEach>
								</div>
						</c:if>
						<c:if test="${not empty successMsgs}">
									<c:forEach var="message" items="${successMsgs}">
										<div class="container">
										  <p class="bg-success">${message}</p>
										</div>
									</c:forEach>
						</c:if>
			</div><!-- 外層  col-sm-2-->
	
</body>
</html>