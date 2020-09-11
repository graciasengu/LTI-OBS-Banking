package banking;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Accounts {
		
	private static int mAccountNo;

	private static String mAccountType;

	private static long mATMCardNo;

	private static String mStatus;

	private static double mBalance;


	public void setAccVar(int pAccountNo)
	{
		setmAccountNo(pAccountNo);
		try{
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
				
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts WHERE accountno ='"+ mAccountNo +"'");
			
			while(rs.next())
			{
				setmAccountNo(rs.getInt(2));
				setmAccountType(rs.getString(3));
				setmATMCardNo(rs.getLong(4));
				setmStatus(rs.getString(5));
				setmBalance(rs.getDouble(6));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void setmAccountNo(int pAccNo) 
	{
		mAccountNo = pAccNo;
	} 
	
	public static int getmAccountNo() 
	{
		return mAccountNo;
	} 
	
	public static void setmAccountType(String pAccType) 
	{
		mAccountType = pAccType;
	} 
	
	public static String getmAccountType() 
	{
		return mAccountType;
	} 
	
	public static void setmATMCardNo(long pATMcardno) 
	{
		mATMCardNo = pATMcardno;
	} 
	
	public static long getmATMCardNo() 
	{
		return mATMCardNo;
	} 
	
	public static void setmStatus(String pStatus) 
	{
		mStatus = pStatus;
	} 
	
	public static String getmStatus() 
	{
		return mStatus;
	} 
	
	public static void setmBalance(double pBalance) 
	{
		mBalance = pBalance;
	} 
	
	public static double getmBalance() 
	{
		return mBalance;
	} 
}
