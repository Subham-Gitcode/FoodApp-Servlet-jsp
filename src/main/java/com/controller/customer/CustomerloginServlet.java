package com.controller.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BrachDao;
import com.dao.CustomerDao;
import com.dto.Branch;
import com.dto.Customer;

@WebServlet("/customerlogin")
public class CustomerloginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		//validate from database
		
		CustomerDao dao=new CustomerDao();
		Customer customerObject = dao.findCustomerByEmail(email);
		if(customerObject!=null )
		{
			if(customerObject.getPassword().equals(pass))
			{
				//store customer object inside session object
				req.getSession().setAttribute("customer", customerObject);
				
				resp.getWriter().print("<h1>Customer Login successfull</h1>");
				//fetch list of branches
				BrachDao brachDao=new BrachDao();
				List<Branch> list = brachDao.findAllBranches();
				if(list!=null)
				{
					//store list inside session object
					req.getSession().setAttribute("list", list);
					req.getRequestDispatcher("customerHome.jsp").include(req, resp);
				}
				else
				{
					resp.getWriter().print("<h1>No Branch Available</h1>");
					req.getRequestDispatcher("customerDashboaard.jsp").include(req, resp);
				}
				
			}
			else	
			{
				resp.getWriter().print("<h1>Invalid Credenitials</h1>");
				req.getRequestDispatcher("customerlogin.jsp").include(req, resp);
			}
			
		}else
		{
			resp.getWriter().print("<h1>Create Account First</h1>");
			req.getRequestDispatcher("customerSignup.jsp").include(req, resp);
		}
		
		
	}

}
