<!DOCTYPE html>

<%@page import="com.dto.User"%>
<%@page import="java.util.List"%>
<%@page import="com.dto.Branch"%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
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
.btnout{
   position: absolute;
   top: 25rem;
   left: 80px;
   justify-content: space-between;
}
  </style>
</head>
<body>
  <div class="wrapper">
    <div class="form">
      <!-- java code  -->
	<%
	List<Branch> listOfBranch = (List<Branch>) session.getAttribute("list");
	User admin = (User) session.getAttribute("admin");
	%>

	<!-- HTML code -->
	<h1><%=admin.getId()%>
		ID ADMIN LOGIN
	</h1>
	<table border="1px solid" cellspacing="5px" cellpadding="5px"
		width="100%" style="color: #fff;">
		<tr>

			<th>S.NO</th>
			<th>B_ID</th>
			<th>B_NAME</th>
			<th>B_ADDRESS</th>
			<th>B_PHONE_NUMBER</th>
			<th>UPDATE</th>
			<th>DELETE</th>
		</tr>

		<!-- java code -->

		<%
		int i = 1;
		for (Branch b : listOfBranch) {
		%>

		<tr>
			<td><%=i++%></td>
			<td><%=b.getId()%></td>
			<td><%=b.getBranchname()%></td>
			<td><%=b.getAddress()%></td>
			<td><%=b.getPhonenumber()%></td>
			<td><a href="updateBranch.jsp?id=<%=b.getId()%>"><button>UPDATE</button>
			</a></td>
			<td><a href="deleteBranch?id=<%=b.getId()%>"><button>DELETE</button>
			</a></td>
		</tr>

		<%
		}
		%>


	</table>
      
    </div>
    <div class="btnout">
        <a href="createbranch.jsp"><button>Create Branch</button></a>
        <a href="index.html"><button>Home</button></a>
        <a href="logout"><button>Logout</button></a>
    </div>
  </div>
</body>
</html>