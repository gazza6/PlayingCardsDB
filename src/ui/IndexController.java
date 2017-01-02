package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Deck;
import application.DeckDAO;
import application.DeckFull;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
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
	
	private Boolean searchMode = false;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// sort by id, date, by name
		sortBox.getItems().setAll("Latest added", "Earliest added", "Oldest first", "Newest first", "A > Z", "Z > A");
		sortBox.getSelectionModel().selectFirst();

		scroller.setFitToWidth(true);
		try {
			fillDecks(sortBox.getSelectionModel().getSelectedItem().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sortBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String t, String t1) {
				try {
					if(searchMode == true){
						search();
					} else {
						fillDecks(t1);
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}    
		});

	}

	private void fillDecks(String order) throws SQLException, IOException, ClassNotFoundException{
		deckBox.getChildren().clear();
		ObservableList<DeckFull> decks = DeckDAO.deckDetail(order);
		fill(decks);
	}

	@FXML
	private void search() throws ClassNotFoundException, SQLException, IOException{
		deckBox.getChildren().clear();
		String searchWord = searchField.getText();
		if(searchWord == ""){
			searchMode = false;
		} else {
			searchMode = true;
		}
		ObservableList<DeckFull> decks = DeckDAO.search(searchWord, sortBox.getSelectionModel().getSelectedItem().toString());
		fill(decks);
	}

	private void fill(ObservableList<DeckFull> decks) throws SQLException, IOException, ClassNotFoundException{
		int i = 0;
		for(DeckFull d : decks){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DeckFrame.fxml"));
			AnchorPane flowPane = loader.load();
			if(i % 2 == 0){
				flowPane.setStyle("-fx-background-color: #D7DBDD");
			}
			// Get the Controller from the FXMLLoader
			DeckFrameController controller = loader.getController();
			// Set data in the controller
			controller.setValues(d);
			deckBox.getChildren().add(flowPane);
			i++;
		}
	}

}
