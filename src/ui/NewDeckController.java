package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import application.Bidder;
import application.BidderDAO;
import application.Deck;
import application.DeckDAO;
import application.Offer;
import application.OfferDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewDeckController implements Initializable {

	Stage prevStage;

	@FXML
	private Label messageLabel;
	@FXML
	private TextField deckText;
	@FXML
	private TextField priceText;
	@FXML
	private TextField bidderText;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ImageView imageView;
	@FXML
	private TextArea remarkText;
	@FXML
	private ComboBox<String> deckCombo;
	@FXML
	private ComboBox<String> conditionCombo;
	@FXML
	private Button addButton;
	@FXML
	private Button clearButton;
	@FXML
	private Button photoButton;
	
	final FileChooser fileChooser = new FileChooser();
	private FileInputStream photoStream;
	private File file;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		conditionCombo.getItems().setAll("5", "4", "3", "2", "1");
		conditionCombo.getSelectionModel().selectFirst();
		try {
			List<String> decks = DeckDAO.disntinctDeckName();
			for(String s : decks){
				deckCombo.getItems().add(s);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		deckCombo.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String name, String deckName) {
				deckText.setText(deckName);
			}    
		});
	}

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;

	}

	// clear the fields
	@FXML
	private void clearInfo (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		clearFields();
	}

	private void clearFields(){
		deckText.setText("");
		priceText.setText("");
		bidderText.setText("");
		remarkText.setText("");
		file = null;
		photoStream = null;
		imageView.setImage(null);
	}

	//add a new deck
	@FXML
	private void addDeck (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		try {

			if(photoStream == null){
			DeckDAO.insertDeck(deckText.getText(), conditionCombo.getSelectionModel().getSelectedItem().toString(), remarkText.getText());
			} else {
				try {
					Class.forName("com.mysql.jdbc.Driver");  
					Connection conn=DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/Cards","root","root");  
					System.out.println("connected");
					Statement stmt = conn.createStatement();
					String updateStmt =
							"INSERT INTO Deck\n" +
									"(Name, DeckCondition, Image, Remark)\n" +
									"VALUES\n" +
									"(?, ?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(updateStmt);
					ps.setString(1, deckText.getText());
					ps.setInt(2, Integer.parseInt(conditionCombo.getSelectionModel().getSelectedItem().toString()));
					ps.setBinaryStream(3, photoStream, (int) file.length());
					ps.setString(4, remarkText.getText());
					ps.executeUpdate();
					System.out.println("Added");

					conn.close();
				} catch (Exception e) {
					System.err.println("Got an exception! ");

					e.printStackTrace();
				}
			}
			// search bidder name if do not exist, then create a new one

			Bidder bidder = BidderDAO.searchBidderName(bidderText.getText());
			if(bidder == null){
				BidderDAO.insertBidder(bidderText.getText(),"");
				bidder = BidderDAO.searchBidderName(bidderText.getText());
			} 

			String bidderID = String.valueOf(bidder.getId());

			// look for the last inserted deck, and get its deckID

			Deck deck = DeckDAO.latesetDeck();
			String deckID = String.valueOf(deck.getId());

			OfferDAO.insertOffer(bidderID, priceText.getText(), deckID, datePicker.getValue().toString(), remarkText.getText());

			// add the offerID back to the deck

			Offer offer = OfferDAO.latesetOffer();
			DeckDAO.updateDeckWinningOffer(deckID, String.valueOf(offer.getId()));

			messageLabel.setText("New Deck added!");
			clearFields();

		} catch (SQLException e) {
			messageLabel.setText("Problem occurred while inserting deck " + e);
			throw e;
		}
	}

	@FXML
	private void addPhoto (ActionEvent actionEvent) throws ClassNotFoundException, SQLException, FileNotFoundException {
		File file = fileChooser.showOpenDialog(prevStage);
		System.out.println(file.toURI().toString());
		Image image = new Image(file.toURI().toString());
		imageView.setImage(image);
		this.photoStream = new FileInputStream ( file );
		this.file = file;
	}

}
