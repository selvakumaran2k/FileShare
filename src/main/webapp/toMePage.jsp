<%@ page import="com.example.fileshare.processingPack.Resource" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: selvakumaran
  Date: 19/12/2021
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp"/>
<html>
<head>
    <title>shared to me</title>
</head>
<body>
<div class="container mt-4" style="color: white">
    <h3>Shared with me</h3>
</div>
<div class="Content" style="display: flex;flex-wrap: wrap ;max-height: 80%">

    <%
        List<Resource> resources = (List<Resource>) session.getAttribute("sharedResources");
        for (Resource resource : resources) {
    %>
    <div style="width: 300px;padding: 10px ;margin: 20px ;">
        <div class="card" style="width:300px;">
            <img src="https://aroundsketch.github.io/Apple-App-Icons/App%20Icon/Apple/Files/@PNG.png" width="70px" height="70px" alt="Application">
            <div class="card-body">
                <h5 class="card-title"><%=resource.getFileName()%>
                    <%--                    <% if (true) { %>--%>
                    <%--                    <span class="badge bg-success">ACTIVE</span>--%>
                    <%--                    <% } else {%>--%>
                    <%--                    <span class="badge bg-danger">INACTIVE</span>--%>
                    <%--                    <% } %>--%>
                </h5>
                <p class="card-text"><%=resource.getOwner()%>
                </p>
                <a href="/FileShare/open/single/<%=resource.getId()%>" class="btn btn-primary" >Open</a>
                <a href="/FileShare/download/<%=resource.getId()%>" class="btn btn-success">Download</a>
            </div>
        </div>
    </div>
    <% } %>
</div>
</body>
</html>
