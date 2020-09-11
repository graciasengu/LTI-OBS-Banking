package banking;
import java.sql.*;

public class ForgetPassword {
	
	private String mPassword;
	
	public String GetPassword(String pCustomerID, String pCustomerEmail) {		
		
		try
		{		
			RandomStringGenerator Generator = new RandomStringGenerator();
			mPassword=Generator.RandomPassword(10);
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			
			mPassword=Encryption.encrypt(mPassword);
			
			PreparedStatement stmt=con.prepareStatement("UPDATE Customer SET password = ? WHERE customerid = ? AND email = ?"); 
			stmt.setString(1,mPassword);
			stmt.setString(2,pCustomerID);
			stmt.setString(3,pCustomerEmail);
			stmt.executeUpdate();
			con.close();
		}

		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return mPassword;
	}

}
