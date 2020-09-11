package banking;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OBSCustLogin
 */
@WebServlet("/OBSCustLoginPath")
public class OBSCustLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String mCustomerID;
	private String mCPassword;
	private int mEmpID;
	private String mEPassword;
	
    public OBSCustLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pCustomerID=request.getParameter("userid");
		HttpSession session=request.getSession(true);  //new session created
	    session.setAttribute("userid",pCustomerID);
		
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String radio=request.getParameter("a");
		//pw.println("a" +radio);
		
		//session 
		SessionChecker.authorizeSession(request, response);
	
		if ("customer".equals(radio))
		{
			try
			{
				String pCPassword=request.getParameter("password");
				
				CustomerL c1 = new CustomerL();
				c1.setAllvar(pCustomerID);
				
				mCustomerID =CustomerL.getmcustomerID();
				mCPassword = CustomerL.getmPassword();
				
				if (pCustomerID.equals(mCustomerID) && pCPassword.equals(mCPassword))
				{						
					pw.println("<H3>You have successfully logged in. </H3>");
					pw.println("Welcome "+pCustomerID);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("SelectionMenu.jsp");
					dispatcher.forward(request, response);
				} 
					
				else if (!pCustomerID.equals(mCustomerID) || !pCPassword.equals(mCPassword))
				{
					pw.println("<H3>Login unsuccessful. Please try again.</H3>");
				}
						
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if ("emp".equals(radio))
		{
			try
			{
				String pEmpID=request.getParameter("userid");
				int empIDno = Integer.parseInt(pEmpID);
				String pEPassword=request.getParameter("password");

				Emp e1 = new Emp();
				e1.setAllvar(empIDno);
				
				mEmpID =Emp.getmEmpID();
				mEPassword = Emp.getmPassword();
				String mjob = Emp.getmJob();
						
				if(empIDno==mEmpID  && pEPassword.equals(mEPassword) && mjob.equalsIgnoreCase("Admin"))
				{
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("AdminSelection.jsp");
					dispatcher.forward(request, response);
					
				}
				else if(empIDno ==mEmpID  && pEPassword.equals(mEPassword) && mjob.equalsIgnoreCase("SuperUser"))
				{
		
					RequestDispatcher dispatcher = request.getRequestDispatcher("ApplyATMCardSU.jsp");
					dispatcher.forward(request, response);
					
				}
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}