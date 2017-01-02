package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Bidder;
import application.DeckDAO;
import application.DeckFull;
import application.OfferDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BidderDeckIndexController implements Initializable{

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
	private Button backButton;

	@FXML
	private VBox deckBox = new VBox();

	private Bidder bidder;

	private Boolean searchMode = false;

	@FXML
	ScrollPane scroller = new ScrollPane(deckBox);

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sortBox.getItems().setAll("Newest first", "Oldest first", "Most expensive", "Cheapest", "A > Z", "Z > A");
		sortBox.getSelectionModel().selectFirst();
		scroller.setFitToWidth(true);

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
		ObservableList<DeckFull> decks = DeckDAO.bidderDeckDetail(order, bidder.getId());
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
		ObservableList<DeckFull> decks = DeckDAO.bidderDeckDetailSearch(searchWord, sortBox.getSelectionModel().getSelectedItem().toString(), bidder.getId());
		fill(decks);
	}

	public void SetValue(Bidder bidder) throws ClassNotFoundException, SQLException, IOException{
		this.bidder = bidder;
		nameLabel.setText(bidder.getName());
		fillDecks(sortBox.getSelectionModel().getSelectedItem().toString());
	}
	
	@FXML
	public void back() throws IOException{
		URL paneUrl = getClass().getResource("BidderIndex.fxml");
		AnchorPane pane = FXMLLoader.load( paneUrl );

		BorderPane border = Start.getRoot();
		border.setCenter(pane);
	}

	@FXML
	public void fill(ObservableList<DeckFull> decks) throws IOException{

		int i = 0;

		for(DeckFull deck : decks){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DeckFrame.fxml"));
			AnchorPane flowPane = loader.load();
			if(i % 2 == 0){
				flowPane.setStyle("-fx-background-color: #D7DBDD");
			}
//			if(deck.getWinningOffer() == deck.get){
//				
//			}
			// Get the Controller from the FXMLLoader
			DeckFrameController controller = loader.getController();
			// Set data in the controller
			controller.setValues(deck);
			controller.editDisable();
			deckBox.getChildren().add(flowPane);
			i++;
		}

	}

}
