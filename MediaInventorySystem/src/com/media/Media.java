package com.media;

public class Media {
	public enum MediaType{Video_DVD,Video_BLURAY,Audio_CD,Video_DIGITAL, Audio_DIGITAL};
	private int id;
	private int scanCode;
	private String title;
	private String desc;
	MediaType mediatype;
	private String genre;
	private int minutes;
	private String yearReleased;
	private String location;
	private double purchasedPrice;
	private String purchasedDate;
	private String lastWatched;
	
	public Media(int id, int scanCode, String title, String desc,  MediaType mediatype ,String genre, int minutes, String yearReleased, String location, double purchasedPrice, String purchasedDate, String lastWatched){
		this.id             = id;
		this.scanCode       = scanCode;
		this.title          = title;
		this.desc           = desc;
		this.mediatype		= mediatype;
		this.genre          = genre;
		this.minutes        = minutes;
		this.yearReleased   = yearReleased;
		this.location       = location;
		this.purchasedPrice = purchasedPrice;
		this.purchasedDate  = purchasedDate;
		this.lastWatched    = lastWatched;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<tr><td>");	   		  sb.append(title);         sb.append("</td>");
		sb.append("<td class='desc'>");   sb.append(desc);	        sb.append("</td>");
		sb.append("<td align='center'>"); sb.append(mediatype);     sb.append("</td>");
		sb.append("<td>"); 	   			  sb.append(genre);         sb.append("</td>");
		sb.append("<td align='center'>"); sb.append(minutes);       sb.append("</td>");
		sb.append("<td align='center'>"); sb.append(yearReleased);  sb.append("</td>");
		sb.append("<td>");	              sb.append(location);      sb.append("</td>");
		sb.append("<td align='center'>"); sb.append(purchasedPrice);sb.append("</td>");
		sb.append("<td align='center'>"); sb.append(purchasedDate); sb.append("</td>");
		sb.append("<td align='center'>"); sb.append(lastWatched);   sb.append("</td>");
		sb.append("<form action='index.jsp' method='POST'>");
		sb.append("<td>"); sb.append("<input type='submit' value='Edit' name='action'>");    sb.append("</td>");
		sb.append("<td>"); sb.append("<input type='submit' value='Remove' name='action'>");  sb.append("</td>");
		sb.append("<td>"); sb.append("<input type='submit' value='Watched' name='action'>"); sb.append("</td></tr>");
		sb.append("<input type='hidden' name='id' value='"+id+"'>");
		sb.append("</form>");
		return sb.toString();
	}
	
	public String exportString(){
		StringBuilder sb = new StringBuilder();
	
		sb.append(title);    		sb.append(", ");
		sb.append(desc);	  		sb.append(", ");
		sb.append(mediatype);		sb.append(", ");
		sb.append(genre);	  		sb.append(", ");
		sb.append(minutes); 	 	sb.append(", ");
		sb.append(yearReleased);	sb.append(", ");
		sb.append(location);		sb.append(", ");
		sb.append(purchasedPrice);  sb.append(", ");
		sb.append(purchasedDate);   sb.append(", ");
		sb.append(lastWatched);  	sb.append(" \n");
		
		return sb.toString();
	}
	
	
	public int getId() {
		return id;
	}
	public int getScanCode() {
		return scanCode;
	}
	public String getTitle() {
		return title;
	}
	public String getDesc() {
		return desc;
	}
	public String getGenre() {
		return genre;
	}
	public int getMinutes() {
		return minutes;
	}
	public String getYearReleased() {
		return yearReleased;
	}
	public String getLocation() {
		return location;
	}
	public double getPurchasedPrice() {
		return purchasedPrice;
	}
	public String getPurchasedDate() {
		return purchasedDate;
	}
	public String getLastWatched() {
		return lastWatched;
	}
}
