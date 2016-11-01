<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%@ page import="com.forum.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="traSvc" scope="page" class="com.travel.model.TravelService" />
<jsp:useBean id="foruSvc" scope="page" class="com.forum.model.ForumService" />
<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />
<jsp:useBean id="blogSvc" scope="page" class="com.blog.model.BlogService" />
<jsp:useBean id="strokeSvc" scope="page" class="com.stroke.model.StrokeService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />

<%
ForumVO forumVO = (ForumVO) request.getAttribute("forumVO"); 
ReportcollectVO rcVO = (ReportcollectVO) request.getAttribute("rcVO"); 
%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="/resources/demos/style.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
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
		
			}
			.txtbox1 {
			width: 200px;
			padding: 10px;
			
			}
			.ellipsis {
			overflow:hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
			}
		</style>
	<script type="text/javascript">
		$(document).ready(
				 function test(){
				   //測試ajax區塊
			       $("#repcnt").bind("click",function(){
			    	   $("#dialog").dialog();
			       });
			       $("#forumcnt").bind("click",function(){
			    	   $("#dialogcnt").dialog();
			       });
  
		})
	</script>
	</head>
	<body>
		<div id="dialog" title="檢舉內容" style="display:none">
			<p>${rcVO.rep_content}</p>
		</div>
		<div id="dialogcnt" title="討論內容" style="display:none">
			<p>${forumVO.forum_content}</p>
		</div>	
		<div class="row">
			<jsp:include page="/back-end/top.jsp"></jsp:include>
		</div>
		<div class="col-sm-12 "></div> <!-- 與TOP的距離 -->
			<br>
		<div class="row">
 			<jsp:include page="/back-end/left.jsp"></jsp:include> 
			
			<div class="col-sm-10">
				<div class="row">
					<div class="col-sm-10">
						<div style="background-color:#FFD382;padding:10px;margin-bottom:5px;width:200px;">
							<a href="<%=request.getContextPath()%>/reportcollect/reportcollect.do?action=backIndex">
								<img src="<%=request.getContextPath()%>/res/images/bikelog.png" width="100" height="100" border="0">
								<font color="blue" size="2" >回檢舉處理</font >
							</a>
						</div>		
						<table class="table table-bordered table-hover" border='1' bordercolor='#CCCCFF' width='600'>
							<tr class="success">
								<th>討論區編號</th>
								<th>發文者</th>
								<th>討論主題</th>
								<th>討論內容</th>
								<th>發文時間</th>
							</tr>
							<tr align='center' valign='middle' class="info">
								<td>${forumVO.forum_no}</td>
									<td>
										<c:forEach var="memVO" items="${memSvc.all}">
						                    <c:if test="${forumVO.mem_no==memVO.mem_no}">
							                	<font>${memVO.mem_name}</font>
						                    </c:if>
						                </c:forEach>
					                </td>
									<td>
						                <div class="txtbox">
											<p class="ellipsis"> <font>${forumVO.forum_title}</font ></p>
										</div>
					                </td> 
					                <td id="forumcnt">
						                <div class="txtbox">
											<p class="ellipsis"> <font>${forumVO.forum_content}</font ></p>
										</div>
					                </td>
									<td>${forumVO.forum_cretime}</td>
							</tr>
						</table>
						<br>
						<table class="table table-bordered table-hover">
					<thead>
						<tr class="success" >
							<th>檢舉編號</th>
							<th>檢舉人</th>
							<th>討論區編號</th>
				            <th>討論主題</th> 
				            <th>檢舉內容</th>
							<th>檢舉處理</th>
							<th colspan=2>審核</th>												
						</tr>
					</thead>
					<tbody>
					
					
						<tr align='center' valign='middle' class="info">
								<td>${rcVO.rc_no}</td>
			 				   
			 				    <td>
									<c:forEach var="memVO" items="${memSvc.all}">
					                    <c:if test="${rcVO.mem_no==memVO.mem_no}">
						                	<font>${memVO.mem_name}</font>
					                    </c:if>
					                </c:forEach>
							    </td>  
							
				                <c:if test="${rcVO.forum_no!=0}">
				                
									<td>${rcVO.forum_no}</td>
						                <td>
						                <div class="txtbox">
										<p class="ellipsis"><font>${forumVO.forum_title}</font></p> 
										</div>
						            </td> 
						               
								</c:if>
								<td  id="repcnt">
										<div class="txtbox1">
										<p class="ellipsis"><font>${rcVO.rep_content}</font></p> 
										</div>
								</td>
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
								    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportcollect/reportcollect.do">
								    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
								    <input type="hidden" name="rc_no" value="${rcVO.rc_no}">
								    <input type="hidden" name="action" value="passrep">
								    <input type="submit" value="成立" class="btn btn-primary"> 					    
								  </FORM>
								</td>
								<td>
								    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportcollect/reportcollect.do">
								  	<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
								    <input type="hidden" name="rc_no" value="${rcVO.rc_no}">
								    <input type="hidden" name="action" value="nopassrep">
								    <input type="submit" value="不成立" class="btn btn-primary"> 					    
								  </FORM>
								</td>
								
							</tr>
					</tbody>
				</table>
					
					</div>	
				</div><!-- 內層  col-sm-10-->
			</div><!-- 內層的ROW-->
		</div><!-- 內層 外的ROW-->
	</body>
</html>