package com.media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import com.media.Media.MediaType;

public class Data {
	//private enum MediaType{Video_DVD,Video_BLURAY,Audio_CD,Video_DIGITAL, Audio_DIGITAL };
	private Connection conn;
	
	public Data(){
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useSSL=false","root","password");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public Map<Integer, Media> getMedia(){
		String query="SELECT * FROM media";

		Map<Integer, Media> media = new TreeMap<>();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int id =rs.getInt("id");
				int scanCode 		 = rs.getInt("scan_code");
				String title 		 = rs.getString("title");
				String desc  		 = rs.getString("description");
				com.media.Media.MediaType mediatype  = MediaType.valueOf(rs.getString("media_type"));
				String genre   		 = rs.getString("genre");
				int minutes          = rs.getInt("minutes");
				String yearReleased  = rs.getString("year_released");
				String location 	 = rs.getString("location");
				double purchasedPrice= rs.getDouble("purchased_price");
				String purchasedDate = rs.getString("purchased_date");
				String lastWatched   = rs.getString("last_watched");
				
				Media med = new Media(id,scanCode, title, desc, mediatype, genre, minutes, yearReleased, location, purchasedPrice, purchasedDate, lastWatched);
				media.put(id, med); 
			}
			stmt.close();
		}catch(SQLException ex){ex.printStackTrace();}
		return media;
		//return sb.toString();
	}
	public Map<Integer, Media> getMedia(String query){
		StringBuilder sb = new StringBuilder();
		Map<Integer, Media> media = new TreeMap<>();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int id =rs.getInt("id");
				int scanCode 		 = rs.getInt("scan_code");
				String title 		 = rs.getString("title");
				String desc  		 = rs.getString("description");
				com.media.Media.MediaType mediatype  = MediaType.valueOf(rs.getString("media_type"));
				String genre   		 = rs.getString("genre");
				int minutes          = rs.getInt("minutes");
				String yearReleased  = rs.getString("year_released");
				String location 	 = rs.getString("location");
				double purchasedPrice= rs.getDouble("purchased_price");
				String purchasedDate = rs.getString("purchased_date");
				String lastWatched   = rs.getString("last_watched");
				
				Media med = new Media(id,scanCode, title, desc, mediatype, genre, minutes, yearReleased, location, purchasedPrice, purchasedDate, lastWatched);
				media.put(id, med); 

			}
			stmt.close();
		}catch(SQLException ex){ex.printStackTrace();}
		return media;
	//	return sb.toString();
	}
	
	public String getGenre(){
		int total=0;
		String query="SELECT genre, count(genre) AS dup FROM media GROUP BY genre";
		StringBuilder sb = new StringBuilder();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				String genre = rs.getString("genre");
				String dup = rs.getString("dup");
				sb.append("<tr>");
				sb.append("<td>"+genre+"</td>");
				sb.append("<td class='secCol'>"+dup+"</td>");
				sb.append("</tr>");
				total++;
			}
			sb.append("<tr><td>Total</td><td class='secCol'>"+total+"</td></tr>");
		}catch(SQLException sx){sx.printStackTrace();}
		return sb.toString();
	}
	public String genreSort(){
		String query="SELECT genre FROM media GROUP BY genre";
		StringBuilder sb = new StringBuilder();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String genre = rs.getString("genre");
				sb.append("<option value='"+genre+"'>");
				sb.append(genre);
				sb.append("</option>");
			}
		}catch(SQLException ec){ec.printStackTrace();}
		
		return sb.toString();
		
	}
	
	public void addMedia(String query){
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		}catch(SQLException ex){ex.printStackTrace();}
	}
	public void editMedia(String query){
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		}catch(SQLException ex){ex.printStackTrace();}
	}
	
	public void watched(int id){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		
		String query="UPDATE `inventory`.`media` SET `last_watched`='"+dateFormat.format(cal.getTime())+"' WHERE `id`='"+id+"';";
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		}catch(SQLException ex){ex.printStackTrace();}
	}
	
	public void deleteMedia(int id){
		String query="DELETE FROM `inventory`.`media` WHERE `id`='"+id+"';";
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		}catch(SQLException ex){ex.printStackTrace();}
	}

}

