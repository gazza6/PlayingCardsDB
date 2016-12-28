package playSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sun.rowset.CachedRowSetImpl;

import application.Bidder;
import application.BidderDAO;
import application.DBUtil;
import application.Deck;
import application.DeckDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
			 CachedRowSetImpl crs = null;
			ResultSet rs = stmt.executeQuery("SELECT d.Name, d.DeckCondition, o.Price, o.BidderID, b.Name, o.Date FROM Deck d LEFT JOIN Offer o on d.ID = o.DeckID left join Bidder b on o.BidderID = b.ID;");
			while(rs.next()) { 
//				int id = rs.getInt("ID"); 
//				String str1 = rs.getString("Name");
//				bidder.setId(id); 
//				bidder.setName(str1); 
//				System.out.println(id);
//				System.out.println(str1);
				System.out.println("Deck name: "+rs.getString("d.Name"));
				System.out.println("Deck condition: "+rs.getString("d.DeckCondition"));
				System.out.println("Deck price: "+rs.getString("o.Price"));
				System.out.println("Deck bidder name: "+rs.getString("b.Name"));
				System.out.println("Deck date: "+rs.getString("o.Date"));
				System.out.println("==================");
			}

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");

			e.printStackTrace();
		}
	}
}
