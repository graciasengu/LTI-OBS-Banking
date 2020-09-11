package banking;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplyATM
 */
@WebServlet("/ApplyATM")
public class ApplyATM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyATM() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String maccno=request.getParameter("uaccountNo");
		String mpin=request.getParameter("upin");
		String mcpin=request.getParameter("ucpin");
		String mcustomerid=request.getParameter("ucustomerid");
		PrintWriter pw = response.getWriter();
	
		//session 
		SessionChecker.authorizeSession(request, response);
		
		if(!mpin.equals(mcpin))
		{
			RequestDispatcher rd = request.getRequestDispatcher("ApplyATMCard.jsp");
			pw.println("<font color=red>The entered pins do not match. Please reenter them.</font>");
			rd.include(request, response);
		}
	
		int mpinz=Integer.parseInt(mpin);
		try{
			DBconnect d1= new DBconnect();
			Connection con=d1.callDB();
			
			PreparedStatement stmt=con.prepareStatement("SELECT * FROM ATM WHERE accountno=? AND cardstatus!=?");
			stmt.setString(1, maccno);
			stmt.setString(2, "Blocked");
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next())
			{
				RequestDispatcher rd = request.getRequestDispatcher("ATMServices.jsp");
				pw.println("<font color=red>You already have an exisiting ATM Card or ATM Card Request</font>");
				rd.include(request, response);
			}
			else
			{
				stmt=con.prepareStatement("INSERT INTO Atm VALUES(?,seq_Atm.nextval,?,?,?)");
				stmt.setString(1, mcustomerid);
				stmt.setString(2, maccno);
				stmt.setInt(3, mpinz);
				stmt.setString(4,"Pending");
				stmt.executeUpdate();
				
				stmt=con.prepareStatement("INSERT INTO Request VALUES(?,seq_Request.nextval,?,?,?,?,NULL,NULL)");
				stmt.setString(1,mcustomerid);
				stmt.setString(2,"Apply ATM Card");
				stmt.setTimestamp(3,getCurrentTimeStamp());
				stmt.setString(4,"Pending");
				stmt.setString(5,"Pending");
				int checkUpdate=stmt.executeUpdate();
				
				
				Statement stm = con.createStatement();
				rs = stm.executeQuery("SELECT atmcardno FROM atm WHERE accountno ='"+ maccno +"'");
				rs.next();
				Accounts.setmATMCardNo(rs.getLong(1));
				
				con.close();
				
				if(checkUpdate!=0)
				{
					RequestDispatcher rd = request.getRequestDispatcher("SelectionMenu.jsp");
					pw.println("<font color=black>ATM Card has been sucessfully applied for.</font>");
					rd.include(request, response);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}
