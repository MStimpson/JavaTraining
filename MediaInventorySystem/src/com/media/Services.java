package com.media;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.media.Data;

public class Services {

	Data data = new Data();
	
	public void getParams(HttpServletRequest request, HttpServletResponse response){
		
		//moving everything here
		
		request.getParameter("test");
	}
	
	public void method(){
		
	}
	
	
	public String buildTable(boolean editTable, boolean searchTable, boolean sort, int id){
		//I will use the query to populate the text boxes with the information currently associated with the id.
		String query="SELECT * FROM media WHERE id="+id;
		Map<Integer, Media> med=data.getMedia(query);
		StringBuilder sb = new StringBuilder();
		if(editTable && id!=0){
			sb.append("<form action='index.jsp' method='POST'><table>");
			sb.append("<input type='hidden' name='id' value='"+id+"'>");
			sb.append("<tr><td>Scan Code</td><td><input type='text' name='scancode' value='"  + med.get(id).getScanCode()+"'></td></tr>");
			sb.append("<tr><td>Movie Title</td><td><input type='text' name='title' value='"   + med.get(id).getTitle()+"'></td></tr>");
			sb.append("<tr><td>Description</td><td><input type='text' name='desc' value='"    + med.get(id).getDesc()+"'></td></tr>");
			
			sb.append("<tr><td>Genre</td><td><input type='text' name='genre' value='"         + med.get(id).getGenre()+"'></td></tr>");
			sb.append("<tr><td>Minutes</td><td><input type='text' name='mins' value='"		  + med.get(id).getMinutes()+"'></td></tr>");
			sb.append("<tr><td>Year Released</td><td><input type='text' name='yearr' value='" + med.get(id).getYearReleased()+"'></td></tr>");
			sb.append("<tr><td>Location</td><td><input type='text' name='location' value='"   + med.get(id).getLocation()+"'></td></tr>");
			sb.append("<tr><td>Purchased Price</td><td><input type='text' name='price' value='"+ med.get(id).getPurchasedPrice()+"'></td></tr>");
			sb.append("<tr><td>Purchased Date</td><td><input type='text' name='date' value='"+ med.get(id).getPurchasedDate()+"'></td></tr>");
			sb.append("<tr><td>Last Watched</td><td><input type='text' name='watched' value='"+ med.get(id).getLastWatched()+"'></td><td><input type='submit' value='EditForm' name='action'></td></tr>");
			sb.append("</table></form>");

			return sb.toString();
		}
		else if(searchTable){
			//prevents it from posting the initial view.
		return "";
		}
		else if(sort){
			//prevents it from posting the initial view.
		return "";
		}else{return data.getGenre();}
			
	}
	//asdf

	public void export(){
		StringBuilder sb = new StringBuilder();
		Map<Integer, Media> med =data.getMedia();
		String file="C:/Users/mstimpson/Desktop/export.csv";
		
		for(Integer key:med.keySet()){
			sb.append(med.get(key).exportString());
		}
		String s = sb.toString();
			
		try {		
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(s);

			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();		
			
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
	}
	public void importCsv() {
		
		
	}
}
