package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.dto.Menu;

public class MenuDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction et = em.getTransaction();
	
	public Menu saveMenu(Menu menu)
	{
		et.begin();
		menu=em.merge(menu);
		et.commit();
		return menu;
	}

	public Menu findMenuById(int id) {
		// TODO Auto-generated method stub
		return em.find(Menu.class,id);
	}

	

}
