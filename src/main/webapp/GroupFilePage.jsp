<%@ page import="com.example.fileshare.processingPack.Resource" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.io.FileUtils" %>
<%@ page import="com.example.fileshare.processingPack.Group" %><%--
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
        function shareUser() {
            document.getElementById("target").value = "user";
            document.getElementById("share").submit();

        }

        function shareGroup() {
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
                    <div class="fileInformation">
                        <%
                            Resource resource = (Resource) session.getAttribute("targetResource");
                        %>
                        <ul class="list-group mt-4 " style="background:#adfde6;">
                            <li class="list-group-item">
                                File name :
                                <strong><%=resource.getFileName()%>
                                </strong>
                            </li>
                            <%!
                                public String redableSize(long size) {
                                    return FileUtils.byteCountToDisplaySize(size);
                                }
                            %>
                            <li class="list-group-item">
                                Size :
                                <strong>
                                    <%=redableSize(resource.getSize())%>
                                </strong>
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
                                        <%
                                            String username = (String) session.getAttribute("username");
                                            Group targetGroup = (Group) session.getAttribute("targetGroup");
                                            if (username.equals(resource.getOwner()))
                                            {
                                        %>
                                        <a href="/FileShare/removeResource/<%=resource.getId()%>_<%=targetGroup.getGroup_id()%>">
                                            <button class="btn btn-danger mx-3 mb-3">
                                                Remove
                                            </button>
                                        </a>
                                        <%}%>
                                        <a href="/FileShare/download/<%=resource.getId()%>">
                                            <button class="btn btn-success mx-3 mb-3">
                                                Download
                                            </button>
                                        </a>

                                    </div>


                                </div>
                            </li>
                            <li class="list-group-item">

                            </li>
                        </ul>

                    </div>

                </div>

            </div>

            <div class="col-4">
                <ul class="list-group mt-4 " style="background:#adfde6;">

                    <li class="list-group-item">
                        <div class="list-group">
                            <a href="#" class="list-group-item list-group-item-action">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">username</h5>
                                    <%--                                    <small class="text-muted">3 days ago</small>--%>
                                </div>
                                <p class="mb-1">Sample message</p>
                                <%--                                <small class="text-muted">And some muted small print.</small>--%>
                            </a>
                        </div>
                    </li>
                    <li class="list-group-item">
                        second message
                    </li>
                    <li class="list-group-item">
                        <div class="col-auto">
                            <form action="/FileShare/addComment" method="post">
                                <input type="text" class="form-control" id="comment" name="comment" placeholder="~~~">
                                <input type="text" class="form-control" id="resourceID" name="resourceID"
                                       placeholder="~~~" hidden value="<%=resource.getId()%>">
                            </form>
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-primary mb-3">Add</button>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>


</div>
</body>
</html>
