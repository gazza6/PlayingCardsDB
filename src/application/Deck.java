package application;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Deck {

	private IntegerProperty id;
	private StringProperty name;
	private IntegerProperty winningOffer;
	private IntegerProperty deckCondition;
	private SimpleObjectProperty<Blob> image;
	private StringProperty remark;

	public Deck(){
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.winningOffer = new SimpleIntegerProperty();
		this.deckCondition = new SimpleIntegerProperty();
		this.remark = new SimpleStringProperty();
	}

	// id
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public IntegerProperty idProperty(){
		return id;
	}

	// name
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty(){
		return name;
	}

	// winningOffer
	public int getWinningOffer() {
		return winningOffer.get();
	}
	public void setWinningOffer(int winningOffer) {
		this.winningOffer.set(winningOffer);
	}
	public IntegerProperty winningOfferProperty(){
		return winningOffer;
	}

	// condition
	public int getDeckCondition() {
		return deckCondition.get();
	}
	public void setDeckCondition(int deckCondition) {
		this.deckCondition.set(deckCondition);
	}
	public IntegerProperty deckConditionProperty(){
		return deckCondition;
	}

	// image
	public Object getImage() {
		return image.get();
	}

	public void setImage(Blob image) {
		this.image.set(image);
	}
	
	public SimpleObjectProperty<Blob> imageProperty() {
        return image;
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

	@Override
	public String toString() {
		return name.get();
	}
	
	
}
