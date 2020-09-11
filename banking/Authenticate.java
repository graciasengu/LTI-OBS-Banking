package banking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Authenticate
{
	public String mtransactionType;
	public double mtransactionAmt;
	public int maccountNo;
	public int mrecipientAccountNo;


	//constructor
	Authenticate(String ptransactionType,double ptransactionAmt,int paccountNo,int precipientAccountNo)
	{
		mtransactionType=ptransactionType;
		mtransactionAmt=ptransactionAmt;
		maccountNo=paccountNo;
		mrecipientAccountNo=precipientAccountNo;

	}
		
	public int authen()
	{
		try
			{	
				//if transfer occurs, authenticate recipient's account number
				if(mrecipientAccountNo!=0)
				{	
					DBconnect d1= new DBconnect();
					Connection con=d1.callDB();
					PreparedStatement stmt=con.prepareStatement("SELECT CUSTOMERID FROM accounts WHERE ACCOUNTNO=?");
					stmt.setInt(1,mrecipientAccountNo);
					ResultSet rs=stmt.executeQuery();
					if(!rs.next())
					{
						con.close();
						return 2;
					}
						
				}
				//if withdraw/transfer occurs, check if there's sufficient amount
				if(mtransactionType.equals("Withdraw")||mtransactionType.equals("Transfer"))
				{
						double bal=Transaction.retrieveBal();
						//sufficient mount in the acc
						if (mtransactionAmt<=bal)
							return 1;
				}
				
			}
	
			catch(Exception e)
			{
				e.printStackTrace();
			}
		// insufficient amount in the acc	
		return 3;
	}

}
