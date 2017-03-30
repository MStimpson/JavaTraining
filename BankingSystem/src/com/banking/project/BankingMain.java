/*
 * Update load book and update, list, del
 * 
 * */

package com.banking.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Scanner;

public class BankingMain {

	private static Data data;
	private static Scanner console;
	private static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	private static Calendar calobj = Calendar.getInstance();

	private BankingMain() {	
		System.out.println("Banking system running ..");
	}
	
	private void deposit(int accnum){
		
		double dep=0;	
		if(data.validAcc(accnum)){
			while(true){
				try{
					dep = Double.parseDouble(userInput("How much would you like to deposit?"));
					break;
				} catch(NumberFormatException ignore){
					System.out.println("Invalid input.");
				}
			}				
			String query="INSERT INTO banking.transaction(`accountnumber`, `transtype`, `toAccountNumber`, `fromAccountNumber`,`amount`, `datetime`) "
					+ "VALUES("+accnum+", 'deposit',"+accnum+","+ accnum+", "+dep+",'"+df.format(calobj.getTime()) +"');";		
			
			data.depositMoney(query);
			System.out.println(dep +" was deposited into your account.");
		}else{System.out.println("That's not a valid account.");}
	}
	
	private void deposit(int secaccnum, int accnum, double trans){
		if(data.validAcc(secaccnum)){
			if(data.validAcc(accnum)){
				String query = "INSERT INTO banking.transaction(`accountnumber`, `transtype`, `toAccountNumber`, `fromAccountNumber`,`amount`, `datetime`) "
						+ "VALUES("+secaccnum +", 'deposit',"+ secaccnum +", "+ accnum+", "+trans+",'"+df.format(calobj.getTime())+"' );";
				data.depositMoney(query);
				System.out.println("The money was transfered successfully.");
			}else{System.out.println("The second account number is not valid.");}
		}else{System.out.println("The first account number is not valid.");}
	}
	
	private static void withdrawl(int accnum){
		
		double withd = 0;
		
		if(data.validAcc(accnum)){
			while(true){
				try{
					withd = Double.parseDouble(userInput("How much would you like to withdrawl?"));
					break;
				} catch(NumberFormatException ignore){
					System.out.println("Invalid input.");
				}
			}		
			String testQuery ="SELECT transtype, amount FROM transaction WHERE accountnumber = "+accnum;		
			String query = "INSERT INTO banking.transaction(`accountnumber`, `transtype`, `toAccountNumber`, `fromAccountNumber`, `amount`, `datetime`) "
					+ "VALUES("+accnum +", 'withdrawl', 0,0,"+withd+",'"+df.format(calobj.getTime())+"' );";
			if(data.withdrawlMoney(testQuery, query, withd)){		
			System.out.println("The withdrawl was successful!");
			}else{System.out.println("The withdrawl was not successful");}			
		}else{System.out.println("That's not a valid account.");}
		
	}

	private static double withdrawl(int accnum, int secaccnum){
		
		double withd = 0;
		
		if(data.validAcc(accnum)){
			while(true){
				try{
					withd = Double.parseDouble(userInput("How much would you like to withdrawl?"));
					break;
				} catch(NumberFormatException ignore){
					System.out.println("Invalid input.");
				}
			}		
			String testQuery ="SELECT transtype, amount FROM transaction WHERE accountnumber = "+accnum;		
			String query = "INSERT INTO banking.transaction(`accountnumber`, `transtype`, `toAccountNumber`, `fromAccountNumber`, `amount`, `datetime`) "
					+ "VALUES("+accnum +", 'withdrawl', "+secaccnum+","+accnum+","+withd+",'"+df.format(calobj.getTime())+"' );";
			data.withdrawlMoney(testQuery, query, withd);		
			System.out.println("The withdrawl was successful!");
			return withd;				
		}else{System.out.println("That's not a valid account.");}
		return withd;
	}
	
	private void transfer(int accnum){
		int secAccNum = toInt(userInput("Enter the account number you'd like to tranfer money too."));
		double trans = withdrawl(accnum, secAccNum);
		if(data.validAcc(accnum)){
			if(data.validAcc(secAccNum)){
				deposit(secAccNum, accnum, trans);
			}else{System.out.println("The second account number is invalid.");}
		}else{System.out.println("The first account number is invalid.");}
		
		
	}
	
	private void listTran(int input) {	
		String accInfo ="SELECT firstname, lastname FROM account WHERE accountnumber = "+ input;
		String query = "SELECT transaction.id, transaction.accountnumber, amount, datetime, transtype, toAccountNumber, fromAccountNumber "
				+ "FROM transaction INNER JOIN account ON transaction.accountnumber = account.accountnumber AND transaction.accountnumber="+input +";";		
		Map<Integer, BankFormat> records = data.getRecords(query);
		if(records.isEmpty()){
			System.out.println("That's not a valid account number.");
		}else{
		System.out.println("Hello, "+data.accOwner(accInfo)+ ".");
		//System.out.println("   Type    |   Amount   |   Balance    |     ToAccount    |      Date   ");
		System.out.println("    Type      |     Amount     |    Balance    |  ToAccount   |  FromAccount  |     Date   ");
		System.out.println(" ----------------------------------------------------------------------------------------------------");
		
		for(Integer key:records.keySet()){		
			System.out.println(records.get(key).toString());
		}}
		System.out.println(" ----------------------------------------------------------------------------------------------------");
		System.out.println();
	}
	
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
	
	public static void main(String[] args) {
		console = new Scanner(System.in);
		BankingMain ab = new BankingMain();
		
		data = new Data();
		
		boolean active = true;
		while (active) {
		    String command = userInput("By your command (deposit, withdrawl, transfer, view, quit)");

		    switch (command.trim().toUpperCase()) {
		    case "DEPOSIT":
			    int deposit = toInt(userInput("Enter the account number:"));
			    ab.deposit(deposit);
		    	break;
		    case "WITHDRAWL":
			    int withdrawl = toInt(userInput("Enter the account number:"));
			    withdrawl(withdrawl);
		    	break;
		    case "TRANSFER":
			    int transfer = toInt(userInput("Enter your account number:"));
			   ab.transfer(transfer);
		    	break;
		    case "VIEW":
		    	int input = toInt(userInput("Enter the account number"));
		    	ab.listTran(input);
		    	break;
		    case "QUIT":
				System.out.println("Banking system ending.");
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
