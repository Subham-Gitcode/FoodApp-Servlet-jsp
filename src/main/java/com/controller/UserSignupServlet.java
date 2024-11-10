package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dto.User;

@WebServlet("/userSignup")
public class UserSignupServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// userSignup?name=SUNIL&email=SUNIL%40gmail.com&pass=12345&role=admin

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		String role = req.getParameter("role");

		// database

		// create object
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);

		// save object in database

		UserDao dao = new UserDao();
		dao.saveUser(user);

		resp.getWriter().print("<h1>" + role + " Signup SuccesFull</h1>");
		req.getRequestDispatcher("login.jsp").include(req, resp);

	}
}
