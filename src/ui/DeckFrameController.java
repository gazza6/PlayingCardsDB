package ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DeckFrameController implements Initializable{
	
	Stage prevStage;

	@FXML
	private Label deckNameLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private Label bidderNamerLabel;
	
	@FXML
	private Label conditionLabel;
	
	@FXML
	private Label dateLabel;
	
	@FXML
	private TextArea remarkArea;
	
	@FXML
	private Button editButton;
	
	@FXML
	private Button fullBidderButton;
	
	@FXML
	private Button viewAllButton;
	
	@FXML
	private ImageView imageView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}
}
