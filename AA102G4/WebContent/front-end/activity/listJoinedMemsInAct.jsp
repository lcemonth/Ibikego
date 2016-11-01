<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.joinactivity.model.*"%>
<%	
   	 List<JoinactivityVO> list;
	 list = (List<JoinactivityVO>) request.getAttribute("list");
	
	 Integer act_no=(Integer)request.getAttribute("act_no");
	 pageContext.setAttribute("act_no",act_no);
	
	if(list==null){
		JoinactivityService jactSvc=new JoinactivityService();
		if(act_no==null){
			act_no=Integer.valueOf(request.getParameter("act_no"));
		}
		list=jactSvc.getCnJMemsByAct(act_no);
	}
	pageContext.setAttribute("list",list);
	
	
	
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />	
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		
		<style type="text/css">
		    body{
				 background-color: #26a8ff;
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
			.col-md-10{
				/*width: 100%;
				background-color: #fdf;*/

			}
			.table tr th{
				text-align:center;
			}
			.empadd{
				text-align: left;
			}
			.empsearch{
				width: 100%
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
		<jsp:useBean id="jactSvc" scope="page" class="com.joinactivity.model.JoinactivityService" />
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
 			<jsp:include page="/front-end/activity/left.jsp"></jsp:include>
 			<div class="container">
			<div class="row">	
			<div class="col-sm-12 col-sm-offset-1">
				<table border='1' bordercolor='#CCCCFF' width='800' class="table table-hover table-bordered table-condensed">
					
					<tr>
					<th width=80>會員編號</th>
					<th width=80>姓名</th>
					<th width=80>連絡電話</th>
					<th>大頭貼</th>
					<th>剔除</th>
					
					</tr>
					<%@ include file="page1a.file" %>
					<%-- <jsp:include page="<%=request.getContextPath()%>/front-end/activity/page1.file" /> --%>
					<c:forEach var="jactVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle'>
						    <c:forEach var="memVO" items="${memSvc.all}">
			                    <c:if test="${jactVO.mem_no==memVO.mem_no}">
				                    <td>${memVO.mem_no}</td>
				                    <td>${memVO.mem_name}</td>
				                    <td>${memVO.mem_phone}</td>
				                    <td><img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memVO.mem_no}" height=80 width=80/></td>
			                    </c:if>
			              	</c:forEach>
			              	
				              	 <td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do">
										     <input type="hidden" name="kick_mem_no" value="${jactVO.mem_no}">
										     <input type="hidden" name="act_no" value="<%=act_no%>">
										     <input type="hidden" name="action"	value="kickOneMem">
										     <input type="submit" value="剔除" class="btn btn-warning">
										</FORM>
								</td>
						</tr>	
					</c:forEach>

				</table>
				<%-- <jsp:include page="<%=request.getContextPath()%>/front-end/activity/page2.file" /> --%>
				<%@ include file="page2a.file" %>
				<c:if test="${not empty Msgs}">
					<font color='red'>
					<ul>
						<c:forEach var="message" items="${Msgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
					</font>
				</c:if>
			</div></div></div></div>
		
	</body>
</html>