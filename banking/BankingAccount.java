package banking;
import java.sql.*;
import banking.DBconnect;


public class BankingAccount {
	
	public BankingAccount() {
		// TODO Auto-generated constructor stub
	}
	
private int mAccountNo;

private String mAccountType;

private double mAccountBal;

private String mRequestType;

private int mVerifyAcc2;

private String mAccStatus;

private String mApplicationStatus;


	//method for timestamp
	private static java.sql.Timestamp TimeStamp()
	{	
		java.util.Date Now = new java.util.Date();
		return new java.sql.Timestamp(Now.getTime());
	}

	//method to open account returning boolean
	public int OpenAcc(String pCustomerID, String pRequestType)
	{
		try
		{	
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB(); 
			PreparedStatement pstmt1 = con.prepareStatement("SELECT Accounts.CustomerID, Accounts.AccountType, Accounts.Status, Request.Requesttype, Request.Appstatus FROM Accounts FULL OUTER JOIN Request ON Accounts.customerid=Request.customerid WHERE Accounts.CustomerID = ?");
			pstmt1.setString(1,pCustomerID);
			ResultSet rs=pstmt1.executeQuery();
			
			//if account exists
			while(rs.next())
			{
				mAccountType=rs.getString(2);
				mAccStatus=rs.getString(3);
				mRequestType=rs.getString(4);
				mApplicationStatus=rs.getString(5);
				
				//Revision 3
				
				//if request = existing account type
				if (mAccountType.equals(pRequestType))
				{
					if (mAccStatus.equals("Active"))
					{
						//if active existing account n request match return existing acc error
						return 2;
					}
					
					if (mAccStatus.equals("Inactive"))
					{
						if(mApplicationStatus == null)
						{
						PreparedStatement pstmt2=con.prepareStatement("INSERT INTO Request (customerid, requestno, requesttype, requesttimestamp, appstatus) VALUES (?,seq_Request.nextval,?,?,?)"); 
						pstmt2.setString(1,pCustomerID);
						pstmt2.setString(2,pRequestType);
						pstmt2.setTimestamp(3,TimeStamp());
						pstmt2.setString(4,"Pending");
						pstmt2.executeUpdate();	
						return 1;
						}
						else if(mApplicationStatus.equals("Pending"))
						{
							//if active existing account n request match return existing acc error
							return 3;
						}
						else 
						{	// if no pending request detected, insert request
							PreparedStatement pstmt2=con.prepareStatement("INSERT INTO Request (customerid, requestno, requesttype, requesttimestamp, appstatus) VALUES (?,seq_Request.nextval,?,?,?)"); 
							pstmt2.setString(1,pCustomerID);
							pstmt2.setString(2,pRequestType);
							pstmt2.setTimestamp(3,TimeStamp());
							pstmt2.setString(4,"Pending");
							pstmt2.executeUpdate();
							return 1;
						}
					}
				}
				
				if (!mAccountType.equals(pRequestType))
				{
					if (mAccStatus.equals("Active"))
					{
						if(mApplicationStatus == null)
						{
						PreparedStatement pstmt2=con.prepareStatement("INSERT INTO Request (customerid, requestno, requesttype, requesttimestamp, appstatus) VALUES (?,seq_Request.nextval,?,?,?)"); 
						pstmt2.setString(1,pCustomerID);
						pstmt2.setString(2,pRequestType);
						pstmt2.setTimestamp(3,TimeStamp());
						pstmt2.setString(4,"Pending");
						pstmt2.executeUpdate();	
						return 1;
						}
						else if(mApplicationStatus.equals("Pending"))
						{
							//if active existing account n request match return existing acc error
							return 3;
						}
						else 
						{	// if no pending request detected, insert request
							PreparedStatement pstmt2=con.prepareStatement("INSERT INTO Request (customerid, requestno, requesttype, requesttimestamp, appstatus) VALUES (?,seq_Request.nextval,?,?,?)"); 
							pstmt2.setString(1,pCustomerID);
							pstmt2.setString(2,pRequestType);
							pstmt2.setTimestamp(3,TimeStamp());
							pstmt2.setString(4,"Pending");
							pstmt2.executeUpdate();
							return 1;
						}
					}
					
					if (mAccStatus.equals("Inactive"))
					{
						if(mApplicationStatus == null)
						{
						PreparedStatement pstmt2=con.prepareStatement("INSERT INTO Request (customerid, requestno, requesttype, requesttimestamp, appstatus) VALUES (?,seq_Request.nextval,?,?,?)"); 
						pstmt2.setString(1,pCustomerID);
						pstmt2.setString(2,pRequestType);
						pstmt2.setTimestamp(3,TimeStamp());
						pstmt2.setString(4,"Pending");
						pstmt2.executeUpdate();	
						return 1;
						}
						else if(pRequestType.equals(mRequestType) && mApplicationStatus.equals("Pending"))
						{
							//if active existing account n request match return existing acc error
							return 3;
						}
						else 
						{	// if no pending request detected, insert request
							PreparedStatement pstmt2=con.prepareStatement("INSERT INTO Request (customerid, requestno, requesttype, requesttimestamp, appstatus) VALUES (?,seq_Request.nextval,?,?,?)"); 
							pstmt2.setString(1,pCustomerID);
							pstmt2.setString(2,pRequestType);
							pstmt2.setTimestamp(3,TimeStamp());
							pstmt2.setString(4,"Pending");
							pstmt2.executeUpdate();
							return 1;
						}
					}
				}
			}
			
			
			//if no account exists
			PreparedStatement pstmt2=con.prepareStatement("INSERT INTO Request (customerid, requestno, requesttype, requesttimestamp, appstatus) VALUES (?,seq_Request.nextval,?,?,?)"); 
			pstmt2.setString(1,pCustomerID);
			pstmt2.setString(2,pRequestType);
			pstmt2.setTimestamp(3,TimeStamp());
			pstmt2.setString(4,"Pending");
			pstmt2.executeUpdate();		
			
			con.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 1;
	}
	
	//method to close account returning integer
	public int CloseAcc(String pCustomerID,int pAccountNo)
	{
		try
		{
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB(); 
			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM Accounts WHERE customerid =?");
			pstmt1.setString(1,pCustomerID);
			ResultSet rs=pstmt1.executeQuery();
			
			while(rs.next())
			{
				mAccountNo=rs.getInt(2);
				mAccountBal=rs.getDouble(6);
				
				if (mAccountNo == pAccountNo)
				{
					if (mAccountBal == 0)
					{
						PreparedStatement pstmt2=con.prepareStatement("INSERT INTO Request (customerid, requestno, requesttype, requesttimestamp, appstatus, requestStatus) VALUES (?,seq_Request.nextval,?,?,?,?)"); 
						pstmt2.setString(1,pCustomerID);
						pstmt2.setString(2,"Delete");
						pstmt2.setTimestamp(3,TimeStamp());
						pstmt2.setString(4,"Complete");
						pstmt2.setString(5,"Pending");
						pstmt2.executeUpdate();
						mVerifyAcc2 = 1;
					}
					else 
						mVerifyAcc2 = 2;
				}
				else 
					mVerifyAcc2 = 3;
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mVerifyAcc2;
	}
	
}
