package banking;
import java.sql.*;

public class OTPtable {

	private static int mOTP;
	//private  static String mCustomerID;
	
	public static void setOTp(int x ) {
		mOTP = x;
		} 
	
	public static int getOTp() {
		   return mOTP;
		} 
	
	
	public int retrieveOTP(String custID)
	{
		try{
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Customer WHERE CustomerID ="+ custID);
			
			while(rs.next())
			{
				mOTP = rs.getInt(2);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error at OTPtable.java");
		}
		
		return mOTP;
	}
	
}

