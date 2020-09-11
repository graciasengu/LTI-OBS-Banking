package banking;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CloseBankAccount
 */
@WebServlet("/CloseBankAccountPath")
public class CloseBankAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//session 
		SessionChecker.authorizeSession(request, response);
		
		
		PrintWriter pw = response.getWriter();
		String pAccNumber=request.getParameter("Account Number");
		int pAccNumberInt=Integer.parseInt(pAccNumber);
		String pcustomerid=request.getParameter("customerID");
		BankingAccount OldAcc = new BankingAccount();
		int result = OldAcc.CloseAcc(pcustomerid, pAccNumberInt);
		if(result == 1) //popup box for successful
		{
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Close Account Request Successfully Created!');");
			pw.println("location='BankingAccountManagement.jsp';");
			pw.println("</script>");
		}
		else if (result == 2) //popup box for not successful :bal
		{
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Failed to Create Close Account Request : There Is Balance Remaining In The Account!');");
			pw.println("location='BankingAccountManagement.jsp';");
			pw.println("</script>");
		}
		else if (result == 3) //popup box for not successful :no acc (not possible)
		{
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Failed to Create Close Account Request : Account Does Not Exist!');");
			pw.println("location='BankingAccountManagement.jsp';");
			pw.println("</script>");
		}
	}

}
