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
 * Servlet implementation class transfer
 */
@WebServlet("/transferpath")
public class transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//session 
		SessionChecker.authorizeSession(request, response);
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String customerID=request.getParameter("uCustomerID");
		String pAccountNo=request.getParameter("uaccountno");//getting from cust input
		int paccountno=Integer.parseInt(pAccountNo); 
		
		Accounts a1=new Accounts();
		a1.setAccVar( paccountno);
		
		//String pAccountType=request.getParameter("accttype");//getting from cust input
				
		String pTransactionAmt=request.getParameter("utransactionamt");//getting from cust input
		double ptransactionamt=Double.parseDouble(pTransactionAmt);
		
		String pRecipientAccountNo=request.getParameter("urecipientaccountno");//getting from cust input
		int precipientaccountno=Integer.parseInt(pRecipientAccountNo);
				
		Authenticate a= new Authenticate("Transfer",ptransactionamt,paccountno,precipientaccountno);
		if(a.authen()==1) //successfully authenticated
		{

			SMTP s1=new SMTP();
			int rand=SMTP.randInt();
			s1.storeOTP(customerID,rand);
			s1.SendOTP(customerID,CustomerL.getmEmail());
			
			TransStore.set_customerID(customerID);
			TransStore.set_accountno(paccountno);
			TransStore.set_transactionamt(ptransactionamt);
			TransStore.set_Location("");
			TransStore.set_transtype("Transfer");
			TransStore.set_recipientaccountno(precipientaccountno);
			RequestDispatcher rd = request.getRequestDispatcher("TransactOTP.jsp");
			rd.include(request, response);
		}
					
		else if (a.authen()==2) //recipient acct doesn't exist
		{
			pw.println("The account number entered is invalid.");
		}
		
		else if (a.authen()==3) //insuffcient amount
		{
			pw.println("The amount in the account is insufficient");
		}
		
		
	}

}
