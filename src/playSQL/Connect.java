package playSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
	public static void main (String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/cards","root","root");  
			System.out.println("connected");
			Statement stmt = conn.createStatement();
			String sql = "Insert into deck (Name, Price, Winner, Conditions) Values ('ss', 100, 'aa(50)', 'New')";
			stmt.executeUpdate(sql);
			System.out.println("Added");
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM deck");
			 
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
		}
	}
}
