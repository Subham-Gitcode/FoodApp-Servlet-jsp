package com.controller.foodproduct;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MenuDao;
import com.dao.UserDao;
import com.dto.FoodProduct;
import com.dto.Menu;
import com.dto.User;

@WebServlet("/findAllFoodProduct")
public class FindAllFoodProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User branchmanager = (User) req.getSession().getAttribute("branchmanager");
		if (branchmanager != null) {
			if (branchmanager.getMenu() != null) {
				int menuId = branchmanager.getMenu().getId();// menu id
				System.out.println(menuId);
				MenuDao menuDao = new MenuDao();
				Menu menuObject = menuDao.findMenuById(menuId);
				List<FoodProduct> listOfFoodPd = menuObject.getFoodProducts();
//				
//				//add in sesssion
				req.getSession().setAttribute("listoffp", listOfFoodPd);
				req.getRequestDispatcher("branchmanagerfpDashboard.jsp").include(req, resp);
			} else {
				int bmId = branchmanager.getId();
				UserDao userDao = new UserDao();
				User bm = userDao.findUserById(bmId);
				Menu menu = bm.getMenu();
				List<FoodProduct> listOfFoodProduct = menu.getFoodProducts();
				req.getSession().setAttribute("listoffp", listOfFoodProduct);
				req.getRequestDispatcher("branchmanagerfpDashboard.jsp").include(req, resp);
			}
		} else {
			resp.getWriter().print("<h1>Login first</h1>");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}

	}

}
