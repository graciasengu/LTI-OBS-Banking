package banking;
import java.sql.*;
public class ChangePw {
	
	public boolean validPw(String pnewpw)
	{
		String upperCaseChars = "(.*[A-Z].*)";
		if (!pnewpw.matches(upperCaseChars ))
			return false;
		String lowerCaseChars = "(.*[a-z].*)";
		if (!pnewpw.matches(lowerCaseChars ))
			return false;
		 String numbers = "(.*[0-9].*)";
		if (!pnewpw.matches(numbers ))
			return false;		 
		String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
		if (!pnewpw.matches(specialChars ))
			return false;
		
	return true;
	
	}
	
	public boolean lengthPw(String pnewpw)
	{
		if (pnewpw.length()<8)
			return false;	
		
		return true;
	}
	
	public boolean newPw_same(String pnewpw,String pnewpwA)
	{
		if(!pnewpw.equals(pnewpwA))
			return false;
		return true;
	}
	
	public boolean newPw_sameCustomerID(String pnewpw,String pcustomerID)
	{
	if(pnewpw.equals(pcustomerID))
		return false;
	return true;
	}
	
	public boolean changePw(String password,String pcustomerID)
	{

		try
		{
			if(CustomerL.getmPassword().equals(password))
				{
					//returning false if existing pw = new pw
					return false;
				}
			else
				{	
					String pwtextEncrypted =Encryption.encrypt(password);
				
					//Changing password 
					DBconnect d1= new DBconnect();
					Connection con=d1.callDB();
					PreparedStatement stmt=con.prepareStatement("UPDATE Customer SET password=? WHERE customerid=?");
					stmt.setString(1,pwtextEncrypted);
					stmt.setString(2,pcustomerID);
					stmt.executeQuery();
					con.close();
					return true;
				}		
		
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return true;
	}
}
