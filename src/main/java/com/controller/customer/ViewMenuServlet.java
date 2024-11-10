package com.controller.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BrachDao;
import com.dto.Branch;
import com.dto.FoodProduct;
import com.dto.Menu;
import com.dto.User;

@WebServlet("/viewMenu")
public class ViewMenuServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int branchId=Integer.parseInt(req.getParameter("id"));//branch Id
		
		//fetch branchByBranchId
		BrachDao brachDao=new BrachDao();
		Branch branchObject = brachDao.findBranchById(branchId);//get Branch Object from database
		
		List<User> listOfUser = branchObject.getUsers();
		for(User u:listOfUser)
		{
			if(u.getRole().equalsIgnoreCase("branchmanager"))
			{
				Menu menuObject = u.getMenu();
				List<FoodProduct> listOfFoodProduct = menuObject.getFoodProducts();
				//store into session object
				resp.getWriter().print("<h1>"+branchId+" ID "+branchObject.getBranchname() + " Name </h1>");
				req.getSession().setAttribute("listoffp",listOfFoodProduct);
				//store branch object into session for customer branch mapping
				req.getSession().setAttribute("branch",branchObject);
				req.getRequestDispatcher("customerViewMenuProduct.jsp").include(req, resp);
				
			}
		}
		
	}

}
