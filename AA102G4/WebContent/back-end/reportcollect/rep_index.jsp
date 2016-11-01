<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reportcollect.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="traSvc" scope="page" class="com.travel.model.TravelService" />
<jsp:useBean id="foruSvc" scope="page" class="com.forum.model.ForumService" />
<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />
<jsp:useBean id="blogSvc" scope="page" class="com.blog.model.BlogService" />
<jsp:useBean id="strokeSvc" scope="page" class="com.stroke.model.StrokeService" />
<%  
	List<ReportcollectVO> list = (List<ReportcollectVO>) request.getAttribute("list");
	if(list==null){
		ReportcollectService rcSvc = new ReportcollectService();
	     list = rcSvc.getAllrep();
	    pageContext.setAttribute("list",list);
	}
%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>檢舉管理</title>
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
			.col-md-12{
				text-align:center;
			}
			.col-md-2{
				height: 100px;
				text-align:center;
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
			.txtbox {
			width: 200px;
			padding: 10px;
			border: lightblue 3px solid;
			}
			.txtbox1 {
			width: 200px;
			padding: 10px;
			border: #FF79BC 3px solid;
			}
			.ellipsis {
			overflow:hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
			}
		</style>
	</head>
	<body>
		<c:if test= "${test==1}" >
			<% for (int i=0;i<1;i++){%>
				<script>
				   
					swal('下架流程完畢')
				</script>
			<%}%>
		</c:if>
		<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		
		<div class="row">
		  <div class="col-xs-9 col-md-10 col-sm-10">
		   <div class="row">
 			<jsp:include page="/back-end/left.jsp"></jsp:include> 
			<div class="col-xs-9 col-md-10 col-sm-10">
			   <div class="container">
				<table class="table table-bordered table-hover">
					<thead>
						<tr class="success">
							<th>檢舉編號</th>
							<th>檢舉人</th>
							<th>編號</th>
				            <th>名稱</th> 
							<th>檢舉處理</th>
							<th>檢舉內容</th>
							<th colspan=3>處理</th>
							
							
						</tr>
					</thead>
					<tbody>
					<%@ include file="page1.file" %>  
					<c:forEach var="rcVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle' class="info" ${(rcVO.rc_no==param.rc_no) ? 'bgcolor=#CCCCFF':''}>
								<td>${rcVO.rc_no}</td>
<%-- 								<td><a href="<%=request.getContextPath()%>/activity/activity.do?act_no=${actVO.act_no}&action=linkOneFromAct_index">${actVO.act_no}</a></td> --%>
			 				   
			 				    <td>
									<c:forEach var="memVO" items="${memSvc.all}">
					                    <c:if test="${rcVO.mem_no==memVO.mem_no}">
						                 	 編號${rcVO.mem_no} <font >${memVO.mem_name}</font>
					                    </c:if>
					                </c:forEach>
							    </td>  
							    <c:if test="${rcVO.tra_no!=0}">
				                	<td>旅遊編號 ${rcVO.tra_no}</td> 
				                	<c:forEach var="traVO" items="${traSvc.all}">
 										<c:if test="${rcVO.tra_no==traVO.tra_no}">
						                <td>
						                <div >
										<p class="ellipsis"><font >${traVO.tra_name}</font></p>
										</div>
										</td> 
						                </c:if> 
					                </c:forEach>
				                </c:if>
				                <c:if test="${rcVO.act_no!=0}">
				                
									<td>揪團編號 ${rcVO.act_no}</td>
									<c:forEach var="actVO" items="${actSvc.all}">
 										<c:if test="${rcVO.act_no==actVO.act_no}">
						                <td>
						                <div >
										<p class="ellipsis"><font >${actVO.act_name}</font></p> 
										</div>
						                </td> 
						                </c:if> 
					                </c:forEach>
								</c:if>
								
								<c:if test="${rcVO.forum_no!=0}">
									<td>討論編號 ${rcVO.forum_no}</td>
									<c:forEach var="foruVO" items="${foruSvc.all}">
 										<c:if test="${rcVO.forum_no==foruVO.forum_no}">
						                <td>
						                <div>
										<p class="ellipsis"> <font  >${foruVO.forum_title}</font ></p>
										</div>
						                </td> 
						                </c:if> 
					                </c:forEach>
								</c:if>
								
								<c:if test="${rcVO.blog_no!=0}">
									<td>日誌編號 ${rcVO.blog_no}</td>
									<c:forEach var="blogVO" items="${blogSvc.all}">
 										<c:if test="${rcVO.blog_no==blogVO.blog_no}">
						                <td>
						                <div >
										<p class="ellipsis"> <font  >${blogVO.blog_title}</font ></p>
										</div>
						                </td> 
						                </c:if> 
					                </c:forEach>
								</c:if>
								
								<c:if test="${rcVO.stroke_no!=0}">
									<td>行程編號 ${rcVO.stroke_no}</td>
									<c:forEach var="strokeVO" items="${strokeSvc.all}">
 										<c:if test="${rcVO.stroke_no==strokeVO.stroke_no}">
						                <td>
						                <div >
										<p class="ellipsis"> <font >${strokeVO.stroke_name}</font ></p>
										</div>
						                </td> 
						                </c:if> 
					                </c:forEach>
								</c:if>
								<c:if test="${rcVO.rc_rep_handle==0}">
									<td>${reportCollectMAP["0"]}</td>
								</c:if>
								<c:if test="${rcVO.rc_rep_handle==1}">
									<td>${reportCollectMAP["1"]}</td>
								</c:if>
								<c:if test="${rcVO.rc_rep_handle==2}">
									<td>${reportCollectMAP["2"]}</td>
								</c:if>
								<c:if test="${rcVO.rc_rep_handle==3}">
									<td>${reportCollectMAP["3"]}</td>
								</c:if>
								<td>
						                <div >
										<p class="ellipsis"> <font  >${rcVO.rep_content}</font ></p>
										</div>
						        </td> 
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportcollect/reportcollect.do">
								 	 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								     <input type="hidden" name="rc_no" value="${rcVO.rc_no}">
								     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
								     <input type="hidden" name="action"	value="getOne_For_Check">
								 
								     <c:if test="${rcVO.rc_rep_handle==2 or rcVO.rc_rep_handle==1}">
								     	 <input type="button" value="已審核" class="btn btn-primary" disabled>
								     </c:if>
								     <c:if test="${rcVO.rc_rep_handle==0}">
								     	 <input type="submit" value="審核" class="btn btn-primary">
								     </c:if>
								       
								  </FORM>
								</td>
								
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportcollect/reportcollect.do">
								  	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								    <input type="hidden" name="rc_no" value="${rcVO.rc_no}">
								    <input type="hidden" name="whichPage"	value="<%=whichPage%>">
								    <input type="hidden" name="action" value="getOne_For_Del">
								    <input type="submit" value="刪除" class="btn btn-primary" ${(rcVO.rc_rep_handle==1 or rcVO.rc_rep_handle==2) ?"":"disabled"} > 					    
								  </FORM>
								</td>
								
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportcollect/reportcollect.do">
								  	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								    <input type="hidden" name="rc_no" value="${rcVO.rc_no}">
								    <input type="hidden" name="whichPage"	value="<%=whichPage%>">
								    <input type="hidden" name="action" value="getOne_For_Shelves">
								    <input type="submit" value="下架" class="btn btn-primary" ${rcVO.rc_rep_handle==1 ?"":"disabled"}> 					    
								  </FORM>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<%@ include file="page2.file" %>
				</div>	
			  </div>		 
			 </div>	
			</div><!-- 內層  col-sm-10-->
		</div><!-- 內層 外的ROW-->
	</body>
</html>