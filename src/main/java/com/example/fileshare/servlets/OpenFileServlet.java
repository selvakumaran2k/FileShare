package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;
import com.example.fileshare.processingPack.Resource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OpenFileServlet", value = "/open/*")
public class OpenFileServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String arr[] = request.getRequestURI().split("/");
        String resourceName = arr[arr.length - 1];
        String openType = arr[arr.length - 2];
        Resource resource = connector.getResource(resourceName);
        System.out.println(resource);
        session.setAttribute("targetResource",resource);

        List<String[]> userGroups = connector.getGroupsForUser(username);
        session.setAttribute("userGroups", userGroups);

        String tagetPage = "/GroupFilePage.jsp";
        if(openType.equals("single"))
        {
            tagetPage = "/singleShareFilePage.jsp";
        }else if(openType.equals("owner"))
        {
            tagetPage = "/FilePage.jsp";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(tagetPage);
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
