package com.example.fileshare.servlets;

import com.example.fileshare.DB.DBConnector;
import com.example.fileshare.processingPack.Resource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UploadServlet", urlPatterns = { "/uploadd" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1 // 1 MB
)
public class UploadServlet extends HttpServlet {

    final String toSaveLocationPath = "D://test";
    private final DBConnector connector = DBConnector.getInstance();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        boolean isUploaded = false;
        String resourceLocation = null;
        String fileName = "";
        try {
            Part filePart = request.getPart("file");
            fileName= filePart.getSubmittedFileName();
            File file = new File(toSaveLocationPath+"\\"+username);
            if(!file.exists())
            {
                file.mkdir();
            }

            File resourcefile = new File(file.getAbsolutePath()+"\\"+fileName);
            if(resourcefile.exists())
            {
                int lastIndexOf = fileName.lastIndexOf('.');
                String ext = fileName.substring(lastIndexOf+1);
                StringBuffer Fname = new StringBuffer(fileName.substring(0, lastIndexOf));
                Fname.append("_new.");
                Fname.append(ext);
                fileName = Fname.toString();
                System.out.println("File name extended as "+fileName);
            }
            resourceLocation = file.getAbsolutePath()+"\\"+fileName;

            for (Part part : request.getParts()) {
                part.write(resourceLocation);
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
            Resource resource = new Resource(username,fileName,0,file.length(),"lables",file.getAbsolutePath(),"description","chats",null,null);
            if(!connector.addResource(resource))
            {
                System.out.println("File is removed");
                requestDispatcher = request.getRequestDispatcher("uploadPage.jsp");
                file.delete();
            }else {
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
