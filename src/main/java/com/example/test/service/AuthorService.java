package com.example.test.service;


import com.example.test.dao.AuthorDao;
import com.example.test.entity.Author;

import java.util.List;

public class AuthorService {

    private AuthorDao authorDao = new AuthorDao();

    public Author findAuthor(int id) {
        return authorDao.findById(id);
    }

    public void saveAuthor(Author author) {
        authorDao.save(author);
    }

    public void deleteAuthor(Author author) {
        authorDao.delete(author);
    }

    public void updateAuthor(Author author) {
        authorDao.update(author);
    }

    public List<Author> findAllAuthors() {
        return authorDao.findAll();
    }
}
