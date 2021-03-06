package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;
import com.example.fileshare.processingPack.Resource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "toMeServlet", value = "/toMe")
public class toMeServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("To me servlet called...");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        List<Resource> resources = connector.getSharedResourcesForUser(username);
        session.setAttribute("sharedResources", resources);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("toMePage.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
