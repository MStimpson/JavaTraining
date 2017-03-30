package com.addressbook.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;


public class Data {
	
	private Connection conn;
	private Statement stmt;
	
	public Data(){
		try{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?useSSL=false", "root", "password");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public Map<Integer, Address> getRecords(String query){
		
		Map<Integer, Address> address = new TreeMap<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String lastName  =  rs.getString("lastname");
				String firstName = rs.getString("firstname");
				String street    =    rs.getString("street");
				String city      =      rs.getString("city");
				String state     = 	   rs.getString("state");
				String zip       =       rs.getString("zip");
				Address addr = new Address(id, lastName, firstName, street, city, state, zip);
				address.put(id, addr);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("That's not a valid account number.");
		}
		return address;
	}
	
	public Address getRecord(int id){
		Map<Integer, Address> records = getRecords("SELECT * FROM address WHERE id = "+id);
		return records.get(id);
	}
	public boolean addRecord(Address addr){
		int i=0;
		try{
			PreparedStatement preparedstmt = conn.prepareStatement("INSERT INTO address VALUES(0,?,?,?,?,?,?)");
			preparedstmt.setString(1, addr.getLastName());
			preparedstmt.setString(2, addr.getFirstName());
			preparedstmt.setString(3, addr.getStreet());
			preparedstmt.setString(4, addr.getCity());
			preparedstmt.setString(5, addr.getState());
			preparedstmt.setString(6, addr.getZip());
			i = preparedstmt.executeUpdate();
		}catch(SQLException ex){ex.printStackTrace();}	
		return i>0;
	}
	public boolean deleteRecord(int id){
		int i=0;
		try{
		Statement stmt = conn.createStatement();
		i = stmt.executeUpdate("DELETE FROM address WHERE id="+id);
		}catch(SQLException ex){ex.printStackTrace();i=0;}
		return i>0;
	}
}
