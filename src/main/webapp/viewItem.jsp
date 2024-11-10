<%@page import="com.dto.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.dto.FoodOrder"%>
<%@page import="com.dao.FoodOrderDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
int foID=Integer.parseInt(request.getParameter("foid"));
List<Item> list=new FoodOrderDao().foodOrderById(foID).getItems();
%>

<table border="1px solid black" cellpadding="2px" cellspacing="3px" width="100%">

<tr>
<th>SN</th>
<th>Item_Id</th>
<th>Item_name</th>
<th>Item_type</th>
<th>Item_price</th>
<th>Item_Quantity</th>

</tr>

<%
int i1=1;
for(Item i:list){ %>
<tr>
<td><%=i1++ %></td>
<td><%=i.getId() %></td>
<td><%=i.getProductName() %></td>
<td><%=i.getType() %></td>
<td><%=i.getPrice() %></td>
<td><%=i.getQuantity() %></td>
<tr>
<%} %>
</table>

<a href="staff.jsp"> back </a>
<a href="stafflogin.jsp"> HOME </a>

</body>
</html>