package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShareServlet", value = "/share")
public class ShareServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String arr[] = request.getRequestURI().split("/");
        String targetName = arr[arr.length - 1];
        String user_or_group = arr[arr.length - 2];
        String resourceID = arr[arr.length - 3];
        System.out.println(request.getRequestURI());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String targetName = request.getParameter("targetName");
        String resource= request.getParameter("resource");
        String target = request.getParameter("target");

        HttpSession session = (HttpSession) request.getSession();
        RequestDispatcher requestDispatcher = null;
        if(target.equals("user"))
        {
            if(connector.shareResource(targetName,resource,"1"))
            {
                System.out.println("Shared");
                session.setAttribute("updateStatus","Success sharing resource");
                requestDispatcher = request.getRequestDispatcher("/home");
            }else
            {
                session.setAttribute("updateStatus","Failed sharing");
                requestDispatcher = request.getRequestDispatcher("/home");
            }
        }else
        {
            if(connector.shareResourceToGroup(targetName,resource,"1"))
            {
                System.out.println("Shared");
                session.setAttribute("updateStatus","Success sharing resource");
                requestDispatcher = request.getRequestDispatcher("/home");
            }else
            {
                session.setAttribute("updateStatus","Failed sharing");
                requestDispatcher = request.getRequestDispatcher("/home");
            }
        }
        requestDispatcher.forward(request,response);
    }
}
