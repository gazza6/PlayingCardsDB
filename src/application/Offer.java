package application;

import java.sql.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Offer {

	private IntegerProperty id;
	private IntegerProperty bidderID;
	private StringProperty bidderName;
	private DoubleProperty price;
	private IntegerProperty deckID;
	private SimpleObjectProperty<Date> date;
	private StringProperty remark;

	public Offer() {
		this.id = new SimpleIntegerProperty();
		this.bidderID = new SimpleIntegerProperty();
		this.bidderName = new SimpleStringProperty();
		this.price = new SimpleDoubleProperty();
		this.deckID = new SimpleIntegerProperty();
		this.date = new SimpleObjectProperty<>();
		this.remark = new SimpleStringProperty();
	}

	// id
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);;
	}
	public IntegerProperty idProperty(){
		return id;
	}

	// bidderID
	public int getBidderId() {
		return bidderID.get();
	}
	public void setBidderId(int bidderID) {
		this.bidderID.set(bidderID);;
	}
	public IntegerProperty bidderIdProperty(){
		return bidderID;
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

	// deckID
	public int getDeckId() {
		return deckID.get();
	}
	public void setDeckId(int deckID) {
		this.deckID.set(deckID);;
	}
	public IntegerProperty deckIdProperty(){
		return deckID;
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

	// remark
	public String getRemark() {
		return remark.get();
	}
	public void setRemark(String remark) {
		this.remark.set(remark);
	}
	public StringProperty remarkProperty(){
		return remark;
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
