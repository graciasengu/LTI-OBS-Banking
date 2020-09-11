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
 * Servlet implementation class SUNewATM
 */
@WebServlet("/SUNewATMpath")
public class SUNewATM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SUNewATM() {
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
				String type=request.getParameter("reqnoapplyatm1");
				long Reqno=Long.parseLong(type);//getting reqno from jsp
				
				String type1=request.getParameter("accnoapplyatm");
				long Accno=Long.parseLong(type1);//getting reqno from jsp
				
				DBconnect d1 = new DBconnect();
				Connection con = d1.callDB(); //connect jdbc
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM Request");	
				ResultSet rs=pstmt.executeQuery();
				while (rs.next())
				{
					RequestTabs r1=new RequestTabs();
					r1.CheckerCardRequestApprove(Reqno, Accno);
					//pw.println(Reqno+Accno);
					//pw.println("Request for new ATM card approved.");
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
				String type2=request.getParameter("reqnoapplyatm1");
				long Reqno2=Long.parseLong(type2);//getting reqno from jsp
				
				DBconnect d1 = new DBconnect();
				Connection con = d1.callDB(); //connect jdbc
				PreparedStatement pstmt1=con.prepareStatement("SELECT * FROM Request");	
				ResultSet rs1=pstmt1.executeQuery();
				while (rs1.next())
				{
					String pRemarks2=request.getParameter("remarkbox1"); //input remarks 
					RequestTabs r1=new RequestTabs(); //pulling class
					r1.CheckerRequestReject(Reqno2, pRemarks2);
					//pw.println(Reqno2+pRemarks2);
					//pw.println("Request number "+Reqno2+" has been rejected due to "+pRemarks2+".");
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
