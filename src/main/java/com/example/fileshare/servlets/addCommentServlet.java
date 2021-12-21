package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "addCommentServlet", value = "/addComment")
public class addCommentServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("comment");
        String resourceID = request.getParameter("resourceID");
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        connector.addComment(resourceID,comment,username);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/open/group/"+resourceID);
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
