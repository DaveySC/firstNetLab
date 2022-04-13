package com.example.test.dao;


import com.example.test.entity.Client;
import com.example.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao {

    public Client findById(int id) {
        Session session = HibernateUtil.getSession();
        Client cl = session.get(Client.class, id);
        session.close();
        return cl;
    }

    public void save(Client client) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }

    public void update(Client client) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(client);
        transaction.commit();
        session.close();
    }

    public void delete(Client client) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(client);
        transaction.commit();
        session.close();
    }

    public List<Client> findAll() {
        Session session = HibernateUtil.getSession();
        List<Client> clients = session.createQuery("SELECT a FROM Client a", Client.class).getResultList();
        session.close();
        return clients;
    }
}
