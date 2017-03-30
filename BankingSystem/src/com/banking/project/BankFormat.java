package com.banking.project;

import java.text.DecimalFormat;

public class BankFormat {
	private int id;
	private int accountnumber;
	private double amount;
	private String lastName;
	private String firstName;
	private String date;
	private String transType;
	private int toAccountNumber;	
	private int fromAccountNumber;
	private double totalBalance;
	
	public BankFormat(int id, int accountnumber, double amount, String datetime, String transType, int toAccountNumber, int fromAccountNumber,double totalBalance) {
		this.id              = id;
		this.accountnumber   = accountnumber;
		this.amount          = amount;
		this.date            = datetime.trim();
		this.transType       = transType.trim();
		this.toAccountNumber = toAccountNumber;
		this.fromAccountNumber = fromAccountNumber;
		this.totalBalance    = totalBalance;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("0.00##");
		sb.append("| ");
		 

		 System.out.printf("| %-12s| %14.2f | %13.2f |  %-12s|  %-13s|%-12s |",
			transType, amount, totalBalance, toAccountNumber, fromAccountNumber, date);
		 /*	sb.append(transType);	 
			if(transType.equals("deposit")){
				for(int i=12;i>String.valueOf(amount).length();i--){
				 sb.append(" ");
			 	}
			 	sb.append(df.format(amount));
					
				for(int i=15;i>String.valueOf(totalBalance).length();i--){
					sb.append(" ");
				}
				sb.append(df.format(totalBalance));
			}		 
			if(transType.equals("withdrawl")){
				for(int i=10;i>String.valueOf(amount).length();i--){
				 sb.append(" ");
				}
				sb.append(df.format(amount));	
				for(int i=15;i>String.valueOf(totalBalance).length();i--){
					sb.append(" ");
				}
				sb.append(df.format(totalBalance));				 
			}
			if(toAccountNumber==0){	
				for(int i=18;i>9;i--){
					sb.append(" ");
				}
			}else{
				for(int i=17;i>String.valueOf(toAccountNumber).length();i--){
					sb.append(" ");
				}
				sb.append(toAccountNumber);
			}
			if(toAccountNumber==0){	
				for(int i=24;i>9;i--){
					sb.append(" ");
				}	
			}else{
				for(int i=16;i>9;i--){
					sb.append(" ");
				}
			}
			sb.append(date);
			sb.append(" |");		
			
			return sb.toString();*/
			 return " ";
	}

	public int getId() {
		return id;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public double getAmount() {
		return amount;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getDate() {
		return date;
	}

	public String getTransType() {
		return transType;
	}

	public int getToAccountNumber() {
		return toAccountNumber;
	}
	public double getBalance(){
		return totalBalance;
	}
}
