package banking;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionChecker{
	public static void authorizeSession(HttpServletRequest request,HttpServletResponse response){
		try{
			
			PrintWriter pw=response.getWriter(); 
			HttpSession session = request.getSession(false); // check for existing session
			
			if (session == null) 
			{
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert'You Are Not Logged In!');");
				pw.println("</script>");
				response.sendRedirect("CustLogin.html");
				
			}
						
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}