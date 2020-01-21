package com.revature.projects.Project0.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.projects.Project0.pojo.Car;
import com.revature.util.ConnectionFactory;

public class CarDAO{
	/*
	 * private static final String filename = "cars.dat";
	 * 
	 * 
	 * public void write(Object obj) { try (FileOutputStream fos = new
	 * FileOutputStream(filename); ObjectOutputStream oos = new
	 * ObjectOutputStream(fos)){ oos.writeObject(obj); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * public Object read() { Object obj = null; try (FileInputStream fis = new
	 * FileInputStream(filename); ObjectInputStream ois = new
	 * ObjectInputStream(fis)) { obj = ois.readObject(); } catch (Exception e) { }
	 * return obj; }
	 */
	
	public void insertCar(String make, String model, int year, String colour, int price) {
		Connection conn = ConnectionFactory.getConnection();
		String insertString = "insert into project0.cars(make, model, make_year, colour, price)"
				+ " values (?, ?, ?, ?, ?)";
		PreparedStatement insertCar = null;
		try {
			insertCar = conn.prepareStatement(insertString);
			insertCar.setString(1, make);
			insertCar.setString(2, model);
			insertCar.setInt(3, year);
			insertCar.setString(4, colour);
			insertCar.setInt(5, price);
			System.out.println(insertCar.toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			insertCar.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Car> readAll() throws SQLException{
		ArrayList<Car> cars = new ArrayList<Car>();
		Connection conn = ConnectionFactory.getConnection();
		String selectString = "select car_id, make, model, make_year, colour, price, car_owner, bought_price from project0.cars";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeQuery(selectString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (rs.next()) {
			cars.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), 
					rs.getInt(6), rs.getString(7), rs.getInt(8)));
		}
		stmt.close();
		return cars;
	}
	
	public void removeCar(Car car) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		String removeString = "delete from project0.cars where car_id = ?";
		PreparedStatement removeCar = null;
		removeCar = conn.prepareStatement(removeString);
		removeCar.setInt(1, car.getCarID());
		removeCar.executeUpdate();
	}
}
