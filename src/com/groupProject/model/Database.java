
package com.groupProject.model;

import java.util.List;

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
		try {
			Session session = getNewSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
	public static void kill() {
		sessionFactory.close();
	}
	
	//test
	public static void main(String[] args) {
		User user = new User("sam", "password!", "male");
		saveToDatabase(user);
		user = new User("kornad", "fsdgaga!", "female");
		saveToDatabase(user);
		kill();
	}
}

