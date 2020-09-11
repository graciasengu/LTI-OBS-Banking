package banking;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailCustomer {

	public EmailCustomer() {
		// TODO Auto-generated constructor stub
	}
	
	public String getemailfromID(String pID)
	{
		String uemail = null;
		try{
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Customer WHERE CustomerID ='"+ pID +"'");
			
			while(rs.next())
			{
				uemail=rs.getString(6);
			}
			}
			
		
		catch (Exception E)
		{
			E.printStackTrace();
		}

		return uemail;
	}
	
	
	
	 public void RejectIncomplete(String pUserEmail , String premark) {    
	      // Recipient's email ID needs to be mentioned.
	      String to = pUserEmail;//actual=pUserEmail

	      // Sender's email ID needs to be mentioned
	      String from = "bankoflti@gmail.com";
	      final String musername = "bankoflti@gmail.com";//username of gmail
	      final String mpassword = "Newuser$123";//Password of gmail

	      // Assuming you are sending email from gmail
	      String host = "smtp.gmail.com";

	      // Get system properties
	      Properties properties = new Properties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.auth", "true");
	      properties.setProperty("mail.smtp.starttls.enable", "true");
	      properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.port", "587");

	      // Get the default Session object.
	      Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(
	                   musername, mpassword);
	             }
	          });

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("Bank of LTI: Rejection of New Banking Account");

	         // Now set the actual message
	         message.setText("Dear Sir/Mdm," + "\r\n\r\n" + "We regret to inform you that your application for a new banking account has been rejected due to :"+premark + "\r\n" + "Please reivew the documents submitted for processing." + "\r\n\r\n" + "Regards," + "\r\n" + "Bank of LTI");

	         // Send message
	         Transport.send(message);
	        // System.out.println("Sent message successfully....");
	      }
	      catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }

}
