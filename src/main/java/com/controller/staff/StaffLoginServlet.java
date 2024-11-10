package com.controller.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.dto.User;

@WebServlet("/staffLogin")
public class StaffLoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("pass");

		// find by Email
		UserDao dao = new UserDao();
		User user = dao.findByEmail(email);// user object or null
		
		if(user!=null)
		{
			if (user.getPassword().equals(password) && user.getRole().equalsIgnoreCase("staff")) {
				//
				resp.getWriter().print("<h1>" +user.getId()+" ID "+user.getRole() + " Login Successfull</h1>");

				// create HttpSession object
				HttpSession hs = req.getSession();// Httpsession type
				hs.setAttribute(user.getRole() + "", user);// admin branchmanager staff

				req.getRequestDispatcher(user.getRole() + ".jsp").include(req, resp);
			} else {
				resp.getWriter().print("<h1>Incorrect Password </h1>");
				req.getRequestDispatcher("login.jsp").include(req, resp);
			}
			
		}else
		{
			resp.getWriter().print("<h1>branch manager not created Staff </h1>");
			req.getRequestDispatcher("userSignup.jsp").include(req, resp);
		}
	}

}
