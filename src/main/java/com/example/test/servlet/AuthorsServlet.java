package com.example.test.servlet;

import com.example.test.entity.Author;
import com.example.test.entity.Book;
import com.example.test.exeption.WrongIDException;
import com.example.test.service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.test.util.Converter;
import org.apache.logging.log4j.*;

@WebServlet(name = "AuthorServlet", value = "/authors")
public class AuthorsServlet extends HttpServlet {
    AuthorService authorService = new AuthorService();
    private static final Logger logger =  LogManager.getLogger(AuthorsServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<Author> authorList = new ArrayList<>(authorService.findAllAuthors());
        request.getSession().setAttribute("authorList", authorList);
        request.getRequestDispatcher("/jsp/authors.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("status").equals("edit")) {
            editAuthor(request,response);
            return;
        } else if (request.getParameter("status").equals("add")) {
            addAuthor(request,response);
            return;
        } else if(request.getParameter("status").equals("delete")) {
            deleteAuthor(request,response);
            return;
        }
        doGet(request,response);

    }

    private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =  request.getParameter("id");
        int[] ids = Converter.convertId(id);
        if (ids.length != 1) {
            request.getSession().setAttribute("errorMessage", "wrong input data");
            logger.error("Exceptions happen", new WrongIDException("wrong input data"));
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }
        Author author = authorService.findAuthor(ids[0]);
        if (author == null) {
            logger.error(new WrongIDException("wrong book id or client id"));
            request.getSession().setAttribute("errorMessage", "wrong book id or client id");
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }

        authorService.deleteAuthor(author);
        logger.info("author was deleted with id : " + id);
        doGet(request,response);

    }

    private void addAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = (String) request.getParameter("name");
        String secondName = (String) request.getParameter("secondName");
        authorService.saveAuthor(new Author(firstName, secondName));
        logger.info("was added author with name : " + firstName + " secondName : " + secondName);
        doGet(request,response);
    }

    private void editAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstName =  request.getParameter("name");
        String secondName =  request.getParameter("secondName");
        int[] ids = Converter.convertId(id);
        if (ids.length != 1) {
            request.getSession().setAttribute("errorMessage", "wrong input data");
            logger.error("Exceptions happen", new WrongIDException("wrong input data"));
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }
        Author author = authorService.findAuthor(ids[0]);
        if (author == null) {
            logger.error(new WrongIDException("wrong book id or client id"));
            request.getSession().setAttribute("errorMessage", "wrong book id or client id");
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }

        author.setFirstname(firstName);
        author.setLastname(secondName);
        authorService.updateAuthor(author);
        logger.info("was edited author with id : " + id);
        doGet(request,response);
    }
}
