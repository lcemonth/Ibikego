<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>

<HTML>
<HEAD>
<TITLE> 查詢員工資料 </TITLE>
</HEAD>
<BODY>
<%
            Context ctx = new javax.naming.InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA102G4");
            Connection con =  ds.getConnection();
            Statement stmt = con.createStatement();
            String stroke_no=request.getParameter("stroke_no");
            pageContext.setAttribute("stroke_no",stroke_no);
            String whichday=request.getParameter("whichday");
            pageContext.setAttribute("whichday",whichday);
            ResultSet rs = stmt.executeQuery("SELECT t.tra_name,t.tra_add FROM strokedetails sd,travel t "+
            " WHERE t.tra_no=sd.tra_no and sd.stroke_no="+stroke_no+" and sd.stroke_whichday="+whichday);
            
            ResultSetMetaData rsmd = rs.getMetaData();
	        int numberOfColumns = rsmd.getColumnCount();
	        int cnt=1;
%>

 <table  class="table">

     <tr  class="success" align='center' valign='middle'> 
    <%for (int i = 1; i <= numberOfColumns; i++){
    	if(i==1){%>
	         <th>景點</th>
	   <% }else{%>
		     <th>地址</th>
	  <% }     
	  }%>      
	 </tr>
   
   <% while (rs.next()) { %>
     <tr class="info">
        <% for (int i = 1; i <= numberOfColumns; i++) { %> 
          <% if(i==1){%>
           <td ><div id="tra_name_<%=cnt%>"><%=rs.getString(i)%></div></td>
          <%   
          } %> 
          <% if(i==2){%>
           <td ><div id="address1_<%=cnt%>"><%=rs.getString(i)%></div></td>
         <%}
          
      } %>
     </tr>
   <% cnt=cnt+1;  } %>

 </table>

<%con.close(); %>

</BODY>
</HTML>
