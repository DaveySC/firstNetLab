package com.example.test.servlet;

import com.example.test.entity.AccountingRecords;
import com.example.test.entity.Book;
import com.example.test.entity.Client;
import com.example.test.exeption.WrongIDException;
import com.example.test.service.AccountingRecordsService;
import com.example.test.service.BookService;
import com.example.test.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "TakeBookServlet", value = "/takeBook")
public class TakeBookServlet extends HttpServlet {
    private static final Logger logger =  LogManager.getLogger(TakeBookServlet.class.getName());
    ClientService clientService = new ClientService();
    BookService bookService = new BookService();
    AccountingRecordsService accountingRecordsService = new AccountingRecordsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<AccountingRecords> accountingRecordsList = new ArrayList<>(accountingRecordsService.findAllRecords());
        request.getSession().setAttribute("accountingRecordsList", accountingRecordsList);
        request.getRequestDispatcher("/jsp/takeBook.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("status").equals("add")) {
            add(request,response);
            return;
        } else if (request.getParameter("status").equals("delete")) {
            delete(request,response);
            return;
        } else if (request.getParameter("status").equals("edit")){
            edit(request,response);
            return;
        }
        doGet(request,response);
    }


    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        String bookId = request.getParameter("bookId");
        String returnDate = request.getParameter("returnDate");
        int [] clientsId = clientId.chars().filter(Character::isDigit).map(Character::getNumericValue).toArray();
        int [] booksId = bookId.chars().filter(Character::isDigit).map(Character::getNumericValue).toArray();

        if (clientsId.length != 1 || booksId.length != 1) {

            request.getSession().setAttribute("errorMessage", "wrong input data");
            logger.error("Exceptions happen",new WrongIDException("wrong input data"));
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }

        Book book = bookService.findBook(clientsId[0]);
        Client client = clientService.findClient(booksId[0]);

        if (book == null || client == null) {
            logger.error(new WrongIDException("wrong book id or client id"));
            request.getSession().setAttribute("errorMessage", "wrong book id or client id");
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        AccountingRecords accountingRecords = new AccountingRecords();
        accountingRecords.setBook(book);
        accountingRecords.setClient(client);
        accountingRecords.setReceiptDate(new Date());
        Date date = null;
        try {
            date = format.parse(returnDate);
        } catch (ParseException ex) {
            date = new Date();
            logger.error("data error", ex);
        }
        accountingRecords.setReturnDate(date);
        accountingRecordsService.saveRecord(accountingRecords);
        logger.info("created new accountingRecord with values : "
                + accountingRecords.getId() + " "
                + accountingRecords.getBook().getId() + " "
                + accountingRecords.getClient().getId() + " ");
        doGet(request,response);

    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        AccountingRecords accountingRecords = accountingRecordsService.findRecord(Integer.parseInt(id));
        accountingRecords.setBook(null);
        accountingRecords.setClient(null);
        accountingRecordsService.deleteRecord(accountingRecords);
        doGet(request,response);

    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String clientId = request.getParameter("clientId");
        String bookId = request.getParameter("bookId");
        String returnDate = request.getParameter("returnDate");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        AccountingRecords accountingRecords =  accountingRecordsService.findRecord(Integer.parseInt(id));
        accountingRecords.setBook(bookService.findBook(Integer.parseInt(bookId)));
        accountingRecords.setClient(clientService.findClient(Integer.parseInt(clientId)));
        accountingRecords.setReceiptDate(new Date());
        Date date = null;
        try {
            date = format.parse(returnDate);
        } catch (ParseException ex) {
            date = new Date();
            ex.printStackTrace();
        }
        accountingRecords.setReturnDate(date);
        accountingRecordsService.updateRecord(accountingRecords);
        doGet(request,response);
    }

}
