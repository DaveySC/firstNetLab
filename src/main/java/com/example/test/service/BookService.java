package com.example.test.service;



import com.example.test.dao.BookDao;
import com.example.test.entity.Book;

import javax.servlet.annotation.WebServlet;
import java.util.List;


public class BookService {
    private BookDao bookDao = new BookDao();

    public Book findBook(int id) {
        return bookDao.findById(id);
    }

    public void saveBook(Book book) {
        bookDao.save(book);
    }

    public void deleteBook(Book book) {
        bookDao.delete(book);
    }

    public void updateBook(Book book) {
        bookDao.update(book);
    }

    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    public List<Book> findAllBooksBySearchValue(String searchValue) {
        return bookDao.findAllBooksBySearchValue(searchValue);
    }
}
