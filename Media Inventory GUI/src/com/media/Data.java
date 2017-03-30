package com.media;

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
	
	public Data(){
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useSSL=false","root","password");
		}catch(SQLException ex){ex.printStackTrace();}
	}
	public Map<Integer, Media> getMedia(){
		String query ="SELECT * FROM media;";
		Map<Integer, Media> media = new TreeMap<>();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String title 	= rs.getString("title");
				String desc  	= rs.getString("description");
				String genre 	= rs.getString("genre");
				int minutes  	= rs.getInt("minutes");
				String location = rs.getString("location");
				
				Media med = new Media(title, desc, genre, minutes, location);
				media.put(id, med);
			}stmt.close();
		}catch(SQLException ex){ex.printStackTrace();}
		return media;	
	}
	public Map<Integer, Media> searchMedia(String search){
		PreparedStatement searchMed = null;
		Map<Integer, Media> media = new TreeMap<>();
 		String query ="SELECT * FROM media WHERE title LIKE '%"+search+"%' OR description LIKE '%"+search+"%' OR genre LIKE '%"+search+"%' OR location LIKE '%"+search+"%';";
 		
 		try{
 			searchMed =conn.prepareStatement(query);
 			ResultSet rs = searchMed.executeQuery();
 			while(rs.next()){
				int id = rs.getInt("id");
				String title 	= rs.getString("title");
				String desc  	= rs.getString("description");
				String genre 	= rs.getString("genre");
				int minutes  	= rs.getInt("minutes");
				String location = rs.getString("location");
				
				Media med = new Media(title, desc, genre, minutes, location);
				media.put(id, med);
 			}
 			
 			searchMed.close();
 		}catch(SQLException ex){ex.printStackTrace();}

		return media;
	}
}
