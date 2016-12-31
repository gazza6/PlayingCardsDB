package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.DeckFull;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditDeckController implements Initializable{

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
	private Button editButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Button photoButton;
	@FXML
	private Button addBidsButton;

	final FileChooser fileChooser = new FileChooser();
	private FileInputStream photoStream;
	private File file;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;

	}

	public void setValues(DeckFull df){
		deckText.setText(df.getName());
		priceText.setText(String.valueOf(df.getPrice()));
		bidderText.setText(df.getBidderName());
		remarkText.setText(df.getRemark());
		InputStream imgStream = df.getImage(); 

		if(imgStream != null){
			Image image = new Image(imgStream);
			imageView.setImage(image);
		}
		conditionCombo.getSelectionModel().select(5-df.getDeckCondition());
	}

	public void addBids(){

	}
	
	@FXML
	private void editDeck (ActionEvent actionEvent) throws IOException{
		
	}
	
	@FXML
	private void back (ActionEvent actionEvent) throws IOException{
		URL paneUrl = getClass().getResource("Index.fxml");
		AnchorPane pane = FXMLLoader.load( paneUrl );

		BorderPane border = Start.getRoot();
		border.setCenter(pane);
	}

	@FXML
	private void addPhoto (ActionEvent actionEvent) throws ClassNotFoundException, SQLException, FileNotFoundException {
		File file = fileChooser.showOpenDialog(prevStage);

		if(file != null){
			Image image = new Image(file.toURI().toString());
			imageView.setImage(image);
			this.photoStream = new FileInputStream ( file );
			this.file = file;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conditionCombo.getItems().setAll("5", "4", "3", "2", "1");

	}

}
