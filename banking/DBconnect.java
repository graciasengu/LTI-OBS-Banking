package banking;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBconnect {
	
	public Connection callDB()throws Exception
	{
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			return con;

	}
	
}
