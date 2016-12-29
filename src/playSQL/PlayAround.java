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
			String updateStmt =
					"INSERT INTO Deck\n" +
							"(Name, DeckCondition, Image, Remark)\n" +
							"VALUES\n" +
							"(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(updateStmt);
			ps.setString(1, "Golden nugget brown");
			ps.setInt(2, 5);
			ps.setBinaryStream(3, photoStream, (int) file.length());
			ps.setString(4, "wrap");
			ps.executeUpdate();
			System.out.println("Added");

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");

			e.printStackTrace();
		}
	}
}
