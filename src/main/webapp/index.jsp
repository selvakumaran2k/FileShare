<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>File share</title>
</head>
<style>
    @import url(http://fonts.googleapis.com/css?family=Roboto:400);
    body {
        background-color:#fff;
        -webkit-font-smoothing: antialiased;
        font: normal 14px Roboto,arial,sans-serif;
    }

    .container {
        padding: 25px;
        position: fixed;
        left: 45%;
        top: 45%;
        transform: translate(-50%, -50%);
    }

    .form-login {
        background-color: #EDEDED;
        padding-top: 10px;
        padding-bottom: 20px;
        padding-left: 20px;
        padding-right: 20px;
        border-radius: 15px;
        border-color:#d2d2d2;
        border-width: 5px;
        box-shadow:0 1px 0 #cfcfcf;
    }

    h4 {
        border:0 solid #fff;
        border-bottom-width:1px;
        padding-bottom:10px;
        text-align: center;
    }

    .form-control {
        border-radius: 10px;
    }

    .wrapper {
        text-align: center;
    }

</style>
<body style="background-color: black">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<!--Pulling Awesome Font -->
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container">
    <form method="post" action="login">
    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
                <h4>Welcome back.</h4>
                <input type="text" id="username" name = "username"  class="form-control input-sm chat-input" placeholder="username" autofocus />
                </br>
                <input type="password" id="password" name = "password" class="form-control input-sm chat-input" placeholder="key" />
                </br>
                <div class="wrapper">
            <span class="group-btn">
                <button type="submit" class="btn btn-primary btn-md">open <i class="fa fa-sign-in"></i></button>
            </span>
                </div>
            </div>

        </div>
    </div>
    </form>
</div>

</body>
</html>