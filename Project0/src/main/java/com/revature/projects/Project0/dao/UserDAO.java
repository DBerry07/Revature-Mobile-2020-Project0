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

import com.revature.util.ConnectionFactory;

public class UserDAO {

	/*
	 * private static final String filename = "users.dat";
	 * 
	 * public void write(Object obj) { try (FileOutputStream fos = new
	 * FileOutputStream(filename); ObjectOutputStream oos = new
	 * ObjectOutputStream(fos)){ oos.writeObject(obj); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * public Object read() { Object obj = null; try (FileInputStream fis = new
	 * FileInputStream(filename); ObjectInputStream ois = new
	 * ObjectInputStream(fis)) { obj = ois.readObject(); } catch (Exception e) {
	 * 
	 * } return obj; }
	 */

	public void insertUser(String username, String password, boolean admin) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		String insertString = "insert into project0.users(username, user_password, admin_status) values (?, ?, ?)";
		PreparedStatement insertUser = null;
		insertUser = conn.prepareStatement(insertString);
		insertUser.setString(1, username);
		insertUser.setString(2, password);
		if (admin == false) {
			insertUser.setInt(3, 0);
		} else if (admin == true) {
			insertUser.setInt(3, 1);
		}
		insertUser.executeUpdate();
		conn.close();

	}

	public boolean checkUser(String username, String password) {
		Connection conn = ConnectionFactory.getConnection();
		String selectString = "select * from project0.users where username = ? and user_password = ?";
		PreparedStatement selectUser = null;
		try {
			selectUser = conn.prepareStatement(selectString);
			selectUser.setString(1, username);
			selectUser.setString(2, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = selectUser.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkAdmin(String username, String password) {
		Connection conn = ConnectionFactory.getConnection();
		String selectString = "select * from project0.users where username = ? and user_password = ? and admin_status = 1";
		PreparedStatement selectUser = null;
		try {
			selectUser = conn.prepareStatement(selectString);
			selectUser.setString(1, username);
			selectUser.setString(2, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = selectUser.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
