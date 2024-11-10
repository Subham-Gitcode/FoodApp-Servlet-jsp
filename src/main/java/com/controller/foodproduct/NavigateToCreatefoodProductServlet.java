package com.controller.foodproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Menu;
import com.dto.User;

@WebServlet("/navigateToCreatefoodProduct")
public class NavigateToCreatefoodProductServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User branchmanager = (User) req.getSession().getAttribute("branchmanager");
		if (branchmanager != null) {
			Menu menunn = (Menu)req.getSession().getAttribute("menu");
			System.out.println(menunn.getId());
			
			req.getRequestDispatcher("createfoodProduct.jsp").include(req, resp);
		}
		else
		{
			resp.getWriter().print("<h1>Login first</h1>");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}
		
		
		
	}

}
