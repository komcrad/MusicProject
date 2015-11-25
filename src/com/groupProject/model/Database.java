
package com.groupProject.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*
 * Class used for generic operations on the database
 */
public class Database {
	static private SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
	private Database() {}
	
	
	/**
	 * Gets a session from the session factory
	 * @return  the session created by the session factory
	 */
	public static Session getNewSession() {
		return sessionFactory.openSession();
	}
	
	/**
	 * Saves an object to the database
	 * @param obj  The object to be saved to the database
	 * @return  if exception occurs false is returned; else true
	 */
	public static boolean saveToDatabase(Object obj) {
		Session session = getNewSession();
		try {
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		session.close();
		return true;
	}
	
	/**
	 * Takes in a list of objects and saves them to the database.
	 * @param objs  The list of objects you want to save
	 */
	public static void saveToDatabase(List<Object> objs) {
		objs.forEach(obj -> {
			saveToDatabase(obj);
		});
	}
	
	public static List runQuery(String queryString) {
		Session session = getNewSession();
		Query query = session.createQuery(queryString);
		List results = query.list();
		session.close();
		return results;
	}
	public static void kill() {
		sessionFactory.close();
	}
}

