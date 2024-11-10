<%@page import="com.dto.User"%>
<%@page import="com.dto.Branch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>create menu</title>
</head>
<body>

<%
//get Branch
User branchManager=(User)session.getAttribute("branchmanager");

%>
<a href="crerateMenu?id=<%=branchManager.getId()%>"><button>CREATE MENU</button></a><br><br>




<a href="logout"><button>LOGOUT</button> </a>

</body>
</html>