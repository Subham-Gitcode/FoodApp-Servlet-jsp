package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.dto.Branch;

public class BrachDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction et = em.getTransaction();

	public void createBranch(Branch b) {
		// TODO Auto-generated method stub
		et.begin();
		em.merge(b);
		et.commit();
		em.clear();

	}

	public Branch findBranchById(int branchId) {
		return em.find(Branch.class, branchId);
	}

	public void deleteBranch(Branch branch) {
		// TODO Auto-generated method stub
		et.begin();
		em.remove(branch);
		et.commit();
		em.clear();
	}

	public List<Branch> findAllBranch() {
		return em.createQuery("select b from Branch b where status=false").getResultList();
	}

	@Transactional
	public void updateBranch(Branch banch) {
		// TODO Auto-generated method stub
		et.begin();
		em.merge(banch);
		et.commit();
		em.clear();
	}

	public List<Branch> findAllBranches() {
		// TODO Auto-generated method stub
		List list = em.createQuery("select b from Branch b where status=true").getResultList();
		if (list.isEmpty())
			return null;
		return list;
	}

}
