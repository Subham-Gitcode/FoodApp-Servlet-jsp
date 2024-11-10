package com.controller.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BrachDao;
import com.dao.CustomerDao;
import com.dao.FoodOrderDao;
import com.dao.StaffDao;
import com.dao.UserDao;
import com.dto.Branch;
import com.dto.Customer;
import com.dto.FoodOrder;
import com.dto.Staff;
import com.dto.User;

@WebServlet("/takeFoodOrder")
public class TakeFoodOrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// takeFoodOrder?foid=2
		
		int cid=Integer.parseInt(req.getParameter("cid"));
		Customer customerObject = new CustomerDao().findCustomerByID(cid);
		int bid=Integer.parseInt(req.getParameter("bid"));
		Branch branch = new BrachDao().findBranchById(bid);
		
		
		
		User staff = (User) req.getSession().getAttribute("staff");
		UserDao userDao = new UserDao();
		User oldStaff = userDao.findUserById(staff.getId());
		// ===========================================================
		int foId = Integer.parseInt(req.getParameter("foid"));
		FoodOrderDao foodOrderDao = new FoodOrderDao();
		FoodOrder newFoodOrderObject = foodOrderDao.foodOrderById(foId);// foodObject
		newFoodOrderObject.setStatus(true);// change status
		// ======================================================
		StaffDao staffDao = new StaffDao();
		Staff staffObject = staffDao.findStaffById(staff.getId());//

		if (staffObject != null) {

			List<FoodOrder> list = staffObject.getFoodOrders();
			if (!list.isEmpty()) {

				System.out.println(1);
				list.add(newFoodOrderObject);
				staffObject.setFoodOrders(list);
				System.out.println(2);
			} else {
				System.out.println(3);
				ArrayList<FoodOrder> fOrders = new ArrayList();
				fOrders.add(newFoodOrderObject);
				staffObject.setFoodOrders(fOrders);
				System.out.println(4);
			}
			
			//save
			staffDao.saveStaff(staffObject);
			req.getSession().setAttribute("custo", customerObject);
			req.getSession().setAttribute("branch", branch);
			resp.getWriter().print("<h1>"+oldStaff.getId()+" staff taken "+newFoodOrderObject.getId()+" successfully</h1>");
			req.getRequestDispatcher("staffmail").include(req, resp);
		}
		else
		{
			Staff newStaff=new Staff();
			
			newStaff.setId(oldStaff.getId());
			newStaff.setName(oldStaff.getName());
			newStaff.setEmail(oldStaff.getEmail());
			newStaff.setPassword(oldStaff.getPassword());
			newStaff.setRole(oldStaff.getRole());
			List<Branch> b=oldStaff.getBranchs();
			newStaff.setBranchId(b.get(0).getId());
			//====================================================================
			//====================================================================
			System.out.println(5);
			ArrayList<FoodOrder> fOrders=new ArrayList();
			fOrders.add(newFoodOrderObject);
			newStaff.setFoodOrders(fOrders);
			System.out.println(6);
			staffDao.saveStaff(newStaff);
			req.getSession().setAttribute("custo", customerObject);
			req.getSession().setAttribute("branch", branch);
			resp.getWriter().print("<h1>"+oldStaff.getId()+" staff taken "+newFoodOrderObject.getId()+" successfully</h1>");
			req.getRequestDispatcher("staffmail").include(req, resp);
		}
		
	}

	// =========================================

}
