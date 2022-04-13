package com.example.test.dao;

import com.example.test.entity.AccountingRecords;
;
import com.example.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AccountingRecordsDao {

    public AccountingRecords findById(int id) {
        try(Session session = HibernateUtil.getSession()) {
            return session.get(AccountingRecords.class, id);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public void save(AccountingRecords accountingRecords) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(accountingRecords);
        transaction.commit();
        session.close();
    }

    public void update(AccountingRecords accountingRecords) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(accountingRecords);
        transaction.commit();
        session.close();
    }

    public void delete(AccountingRecords accountingRecords) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(accountingRecords);
        transaction.commit();
        session.close();
    }

    public List<AccountingRecords> findAll() {
        Session session = HibernateUtil.getSession();
        List<AccountingRecords> accountingRecords = session.createQuery("SELECT a FROM AccountingRecords a", AccountingRecords.class).getResultList();
        session.close();
        return accountingRecords;
    }
}
