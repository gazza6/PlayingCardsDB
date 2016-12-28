package ui;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.Bidder;
import application.BidderDAO;
import application.DeckDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrollPaneController implements Initializable{
	
	Stage prevStage;
	
	@FXML
	private VBox vBox = new VBox();
	
	@FXML
	private ScrollPane scroller = new ScrollPane();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		scroller.setFitToWidth(true);
		
		try {
			ObservableList<Bidder> bidders = BidderDAO.searchBidders();
			int i = 0;

			for(Bidder b : bidders){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("BidderFrame.fxml"));
				AnchorPane flowPane = loader.load();
				if(i % 2 == 0){
					flowPane.setStyle("-fx-background-color: #D7DBDD");
				}
				// Get the Controller from the FXMLLoader
				BidderFrameController controller = loader.getController();
				// Set data in the controller
				controller.setValues(b);
				vBox.getChildren().add(flowPane);
				i++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
