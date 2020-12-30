<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>The statement is given below:</h2>
<% out.println("The statement of account no: "+session.getAttribute("accno") + " is below :");%>
<br/>
<% out.println(session.getAttribute("al"));%>
</body>
</html>