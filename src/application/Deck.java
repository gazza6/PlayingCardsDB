package application;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Deck {

	private IntegerProperty id;
	private StringProperty name;
	private IntegerProperty winningOffer;
	private StringProperty condition;
	private Blob image;

	public Deck(){
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.winningOffer = new SimpleIntegerProperty();
		this.condition = new SimpleStringProperty();
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

	// name
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);;
	}
	public StringProperty nameProperty(){
		return name;
	}
	
	// winningOffer
	public int getWinningOffer() {
		return winningOffer.get();
	}
	public void setWinningOffer(int winningOffer) {
		this.winningOffer.set(winningOffer);;
	}
	public IntegerProperty winningOfferProperty(){
		return winningOffer;
	}
	
	// condition
	public String getCondition() {
		return condition.get();
	}
	public void setCondition(String condition) {
		this.condition.set(condition);
	}
	public StringProperty conditionProperty(){
		return condition;
	}

	// image
	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
