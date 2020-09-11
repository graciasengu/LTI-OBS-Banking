package banking;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deposit
 */
@WebServlet("/depositpath")
public class deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deposit() {

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
	
		String mcustomerID=request.getParameter("uCustomerID");
		String mAccountNo=request.getParameter("uaccountno");//getting from cust input
		int maccountno=Integer.parseInt(mAccountNo); 
				
		//session 
		SessionChecker.authorizeSession(request, response);
		
		//calling and setting the Accounts class
		Accounts a1=new Accounts();
		a1.setAccVar(maccountno);
		
		String mTransactionAmt=request.getParameter("utransactionamt");//getting from cust input
		double mtransactionamt=Double.parseDouble(mTransactionAmt);
		
		String mLocation=request.getParameter("utranslocation");
				
		SMTP s1=new SMTP();
		int rand=SMTP.randInt();
		s1.storeOTP(mcustomerID,rand);
		s1.SendOTP(mcustomerID,CustomerL.getmEmail());
		
		TransStore.set_customerID(mcustomerID);
		TransStore.set_accountno(maccountno);
		TransStore.set_transactionamt(mtransactionamt);
		TransStore.set_Location(mLocation);
		TransStore.set_transtype("Deposit");
		
		RequestDispatcher rd = request.getRequestDispatcher("TransactOTP.jsp");
		rd.include(request, response);
	}
}