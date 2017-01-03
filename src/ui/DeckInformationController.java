package ui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Deck;
import application.DeckDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeckInformationController implements Initializable{
	
	@FXML
	private Label averageLabel;

	@FXML
	private Label medianLabel;
	
	@FXML
	private Label maxLabel;
	
	@FXML
	private Label minLabel;
	
	@FXML
	private Label deckNameLabel;
	
	@FXML
	private TextField searchText;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private DatePicker fromDate;
	
	@FXML
	private DatePicker untilDate;
	
	@FXML
	private ComboBox deckCombo;
	
	@FXML
	private ComboBox conditionCombo;
	
	@FXML
	private ComboBox periodCombo;
	
	@FXML
	private AreaChart chart;
	
	public void setDeckName(String name){
		deckNameLabel.setText(name);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conditionCombo.getItems().setAll("All", "5", "4", "3", "2", "1");
		conditionCombo.getSelectionModel().selectFirst();
		
		periodCombo.getItems().setAll("Recent 3 months", "Recent 1 months","Recent 6 months","Recent 1 year","All time");
		periodCombo.getSelectionModel().selectFirst();
		
		try {
			ObservableList<Deck> decks = DeckDAO.searchDecks();
			for(Deck d : decks){
				deckCombo.getItems().add(d.getName());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		deckCombo.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String name, String deckName) {
				deckNameLabel.setText(deckName);
			}    
		});
		
	}

}
