<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="com.dto.User"%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Staff Dashboard </title>
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

.form {
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
#btn{
    display: flex;
    justify-content: space-evenly;
}



  </style>
</head>
<body>
  <div class="wrapper">
    <div class="form">
        
	<%
	List<User> listOfStaff = (List<User>) session.getAttribute("listOfStaff");
	%>

	<table border="1px solid" cellspacing="5px" cellpadding="5px"
		width="100%">
		<tr>

			<th>S.NO</th>
			<th>STAFF_ID</th>
			<th>STAFF_NAME</th>
			<th>STAFF_EMAIL</th>
			<th>UPDATE</th>
			<th>DELETE</th>
		</tr>

		<!-- java code -->

		<%
		int i = 1;
		for (User u : listOfStaff) {
		%>

		<tr>
			<td><%=i++%></td>
			<td><%=u.getId()%></td>
			<td><%=u.getName()%></td>
			<td><%=u.getEmail()%></td>
			<td><a href="updateStaff.jsp?id=<%=u.getId()%>"><button>UPDATE</button>
			</a></td>
			<td><a href="deleteStaff?id=<%=u.getId()%>"><button>DELETE</button>
			</a></td>
		</tr>

		<%
		}
		%>
	</table>
	<a href="createstaff.jsp"><button>ADD STAFF</button> </a>
	<a href="menu.jsp">GO TO</a>
    </div>
  </div>
</body>
</html>