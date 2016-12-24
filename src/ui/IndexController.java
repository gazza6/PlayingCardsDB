package ui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Deck;
import application.DeckDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

	@FXML
	private Button addButton;

	@FXML
	private VBox deckBox = new VBox();

	@FXML
	ScrollPane scroller = new ScrollPane(deckBox);

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sortBox.getItems().setAll("Oldest first", "Newest first", "A > Z", "Z < A");
		sortBox.getSelectionModel().selectFirst();
		scroller.setFitToWidth(true);

		try {
			ObservableList<Deck> decks = DeckDAO.searchDecks();

			for(Deck d : decks){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("DeckFrame.fxml"));
		        AnchorPane flowPane = loader.load();
		        // Get the Controller from the FXMLLoader
		        DeckFrameController controller = loader.getController();
		        // Set data in the controller
//		        controller.setValues(d.getName());
		        deckBox.getChildren().add(flowPane);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

//		addButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent e) {
//				URL menuBarUrl = getClass().getResource("DeckFrame.fxml");
//				AnchorPane pane;
//				try {
//					pane = FXMLLoader.load( menuBarUrl );
//					deckBox.getChildren().add(pane);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
	}

}
