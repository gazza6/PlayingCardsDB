package ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Bidder;
import application.BidderDAO;
import application.Deck;
import application.DeckDAO;
import application.Offer;
import application.OfferDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NewDeckController implements Initializable {

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
	private ComboBox<String> deckCombo;
	@FXML
	private ComboBox<String> conditionCombo;
	@FXML
	private Button addButton;
	@FXML
	private Button clearButton;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		conditionCombo.getItems().setAll("5", "4", "3", "2", "1");
		conditionCombo.getSelectionModel().selectFirst();
		try {
			ObservableList<Deck> decks = DeckDAO.searchDecks();
			for(Deck d : decks){
				deckCombo.getItems().add(d.getName());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		deckCombo.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String name, String deckName) {
				deckText.setText(deckName);
			}    
		});
	}

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

			Bidder bidder = BidderDAO.searchBidderName(bidderText.getText());
			if(bidder == null){
				BidderDAO.insertBidder(bidderText.getText(),"");
				bidder = BidderDAO.searchBidderName(bidderText.getText());
			} 

			String bidderID = String.valueOf(bidder.getId());

			// look for the last inserted deck, and get its deckID

			Deck deck = DeckDAO.latesetDeck();
			String deckID = String.valueOf(deck.getId());

			OfferDAO.insertOffer(bidderID, priceText.getText(), deckID, datePicker.getValue().toString(), remarkText.getText());

			// add the offerID back to the deck

			Offer offer = OfferDAO.latesetOffer();
			DeckDAO.updateDeckWinningOffer(deckID, String.valueOf(offer.getId()));

			messageLabel.setText("New Deck added!");
			clearFields();

		} catch (SQLException e) {
			messageLabel.setText("Problem occurred while inserting deck " + e);
			throw e;
		}
	}

	private void getAllDecks (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		ObservableList<Deck> decks = DeckDAO.searchDecks();
	}

}
