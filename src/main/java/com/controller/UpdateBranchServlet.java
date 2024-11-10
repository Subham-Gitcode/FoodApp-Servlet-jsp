package com.controller;

import java.io.IOException;
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

@WebServlet("/updateBranch")
public class UpdateBranchServlet extends HttpServlet {
	// updateBranch?id=4&bn=BANGALORE&address=jaynagar+Bangalore&mob=1234567890&email=jaynagar%40gmail.com&bmn=MR+Shubham
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//find Admin
				User admin = (User) req.getSession().getAttribute("admin");// admin
				if(admin!=null)
				{
					int id = Integer.parseInt(req.getParameter("id"));
					String branchName = req.getParameter("bn");
					String address = req.getParameter("address");
					long phoneNumber = Long.parseLong(req.getParameter("mob"));

					// find Branch object
					BrachDao dao = new BrachDao();
					Branch b = dao.findBranchById(id);
					b.setBranchname(branchName);
//					b.setEmail(email);
					b.setAddress(address);
					b.setPhonenumber(phoneNumber);
					// update

					dao.createBranch(b);
					
					
					//based on find find All Branches
					int adminId=admin.getId();
					UserDao dao1=new UserDao();
					
					User adminUser = dao1.findUserById(adminId);
					List<Branch> listOfBranches = adminUser.getBranchs();
					//store in session object
					req.getSession().setAttribute("list", listOfBranches);
					//display adminDashboard
					resp.getWriter().print("<h1>"+id+" Bracnh Updated Success Fully</h1>");;
					req.getRequestDispatcher("adminDashboard.jsp").include(req, resp);
				}
				else
				{
					resp.getWriter().print("<h1>DO LOGIN FIRST</h1>");
					req.getRequestDispatcher("login.jsp").include(req, resp);
				}
		
		

	}

}
