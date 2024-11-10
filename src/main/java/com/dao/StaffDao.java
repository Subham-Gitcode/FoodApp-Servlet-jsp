package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.dto.Staff;

public class StaffDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction et = em.getTransaction();
	
	public void saveStaff(Staff staff)
	{
		et.begin();
		em.merge(staff);
		et.commit();
	}

	
	public Staff findStaffById(int id) {
		// TODO Auto-generated method stub
		return em.find(Staff.class, id);
	}

}
