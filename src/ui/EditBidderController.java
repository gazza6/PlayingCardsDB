package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Bidder;
import application.BidderDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditBidderController {
	
	Stage prevStage;

	@FXML
	private Label messageLabel;
	@FXML
	private TextField nameText;
	@FXML
	private TextArea remarkText;
	@FXML
	private Button editButton;
	@FXML
	private Button backButton;
	
	private Bidder bidder;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}
	
	public void setValues(Bidder bidder){
		nameText.setText(bidder.getName());
		remarkText.setText(bidder.getRemark());
		this.bidder = bidder;
	}
	
	@FXML
	void switchToViewBidder(ActionEvent event) throws IOException {
		URL paneUrl = getClass().getResource("BidderIndex.fxml");
		AnchorPane pane = FXMLLoader.load( paneUrl );

		BorderPane border = Start.getRoot();
		border.setCenter(pane);
	}
	
	@FXML
	void editBidder(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		BidderDAO.updateBidder(bidder.getId(), nameText.getText(), remarkText.getText());
		messageLabel.setTextFill(Color.web("#FF0000"));
		messageLabel.setText("Bidder edited");
	}

}
