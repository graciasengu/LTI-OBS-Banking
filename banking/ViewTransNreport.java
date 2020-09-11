package banking;
import java.io.*;
import java.text.SimpleDateFormat;
import java.sql.*; 
import java.sql.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;

public class ViewTransNreport {  
	

        public void generatePDF(String udate,java.sql.Date javaSqlDate,java.sql.Date ejavaSqlDate, Long pacctNum1)
        {
                
        		try{
        			DBconnect d1 = new DBconnect();
        			Connection con = d1.callDB();
         			Statement stmt = con.createStatement();
         			PreparedStatement pstmt = con.prepareStatement("select * from transaction where TRANSTIMESTAMP BETWEEN ? AND ? AND ACCOUNTNO =?");
         			System.out.println("select * from transaction where TRANSTIMESTAMP BETWEEN "+ejavaSqlDate+" AND "+ javaSqlDate+" AND ACCOUNTNO =?");
         			
         			//request.setCharacterEncoding("UTF-8");
        			Font bfBold20 = new Font(FontFamily.TIMES_ROMAN, 8,
        					Font.BOLD, new BaseColor(8, 8, 8));
        			Font bfBold21 = new Font(FontFamily.TIMES_ROMAN, 8,
        					Font.NORMAL, new BaseColor(8, 8, 8));
        			
        			
         			pstmt.setDate(1, javaSqlDate);
         			pstmt.setDate(2, ejavaSqlDate);
         			pstmt.setLong(3, pacctNum1);
                     /* Step-2: Initialize PDF documents - logical objects */
                     Document my_pdf_report = new Document();
                       
                     makedir();
                     String filepathloc = "C:\\OBS_PDF_logs\\log"+udate+".pdf" ;
                     PdfWriter.getInstance(my_pdf_report, new FileOutputStream(filepathloc));

                     my_pdf_report.open();            
                     //we have four columns in our table
                     PdfPTable my_report_table = new PdfPTable(9);
                     my_report_table.setWidthPercentage(110);
                     //create a cell object
                     PdfPCell table_cell;
                    
                     String cnACCOUNTNO = "ACCOUNTNO";
                     table_cell=new PdfPCell(new Phrase(cnACCOUNTNO, bfBold20));
                     my_report_table.addCell(table_cell);
                     
                     String cnACCOUNTTYPE = "ACCOUNTTYPE";
                     table_cell=new PdfPCell(new Phrase(cnACCOUNTTYPE, bfBold20));
                     my_report_table.addCell(table_cell);
                   
                     String cnTRANSACTIONNO = "TRANSACTIONNO";
                     table_cell=new PdfPCell(new Phrase(cnTRANSACTIONNO ,bfBold20));
                     my_report_table.addCell(table_cell);
                   
                     String cnTRANSACTIONTYPE = "TRANSACTIONTYPE";
                     table_cell=new PdfPCell(new Phrase(cnTRANSACTIONTYPE,bfBold20));
                     my_report_table.addCell(table_cell);
                     
                     String cnTRANSACTIONAMT = "TRANSACTIONAMT";
                     table_cell=new PdfPCell(new Phrase(cnTRANSACTIONAMT,bfBold20));
                     my_report_table.addCell(table_cell);
                     
                     String cnTRANSTIMESTAMP = "TRANSTIMESTAMP";
                     table_cell=new PdfPCell(new Phrase(cnTRANSTIMESTAMP,bfBold20));
                     my_report_table.addCell(table_cell);
                     
                     String cnRECIPIENTNO = "RECIPIENTNO";
                     table_cell=new PdfPCell(new Phrase(cnRECIPIENTNO,bfBold20));
                     my_report_table.addCell(table_cell);
                     
                     String cnBALANCE = "BALANCE";
                     table_cell=new PdfPCell(new Phrase(cnBALANCE,bfBold20));
                     my_report_table.addCell(table_cell);
                     
                     String cnLOCATION = "LOCATION";
                     table_cell=new PdfPCell(new Phrase(cnLOCATION,bfBold20));
                     my_report_table.addCell(table_cell);
                     
                     
                     ResultSet rs = 
                    		pstmt.executeQuery() ; 
                     
                     while (rs.next()) {   
                     				
                     				
                                     int ACCOUNTNO  = rs.getInt("ACCOUNTNO");
                                     String acctno = Integer.toString(ACCOUNTNO);
                                     table_cell=new PdfPCell(new Phrase(acctno, bfBold21));
                                     my_report_table.addCell(table_cell);
                                     
                                     String ACCOUNTTYPE  = rs.getString("ACCOUNTTYPE");
                                     table_cell=new PdfPCell(new Phrase(ACCOUNTTYPE, bfBold21));
                                     my_report_table.addCell(table_cell);
                                     
                                     Long TRANSACTIONNO   = rs.getLong("TRANSACTIONNO");
                                     String sTRANSACTIONNO = Long.toString(TRANSACTIONNO);
                                     table_cell=new PdfPCell(new Phrase(sTRANSACTIONNO, bfBold21));
                                     my_report_table.addCell(table_cell);
                                     
                                     String TRANSACTIONTYPE  = rs.getString("TRANSACTIONTYPE");
                                     table_cell=new PdfPCell(new Phrase(TRANSACTIONTYPE, bfBold21));
                                     my_report_table.addCell(table_cell);
                                     
                                     int TRANSACTIONAMT = rs.getInt("TRANSACTIONAMT");
                                     String sTRANSACTIONAMT = Integer.toString(TRANSACTIONAMT);
                                     table_cell=new PdfPCell(new Phrase(sTRANSACTIONAMT, bfBold21));
                                     my_report_table.addCell(table_cell);
                                     
                                     
                                     
                                     Date TRANSTIMESTAMP = rs.getDate("TRANSTIMESTAMP");
                                     SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                     String stringdate  = dateFormat.format(TRANSTIMESTAMP); 
                                     table_cell=new PdfPCell(new Phrase(stringdate, bfBold21));
                                     my_report_table.addCell(table_cell);
                                     
                                     
                                     int RECIPIENTNO = rs.getInt("RECIPIENTNO");
                                     String sRECIPIENTNO = Integer.toString(RECIPIENTNO);
                                     table_cell=new PdfPCell(new Phrase(sRECIPIENTNO, bfBold21 ));
                                     my_report_table.addCell(table_cell);
                                     
                                     int BALANCE = rs.getInt("BALANCE");
                                     String sBALANCE = Integer.toString(BALANCE);
                                     table_cell=new PdfPCell(new Phrase(sBALANCE, bfBold21));
                                     my_report_table.addCell(table_cell);
                                 
                                     String LOCATION  = rs.getString("TLocation");
                                     table_cell=new PdfPCell(new Phrase(LOCATION, bfBold21));
                                     my_report_table.addCell(table_cell);
                                     
                                     
                                     /*
                                     String pPASSWORD=rs.getString("PASSWORD");
                                     table_cell=new PdfPCell(new Phrase(pPASSWORD));
                                     my_report_table.addCell(table_cell);
                                     
                                     String pEMAIL=rs.getString("EMAIL");
                                     table_cell=new PdfPCell(new Phrase(pEMAIL));
                                     my_report_table.addCell(table_cell);
                                     */
                                    
                                     }
                     /* Attach report table to PDF */
                     my_pdf_report.add(my_report_table);                       
                     my_pdf_report.close();
                    // System.out.println("done");
                     /* Close all DB related objects */
                     rs.close();
                     stmt.close(); 
                     con.close();           
        		}
                  catch(Exception e) 
                  {
                	  e.printStackTrace();
                	  //System.out.println("lol");
                  }
                
        }
        
       
        public static void mkfile()
        {
        	try {

    	      File file = new File("c:\\newfile.txt");

    	      if (file.createNewFile()){
    	        //System.out.println("File is created!");
    	      }else{
    	      //  System.out.println("File already exists.");
    	      }

        	} catch (IOException e) {
    	      e.printStackTrace();
    	}
        }
        
        
        
        
        
        public static void makedir(){

            File file = new File("C:\\OBS_PDF_logs");
            if (!file.exists()) {
                if (file.mkdir()) {
                  //  System.out.println("Directory is created!");
                } else {
                  //  System.out.println("Failed to create directory!");
                }
            }


        }
        
        
        
}