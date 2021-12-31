<%@ page import="com.example.fileshare.processingPack.Resource" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.io.FileUtils" %><%--
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

        </div>
    </div>


</div>
</body>
</html>
