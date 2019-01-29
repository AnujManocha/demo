package com.test.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {
	private static SessionFactory sessionFactory = buildSessionFactory();
	 
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        //	Configuration config = new AnnotationConfiguration().configure(new File("hibernate.cfg.xml"));
//        	return config.buildSessionFactory();
        //	return new Configuration().configure().buildSessionFactory();
        	Configuration configuration = new Configuration();
        	configuration.configure();
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
        	configuration.getProperties()).build();

        	sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);
        	return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }
}
