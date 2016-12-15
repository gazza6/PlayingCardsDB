package ui;

import java.sql.SQLException;

import application.BidderDAO;
import application.DeckDAO;
import application.OfferDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NewDeckController {

	Stage prevStage;

	@FXML
	private Label messageLabel;
	@FXML
	private TextField deckText;
	@FXML
	private TextField priceText;
	@FXML
	private TextField bidderText;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ImageView imageView;
	@FXML
	private TextArea remarkText;
	@FXML
	private ComboBox deckCombo;
	@FXML
	private ComboBox conditionCombo;
	@FXML
	private Button addButton;
	@FXML
	private Button clearButton;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	// clear the fields
	@FXML
	private void clearInfo (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		clearFields();
	}
	
	private void clearFields(){
		deckText.setText("");
		priceText.setText("");
		bidderText.setText("");
		remarkText.setText("");
	}
	
	//add a new deck
		@FXML
		private void addDeck (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
			try {
				DeckDAO.insertDeck(deckText.getText(), conditionCombo.getSelectionModel().getSelectedItem().toString(), remarkText.getText());
				
				// search bidder name if do not exist, then create a new one
				
				// look for the last inserted deck, and get its deckID
				
				//OfferDAO.insertOffer(bidderID, priceText.getText(), deckID, datePicker.getValue(), remarkText.getText());
				
				// add the offerID back to the deck
				
				messageLabel.setText("New bidder added!");
				clearFields();
				
			} catch (SQLException e) {
				messageLabel.setText("Problem occurred while inserting deck " + e);
				throw e;
			}
		}

}
