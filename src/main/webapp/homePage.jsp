<%@ page import="com.example.fileshare.processingPack.Resource" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.io.FileUtils" %><%--
  Created by IntelliJ IDEA.
  User: selvakumaran
  Date: 18/12/2021
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp"/>
<html>
<head>
    <title>FileShare</title>

</head>
<body>
<div class="Content" style="display: flex;flex-wrap: wrap ;max-height: 80%">

    <%
        List<Resource> resources = (List<Resource>) session.getAttribute("userResources");
        for (Resource resource : resources) {
    %>
    <div style="width: 300px;padding: 10px ;margin: 20px ;">
        <div class="card" style="width:300px;">
            <img src="https://aroundsketch.github.io/Apple-App-Icons/App%20Icon/Apple/Files/@PNG.png" width="70px" height="70px" alt="Application">
            <div class="card-body">
                <h5 class="card-title"><%=resource.getFileName()%>
                    </li>
                    <%!
                        public String redableSize(long size) {
                            return FileUtils.byteCountToDisplaySize(size);
                        }
                    %>

                    <span class="badge bg-info" style="color: black;"> <%=redableSize(resource.getSize())%></span>


                </h5>
                <p class="card-text"><%=resource.getTimestamp().substring(0,resource.getTimestamp().lastIndexOf('.'))%>
                </p>

                <a href="/FileShare/open/owner/<%=resource.getId()%>" class="btn btn-primary" >Open</a>
                <a href="/FileShare/download/<%=resource.getId()%>" class="btn btn-success">Download</a>
            </div>
        </div>
    </div>
    <% } %>
</div>

</body>
</html>
