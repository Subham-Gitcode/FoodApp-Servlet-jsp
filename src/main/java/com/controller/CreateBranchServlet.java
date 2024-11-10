package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BrachDao;
//import com.dao.BrachDao;
import com.dao.UserDao;
import com.dto.Branch;
import com.dto.Customer;
import com.dto.User;

@WebServlet("/createbranch")
public class CreateBranchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// createbranch?bn=KolKata&address=BIDHANAGAR+SECTOR+V&=09876541990&=qspider%40gmail.com&pass=12345&=SUNIL

		HttpSession hs = req.getSession();// HttpSession object
		User admin = (User) hs.getAttribute("admin");// admin

		if (admin != null) {
			String branchName = req.getParameter("bn");
			String address = req.getParameter("address");
			long phoneNumber = Long.parseLong(req.getParameter("mob"));

			// create Branch Object

			Branch b = new Branch();
			b.setBranchname(branchName);
			b.setPhonenumber(phoneNumber);
			b.setAddress(address);
			b.setStatus(false);//------
			
			b.setUsers(Arrays.asList(admin));// mapping
			

//			System.out.println(b.getAddress());
			UserDao userDao = new UserDao();
			BrachDao brachDao = new BrachDao();
			brachDao.createBranch(b);

			int adminId = admin.getId();
			User a = userDao.findUserById(adminId);
			List<Branch> list = a.getBranchs();
			resp.getWriter().print("<h1>Bracnh created Succesfully</h1>");
			req.getSession().setAttribute("list", list);
			req.getRequestDispatcher("adminDashboard.jsp").include(req, resp);

		} else {
			resp.getWriter().print("<h1>DO LOGIN FIRST</h1>");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}
	}
}
