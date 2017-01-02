package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Bidder;
import application.BidderDAO;
import application.DeckDAO;
import application.DeckFull;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BidderIndexController implements Initializable{

	@FXML
	private Label nameLabel;

	@FXML
	private ComboBox<String> sortBox;

	@FXML
	private TextField searchField;

	@FXML
	private Button searchButton;

	@FXML
	private VBox bidderBox = new VBox();

	@FXML
	ScrollPane scroller = new ScrollPane(bidderBox);
	
	private Boolean searchMode = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sortBox.getItems().setAll("A > Z", "Z > A");
		sortBox.getSelectionModel().selectFirst();

		scroller.setFitToWidth(true);

		try {
			fillBidders(sortBox.getSelectionModel().getSelectedItem().toString());
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
						fillBidders(t1);
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
	
	private void fillBidders(String order) throws SQLException, IOException, ClassNotFoundException{
		bidderBox.getChildren().clear();
		ObservableList<Bidder> bidders = BidderDAO.bidderDetail(order);
		fill(bidders);
	}
	
	@FXML
	private void search() throws ClassNotFoundException, SQLException, IOException{
		bidderBox.getChildren().clear();
		String searchWord = searchField.getText();
		if(searchWord == ""){
			searchMode = false;
		} else {
			searchMode = true;
		}
		ObservableList<Bidder> bidders = BidderDAO.search(searchWord, sortBox.getSelectionModel().getSelectedItem().toString());
		fill(bidders);
	}

	public void fill(ObservableList<Bidder> bidders){
		try {
			
			int i = 0;

			for(Bidder b : bidders){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("BidderFrame.fxml"));
				AnchorPane flowPane = loader.load();
				if(i % 2 == 0){
					flowPane.setStyle("-fx-background-color: #D7DBDD");
				}
				ResultSet allOffer = BidderDAO.bidAllOffers(b);
				ResultSet winningOffer = BidderDAO.bidWinningOffers(b);
				int allOfferNumber = 0;
				int winningOfferNumber = 0;
				if(allOffer.next()){
					allOfferNumber = allOffer.getInt("Total");
				}
				if(winningOffer.next()){
					winningOfferNumber = winningOffer.getInt("Total");
				}
				// Get the Controller from the FXMLLoader
				BidderFrameController controller = loader.getController();
				// Set data in the controller
				controller.setValues(b, allOfferNumber, winningOfferNumber);
				bidderBox.getChildren().add(flowPane);
				i++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
