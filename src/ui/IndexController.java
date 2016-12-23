package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


public class IndexController implements Initializable {

	Stage prevStage;
	
	@FXML
	private ComboBox<String> sortBox;
	
	@FXML
	private TextField searchField;
	
	@FXML
	private Button searchButton;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sortBox.getItems().setAll("Oldest first", "Newest first", "A > Z", "Z < A");
		sortBox.getSelectionModel().selectFirst();
		
	}

}
