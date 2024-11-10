package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.dto.Branch;
import com.dto.User;

public class UserDao {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction et = em.getTransaction();

	public void saveUser(User user) {
		et.begin();
		em.persist(user);
		et.commit();
	}

	public User findByEmail(String email) {

		// custom query
		Query q = em.createQuery("select u from User u where email=?1");

		q.setParameter(1, email);

		List<User> list = q.getResultList();
		if (!list.isEmpty())
			return list.get(0);

		return null;

	}
	
	public void updateUser(User user)
	{
		
		et.begin();
		em.merge(user);
		et.commit();
	}

	public User findUserById(int adminId) {
		// TODO Auto-generated method stub
		return em.find(User.class, adminId);
	}

	public void deleteUser(User staff) {
		// TODO Auto-generated method stub
		et.begin();
		em.remove(staff);
		et.commit();
	}

}
