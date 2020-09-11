package banking;
import java.sql.*;

public class LoginAccount {
	
	private String mcustomerID;
	private String mpassword;
	private String memail;
	private int motp;
	
	
	public LoginAccount()
	{
		mcustomerID ="";
		mpassword  = "";
		memail = "";
		motp =0;	
	}
		public LoginAccount(String pcustomerID,String ppassword,String pemail,int potp)
	{
		mcustomerID=pcustomerID;
		mpassword=ppassword;
		memail=pemail;
		motp=potp;
		
	}

	public String getCustomerID()
	{
			return mcustomerID;
			
	}
	
	public String getPassword()
	{
			return mpassword;
			
	}
	
	public String getEmail()
	{
			return memail;
			
	}
	
	public int getOtp()
	{
			return motp;
			
	}
	
	public boolean changePassword(String password,String customerID)
	{ 
		try
		{
			//Creating a connection  with JDBC 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
			
			//Check if existing pw = new pw
			PreparedStatement stmt=con.prepareStatement("SELECT password FROM Customer WHERE customerid=?");
			stmt.setString(1,customerID);
			ResultSet rs=stmt.executeQuery();
			rs.next();
		
			if(rs.getString(1).equals(password))
			{
				//returning false if existing pw = new pw
				return false;
			}
				
			else
				
			{	
				//Changing password 
				stmt=con.prepareStatement("UPDATE Customer SET password=? WHERE customerid=?");
				stmt.setString(1,password);
				stmt.setString(2,customerID);
				stmt.executeQuery();
			}	
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return true;
		
	}
	
}
