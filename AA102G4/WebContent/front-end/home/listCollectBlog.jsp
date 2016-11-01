<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.reportcollect.model.*"%>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
List<BlogVO> searchPoint = (List<BlogVO>)request.getAttribute("searchPoint");

session.setAttribute("location", request.getRequestURI());
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
		<script src="<%=request.getContextPath()%>/res/js/index.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/home.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<style type="text/css">
			body{
				background-color: #87CEEB;
			}
			.col-xs-2{
				position: relative;
				top: 50px;
			}
			.a1{
				background-color: #67cad2; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:176px;/*右邊*/
			}
			.a1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
	
			}
			.b1{
				background-color: #f86e8f; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:96px;/*右邊*/
			}
			.b1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
	
			}
			.c1{
				background-color: #ffc15e; 
				float:right; 
				height:60%; 
				width: 80px;
				transition: height 0.4s;
				border-radius:0px 0px 10px 10px;/*圓角*/
				position:absolute;/*絕對位置*/top:-10px;/*絕對高度*/right:16px;/*右邊*/
			}
			.c1:hover{
				height:70%;
				box-shadow:   5px 5px 15px #888888;/*陰影*/
			}
			.box{
				width: 240px;
				height: 480px;
				background-color: #E6E6E6;
				border-radius:10px 10px 2px 2px;
				cursor:pointer;/*變手*/
				overflow-y:hidden;         /*--設定超出的內容隱藏, IE, FireFox通用--*/
			    text-overflow:ellipsis;  /*--(IE專用)在內容超出時,在後方補上逗號--*/
			    /*white-space:nowrap;*/ /*-- 設定內容強制顯示一行,直到內容結束或<br>--*/
				float:left;
				margin:80px 20px 0px 40px;
			}
			img{
				width: 100%;
				height: 50%;
				border-radius:10px 10px 0px 0px;
			}
			.box:hover{
				box-shadow: 0px 0px 2px 4px #01B468;
			}
			.p1 {
			    margin-top: 15px;/*字體高(外部)*/
			    font-size: 14px;/*字體大小*/
			    letter-spacing: 2px;/*字體寬*/
			    line-height: 21px;/*字體高(內部)*/
			    color: #555;/*字體顏色*/
			    margin: 5px;
			    padding: 10px;
			}
			h3, p{
			    margin: 0;
			    padding: 0;
			    border: 0;
			    font: inherit;   /*h3大小*/
			    font-family: '蘋果儷中黑','微軟正黑體','Helvetica','Arial',sans-serif;
			}
			#pp{
				background-color: #1D7F56;
				width: 60px;
				color:#fff;
				position:relative; top:-25px; left:0%;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="container">
			<jsp:include page="homeTop.jsp"></jsp:include>
			<!-- 下面	 -->
			<div class="row">
				<jsp:include page="homeFunction.jsp"></jsp:include>
				<div class="row">
					<jsp:useBean id="blogSvc" scope="page" class="com.blog.model.BlogService" />
				    <jsp:useBean id="reportcollectSvc" scope="page" class="com.reportcollect.model.ReportcollectService" />
					<c:forEach var="blogVO" items="${blogSvc.all}">
						<c:if test="${blogVO.blog_del!=1}">
							<c:if test="${reportcollectSvc.getOneColBlogBymem_no(memberVO.mem_no,blogVO.blog_no).rc_col_status==0}">
								<a href="<%=request.getContextPath()%>/blog/blog.do?action=getOne_For_UserDisplay&blog_no=${blogVO.blog_no}">
									<div class="box">
										<img src="<%=request.getContextPath()%>/blogImage.do?blog_no=${blogVO.blog_no}"/>
										<h3 id="title">${blogVO.blog_title}</h3>
										<p class="p1">${blogVO.blog_content}</p>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog.do" >
						                    <button type="submit" class="btn btn-primary collect">取消收藏</button>
						                    <input type="hidden" name="action" value="cancelCollectFromHome">
						                    <input type="hidden" name="rc_no" value="${reportcollectSvc.checkBlogCollect(blogVO.getBlog_no(),memberVO.getMem_no()).rc_no}">
						                    <input type="hidden" name="blog_no" value="${blogVO.blog_no}">
						                    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
								    	</FORM>
									</div>
								</a>
							</c:if>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</body>
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
</html>