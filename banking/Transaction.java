package banking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transaction
	{
		private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

		}

		//return current balance in account
		public static double retrieveBal()
		{	
			 return Accounts.getmBalance(); 
		
				
		}

		//withdraw from account
		public long withdraw(int paccountNo,double pamt,String plocation)
		{
			
				double mcurrbal=retrieveBal();
				double mnewbal=mcurrbal-pamt;
				String macctype="";
				
				long transactid=0L;
				try
				{
					DBconnect d1= new DBconnect();
					Connection con=d1.callDB();
					
					//Updating the Accounts DB
					PreparedStatement stmt=con.prepareStatement("UPDATE Accounts SET balance=? WHERE accountno=?");
					stmt.setDouble(1,mnewbal);
					stmt.setInt(2,paccountNo);
					stmt.executeUpdate();
		
					macctype=Accounts.getmAccountType();
					
					//Update the Transaction DB
					stmt=con.prepareStatement("INSERT INTO Transaction VALUES(?,?,seq_transaction.nextval,?,?,?,?,?,?)");
					stmt.setInt(1,paccountNo);
					stmt.setString(2,macctype);
					stmt.setString(3,"Withdraw");
					stmt.setDouble(4,pamt);
					stmt.setTimestamp(5,getCurrentTimeStamp());
					stmt.setInt(6,0);
					stmt.setDouble(7,mnewbal);
					stmt.setString(8,plocation);
					stmt.executeUpdate();
					
					stmt=con.prepareStatement("SELECT seq_Transaction.currval FROM Transaction");
					ResultSet rs=stmt.executeQuery();
					rs.next();
					transactid=rs.getLong(1);
			
					con.close();
					Accounts.setmBalance(mnewbal); 
					return transactid;
				}
				
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				return transactid;	
		}
		
		//deposit to account
		public long deposit(int paccountNo,double pamt,String plocation)
		{
			
			double mcurrbal=retrieveBal();
			
			double mnewbal=mcurrbal+pamt;
			
			String macctype="";
			long transactid=0L;
			
			
			try
			{
				DBconnect d1= new DBconnect();
				Connection con=d1.callDB(); 
				
				//Updating the Accounts DB
				PreparedStatement stmt=con.prepareStatement("UPDATE Accounts SET balance=? WHERE accountno=?");
				stmt.setDouble(1,mnewbal);
				stmt.setInt(2,paccountNo);
				stmt.executeUpdate();
				
				Accounts.setmBalance(mnewbal); 
				
				//Update the Transaction DB
				macctype=Accounts.getmAccountType();
				stmt=con.prepareStatement("INSERT INTO Transaction VALUES(?,?,seq_transaction.nextval,?,?,?,?,?,?)");
				stmt.setInt(1,paccountNo);
				stmt.setString(2,macctype);
				stmt.setString(3,"Deposit");
				stmt.setDouble(4,pamt);
				stmt.setTimestamp(5,getCurrentTimeStamp());
				stmt.setInt(6,0);
				stmt.setDouble(7,mnewbal);
				stmt.setString(8,plocation);
				stmt.executeUpdate();
				
				stmt=con.prepareStatement("SELECT seq_Transaction.currval FROM Transaction");
				ResultSet rs=stmt.executeQuery();
				rs.next();
				transactid=rs.getLong(1);
				
				con.close();
				
				
				return transactid;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return transactid;
			
		}

		//transfer to another account
		public long transfer(int paccountNo,double pamt,int paccountNoA)
		{
			//sender
			double mcurrbal=retrieveBal();
			double mnewbal=mcurrbal-pamt;
			String macctype="";
			long transactid=0L;
			
			
			//recipient
			double currbalA=retrieveBal();
			double newbalA=currbalA+pamt;
			
			try
			{
				DBconnect d1= new DBconnect();
				Connection con=d1.callDB(); 
				
				//Updating the Accounts DB for sender
				PreparedStatement stmt=con.prepareStatement("UPDATE Accounts SET balance=? WHERE accountno=?");
				stmt.setDouble(1,mnewbal);
				stmt.setInt(2,paccountNo);
				stmt.executeUpdate();
				
				//Updating the Accounts DB for recipient
				stmt=con.prepareStatement("UPDATE Accounts SET balance=? WHERE accountno=?");
				stmt.setDouble(1,newbalA);
				stmt.setInt(2,paccountNoA);
				stmt.executeUpdate();
				
				Accounts.setmBalance(mnewbal); 
				
				macctype=Accounts.getmAccountType();
				
				//Update the Transaction DB
				stmt=con.prepareStatement("INSERT INTO Transaction VALUES(?,?,seq_transaction.nextval,?,?,?,?,?)");
				stmt.setInt(1,paccountNo);
				stmt.setString(2,macctype);
				stmt.setString(3,"Transfer");
				stmt.setDouble(4,pamt);
				stmt.setTimestamp(5,getCurrentTimeStamp());
				stmt.setInt(6,paccountNoA);
				stmt.setDouble(7,mnewbal);
				stmt.executeUpdate();
	
				stmt=con.prepareStatement("SELECT seq_Transaction.currval FROM Transaction");
				ResultSet rs=stmt.executeQuery();
				rs.next();
				transactid=rs.getInt(1);
				
				con.close();
			
				return transactid;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return transactid;
			
		}
		
		//block ATM card immediately
		public  void block(long patmCardno)
		{
			try
			{
				DBconnect d1= new DBconnect();
				Connection con=d1.callDB(); 
				
				PreparedStatement stmt=con.prepareStatement("UPDATE atm SET cardstatus=? WHERE atmcardno=?" );
				stmt.setString(1,"Blocked");
				stmt.setLong(2,patmCardno);
				stmt.executeUpdate();
				ATM.setcardStatus("Blocked");
				
			}				
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

