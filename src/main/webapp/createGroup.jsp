<%--
  Created by IntelliJ IDEA.
  User: selvakumaran
  Date: 19/12/2021
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp"/>
<html>
<head>
    <title>Create Group</title>
</head>
<body>
<div class="container">
    <div class="container" style="width: 60%">
        <form  action="/FileShare/createGroup" method="post">
            <div class="form-group mt-3">
                <label for="name">Group name</label>
                <input type="text" class="form-control" id="name" name = "name" placeholder="Name your group" required>
            </div>
            <div class="form-group">
                <label for="members">Members</label>
                <input type="text" class="form-control" id="members" name = "members" placeholder="Enter userID of members separated by comma Eg : user1,user2">
            </div>
<%--            <div class="form-group mt-3 mb-3 p-2" style="background:#99ff9d; width: max-content">--%>
<%--                <div class="form-check">--%>
<%--                    <input class="form-check-input" type="checkbox" name = "isPublic" id="flexCheckChecked" value="public">--%>
<%--                    <label class="form-check-label" for="flexCheckChecked">--%>
<%--                        Public--%>
<%--                    </label>--%>
<%--                </div>--%>
<%--            </div>--%>
            <button type="submit" class="btn btn-success mt-3">Create Group</button>
        </form>
    </div>
</div>
</body>
</html>
