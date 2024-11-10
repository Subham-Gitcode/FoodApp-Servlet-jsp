package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.dto.Customer;
import com.dto.User;

public class CustomerDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction et = em.getTransaction();
	
	
	//save customer
	
	public Customer saveCustomer(Customer customer)
	{
		et.begin();
		Customer c = em.merge(customer);
		et.commit();
		em.clear();
		return c;
	}


	public Customer findCustomerByEmail(String email) {
		
		// custom query
				Query q = em.createQuery("select u from Customer u where email=?1");

				q.setParameter(1, email);

				List<Customer> list = q.getResultList();
				if (!list.isEmpty())
					return list.get(0);

				return null;
		
	}


	public Customer findCustomerByID(int id) {
		// TODO Auto-generated method stub
		return em.find(Customer.class,id);
	}

}
