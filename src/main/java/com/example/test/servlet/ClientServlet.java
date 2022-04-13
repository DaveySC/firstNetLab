package com.example.test.servlet;

import com.example.test.entity.Client;
import com.example.test.exeption.WrongIDException;
import com.example.test.service.ClientService;

import com.example.test.util.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ClientServlet", value = "/clients")
public class ClientServlet extends HttpServlet {
    private String message;
    Logger logger = LogManager.getLogger(ClientServlet.class);
    ClientService clientService = new ClientService();

    public void init() {
        message = "Hello World!";
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        List<Client> clientList = new ArrayList<>(clientService.findAllClients());
        request.getSession().setAttribute("clientList", clientList);
        request.getRequestDispatcher("/jsp/clients.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("status").equals("edit")) {
            editUser(request,response);
            return;
        } else if (request.getParameter("status").equals("add")) {
            addUser(request,response);
            return;
        } else if(request.getParameter("status").equals("delete")) {
            deleteUser(request,response);
            return;
        }
        doGet(request,response);
    }

   private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String firstName = (String) request.getParameter("firstName");
       String secondName = (String) request.getParameter("secondName");
       clientService.saveClient(new Client(firstName, secondName));
       logger.info("added new client with name : " + firstName + " second name : " + secondName);
       doGet(request,response);
   }

   private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String) request.getParameter("id");
        String firstName = (String) request.getParameter("firstName");
        String secondName = (String) request.getParameter("secondName");
        int[] ids = Converter.convertId(id);
        if (ids.length != 1) {
            request.getSession().setAttribute("errorMessage", "wrong input data");
            logger.error("Exceptions happen", new WrongIDException("wrong input data"));
            request.getRequestDispatcher("/error").forward(request,response);
            return;
        }

        Client client = clientService.findClient(ids[0]);
       if (client == null) {
           logger.error(new WrongIDException("wrong book id or client id"));
           request.getSession().setAttribute("errorMessage", "wrong book id or client id");
           request.getRequestDispatcher("/error").forward(request,response);
           return;
       }


        client.setFirstName(firstName);
        client.setSecondName(secondName);
        clientService.updateClient(client);
        logger.info("edited user with id : " + id + "to " + firstName + " " + secondName);
        doGet(request,response);
   }

   private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String id = (String) request.getParameter("id");

       int[] ids = Converter.convertId(id);
       if (ids.length != 1) {
           request.getSession().setAttribute("errorMessage", "wrong input data");
           logger.error("Exceptions happen", new WrongIDException("wrong input data"));
           request.getRequestDispatcher("/error").forward(request,response);
           return;
       }
       Client client = clientService.findClient(ids[0]);
       if (client == null) {
           logger.error(new WrongIDException("wrong book id or client id"));
           request.getSession().setAttribute("errorMessage", "wrong book id or client id");
           request.getRequestDispatcher("/error").forward(request,response);
           return;
       }
       clientService.deleteClient(client);
       logger.info("user was deleted with id : " + id);
       doGet(request,response);
   }
}