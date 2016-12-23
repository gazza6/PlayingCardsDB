package playSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Bidder;
import application.BidderDAO;
import application.DBUtil;

public class Connect {
	public static void main (String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Cards","root","root");  
			System.out.println("connected");
			Statement stmt = conn.createStatement();
			//			String updateStmt =
			//					"SELECT * FROM Bidder WHERE Name='aa'";
			//			DBUtil.dbExecuteQuery(updateStmt);
			//			Bidder bidder = BidderDAO.searchBidder("1");
			//			System.out.println("Added");

			ResultSet rs = stmt.executeQuery("SELECT * FROM Bidder");
			Bidder bidder = new Bidder();
			if(rs.next()) { 
				int id = rs.getInt("ID"); 
				String str1 = rs.getString("Name");
				bidder.setId(id); 
				bidder.setName(str1); 
				System.out.println(id);
				System.out.println(str1);
			}

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");

			e.printStackTrace();
		}
	}
}
