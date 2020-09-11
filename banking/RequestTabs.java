package banking;
import java.sql.*;

public class RequestTabs {

	public RequestTabs() {
		// TODO Auto-generated constructor stub
	}
	
	//method for timestamp
	private static java.sql.Timestamp TimeStamp()
	{	
		java.util.Date Now = new java.util.Date();
		return new java.sql.Timestamp(Now.getTime());
	}
	
	public void AdminRequestApprove(long pRequestNo)
	{
		try
		{

			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			PreparedStatement pstmt1 = con.prepareStatement("UPDATE Request SET appstatus = ?, requeststatus = ?WHERE requestno=?");
			pstmt1.setString(1,"Complete");
			pstmt1.setString(2,"Pending");
			//pstmt1.setString(3,"premark");
			pstmt1.setLong(3,pRequestNo);
			pstmt1.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public void AdminRequestReject(long pRequestNo,String premark)
	{
		try
		{
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			PreparedStatement pstmt1 = con.prepareStatement("UPDATE Request SET appstatus = ? ,REMARKS =? WHERE requestno=?");
			pstmt1.setString(1,"Incomplete");
			pstmt1.setString(2,premark);
			pstmt1.setLong(3,pRequestNo);
			pstmt1.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void CheckerNewAccRequestApprove(long pRequestNo)
	{
		try
		{
			
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			PreparedStatement pstmt1 = con.prepareStatement("UPDATE Request SET requeststatus = ?, approvetimestamp = ? WHERE requestno=?");
			pstmt1.setString(1,"Approved");
			pstmt1.setTimestamp(2,TimeStamp());
			pstmt1.setLong(3,pRequestNo);
			pstmt1.executeUpdate();
			
		
			
			PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM Request WHERE requestno=?");
			pstmt2.setLong(1,pRequestNo);
			ResultSet rs=pstmt2.executeQuery();
			String pCustomerid="";
			String pAccountType="";
			while (rs.next())
			{
				pCustomerid=rs.getString(1);
				pAccountType=rs.getString(3);
			}
			
			
			PreparedStatement pstmt3 = con.prepareStatement("INSERT INTO Accounts (customerid, accountno, accounttype, status, balance) VALUES (?,seq_Accounts.nextval,?,?,?)");
			pstmt3.setString(1,pCustomerid);
			pstmt3.setString(2,pAccountType);
			pstmt3.setString(3,"Active");
			pstmt3.setInt(4,0);
			pstmt3.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void CheckerCloseAccRequestApprove(long pRequestNo,long pAccountNo)
	{
		try
		{

			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			PreparedStatement pstmt1 = con.prepareStatement("UPDATE Request SET requeststatus = ?, approvetimestamp = ? WHERE requestno=?");
			pstmt1.setString(1,"Approved");
			pstmt1.setTimestamp(2,TimeStamp());
			pstmt1.setLong(3,pRequestNo);
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM Request WHERE requestno=?");
			pstmt2.setLong(1,pRequestNo);
			ResultSet rs=pstmt2.executeQuery();
			String pCustomerid=rs.getString(1);
			
			PreparedStatement pstmt3 = con.prepareStatement("UPDATE Accounts SET status=? WHERE customerid=? AND accountno=?");
			pstmt3.setString(1,"Inactive");
			pstmt3.setString(2,pCustomerid);
			pstmt3.setLong(3,pAccountNo);
			pstmt3.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void CheckerCardRequestApprove(long pRequestNo,long pAccountNo) 
	{
		try
		{

			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB(); 
			PreparedStatement pstmt1 = con.prepareStatement("UPDATE Request SET requeststatus=?, approvetimestamp=? WHERE requestno=?");
			pstmt1.setString(1,"Approved");
			pstmt1.setTimestamp(2,TimeStamp());
			pstmt1.setLong(3,pRequestNo);
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM Request WHERE requestno=?");
			pstmt2.setLong(1,pRequestNo);
			ResultSet rs1=pstmt2.executeQuery();
			String pCustomerid=rs1.getString(1);
			
			PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM ATM WHERE customerid=? AND accountno=? AND cardstatus=?");
			pstmt3.setString(1,pCustomerid);
			pstmt3.setLong(2,pAccountNo);
			pstmt3.setString(3,"Pending");
			ResultSet rs2=pstmt3.executeQuery();
			int pATMCardNo=rs2.getInt(2);
			
			PreparedStatement pstmt4 = con.prepareStatement("UPDATE ATM SET cardstatus=? WHERE customerid=? AND accountno=? AND atmcardno=?");
			pstmt4.setString(1,"Active");
			pstmt4.setString(2,pCustomerid);
			pstmt4.setLong(3,pAccountNo);
			pstmt4.setInt(3,pATMCardNo);
			pstmt4.executeUpdate();
						
			PreparedStatement pstmt5 = con.prepareStatement("UPDATE Accounts SET atmcardno=? WHERE customerid=? AND accountno=?");
			pstmt5.setInt(1,pATMCardNo);
			pstmt5.setString(2,pCustomerid);
			pstmt5.setLong(3,pAccountNo);
			pstmt5.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void CheckerRequestReject(long pRequestNo, String pRemarks)
	{
		try
		{
			DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			PreparedStatement pstmt1 = con.prepareStatement("UPDATE Request SET requeststatus = ?, approvetimestamp = ?, remarks=? WHERE requestno=?");
			pstmt1.setString(1,"Rejected");
			pstmt1.setTimestamp(2,TimeStamp());
			pstmt1.setString(3,pRemarks);
			pstmt1.setLong(4,pRequestNo);
			pstmt1.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
