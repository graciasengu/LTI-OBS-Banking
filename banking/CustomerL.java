package banking;
import java.sql.*;
public class CustomerL {
	
	private  static Date mDOB;
	private  static String mAddress;
	private  static int mContactNo;
	private  static String mGender;
	private static String mFirstName;
	private static String mLastName;
	private static String mPassword;
	private static String mcustomerID;
	private static String mEmail;
	
	
	//CALLED THIS IF U NEED THIS - 
	//example how to call
	//CustomerL C1 = new CustomerL();
	//C1.setAllvar();
	public void setAllvar(String pcustID)throws Exception
	{
		setmcustomerID(pcustID);
		DBconnect d1 = new DBconnect();
		Connection con = d1.callDB();
			
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * from Customer WHERE CustomerID ='"+mcustomerID+"'");
		
		while(rs.next())
		{
			
			
			setmPassword(rs.getString(2));
			setmFirstName(rs.getString(3));
			setmLastName(rs.getString(4));
			setmGender(rs.getString(5));
			setmEmail(rs.getString(6));
			setmAddress(rs.getString(7));
			setmContactNo(rs.getInt(8));
			setmDOB(rs.getDate(9));
	
		}
		
	}
	
	public static void setmEmail(String pEmail) {
		mEmail = pEmail;
		} 
	
	public static String getmEmail() {
		   return mEmail;
		} 
	
	public static void setmPassword(String pPassword) {
		mPassword = pPassword;
		mPassword= Encryption.decrypt(mPassword);
		
	} 
	
	public static String getmPassword() {
		
		return mPassword;
		
	} 
			
	public static void setmDOB(Date pDOB) {
		mDOB = pDOB;
		} 
	
	public static Date getmDOB() {
		   return mDOB;
		} 
		
	public static void setmFirstName ( String pFirstName ) {
		mFirstName = pFirstName;
		} 
	
	public static String getmFirstName() {
		   return mFirstName;
		} 
	
	public static void setmLastName( String pLastName ) {
		mLastName = pLastName;
		} 
	
	public static String getmLastName() {
		   return mLastName;
		} 
	
	public static void setmGender( String pGender ) {
		mGender =  pGender;
		} 
	
	public static String getmGender() {
		   return mGender;
		} 
	
	public static void setmContactNo( int pContactNo) {
		mContactNo = pContactNo;
		} 
	
	public static int getmContactNo() {
		   return mContactNo;
		} 

	public static void setmcustomerID ( String pcustomerID ) {
		mcustomerID = pcustomerID;
		} 
	
	public static String getmcustomerID() {
		   return mcustomerID;
		} 

	public static void setmAddress ( String pAddress ) {
		mAddress = pAddress;
		} 
	
	public static String getmAddress() {
		   return mAddress;
		} 
	
	
}
