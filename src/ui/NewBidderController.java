package ui;

import java.io.IOException;
import java.sql.SQLException;

import application.Bidder;
import application.BidderDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NewBidderController {

	Stage prevStage;

	@FXML
	private Label messageLabel;
	@FXML
	private TextField nameText;
	@FXML
	private TextArea remarkText;
	@FXML
	private Button addButton;
	@FXML
	private Button clearButton;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	//add a new bidder
	@FXML
	private void addBidder (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		try {
			Bidder bidder = BidderDAO.searchBidderName(nameText.getText());
			if(bidder == null){
				BidderDAO.insertBidder(nameText.getText(),remarkText.getText());
				messageLabel.setTextFill(Color.web("#000000"));
				messageLabel.setText("New bidder added!");
				clearFields();
			} else {
				messageLabel.setTextFill(Color.web("#FF0000"));
				messageLabel.setText("This bidder already existed!");
				
			}
			
		} catch (SQLException e) {
			messageLabel.setTextFill(Color.web("#FF0000"));
			messageLabel.setText("Problem occurred while inserting bidder " + e);
			throw e;
		}
	}

	// clear the fields
	@FXML
	private void clearInfo (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		clearFields();
	}

	private void clearFields(){
		nameText.setText("");
		remarkText.setText("");
	}

}
