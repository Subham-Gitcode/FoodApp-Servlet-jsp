<!DOCTYPE html>

<%@page import="com.dto.Customer"%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Coustomer Login</title>
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
	width: 99%;
    height: 97%;
   	background: url("./images/Background2.png"), #000;
   	background-size: cover;
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
}
.nav{
    position: relative;
    left:-180px;
}
a{
    padding:5px 15px;
    color:orange;
    font-size: 20px;
    text-decoration:none;
}
a:hover{
    color:white;
    text-decoration: underline;
    text-underline-position:under;
}
.search{
    font-size: 25px;
}
.search i{
    padding:5px 20px;
}
/************heading**************/
.heading{
    display: flex;
    justify-content: space-evenly;
    padding-top:65px;
    align-items:center;
}
.right{
    width: 440px;
    height: 440px;
    background-image: url('food.jpg');
    background-size:cover;
    border-radius: 400px;
    display: flex;
    justify-content: center;
    align-items:center;
}
.right::before{
    width: 430px;
    height: 430px;
    border:2px solid white;
    content: '';
    position: absolute;
    border-radius: 430px;
    padding:10px;
}

/**********left************/
.left p:nth-child(1){
    color: white;
    font-size: 30px;
    letter-spacing: 1px;
    font-style: italic;
}
.left h1{
    font-size: 90px;
    color:white;
}
.left p:nth-child(3){
    color: orange;
    font-size: 30px;
    font-style: italic;
}
.left button{
    width: 150px;
    height: 30px;
    font-size: 15px;
    border-radius: 20px;
    border:2px solid white;
    background-color:transparent;
    color:white;
    margin:20px 0px;
}
/*********SocialMedia***************/
.SocialMedia{
    position: absolute;
    right: 0;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    flex-direction: column;
    width: 40px;
    height: 200px;
    background-color:white;
    font-size: 25px;

}
.SocialMedia i:hover {
    color:orange;
}


.menu-container {
    position: absolute;
    top:20px;
    right:100px;
    display: inline-block;
  }
  </style>
</head>
<body>
<%Customer customer =( Customer) session.getAttribute("customer"); %>
  
<div class="top">
            <div class="logo">CraveCourier</div>
            
            <div class="search">
                <!-- <i class="fa-solid fa-search"></i> -->
                <div class="menu-container">
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

        <div class="heading">
            <div class="left">
                <p>Are You Hungry?</p>
                <h1>Don't Wait</h1>
                <p>Let start to order food now</p>

                <button><a href="customerDashboaard.jsp">Menu</a></button>
            </div>

            <div class="right" id="food">
               
            </div>

            
        </div>
  <script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>