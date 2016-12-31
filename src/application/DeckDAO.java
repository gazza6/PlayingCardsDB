package application;

import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeckDAO {

	//*******************************
	//SELECT a deck
	//*******************************
	public static Deck searchDeck (String deckId) throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM Deck WHERE ID="+deckId;

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsDeck = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeFromResultSet method and get employee object
			Deck deck = getDeckFromResultSet(rsDeck);

			//Return employee object
			return deck;
		} catch (SQLException e) {
			System.out.println("While searching a deck with " + deckId + " id, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}

	//Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
	private static Deck getDeckFromResultSet(ResultSet rs) throws SQLException
	{
		Deck deck = null;
		if (rs.next()) {
			deck = new Deck();
			deck.setId(rs.getInt("ID"));
			deck.setName(rs.getString("Name"));
			deck.setWinningOffer(rs.getInt("WinningOffer"));
			deck.setDeckCondition(rs.getInt("DeckCondition"));
		}
		return deck;
	}

	//*******************************
	//SELECT Decks
	//*******************************
	public static ObservableList<Deck> searchDecks() throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM Deck";

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsDecks = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeList method and get employee object
			ObservableList<Deck> deckList = getDeckList(rsDecks);

			//Return employee object
			return deckList;
		} catch (SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
			//Return exception
			throw e;
		}
	}

	//Select * from employees operation
	private static ObservableList<Deck> getDeckList(ResultSet rs) throws SQLException, ClassNotFoundException {
		//Declare a observable List which comprises of Employee objects
		ObservableList<Deck> deckList = FXCollections.observableArrayList();

		while (rs.next()) {
			Deck deck = new Deck();
			deck.setId(rs.getInt("ID"));
			deck.setName(rs.getString("Name"));
			deck.setWinningOffer(rs.getInt("WinningOffer"));
			deck.setDeckCondition(rs.getInt("DeckCondition"));
			//Add employee to the ObservableList
			deckList.add(deck);
		}
		//return empList (ObservableList of Employees)
		return deckList;
	}

	//Select * from employees operation
	private static ObservableList<DeckFull> getDeckFullList(ResultSet rs) throws SQLException, ClassNotFoundException {
		//Declare a observable List which comprises of Employee objects
		ObservableList<DeckFull> deckList = FXCollections.observableArrayList();

		while (rs.next()) {
			DeckFull deck = new DeckFull();
			deck.setId(rs.getInt("ID"));
			deck.setName(rs.getString("Name"));
			deck.setDeckCondition(rs.getInt("DeckCondition"));
			deck.setPrice(rs.getDouble("Price"));
			deck.setbidderName(rs.getString("BidderName"));
			deck.setDate(rs.getDate("Date"));
			deck.setImage(rs.getBinaryStream("Image"));
			deck.setRemark(rs.getString("Remark"));
			//Add employee to the ObservableList
			deckList.add(deck);
		}
		//return empList (ObservableList of Employees)
		return deckList;
	}

	//*************************************
	//Update a deck
	//*************************************
	public static Deck latesetDeck () throws SQLException, ClassNotFoundException {
		//Declare a INSERT statement

		String selectStmt =
				"SELECT * FROM Deck ORDER BY ID DESC LIMIT 0, 1";

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsDeck = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeFromResultSet method and get employee object
			Deck deck = getDeckFromResultSet(rsDeck);

			//Return employee object
			return deck;

		} catch (SQLException e) {
			System.out.println("While searching for lastest deck inserted, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}

	public static ObservableList<DeckFull> deckDetail(String order) throws SQLException, ClassNotFoundException {
		String originalStmt =
				"SELECT d.ID, o.ID as OfferID, d.Name, d.DeckCondition, d.Image, d.Remark, o.Price, o.BidderID, b.Name as BidderName, o.Date FROM Deck d LEFT JOIN Offer o on d.WinningOffer = o.ID left join Bidder b on o.BidderID = b.ID ";
		String selectStmt = null;

		switch(order){
		case "Earliest added": selectStmt = originalStmt; break;
		case "Latest added": selectStmt = originalStmt + "ORDER BY d.ID DESC"; break;
		case "Oldest first": selectStmt = originalStmt + "ORDER BY o.Date ASC"; break;
		case "Newest first": selectStmt = originalStmt + "ORDER BY o.Date DESC"; break;
		case "A > Z": selectStmt = originalStmt + "ORDER BY d.Name ASC"; break;
		case "Z > A": selectStmt = originalStmt + "ORDER BY d.Name DESC"; break;
		}

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsDeck = DBUtil.dbExecuteQuery(selectStmt);

			ObservableList<DeckFull> deckList = getDeckFullList(rsDeck);
			
			return deckList;
		} catch (SQLException e) {
			System.out.println("While sorting all decks infomation, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}

	public static ObservableList<DeckFull> search(String searchWord) throws SQLException, ClassNotFoundException {
		String originalStmt =
				"SELECT d.ID, d.Name, o.ID as OfferID, d.DeckCondition, d.Image, d.Remark, o.Price, o.BidderID, b.Name as BidderName, o.Date FROM Deck d LEFT JOIN Offer o on d.ID = o.DeckID left join Bidder b on o.BidderID = b.ID "
						+"WHERE(d.Name LIKE '%"+searchWord+"%' OR b.Name LIKE '%"+searchWord+"%')";


		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsDeck = DBUtil.dbExecuteQuery(originalStmt);
ObservableList<DeckFull> deckList = getDeckFullList(rsDeck);
			
			return deckList;
		} catch (SQLException e) {
			System.out.println("While searching for all decks infomation, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}

	//*************************************
	//INSERT a deck with no winning bid
	//*************************************
	public static void insertDeck (String name, String condition, FileInputStream fin, String remark) throws SQLException, ClassNotFoundException {
		//Declare a INSERT statement

		// To do 

		String updateStmt =
				"INSERT INTO Deck\n" +
						"(Name, DeckCondition, Remark)\n" +
						"VALUES\n" +
						"('"+name+"', "+condition+","+fin+", '"+remark+"')";

		//Execute DELETE operation
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			throw e;
		}
	}

	//*************************************
	//Update a deck's winning offer number
	//*************************************
	public static void updateDeckWinningOffer (String id, String winningOffer) throws SQLException, ClassNotFoundException {
		//Declare a INSERT statement

		// To do 

		String updateStmt =
				"UPDATE Deck\n" +
						"SET WinningOffer = '" + winningOffer + "'\n" +
						"WHERE ID = " + id;

		//Execute DELETE operation
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			throw e;
		}
	}

	//*************************************
	//Update a deck
	//*************************************
	public static void updateDeck (String id, String name,String winningOffer, String condition, String remark) throws SQLException, ClassNotFoundException {
		//Declare a INSERT statement

		// To do 

		String updateStmt =
				"BEGIN\n" +
						"UPDATE Deck\n" +
						"SET Name = '" + name + "', WinningOffer = '"+ winningOffer+ 
						"', DeckCondition = '"+ condition+ 
						"', Remark = '"+ remark+ "'\n" +
						"    WHERE ID = " + id + ";\n" +
						"   COMMIT;\n" +
						"END;";

		//Execute DELETE operation
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			throw e;
		}
	}


}
