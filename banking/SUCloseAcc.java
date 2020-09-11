package banking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SUCloseAcc
 */
@WebServlet("/SUCloseAccpath")
public class SUCloseAcc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SUCloseAcc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String radio=request.getParameter("a");
		//pw.println("a" +radio);
		
		if ("Approved".equals(radio))
		{
			try
			{
				String type5=request.getParameter("reqnocloseacc");
				long Reqno5=Long.parseLong(type5);//getting reqno from jsp
				
				String type6=request.getParameter("accnocloseacc");
				long Accno6=Long.parseLong(type6);//getting reqno from jsp
				

				DBconnect d1 = new DBconnect();
				Connection con = d1.callDB(); //connect jdbc
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM Request");	
				ResultSet rs=pstmt.executeQuery();
				while (rs.next())
				{
					RequestTabs r1=new RequestTabs();
					r1.CheckerCloseAccRequestApprove(Reqno5,Accno6);
					//pw.println(Reqno5+Accno6);
					//pw.println("Request for close account approved.");
					break;
				}
			}
			catch(Exception e)
			{
				pw.println(e);
			}
		}
		
		else if ("Rejected".equals(radio))
		{
			try
			{
				String type7=request.getParameter("reqnocloseacc");
				long Reqno7=Long.parseLong(type7);//getting reqno from jsp
				
				DBconnect d1 = new DBconnect();
				Connection con = d1.callDB(); //connect jdbc
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM Request");	
				ResultSet rs=pstmt.executeQuery();
				while (rs.next())
				{
					String pRemarks2=request.getParameter("remarkbox3"); //input remarks 
					RequestTabs r1=new RequestTabs(); //pulling class
					r1.CheckerRequestReject(Reqno7, pRemarks2);
					//pw.println(Reqno2+pRemarks2);
					//pw.println("Request number "+Reqno7+" has been rejected due to "+pRemarks2+".");
					break;
				}
			}
			
			catch(Exception e)
			{
				pw.println(e);
			}
		}
		response.sendRedirect("ApplyATMCardSU.jsp");
	}

}
