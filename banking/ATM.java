package banking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ATM {
	
	private static String mcustomerID;
	private static long matmCardNo;
	private static int maccountNo ;
	private static int matmPin;
	private static String mcardStatus;
	
	//setting all the attributes directly from DB
	public void setAllvar()
	{
		setaccountNo(Accounts.getmAccountNo());
		try
		{
			DBconnect d1= new DBconnect();
			Connection con=d1.callDB();
			PreparedStatement stmt=con.prepareStatement("SELECT * FROM ATM WHERE accountno=?");
			stmt.setInt(1,getaccountNo());
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				
				setatmCardNo(rs.getLong(2));
				setatmPin(rs.getInt(4));
				setcardStatus(rs.getString(5));		
			}
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void setcustomerID ( String pcustomerID ) {
		mcustomerID = pcustomerID;
		
	} 
	
	public static String getcustomerID() {
		   return mcustomerID;
		
	} 

	public static void setatmCardNo ( long patmCardNo) {
		matmCardNo = patmCardNo;
		
	} 
	
	public static long getatmCardNo() {
		   return matmCardNo;
		
	} 
	
	public static void setaccountNo ( int paccountNo ) {
		maccountNo = paccountNo;
		
	} 
	
	public static int getaccountNo() {
		   return maccountNo;
		
	} 
	
	public static void setatmPin ( int patmPin ) {
		matmPin = patmPin;
		
	} 
	
	public static int getatmPin() {
		   return matmPin;
		
	} 
	
	public static void setcardStatus ( String pcardStatus ) {
		mcardStatus = pcardStatus;
		
	} 
	
	public static String getcardStatus() {
		   return mcardStatus;
		
	} 
	
}

