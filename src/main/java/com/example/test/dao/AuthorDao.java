package com.example.test.dao;


import com.example.test.entity.Author;
import com.example.test.entity.Book;
import com.example.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class AuthorDao {
    public Author findById(int id) {
        try(Session session = HibernateUtil.getSession()) {
            return session.get(Author.class, id);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
       return null;

    }

    public void save(Author author) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(author);
        transaction.commit();
        session.close();
    }

    public void update(Author author) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(author);
        transaction.commit();
        session.close();
    }

    public void delete(Author author) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(author);
        transaction.commit();
        session.close();
    }

    public List<Author> findAll() {
        Session session = HibernateUtil.getSession();
        List<Author> authors = session.createQuery("SELECT a FROM Author a", Author.class).getResultList();
        session.close();
        return authors;
    }

}
