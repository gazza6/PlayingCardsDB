package ui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Bidder;
import application.DeckDAO;
import application.OfferDAO;
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
	private Button addButton;

	@FXML
	private VBox deckBox = new VBox();
	
	private Bidder bidder;

	@FXML
	ScrollPane scroller = new ScrollPane(deckBox);

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sortBox.getItems().setAll("Oldest first", "Newest first", "A > Z", "Z > A");
		sortBox.getSelectionModel().selectFirst();
		scroller.setFitToWidth(true);
	}
	
	@FXML
	public void bidderAllOffers(Bidder bidder){
		this.bidder = bidder;
		nameLabel.setText(bidder.getName());
		try {
			ResultSet decks = OfferDAO.allOfferForBidder(bidder);
			int i = 0;

			while(decks.next()){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("DeckFrame.fxml"));
				AnchorPane flowPane = loader.load();
				if(i % 2 == 0){
					flowPane.setStyle("-fx-background-color: #D7DBDD");
				}
				// Get the Controller from the FXMLLoader
				DeckFrameController controller = loader.getController();
				// Set data in the controller
				//controller.setValues(decks);
				deckBox.getChildren().add(flowPane);
				i++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
