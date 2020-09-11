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
 * Servlet implementation class Admin_atmreq_Verified
 */
@WebServlet("/Admin_atmreq_Verified")
public class Admin_atmreq_Verified extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_atmreq_Verified() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{			
			//call the class-Kelvin
		try
		{
			String checkboxValues = request.getParameter("uhidVerified");
			//System.out.println(checkboxValues);
			long pRequestNo = Long.parseLong(checkboxValues);
			
			
			RequestTabs RT1 = new RequestTabs();
			RT1.AdminRequestApprove(pRequestNo);
			
			PrintWriter out = response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("AdminSelection.jsp");
			out.println("<script type='text/javascript'>");
			out.println("alert('Request send to Checker');");
			 out.println("location='AdminSelection.jsp';");
			out.println("</script>");
			//out.println("<font color=red>Request send to admin</font>");
			rd.include(request, response);
			
			//System.out.println("Done");
			
		}
		catch (Exception E)
		
		{
			PrintWriter out = response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("AdminSelection.jsp");
	
			out.println("<font color=red>Error encounter in sending to checker. please check with the IT support</font>");
			rd.include(request, response);
		}
		
		
	}

}
