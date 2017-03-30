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
</head>
<body>
<%=s.getParams(request, response)%>

</body>
</html>