package com.example.fileshare.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.fileshare.DB.DBConnector;
import com.example.fileshare.processingPack.Resource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1 // 1 MB
)
public class MultipleFileServlet extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "D:\\test";
    private final DBConnector connector = DBConnector.getInstance();
    private static final long serialVersionUID = 1L;

    public MultipleFileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        RequestDispatcher requestDispatcher = null;

        try {
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : multiparts) {
                boolean isUploaded = false;
                File currentFile = null;
                String name = null;
                if (!item.isFormField()) {
                    name = new File(item.getName()).getName();
                    currentFile = new File(UPLOAD_DIRECTORY + File.separator + name);
                    item.write(currentFile);
                    isUploaded = true;
                }
                if (isUploaded) {
                    Resource resource = new Resource(username, name, 0, currentFile.length(), "lables", currentFile.getAbsolutePath(), "description", "chats", null, null);
                    if (!connector.addResource(resource)) {
                        System.out.println("File is removed");
                        requestDispatcher = request.getRequestDispatcher("uploadPage.jsp");
                        currentFile.delete();
                    } else {
                        session.setAttribute("uploadStatus", "success upload");
                        requestDispatcher = request.getRequestDispatcher("home");
                    }
                }

            }
            request.setAttribute("message", "File uploaded successfully.");
        } catch (Exception ex) {
            request.setAttribute("message", "File upload failed due to : " + ex);
        }

        requestDispatcher.forward(request, response);
    }
}
