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
                Create New Group
            </button>
        </a>

        <div class="row">
            <div class="col">
                <div class="container">
                    <div class="list-group mt-5" style="width: 50%">
                        <h3 class="text-white">Groups</h3>
                        <%
                            List<String[]> userGroups = (List<String[]>) session.getAttribute("userGroups");
                            for (String userGroup[] : userGroups) {
                        %>

                        <a href="/FileShare/openGroup/<%=userGroup[1]%>"
                           class="list-group-item list-group-item-action mb-2">
                            <%=userGroup[0]%> ~ <%=userGroup[1]%>
                        </a>
                        <% } %>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="container">

                    <div class="list-group mt-3 mb-3">
                        <h3>Group join requests</h3>
                        <%
                            List<String[]> userRequest = (List<String[]>) session.getAttribute("userRequest");
                            for (String userGroup[] : userRequest) {
                        %>
                        <li class="list-group-item mb-2">
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <strong><%=userGroup[1]%>
                                        </strong>
                                    </div>
                                    <div class="col">
                                        <p>
                                            Owned by : <strong><%=userGroup[2]%>
                                        </strong>
                                        </p>
                                    </div>
                                    <div class="col-2">
                                        <a href="/FileShare/join/<%=userGroup[0]%>" class="btn btn-success">
                                            Join
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <% } %>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
