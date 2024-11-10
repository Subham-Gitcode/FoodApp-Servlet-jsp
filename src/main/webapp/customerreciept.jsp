<%@page import="com.dto.Item"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.sql.Date"%>
<%@page import="com.dto.FoodOrder"%>
<%@page import="com.dao.BrachDao"%>
<%@page import="com.dao.CustomerDao"%>
<%@page import="com.dto.Customer"%>
<%@page import="com.dto.Branch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reciept</title>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />

<style>
    body {
        font-family: 'Arial', sans-serif;
        background: url("./images/Background2.png"), #000; ;
        margin: 0;
        padding: 20px;
        display: flex;
        justify-content: center;
    }

    .receipt-container {
        background-color: white;
        margin:50px;
        padding: 20px;
        
        max-width: 500px;
        border-radius: 10px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        font-size: 24px;
        margin-bottom: 20px;
    }

    

    p {
        font-size: 14px;
        color: #333;
    }

    .order-details {
        margin: 20px 0;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    th, td {
        padding: 10px;
        border-bottom: 1px solid #e9ecef;
        text-align: left;
    }

    th {
        background-color: #f8f9fa;
        font-size: 14px;
        color: #555;
    }

    td {
        font-size: 14px;
        color: #333;
    }

    .total {
        text-align: right;
        margin-top: 20px;
        font-size: 18px;
        font-weight: bold;
    }

    .pay-button {
        display: inline-block;
        background-color: #007bff;
        color: white;
        padding: 10px 20px;
        border-radius: 5px;
        text-decoration: none;
        text-align: center;
    }

    .footer {
        margin-top: 30px;
        text-align: center;
        font-size: 12px;
        color: #777;
    }

    .green-box {
        background-color: orange;
        padding: 20px;
        color: white;
        text-align: center;
        margin-top: 30px;
        border-radius: 5px;
    }

    .green-box a {
        color: white;
        font-weight: bold;
        text-decoration: none;
    }

    .green-box button {
        background-color: white;
        color: #28a745;
        border: none;
        padding: 10px 15px;
        border-radius: 5px;
        margin: 10px;
    }
    .user {
    position: absolute;
    top:20px;
    right:100px;
    display: inline-block;
  }
.top{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    height: 80px;
    align-items:center;
    padding:0px 40px;
}
.logo{
    font-size: 30px;
    color:orange;
    position:absolute;
    top:20px;
    left: 100px;
}
.nav{
    position: relative;
    left:-100px;
}
</style>
</head>
<body>

<%

Customer customerObject = (Customer) request.getSession().getAttribute("customer");
Branch b = (Branch) request.getSession().getAttribute("branch");
FoodOrder f = (FoodOrder) request.getSession().getAttribute("foodorder");


Customer c=new CustomerDao().findCustomerByID(customerObject.getId());
Branch branch=new BrachDao().findBranchById(b.getId());
Customer customer = (Customer)session.getAttribute("customer");
%>
<div class="top">
            <div class="logo">CraveCourier</div>
            <div class="search">
                <!-- <i class="fa-solid fa-search"></i> -->
                <div class="user">
                    <button id="dropdownAvatarNameButton" data-dropdown-toggle="dropdownAvatarName" class="flex items-center text-sm pe-1 font-medium text-gray-900 rounded-full hover:text-blue-600 dark:hover:text-blue-500 md:me-0 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:text-white" type="button">
                        <span class="sr-only">Open user menu</span>
                        <img class="w-8 h-8 me-2 rounded-full" src="./images/user.png" alt="user photo">
                        <%= customer.getName() %>
                        <svg class="w-2.5 h-2.5 ms-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 4 4 4-4"/>
                        </svg>
                        </button>
                        
                        <!-- Dropdown menu -->
                        <div id="dropdownAvatarName" class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600">
                            <div class="px-4 py-3 text-sm text-gray-900 dark:text-white">
                              <div class="font-medium "><%=customer.getName() %></div>
                              <div class="truncate"><%=customer.getEmail() %></div>
                            </div>
                            <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownInformdropdownAvatarNameButtonationButton">
                              <li>
                                <a href="customerHome.jsp" class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Dashboard</a>
                              </li>
                              <li>
                                <a href="#" class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">NearBy Restrurents</a>
                              </li>
                              <li>
                                <a href="#" class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Orders</a>
                              </li>
                            </ul>
                            <div class="py-2">
                              <a href="logout" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Sign out</a>
                            </div>
                        </div>
                    </div>
            </div>
        </div>

<div class="receipt-container">
    <h1 style="color: orange">Thanks For Your Order!</h1>
    <p>Order ID: <%= f.getId() %></p>
    <p>Order Date: <%= LocalDateTime.now().toLocalDate() %></p>
    <p>Customer Name: <%= c.getName() %></p>
    <p>Order Time: <%= f.getOrdertime() %></p>

    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <%
            int i1=1;
            double sum=0.0;
            for(Item i : f.getItems()) {
            %>
            <tr>
                <td><%= i1++ %></td>
                <td><%= i.getProductName() %></td>
                <td><%= i.getQuantity() %></td>
                <td><%= i.getPrice() %></td>
                <% sum += i.getPrice(); %>
            </tr>
            <% } %>
        </tbody>
    </table>

    <p class="total">Total (with 18% GST): (&#8377;)<%= sum + (sum * 0.18) %></p>
	<%
		session.setAttribute("pay",(sum+(sum*0.18)));
		session.setAttribute("orderitems", f.getItems());
%>
    <a href="customerpayment.jsp" class="pay-button">PAY</a>

    <div class="green-box">
        <p>Follow Me and Share my unique URL with friends and get a &#8377; 5 credit:</p>
        <a href="https://www.linkedin.com/in/subham-roy-linkdn/">Linkedin</a>
        <div>
            <button>Share on Facebook</button>
            <button>Share on Twitter</button>
        </div>
    </div>

    <div class="footer">
        <p>Thanks for being a great customer!-<%= b.getAddress() %></p>
        
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>