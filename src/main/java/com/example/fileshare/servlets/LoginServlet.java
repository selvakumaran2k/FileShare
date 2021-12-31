package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private DBConnector connector = DBConnector.getInstance();
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private static Properties properties = new Properties();
    static {
//            properties.load(new FileInputStream("log4j.properties"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        logger.info("user name : "+username);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Local Login called....");
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        logger.info("user name : "+username);
        logger.info("user password :"+password);
        System.out.println(logger.getName());

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

