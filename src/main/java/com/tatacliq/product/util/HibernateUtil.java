package com.tatacliq.product.util;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateUtil {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	
	public SessionFactory getSessionFactory() {
		
		if(entityManagerFactory.unwrap(SessionFactory.class)==null) {
			throw new NullPointerException("factory is not hibernate factory");
		}
		return entityManagerFactory.unwrap(SessionFactory.class);
	}
	

}
