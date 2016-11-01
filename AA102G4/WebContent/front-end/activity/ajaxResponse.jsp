<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<% 
    //查詢揪團
	Context ctx=new javax.naming.InitialContext();
	DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/AA102G4");
	Connection con=ds.getConnection();
	Statement stmt=con.createStatement();
	String s_date=request.getParameter("param1").trim();
	String e_date=request.getParameter("param2").trim(); 
	String mem_no=request.getParameter("mem_no").trim(); 
	 ResultSet rs = stmt.executeQuery(
		  " select count(1) as cnt from activity where ("+
		  " to_date('"+s_date+"','yyyy-mm-dd')"+
          " between act_start_date and  act_end_date or "+
          " to_date('"+e_date+"','yyyy-mm-dd')"+
          " between act_start_date and  act_end_date or"+
          " act_start_date between to_date('"+s_date+"','yyyy-mm-dd')and to_date('"+e_date+"','yyyy-mm-dd') or"+
          " act_end_date between to_date('"+s_date+"','yyyy-mm-dd')and to_date('"+e_date+"','yyyy-mm-dd')"+
          " ) and mem_no="+mem_no);

	int cnt=0;
	rs.next();
	
    cnt=rs.getInt("cnt");
    
    //查詢個人參加的
     rs = stmt.executeQuery(
   		 " select count(1) as cnt from (select a.act_start_date,a.act_end_date from joinactivity ja,activity a "+
         " where ja.act_no=a.act_no and ja.mem_no="+mem_no+" ) where ("+
	  	 " to_date('"+s_date+"','yyyy-mm-dd')"+
         " between act_start_date and  act_end_date or "+
         " to_date('"+e_date+"','yyyy-mm-dd')"+
         " between act_start_date and  act_end_date or"+
         " act_start_date between to_date('"+s_date+"','yyyy-mm-dd')and to_date('"+e_date+"','yyyy-mm-dd') or"+
         " act_end_date between to_date('"+s_date+"','yyyy-mm-dd')and to_date('"+e_date+"','yyyy-mm-dd')"+
         " ) ");
    int cntjact=0;
    rs.next();
    cntjact=rs.getInt("cnt");
    rs.close();
    
	String resText="";
	if(cnt!=0){
		resText="此時段已有團，請選其他時間";
		if(cntjact!=0){
			resText+="，該時段已參加活動，請選其他時間";	
		}
	}else{
		resText="此時段時間OK";
		if(cntjact!=0){
			resText="該時段已參加活動，請選其他時間";	
		}
	}
    out.print(resText);
    
%>

<%con.close(); %>