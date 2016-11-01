<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.relationship.model.*"%>
<%	
   	 List<RelationshipVO> list;
	 list = (List<RelationshipVO>) request.getAttribute("list");
	 Integer mem_no;
	 Integer act_no;
	if(list==null){
		RelationshipService rsSvc=new RelationshipService();
		mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
		list=rsSvc.getMFollowRms(mem_no);
		pageContext.setAttribute("list",list);
	}else{
		mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
		pageContext.setAttribute("list",list);
	}
	if(request.getAttribute("act_no")!=null){	
	    act_no=(Integer)request.getAttribute("act_no");
	}
	else{
		act_no=(Integer.valueOf(request.getParameter("act_no")));	
	}
	pageContext.setAttribute("act_no",act_no);
	
	
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
					<th>邀請</th>
					<th>取消</th>
					</tr>
					<%@ include file="page1a.file" %>
					<c:forEach var="rsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle'>
					    <c:forEach var="memVO" items="${memSvc.all}">
		                    <c:if test="${rsVO.rel_mem_no==memVO.mem_no}">
			                    <td>${memVO.mem_no}</td>
			                    <td>${memVO.mem_name}</td>
			                    <td>${memVO.mem_phone}</td>
			                    <td><img src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memVO.mem_no}" height=80 width=80/></td>
		                    </c:if>
		              	</c:forEach>
		              	
			              	 <td>
									     <input type="hidden" name="r_mem_no" value="${rsVO.rel_mem_no}">
									     <input type="hidden" name="m_mem_no" value="<%=mem_no%>">
									     <input type="hidden" name="rel_invite" value="1">
									     <input type="hidden" name="act_no" value="<%=act_no%>">
									     <input type="hidden" name="action"	value="inviteOneMem">
									     <input type="button" value="邀請" onclick="sendMessage(${rsVO.rel_mem_no},<%=act_no%>,1);"  ${jactSvc.getIsINActByMem_no(act_no,rsVO.rel_mem_no)==null or jactSvc.getIsINActByMem_no(act_no,rsVO.rel_mem_no).getJoinact_is_join()==3 ?'class="btn btn-primary" ':'class="btn btn-default" disabled'}>

							</td>

			              	 <td>
									     <input type="hidden" name="r_mem_no" value="${rsVO.rel_mem_no}">
									     <input type="hidden" name="m_mem_no" value="<%=mem_no%>">
									     <input type="hidden" name="rel_invite" value="0">
									     <input type="hidden" name="act_no" value="<%=act_no%>">
									     <input type="hidden" name="action"	value="inviteOneMem">
									     <input type="button" value="取消" onclick="sendMessage(${rsVO.rel_mem_no},<%=act_no%>,0);"  ${jactSvc.getIsINActByMem_no(act_no,rsVO.rel_mem_no).getJoinact_is_join()==0 ?'class="btn btn-primary" ':'class="btn btn-default" disabled'}>
							</td>
					</tr>	
					</c:forEach>

				</table>
				<%@ include file="page2a.file" %>
			</div></div></div>
		</div>

	</body>
</html>