<%@page import="com.dao.CustomerDao"%>
<%@page import="com.dto.Item"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.dto.Customer"%>
<%@page import="com.dto.Branch"%>
<%@page import="com.dto.User"%>
<%@page import="com.dto.FoodOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.FoodOrderDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	User staff = (User) session.getAttribute("staff");
	Branch branch = staff.getBranchs().get(0);
	List<Item> listOfItem=null;
	Customer c=null;

	List<FoodOrder> list = new FoodOrderDao().findByFoodOrderStatus();
	if (!list.isEmpty()) {
		
	%>
	<h3>branch id:<%=branch.getId()%></h3>
	<h3>branch name:<%=branch.getBranchname() %></h3>
	
	
	
	<table border="1px solid black" cellpadding="2px" cellspacing="3px"
		width="100%">
		<tr>
			<th>SN</th>
			<th>BRANCH ID</th>
			<th>Cust_Id</th>
			<th>cust_Name</th>
			<th>Cust_Mob</th>
			<th>Cust_Address</th>
			<th>Cust_FoodOrderId</th>
			<th>Cust_FoodOrderTime</th>
			<th>Cust_FoodOrderPrice</th>
			<th>status</th>
			<th>View Item</th>
			<th>TAKE</th>
		</tr>
		
		<% 
		//System.out.println("branch id "+branch.getId());
		//System.out.println( "cust id "+c.getId());
		int id=1;
		for(FoodOrder f:list)
		{
			if(f.getBranchId()==branch.getId())
			{
				c=new CustomerDao().findCustomerByID(f.getCustId());
				System.out.println(c.getId());
				System.out.println("branchid:"+f.getBranchId()+"-food Id "+f.getId());
			
				%>
				<tr>
						 <td><%=id++ %></td>
						 <td><%=branch.getId()%></td>
						 <td><%=c.getId() %></td>
						 <td><%=c.getName() %></td>
						 <td><%=c.getMob() %></td>
						 <td><%=c.getAddress() %></td>
						 <td><%=f.getId() %></td>
						 <td><%=f.getOrdertime() %></td>
						 <td><%=f.getTotalprice() %></td>
						 <td><%=f.isStatus() %></td>
						 <td><a href="viewItem.jsp?foid=<%=f.getId()%>"><button>VIEW ITEM</button> </a> </td>
						 <td><a href="takeFoodOrder?foid=<%=f.getId()%>&cid=<%=c.getId()%>&bid=<%=branch.getId()%>"><button>TAKE</button> </a> </td>	 
				 </tr>
				<%	
			}
		}
		
		%>
	</table>
	<%} %>
	
	
	
	
	<a href="stafflogin.jsp"> HOME </a>
</body>
</html>