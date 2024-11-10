package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.dto.FoodOrder;

public class FoodOrderDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction et = em.getTransaction();
	
	public void saveFoodOrder(FoodOrder foodOrder)
	{
		et.begin();
		em.merge(foodOrder);
		et.commit();
	}
	
	public FoodOrder foodOrderById(int id)
	{
		return em.find(FoodOrder.class,id);
	}
	
	
	public List<FoodOrder> findByFoodOrderStatus()
	{
		List<FoodOrder> list = em.createQuery("select f from FoodOrder f where status=false").getResultList();
		if(!list.isEmpty())
			return list;
		return null;
			
	}

}
