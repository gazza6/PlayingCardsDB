package ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSeparatorUI;

import application.Deck;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeckFrameController implements Initializable{

	Stage prevStage;

	@FXML
	private AnchorPane pane  = new AnchorPane();

	@FXML
	private Label deckNameLabel;

	@FXML
	private Label priceLabel;

	@FXML
	private Label bidderNamerLabel;

	@FXML
	private Label conditionLabel;

	@FXML
	private Label dateLabel;

	@FXML
	private TextArea remarkArea;

	@FXML
	private Button detailButton;

	@FXML
	private Button viewAllButton;

	@FXML
	private ImageView imageView;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	public void setValues(ResultSet rs) throws SQLException{
		deckNameLabel.setText(rs.getString("Name"));
		priceLabel.setText(rs.getString("Price"));
		bidderNamerLabel.setText(rs.getString("BidderName"));
		conditionLabel.setText(rs.getString("DeckCondition"));
		dateLabel.setText(rs.getDate("Date").toString());
		InputStream imgStream = rs.getBinaryStream("Image"); 
		if(imgStream != null){
			Image image = new Image(imgStream);
			imageView.setImage(image);
		}
		remarkArea.setText(rs.getString("Remark"));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
