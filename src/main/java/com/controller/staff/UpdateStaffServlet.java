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
import com.dao.UserDao;
import com.dto.Branch;
import com.dto.User;

@WebServlet("/updatestaff")
public class UpdateStaffServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int staffId = Integer.parseInt(req.getParameter("id"));
		// find staff from database
		UserDao userDao = new UserDao();
		User staff = userDao.findUserById(staffId);

		staff.setName(req.getParameter("name"));
		staff.setEmail(req.getParameter("email"));

		// update staff in database
		userDao.updateUser(staff);

		// find All the staff and display over the browser
		User branchmanager = (User) req.getSession().getAttribute("branchmanager");
		User bm1 = userDao.findUserById(branchmanager.getId());
		Branch b = bm1.getBranchs().get(0);

		// go to database and fetch the branch based on branch id
		BrachDao brachDao = new BrachDao();
		Branch branchObject = brachDao.findBranchById(b.getId());
		List<User> listOfUser = branchObject.getUsers();
		ArrayList<User> listOfStaff = new ArrayList<>();
		for (User u : listOfUser) {
			if (u.getRole().equals("staff")) {
				listOfStaff.add(u);
			}
		}
		if (!listOfStaff.isEmpty()) {
			resp.getWriter().print("<h1>" + staffId + "ID Staff Updated Successfully</h1>");
			req.getSession().setAttribute("listOfStaff", listOfStaff);
			req.getRequestDispatcher("staffDashboard.jsp").include(req, resp);
		} else {
			resp.getWriter().print("<h1>No staff found</h1>");
			req.getRequestDispatcher("menu.jsp").include(req, resp);
		}
	}

}
