package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OfferDAO {


	//*******************************
	//SELECT a deck
	//*******************************
	public static Offer searchOffer (String offerId) throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM Offer WHERE ID="+offerId;

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsOffer = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeFromResultSet method and get employee object
			Offer offer = getOfferFromResultSet(rsOffer);

			//Return employee object
			return offer;
		} catch (SQLException e) {
			System.out.println("While searching an offer with " + offerId + " id, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}

	//Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
	private static Offer getOfferFromResultSet(ResultSet rs) throws SQLException
	{
		Offer offer = null;
		if (rs.next()) {
			offer = new Offer();
			offer.setId(rs.getInt("ID"));
			offer.setBidderId(rs.getInt("BidderID"));
			offer.setPrice(rs.getDouble("Price"));
			offer.setDeckId(rs.getInt("DeckID"));
			offer.setDate(rs.getDate("Date"));
			offer.setRemark(rs.getString("Remark"));
		}
		return offer;
	}

	//*******************************
	//SELECT Decks
	//*******************************
	public static ObservableList<Offer> searchOffers () throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM Offer";

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsOffers = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeList method and get employee object
			ObservableList<Offer> offerList = getOfferList(rsOffers);

			//Return employee object
			return offerList;
		} catch (SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
			//Return exception
			throw e;
		}
	}

	//Select * from employees operation
	private static ObservableList<Offer> getOfferList(ResultSet rs) throws SQLException, ClassNotFoundException {
		//Declare a observable List which comprises of Employee objects
		ObservableList<Offer> offerList = FXCollections.observableArrayList();

		while (rs.next()) {
			Offer offer = new Offer();
			offer.setId(rs.getInt("ID"));
			offer.setBidderId(rs.getInt("BidderID"));
			offer.setPrice(rs.getDouble("Price"));
			offer.setDeckId(rs.getInt("DeckID"));
			offer.setDate(rs.getDate("Date"));
			offer.setRemark(rs.getString("Remark"));
		}
		//return empList (ObservableList of Employees)
		return offerList;
	}
	
	public static ResultSet allOfferForBidder(Bidder bidder) throws SQLException, ClassNotFoundException {
		String selectStmt =
				"SELECT d.Name, d.DeckCondition, d.Image, d.Remark, o.Price, o.BidderID, b.Name as BidderName, o.Date FROM Deck d LEFT JOIN Offer o on d.ID = o.DeckID left join Bidder b on o.BidderID = b.ID WHERE o.BidderID = "+bidder.getId();

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsDeck = DBUtil.dbExecuteQuery(selectStmt);
			return rsDeck;
		} catch (SQLException e) {
			System.out.println("While searching for all decks infomation, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	public static ResultSet allOfferForDeck(DeckFull df) throws SQLException, ClassNotFoundException {
		String selectStmt =
				"SELECT o.Date, o.Price, b.Name, d.WinningOffer, o.ID FROM Deck d LEFT JOIN Offer o on d.ID = o.DeckID left join Bidder b on o.BidderID = b.ID WHERE o.DeckID = "+ df.getId() +" ORDER BY o.Price DESC";

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsDeck = DBUtil.dbExecuteQuery(selectStmt);
			return rsDeck;
		} catch (SQLException e) {
			System.out.println("While searching for all decks infomation, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	public static Offer latesetOffer () throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM Offer ORDER BY ID DESC LIMIT 0, 1";

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsOffer = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeFromResultSet method and get employee object
			Offer offer = getOfferFromResultSet(rsOffer);

			//Return employee object
			return offer;
		} catch (SQLException e) {
			System.out.println("While searching for lastest offer inserted, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}

	//*************************************
	//INSERT an offer
	//*************************************
	public static void insertOffer (String bidderID, String price, String deckID, String date, String remark) throws SQLException, ClassNotFoundException {
		//Declare a INSERT statement

		String updateStmt =
				"INSERT INTO Offer\n" +
						"(BidderID, Price, DeckID, Date, Remark)\n" +
						"VALUES\n" +
						"("+bidderID+","+price+","+deckID+", '"+date+"', '"+remark+"')";

		//Execute INSERT operation
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			throw e;
		}
	}

	//*************************************
	//Update an offer
	//*************************************
	public static void updateOffer (String id, String bidderID, String price, String date) throws SQLException, ClassNotFoundException {
		//Declare a INSERT statement

		String updateStmt =
						"UPDATE Offer\n" +
						"SET Price = '"+ price+ 
						"', BidderID = '"+ bidderID+ 
						"', Date = '"+ date + 
						"' WHERE ID = " + id;

		//Execute DELETE operation
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			throw e;
		}
	}

}
