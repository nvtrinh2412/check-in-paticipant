package main;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pojo.Account;


public class Main {
    public static void main(String[] args) {
        System.out.println("Project started..");
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory ();

        Account newAccount = new Account(5, "123", "123");

        Session session = factory.openSession();
        session.beginTransaction();
        session.persist(newAccount);
        session.getTransaction().commit();
        session.close();




    }
}
