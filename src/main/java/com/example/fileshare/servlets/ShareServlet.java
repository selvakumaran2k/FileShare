package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShareServlet", value = "/share")
public class ShareServlet extends HttpServlet {
    private static final DBConnector connector = DBConnector.getInstance();
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

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
        System.out.println(targetName+" "+resource+" "+target);

        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher = null;
        if(target.equals("user"))
        {
            if(connector.shareResource(targetName,resource,"1"))
            {
                System.out.println("Shared");
                logger.info("Resource with id  " + resource + "shared to" +targetName + "by"+ target);
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
                logger.info("Resource with id  " + resource + "shared to Group " +targetName + " by "+ target);
                session.setAttribute("updateStatus","Success sharing resource");
                requestDispatcher = request.getRequestDispatcher("/openGroup/"+targetName);
            }else
            {
                session.setAttribute("updateStatus","Failed sharing");
                requestDispatcher = request.getRequestDispatcher("/home");
            }
        }
        requestDispatcher.forward(request,response);
    }
}
