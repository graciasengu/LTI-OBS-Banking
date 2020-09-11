package banking;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String moldpw=request.getParameter("uoldpassword");
	String mnewpw=request.getParameter("unewpassword");
	String mnewpwA=request.getParameter("unewpasswordA");
	String mcustomerID=request.getParameter("uCustomerID");
	PrintWriter pw = response.getWriter();
	ChangePw c= new ChangePw();
	
	boolean mvalidPw=c.validPw(mnewpw);
	boolean mlengthPw=c.lengthPw(mnewpw);
	boolean mnewPw_same=c.newPw_same(mnewpw,mnewpwA);
	boolean mnewPw_sameCustomerID=c.newPw_sameCustomerID(mnewpw,mcustomerID);
	
	//session 
	SessionChecker.authorizeSession(request, response);
		
	 //checking the entered 2 new passwords match
	if(!mnewPw_same)
		{
			RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
			pw.println("<font color=red>The entered new passwords do not match! Please re-enter them.</font>");
			rd.include(request, response);
			
		}
		
	//checking if all fields have been filled up
	else if(moldpw.isEmpty()||mnewpw.isEmpty()||mnewpwA.isEmpty())
		{
			RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
			pw.println("<font color=red>Please enter all the fields.</font>");
			rd.include(request, response);
		}
		
	//checking if the entered password fulfills the password security criteria
	else if(!mvalidPw)
		{
			RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
			pw.println("<font color=red>Password should contain atleast one special character,one number,one lower case alphabet,one upper case alphabet</font>");
			rd.include(request, response);
		
		}
	//checking length of password
	else if(!mlengthPw)
		{
			RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
			pw.println("<font color=red>Password should be more than 8 characters in length</font>");
			rd.include(request, response);
				
		}
		
	//checking if password is same as the Customer ID
	else if(!mnewPw_sameCustomerID)
		{
			RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
			pw.println("<font color=red>Password should not be the same as Customer ID</font>");
			rd.include(request, response);
				
		}
		
	//password fulfills all requirements and has been successfully changed
	else
		{
			c.changePw(mnewpw, mcustomerID);
			RequestDispatcher rd = request.getRequestDispatcher("EditProfile.jsp");
			pw.println("<font color=black>Password has been sucesfully changed.</font>");
			rd.include(request, response);
		}
			
	
	}

}
