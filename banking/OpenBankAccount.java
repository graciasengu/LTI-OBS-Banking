package banking;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OpenBankAccount
 */
@WebServlet("/OpenBankAccountPath")
public class OpenBankAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		String pAccRequestType=request.getParameter("Account Type");
		String pcustomerid=request.getParameter("customerID");
		BankingAccount NewAcc = new BankingAccount();
		
		//session 
		SessionChecker.authorizeSession(request, response);
		
		int result = NewAcc.OpenAcc(pcustomerid, pAccRequestType);
		if(result == 1)//popup box for successful
		{
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('New Account Request Successfully Created');");
			pw.println("location='BankingAccountManagement.jsp';");
			pw.println("</script>");
		}
		else if(result == 2)//popup box for not successful
		{
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('New Account Request Failed: Account Type Already Exist');");
			pw.println("location='BankingAccountManagement.jsp';");
			pw.println("</script>");
		}
		else if(result == 3)//popup box for not successful
		{
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('New Account Request Failed: Request Already Pending');");
			pw.println("location='BankingAccountManagement.jsp';");
			pw.println("</script>");
		}
		
	}

}
