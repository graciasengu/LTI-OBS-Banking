package banking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/LogoutPath")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
		PrintWriter pw=response.getWriter(); 
         
		HttpSession session=request.getSession();  
		session.invalidate();  
		
		CustomerL.setmcustomerID ( null );
         
		pw.println("<script type=\"text/javascript\">");
		pw.println("alert('You have logged out successfully!');");
		pw.println("</script>");
		response.sendRedirect("CustLogin.html");
		
	}
}
