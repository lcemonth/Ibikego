<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stroke.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="locSvc" scope="page" class="com.location.model.LocationService" />
<jsp:useBean id="rcSvc" scope="page" class="com.reportcollect.model.ReportcollectService" />
<%  
	List<StrokeVO> list = (List<StrokeVO>) request.getAttribute("list");
	if(list==null){
		StrokeService strokeSvc = new StrokeService();
	     list = strokeSvc.getAll();
	    pageContext.setAttribute("list",list);
	}
	Integer mem_no=((MemberVO)session.getAttribute("memberVO")).getMem_no();
	 pageContext.setAttribute("mem_no",mem_no);
%>
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
		
		<script src="<%=request.getContextPath()%>/res/js/sweetalert2.js"></script>
		<script src="<%=request.getContextPath()%>/res/js/sweetalert2.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/sweetalert2.min.css">
		<style type="text/css">
		    .col-sm-12{ 
				height: 35px; 
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
			
			.thumbnail img {
			
			height:100px;
			}
			.thumbnail{
			height:300px;
			width:250px;
			}
			.txtlim{
			height:100px;
/* 			white-space:nowrap; */
			text-overflow:ellipsis;
			-o-text-overflow:ellipsis;
			overflow:hidden;
			}
			.box{
			width: 300px;
			height: 300px;
			background-color: #fff;
			border-radius:10px 10px 10px 10px;
			cursor:pointer;/*變手*/
			 overflow-y:hidden;         /*--設定超出的內容隱藏, IE, FireFox通用--*/
		    text-overflow:ellipsis;  /*--(IE專用)在內容超出時,在後方補上逗號--*/
		    /*white-space:nowrap;*/ /*-- 設定內容強制顯示一行,直到內容結束或<br>--*/
			float:left;
			margin:80px 20px 0px 0px;
			}
		
		img{
		width: 50%;
		height: 50%;
		border-radius:10px 10px 0px 0px;
		}

		.box:hover{
			box-shadow: 0px 0px 2px 4px #3399CC;
		}
		.aaa{
		cursor:pointer;
		}
		.aaa:hover{
			box-shadow: 0px 0px 2px 4px #3399CC;
		}
		.p1 {
		    margin-top: 15px;/*字體高(外部)*/
		    font-size: 14px;/*字體大小*/
		    letter-spacing: 2px;/*字體寬*/
		    line-height: 21px;/*字體高(內部)*/
		    color: #555;/*字體顏色*/ 
		  }
		  h3, p{
		    margin: 0;
		    padding: 0;
		    border: 0;
		    font: inherit;   /*h3大小*/
		    font-family: '蘋果儷中黑','微軟正黑體','Helvetica','Arial',sans-serif;
			}
		#pp{
			background-color: #99CCFF;
			width: 60px;
			color:#fff;
			position:relative; top:-20px; left:0%;
		
		}
		</style>
		<script type="text/javascript">
			$(function(){
				//getOne_For_Update
				
				$("input[id^='bt']").bind("click",function(){
					
					var idStr = $(this).attr("id");
					var idAry = idStr.split("_");
					$("#stroke_no").val(idAry[1]);
					 if(idAry[0]=="bt2"){
						//-----檢舉彈跳視窗-----
						swal({
						  title: '檢舉內容',
						  input: 'text',
						  showCancelButton: true,
						  inputValidator: function(value) {
						    return new Promise(function(resolve, reject) {
						      if (value) {
						        resolve();
						      } else {
						        reject('需要填寫檢舉內容!');
						      }
						    });
						  }
						}).then(function(result) {
							
						  swal({
						    type: 'success',
						    html: '檢舉送出 '
						  });
							$("#rep_cnt").val(result);
							$("#form1").attr("action","<%=request.getContextPath()%>/reportcollect/reportcollect.do"); 
					    	$("#form1_action").val("reportStroke");
					    	$("#stroke_no").val(idAry[1]);
							setTimeout(toC,2000);
						})
					   		
						
						function toC(){
							$("#form1").submit();
						}
					}else{
							$("#form1").attr("action","<%=request.getContextPath()%>/reportcollect/reportcollect.do");
							$("#form1_action").val("collectOneStroke");
							$("#stroke_no").val(idAry[1]);
							$("#form1").submit();
					}
				
				}) 
			})
			
		</script>
	</head>
	<body>
		<div class="row">
			<jsp:include page="/front-end/top.jsp"></jsp:include>
		</div>
		
		<div class="col-sm-12 "></div> <!-- 與TOP的距離 -->
		<br>
		<div class="row">
			<div class="col-xs-12 col-sm-10">
			<div class="row">
 				<jsp:include page="/front-end/activity/left.jsp"></jsp:include> 
				<div class="col-xs-9 col-sm-10">
					<div class="container">
					 	
					   <h1>討論區</h1>
					   	<div class="bg"></div>
						<div class="content"></div>
						<div class="row">
							<c:forEach var="strokeVO" items="${list}" >
								<div class="col-xs-12 col-sm-3">
									<div class="thumbnail aaa">
											<div class="caption">
												<h3 style=color:#0066CC><b>${strokeVO.stroke_no}</b></h3>
												<p class="txtlim">
													${strokeVO.stroke_name}
												</p>
													<c:if test="${rcSvc.getOneRepStrokeBymem_no(mem_no,strokeVO.stroke_no)==null}">   
														<input type="button" id="bt2_${strokeVO.stroke_no}" value="檢舉" class="btn btn-primary">
													</c:if>
													<c:if test="${rcSvc.getOneRepStrokeBymem_no(mem_no,strokeVO.stroke_no)!=null}">   
														<input type="button" id="bt2_${strokeVO.stroke_no}" value="已檢舉" class="btn btn-default" disabled='true'>
													</c:if>
													<input type="button" id="bt3_${strokeVO.stroke_no}" value="收藏" class="btn btn-primary">	  						
											</div>
									</div>
								</div>
							</c:forEach>
								
								<FORM METHOD="post" id="form1" ACTION="">
									 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								     <input type="hidden" id="stroke_no" name="stroke_no" value="">
								     <input type="hidden" id="form1_action" name="action" value="">
								     <input type="hidden" id="rep_cnt" name="rep_cnt" value="">
							  	</FORM>
						</div>	
	 					
					</div><!-- container-->
					
				
				</div><!-- 內層  col-sm-10-->
			</div><!-- 內層  col-sm-10 row-->
			</div><!-- 外層  col-sm-10-->
			<div class="col-xs-1 col-sm-2" id="aaa" >
				<%-- 錯誤表列 --%>
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
			</div><!-- 外層  col-sm-2-->
		</div><!-- 最外層  col-sm-10 row-->
</body>

<style>
#aaa{
position:relative;
}

</style>
</html>