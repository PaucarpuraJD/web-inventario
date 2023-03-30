<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
    <style>
        input [type="text"]{
            border-bottom-left-radius: 0px;
            border-bottom-right-radius: 0px;
        }
        input [type="password"]{
            border-top-left-radius: 0px;
            border-top-right-radius: 0px;
            border-top: 0px;
        }
    </style>

    <div class="text-center mt-5">
        <form action="${pageContext.request.contextPath}/login" method="post" style="max-width:300px;margin:auto;">
            <img class="mt-4 mb-4" src="logo.png" height="72" alt="Logo de Sesion">
            <h1 class="h3 mb-3 font-weight-normal">Inicio de Sesion</h1>
            <input type="text" name="username"  class="form-control" placeholder="Username" required autofocus>
            <input type="password" name="password" class="form-control mt-3" placeholder="Password">
            <div class="mt-3">
                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Inicio">
            </div>

        </form>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>