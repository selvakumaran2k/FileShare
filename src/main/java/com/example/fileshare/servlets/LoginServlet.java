package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher RequestsDispatcherObj = null;
        RequestsDispatcherObj = request.getRequestDispatcher("/index.jsp");
        RequestsDispatcherObj.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Local Login called....");
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        System.out.println(username + " " + password);
        boolean isVaildUser = connector.validateUser(username, password);

        RequestDispatcher RequestsDispatcherObj = null;
        HttpSession session = request.getSession();
        if (isVaildUser) {
            System.out.println("User validated");
            session.setAttribute("username", username);
            RequestsDispatcherObj = request.getRequestDispatcher("/home");
        } else {
            RequestsDispatcherObj = request.getRequestDispatcher("/index.jsp");
        }
        RequestsDispatcherObj.forward(request, response);

    }

}

