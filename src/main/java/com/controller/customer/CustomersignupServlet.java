package com.controller.customer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CustomerDao;
import com.dto.Customer;
import com.dto.FoodOrder;

@WebServlet("/customersignup")
public class CustomersignupServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//customersignup?name=SUNIL&email=rakeshsumo%40gmail.com&pass=12345&mob=9876543210&address=SOUTH+AND+CIRCLE+BANGALORE
		
		//create customer object
		Customer customer=new Customer();
		customer.setName(req.getParameter("name"));
		customer.setEmail(req.getParameter("email"));
		customer.setPassword(req.getParameter("pass"));
		customer.setMob(Long.parseLong(req.getParameter("mob")));
		customer.setAddress(req.getParameter("address"));
		
		//store data in database
		CustomerDao dao=new CustomerDao();
		dao.saveCustomer(customer);
		
		//go to customerlogin page
		resp.getWriter().print("<h1>customer signup successfull</h1>");
		req.getRequestDispatcher("customerlogin.jsp").include(req, resp);
		
	}
}
