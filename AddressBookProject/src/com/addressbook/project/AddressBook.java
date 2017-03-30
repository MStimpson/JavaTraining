/*
 * Update load book and update, list, del
 * 
 * */

package com.addressbook.project;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;


/**
 * Address Book - class project example
 * @author Jesse, ECSS
 *
 */
public class AddressBook {

	private static Data data;
	private String filename;
	private static Scanner console;

	/**
	 * Initializes the AddressBook instance.
	 * 
	 */
	private AddressBook() {	
		System.out.println("AddressBook running ..");
	}
	
	/**
	 * Adds new record.
	 * 
	 * @param record String "last, first; street; city; state; zip"
	 */
	private void addRecord(String record) {
		String[] rec = record.split(";");
		if (rec.length == 6) {
			// New Address record ..  (id, lastname, firstname,  street, city,   state,  zip)
			Address addr = new Address(0,rec[0], rec[1], rec[2], rec[3], rec[4], rec[5]);
			data.addRecord(addr);
			System.out.println("New record added:\n" + addr.toString());
		} else {
			System.out.println("Record not added (expected: lastname; firstname; street; city; state; zip)");
		}
	}
	
	/**
	 * Deletes requested record.
	 * 
	 * @param name String
	 */
	private void deleteRecord(int id) {
		Address addr = data.getRecord(id);
		if (addr == null) {
			System.out.println("Record not found: " + id);
		} else {
			data.deleteRecord(id);
			System.out.println("Record removed: " + id  + ": " + addr.toString());
		}
	}

	/**
	 * Lists records from the address book.
	 * 
	 */
	private void listRecords() {
		
		String query = "SELECT * FROM address_book.address ORDER BY lastname, firstname";
		Map<Integer, Address> records = data.getRecords(query);
		for(Integer key:records.keySet()){
			System.out.println(records.get(key).toString());
		}
	}
	
	/**
	 * Gets input from the user.
	 * 
	 * @param prompt String
	 * @return String
	 */
	public static String userInput(String prompt) {
	    System.out.print(prompt + " >");
//	    String record = System.console().readLine();
	    String input = console.nextLine();
	    return input;
	}
	
	private static int toInt(String num){
		int n=0;
		try{
			n = Integer.parseInt(num);
		}catch(Exception ex){System.out.println("Please enter a valid ID.");}
		return n;
	}
	
	/**
	 * Main
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		console = new Scanner(System.in);
		AddressBook ab = new AddressBook();
		
		data = new Data();
		
		boolean active = true;
		while (active) {
		    String command = userInput("By your command (add, delete, list, quit)");

		    switch (command.trim().toUpperCase()) {
		    case "ADD":
			    String record = userInput("Enter information (last; first; street; city; state; zip)");
			    ab.addRecord(record);
		    	break;
		    case "DELETE":
			    int id = toInt(userInput("Enter the person's id"));
			    ab.deleteRecord(id);
		    	break;
		    case "LIST":
		    	ab.listRecords();
		    	break;
		    case "QUIT":
				System.out.println("AddressBook ending.");
				active = false;
				console.close();
		    	break;
	    	default:
		    	System.out.println("Unknown command. " + command);
		    	break;
		    }
		}
	}
}
