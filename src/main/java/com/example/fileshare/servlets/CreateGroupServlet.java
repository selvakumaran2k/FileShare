package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateGroupServlet", value = "/createGroup")
public class CreateGroupServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupName = request.getParameter("name");
        String members = request.getParameter("members");
        String membersList[] = members.split(",");

        RequestDispatcher requestDispatcher = null;
        boolean isGroupCreated = connector.createGroup(groupName, membersList);

        if(isGroupCreated)
        {
            requestDispatcher = request.getRequestDispatcher("/home");
        }else
        {
            requestDispatcher = request.getRequestDispatcher("/FileShare/createGroup.jsp");
        }
        requestDispatcher.forward(request,response);

    }
}
