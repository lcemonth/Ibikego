package tools;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ToolsByStart extends HttpServlet {
	
    public void destroy() {
        
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	public void init(){
		ServletContext servlet = getServletContext();  
		Map<String, String> activityMAP = new HashMap<String, String>();
		activityMAP.put("0","公開");
		activityMAP.put("1","私有");
		servlet.setAttribute("activityMAP",activityMAP);
			
		Map<String, String> reportCollectMAP = new HashMap<String, String>();
		reportCollectMAP.put("0","未處理");
		reportCollectMAP.put("1","成立");
		reportCollectMAP.put("2","未成立");
		reportCollectMAP.put("3","非檢舉");
		servlet.setAttribute("reportCollectMAP",reportCollectMAP);	
		
		Map<String, String> joinActivityMAP = new HashMap<String, String>();
		joinActivityMAP.put("0","已邀請");
		joinActivityMAP.put("1","參加");
		joinActivityMAP.put("2","不參加");
		joinActivityMAP.put("3","取消邀請");
		joinActivityMAP.put("4","被剔除");
		servlet.setAttribute("joinActivityMAP",joinActivityMAP);	

		System.out.println("AA102G4的MAP已載入!!");

	}
}
