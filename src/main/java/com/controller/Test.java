package com.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dto.Branch;
import com.dto.Customer;
import com.dto.FoodOrder;
import com.dto.Item;

public class Test {
	
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("dev");
		
//		EntityManager em = emf.createEntityManager();
//
//		List<FoodOrder> list = em.createQuery("select f from FoodOrder f where status=false").getResultList();
//		List<Item> listOfItems=null;
//		for(FoodOrder f:list)
//		{
//			
//			if(f.getBranchId()==2)
//			{
//				
//				listOfItems = f.getItems();
//			}
//		}
//		
//		for(Item i:listOfItems)
//			System.out.println(i.getId());
	}

}
	