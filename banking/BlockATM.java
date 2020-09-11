package banking;


import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BlockATM
 */
@WebServlet("/BlockATM")
public class BlockATM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockATM() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
				int maccountno = Integer.parseInt(request.getParameter("uaccountNo")); 
				PrintWriter pw = response.getWriter();
				
				//session 
				SessionChecker.authorizeSession(request, response);
		
				//retrieving atmcard no from Accounts class
				Accounts a1=new Accounts();
				a1.setAccVar(maccountno);
				
				ATM a =new ATM();
				a.setAllvar();
				
				long matmno=ATM.getatmCardNo();
				
				//checking status of ATM Card.
				String atmstatus=ATM.getcardStatus();
				
				
				if(!atmstatus.equals("Blocked"))
				{
					//executing blocking of atmcard
					Transaction t1=new Transaction();
					t1.block(matmno);
					RequestDispatcher rd = request.getRequestDispatcher("ATMServices.jsp");
					pw.println("<font color=black>Your current ATM card has sucesfully been blocked.</font>");
					rd.include(request, response);
				}
				else 
				{
					RequestDispatcher rd = request.getRequestDispatcher("ATMServices.jsp");
					pw.println("<font color=red>Your current ATM card has already been blocked.</font>");
					rd.include(request, response);
				}
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
}

