package banking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SUNewAcc
 */
@WebServlet("/SUNewAccpath")
public class SUNewAcc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SUNewAcc() {
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
				String type=request.getParameter("reqnonewacc");
				long Reqno=Long.parseLong(type);//getting reqno from jsp
				
				DBconnect d1 = new DBconnect();
				Connection con = d1.callDB(); //connect jdbc
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM Request");	
				ResultSet rs=pstmt.executeQuery();
				while (rs.next())
				{
					RequestTabs r1=new RequestTabs();
					r1.CheckerNewAccRequestApprove(Reqno);
					//pw.println(Reqno);
					//pw.println("Requestno "+Reqno+" has been successfully approved.");
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
				String type=request.getParameter("reqnonewacc");
				long Reqno=Long.parseLong(type);//getting reqno from jsp
				
				DBconnect d1 = new DBconnect();
				Connection con = d1.callDB(); //connect jdbc
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM Request");	
				ResultSet rs=pstmt.executeQuery();
				while (rs.next())
				{
					String pRemarks=request.getParameter("remarkbox2"); //input remarks 
					RequestTabs r1=new RequestTabs(); //pulling class
					r1.CheckerRequestReject(Reqno,pRemarks);
					//pw.println(Reqno+pRemarks);
					//pw.println("Requestno "+Reqno+" has been rejected due to "+pRemarks+".");
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
