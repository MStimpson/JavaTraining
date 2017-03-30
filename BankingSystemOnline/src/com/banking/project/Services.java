package com.banking.project;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.banking.project.*;


@SuppressWarnings("unused")
public class Services {
	
	
	private String command="";
	private String display="";
	private int accountnumber=0;
	private double withdrawalAmount=0;
	private double depositAmount=0;
	private int toAccountNumber=0;
	BankingMain bm = new BankingMain();
	
	public String getParams(HttpServletRequest request,HttpServletResponse response){
		try{
				command = request.getParameter("command");
				accountnumber   =BankingMain.toInt(request.getParameter("accountnumber"));
				
				System.out.println(command+" "+accountnumber);
				switch (command.trim().toUpperCase()) {
			    case "DEPOSIT":
			    	depositAmount   =Double.parseDouble(request.getParameter("depositAmount"));
				   display= bm.deposit(accountnumber,depositAmount);
			    	break;
			    case "WITHDRAWAL":			   
			    	withdrawalAmount=Double.parseDouble(request.getParameter("withdrawalAmount"));
			    	display=bm.withdrawl(accountnumber,withdrawalAmount);
			    	break;
			    case "TRANSFER":
			    	withdrawalAmount=Double.parseDouble(request.getParameter("withdrawalAmount"));
			    	toAccountNumber =BankingMain.toInt(request.getParameter("toAccountNumber"));
			    	display=bm.transfer(accountnumber, toAccountNumber, withdrawalAmount);
			    	break;
			    case "VIEW":		    	
			    	display=bm.listTran(accountnumber);
			    	break;
		    	default:
			    	System.out.println("Unknown command. " + command);
			    	break;
			    }
				response.flushBuffer();
		}catch(IOException e){e.printStackTrace();}	
		return display;
	}
}
