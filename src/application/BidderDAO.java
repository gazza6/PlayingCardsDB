package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BidderDAO {

	public static Bidder searchBidder (String bidderId) throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM Bidder WHERE ID="+bidderId;

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeFromResultSet method and get employee object
			Bidder bidder = getBidderFromResultSet(rsEmp);

			//Return employee object
			return bidder;
			
		} catch (SQLException e) {
			System.out.println("While searching a bidder with " + bidderId + " id, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}

	//Use ResultSet from DB as parameter and set Bidder Object's attributes and return bidder object.
	private static Bidder getBidderFromResultSet(ResultSet rs) throws SQLException
	{
		Bidder bidder = null;
		if (rs.next()) {
			bidder = new Bidder();
			bidder.setId(rs.getInt("ID"));
			bidder.setName(rs.getString("Name"));
			bidder.setRemark(rs.getString("Remark"));
		}
		return bidder;
	}
	
	public static Bidder searchBidderName(String name) throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM Bidder WHERE Name="+name;

		//Execute SELECT statement
		try {
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

			//Send ResultSet to the getEmployeeFromResultSet method and get employee object
			Bidder bidder = getBidderFromResultSet(rsEmp);

			//Return employee object
			return bidder;
			
		} catch (SQLException e) {
			System.out.println("While searching a bidder with " + name + " id, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	//*******************************
    //SELECT Bidders
    //*******************************
    public static ObservableList<Bidder> searchEmployees () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Bidder";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsBidders = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Bidder> bidderList = getBidderList(rsBidders);

            //Return employee object
            return bidderList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

	//Select * from employees operation
	private static ObservableList<Bidder> getBidderList(ResultSet rs) throws SQLException, ClassNotFoundException {
		//Declare a observable List which comprises of Employee objects
		ObservableList<Bidder> bidderList = FXCollections.observableArrayList();

		while (rs.next()) {
			Bidder bidder = new Bidder();
			bidder = new Bidder();
			bidder.setId(rs.getInt("ID"));
			bidder.setName(rs.getString("Name"));
			bidder.setRemark(rs.getString("Remark"));
			//Add employee to the ObservableList
			bidderList.add(bidder);
		}
		//return empList (ObservableList of Employees)
		return bidderList;
	}
	
	//*************************************
    //INSERT a bidder
    //*************************************
    public static void insertBidder (String name, String remark) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "INSERT INTO Bidder\n" +
                        "(Name, Remark)\n" +
                        "VALUES\n" +
                        "('"+name+"', '"+remark+"')";
                     
 
        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
            System.out.println("New bidder added successfully!");
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    
  //*************************************
    //UPDATE a bidder 
    //*************************************
    public static void updateBidder (String id, String name, String remark) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE Bidder\n" +
                        "      SET Name = '" + name + "',' Remark = " + remark + "'\n" +
                        "    WHERE ID = " + id + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
}
