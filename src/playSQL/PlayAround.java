package playSQL;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.sun.rowset.CachedRowSetImpl;

import application.Bidder;
import application.BidderDAO;
import application.DBUtil;
import application.Deck;
import application.DeckDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayAround {
	public static void main (String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/Cards","root","root");  
			System.out.println("connected");
			File file = new File("/Users/shenghaolu/Downloads/browngn.jpg");
			FileInputStream photoStream = new FileInputStream ( file.getAbsolutePath() );
			Statement stmt = conn.createStatement();
			 ResultSet rs = null;
			String updateStmt =
					"SELECT o.ID as OfferID, o.Price, o.BidderID, b.Name as BidderName, o.Date FROM Offer o INNER JOIN Bidder b on o.BidderID = b.ID INNER JOIN Deck d on d.ID = o.DeckID where o.BidderID = 1 ";
			 rs = stmt.executeQuery(updateStmt);
			while(rs.next()){
				System.out.println(rs.getInt("OfferID"));
				System.out.println(rs.getString("BidderName"));
				System.out.println(rs.getDouble("Price"));
				System.out.println("===========");
			}

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");

			e.printStackTrace();
		}
	}
}
