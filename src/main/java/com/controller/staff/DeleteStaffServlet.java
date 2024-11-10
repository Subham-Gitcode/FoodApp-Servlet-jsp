package com.controller.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

@WebServlet("/deleteStaff")
public class DeleteStaffServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User branchmanager = (User) req.getSession().getAttribute("branchmanager");

		int staffId = Integer.parseInt(req.getParameter("id"));
		UserDao userDao = new UserDao();
		int bmid = branchmanager.getId();
		User bmobject = userDao.findUserById(bmid);
		Branch branch = bmobject.getBranchs().get(0);

		BrachDao brachDao = new BrachDao();

		// find branch from databases
		Branch branchObject = brachDao.findBranchById(branch.getId());
		List<User> listOfUser = branchObject.getUsers();
		Iterator<User> i = listOfUser.iterator();
		while (i.hasNext()) {
			User staff = i.next();
			if (staff.getId() == staffId) {

				i.remove();
				// delete form database
			}
		}

		// set updated list into the branch Object
		branchObject.setUsers(listOfUser);
		brachDao.updateBranch(branchObject);
		// delete

		User staff = userDao.findUserById(staffId);
		userDao.deleteUser(staff);

		// find list of staff and print over the browser
		Branch branchObject1 = brachDao.findBranchById(branch.getId());
		List<User> listOfUser1 = branchObject1.getUsers();
		ArrayList<User> listOfStaff = new ArrayList<>();
		for (User u : listOfUser1) {
			if (u.getRole().equals("staff")) {
				listOfStaff.add(u);
			}
		}
		if (!listOfStaff.isEmpty()) {
			resp.getWriter().print("<h1>" + staffId + " staff Deleted successfully</h1>");
			req.getSession().setAttribute("listOfStaff", listOfStaff);
			req.getRequestDispatcher("staffDashboard.jsp").include(req, resp);
		} else {
			resp.getWriter().print("<h1>No staff found</h1>");
			req.getRequestDispatcher("menu.jsp").include(req, resp);
		}

	}
}
