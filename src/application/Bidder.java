package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Bidder {

	private IntegerProperty id;
	private StringProperty name;
	private StringProperty remark;

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

	// remark
	public String getRemark() {
		return remark.get();
	}
	public void setRemark(String remark) {
		this.remark.set(remark);;
	}
	public StringProperty remarkProperty(){
		return remark;
	}

}
