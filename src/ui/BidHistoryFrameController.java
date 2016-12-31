package ui;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BidHistoryFrameController {
	
	Stage prevStage;
	
	@FXML
	private Label nameLabel;

	@FXML
	private Label priceLabel;

	@FXML
	private Label dateLabel;
	
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}
	
	public void setValue(ResultSet rs) throws SQLException{
		nameLabel.setText(rs.getString("Name"));
		priceLabel.setText(rs.getString("Price"));
		dateLabel.setText(rs.getDate("Date").toString());
	}

}
