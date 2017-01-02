package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuBarController {

	@FXML // fx:id="back"
	private MenuItem back; // Value injected by FXMLLoader

	@FXML // fx:id="addBidder"
	private MenuItem addBidder; // Value injected by FXMLLoadera
	
	@FXML // fx:id="addBidder"
	private MenuItem addDeck; // Value injected by FXMLLoadera
	
	@FXML // fx:id="addBidder"
	private MenuItem viewBidder; // Value injected by FXMLLoadera

	@FXML
	void switchToIndex(ActionEvent event) throws IOException {
		switchPane("Index.fxml");
	}
	
	@FXML
	void switchToAddDeck(ActionEvent event) throws IOException {
		switchPane("NewDeck.fxml");
	}

	@FXML
	void switchToAddBidder(ActionEvent event) throws IOException {
		switchPane("NewBidder.fxml");
	}
	
	@FXML
	void switchToViewBidder(ActionEvent event) throws IOException, SQLException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("BidderIndex.fxml"));
//		AnchorPane pane = loader.load();
//		BidderIndexController controller = loader.getController();
//		// Set data in the controller
//		controller.setBidderValue();
//		BorderPane border = Start.getRoot();
//		border.setCenter(pane);
		
		switchPane("BidderIndex.fxml");
	}
	
	private void switchPane(String url) throws IOException{
		URL paneUrl = getClass().getResource(url);
		AnchorPane pane = FXMLLoader.load( paneUrl );

		BorderPane border = Start.getRoot();
		border.setCenter(pane);
	}

}
