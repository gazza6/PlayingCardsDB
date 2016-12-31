package ui;

import java.sql.SQLException;

import application.Bidder;
import application.BidderDAO;
import application.DeckFull;
import application.OfferDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewOfferController {

	@FXML
	private TextField bidderText;
	@FXML
	private TextField priceText;
	@FXML
	private Button addButton;
	@FXML
	private Button closeButton;

	private DeckFull df;

	public void setValue(DeckFull df){
		this.df = df;
	}

	@FXML
	private void close (ActionEvent actionEvent) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void addOffer (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		Bidder bidder = BidderDAO.searchBidderName(bidderText.getText());
		if(bidder == null){
			BidderDAO.insertBidder(bidderText.getText(),"");
			bidder = BidderDAO.searchBidderName(bidderText.getText());
		} 
		OfferDAO.insertOffer(String.valueOf(bidder.getId()), priceText.getText(), String.valueOf(df.getId()),  String.valueOf(df.getDate()), "");

		System.out.println("Offer added successfully");
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}
