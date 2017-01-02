package application;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeckFull extends Deck{

	private DoubleProperty price;
	private StringProperty bidderName;
	private SimpleObjectProperty<Date> date;

	private IntegerProperty id;
	private StringProperty name;
	private IntegerProperty deckCondition;
	private SimpleObjectProperty<InputStream> image;
	private StringProperty remark;

	private IntegerProperty offerId;

	DeckFull(){
		this.price = new SimpleDoubleProperty();
		this.bidderName = new SimpleStringProperty();
		this.date = new SimpleObjectProperty<>();
		this.offerId = new SimpleIntegerProperty();
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.deckCondition = new SimpleIntegerProperty();
		this.remark = new SimpleStringProperty();
		this.image = new SimpleObjectProperty();
	}

	// id
	public int getOfferId() {
		return offerId.get();
	}
	public void setOfferId(int offerId) {
		this.offerId.set(offerId);
	}
	public IntegerProperty offerIdProperty(){
		return offerId;
	}

	// bidderID
	public double getPrice() {
		return price.get();
	}
	public void setPrice(double price) {
		this.price.set(price);;
	}
	public DoubleProperty priceProperty(){
		return price;
	}

	//hire_date
	public Date getDate(){
		return date.get();
	}

	public void setDate(Date date){
		this.date.set(date);
	}

	public SimpleObjectProperty<Date> dateProperty(){
		return date;
	}

	// name
	public String getBidderName() {
		return bidderName.get();
	}
	public void setbidderName(String name) {
		this.bidderName.set(name);;
	}
	public StringProperty bidderNameProperty(){
		return bidderName;
	}

}
