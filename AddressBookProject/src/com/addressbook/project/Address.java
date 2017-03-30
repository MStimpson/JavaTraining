package com.addressbook.project;

public class Address {
	private int id;
	private String lastName;
	private String firstName;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	public Address(int id, String lastName, String firstName, String street, String city, String state, String zip) {
		this.lastName  =  lastName.trim();
		this.firstName = firstName.trim();
		this.street    =    street.trim();
		this.city      =      city.trim();
		this.state     =     state.trim();
		this.zip       =       zip.trim();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);		  sb.append("\n");
		sb.append(lastName);  sb.append(", ");
		sb.append(firstName); sb.append("\n");
		sb.append(street);    sb.append("\n");
		sb.append(city);      sb.append(", ");
		sb.append(state);     sb.append(", ");
		sb.append(zip); 	  sb.append("\n");
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
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