package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "JoinServlet", value = "/join/*")
public class JoinServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String arr[] = request.getRequestURI().split("/");
        String groupLink = arr[arr.length - 1];

        RequestDispatcher requestDispatcher ;

        if(connector.joinGroup(username, groupLink))
        {
            requestDispatcher = request.getRequestDispatcher("/openGroup/"+groupLink);
        }else
        {
            requestDispatcher = request.getRequestDispatcher("/oursPage.jsp");
        }
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
