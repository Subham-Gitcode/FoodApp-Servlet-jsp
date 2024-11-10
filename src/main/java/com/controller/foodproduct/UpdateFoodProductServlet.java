package com.controller.foodproduct;

import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
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

@WebServlet("/updatefoodproduct")
public class UpdateFoodProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User branchmanager = (User) req.getSession().getAttribute("branchmanager");
		if (branchmanager != null) {

			// get existing food product from database through id

			int fpid = Integer.parseInt(req.getParameter("id"));
			FoodProductDao dao = new FoodProductDao();
			FoodProduct foodProduct = dao.findFoodProductById(fpid);// food product object
			foodProduct.setAbout(req.getParameter("about"));
			foodProduct.setPrice(Double.parseDouble(req.getParameter("price")));

			// update in database
			dao.updateFoodproduct(foodProduct);
			//
			resp.getWriter()
					.print("<h1>" + foodProduct.getId() + " " + foodProduct.getName() + " updated Successfully</h1>");
			//

			int bmId = branchmanager.getId();
			UserDao userDao = new UserDao();
			User bm = userDao.findUserById(bmId);
			Menu menu = bm.getMenu();
			List<FoodProduct> listOfFoodProduct = menu.getFoodProducts();
			req.getSession().setAttribute("listoffp", listOfFoodProduct);
			req.getRequestDispatcher("branchmanagerfpDashboard.jsp").include(req, resp);
		} else {
			resp.getWriter().print("<h1>Login first</h1>");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}

	}

}
