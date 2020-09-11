package banking;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditParticulars
 */
@WebServlet("/EditParticulars")
public class EditParticulars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditParticulars() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String mfirstname=request.getParameter("ufirstname");
		String mlastname=request.getParameter("ulastname");
		String madd=request.getParameter("uadd");
		String memailadd=request.getParameter("uemailadd");
		String mno=request.getParameter("ucontactno");
		String mCustomerID=request.getParameter("uCustomerID");
		int mcontactno;
		PrintWriter pw = response.getWriter();
		
		//session 
		SessionChecker.authorizeSession(request, response);
		
		if(mfirstname.isEmpty()|| mlastname.isEmpty()||madd.isEmpty()||memailadd.isEmpty()||mno.isEmpty())
		{
			RequestDispatcher rd = request.getRequestDispatcher("EditParticulars.jsp");
			pw.println("<font color=red>Please enter all the fields.</font>");
			rd.include(request, response);
		}
		
		else 
		{
				try
				{
					mcontactno=Integer.parseInt(mno);
					DBconnect d1= new DBconnect();
					Connection con=d1.callDB();
					
					//Check if existing pw = new pw
					PreparedStatement stmt=con.prepareStatement("UPDATE Customer SET firstname=?,lastname=?,address=?,email=?,contactno=? WHERE customerid=?");
					stmt.setString(1,mfirstname);
					stmt.setString(2,mlastname);
					stmt.setString(3,madd);
					stmt.setString(4,memailadd);
					stmt.setInt(5,mcontactno);
					stmt.setString(6,mCustomerID);
					stmt.executeUpdate();	
					con.close();
					
					CustomerL c1= new CustomerL();
					c1.setAllvar(mCustomerID);
					
					RequestDispatcher rd = request.getRequestDispatcher("EditParticulars.jsp");
					pw.println("<font color=black>Details have been sucessfully updated.</font>");
					rd.include(request, response);
				}
				catch (Exception e)
				{
					System.out.println(e);
				
				}
		}
		
	}

}
