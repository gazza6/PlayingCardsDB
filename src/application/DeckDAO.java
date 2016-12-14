package application;

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
			ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeFromResultSet method and get employee object
			Deck employee = getDeckFromResultSet(rsEmp);

			//Return employee object
			return employee;
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
			//deck.setWinningOffer(rs.getInt("WinningOffer"));
			deck.setCondition(rs.getInt("Condition"));
		}
		return deck;
	}

	//*******************************
	//SELECT Decks
	//*******************************
	public static ObservableList<Deck> searchEmployees () throws SQLException, ClassNotFoundException {
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
			//deck.setWinningOffer(rs.getInt("WinningOffer"));
			deck.setCondition(rs.getInt("Condition"));
			//Add employee to the ObservableList
			deckList.add(deck);
		}
		//return empList (ObservableList of Employees)
		return deckList;
	}

	//*************************************
	//INSERT a deck with no winning bid
	//*************************************
	public static void insertDeck (String name, String condition, String remark) throws SQLException, ClassNotFoundException {
		//Declare a INSERT statement

		// To do 

		String updateStmt =
				"BEGIN\n" +
						"INSERT INTO Deck\n" +
						"(Name, condition, remark)\n" +
						"VALUES\n" +
						"('"+name+"', '"+condition+"', '"+remark+"');\n" +
						"END;";

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
				"BEGIN\n" +
						"UPDATE Deck\n" +
						"SET WinningOffer = '" + winningOffer + "'\n" +
						"WHERE ID = " + id + ";\n" +
						"   COMMIT;\n" +
						"END;";

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
	public static void updateDeck (String name,String winningOffer, String condition, String remark) throws SQLException, ClassNotFoundException {
		//Declare a INSERT statement

		// To do 

		String updateStmt =
				"BEGIN\n" +
						"INSERT INTO Deck\n" +
						"(Name, winningOffer, condition, remark)\n" +
						"VALUES\n" +
						"('"+name+"', '"+winningOffer+"','"+condition+"', '"+remark+"');\n" +
						"END;";

		//Execute DELETE operation
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			throw e;
		}
	}


}
