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
 * Servlet implementation class Trans_OTP
 */
@WebServlet("/Trans_OTP")
public class Trans_OTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Trans_OTP() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session 
		SessionChecker.authorizeSession(request, response);
		
		PrintWriter pw=response.getWriter();
		
		String customerID=request.getParameter("pCustomerID");
		
		OTPtable O1=new OTPtable();
		OTPtable.setOTp(O1.retrieveOTP(customerID));
		
		int motp=OTPtable.getOTp();
		int motpA=Integer.parseInt(request.getParameter("uotp"));
		long transactid=0L;
	
		
		if(motp==motpA)
		{
			
			Transaction t1=new Transaction();
			
			if(TransStore.get_transtype().equals("Deposit"))
				transactid=t1.deposit(TransStore.get_accountno(),TransStore.get_transactionamt(),TransStore.get_Location());
			
			if(TransStore.get_transtype().equals("Withdraw"))
				transactid=t1.withdraw(TransStore.get_accountno(),TransStore.get_transactionamt(),TransStore.get_Location());
			
			if(TransStore.get_transtype().equals("Transfer"))
				transactid=t1.transfer(TransStore.get_accountno(),TransStore.get_transactionamt(),TransStore.get_recipientaccountno());
			
			TransStore.set_transid(transactid);
			
			RequestDispatcher rd = request.getRequestDispatcher("Trans_Display.jsp");
			rd.include(request, response);
					
		}
		if(motp!=motpA)
		{
			RequestDispatcher rd = request.getRequestDispatcher("TransactOTP.jsp");
			pw.println("<font color=red>The entered OTP is incorrect.Please try again</font>");
			rd.include(request, response);
			
		}
	
	}

}
