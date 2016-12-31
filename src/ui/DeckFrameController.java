package ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Deck;
import application.DeckFull;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	private Button historyButton;

	@FXML
	private Button viewAllButton;

	@FXML
	private ImageView imageView;
	
	private DeckFull deckFull;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	public void setValues(DeckFull df) throws SQLException{
		this.deckFull = df;
		deckNameLabel.setText(df.getName());
		priceLabel.setText(String.valueOf(df.getPrice()));
		bidderNamerLabel.setText(df.getBidderName());
		conditionLabel.setText(String.valueOf(df.getDeckCondition()));
		dateLabel.setText(df.getDate().toString());
		InputStream imgStream = df.getImage(); 
		if(imgStream != null){
			Image image = new Image(imgStream);
			imageView.setImage(image);
		}
		remarkArea.setText(df.getRemark());
	}

	@FXML
	public void viewHistory() throws IOException, SQLException, ClassNotFoundException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BidHistory.fxml"));
		AnchorPane flowPane = fxmlLoader.load();
		BidHistoryController controller = fxmlLoader.getController();
		System.out.println("deckName test: "+deckFull.getName());
		controller.setValue(deckFull);
		Stage stage = new Stage();
		stage.setTitle("Bid history");
		stage.setScene(new Scene(flowPane));  
		stage.show();
	}

	@FXML
	public void editDeck() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDeck.fxml"));
		AnchorPane flowPane = loader.load();
		// Get the Controller from the FXMLLoader
		EditDeckController controller = loader.getController();
		// Set data in the controller
		controller.setValues(deckFull);
		BorderPane border = Start.getRoot();
		border.setCenter(flowPane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
