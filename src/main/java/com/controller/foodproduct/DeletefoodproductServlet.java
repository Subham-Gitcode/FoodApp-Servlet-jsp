package com.controller.foodproduct;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FoodProductDao;
import com.dao.UserDao;
import com.dto.FoodProduct;
import com.dto.Menu;
import com.dto.User;

@WebServlet("/deletefoodproduct")
public class DeletefoodproductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User bm = (User) req.getSession().getAttribute("branchmanager");
		if(bm!=null)
		{
			int foodProductId = Integer.parseInt(req.getParameter("fpid"));
			// find foodProduct from database

			FoodProductDao dao = new FoodProductDao();
			FoodProduct fp = dao.findFoodProductById(foodProductId);// Food product object

			dao.deleteFoodProduct(fp);//delete food product from database
//			// delete
			
			UserDao userDao = new UserDao();
			int bmId2 = bm.getId();
			User bm1 = userDao.findUserById(bmId2);
			Menu menu = bm1.getMenu();
			List<FoodProduct> listOfFoodProduct1 = menu.getFoodProducts();
			req.getSession().setAttribute("listoffp", listOfFoodProduct1);
			req.getRequestDispatcher("branchmanagerfpDashboard.jsp").include(req, resp);
		}
		else
		{
			resp.getWriter().print("<h1>Login first</h1>");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}
		
		

	}

}
