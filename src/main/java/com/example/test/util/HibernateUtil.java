package com.example.test.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static  SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
           ex.printStackTrace();
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
