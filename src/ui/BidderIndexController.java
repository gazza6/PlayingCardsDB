package ui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Bidder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
	private VBox bidderBox = new VBox();
	
	private Bidder bidder;

	@FXML
	ScrollPane scroller = new ScrollPane(bidderBox);

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
	}

}
