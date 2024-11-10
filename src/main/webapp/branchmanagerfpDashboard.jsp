<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="com.dto.FoodProduct"%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Exisiting Branch</title>
  <link rel="stylesheet" href="style.css">
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
}

body::before {
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  background: url("./images/hero-bg.jpg"), #000;
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
  top: 50%;
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
.btnout{
   position: absolute;
   top: 29rem;
   left: 13rex;
   justify-content: space-between;
}
  </style>
</head>
<body>
    <%
	List<FoodProduct> list = (List<FoodProduct>) session.getAttribute("listoffp");
	%>

	<div class="wrapper">
        <div class="form">
            <table border="1px solid black" cellspacing="2px" cellpadding="2px" width="100% " style="color:#fff;">
                <tr>
                    <th>SNO</th>
                    <th>FP_ID</th>
                    <th>FP_NAME</th>
                    <th>FP_AVAILABILITY</th>
                    <th>FP_ABOUT</th>
                    <th>FP_PRICE</th>
                    <th>FP_TYPE</th>
                    <th>CHANGE STATUS</th>
                    <th>UPDATE</th>
                    <th>DELETE</th>
                </tr>
        
                <%
                int i=1;
                for (FoodProduct f : list) {
                %>
        
                    <tr>
                    <td><%=i++ %></td>
                    <td><%=f.getId() %></td>
                    <td><%=f.getName()%></td>
                    <td><%=f.isAvailability() %></td>
                    <td><%=f.getAbout() %></td>
                    <td><%=f.getPrice() %></td>
                    <td><%=f.getType() %></td>
                    <td><a href="changeAvailability?id=<%=f.getId()%>"><button>CHANGE STATUS</button></a></td>
                    <td><a href="updatefoodproduct.jsp?fpid=<%=f.getId()%>"><button>UPDATE</button></a></td>
                    <td><a href="deletefoodproduct?fpid=<%=f.getId()%>"><button>DELETE</button></a></td>
                    </tr>
                <%
                }
                %>
            </table> 

            <div class="main">
                <a href="createfoodProduct.jsp"><button>ADD FOOD PRODUCT</button></a>
<a href="menu.jsp"><button>HOME</button></a>
<a href="logout"><button>LOGOUT</button> </a>
            </div>
        </div>
    </div>


	
	
</body>
</html>