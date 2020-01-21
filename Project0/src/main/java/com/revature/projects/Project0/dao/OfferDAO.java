package com.revature.projects.Project0.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.revature.util.ConnectionFactory;

public class OfferDAO {
	
	public Map<String, Integer> getCarOffers(int carID) throws SQLException {
		Map<String, Integer> offers = new HashMap<String, Integer>();
		Connection conn = ConnectionFactory.getConnection();
		String selectString = "select username, offer_amount from project0.offers where car_id = ?";
		PreparedStatement selectOffer = conn.prepareStatement(selectString);
		selectOffer.setInt(1, carID);
		ResultSet rs = selectOffer.executeQuery();
		while (rs.next()) {
			String username = rs.getString(1);
			int value = rs.getInt(2);
			offers.put(username, value);
		}
		return offers;
	}
	
	public void insertOffer(int carID, String username, int offerValue) {
		Connection conn = ConnectionFactory.getConnection();		
		String insertString = "insert into project0.offers (username, car_id, offer_amount) values (?, ?, ?)";
		String updateString = "update project0.offers set offer_amount = ? where username = ?";
		PreparedStatement insertOffer = null;
		PreparedStatement updateOffer = null;
		try {
			insertOffer = conn.prepareStatement(insertString);
			updateOffer = conn.prepareStatement(updateString);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		try {
			insertOffer.setString(1, username);
			insertOffer.setInt(2, carID);
			insertOffer.setInt(3, offerValue);
			updateOffer.setInt(1, offerValue);
			updateOffer.setString(2, username);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			insertOffer.executeUpdate();
		}
		catch (SQLException e){
			try {
				updateOffer.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void rejectOffer(int carID, String username) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		String deleteString = "delete from project0.offers where car_id = ? and username = ?";
		PreparedStatement deleteOffer = conn.prepareStatement(deleteString);
		deleteOffer.setInt(1, carID);
		deleteOffer.setString(2, username);
		deleteOffer.executeUpdate();
	}
	
	public void acceptOffer(int carID, String username) throws SQLException {
		
		ResultSet rs = null;
		int offer = 0;
		Connection conn = ConnectionFactory.getConnection();
		String offerString = "select offer_amount from project0.offers where car_id = ? and username = ?";
		PreparedStatement offerAmount = conn.prepareStatement(offerString);
		offerAmount.setInt(1, carID);
		offerAmount.setString(2,  username);
		rs = offerAmount.executeQuery();
		System.out.println(offerAmount.toString());
		while (rs.next()) {
			offer = rs.getInt(1);
		}
		System.out.println(offer);
		
		String ownerString = "update project0.cars set car_owner = ?, bought_price = ? where car_id = ?";
		PreparedStatement ownerSet = conn.prepareStatement(ownerString);
		ownerSet.setString(1, username);
		ownerSet.setInt(2, offer);
		ownerSet.setInt(3, carID);
		ownerSet.executeUpdate();
		
		String deleteString = "delete from project0.offers where car_id = ?";
		PreparedStatement deleteOffers = conn.prepareStatement(deleteString);
		deleteOffers.setInt(1, carID);
		deleteOffers.executeUpdate();
	}
}
