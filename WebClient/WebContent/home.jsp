<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	  <%@page 
	  import="java.util.Random"
	  import="ecss.Services"
	  %>
	<% 
	//Services s = new Services();
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%Random rn = new Random();

	System.out.println(rn.nextInt());
%>
<h1>	  <%--= s.getString()    --%> </h1>
Today is: <%--= s.getCurrentDate() --%>
</body>
</html>