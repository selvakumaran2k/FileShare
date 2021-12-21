<%@ page import="com.example.fileshare.processingPack.Resource" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: selvakumaran
  Date: 18/12/2021
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp"/>

<html>
<head>
    <title>File</title>
    <script>
        function shareUser()
        {
            document.getElementById("target").value = "user";
            document.getElementById("share").submit();

        }
        function shareGroup()
        {
            document.getElementById("target1").value = "group";
            document.getElementById("share1").submit();

        }
    </script>
</head>
<body>
<div class="container">

    <div class="container">
        <div class="row">
            <div class="col">
                <div class="container">
                    <div class = "fileInformation">
                        <%
                            Resource resource = (Resource) session.getAttribute("targetResource");
                        %>
                        <ul class="list-group mt-4 " style="background:#adfde6;">
                            <li class="list-group-item">
                                File name :
                                <strong><%=resource.getFileName()%></strong>
                            </li>
                            <li class="list-group-item">
                                Size :
                                <strong>
                                    <%=resource.getSize()%>
                                </strong> Bytes
                            </li>
<%--                            <li class="list-group-item">--%>
<%--                                <strong>--%>
<%--                                    <%=resource.getVersion()%>--%>
<%--                                </strong> th Version--%>
<%--                            </li>--%>
                            <li class="list-group-item">
                                Owned by
                                <strong>
                                    <%=resource.getOwner()%>
                                </strong>
                            </li>
                            <li class="list-group-item">
                                <%=resource.getDescription()%>
                            </li>
                            <li class="list-group-item">

                                <div class="col-3 ">
                                    <div class="d-flex flex-row bd-highlight ">
<%--                                        <a href="/EventLogAnalyser/editUser/edit/">--%>
<%--                                            <button class="btn btn-success">--%>
<%--                                                update--%>
<%--                                            </button>--%>
<%--                                        </a>--%>
                                        <a href="/FileShare/deleteResource/<%=resource.getId()%>">
                                            <button class="btn btn-danger mx-3 mb-3">
                                                Delete
                                            </button>
                                        </a>
                                        <a href="/FileShare/download/<%=resource.getId()%>">
                                            <button class="btn btn-success mx-3 mb-3">
                                                Download
                                            </button>
                                        </a>

                                    </div>




                                    <div class="modal fade" id="getTargetUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Share with</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form method="post" action="/FileShare/share" id = "share" >
                                                        <div class="mb-3">
                                                            <label for="targetName" class="col-form-label">Recipient:</label>
                                                            <input type="text" class="form-control" id="targetName" name="targetName">
                                                            <input type="text" name = "resource" id="resource_id" value=<%=resource.getId()%> hidden>
                                                            <input type="text" id="target" name="target" hidden>
                                                        </div>
                                                        <div class="mb-3">

                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="button" onclick="shareUser()" class="btn btn-primary">Share</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal fade" id="getTargetGroup" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel1">Share with group</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form method="post" action="/FileShare/share" id = "share1">
                                                        <div class="mb-3">
                                                            <label for="recipient-name1" class="col-form-label">Recipient:</label>
<%--                                                            <input type="text" class="form-control" id="recipient-name1" name="targetName">--%>
                                                            <select class="form-select" aria-label="Default select example"  id="recipient-name1"  name="targetName">
                                                                <%
                                                                    List<String[]> groups = (List<String[]>) session.getAttribute("userGroups");
                                                                    for(String s[] : groups) {
                                                                %>
                                                                <option value="<%=s[1]%>">
                                                                    <strong><%=s[0]%></strong>
                                                                    ~
                                                                    <%=s[1]%>
                                                                </option>
                                                                <% } %>
                                                            </select>
                                                            <input type="text" class="form-control" name = "resource" id="resource_id1" value=<%=resource.getId()%> hidden>
                                                            <input type="text" class="form-control" id="target1" name="target" hidden>

                                                        </div>
                                                        <div class="mb-3">

                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="button" onclick="shareGroup()" class="btn btn-primary">Share</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <button type="button" class="btn btn-primary mr-3" data-bs-toggle="modal" data-bs-target="#getTargetUser">Share</button>
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#getTargetGroup">add to group</button>
                            </li>
                        </ul>

                    </div>

                </div>

            </div>

            <div class="col-3">
               <p>Groups</p>
            </div>
        </div>
    </div>



</div>
</body>
</html>
