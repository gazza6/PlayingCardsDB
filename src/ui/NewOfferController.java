package ui;

import java.sql.SQLException;

import application.Bidder;
import application.BidderDAO;
import application.OfferDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewOfferController {

	@FXML
	private TextField bidderText;
	@FXML
	private TextField priceText;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button addButton;
	@FXML
	private Button closeButton;

	private int id;

	public void setValue(int id){
		this.id = id;
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
		OfferDAO.insertOffer(String.valueOf(bidder.getId()), priceText.getText(), String.valueOf(this.id),  datePicker.getValue().toString(), "");

		System.out.println("Offer added successfully");
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}
