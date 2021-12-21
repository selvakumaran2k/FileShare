package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;
import com.example.fileshare.processingPack.Resource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "DownloadServlet", value = "/download/*")
public class DownloadServlet extends HttpServlet {
    DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String arr[] = request.getRequestURI().split("/");
        String resourceID = arr[arr.length - 1];
        Resource resource = connector.getResource(resourceID);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + resource.getFileName());
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(Files.readAllBytes(Paths.get(resource.getLocation())));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
