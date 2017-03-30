<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.banking.project.Data"
    		 import="com.banking.project.Services"
    		 import="java.util.Map"
     %><%
     	Services s = new Services();
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banking Project</title>
<link rel="stylesheet" type="text/css" href="css.css">
</head>
<body>
<p>By your command...</p>
<form method="POST" action="logic.jsp">
  	Account Number:<br/>
  	<input type="text" name="accountnumber" ><br/><br/>
 	<input type="radio" name="command" value="deposit" checked> Deposit<br/>
 	<input type="text" name="depositAmount" ><br>
  	<input type="radio" name="command" value="withdrawal"> Withdrawal<br/>
  	<input type="text" name="withdrawalAmount" ><br>
 	<input type="radio" name="command" value="transfer"> Transfer<br/>
 	Please enter a value into withdraw and deposit for transfers.<br/>
 	<input type="text" name="toAccountNumber" >--Account you'd like the money  transfered too.<br/>
  	<input type="radio" name="command" value="view"> View<br>
  	<input type='submit' value='submit'>
</form>


</body>
</html>