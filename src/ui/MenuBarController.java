package ui;

import java.io.IOException;
import java.net.URL;

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

	@FXML
	void switchToIndex(ActionEvent event) throws IOException {

		switchPane("Index.fxml");

	}

	@FXML
	void switchToAddBidder(ActionEvent event) throws IOException {
		switchPane("NewBidder.fxml");
	}
	
	private void switchPane(String url) throws IOException{
		URL paneUrl = getClass().getResource(url);
		AnchorPane pane = FXMLLoader.load( paneUrl );

		BorderPane border = Start.getRoot();
		border.setCenter(pane);
	}

}
