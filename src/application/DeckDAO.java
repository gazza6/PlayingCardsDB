package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeckDAO {

	//*******************************
	//SELECT a deck
	//*******************************
	public static Deck searchEmployee (String empId) throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM Deck WHERE employee_id="+empId;

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeFromResultSet method and get employee object
			Deck employee = getDeckFromResultSet(rsEmp);

			//Return employee object
			return employee;
		} catch (SQLException e) {
			System.out.println("While searching an employee with " + empId + " id, an error occurred: " + e);
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
			deck.setCondition(rs.getString("Condition"));
		}
		return deck;
	}
	
	//*******************************
    //SELECT Decks
    //*******************************
    public static ObservableList<Deck> searchEmployees () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM deck";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Deck> empList = getDeckList(rsEmps);

            //Return employee object
            return empList;
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
			deck.setCondition(rs.getString("Condition"));
			//Add employee to the ObservableList
			deckList.add(deck);
		}
		//return empList (ObservableList of Employees)
		return deckList;
	}


}
