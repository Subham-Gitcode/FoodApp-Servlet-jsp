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

@WebServlet("/findAllStaff")
public class FindAllStaffServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User branchmanager = (User) req.getSession().getAttribute("branchmanager");// branchManager
		
		
		UserDao userDao=new UserDao();
		User bm = userDao.findUserById(branchmanager.getId());
		Branch b=bm.getBranchs().get(0);
		
		// fetch the branch from database based on branch id
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
			req.getSession().setAttribute("listOfStaff", listOfStaff);
			req.getRequestDispatcher("staffDashboard.jsp").include(req, resp);
		} else {
			resp.getWriter().print("<h1>No staff found</h1>");
			req.getRequestDispatcher("menu.jsp").include(req, resp);
		}

	}

}
