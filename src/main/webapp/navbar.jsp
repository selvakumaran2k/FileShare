<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body
        {
            background: #4a58e8;
        }
    </style>
    <title>File share</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body style="background:#516bf5;">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg  navbar-dark text-light bg-dark fixed" style="background-color: #e3f2fd;">
    <!-- Container wrapper -->
    <div class="container-fluid">
        <!-- Toggle button -->
        <button
                class="navbar-toggler"
                type="button"
                data-mdb-toggle="collapse"
                data-mdb-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <i class="fas fa-bars"></i>
        </button>

        <!-- Collapsible wrapper -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <!-- Navbar brand -->
            <h4>File Share</h4>

        <!-- Collapsible wrapper -->
            <ul class="navbar-nav mx-auto text-center">
                <li class="nav-item active text-center mr-3">
                    <a class="nav-link" href="/FileShare/home">Mine<span class="sr-only"></span></a>
                </li>
                <li class="nav-item text-center mr-3">
                    <a class="nav-link" href="/FileShare/toMe">To Me</a>
                </li>
                <li class="nav-item text-center mr-3">
                    <a class="nav-link" href="/FileShare/ours">Our's</a>
                </li>
            </ul>
        </div>
        <!-- Right elements -->
        <div class="d-flex align-items-center">

<%--            <a style="margin-right: 18px;" href="home" class="btn btn-primary mr-5">Refresh Page</a>--%>
            <a style="margin-right: 18px;" href="/FileShare/uploadPage.jsp" class="btn btn-success mr-5">Upload</a>
            <%
                String userName = (String) session.getAttribute("username");
            %>
            <h3><%=userName%></h3>
            <a
                    class="d-flex align-items-center btn btn-error ml-4"
                    href="/FileShare/logout"
                    role="button"
                    data-mdb-toggle="dropdown"
                    aria-expanded="false"
            >
                <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="red" class="bi bi-power" viewBox="0 0 16 16">
                    <path d="M7.5 1v7h1V1h-1z"/>
                    <path d="M3 8.812a4.999 4.999 0 0 1 2.578-4.375l-.485-.874A6 6 0 1 0 11 3.616l-.501.865A5 5 0 1 1 3 8.812z"/>
                </svg>
            </a>

            </a>

        </div>
        <!-- Right elements -->
    </div>
    <!-- Container wrapper -->
</nav>
<!-- Navbar -->

</body>
</html>