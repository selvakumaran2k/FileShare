package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;
import com.example.fileshare.processingPack.Resource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UpdateFileServlet", value = "/updateFile")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1 // 1 MB
)
public class UpdateFileServlet extends HttpServlet {
    private final DBConnector connector = DBConnector.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String resourceID = request.getParameter("resource");

        System.out.println(username + ":" + resourceID);
        Resource targetResource = connector.getResource(resourceID);
        String location = targetResource.getLocation();
        String root = location.substring(0,location.lastIndexOf("/")+1);


        boolean isUploaded = false;
        String resourceLocation = null;
        String fileName = "";
        try {
            Part filePart = request.getPart("file");
            fileName= filePart.getSubmittedFileName();
            File file = new File(targetResource.getLocation());
            if(!file.exists())
            {
                file.mkdir();
            }
            resourceLocation = root+"\\"+fileName;
            for (Part part : request.getParts()) {
                part.write(targetResource.getLocation());
            }
            isUploaded = true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher ;
        if(isUploaded)
        {
            File file = new File(resourceLocation);
            Resource resource = new Resource(username,fileName,1,file.length(),"lables",file.getAbsolutePath(),"description","chats", targetResource.getId(), null);
            if(!connector.updateResource(resource,fileName,targetResource.getVersion(),resourceLocation))
            {
                System.out.println("File is updated");
                requestDispatcher = request.getRequestDispatcher("uploadPage.jsp");
                file.delete();
            }else {
                System.out.println("Successfully updated ..");
                session.setAttribute("uploadStatus", "success upload");
                requestDispatcher = request.getRequestDispatcher("home");
            }

        }else
        {
            session.setAttribute("uploadStatus", "failed upload");
            requestDispatcher = request.getRequestDispatcher("uploadPage.jsp");
        }
        requestDispatcher.forward(request,response);
    }
}
