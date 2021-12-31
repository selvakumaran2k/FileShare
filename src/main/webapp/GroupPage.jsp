<%@ page import="java.util.List" %>
<%@ page import="com.example.fileshare.processingPack.Resource" %>
<%@ page import="com.example.fileshare.processingPack.Group" %>
<%@ page import="org.apache.commons.io.FileUtils" %><%--
  Created by IntelliJ IDEA.
  User: selvakumaran
  Date: 20/12/2021
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp"></jsp:include>
<html>
<head>
    <%
        Group group = (Group) session.getAttribute("targetGroup");
    %>
    <title><%=group.getGroupName()%></title>
</head>
<body>
<div class="container mt-2">

    <div class="details mb-6 p-3" style=" width: max-content;color: white">
        <h3 style="color: white"><strong><%=group.getGroupName()%></strong></h3>&nbsp;&nbsp;
        created at : <%=group.getTimeCreated()%>
<%--        <%=group.getDescription()%>--%>
    </div>
    <div class="row">
        <div class="col ml-3" style="display: flex;flex-wrap: wrap; background:#4f64d0;">
            <%
                for (Resource resource : group.getGroupResources()) {
            %>
            <div style="width: max-content;padding: 10px ;margin: 20px ;">
                <div class="card" style="width:max-content;">
                    <img src="https://aroundsketch.github.io/Apple-App-Icons/App%20Icon/Apple/Files/@PNG.png" width="70px" height="70px" alt="Application">
                    <div class="card-body">
                        <h5 class="card-title"><%=resource.getFileName()%>
                            <%!
                                public String redableSize(long size) {
                                    return FileUtils.byteCountToDisplaySize(size);
                                }
                            %>

                            <span class="badge bg-info" style="color: black;"> <%=redableSize(resource.getSize())%></span>
                            <%--                    <% if (true) { %>--%>
                            <%--                    <span class="badge bg-success">ACTIVE</span>--%>
                            <%--                    <% } else {%>--%>
                            <%--                    <span class="badge bg-danger">INACTIVE</span>--%>
                            <%--                    <% } %>--%>
                        </h5>
                        <p class="card-text">
                            Updated @ :
                            <%=resource.getTimestamp().substring(0,resource.getTimestamp().lastIndexOf('.'))%>
                        </p>
                        <a href="/FileShare/open/group/<%=resource.getId()%>" class="btn btn-primary" >Open</a>
                        <%--                <a href="/Master/removeApplication/<%=resource.getSize()%>" class="btn btn-danger">X</a>--%>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        <div class="col-2 ml-3" style="background:#8F9FF4;">
            <div class="list-group mt-5 mb-3">
                <h4>Members</h4>
                <%
                    for (String userGroup : group.getGroupMembers()) {
                %>
                <a href="#" class="list-group-item list-group-item-action">
                    <%=userGroup%>
                </a>
                <% } %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
