package com.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestDao {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		System.out.println(emf);
	}

}
