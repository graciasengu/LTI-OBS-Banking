package banking;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class PasswordEmail {

	public PasswordEmail() {
		// TODO Auto-generated constructor stub
	}
	  public void SendEmail(String pUserEmail,String pUserNewPassword) {    
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
	         message.setSubject("Bank of LTI: Forget Password Temporary Reset");

	         // Now set the actual message
	         message.setText("This is your temporary password : "+pUserNewPassword + "\r\n" + "Please use temporary password to login and change you password immediately!");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }
	      catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }

}
