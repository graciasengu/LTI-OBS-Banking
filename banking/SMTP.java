package banking;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Random;

public class SMTP {
	

	public void storeOTP(String Pnric,int UOTP)
	{
		try{	

		DBconnect d1 = new DBconnect();
		Connection con = d1.callDB();
		PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO OTP VALUES(?,?)");
		pstmt1.setString(1,Pnric);
		pstmt1.setInt(2,UOTP);
		 pstmt1.executeUpdate();
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
	
		
	}
	
	public static int randInt() {
		
		int max =999999;
		int min=1;

	    Random rand = new Random();

	   
	    int randomNum = rand.nextInt((max - min) + 100000);

	    return randomNum;
	}
	
	
	public void SendOTP(String puser,String pemail)
	{// Recipient's email ID needs to be mentioned.
	      String to = pemail;//change accordingly

	      // Sender's email ID needs to be mentioned
	      String from = "bankoflti@gmail.com";//change accordingly
	      final String username = "bankoflti@gmail.com";//change accordingly
	      final String password = "Newuser$123";//change accordingly

	      // Sending email through smtp.gmail.com
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("This is from Button Bank");

	         int a = randInt();
	         
	         	      
	         OTPtable.setOTp(a);
	         
	         
	         // Set the actual message
	         message.setText("Hello, this is from Button Bank. Here is the OTP. Kindly enter : "
	            + a);

	         // Send message
	         Transport.send(message);
	        storeOTP(puser,a);
	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
		
	}
	
	
   
}