package smart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Testdb {

	public void databaseconnect()
	{
		
		try {
			String host ="jdbc:mysql://localhost:3306/smarthealthdb";
			String Username = "root";
			String Password = "";
			
			Connection con = DriverManager.getConnection(host,Username,Password);
			if(con != null)
				System.out.println("Connected Successfully");
			
			Statement stmt = con.createStatement();
			String SQL = "desc user";
			ResultSet rs = stmt.executeQuery(SQL);
			
			System.out.println("result set is:" + rs);
			con.close();
		}
		catch ( SQLException err ) {
		System.out.println( err.getMessage( ) );
		}
		
		
		
	}
	
}
