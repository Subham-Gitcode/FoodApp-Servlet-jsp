package com.controller.menu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BrachDao;
import com.dto.Branch;
import com.dto.User;

@WebServlet("/takeBranchAsBranchManager")
public class TakeBranchAsBranchManager extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User branchmanager = (User) req.getSession().getAttribute("branchmanager");// branchManager
		
		if(branchmanager!=null)
		{
			int branchId = Integer.parseInt(req.getParameter("id"));
			// map with branch manager
			

			// find Branch

			BrachDao brachDao = new BrachDao();//
			Branch branch = brachDao.findBranchById(branchId);// branch object
			// set Status to true
			branch.setStatus(true);

			List<User> listOfUser = branch.getUsers();

			listOfUser.add(branchmanager);// mapping
			branch.setUsers(listOfUser);
			//
			brachDao.createBranch(branch);// store into database

			req.getSession().setAttribute("branch", branch);
			req.getSession().setAttribute("branchmanager", branchmanager);

			resp.getWriter()
					.print("<h1>" + branch.getId() + " Branch, Taken By " + branchmanager.getId() + " branch Manager</h1>");
			req.getRequestDispatcher("createMenu.jsp").include(req, resp);
		}else
		{
			resp.getWriter().print("<h1>Login first</h1>");
	      req.getRequestDispatcher("login.jsp").include(req, resp);
		}
		
	

	}

}
