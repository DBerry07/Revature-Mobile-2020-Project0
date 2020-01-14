package com.revature.projects.Project0.pojo;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class User implements Serializable{
	
	private String username;
	private String password;
	private boolean admin = false;
	
	public User() {} //No args constructor
	
	public User(String username, String password, boolean admin) {
		this.admin = admin;
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public boolean getAdmin() {
		return this.admin;
	}
	public ArrayList<Car> getCars(ArrayList<Car> bought){
		ArrayList<Car> arr = new ArrayList<Car>();
		for (Car car : bought) {
			if (car.getOwner().equals(this)) {
				arr.add(car);
			}
		}
		return arr;
	}
	
	@Override
	public String toString() {
		return username;
	}

}
