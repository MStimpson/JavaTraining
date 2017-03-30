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

	
	public String deposit(int accnum, double depositAmount){
		Data data = new Data();	
		StringBuilder sb=new StringBuilder();
		String query="INSERT INTO banking.transaction(`accountnumber`, `transtype`, `toAccountNumber`, `fromAccountNumber`,`amount`, `datetime`) "
			+ "VALUES("+accnum+", 'deposit',"+accnum+","+ accnum+", "+depositAmount+",'"+df.format(calobj.getTime()) +"');";		
			
		data.depositMoney(query);
		System.out.println(depositAmount +" was deposited into your account.");
		sb.append(depositAmount+" was deposited into the account.");
		return sb.toString();
	}
	
	public void deposit(int secaccnum, int accnum, double trans){
		Data data = new Data();
		
			String query = "INSERT INTO banking.transaction(`accountnumber`, `transtype`, `toAccountNumber`, `fromAccountNumber`,`amount`, `datetime`) "
				+ "VALUES("+secaccnum +", 'deposit',"+ secaccnum +", "+ accnum+", "+trans+",'"+df.format(calobj.getTime())+"' );";
			data.depositMoney(query);
	}
	
	public String withdrawl(int accnum, double withAmount){
		Data data = new Data();
		StringBuilder sb = new StringBuilder();
		
		
		
			String testQuery ="SELECT transtype, amount FROM transaction WHERE accountnumber = "+accnum;		
			String query = "INSERT INTO banking.transaction(`accountnumber`, `transtype`, `toAccountNumber`, `fromAccountNumber`, `amount`, `datetime`) "
					+ "VALUES("+accnum +", 'withdrawl', 0,0,"+withAmount+",'"+df.format(calobj.getTime())+"' );";
			if(data.withdrawlMoney(testQuery, query, withAmount)){		
			sb.append("The withdrawal was successful.");
			}else{sb.append("The withdrawal was not successful.");}			
		return sb.toString();
	}

	public double withdrawl(int accnum, int secaccnum){
		Data data = new Data();
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
	
	public String transfer(int accnum, int secaccnum, double withAmount){
		Data data = new Data();
		StringBuilder sb=new StringBuilder();
		 		
			withdrawl(accnum, withAmount);
			deposit(secaccnum, accnum, withAmount);
		
			sb.append("The money was transfered.");
			return sb.toString();
	}
	
	public String listTran(int input) {	
		StringBuilder sb = new StringBuilder();
		Data test = new Data();
		String accInfo ="SELECT firstname, lastname FROM account WHERE accountnumber = "+ input;
		String query = "SELECT transaction.id, transaction.accountnumber, amount, datetime, transtype, toAccountNumber, fromAccountNumber "
				+ "FROM transaction INNER JOIN account ON transaction.accountnumber = account.accountnumber AND transaction.accountnumber="+input +";";		
		
		Map<Integer, BankFormat> records = test.getRecords(query);
		if(records.isEmpty()){
			System.out.println("That's not a valid account number.");
		}else{
			sb.append("Hello, "+test.accOwner(accInfo)+ ".<br/>");
			sb.append("<p>    Type      |     Amount     |    Balance    |  ToAccount   |  FromAccount  |     Date   </p>");
			sb.append(" ----------------------------------------------------------------------------------------------------<br/>");
		
		for(Integer key:records.keySet()){		
			sb.append("<p>"+records.get(key).toString()+"<p>");
		}}
		sb.append(" ----------------------------------------------------------------------------------------------------<br/>");		
		return sb.toString();
	}
	
	public static String userInput(String prompt) {
	    System.out.print(prompt + " >");
//	    String record = System.console().readLine();
	    String input = console.nextLine();
	    return input;
	}

	public static int toInt(String num){
		int n=0;
		try{
			n = Integer.parseInt(num);
		}catch(Exception ex){System.out.println("Please enter a valid ID.");}
		return n;
	}
	
	
		
	
}
