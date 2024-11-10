package com.controller.foodproduct;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FoodProductDao;
import com.dao.MenuDao;
import com.dao.UserDao;
import com.dto.FoodProduct;
import com.dto.Menu;
import com.dto.User;

@WebServlet("/changeAvailability")
public class ChangeAvailabilityServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		User bm = (User) req.getSession().getAttribute("branchmanager");

		if (bm != null) {
			int fpId = Integer.parseInt(req.getParameter("id"));
			FoodProductDao dao = new FoodProductDao();
			FoodProduct fp = dao.findFoodProductById(fpId);

			if (fp.isAvailability() == false)
				fp.setAvailability(true);
			else
				fp.setAvailability(false);
			//
			dao.updateFoodproduct(fp);//
			// find
			int bmid = bm.getId();
			UserDao userDao = new UserDao();
			User branchmanager = userDao.findUserById(bmid);
			int menuId = branchmanager.getMenu().getId();
			MenuDao menuDao = new MenuDao();
			Menu menuObject = menuDao.findMenuById(menuId);
			List<FoodProduct> listOfFoodPd = menuObject.getFoodProducts();

			// add in sesssion
			req.getSession().setAttribute("listoffp", listOfFoodPd);
			resp.getWriter().print("<h1>" + fpId + " availability updated</h1>");
			req.getRequestDispatcher("branchmanagerfpDashboard.jsp").include(req, resp);
		} else {
			resp.getWriter().print("<h1>Login first</h1>");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}

	}

}
