<%@ page language="java" contentType="text/html; charset=BIG5"  pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.blogScore.model.*"%>
<%@ page import="com.blog.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js'></script>
<style type="text/css">

/* 		background-color:#01FF93; */
/* .green{width: 100%; height: 1300px; background-color: #01FF93; */
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
	background-color: #1D7F56;
}

.box1 {

	background-color: #FFF;
	text-align: center;
	line-height: 200px;
	
	
}

.col-sm-8 {
	width: 600px;
	height: 100px;
	background-color: #017F4A;
}

div#box2 {
	width: 200;
	height: 200;
	/*     background:#BBFFEE; */
	line-height: 50px;
	text-align: center;
}
div#box3 {
	font-size:16px;
	/*     background:#BBFFEE; */
	display:inline-block;
	text-align: center;
	word-break: break-all;
	
}
.borderless td, .borderless th {
    border: none;
}
.table tr th {
	text-align: center;
	
}
.p1 {
			    margin-top: 15px;/*字體高(外部)*/
			    font-size: 14px;/*字體大小*/
			    letter-spacing: 2px;/*字體寬*/
			    line-height: 21px;/*字體高(內部)*/
			    color: #555;/*字體顏色*/
			    
}
</style>
<script type="text/javascript">
			$(function(){
			    var len = 85; // 超過85個字以"..."取代
			    $(".p1").each(function(i){
			        if($(this).text().length>len){
			            $(this).attr("title",$(this).text());
			            var text=$(this).text().substring(0,len-1)+"...";
			            $(this).text(text);
			        }
			    });
			});
</script>
</head>
<body bgcolor='white'>

	<div class="row">
		<jsp:include page="/front-end/top.jsp"></jsp:include>
	</div>
<%
// BlogScoreService blogscoreSvc = new BlogScoreService();
// List<BlogScoreVO> listblogscore = blogscoreSvc.getAll();
// pageContext.setAttribute("listblogscore",listblogscore);


		ForumService forumSvc = new ForumService();
		List<ForumVO> list = forumSvc.getAll_new();
		pageContext.setAttribute("list", list);
		
		List<BlogVO> Bloglist = forumSvc.getAll_newblog();
		pageContext.setAttribute("Bloglist", Bloglist);
		
		List<BlogScoreVO> bslist = forumSvc.getAll_bsnew();
		pageContext.setAttribute("bslist", bslist);
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<jsp:useBean id="blogSvc" scope="page" class="com.blog.model.BlogService" />

<div class="content">
	<div class="col-md-4" style="height:1024px; background-color:#CCFFCC;">
	<center><h3><font face="微軟正黑體"  color="blue" >最熱門單車日誌</font></h3></center>
		<table class="table table-hover table-striped"  style="background-color:#FFFFFF;">

			<c:forEach var="blogscoreVO" items="${bslist}">

				<tr align='center' valign='middle'>

					<td style="background-color:#CCFFCC; border: none;" rowspan="2"><c:forEach var="blogVO" items="${blogSvc.all}">
							<div id="box2">

								<c:if test="${blogscoreVO.blog_no==blogVO.blog_no}">
									<a href="<%=request.getContextPath()%>/blog/blog.do?action=getOne_For_UserDisplay&blog_no=${blogVO.blog_no}">
									<img src="<%=request.getContextPath()%>/blogImage.do?blog_no=${blogVO.blog_no}"
										width="280" height="280" /></a>

								</c:if>
							</div>
						</c:forEach></td>
					<td><c:forEach var="blogVO" items="${blogSvc.all}">
							<c:if test="${blogscoreVO.blog_no==blogVO.blog_no}">
								<div id="box1">
									<!-- 								//單車日誌方法/ -->
									<a href="<%=request.getContextPath()%>/blog/blog.do?action=getOne_For_UserDisplay&blog_no=${blogVO.blog_no}">${blogVO.blog_title}</a>
								</div>
							</c:if>
						</c:forEach></td>
				</tr>
				<tr>
					<td><c:forEach var="blogVO" items="${blogSvc.all}">
							<c:if test="${blogscoreVO.blog_no==blogVO.blog_no}">
								<div id="box1">
									<a href="<%=request.getContextPath()%>/blog/blog.do?action=getOne_For_UserDisplay&blog_no=${blogVO.blog_no}">
									<p class="p1">${blogVO.blog_content}</p>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
	
		</table>
	</div>
</div>
<div class="content" style="padding:0 0 0 0;">
	<div class="col-md-4" style="height:1024px;background-color:#99CCCC;">
	<center><h3><font face="微軟正黑體"  color="#5555FF">最新單車日誌</font></h3></center>
		<table class="table table-hover table-striped" style=" background-color:#FFFFFF;">

			<c:forEach var="blogVO" items="${Bloglist}">
				<tr align='center' valign='middle'>
					<td style="background-color:#99CCCC; border: none;"rowspan="2">
						<div id="box2">
							<a href="<%=request.getContextPath()%>/blog/blog.do?action=getOne_For_UserDisplay&blog_no=${blogVO.blog_no}">
							<img src="<%=request.getContextPath()%>/blogImage.do?blog_no=${blogVO.blog_no}"
								width="280" height="280" />
								</a>
						</div>
					</td>
					<td>
						<div id="box1">
							<!-- 								//單車日誌方法/ -->
							<a href="<%=request.getContextPath()%>/blog/blog.do?action=getOne_For_UserDisplay&blog_no=${blogVO.blog_no}">${blogVO.blog_title}</a>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="box1">
							<a href="<%=request.getContextPath()%>/blog/blog.do?action=getOne_For_UserDisplay&blog_no=${blogVO.blog_no}">
							<p class="p1">${blogVO.blog_content}</p>
							</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="content">
	<div class="col-md-4" style="height:1024px;background-color:#FFFFCC;">


	<center><h3><font face="微軟正黑體" color="green">討論區最新文章</font></h3></center>
	<table class="table table-hover table-striped" style="background-color:#FFFFFF;">
		<tr>
			<th>標題</th>
			<th>內文</th>
		</tr>
		<c:forEach var="forumVO" items="${list}">
			<tr align='center' valign='middle'>
					<td  style="height: 169.5px;">
						<div  id="box3">
							<p><a href="<%=request.getContextPath()%>/forum.do?action=getOne_For_Select_front&forum_no=${forumVO.forum_no}">${forumVO.forum_title}</a></p>
						</div>
					</td>
					<td>
						<div id="box3">
							<a href="<%=request.getContextPath()%>/forum.do?action=getOne_For_Select_front&forum_no=${forumVO.forum_no}">
								<p class="p1">${forumVO.forum_content}</p>
							</a>
						</div>
					</td>
				</tr>
		</c:forEach>
	</table>
	</div>
</div>
</body>
</html>