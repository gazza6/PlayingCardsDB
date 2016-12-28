package ui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
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
	
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	public void setValues(ResultSet rs) throws SQLException{
		bidderNameLabel.setText(rs.getString("Name"));
		remarkArea.setText(rs.getString("Remark"));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}