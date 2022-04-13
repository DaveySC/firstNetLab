package com.example.test.servlet;

import com.example.test.entity.Book;
import com.example.test.entity.Client;
import com.example.test.exeption.WrongIDException;
import com.example.test.service.AuthorService;
import com.example.test.service.BookService;
import com.example.test.util.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookService", value = "/catalog")
public class BookServlet extends HttpServlet {
    Logger logger = LogManager.getLogger(BookServlet.class);
    BookService bookService = new BookService();
    AuthorService authorService = new AuthorService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<Book> bookList = new ArrayList<>(bookService.findAllBooks());
        request.getSession().setAttribute("bookList",bookList);
        request.getRequestDispatcher("/jsp/catalog.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("status").equals("edit")) {
            editBook(request,response);
            return;
        } else if (request.getParameter("status").equals("add")) {
            addBook(request,response);
            return;
        } else if(request.getParameter("status").equals("delete")) {
            deleteBook(request,response);
            return;
        }
        doGet(request,response);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String) request.getParameter("id");
        int[] ids = Converter.convertId(id);
        if (ids.length != 1) {
            request.getSession().setAttribute("errorMessage", "wrong input data");
            logger.error("Exceptions happen", new WrongIDException("wrong input data"));
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }
        Book book = bookService.findBook(ids[0]);
        if (book == null) {
            logger.error(new WrongIDException("wrong book id or client id"));
            request.getSession().setAttribute("errorMessage", "wrong book id or client id");
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }

        bookService.deleteBook(book);
        logger.info("was deleted book with id : "  + id);
        doGet(request,response);
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String authorStr = request.getParameter("authors");
        Book book = new Book(category, name);
        int[] authors = authorStr.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .toArray();

        for (Integer id : authors) {
            book.addAuthor(authorService.findAuthor(id));
        }
        bookService.saveBook(book);
        logger.info("was added book with name : " + name + " category : "  + category);
        doGet(request,response);
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String) request.getParameter("id");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String authorStr = request.getParameter("authors");

        int[] ids = Converter.convertId(id);
        if (ids.length != 1) {
            request.getSession().setAttribute("errorMessage", "wrong input data");
            logger.error("Exceptions happen", new WrongIDException("wrong input data"));
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }
        Book book = bookService.findBook(ids[0]);
        if (book == null) {
            logger.error(new WrongIDException("wrong book id or client id"));
            request.getSession().setAttribute("errorMessage", "wrong book id or client id");
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }

        book.setCategory(category);
        book.setName(name);
        book.setAuthors(new ArrayList<>());
        if (authorStr == null) {
            authorStr = "";
        }
        int[] authors = authorStr.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .toArray();

        for (Integer value : authors) {
            book.addAuthor(authorService.findAuthor(value));
        }
        bookService.updateBook(book);
        logger.info("was edited book with id : " + id);
        doGet(request,response);
    }
}
