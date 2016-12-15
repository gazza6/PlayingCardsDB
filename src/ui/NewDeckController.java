package ui;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NewDeckController {

	Stage prevStage;

	@FXML
	private Label messagelabel;
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
	private ComboBox deckCombo;
	@FXML
	private ComboBox conditionCombo;
	@FXML
	private Button addButton;
	@FXML
	private Button clearButton;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	// clear the fields
	@FXML
	private void clearInfo (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		deckText.setText("");
		priceText.setText("");
		bidderText.setText("");
		remarkText.setText("");
	}

}
