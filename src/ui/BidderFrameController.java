package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Bidder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BidderFrameController implements Initializable{
	
	Stage prevStage;

	@FXML
	private AnchorPane pane  = new AnchorPane();
	
	@FXML
	private Label bidderNameLabel;

	@FXML
	private TextArea remarkArea;

	@FXML
	private Button editButton;

	@FXML
	private Button detailButton;
	
	private Bidder bidder;
	
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	public void setValues(Bidder bidder) throws SQLException{
		bidderNameLabel.setText(bidder.getName());
		remarkArea.setText(bidder.getRemark());
		this.bidder = bidder;
	}
	
	@FXML
	public void editBidder() throws SQLException, IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditBidder.fxml"));
		AnchorPane flowPane = loader.load();
		EditBidderController controller = loader.getController();
		// Set data in the controller
		controller.setValues(this.bidder);
		BorderPane border = Start.getRoot();
		border.setCenter(flowPane);

	}
	
	@FXML
	public void bidderDetail() throws SQLException{
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
