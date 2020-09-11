package banking;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GeneratePDFServ
 */
@WebServlet("/GeneratePDFServPath")
public class GeneratePDFServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratePDFServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		ViewTransNreport G1 = new ViewTransNreport();
		String pdate=request.getParameter("pdate");
		String pedate=request.getParameter("pedate");
		//String pEmail=request.getParameter("javaSqlDate");
		String pacctNum=request.getParameter("pacctNum");
		long pacctNum1= Long.parseLong(pacctNum);
		
		//System.out.println(pdate);
		//System.out.println(pacctNum1);
		
		try{
			java.util.Date pStartdate = null;
			java.sql.Date javaSqlDate = null;

			java.util.Date penddate = null;
			java.sql.Date ejavaSqlDate = null;
					try{
						pStartdate = new SimpleDateFormat("dd-MM-yyyy").parse(pdate);
						javaSqlDate = new java.sql.Date(pStartdate.getTime());
						

						penddate = new SimpleDateFormat("dd-MM-yyyy").parse(pedate);
						ejavaSqlDate = new java.sql.Date(penddate.getTime());
					}
			
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
			
			G1.generatePDF(pdate,javaSqlDate,ejavaSqlDate,pacctNum1);
			PrintWriter out = response.getWriter();
			
			out.println("<script type='text/javascript'>");
			out.println("alert('PDF generated in C:\\OBS_PDF_logs');");
			 out.println("location='BankingAccountManagement.jsp';");
			out.println("</script>");
			/*
			RequestDispatcher rd = request.getRequestDispatcher("viewTransaction.jsp");
			out.println("<font color=red>PDF generated in C:\\OBS_PDF_logs</font>");
			rd.include(request, response);
			*/
		}
		catch (FileNotFoundException E)
		{
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("prompt('Unable to generate PDF. Please check if there is any PDF with the same file name is opened');");
			 out.println("location='BankingAccountManagement.jsp';");
			out.println("</script>");
			
			/*
			PrintWriter out = response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("TransactionResult.jsp");
			out.println("<font color=red>Please check if there is any duplicate pdf opened</font>");
			rd.include(request, response);
			*/
		}
	}

}
