package com.banking.project;


/**
 * Banking User Record.
 * 
 * @author Jesse, ECSS, Mar2017
 *
 */
public class User {

	private int id = 0;
	private String fname  = "";
	private String lname  = "";
	private String street = "";
	private String city   = "";
	private String state  = "";
	private String zip    = "";

//	private Set<Account> accounts;
	
	public User() {
	}

	/**
	 * Create new user record.
	 * 
	 * @param username
	 * @param lastname
	 * @param firstname
	 * @param admin
	 */
	public User(String fname, String lname, String street, String city, String state, String zip) {
		this(0, fname, lname, street, city, state, zip);
	}

	/**
	 * Create new user record.
	 *
	 * @param id
	 * @param username
	 * @param password
	 * @param lastname
	 * @param firstname
	 * @param email
	 * @param phone
	 * @param admin
	 */
	public User(int id, String fname, String lname, String street, String city, String state, String zip) {
		this.id     = id;
		this.fname  =  fname;
		this.lname  =  lname;
		this.street = street;
		this.city   =   city;
		this.state  =  state;
		this.zip    =    zip;

	}

	public boolean isLoggedIn() {
		return id > 0 && fname != null && fname.length() > 0;
	}
	
	public String toString() {
		return fname+", "+lname+"<br/>"+street+"<br/>"+city +", "+state+", "+zip;
	}

	public int getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}
}
