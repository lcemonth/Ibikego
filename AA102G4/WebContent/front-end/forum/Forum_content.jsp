<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.forumresponse.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%@ page import="java.util.*"%>

<%
	Integer forum_no = new Integer(request.getParameter("forum_no"));

	ForumService forumSvc = new ForumService();
	ForumVO forumVO = forumSvc.getOneForum(forum_no);
	request.setAttribute("forumVO", forumVO);

	ForumresService forumresSvc = new ForumresService();
	List<ForumresVO> forumreslist = forumresSvc.getOneForumres(forum_no);
	request.setAttribute("forumreslist", forumreslist);

	ReportcollectService rcSvc = new ReportcollectService();
	ReportcollectVO rcVO = rcSvc.getOneForum_state(forum_no);
	request.setAttribute("rcVO", rcVO);

	ReportcollectVO rccVO = rcSvc.getOneForum_handle(forum_no);
	request.setAttribute("rccVO", rccVO);
%>

<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<%-- <jsp:useBean id="repSvc" scope="page" class="com.reportcollect.model.ReportcollectService" />	 --%>
<html>
<head>
<title>${forumVO.forum_title}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
.col-md-12 {
	/*width: 100%;
	background-color: #fdf;*/
	
}

body {
	padding-top: 50px;
	height: 100px;
	color: #000;
	background-color: #FFFFFF;
}

.green {
	width: 100%;
	height: 100px;
	background-color: #FFFFFF;
}
/* img { width: 100%; } */
.thumbnail {
	background-color: #fff;
	width: 280px;
	height: 450px;
}

.i {
	background-color: #DDDDDD;
}

.box1 {
	width: 280px;
	height: 480px;
	background-color: #FFF;
}

.col-sm-8 {
	width: 600px;
	height: 100px;
	background-color: #DDDDDD;
}

.col-md-1 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 140px;
	text-align: center;
}

.col-md-2 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 120px;
	text-align: center;
}

.col-md-3 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 40px;
	text-align: center;
}

.col-md-5 {
	/*width: 100%;
				background-color: #fcf;*/
	height: 140px;
	text-align: center;
}

.col-md-10 {
	/*width: 100%;
	background-color: #fdf;*/
	
}

.table tr th {
	text-align: center;
}

.empadd {
	text-align: left;
}

.empsearch {
	width: 100% text-align:center;
}
</style>
<!-- 引用js及css -->
<script src="<%=request.getContextPath()%>/front-end/forum/js/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/forum/js/calendarcode.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/forum/css/calendar.css">
<script src="<%=request.getContextPath()%>/res/js/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/js/ckeditor/contents.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
</head>
<body bgcolor='white'>

	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>

	<!-- 		<div class="row"> -->
	<%-- 			<jsp:include page="/front-end/left.jsp"></jsp:include> --%>


	<div class="col-md-2"></div>
	<div class="col-md-8">

	<main>
		<article>
			<table>
			<tr>
				<td>
					<H1>
						<B>${forumVO.forum_title}</B>
					</H1>
				</td>
		
				<td style="position:relative; top:20px; right:-20px;">
							<div>
								<c:if test="${memberVO.mem_no != null}">
									<%-- 							如果想在某條件式成立時顯示某些內容，不成立就顯示另一內容，則可以使用<c:choose>、<c:when>及<c:otherwise>標籤。 --%>
									<c:choose>
										<c:when test="${(rcVO.forum_no == forumVO.forum_no) && (rcVO.mem_no == memberVO.mem_no) && rcVO.rc_col_status == 0}">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?update_status">
												<input type="submit" class="btn btn-sm btn-primary" value="已收藏">
												<input type="hidden" name="rc_no" value="${rcVO.rc_no}">
												<input type="hidden" name="mem_no" value="${rcVO.mem_no}">
												<input type="hidden" name="forum_no" value="${rcVO.forum_no}">
												<input type="hidden" name="action" value="update_status">
											</FORM>
										</c:when>

										<c:when test="${(rcVO.forum_no == forumVO.forum_no) && (rcVO.mem_no == memberVO.mem_no) && rcVO.rc_col_status == 1}">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?update_status_A">
												<input type="submit" class="btn btn-sm btn-primary" value="收藏">
												<input type="hidden" name="forum_no" value="${forumVO.forum_no}">
												<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
												<input type="hidden" name="rc_no" value="${rcVO.rc_no}">
												<input type="hidden" name="action" value="update_status_A">
											</FORM>
										</c:when>

										<c:otherwise>
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?insert_statuss">
											<input type="submit" class="btn btn-sm btn-primary" value="收藏">
											<input type="hidden" name="forum_no" value="${forumVO.forum_no}">
											<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
											<input type="hidden" name="rc_no" value="${rcVO.rc_no}">
											<input type="hidden" name="action" value="insert_status">
										</FORM>
									</c:otherwise>
								</c:choose>
							</c:if>
						</div>
				</td>
				<td style="position:relative; top:20px; right:-20px;">
					<div>
						<c:if test="${memberVO.mem_no != null}">
							<c:choose>
								<c:when test="${(rccVO.forum_no == forumVO.forum_no) && (rccVO.mem_no == memberVO.mem_no) && (rccVO.rep_rel == 0)}">
									<FORM>
										<input type="submit" class="btn btn-sm btn-danger" value="已檢舉" disabled="true">
									</FORM>
								</c:when>
								<c:otherwise>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum.do?action=add_handle">
													<%-- 									<input type="submit" id="opener" class="btn btn-sm btn-danger" value="檢舉" onclick="location.href='<%=request.getContextPath()%>/front-end/forum/Forumhandle_add.jsp'"> --%>
										<input type="submit" class="btn btn-sm btn-danger" value="檢舉">
										<input type="hidden" name="forum_no" value="${forumVO.forum_no}">
										<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
										<input type="hidden" name="rep_content" value=" ">
										<input type="hidden" name="action" value="add_handle">
									</FORM>
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>
				</td>
			</tr>
			</table>
			</article>
			<article>
				<div style="background:#B0C4DE; text-align: left; height: 200;" class="col-md-12">
				<table>
					<tr>
						<td>
							<div>
							<img height="200" width="180" src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${forumVO.mem_no}" alt="...">
							</div>
						</td>
						<td style="position:relative; top:-60px; right:-20px;">
							<c:forEach var="memberVO" items="${memSvc.all}">
									<c:if test="${forumVO.mem_no==memberVO.mem_no}">
										<H3>${memberVO.mem_name}</H3>
									</c:if>
							</c:forEach>
							<div>
								<H5>${forumVO.forum_cretime}</H5>
							</div>				
						</td>
						<td style="position:relative; top:-60px; right:-20px;">
							<div>
								<c:if test="${forumVO.mem_no == sessionScope.memberVO.mem_no}">
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/forum.do?action=getOne_For_Update_front">
										<input type="submit" class="btn btn-sm btn-info" value="修改">
										<input type="hidden" name="forum_no"
											value="${forumVO.forum_no}"> <input type="hidden"
											name="action" value="getOne_For_Update_front">
									</FORM>
								</c:if>
							</div>
						</td>
						<td style="position:relative; top:-60px; right:-20px;">
							<div>
								<c:if test="${forumVO.mem_no == sessionScope.memberVO.mem_no}">
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/forum.do?action=delete_front">
										<input type="submit" class="btn btn-sm btn-warning" value="刪除">
										<input type="hidden" name=forum_no value="${forumVO.forum_no}">
										<input type="hidden" name=forumres_no
											value="${forumresVO.forumres_no}"> <input
											type="hidden" name="action" value="delete_front">
									</FORM>
								</c:if>
							</div>
						</td>
					</tr>
			</table>
			</div>
		</article>

		<div style="background: #FFFFFF; height: 500" class="col-md-12  panel-info">
			<a href="<%=request.getContextPath()%>/forumres.do?action=add_front&forum_no=${forumVO.forum_no}">
				<c:if test="${memberVO.mem_no != null}">
					<button type="button" class="btn btn-sm btn-success">回覆</button>
				</c:if>
			</a>
			<H4>${forumVO.forum_content}</H4>
		</div>


	<article>
		<%-- 			${forumreslist.get(0).forumres_cretime} --%>
		<c:forEach var="forumresVO" items="${forumreslist}">
						<div style="background:#B0C4DE; height: 120;" class="col-md-12">
						<table>
							<tr>
								<td>
									<div>
									<c:forEach var="memberVO" items="${memSvc.all}">
											<c:if test="${forumresVO.mem_no==memberVO.mem_no}">
												<img height="120" width="180" src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${forumresVO.mem_no}" alt="">
											</c:if>
									</c:forEach>
									</div>
								</td>
								<td style="position:relative; top:-30px; right:-20px;">
									<di >
										<c:forEach var="memberVO" items="${memSvc.all}">
											<c:if test="${forumresVO.mem_no==memberVO.mem_no}">
												<H3>${memberVO.mem_name}</H3>
											</c:if>
										</c:forEach>
										${forumresVO.forumres_cretime}
									</div>
								</td>
								<td style="position:relative; top:-30px; right:-20px;">
									<div>
										<c:if test="${forumresVO.mem_no == sessionScope.memberVO.mem_no}">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/forumres.do?action=getOne_For_Update_front">
												<input type="submit" class="btn btn-sm btn-info" value="修改">
												<input type="hidden" name="forumres_no"
													value="${forumresVO.forumres_no}"> <input
													type="hidden" name="action" value="getOne_For_Update_front">
											</FORM>
										</c:if>
									</div>
								</td>
								<td style="position:relative; top:-30px; right:-20px;">
										<div >
											<c:if test="${forumresVO.mem_no == sessionScope.memberVO.mem_no}">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/forumres.do?action=delete_front">
													<input type="submit" class="btn btn-sm btn-warning" value="刪除">
													<input type="hidden" name=forum_no value="${forumVO.forum_no}">
													<input type="hidden" name=forumres_no
														value="${forumresVO.forumres_no}"> <input
														type="hidden" name="action" value="delete_front">
												</FORM>
											</c:if>
										</div>
								</td>
									
							</tr>
						</table>
					</div>
		
					<div style="background: #FFFFFF; height: 500;" class="col-md-12 panel-info">
							<H4>${forumresVO.forumres_con}</H4>
					</div>
				</c:forEach>
			</article>
		</main>
	</div>

	<div class="col-md-2"></div>

	<script>
		//履歷上傳-預覽用
		function preview(file) {

			if (file.files && file.files[0]) {
				var reader = new FileReader();
				reader.onload = function(evt) {
					$("#clob_picture_holder")
							.html(
									'<img width="100px" height="60" border="1" src="' + evt.target.result + '" >');
					data = evt.target.result;
				}
				reader.readAsDataURL(file.files[0]);
			}
		}
		$(".various").click(
				function() {
					var ori = CKEDITOR.instances.content.getData();
					CKEDITOR.instances.content.setData(ori
							+ "<img width='30%'src='" + data + "'>");
				});
	</script>
</body>
</html>
