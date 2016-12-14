package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public class Offer {

	private IntegerProperty id;
	private IntegerProperty bidderID;
	private StringProperty price;
	private IntegerProperty deckID;
	private SimpleObjectProperty<Date> date;
	private StringProperty remark;
	
	
	
}
