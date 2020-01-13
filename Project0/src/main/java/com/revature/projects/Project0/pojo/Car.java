package com.revature.projects.Project0.pojo;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
public class Car implements Serializable {

	private String make;
	private String model;
	private int year;
	private String colour;
	private int price;
	private Map<User, Integer> offers;
	
	public Car() {} //No args constructor
	
	public Car(String make, String model, int year, String colour, int price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.colour = colour;
		this.offers = new HashMap<User, Integer>();
		this.price = price;
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
	public Map<User, Integer> getBids() {
		return this.offers;
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
	public void addOffer(User user, int offer) {
		offers.put(user, offer);
	}
	
	@Override
	public String toString(){
		String str = this.year + " " + this.colour + " " + this.make + " " + this.model + ": $" + this.price;
		return str;
	}
	
}
