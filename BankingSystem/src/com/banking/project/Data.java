package com.banking.project;

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
	public static double totalBalance=0;
	
	public Data(){
		try{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking?useSSL=false", "root", "password");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public Map<Integer, BankFormat> getRecords(String query){
		
		Map<Integer, BankFormat> address = new TreeMap<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int id              =              rs.getInt("id");
				int accountnumber   =   rs.getInt("accountnumber");
				double amount       =       rs.getDouble("amount");	
				String date         =     rs.getString("datetime");
				String transType    = 	 rs.getString("transType");
				int toAccountNumber = rs.getInt("toAccountNumber");
				int fromAccountNumber = rs.getInt("fromAccountNumber");
				calcBalance(transType, amount);
				
				BankFormat addr = new BankFormat(id, accountnumber, amount, date, transType, toAccountNumber,fromAccountNumber, totalBalance);
				address.put(id, addr);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
	
	public void depositMoney(String query){	
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("There was an issue and money was not deposited.");
			}
	}
	
	public boolean withdrawlMoney(String testQuery, String query, double withd){
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(testQuery);
			while(rs.next()){
				String transType = rs.getString("transType");
				double amount = rs.getDouble("amount");
				calcBalance(transType, amount);		
			}	
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(totalBalance>withd){
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("There was an issue and money was not withdrawn.");
			}
		}else{System.out.println("Not enough funds.");return false;}
		return true;
	}

	public String accOwner(String query){
		
		String fname,lname,fullname ="";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				fname = rs.getString("firstname");
			 	lname = rs.getString("lastname");
			 	fullname=fname+" "+lname;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fullname;
		
	}

	
	public boolean validAcc(int accnum){
		try {
			String query = "SELECT * FROM account WHERE accountnumber="+accnum;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(!rs.next()){
				return false;
			}	
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	private static double calcBalance(String transType, double amount){
		
		if(transType.equals("deposit")){
			totalBalance = totalBalance+amount;
		}else if(transType.equals("withdrawl")){
			totalBalance = totalBalance-amount;
		}	
		return totalBalance;	
	}
}
