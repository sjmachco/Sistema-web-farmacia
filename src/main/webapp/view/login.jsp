<%-- 
    Document   : login
    Created on : 5 nov. 2024, 17:55:59
    Author     : TIVITO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html, 
              width=device-width, initial-scale=1.0; charset=UTF-8" 
              name="viewport">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link rel="stylesheet" type = "text/css" href="${pageContext.request.contextPath}/css/estilos.css"/>
        <% String no_login = (String) request.getAttribute("nologin");%>
    </head>
    <body>
        <div class="formulario">
            <form id="formRegistrarLo" action="Login_controller" method="post" autocomplete="off">
                <div style="text-align: center">
                    <img src="${pageContext.request.contextPath}/imagenes/user-img.png" alt="user"/>
                </div>
                <div style="margin: 20px;">
                    <div style="margin-top: 30px;" class="mb-3">
                        <label for="usuario" class="form-label">Usuario</label>
                        <input type="text" class="form-control" id="usuario" name="usuario" 
                               aria-describedby="emailHelp" required>
                    </div>  
                    <div class="mb-3">
                        <label for="pass" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="pass" name="pass" required>
                    </div>
                </div>
                <div style="text-align: center">
                    <button type="submit" class="btn btn-primary">Ingresar</button>
                </div>
                <%if (no_login != null){%>
                <div id="login-error" style="text-align: center; margin-top: 10px;" class="mb-3">
                    <label style="color: red;" for="pass" class="form-label"><%=no_login%></label>
                </div>              
                <%}%>
            </form>
        </div>
    </body>
</html>
