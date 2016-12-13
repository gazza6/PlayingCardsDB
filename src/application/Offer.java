package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public class Offer {

	private IntegerProperty id;
	private IntegerProperty bidderID;
	private SimpleObjectProperty<Date> date;
	private StringProperty price;
	private IntegerProperty deckID;
	private StringProperty remark;
	
}
