package com.example.test.servlet;

import com.example.test.entity.Author;
import com.example.test.entity.Book;
import com.example.test.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FindBookServlet", value = "/findBook")
public class FindBookServlet extends HttpServlet {
    BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/jsp/findBook.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchValue = (String) request.getParameter("searchValue");
        List<Book> bookList = bookService.findAllBooksBySearchValue(searchValue);
        request.getSession().setAttribute("bookList",bookList);
        doGet(request,response);
    }
}
