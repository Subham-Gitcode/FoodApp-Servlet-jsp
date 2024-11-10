<!DOCTYPE html>
<%@page import="com.dao.BrachDao"%>
<%@page import="com.dao.CustomerDao"%>
<%@page import="com.dto.FoodOrder"%>
<%@page import="com.dto.Branch"%>
<%@page import="com.dto.Customer"%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Coustomer Payment</title>
  <link rel="stylesheet" href="style.css">
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
  
  <style>
    @import url("https://fonts.googleapis.com/css2?family=Open+Sans:wght@200;300;400;500;600;700&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Open Sans", sans-serif;
}

body {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  width: 100%;
  padding: 0 10px;
  background: url("./images/Background2.png"), #000;
  background-position: center;
  background-size: cover;
}

.wrapper {
  
  border-radius: 8px;
  padding: 30px;
  text-align: center;
  border: 1px solid rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(8px);
  -webkit-backdrop-filter: blur(8px);
}

form {
  display: flex;
  flex-direction: column;
}

h2 {
  font-size: 2rem;
  margin-bottom: 20px;
  color: #fff;
}

.input-field {
  position: relative;
  border-bottom: 2px solid #ccc;
  margin: 15px 0;
}

.input-field label {
  position: absolute;
  
  left: 0;
  transform: translateY(-50%);
  color: #fff;
  font-size: 16px;
  pointer-events: none;
  transition: 0.15s ease;
}

.input-field input {
  width: 100%;
  height: 40px;
  background: transparent;
  border: none;
  outline: none;
  font-size: 16px;
  color: #fff;
}

.input-field input:focus~label,
.input-field input:valid~label {
  font-size: 0.8rem;
  top: 10px;
  transform: translateY(-120%);
}

.forget {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 25px 0 35px 0;
  color: #fff;
}

#remember {
  accent-color: #fff;
}

.forget label {
  display: flex;
  align-items: center;
}

.forget label p {
  margin-left: 8px;
}

.wrapper a {
  color: #efefef;
  text-decoration: none;
}

.wrapper a:hover {
  text-decoration: underline;
}

button {
  background: #fff;
  color: #000;
  font-weight: 600;
  border: none;
  padding: 12px 20px;
  cursor: pointer;
  border-radius: 3px;
  font-size: 16px;
  border: 2px solid transparent;
  transition: 0.3s ease;
}

button:hover {
  color: #fff;
  border-color: #fff;
  background: rgba(255, 255, 255, 0.15);
}

.register {
  text-align: center;
  margin-top: 30px;
  color: #fff;
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
    position: absolute;
    top: 20px;
    left: 100px;
}
.nav{
    position: absolute;
    left: 650px;
    top: 35px;
    display: flex;
    justify-content: space-between;
    gap:30px;
}
.search{
    font-size: 25px;
}
.search i{
    padding:5px 20px;
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
             <div class="nav">
                <button><a href="customerreciept.jsp">Back</a></button><br><br>
                <button><a href="customerHome.jsp">Home</a></button>
                
            </div>
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
  <div class="wrapper">
    <form action="payment">
        <h2>CUSTOMER PAYMENT PAGE</h2>
        <div class="input-field">
        <input type="text" name="id" value="<%=c.getId()%>" readonly="readonly">
        <label>Your Customer id</label>
      </div>
      <div class="input-field">
        <input type="text" name="name" value="<%=c.getName()%>" readonly="readonly">
        <label>Customer Name</label>
      </div>
      <div class="input-field">
        <input type="text" name="bid" value="<%=b.getId()%>" readonly="readonly">
        <label>Branch Id</label>
      </div>
      <div class="input-field">
        <input type="text" name="fid" value="<%=f.getId()%>" readonly="readonly">
        <label>Food Order Id</label>
      </div>
      <div class="input-field">
        <input type="text" name="payment" value="<%=(double)session.getAttribute("pay") %>"" readonly="readonly">
        <label>Amount</label>
      </div>
        <button type="submit">Payment</button>
        </form>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
  
</body>
</html>