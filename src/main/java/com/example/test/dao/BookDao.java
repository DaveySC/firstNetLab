package com.example.test.dao;


import com.example.test.entity.Book;
import com.example.test.entity.Client;
import com.example.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class BookDao {

    public Book findById(int id) {
       Session session = HibernateUtil.getSession();
        Book book = session.find(Book.class, id);
        session.close();
        return book;
    }

    public void save(Book book) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    public void update(Book book) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
        session.close();
    }

    public void delete(Book book) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(book);
        transaction.commit();
        session.close();
    }

    public List<Book> findAll() {
        Session session = HibernateUtil.getSession();
        List<Book> books = session.createQuery("SELECT a FROM Book a", Book.class).getResultList();
        session.close();
        return books;
    }

    public List<Book> findAllBooksBySearchValue(String searchValue) {
        Session session = HibernateUtil.getSession();
        List<Book> books = session.createQuery("SELECT a FROM Book a", Book.class).getResultList();
        session.close();
        books.removeIf(book -> !book.getName().contains(searchValue));
        return books;
    }

}
