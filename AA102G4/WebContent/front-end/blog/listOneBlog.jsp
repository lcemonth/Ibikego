<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.blogScore.model.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	BlogVO blogVO = (BlogVO) session.getAttribute("blogVO");
	
	BlogScoreService blogScoreSvc = new BlogScoreService();
	BlogScoreVO total = (BlogScoreVO) blogScoreSvc.getOneBlogScore(blogVO.getBlog_no());
	pageContext.setAttribute("total",total);
	
	session.setAttribute("frontLocation", request.getRequestURI());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/respond.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.cycle.all.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCXAmhDtb0jrnLSGagfxffHdbDlM362nVw"></script>
		<script src="<%=request.getContextPath()%>/res/js/index.js"></script>
		<script src="<%=request.getContextPath()%>/front-end/Login/js/sweetalert.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/blog/css/listOneBlog.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Login/css/sweetalert.css">
		<style type="text/css">
			body{
	    		background-color: #0099CC;
			}
			.pre{
				white-space:pre-wrap; /*讓內容維持原排版*/
			}
		</style>
	</head>
	<body>
		<div>
			<c:if test="${not empty errorMsgs}">
	    		<font color='red'>
					<c:forEach var="message" items="${errorMsgs}">
						<script>swal("${message}", "請修正錯誤", "error")</script>
					</c:forEach>
	  			</font>	
			</c:if>
			<c:if test="${not empty warningMsgs}">
	    		<font color='red'>
					<c:forEach var="warning" items="${warningMsgs}">
						<script>swal("${warning}")</script>
					</c:forEach>
	  			</font>	
			</c:if>
		</div>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="container listone">
			<div class="outside2">
				<div class="content">
					<div id="userImg">
						<img class="photo" src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${blogVO.mem_no}">
					</div>
					<div id="userInfo">
						<div>
							<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
							<c:forEach var="memberVO" items="${memberSvc.all}">
			                    <c:if test="${blogVO.mem_no == memberVO.mem_no}">
			                    	<p>【發布者】:${memberVO.mem_name}</p>
			                    </c:if>
			                </c:forEach>
						</div>
						<p>【最後編輯】:${blogVO.blog_cre}</p>
						<p>【總分】:${total.totalScore}</p>
					</div>
				</div>
				<div>
					<c:if test="${not empty memberVO}">
						<%
							BlogScoreVO scored = (BlogScoreVO) blogScoreSvc.getCheck(blogVO.getBlog_no(),memberVO.getMem_no());
							pageContext.setAttribute("scored",scored);
						%>
				    	<div>
		                    <button id="report" class="btn btn-danger btn-lg">檢舉</button>
		                    <input type="hidden" name="blog_no" value="${blogVO.blog_no}">
		                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
				    	</div>
				    	<c:choose>
		                    <c:when test="${empty scored}">
		                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogScore/blogScore.do" >
				                	<button id="score" type="submit" class="btn btn-success btn-lg">評分</button>
				                    <input type="hidden" name="action" value="userInsert">
				                    <input type="hidden" name="blog_no" value="${blogVO.blog_no}">
				                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			                    </FORM>
		                    </c:when>
			                <c:otherwise>
			                	<button type="submit" class="btn btn-default btn-lg">已評</button>
		                    </c:otherwise>
	                    </c:choose>
		                <%
		                ReportcollectService reportcollectSvc = new ReportcollectService();
		                ReportcollectVO collect = (ReportcollectVO) reportcollectSvc.checkBlogCollect(blogVO.getBlog_no(),memberVO.getMem_no());
		                pageContext.setAttribute("collect",collect);
		                %>
				    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do" >
							<c:choose>
			                    <c:when test="${(blogVO.blog_no==collect.blog_no)&&(collect.mem_no==memberVO.mem_no)&&(collect.rc_col_status==0)}">
				                    <button type="submit" class="btn btn-default btn-lg">取消收藏</button>
				                    <input type="hidden" name="action" value="userCancelCollect">
				                    <input type="hidden" name="rc_no" value="${collect.rc_no}">
				                    <input type="hidden" name="blog_no" value="${blogVO.blog_no}">
				                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			                    </c:when>
				                <c:otherwise>
				                    <button type="submit" class="btn btn-warning btn-lg">收藏</button>
				                    <input type="hidden" name="action" value="userInsertCollect">
				                    <input type="hidden" name="blog_no" value="${blogVO.blog_no}">
				                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			                    </c:otherwise>
		                    </c:choose>
				    	</FORM>
			    	</c:if>
			    </div>
			</div>
			<div class="outside">
				<p id="title1">${blogVO.blog_title}</p>
	        	<img height="100%" width="100%" src="<%=request.getContextPath()%>/blogImage.do?blog_no=${blogVO.blog_no}"/>
                <div class="content">
					<p class="pre">${blogVO.blog_content}</p>
				</div>	<!-- content -->
			</div>	<!-- outside -->
			<div class="msg">
				<jsp:useBean id="messageSvc" scope="page" class="com.message.model.MessageService" />
				<c:forEach var="messageVO" items="${messageSvc.all}">
                	<c:if test="${blogVO.blog_no == messageVO.blog_no}">
                    	<c:forEach var="memberVO" items="${memberSvc.all}">
		                	<c:if test="${messageVO.mem_no == memberVO.mem_no}">
								<table class="table table-hover">
								   	<tbody>
								   		<tr>
								   			<td width="110">
								   				<img class="photo" src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memberVO.mem_no}">
								   			</td>
								   			<td>
									   			<p style="font-size: 25px;font-weight:bold;">${memberVO.mem_name}</p>
									   			<p class="pre">${messageVO.mes_content}</p>
									   			<p style="font-size: 10px;">${messageVO.mes_cre}</p>
								   			</td>
								   		</tr>
								   	</tbody>
								</table>
							</c:if>
						</c:forEach>						                   	
					</c:if>
				</c:forEach>
				<div>
					<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
					<table class="table table-hover">
					   	<tbody>
							<tr>
								<td width="110">
								   	<img class="photo" src="<%=request.getContextPath()%>/member/memberPhoto.do?mem_no=${memberVO.mem_no}">
								</td>
								<td>
									<p style="font-size: 25px;font-weight:bold;">${memberVO.mem_name}</p><br>
									<textarea id="desc" name="mes_content" placeholder="Add a content..." value="${messageVO.mes_content}">${messageVO.mes_content}</textarea><br>
									<button id="msg" class="btn btn-primary">留言</button>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="blog_no" value="${blogVO.blog_no}"/>
					<input type="hidden" name="mem_no" value="${memberVO.mem_no}"/>
					<input type="hidden" name="mes_cre" value="<%= date_SQL%>">
					<input type="hidden" name="action" value="userInsert">
				</div>
			</div>	<!-- msg -->
		</div>	<!-- container listone -->
	</body>
	<script type="text/javascript">
    	/*留言*/
		$("#msg").click(function(){
			var mesContent = $("#desc").val();
			<c:if test="${memberVO==null}">
				swal({
					type: "error",
					title: "請先登入或是註冊!",
					confirmButtonText: "確定",
					closeOnConfirm: false
				});
			</c:if>
			<c:if test="${memberVO!=null}">
				if(mesContent.length>30){
					swal({
						type: "error",
						title: "留言不可超過30字!",
						confirmButtonText: "確定",
						closeOnConfirm: false
					});
				}else if(mesContent.length==0){
					swal({
						type: "error",
						title: "請輸入留言!",
						confirmButtonText: "確定",
						closeOnConfirm: false
					});
				}else{
					$.ajax({
						type: "POST",
						url: "<%=request.getContextPath()%>/message/message.do",
						data:{
							action: "userInsert",
							blog_no: blog_no(this),
							mem_no: mem_no(this),
							mes_cre: mes_cre(this),
							mes_content: mes_content(this)
						},
						success: function(data){
							location.reload();
						},
						error: function(){
							alert("error");
						}
					});
				}
			</c:if>
		});
		function blog_no(e){
			return $("input[name='blog_no']").val();
	    }
		function mem_no(e){
			return $("input[name='mem_no']").val();
	    }
		function mes_cre(e){
			return $("input[name='mes_cre']").val();
	    }
		function mes_content(e){
			return $("#desc").val();
	    }
		/*留言*/
		
		/*檢舉*/
		$("#report").click(function(){
			swal({
				title: "您確定要檢舉此日誌?",
				text: "檢舉內容",
				type: "input",
				showCancelButton: true,
				confirmButtonClass: "btn-danger",
				confirmButtonText: "檢舉此日誌",
				cancelButtonText: "不了，謝謝!",
				closeOnConfirm: false,
				inputPlaceholder: "請輸入檢舉內容"
			},
			function(inputValue){
				if(inputValue === false){
					return false;
				}
				if(inputValue === ""){     
					swal.showInputError("請填寫內容");     
					return false;
				}
				$.ajax({
					type: "POST",
					dataType: "text",
					url: "<%=request.getContextPath()%>/blog/blog.do",
					data:{
						action: "userInsertReport",
						blog_no: blog_no(this),
						mem_no: mem_no(this),
						rep_content: inputValue
					},
					success: function(data){
						if("userInsertReport"){
							swal({title:"", text: "\n你的檢舉已收到 ，會盡速處理",timer: 1000,type: "success",showConfirmButton: false});
							return;
						}
					},
					error: function(){
						alert("error");
					}
				});
			});
		});
		function blog_no(e){
			return $("input[name='blog_no']").val();
	    }
		function mem_no(e){
			return $("input[name='mem_no']").val();
	    }
		/*檢舉*/
	</script>
</html>