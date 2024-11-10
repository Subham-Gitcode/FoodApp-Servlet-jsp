package com.controller.foodproduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FoodDao;
import com.dao.FoodProductDao;
import com.dao.MenuDao;
import com.dao.UserDao;
import com.dto.FoodProduct;
import com.dto.Menu;
import com.dto.User;

@WebServlet("/createFoodProduct")
public class CreateFoodProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User bm= (User)req.getSession().getAttribute("branchmanager");
		
		UserDao userDao=new UserDao();
		User b = userDao.findUserById(bm.getId());
		int menuId = b.getMenu().getId();
		MenuDao menuDao=new MenuDao();
	    Menu menu = menuDao.findMenuById(menuId);
//	    
		FoodProduct fp = new FoodProduct();// create the food Product object
//		// createFoodProduct?name=CHICKEN+TANDooRI&type=non_veg&about=FULL+CHICKEN+ROAST&price=599
		fp.setName(req.getParameter("name"));
		fp.setType(req.getParameter("type"));
		fp.setAbout(req.getParameter("about"));
		fp.setPrice(Double.parseDouble(req.getParameter("price")));
		fp.setAvailability(false);//
//
		fp.setMenus(Arrays.asList(menu));
//
//		System.out.println(menu.getId());
		
//		System.out.println(menu.getId());

		// store the data in database
//		MenuDao menuDao=new MenuDao();
		Menu m = menuDao.saveMenu(menu);//save menu
		FoodProductDao foodDao = new FoodProductDao();
		foodDao.createFoodProduct(fp);
//		System.out.println("Mapping");
////		
//		// find the list of food Product
//		
//		int menuId = m.getId();
		Menu menuObject = menuDao.findMenuById(menuId);
		List<FoodProduct> listOfFoodPd = menuObject.getFoodProducts();
////		
////		//add in sesssion
		req.getSession().setAttribute("listoffp", listOfFoodPd);
////		
		resp.getWriter().print("<h1>FooD PRoduct Created Successfully</h1>");
		req.getRequestDispatcher("branchmanagerfpDashboard.jsp").include(req, resp);

	}

}
