package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.List;
import java.io.IOException;

@WebServlet(name = "OursServlet", value = "/ours")
public class OursServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession();
        String username  = (String) session.getAttribute("username");

        List<String[]> userGroups = connector.getGroupsForUser(username);
        session.setAttribute("userGroups", userGroups);

        List<String[]> userRequest = connector.getJoinGroupRequest(username);
        session.setAttribute("userRequest", userRequest);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("oursPage.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }
}
