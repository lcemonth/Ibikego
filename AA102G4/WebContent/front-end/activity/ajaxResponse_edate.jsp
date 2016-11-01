<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<% 

	Context ctx=new javax.naming.InitialContext();
	DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/AA102G4");
	Connection con=ds.getConnection();
	Statement stmt=con.createStatement();
	String e_date=request.getParameter("param2").trim();
	String mem_no=request.getParameter("mem_no").trim();
	 ResultSet rs = stmt.executeQuery("select count(1) as cnt from activity where to_date('"+e_date+
			 "','yyyy-mm-dd') between act_start_date and act_end_date and mem_no="+mem_no);	
	 
	 	int cnt=0;
		rs.next();
	    cnt=rs.getInt("cnt");
	    
	    int cntjact=0;
	    rs = stmt.executeQuery("select count(1) as cnt from "+
	    		"(select a.act_start_date,a.act_end_date from joinactivity ja,activity a "+
	            " where ja.act_no=a.act_no and ja.mem_no="+mem_no+") where to_date('"+e_date+
				"','yyyy-mm-dd') between act_start_date and act_end_date ");
	    rs.next();
	    cntjact=rs.getInt("cnt");
	    rs.close();
	    
		String resText="";
		if(e_date!="" && e_date!=null ){
			if(cnt!=0||cntjact!=0){
				resText="此時間衝突，請選其他時間";
			}else{
				resText="此時間OK";
			}
		}
	    out.print(resText);
    
%>

<%con.close(); %>