package ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Bidder;
import application.DeckFull;
import application.OfferDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BidHistoryController implements Initializable{

	Stage prevStage;

	@FXML
	private Label nameLabel;

	@FXML
	private ImageView imageView;

	@FXML
	private VBox offerVBox = new VBox();

	public void setValue(DeckFull df) throws ClassNotFoundException, SQLException, IOException{
		//this.df = df;
		nameLabel.setText(df.getName());
		InputStream imgStream = df.getImage(); 
		if(imgStream != null){
			Image image = new Image(imgStream);
			imageView.setImage(image);
			imgStream.reset();
		}
		int i = 0;

		ResultSet rs = OfferDAO.allOfferForDeck(df);

		while(rs.next()){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BidHistoryFrame.fxml"));
			AnchorPane flowPane = loader.load();
			if(i % 2 == 0){
				flowPane.setStyle("-fx-background-color: #D7DBDD");
			}
			if(rs.getInt("WinningOffer") == rs.getInt("ID")){
				flowPane.setStyle("-fx-background-color: #F6AA3D");
			}
			// Get the Controller from the FXMLLoader
			BidHistoryFrameController controller = loader.getController();
			// Set data in the controller
			controller.setValue(rs);
			offerVBox.getChildren().add(flowPane);
			i++;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
	}

}
