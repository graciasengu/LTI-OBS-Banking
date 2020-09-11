package banking;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgetPasswordServlet
 */
@WebServlet("/ForgetPasswordServletPath")
public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//session 
		SessionChecker.authorizeSession(request, response);
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String mcustomerID=request.getParameter("ucustomerID");
		String mregEmail=request.getParameter("uregEmail");
		ForgetPassword userPassword = new ForgetPassword();
		PasswordEmail sendPassword = new PasswordEmail();
		String mtempPassword=(userPassword.GetPassword(mcustomerID, mregEmail));
		sendPassword.SendEmail(mregEmail, mtempPassword);
		
		RequestDispatcher rd = request.getRequestDispatcher("CustLogin.html");
		pw.println("<font color=black>Please check your email for more information to login.</font>");
		rd.include(request, response);
		
	}

}
