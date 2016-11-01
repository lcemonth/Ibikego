<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.stroke.model.*"%>
<%@ page import="com.travel.model.*"%>
<% 
	HttpSession sessionMemberVO = request.getSession();
	MemberVO memberVO = (MemberVO) sessionMemberVO.getAttribute("memberVO");	//所有天數的行程
	List<StrokeVO> strokeList =new StrokeService().getMemberStrokeAll(memberVO.getMem_no());
// 	strokeSve
	pageContext.setAttribute("strokeList",strokeList);
	
%>
<jsp:useBean id="strokeDetailsSvc" scope="page" class="com.strokedetails.model.StrokeDetailsService"/>
<jsp:useBean id="travelSvc" scope="page" class="com.travel.model.TravelService"/>
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
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css">
		<style type="text/css">
		/*這是空白*/
		body{
			background-color: #ededed;
		}
		.hd{
			height: 200px;
		}
		/*盒子連在一起*/
		.panel-group .panel{
		    border:0px none;
		    border-radius: 0px;
		    box-shadow: none;
		    border-bottom: 1px solid #676767;
		}
		/*標題的div*/
		.panel-heading{
		    padding: 0;
		}
		
		/*標題外的div*/
		.panel-title > a{
		    display: block;
		    padding: 11px 35px;
		    background: #fff;
		    font-weight: 600;
		    text-decoration:none;

		}
		/*變紅色的div*/
		.box{
			width: 60px; 
			height: 60px;
			border-radius:10px 10px 10px 10px;/*圓角*/
			cursor:pointer;/*變手*/
			box-shadow: 0px 0px 2px 2px #99CCFF;
			font-size: 24px;
			text-align:center;/*置中*/
			line-height:60px;
			display: inline-block;
			margin:0px 30px 0px 0px;/*外邊距離*/

		}
		/*刪除button*/
/*  		.dele{  */
/*  			z-index:1; */
/*  			position:relative;  */
/*  			top:0px;   */
/*  			left:80%;  */
/*  		}  */
		</style>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="container">
					<div class="row">
						<div class="col-sm-12" style="background-color: #ffffff;height: 150px;">
							這裡有需要方東西嗎?
						</div>
						<div class="col-sm-12">
							<br>
							<div class="panel-group"  role="tablist" aria-multiselectable="true">
								<!--這可放迴圈開始-->
<!-- 								<div class="panel panel-default"> -->
<!-- 									<div class="panel-heading" role="tab"> -->
<!-- 										<div class="panel-title"> -->
<!-- 											<a  role="button" data-toggle="collapse"  href="#a1"> -->
<!-- 												<div class="box">8/30</div> -->
<!-- 												這裡是標題<input type="submit" value="刪除" class="btn btn-danger dele" data-toggle="modal" data-target="#additemdetails"> -->
<!-- 											</a> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div id="a1" class="panel-collapse collapse"  > -->
<!-- 										<div class="panel-body"> -->
<!-- 											<ul> -->
<!-- 												<li> -->
<!-- 													第一天: -->
<!-- 												</li> -->
<!-- 												<li> -->
<!-- 													第二天: -->
<!-- 												</li> -->
<!-- 											</ul> -->
<!-- 											<input type="submit" value="詳細資料" class="btn "> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<!--這可放迴圈結束-->
								<!--這可放迴圈開始-->
								<c:forEach var="strokeVO" items="${strokeList}" >
									<div class="panel panel-default">
										<div class="panel-heading" role="tab">
											<div class="panel-title">
												<a  role="button" data-toggle="collapse"  href="#${strokeVO.stroke_no}">
													<table border="0" width="100%">
														<tr>
															<td width="5%">
																<div class="box">
																	<fmt:formatDate value="${strokeVO.buildDate}" pattern="MM/dd"  />
																</div>
															</td>
															<td>
																${strokeVO.stroke_name}
															</td>
															<td width="5%">
<%-- 																<input type="hidden" name="stroke_no" id="stroke_no" value="${strokeVO.stroke_no}" /> --%>
<%-- 																<input type="hidden" name="mem_no" id="mem_no" value="${memberVO.mem_no}" /> --%>
																<input type="button" name="delete" id="delete" value="刪除" onclick="deletemove(this);" class="btn btn-danger dele" data-toggle="modal" data-target="#additemdetails" data-strole-no="${strokeVO.stroke_no}">
															</td>
														</tr>
													</table>
												</a>
											</div>
										</div>
										<div id="${strokeVO.stroke_no}" class="panel-collapse collapse"  >
											<div class="panel-body">
												<ul>
													<c:forEach var="days" begin="1" end="${strokeDetailsSvc.Days(strokeVO.stroke_no)}" step="1">
														<li>
															第${days}天:
															<c:set var="max" value="${strokeDetailsSvc.DaysItinerary(strokeVO.stroke_no,days).size()}"></c:set>
															<c:forEach var="strokeDetailsVO" items="${strokeDetailsSvc.DaysItinerary(strokeVO.stroke_no,days)}" varStatus="count" begin="0" end="${strokeDetailsSvc.DaysItinerary(strokeVO.stroke_no,days).size()}" step="1" >	
																【<a href="<%=request.getContextPath()%>/travel/travel.do?action=getOne_For_UserDisplay&tra_no=${strokeDetailsVO.tra_no}"> 
																	<c:out value="${travelSvc.getOneTravel(strokeDetailsVO.tra_no).tra_name}"></c:out>
																</a>】
																<c:if test="${max!=count.count}">
																	&rArr;
																</c:if>
															</c:forEach>
														</li>
													</c:forEach>
												</ul>
												<form METHOD="post" ACTION="<%=request.getContextPath()%>/stroke.do?action=detail">
													<input type="hidden" name="stroke_no" id="stroke_no" value="${strokeVO.stroke_no}" />
													<input type="submit" value="詳細資料" class="btn ">
												</form>
											</div>
										</div>
									</div>
								</c:forEach>
								<!--這可放迴圈結束-->
								
							</div>
						</div>
					</div>
				</div>
				<!--請用js自己新增,或者拿掉我好累喔-->
				<div class="modal fade" id="additemdetails" role="dialog"><!--跳出視窗-->
					<div class="modal-dialog">
						<div class="modal-content"><!--沒有會透明-->
							<div class="modal-header"><!--有一條線隔開-->
								<button type="button" class="close" data-dismiss="modal">&times;</button><!--右上X-->
						    	<h3 class="modal-title">是否刪除資訊</h3><!--modal-title跟右上X不會太開-->
						  	</div>
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/stroke.do?action=delete" class="form-horizontal">
						    	<div class="modal-footer"><!--有一條線隔開-->
						    		<input type="hidden" name="no" id="no" value="" />
						    		<button type="submit" class="update btn btn-primary">確定</button>
						      		<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
						    	</div>
					        </form>
					    </div><!--modal-content-->
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	$(".box").hover(function(){
		$(this).css("background-color","#99CCFF");
	},function(){
		$(this).css("background-color","white");
	});
	function deletemove(stroke_no){
		var no=$(stroke_no).attr("data-strole-no");
// 		alert($(stroke_no).attr("data-strole-no"))
		$("#no").val(no);
	
	}
</script>