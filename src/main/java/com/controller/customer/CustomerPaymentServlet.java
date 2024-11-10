package com.controller.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.Item;

@WebServlet("/payment")
public class CustomerPaymentServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Double needToPay=(Double) req.getSession().getAttribute("pay");
		double payed=Double.parseDouble(req.getParameter("payment"));
		
		if(needToPay==payed)
		{
			HttpSession hs = req.getSession();
			List<Item> list = (List<Item>)hs.getAttribute("orderitems");
			hs.setAttribute("orderitems", list);
			hs.setAttribute("cid", Integer.parseInt(req.getParameter("id")));
			hs.setAttribute("cname", req.getAttribute("name"));
			hs.setAttribute("bid", Integer.parseInt(req.getParameter("bid")));
			hs.setAttribute("fid", Integer.parseInt(req.getParameter("fid")));
			hs.setAttribute("payment", Double.parseDouble(req.getParameter("payment")));
			resp.getWriter().write("<h1>PAID</h1>");
			req.getRequestDispatcher("mail").include(req, resp);
		}
		else
		{
			resp.getWriter().write("<h1>NOT PAID</h1>");
			req.getRequestDispatcher("customerpayment.jsp").include(req, resp);
		}
	}

}
