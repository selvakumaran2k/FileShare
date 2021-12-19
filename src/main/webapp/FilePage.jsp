<%@ page import="com.example.fileshare.processingPack.Resource" %><%--
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
                        <ul class="list-group">
                            <li class="list-group-item"><%=resource.getFileName()%></li>
                            <li class="list-group-item"><%=resource.getSize()%></li>
                            <li class="list-group-item"><%=resource.getVersion()%></li>
                            <li class="list-group-item"><%=resource.getOwner()%></li>
                            <li class="list-group-item"><%=resource.getDescription()%></li>
                            <li class="list-group-item">
                                <div class="col-3 ">
                                    <div class="d-flex flex-row bd-highlight ">
                                        <a href="/EventLogAnalyser/editUser/edit/">
                                            <button class="btn btn-primary">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                </svg>
                                                Update
                                            </button>
                                        </a>
                                        <a href="/EventLogAnalyser/editUser/delete/">
                                            <button class="btn btn-danger mx-3">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-person-x-fill" viewBox="0 0 16 16">
                                                    <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6.146-2.854a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                Delete
                                            </button>
                                        </a>
                                        <a href="/EventLogAnalyser/editUser/delete/">
                                            <button class="btn btn-primary mx-3">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-person-x-fill" viewBox="0 0 16 16">
                                                    <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6.146-2.854a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                Share
                                            </button>
                                        </a>
                                        <a href="/EventLogAnalyser/editUser/delete/">
                                            <button class="btn btn-success mx-3">
                                                Add to group
                                            </button>
                                        </a>
                                    </div>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#getTargetUser">Share</button>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#getTargetGroup">add to group</button>


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
                                                            <input type="submit">
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
                                                            <input type="text" class="form-control" id="recipient-name1" name="targetName">
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
