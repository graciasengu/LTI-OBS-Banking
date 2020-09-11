package banking;
import java.sql.*;

public class Emp {
	
	private static int mEmpID;
	private static String mPassword;
	private static String mJob;
	
	//CALLED THIS IF U NEED THIS - 
	//example how to call
	//CustomerL C1 = new CustomerL();
	//C1.setAllvar();
	//String padmin = Request.classgetparameter("puser");
	//Emp.setAllvar(padmin);
	
		
		
		public static void setmEmpID(int n) {
			mEmpID = n;
			} 
		public static int getmEmpID() {
			   return mEmpID;
			} 

		public static void setmPassword(String n) {
			mPassword = n;
			} 
		public static String getmPassword() {
			   return mPassword;
			} 
		
		public static void setmJob(String n) {
			mJob = n;
			} 
		public static String getmJob() {
			   return mJob;
			} 
		
		public void setAllvar(int pEmpID)throws Exception
		{
			setmEmpID(pEmpID);
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
				
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Emp WHERE EmpID ="+pEmpID);
			
			while(rs.next())
			{
				setmPassword(rs.getString(2));
				setmJob(rs.getString(3));
			}
			con.close();
		}
	}
