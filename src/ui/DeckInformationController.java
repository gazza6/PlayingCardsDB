package ui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import application.Deck;
import application.DeckDAO;
import application.DeckFull;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
	private LineChart chart;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	private ObservableList<DeckFull> decks;

	public void setDeckName(String name) throws ClassNotFoundException, SQLException{
		deckNameLabel.setText(name);
		this.decks = DeckDAO.searchDeckFull(name);
		deckCombo.getSelectionModel().select(name);
		draw();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources){
		conditionCombo.getItems().setAll("Condition All", "5", "4", "3", "2", "1");
		conditionCombo.getSelectionModel().selectFirst();

		conditionCombo.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String name, String currentPeriod) {
				draw();
			}
		});

		periodCombo.getItems().setAll("Recent 3 months", "Recent 1 months","Recent 6 months","Recent 1 year","All time", "Customise");
		periodCombo.getSelectionModel().selectFirst();

		periodCombo.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String name, String currentPeriod) {
				switch(currentPeriod){
				case "Recent 3 months": 
					untilDate.setValue(LocalDate.now());
					fromDate.setValue(untilDate.getValue().minusMonths(3));
					untilDate.setDisable(true);
					fromDate.setDisable(true);
					break;
				case "Recent 1 months":
					untilDate.setValue(LocalDate.now());
					fromDate.setValue(untilDate.getValue().minusMonths(1));
					untilDate.setDisable(true);
					fromDate.setDisable(true);
					break;
				case "Recent 6 months":
					untilDate.setValue(LocalDate.now());
					fromDate.setValue(untilDate.getValue().minusMonths(6));
					untilDate.setDisable(true);
					fromDate.setDisable(true);
					break;
				case "Recent 1 year": 
					untilDate.setValue(LocalDate.now());
					fromDate.setValue(untilDate.getValue().minusYears(1));
					untilDate.setDisable(true);
					fromDate.setDisable(true);
					break;
				case "All time":
					untilDate.setValue(LocalDate.now());
					fromDate.setValue(LocalDate.of(2010, 1, 1));
					untilDate.setDisable(true);
					fromDate.setDisable(true);
					break;
				case "Customise":
					untilDate.setDisable(false);
					fromDate.setDisable(false);
					break;
				}
				if(!currentPeriod.equals("Customise")){
					draw();
				}
			}    
		});
		
		fromDate.setOnAction(e ->
	    {
	        if(untilDate.getValue() != null){
	        	draw();
	        }
	    });
		
		untilDate.setOnAction(e ->
	    {
	        if(fromDate.getValue() != null){
	        	draw();
	        }
	    });

		untilDate.setValue(LocalDate.now());
		fromDate.setValue(untilDate.getValue().minusMonths(3));
		untilDate.setDisable(true);
		fromDate.setDisable(true);

		try {
			List<String> decks = DeckDAO.disntinctDeckName();
			for(String s : decks){
				deckCombo.getItems().add(s);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} 

		deckCombo.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String name, String deckName) {
				try {
					setDeckName(deckName);
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}    
		});

		chart.setAnimated(false);

	}

	private void drawChart(Date from, Date until, String condition){
		chart.getData().clear();
		XYChart.Series series = new XYChart.Series();
		series.setName(deckNameLabel.getText());
		for(DeckFull d : decks){
			if(d.getDate().before(from) || d.getDate().after(until)){
				continue;
			}
			if(condition.equals("Condition All")){
				series.getData().add(new XYChart.Data(d.getDate().toString(), d.getPrice()));
			} else {
				if(String.valueOf(d.getDeckCondition()).equals(condition)){
					series.getData().add(new XYChart.Data(d.getDate().toString(), d.getPrice()));
				}
			}
		}
		chart.getData().add(series);

	}

	private void draw(){
		drawChart(Date.valueOf(fromDate.getValue()), Date.valueOf(untilDate.getValue()), conditionCombo.getSelectionModel().getSelectedItem().toString());
	}

}
