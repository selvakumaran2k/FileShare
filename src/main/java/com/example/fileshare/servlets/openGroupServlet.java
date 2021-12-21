package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;
import com.example.fileshare.processingPack.Group;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "openGroupServlet", value = "/openGroup/*")
public class openGroupServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String arr[] = request.getRequestURI().split("/");
        String groupLink = arr[arr.length - 1];
        System.out.println(request.getRequestURI());
        System.out.println(groupLink);
        Group group  = connector.getGroup(groupLink);
        System.out.println(group);
        session.setAttribute("targetGroup",group);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/GroupPage.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
