package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveResourceServlet", value = "/removeResource/*")
public class RemoveResourceServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RemoveResourceServlet called");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String arr[] = request.getRequestURI().split("/");
        String group_and_resource = arr[arr.length - 1];
        String groupID = group_and_resource.substring(group_and_resource.lastIndexOf("_")+1);
        String resourceID = group_and_resource.substring(0,group_and_resource.lastIndexOf("_"));

        connector.removeResourceFromGroup(groupID,resourceID);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/openGroup/"+groupID);
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
