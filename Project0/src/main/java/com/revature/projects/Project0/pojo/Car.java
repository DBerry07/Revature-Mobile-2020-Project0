package com.revature.projects.Project0.pojo;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Car implements Serializable {

	private String make;
	private int model;
	private String colour;
	private ArrayList<Double> bids;
	private double soldValue;
	private User soldTo;
	
	public Car() {} //No args constructor
	
	public Car(String make, int model, String colour) {
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.bids = new ArrayList<Double>();
	}
	
	public String getMake() {
		return this.make;
	}
	public int getModel() {
		return this.model;
	}
	public ArrayList<Double> getBids() {
		return this.bids;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public void addBid(double bid) {
		this.bids.add(bid);
	}
	
}
