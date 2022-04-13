package com.example.test.service;

import com.example.test.dao.AccountingRecordsDao;
import com.example.test.dao.AuthorDao;
import com.example.test.entity.AccountingRecords;
import com.example.test.entity.Author;

import java.util.List;

public class AccountingRecordsService {
    private AccountingRecordsDao accountingRecordsDao = new AccountingRecordsDao();

    public AccountingRecords findRecord(int id) {
        return accountingRecordsDao.findById(id);
    }

    public void saveRecord(AccountingRecords accountingRecords) {
        accountingRecordsDao.save(accountingRecords);
    }

    public void deleteRecord(AccountingRecords accountingRecords) {
        accountingRecordsDao.delete(accountingRecords);
    }

    public void updateRecord(AccountingRecords accountingRecords) {
        accountingRecordsDao.update(accountingRecords);
    }

    public List<AccountingRecords> findAllRecords() {
        return accountingRecordsDao.findAll();
    }
}
