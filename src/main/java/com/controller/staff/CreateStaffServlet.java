package com.controller.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

@WebServlet("/createStaff")
public class CreateStaffServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email1 = req.getParameter("email");
		String password = req.getParameter("pass");

		//create Staff 
		
		User staff=new User();
		staff.setName(name);
		staff.setEmail(email1);
		staff.setPassword(password);
		staff.setRole("staff");
		
		// mapping 
		User branchmanager = (User) req.getSession().getAttribute("branchmanager");
		int bmId = branchmanager.getId();
		// based on id fetch branch manager object from database
//		System.out.println(branchmanager.getId());//2

		UserDao userDao = new UserDao();
		User bm = userDao.findUserById(bmId);
		List<Branch> listOfBranches = bm.getBranchs();
		Branch branch = listOfBranches.get(0);// branch object
//		System.out.println(branch.getId());//1
//
//		// mapping
//
		ArrayList<User> newListOfUser=new ArrayList<>();
		List<User> listOfusers = branch.getUsers();
		for(User u:listOfusers)
		{
			newListOfUser.add(u);
		}
		newListOfUser.add(staff);
		branch.setUsers(newListOfUser);
		
		staff.setBranchs(Arrays.asList(branch));
//
		BrachDao brachDao = new BrachDao();
		brachDao.updateBranch(branch);
//
//		//find All Staff and print over browser	
		
		User bm1 = userDao.findUserById(branchmanager.getId());
		Branch b=bm1.getBranchs().get(0);
		
		//go to database and fetch the branch based on branch id
		Branch branchObject = brachDao.findBranchById(b.getId());
		List<User> listOfUser = branchObject.getUsers();
		ArrayList<User> listOfStaff=new ArrayList<>();
		for (User u : listOfUser) {
			if (u.getRole().equals("staff")) {
				listOfStaff.add(u);
			}
		}
		if(!listOfStaff.isEmpty())
		{
			resp.getWriter().print("<h1>Staff Created Successfully</h1>");
			req.getSession().setAttribute("listOfStaff", listOfStaff);
			req.getRequestDispatcher("staffDashboard.jsp").include(req, resp);
		}
		else
		{
			resp.getWriter().print("<h1>No staff found</h1>");
			req.getRequestDispatcher("menu.jsp").include(req, resp);
		}
		
		//
	
		

	}
}
