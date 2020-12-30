<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Done</title>
</head>
<body>
<%
out.println("Dear "+ session.getAttribute("name")+" ,");
out.println("Thank you for your interest. We will soon get back to you on your email id :- "+ session.getAttribute("email"));
%>
</body>
</html>