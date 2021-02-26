package com.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
		
		//Unit of Work
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			//HQL --> Hibernate Query Language
			//ASC - Ascending
			//DESC - Descending
			//Select * from  city			
			
			  List<City> cities =
			  session.createQuery("from City c ORDER BY c.name").getResultList();
			  
			  for(City city:cities) { System.out.println(city.getName()); }
			 
			
			//INSERT
			/*
			 * City city = new City(); city.setName("Düzce 10"); city.setCountryCode("TUR");
			 * city.setDistrict("Karadeniz"); city.setPopulation(100000);
			 * 
			 * session.save(city); //session.save(city); burada bu þekilde bir ekleme vs
			 * daha yaparsak eðer hatalý olursa ilki de iptal olur. //Hibernate session'la
			 * bu iþlemi otomatik yapýyor.
			 */
			
			//UPDATE
			/*
			 * City city = session.get(City.class, 4080);
			 * city.setPopulation(110000);
			 * 
			 * session.save(city);
			 */
			
			//DELETE
			/*
			 * City city = session.get(City.class, 4080); session.delete(city);
			 */
			
			
			session.getTransaction().commit();		 
			System.out.println("Þehir Silindi!");
		}finally {
			factory.close();
		}

	}

}
