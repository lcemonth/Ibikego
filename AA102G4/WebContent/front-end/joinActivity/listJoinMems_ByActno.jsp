<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <jsp:useBean id="listJoinMemsOnLink" scope="session" type="java.util.Set" /> 
  
<%-- <c:out value="${listJoinMemsOnLink}"  default="listJoinMems�L��"></c:out> --%>

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<%--  <c:out value="${memSvc.all}"  default="memSvc�L��"></c:out>  --%>
<!DOCTYPE html>
<html >
	<head>
		<title>���θ��</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="http://malsup.github.com/jquery.cycle.all.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
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
			textarea{/* Text Area �T�w�j�p*/
			 max-width:250px;
			 max-height:100px;
			 width:250px;
			 height:100px;
			 margin: 5px;
			}
			img{
				width: 100px;
				height: 100px;
				border-radius:10px 10px 10px 10px;
			}
			#aaa{
				position:relative;
			}
		</style>
		
	</head>
	<body>
		<div class="row">
			
		</div>
		
		<div class="col-sm-12 "></div> <!-- �PTOP���Z�� -->
		<br>
		<div class="row">
			
				<div class="row">
 		
 				<div class="col-xs-9 col-md-10 col-sm-10">
					<div class="container">
					 	<table class="table table-bordered table-hover">
							<tr class="success">
								<th>���νs��</th>
								<th>�ѥ[�|���s��</th>
								<th>�O�_�ѥ[</th>
								<th>�n��</th>
								<th>�g��</th>
								<th>�m�W</th>
								
							</tr>
							 <%@ include file="/front-end/joinActivity/page1.file" %>
							<c:forEach var="jaVO" items="${listJoinMemsOnLink}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr class="info" align='center' valign='middle' ${(jaVO.act_no==param.act_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
									<td>${jaVO.act_no}</td>
									<td>${jaVO.mem_no}</td>
									<td>${jaVO.joinact_is_join}</td>
									<td>${jaVO.joinact_lati}</td>
									<td>${jaVO.joinact_longi}</td>			
									<td>
										<c:forEach var="memVO" items="${memSvc.all}">
						                    <c:if test="${jaVO.mem_no==memVO.mem_no}">
							                    	<font color=orange>${memVO.mem_name}</font>
						                    </c:if>
						                </c:forEach>
									</td>
						
								</tr>
							</c:forEach>
						
							
						</table>
						<%-- <% Integer act_no=Integer.valueOf(request.getParameter("act_no"));  --%>
						   
						<%--    %> --%>
						<%@ include file="/front-end/joinActivity/page2.file" %>
					  
	 					
					</div><!-- container-->
				 </div><!--���h col-xs-9 col-md-10 col-sm-10-->
				</div><!-- ���h�~��row-->
			</div><!--�~�h col-xs-9 col-md-10 col-sm-10-->
		
			<div class="col-xs-1 col-sm-2" id="aaa" >
				<%-- ���~��C --%>
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
			</div><!-- �~�h  col-sm-2-->
	
</body>
</html>