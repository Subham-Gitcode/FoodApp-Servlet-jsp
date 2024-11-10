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

@WebServlet("/deleteBranch")
public class DeleteBranchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User admin = (User) req.getSession().getAttribute("admin");// admin
		if(admin!=null)
		{
			int branchId=Integer.parseInt(req.getParameter("id"));
			//find By branch id
			BrachDao dao=new BrachDao();
			Branch branch = dao.findBranchById(branchId);
			//delet
			//
			
//			branch.setUser(null);//de refered
			dao.deleteBranch(branch);//delete
			resp.getWriter().print("<h1>"+branchId+" Deleted Successfully</h1>");
			
			//based on find find All Branches
			int adminId=admin.getId();
			UserDao dao1=new UserDao();
			
			User adminUser = dao1.findUserById(adminId);
			List<Branch> listOfBranches = adminUser.getBranchs();
			//store in session object
			req.getSession().setAttribute("list", listOfBranches);
			//display adminDashboard
			req.getRequestDispatcher("adminDashboard.jsp").include(req, resp);
		}
		else
		{
			resp.getWriter().print("<h1>DO LOGIN FIRST</h1>");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}
	}
}
