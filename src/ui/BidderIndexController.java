package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Bidder;
import application.BidderDAO;
import application.DeckDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BidderIndexController implements Initializable{
	
Stage prevStage;
	
	@FXML
	private Label nameLabel;

	@FXML
	private ComboBox<String> sortBox;

	@FXML
	private TextField searchField;

	@FXML
	private Button searchButton;
	
	@FXML
	private VBox deckBox = new VBox();
	
	@FXML
	ScrollPane scroller = new ScrollPane(deckBox);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sortBox.getItems().setAll("Latest added", "Earliest added", "Oldest first", "Newest first", "A > Z", "Z > A");
		sortBox.getSelectionModel().selectFirst();
		
		scroller.setFitToWidth(true);
		
	}
	
	public void setBidderValue(){
		try {
			ObservableList<Bidder> bidders = BidderDAO.searchBidders();
			int i = 0;

			for(Bidder b : bidders){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("BidderFrame.fxml"));
				AnchorPane flowPane = loader.load();
				if(i % 2 == 0){
					flowPane.setStyle("-fx-background-color: #D7DBDD");
				}
				// Get the Controller from the FXMLLoader
				BidderFrameController controller = loader.getController();
				// Set data in the controller
				controller.setValues(b);
				deckBox.getChildren().add(flowPane);
				i++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
