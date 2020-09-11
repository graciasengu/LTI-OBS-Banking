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
 * Servlet implementation class Admin_atmreq_Incomplete
 */
@WebServlet("/Admin_atmreq_Incomplete")
public class Admin_atmreq_Incomplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_atmreq_Incomplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try{
		String premark=request.getParameter("uremark"); 
		//System.out.println(premark);
		String checkboxValues = request.getParameter("uhidIncomplete");
		//System.out.println(checkboxValues);
		long pRequestNo = Long.parseLong(checkboxValues);
		String uhidID = request.getParameter("uhidID");
		
		RequestTabs RT1 = new RequestTabs();
		RT1.AdminRequestReject(pRequestNo,premark);
		EmailCustomer E1 = new EmailCustomer();
		String pemail = E1.getemailfromID(uhidID);
		//System.out.println(pemail);
		E1.RejectIncomplete(pemail,premark);
		
		
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("AdminSelection.jsp");
		out.println("<script type='text/javascript'>");
		out.println("alert('Rejected as per admin');");
		 out.println("location='AdminSelection.jsp';");
		out.println("</script>");
		//out.println("<font color=red>Request send to admin</font>");
		rd.include(request, response);
		}
		catch (Exception E)
		
		{
			PrintWriter out = response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("AdminSelection.jsp");
	
			out.println("<font color=red>Error encounter in sending to checker. please check with the IT support</font>");
			rd.include(request, response);
		}
		
		//System.out.println("Done");
	}

}
