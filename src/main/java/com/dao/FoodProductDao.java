package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.dto.FoodProduct;

public class FoodProductDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction et = em.getTransaction();
	
	public void createFoodProduct(FoodProduct foodProduct)
	{
		et.begin();
		em.merge(foodProduct);
		et.commit();
	}
	public FoodProduct findFoodProductById(int id)
	{
		return em.find(FoodProduct.class,id);
	}

	public void updateFoodproduct(FoodProduct fp) {
		// TODO Auto-generated method stub
		et.begin();
		em.merge(fp);
		et.commit();
	}
	public void deleteFoodProduct(FoodProduct f) {
		// TODO Auto-generated method stub
		et.begin();
		em.remove(f);
		et.commit();
	}

}
