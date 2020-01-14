package com.revature.projects.Project0.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Car implements Serializable {

	private String make;
	private String model;
	private int year;
	private String colour;
	private int price;
	private Map<String, Integer> offers;
	private String owner;
	private int payLeft;
	
	public Car() {} //No args constructor
	
	public Car(String make, String model, int year, String colour, int price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.colour = colour;
		this.offers = new HashMap<String, Integer>();
		this.price = price;
		this.owner = null;
	}
	
	public String getMake() {
		return this.make;
	}
	public String getModel() {
		return this.model;
	}
	public int getYear() {
		return this.year;
	}
	public int getPrice() {
		return this.price;
	}
	public String getColour() {
		return this.colour;
	}
	public int getPayment() {
		return this.payLeft;
	}
	public Map<String, Integer> getOffers() {
		if (this.owner == null) {
			return this.offers;
		}
		else {
			return null;
		}
	}
	
	public String getOwner() {
		return this.owner;
	}
	public void setOwner(String owner, int offerPrice) {
		this.owner = owner;
		this.payLeft = offerPrice;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setPayment(int payment) {
		this.payLeft = payment;
	}
	public void addOffer(String user, int offer) {
		offers.put(user, offer);
	}
	
	@Override
	public String toString(){
		String str = this.year + " " + this.colour + " " + this.make + " " + this.model + ": $" + this.price;
		return str;
	}
	
}
