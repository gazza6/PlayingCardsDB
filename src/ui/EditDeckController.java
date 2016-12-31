package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DeckFull;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditDeckController {

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

	}

	public void addBids(){

	}

	@FXML
	private void addPhoto (ActionEvent actionEvent) throws ClassNotFoundException, SQLException, FileNotFoundException {
		File file = fileChooser.showOpenDialog(prevStage);
		System.out.println(file.toURI().toString());
		Image image = new Image(file.toURI().toString());
		imageView.setImage(image);
		this.photoStream = new FileInputStream ( file );
		this.file = file;
	}

}
