package com.media;

public class Media {

	private String title;
	private String desc;
	private String genre;
	private int minutes;
	private String location;
	
	public Media( String title, String desc, String genre, int minutes, String location){
		this.title 		= title;
		this.desc 		= desc;
		this.genre		= genre;
		this.minutes 	= minutes;
		this.location 	= location;	
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

	public String getLocation() {
		return location;
	}
	
}
