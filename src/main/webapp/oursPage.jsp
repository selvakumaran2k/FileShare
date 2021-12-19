<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: selvakumaran
  Date: 19/12/2021
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp"/>
<html>
<head>
    <title>My Groups</title>
</head>
<body>
<div class="container">
    <div class="container">
        <a href="/FileShare/createGroup.jsp">
            <button class="btn btn-success mt-5">
                Create New
            </button>
        </a>

        <div class="list-group mt-5">
            <%
                List<String[]> userRequest = (List<String[]>) session.getAttribute("userRequest");
                for (String userGroup[] : userRequest) {
            %>

            <a href="/join/<%=userGroup[0]%>" class="list-group-item list-group-item-action">
                <%=userGroup[1]%>
            </a>
            <p>
                <%=userGroup[2]%>
            </p>
            <% } %>
        </div>

        <div class="list-group mt-5">
            <%
                List<String> userGroups = (List<String>) session.getAttribute("userGroups");
                for (String userGroup : userGroups) {
            %>

            <a href="/FileShare/openGroup/<%=userGroup%>" class="list-group-item list-group-item-action">
                <%=userGroup%>
            </a>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
