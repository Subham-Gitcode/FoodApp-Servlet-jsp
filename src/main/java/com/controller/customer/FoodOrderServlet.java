package com.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BrachDao;
import com.dao.CustomerDao;
import com.dao.FoodOrderDao;
import com.dao.FoodProductDao;
import com.dto.Branch;
import com.dto.Customer;
import com.dto.FoodOrder;
import com.dto.FoodProduct;
import com.dto.Item;

@WebServlet("/foodorder")
public class FoodOrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// foodorder?fp=1&quantity=1&fp=2&quantity=2&fp=4&quantity=3
//
		Customer customerObject = (Customer) req.getSession().getAttribute("customer");
		Branch b = (Branch) req.getSession().getAttribute("branch");
		// =====================================================
		// ==================================================
		String[] fpIds = req.getParameterValues("fp");
		String[] qunatities = req.getParameterValues("quantity");// [0,3]
		// remove 0 from quantities
		ArrayList<String> al = new ArrayList<>();
		for (String ele : qunatities) {
			if (!ele.equals("0"))
				al.add(ele);
		} // [3]
//
//		// fetch Food Product from database and keep into listOfFoodProduct list
		List<FoodProduct> listOfFoodProduct = new ArrayList<>();
		FoodProductDao foodProductDao = new FoodProductDao();
		for (int i = 0; i < fpIds.length; i++) {
			int foodProductId = Integer.parseInt(fpIds[i]);

			FoodProduct foodProductobject = foodProductDao.findFoodProductById(foodProductId);
			listOfFoodProduct.add(foodProductobject);
		}
//
//		// create List of items
		List<Item> items = new ArrayList<>();

		double totalPrice = 0;
		int i = 0;
		for (FoodProduct fp : listOfFoodProduct) {

//			System.out.println(fp.getName()+" "+fp.getPrice());//100.0
			Item item = new Item();
			item.setProductName(fp.getName());
			item.setType(fp.getType());
			int quantity = Integer.parseInt(al.get(i));
			item.setQuantity(quantity);// 0
			double price = fp.getPrice() * quantity;
			item.setPrice(price);// 0.0
			totalPrice += price;// total items price
			items.add(item);
			i++;// increase index
		}

////		// newly create FoodOrder object
		FoodOrder foodOrder = new FoodOrder();
		foodOrder.setTotalprice(totalPrice);
		foodOrder.setStatus(false);//by default set false
		foodOrder.setBranchId(b.getId());
		foodOrder.setCustId(customerObject.getId());
		foodOrder.setItems(items);//// foodOrder mapping with items

//		System.out.println(customerObject);// com.dto.Customer@7e0ec7c7
		System.out.println(customerObject.getId());
		CustomerDao customerDao = new CustomerDao();

		Customer newCustObject = customerDao.findCustomerByID(customerObject.getId());
//		System.out.println(newCustObject);//
		// create new FoodOrder List
		ArrayList<FoodOrder> foodOrders = new ArrayList<>(newCustObject.getFoodOrders());
		foodOrders.add(foodOrder);
		newCustObject.setFoodOrders(foodOrders);
		Customer c = customerDao.saveCustomer(newCustObject);// detch object

		Customer custobject = customerDao.findCustomerByID(c.getId());

//		System.out.println(custobject);// com.dto.Customer@48ee7286
//
//		System.out.println(c);// com.dto.Customer@16d8f4ba

		// com.dto.Customer@48ee7286
		// com.dto.Customer@16d8f4ba
		System.out.println(c.getId());

		List<FoodOrder> list = c.getFoodOrders();//
		System.out.println(list.get(list.size()-1).getId());
		FoodOrderDao foodOrderDao=new FoodOrderDao();
		req.getSession().setAttribute("foodorder",foodOrderDao.foodOrderById(list.get(list.size()-1).getId()));
		// =================================================================//

//		// logic for mapping with branch

		BrachDao brachDao = new BrachDao();
		Branch branchObject = brachDao.findBranchById(b.getId());

		List<Customer> listModifylist = branchObject.getCustomers();

		if (!listModifylist.isEmpty()) {
//			resp.getWriter().print("<h1>NOt DONE</h1>");
			Iterator<Customer> i1 = listModifylist.iterator();
			while (i1.hasNext()) {
				Customer cust = i1.next();
				System.out.println(cust);
				if (cust.getId() == c.getId())
					i1.remove();
			}
			listModifylist.add(c);
			branchObject.setCustomers(listModifylist);
//			resp.getWriter().print("<h1>NOt DONE</h1>");

		} else {
			ArrayList<Customer> listModify = new ArrayList<>(listModifylist);
			listModify.add(c);
			branchObject.setCustomers(listModify);
		}

//		System.out.println(branchObject.getCustomers());// [com.dto.Customer@3ea022d2, com.dto.Customer@16d8f4ba]
//		for (Customer c1 : branchObject.getCustomers())//
//			System.out.println(c1);
		// mapping
		brachDao.updateBranch(branchObject);

//		resp.getWriter().print("<h1>DONE</h1>");
		req.getRequestDispatcher("customerreciept.jsp").include(req, resp);

//
//		// =================================================================//

	}

}
